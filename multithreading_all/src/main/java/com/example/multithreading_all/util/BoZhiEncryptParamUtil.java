package com.example.multithreading_all.util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @Author: weirui
 * @Date: 2021/01/11
 * @Description:
 */
public class BoZhiEncryptParamUtil {
    /**
     * 参数加密
     *
     * @param srcStr 要加密的原始字符串
     * @param rsaEncryptPubKey 加密RSA公钥
     * @return
     */
    public static String encryptParam(String srcStr, String rsaEncryptPubKey) {
        try {
            byte[] s1Byte = BoZhiRsaUtil.encryptByPublicKey(srcStr.getBytes(),rsaEncryptPubKey);
            String s1 = BoZhiRsaUtil.encryptBASE64(s1Byte);
            return s1;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 参数解密
     * @param encryptedStr 已加密的字符串
     * @param rsaSignPrivKey 加密RSA私钥
     * @return
     */
    public static String decryptParam(String encryptedStr, String rsaSignPrivKey) {
        try {
            byte[] s1EncryptedByte = BoZhiRsaUtil.decryptBASE64(encryptedStr);
            byte[] s1Byte = BoZhiRsaUtil.decryptByPrivateKey(s1EncryptedByte, rsaSignPrivKey);
            return new String(s1Byte);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String s = "{\"bottleInnerCode\":\"HTTP://VJ1.TV/ZW/QF7MM3NB5PFV\",\"openId\":\"oaFdy5L2fkpu81DQywSsn0aRCGHw\",\"provinceCode\":\"110000\",\"provinceName\":\"北京市\",\"cityCode\":\"110200\",\"cityName\":\"北京市\",\"districtCode\":\"110202\",\"districtName\":\"东城区\",\"longitude\":\"116.42453610653013\",\"latitude\":\"39.90774545897724\"}";
        String url = "https://greatwallclubdev.cofco.com/wechat/open/vsroce/scanBottleInnerCode";

        SyncRequestBody syncRequestBody = new SyncRequestBody();
        syncRequestBody.setBottleInnerCode("HTTP://VJ1.TV/ZW/QF7MM3NB5PFV");
        syncRequestBody.setOpenId("oaFdy5L2fkpu81DQywSsn0aRCGHw");
        syncRequestBody.setProvinceCode("110000");
        syncRequestBody.setProvinceName("北京市");
        syncRequestBody.setCityCode("110200");
        syncRequestBody.setCityName("北京市");
        syncRequestBody.setDistrictCode("110202");
        syncRequestBody.setDistrictName("东城区");
        syncRequestBody.setLongitude("116.42453610653013");
        syncRequestBody.setLatitude("39.90774545897724");
        String srcStr = JsonUtils.toJson(syncRequestBody);
        System.out.println(srcStr);

        String requestBodyStr = encryptParam(srcStr,"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCiAuohh1ag4UWHE9FmtjKlYTUqK5ga8UXZtnGewmbTZlo5NyOop+sUyQq8smU5os4vg0H5JTmnE/+RrX6Us+bQZBY7rrafc0oxPcuIp2P6Atwy7fELGrqgAYS42mPRlQvSrPMgX7lW+uDjOp0iIPodGT+UYXSVBgz0oIjzQuUC5wIDAQAB");

        Map<String, String> pa = Maps.newHashMap();
        pa.put("requestBodyStr",requestBodyStr);

        System.out.println(requestBodyStr);

        JSONObject resultJson = HttpClientUtil.httpFormReq(url, pa);

        System.out.println(resultJson);

        System.out.println("requestBodyStr:");
        System.out.println(s);


        System.out.println(decryptParam(requestBodyStr,"MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKIC6iGHVqDhRYcT0Wa2MqVhNSormBrxRdm2cZ7CZtNmWjk3I6in6xTJCryyZTmizi+DQfklOacT/5GtfpSz5tBkFjuutp9zSjE9y4inY/oC3DLt8QsauqABhLjaY9GVC9Ks8yBfuVb64OM6nSIg+h0ZP5RhdJUGDPSgiPNC5QLnAgMBAAECgYAFapGHQ24K/N0FMg1rIKY6zzAm08RHCk/qgmX0B83GPYLArO49QjfivwHj4xbtQVshTYMUIEF4K3v8088Ki6Uovr75glwlVFyTMxi5aQ0WQ1MeFuVGV3fA+CUL959Gje50lUhbQqLhOXvYE/fInutTOn88pf0nteRIOC3iP8PwSQJBANQiGw6jbQCJdQfYDqAkAfuQOptohqLrCi67wh9R7Pax/LpA3i5Ugk7wwATlhE3DRsmoRwt9nkjAMrlEWKtkFP0CQQDDg3ZZE1ml0uWcbhnk1yAOnQJ8b9YNtSDCisp8h//RhsJ3UZEtwWK1ZOnc0SLTVl38HJn1ID55iFVrozIPPo6zAkEAmyA5sxG1Y0AhsdPAlkDepkzJXt4oHrlMX/JzuZ2acKilzLok/7JS/FE3yhzhDFCx0kPZeECrYD4UmcCuKt9sLQJAAwfutcLPCPsUBDDQxfNv08orV7KEJwI/18Q/D18sYBsuuJrTHVp6JUXDOmEjoRMdseLfoCU30rqCWIDXLdVScQJAcupaHYPgrMiolRU2NwSeKXKpadGRTXn8PjcoHZmoAUJwgtA2pp0anfjSK4tkfZx2opl8lzkRZgXa6BzRQF7BOA=="));


        String sssssss = encryptParam(srcStr,"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCiAuohh1ag4UWHE9FmtjKlYTUqK5ga8UXZtnGewmbTZlo5NyOop+sUyQq8smU5os4vg0H5JTmnE/+RrX6Us+bQZBY7rrafc0oxPcuIp2P6Atwy7fELGrqgAYS42mPRlQvSrPMgX7lW+uDjOp0iIPodGT+UYXSVBgz0oIjzQuUC5wIDAQAB");

        System.out.println(decryptParam(sssssss,"MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKIC6iGHVqDhRYcT0Wa2MqVhNSormBrxRdm2cZ7CZtNmWjk3I6in6xTJCryyZTmizi+DQfklOacT/5GtfpSz5tBkFjuutp9zSjE9y4inY/oC3DLt8QsauqABhLjaY9GVC9Ks8yBfuVb64OM6nSIg+h0ZP5RhdJUGDPSgiPNC5QLnAgMBAAECgYAFapGHQ24K/N0FMg1rIKY6zzAm08RHCk/qgmX0B83GPYLArO49QjfivwHj4xbtQVshTYMUIEF4K3v8088Ki6Uovr75glwlVFyTMxi5aQ0WQ1MeFuVGV3fA+CUL959Gje50lUhbQqLhOXvYE/fInutTOn88pf0nteRIOC3iP8PwSQJBANQiGw6jbQCJdQfYDqAkAfuQOptohqLrCi67wh9R7Pax/LpA3i5Ugk7wwATlhE3DRsmoRwt9nkjAMrlEWKtkFP0CQQDDg3ZZE1ml0uWcbhnk1yAOnQJ8b9YNtSDCisp8h//RhsJ3UZEtwWK1ZOnc0SLTVl38HJn1ID55iFVrozIPPo6zAkEAmyA5sxG1Y0AhsdPAlkDepkzJXt4oHrlMX/JzuZ2acKilzLok/7JS/FE3yhzhDFCx0kPZeECrYD4UmcCuKt9sLQJAAwfutcLPCPsUBDDQxfNv08orV7KEJwI/18Q/D18sYBsuuJrTHVp6JUXDOmEjoRMdseLfoCU30rqCWIDXLdVScQJAcupaHYPgrMiolRU2NwSeKXKpadGRTXn8PjcoHZmoAUJwgtA2pp0anfjSK4tkfZx2opl8lzkRZgXa6BzRQF7BOA=="));





    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SyncRequestBody {
        String bottleInnerCode;
        String openId;
        String provinceCode;
        String provinceName;
        String cityCode;
        String cityName;
        String districtCode;
        String districtName;
        String longitude;
        String latitude;
    }
}
