package com.example.multithreading_all.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * module: 应用模块名称<br/>
 * <p>
 * description: 描述<br/>
 *
 * @author XiaoDong.Yang
 * @date: 2023/12/11 18:17
 */
@Slf4j
public class HttpClientUtil {
    /** The log. */
    /**
     * <pre>
     * Input stream2 string.
     * </pre>
     *
     * @author 孙顺博 2015-1-16
     * @param is
     *            the is
     * @return the string
     */
    public static String inputStream2String(InputStream is) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        try {
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
                baos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toString();
    }

    /**
     * http请求.
     *
     * @author sunshunbo  2017-8-11
     * @param uri the uri
     * @param parameterStr the parameter str
     * @param request the request
     * @return the JSON object
     */
    public static JSONObject httpFormReq(String uri, Map<String, String> paramMap) {
        JSONObject jsonObject = null;
        CloseableHttpClient client = null;
        HttpPost post = null;
        try {
            client = HttpClients.createDefault();
            // client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
            // 3000);// 连接时间
            // client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
            // 3000);// 数据传输时间
            post = new HttpPost(uri);
            post.addHeader("Content-Type", "application/x-www-form-urlencoded");

            List<NameValuePair> paramLst = new ArrayList<>();
            for (String key : paramMap.keySet()) {
                paramLst.add(new BasicNameValuePair(key, paramMap.get(key)));
            }
            post.setEntity(new UrlEncodedFormEntity(paramLst, "UTF-8"));

            RequestConfig requestConfig=RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();
            post.setConfig(requestConfig);

            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            String resBody = IOUtils.toString(entity.getContent(), "UTF-8");
            if (response.getStatusLine().getStatusCode() == 200 && StringUtils.isNotBlank(resBody)) {
                try {
                    jsonObject = JSON.parseObject(resBody);
                } catch (Exception e) {
                    jsonObject=new JSONObject();
                    jsonObject.put("otherString", resBody);
                }

            }else{
                log.error("http请求失败（目前获取token调用），返回resBody=" + resBody);
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != post) {
                    post.abort();// 终止本次连接
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (null != client) {
//					client.getConnectionManager().shutdown();// 关闭
                    client.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }
    /**
     * http请求.
     *
     * @author sunshunbo  2017-8-11
     * @param uri the uri
     * @param parameterStr the parameter str
     * @param request the request
     * @return the JSON object
     */
    public static JSONObject httpReq(String uri, String parameterStr, HttpServletRequest request) {
        JSONObject jsonObject = null;
        CloseableHttpClient client = null;
        HttpPost post = null;
        try {
            client = HttpClients.createDefault();
            // client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
            // 3000);// 连接时间
            // client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
            // 3000);// 数据传输时间
            post = new HttpPost(uri);
            post.addHeader("Content-Type", "application/json");

            // post.addHeader("Content-Type", "image/jpeg");
            if (StringUtils.isNotBlank(parameterStr)) {
                byte[] data = parameterStr.getBytes("UTF-8");
                post.setEntity(new ByteArrayEntity(data));
            }
            if (request != null) {
                post.setEntity(new ByteArrayEntity(IOUtils.toByteArray(request.getInputStream())));
            }

            RequestConfig requestConfig=RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();
            post.setConfig(requestConfig);

            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            String resBody = IOUtils.toString(entity.getContent(), "UTF-8");
            if (response.getStatusLine().getStatusCode() == 200 && StringUtils.isNotBlank(resBody)) {
                try {
                    jsonObject = JSON.parseObject(resBody);
                } catch (Exception e) {
                    jsonObject=new JSONObject();
                    jsonObject.put("otherString", resBody);
                }

            }else{
                log.error("http请求失败（目前获取token调用），返回resBody=" + resBody);
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != post) {
                    post.abort();// 终止本次连接
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (null != client) {
//					client.getConnectionManager().shutdown();// 关闭
                    client.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

    /**
     * 用户风险等级 -- 响应超时时间较短并对超时异常有特殊处理 -- 慎用
     * @param uri
     * @param parameterStr
     * @param request
     * @return
     */
    public static JSONObject httpReqRapid(String uri, String parameterStr, HttpServletRequest request) {
        JSONObject jsonObject = null;
        CloseableHttpClient client = null;
        HttpPost post = null;
        try {
            client = HttpClients.createDefault();
            post = new HttpPost(uri);
            post.addHeader("Content-Type", "application/json");

            if (StringUtils.isNotBlank(parameterStr)) {
                byte[] data = parameterStr.getBytes("UTF-8");
                post.setEntity(new ByteArrayEntity(data));
            }
            if (request != null) {
                post.setEntity(new ByteArrayEntity(IOUtils.toByteArray(request.getInputStream())));
            }

            RequestConfig requestConfig=RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(3000).build();
            post.setConfig(requestConfig);

            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            String resBody = IOUtils.toString(entity.getContent(), "UTF-8");
            if (response.getStatusLine().getStatusCode() == 200 && StringUtils.isNotBlank(resBody)) {
                try {
                    jsonObject = JSON.parseObject(resBody);
                } catch (Exception e) {
                    jsonObject=new JSONObject();
                    jsonObject.put("otherString", resBody);
                }

            }else{
                log.error("http请求失败（目前获取token调用），返回resBody=" + resBody);
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            jsonObject=new JSONObject();
            jsonObject.put("timeout", "noResBody");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != post) {
                    post.abort();// 终止本次连接
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (null != client) {
//					client.getConnectionManager().shutdown();// 关闭
                    client.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

    /**
     * 处理http请求.
     *
     * @author sunshunbo  2017-8-11
     * @param uri the uri
     * @param parameterStr the parameter str
     * @param request the request
     * @return the JSON object
     */
    @SuppressWarnings({ "resource" })
    public static JSONObject handerHttpReq(String uri, String parameterStr,HttpServletRequest request) {
        JSONObject jsonObject = null;
        try {
            HttpClient client = new SSLClient();
            HttpPost post = new HttpPost(uri);
            post.addHeader("Content-Type", "application/json");
            if (StringUtils.isNotBlank(parameterStr)) {
                if (parameterStr.contains("media")){
                    post.addHeader("Content-Type", "multipart/form-data");
                }
                byte[] data = parameterStr.getBytes("UTF-8");
                post.setEntity(new ByteArrayEntity(data));
            }
            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            String resBody = IOUtils.toString(entity.getContent(), "UTF-8");
            if (response.getStatusLine().getStatusCode() == 200 && StringUtils.isNotBlank(resBody)) {
                jsonObject = JSON.parseObject(resBody);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("互动***********请求结果"+jsonObject.toJSONString());
        return jsonObject;
    }

    /**
     *  恶意图片过滤
     * @param multipartFile
     * @return
     */
    public static Boolean checkPic(MultipartFile multipartFile, String accessToken) {
        try {

            CloseableHttpClient httpclient = HttpClients.createDefault();

            CloseableHttpResponse response = null;

            HttpPost request = new HttpPost("https://api.weixin.qq.com/wxa/img_sec_check?access_token=" + accessToken);
            request.addHeader("Content-Type", "application/octet-stream");



            InputStream inputStream = multipartFile.getInputStream();

            byte[] byt = new byte[inputStream.available()];
            inputStream.read(byt);
            request.setEntity(new ByteArrayEntity(byt, ContentType.create("image/jpg")));


            response = httpclient.execute(request);
            HttpEntity httpEntity = response.getEntity();
            String result = EntityUtils.toString(httpEntity, "UTF-8");// 转成string
            JSONObject jso = JSONObject.parseObject(result);
            System.out.println(jso + "-------------验证效果");


            Object errcode = jso.get("errcode");
            int errCode = (int) errcode;
            if (errCode == 0) {
                return true;
            } else if (errCode == 87014) {
                System.out.println("图片内容违规-----------");
                return false;
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("----------------调用腾讯内容过滤系统出错------------------");
            return true;
        }
    }
    /**
     * Get方式请求
     * @param url
     * @return
     */
    @SuppressWarnings({ "resource", "deprecation" })
    public static String httpGetString(String url){
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(url);

            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            String resBody = IOUtils.toString(entity.getContent(), "UTF-8");
            if (response.getStatusLine().getStatusCode() == 200 && StringUtils.isNotBlank(resBody)) {
                return resBody;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("deprecation")
    public static class SSLClient extends DefaultHttpClient {
        public SSLClient() throws Exception{
            super();
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }
                @Override
                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            ctx.init(null, new TrustManager[]{tm}, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = this.getConnectionManager();
            SchemeRegistry sr = ccm.getSchemeRegistry();
            sr.register(new Scheme("https", 443, ssf));
        }
    }
}
