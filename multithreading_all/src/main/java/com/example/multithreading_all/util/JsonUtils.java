package com.example.multithreading_all.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TimeZone;


/**
 * json解析工具（依赖jackson）
 * Created by log.chang on 2023/8/24
 */
@SuppressWarnings("all")
@Slf4j
public class JsonUtils {

    private static ObjectMapper defaultMapper;
    private static ObjectMapper snakeMapper;

    static {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        defaultMapper = new ObjectMapper();
        defaultMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        defaultMapper.setDateFormat(sdf);
        defaultMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        snakeMapper = new ObjectMapper();
        snakeMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        snakeMapper.setDateFormat(sdf);
        snakeMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        snakeMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    /**
     * 对象转json
     * 1、key默认与对象属性名相同
     * 2、配置JsonProperty则使用配置做key
     */
    public static String toJson(Object obj) {
        try {
            return obj == null ? null : defaultMapper.writeValueAsString(obj);
        } catch (IOException var2) {
             log.info("[writeValueAsString]：" + var2.getMessage(), var2);
            return null;
        }
    }

    /**
     * 对象转json
     * 1、如果对象属性名为驼峰则json对应的key转为下划线
     * 2、配置JsonProperty则使用配置做key
     */
    public static String toJsonSnake(Object obj) {
        try {
            return obj == null ? null : snakeMapper.writeValueAsString(obj);
        } catch (IOException var2) {
             log.info("[writeValueAsString]：" + var2.getMessage(), var2);
            return null;
        }
    }

    public static <T> T toObj(String json, Class<T> clazz) {
        try {
            return StringUtils.isBlank(json) ? null : defaultMapper.readValue(json, clazz);
        } catch (IOException var3) {
             log.info("[readValue]：" + var3.getMessage(), var3);
            return null;
        }
    }


    public static <T> T toObj(String json, TypeReference<T> typeRef) {
        try {
            return StringUtils.isBlank(json) ? null : (T) defaultMapper.readValue(json, typeRef);
        } catch (IOException var3) {
            log.info("[readValue]：" + var3.getMessage(), var3);
            return null;
        }
    }


    public static <T> T toObjSnake(String json, Class<T> clazz) {
        try {
            return StringUtils.isBlank(json) ? null : snakeMapper.readValue(json, clazz);
        } catch (IOException var3) {
             log.info("[readValue]：" + var3.getMessage(), var3);
            return null;
        }
    }

    public static <T> T toObj(String json, Class<? extends Collection> collectionClass, Class<?> elementClass) {
        try {
            if (StringUtils.isBlank(json)) {
                return null;
            } else {
                JavaType javaType = defaultMapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
                return defaultMapper.readValue(json, javaType);
            }
        } catch (IOException var4) {
             log.info("[toObj]" + var4.getMessage(), var4);
            return null;
        }
    }

    public static <T> T toObj(String json, Class<? extends Map> mapClass, Class<?> keyClass, Class<?> valueClass) {
        try {
            if (StringUtils.isBlank(json)) {
                return null;
            } else {
                JavaType javaType = defaultMapper.getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
                return defaultMapper.readValue(json, javaType);
            }
        } catch (IOException var5) {
             log.info("[toObj]" + var5.getMessage(), var5);
            return null;
        }
    }

    public static String formatJson(String json) {
        try {
            if (json == null) {
                return null;
            } else {
                Object obj = defaultMapper.readValue(json, Object.class);
                return defaultMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            }
        } catch (Exception var2) {
             log.info("[formatJson]：" + var2.getMessage(), var2);
            return json;
        }
    }

    /**
     * 获取json某个节点
     */
    public static <T> T elementToObj(String json, String key, Class<T> clazz) {
        return elementToObj(defaultMapper, json, key, clazz);
    }

    public static <T> T elementToObjSnake(String json, String key, Class<T> clazz) {
        return elementToObj(snakeMapper, json, key, clazz);
    }

    private static <T> T elementToObj(ObjectMapper mapper, String json, String key, Class<T> clazz) {
        JsonNode node = null;
        try {
            node = mapper.readTree(json);
            JsonNode value = node.findValue(key);
            return mapper.readValue(value.toString(), clazz);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 对象转json 过滤掉空值
     */
    public static String obj2JsonByFiledOrder(Object obj, boolean acceptNull) {
        Map<String, Object> results = new LinkedHashMap();
        if (obj == null) {
            return JsonUtils.toJson(results);
        } else {
            Field[] declaredFields = obj.getClass().getDeclaredFields();
            Field[] var4 = declaredFields;
            int var5 = declaredFields.length;
            for (int var6 = 0; var6 < var5; ++var6) {
                Field field = var4[var6];
                field.setAccessible(true);
                try {
                    Method method = obj.getClass().getMethod("get" + field.getName().toUpperCase().charAt(0) + field.getName().substring(1), null);
                    method.setAccessible(true);
                    Object value = method.invoke(obj, null);
                    if (value != null || acceptNull) {
                        JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);
                        results.put(jsonProperty != null ? jsonProperty.value() : field.getName(), value);
                    }
                } catch (Exception var9) {
                }
            }
            return JsonUtils.toJson(results);
        }
    }

}
