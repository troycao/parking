package com.troy.parking.common.utils;


import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Http请求工具类
 *
 * @Created with IntelliJ IDEA.
 * @author: ZhangYuSai
 * @Date: 2018/1/5
 * @Time: 16:56
 * @description: 连接10s超时, 读20s, 写20s ,最大10个线程 保活5分钟
 */
@Slf4j
public class HttpRequest {

    private final static OkHttpClient CLIENT = new OkHttpClient.Builder()
            //忽略SSL证书校验
            .sslSocketFactory(createSSLSocketFactory(), new TrustAllManager())
            //信任所有地址
            .hostnameVerifier(new TrustAllHostnameVerifier())
            //超时设置
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(128, 20, TimeUnit.MINUTES))
            .build();

    /**
     * Post表单请求
     *
     * @param url
     * @param params
     * @return
     */
    public static String post(String url, Map<String, Object> params) throws IOException {

        log.info("Post Url:{}", url);
        FormBody.Builder builder = new FormBody.Builder();
        Response response = null;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = String.valueOf(entry.getValue());
            builder.add(key, value);
            log.info("key:{},value:{}", key, value);
        }
        RequestBody formBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        response = CLIENT.newCall(request).execute();
        String result = getResponseString(response);
        return result;
    }

    /**
     * Post Json请求
     *
     * @param url
     * @param params
     * @return
     */
    public static String post_json(String url, Object params) throws IOException {
        log.info("Post Url:{}", url);
        String requestParam = JsonUtil.toJsonString(params);
        RequestBody formBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), requestParam);
        log.info("contentType:{}", formBody.contentType());
        Response response = null;
        log.info("requestParam:{}", requestParam);
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        response = CLIENT.newCall(request).execute();
        String result = getResponseString(response);
        return result;
    }
    public  static <T> T post_json(String url, Object params,Class<T> clazz) throws IOException{
        String res = post_json(url,params);
        return JsonUtil.parseObject(res,clazz);
    }
    /**
     * Get表单请求
     *
     * @param url
     * @return
     */
    public static String get(String url) throws IOException {
        log.info("Get Url:{}", url);
        Response response = null;
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        response = CLIENT.newCall(request).execute();
        String result = getResponseString(response);
        return result;
    }

    private static String getResponseString(Response response) throws IOException {
        String result = response.body().string();
        log.info("{}\nResponse:{}", response, result);
        if (!response.isSuccessful()) {
            throw new IOException("Http请求失败 " + response);
        }
        response.close();
        return result;
    }

    /**
     * Get表单请求
     *
     * @param url
     * @return
     */
    public static String get(String url, Map<String, Object> params) throws IOException {
        Response response = null;
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        params.entrySet().forEach(param -> {
            String key = param.getKey();
            String value = String.valueOf(param.getValue());
            builder.addQueryParameter(key, value);
            log.info("key:{},value:{}", key, value);
        });
        log.info("Get Url:{}", builder.build().toString());
        Request request = new Request.Builder()
                .url(builder.build().url())
                .get()
                .build();
        response = CLIENT.newCall(request).execute();
        String result = getResponseString(response);
        return result;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(HttpRequest.get("https://baidu.com"));
    }

    /**
     * 默认信任所有的证书
     * TODO 最好加上证书认证，主流App都有自己的证书
     *
     * @return
     */
    private static SSLSocketFactory createSSLSocketFactory() {

        SSLSocketFactory sSLSocketFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllManager()},
                    new SecureRandom());
            sSLSocketFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return sSLSocketFactory;
    }

    private static class TrustAllManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
}
