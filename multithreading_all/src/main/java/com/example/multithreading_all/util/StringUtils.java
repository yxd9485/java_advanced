package com.example.multithreading_all.util;

import com.google.common.collect.Lists;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * module: 工具类<br/>
 * <p>
 * description: 字符串工具类<br/>
 *
 * @author XiaoDong.Yang
 * @date: 2023/8/24 16:26
 */
@SuppressWarnings("all")
public class StringUtils {

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 是否为空或空串
     *
     * @param arg 源字符串
     * @return 是否空或空串
     */
    public static boolean isBlank(String arg) {
        return arg == null || arg == "" || arg.isEmpty() || arg == "{}";
    }

    /**
     * 判断数字类型
     * @param arg
     * @param bool
     * @return
     */
    public static boolean isBlank(String arg,boolean bool) {
        if (bool) {
            return arg == null || arg.isEmpty() || arg == "{}" || arg == "0";
        }
        return arg == null || arg.isEmpty() || arg == "{}";
    }

    /**
     * 是否不为空且不为空串
     *
     * @param arg
     * @return
     */
    public static boolean isNotBlank(String arg) {
        return !isBlank(arg);
    }

    /**
     * 是否为空或空串或空白符
     */
    public static boolean isTrimBlank(String arg) {
        return arg == null || arg.isEmpty() || arg.trim().isEmpty();
    }

    /**
     * 对象转字符串
     */
    public static String obj2str(Object o) {
        return obj2str(o, null);
    }

    /**
     * 对象转字符串
     */
    public static String obj2str(Object o, String defValue) {
        if (o == null) {
            return defValue;
        }
        if (o instanceof String) {
            if ("null".equals(o) || "'null'".equals(o) || "NULL".equals(o) || "'NULL'".equals(o)) {
                return defValue;
            }
            return (String) o;
        } else {
            return o.toString();
        }
    }

    /**
     * 字符串拼接
     */
    public static String joinStr(String joinChar, String... args) {
        StringBuilder sb = new StringBuilder();
        return  sb.append(joinChar).append(args).toString();
    }

    /**
     * 字符串拼接
     */
    public static String joinStr(String joinChar, List<String> args) {
        if (args == null || args.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            if (sb.length() > 0) {
                sb.append(joinChar);
            }
            sb.append(arg);
        }
        return sb.toString();
    }

    /**
     * 字符串拼接
     */
    public static String joinStr(String joinChar, Collection<String> args) {
        if (args == null || args.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            if (sb.length() > 0) {
                sb.append(joinChar);
            }
            sb.append(arg);
        }
        return sb.toString();
    }

    /**
     * 格式化字符串，格式：hi,{0} hello world {1}, welcome {0}
     */
    public static String formatString(String str, Object... args) {
        return MessageFormat.format(str, args);
    }

    public static String md5(String str) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(str.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        int length = md5code.length();
        for (int i = 0; i < 32 - length; i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    public static String buildQueryString(String uri, Map<String, String[]> paramMap, String charset) {
        if (paramMap == null || paramMap.isEmpty()) {
            return uri;
        }
        if (charset == null) {
            charset = StandardCharsets.UTF_8.name();
        }
        StringBuilder sb = new StringBuilder(uri);
        int index = 1;
        for (Map.Entry<String, String[]> param : paramMap.entrySet()) {
            if (index == 1 && !uri.contains("?")) {
                sb.append("?");
            } else {
                sb.append("&");
            }
            try {
                sb.append(URLEncoder.encode(param.getKey(), charset));
                sb.append("=");
                String[] values = param.getValue();
                if (values != null && values.length > 0) {
                    String value = values.length == 1 ? values[0] : JsonUtils.toJson(values);
                    sb.append(URLEncoder.encode(value, charset));
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            index++;
        }
        return sb.toString();
    }


    /**
     * 驼峰转下划线
     *
     * @param str 驼峰字符串
     * @return 下划线字符串
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }



    public static void main(String[] args) {
        List<String> objects = Lists.newArrayList();
        objects.add(null);
        objects.add(null);
        for (int i = 0;i<20;i++) {
            if (i==10) {

                break;
            }

            System.out.println(i);

        }


      //  System.out.println(UUID.randomUUID().toString());


        System.out.println(objects.size());
    }



}
