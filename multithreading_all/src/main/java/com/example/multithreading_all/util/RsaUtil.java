package com.example.multithreading_all.util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

/**
 * module: 应用模块名称<br/>
 * <p>
 * description: 描述<br/>
 *
 * @author XiaoDong.Yang
 * @date: 2023/12/11 18:14
 */
public class RsaUtil {

    public static String encryptParam(String srcStr, String rsaEncryptPubKey) {
        try {
            byte[] s1Byte = RsaUtil.encryptByPublicKey(srcStr.getBytes(), rsaEncryptPubKey);
            String s1 = RsaUtil.encryptBASE64(s1Byte);
            return s1;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] encryptByPublicKey(byte[] data, String key) throws Exception {
        byte[] keyBytes = decryptBASE64(key);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, publicKey);
        return cipher.doFinal(data);
    }

    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    public static void main(String[] args) throws Exception {
        String url = "https://greatwallclubdev.cofco.com/wechat/open/vsroce/scanBottleInnerCode";
     //   String key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyiYJIU0aoiQkug2xPaTpVVLA+WxQryy3k0ggb+gLM3Bptx7kEqc10yVP5nKE3aS9Imt0y7oSFXJFwzdTkfV+s7n9so/O1jj3h3ir5TmHj0zfHqxuVEvRMuHdNqr9Z4Qd0+13B2x0C2DbklgdpcqEXRmnXkcQev5Ui5kPXEX0Jj9vggFwGJplQCqCXRaCts5NvD0TwShop6jAq+oYMXimstNf1mp+toO10n7tZLEzFjhFO5NY0pO9s6BbUzOYAQYSW8xqYdF7FmR9cmR3f/AQ4Swipq2f0KtUYIIz53IAS+L2OlNN7nUiUMsmtdC6EObkJvbx19oa9nXmDdNcT/wQ4wIDAQAB";
        String key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyiYJIU0aoiQkug2xPaTpVVLA+WxQryy3k0ggb+gLM3Bptx7kEqc10yVP5nKE3aS9Imt0y7oSFXJFwzdTkfV+s7n9so/O1jj3h3ir5TmHj0zfHqxuVEvRMuHdNqr9Z4Qd0+13B2x0C2DbklgdpcqEXRmnXkcQev5Ui5kPXEX0Jj9vggFwGJplQCqCXRaCts5NvD0TwShop6jAq+oYMXimstNf1mp+toO10n7tZLEzFjhFO5NY0pO9s6BbUzOYAQYSW8xqYdF7FmR9cmR3f/AQ4Swipq2f0KtUYIIz53IAS+L2OlNN7nUiUMsmtdC6EObkJvbx19oa9nXmDdNcT/wQ4wIDAQAB";


       // String s = "{\"bottleInnerCode\":\"00000001\",\"openId\":\"12345678\"}";
      //  byte[] s1Byte = encryptByPublicKey(s.getBytes(), "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyiYJIU0aoiQkug2xPaTpVVLA+WxQryy3k0ggb+gLM3Bptx7kEqc10yVP5nKE3aS9Imt0y7oSFXJFwzdTkfV+s7n9so/O1jj3h3ir5TmHj0zfHqxuVEvRMuHdNqr9Z4Qd0+13B2x0C2DbklgdpcqEXRmnXkcQev5Ui5kPXEX0Jj9vggFwGJplQCqCXRaCts5NvD0TwShop6jAq+oYMXimstNf1mp+toO10n7tZLEzFjhFO5NY0pO9s6BbUzOYAQYSW8xqYdF7FmR9cmR3f/AQ4Swipq2f0KtUYIIz53IAS+L2OlNN7nUiUMsmtdC6EObkJvbx19oa9nXmDdNcT/wQ4wIDAQAB");
        Map<String, String> map = Maps.newHashMap();
        SyncRequestBody syncRequestBody = new SyncRequestBody();
        syncRequestBody.setBottleInnerCode("HTTP://VJ1.TV/ZW/QF7MM3NB5PFV");
        syncRequestBody.setOpenId("oaFdy5L2fkpu81DQywSsn0aRCGHw");

        String srcStr = JSONObject.toJSONString(syncRequestBody);
        map.put("requestBodyStr",encryptParam(srcStr,key));
        System.out.println("str-----"+srcStr);
        System.out.println("key-----"+key);
        JSONObject jsonObject = HttpClientUtil.httpFormReq(url, map);



        System.out.println(encryptParam(srcStr,key));
        System.out.println(jsonObject);
      //  encryptBASE64(s1Byte);

    }
}
