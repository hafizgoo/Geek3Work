package com.hafizgoo.gateway;

import io.netty.handler.codec.http.HttpHeaders;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class OkHttpConnect {

    public  static OkHttpClient client= new OkHttpClient.Builder()
            .callTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .build();

    private static String key;

    private  static String value;

    public  static String doGet(String url, HttpHeaders headers)throws  IOException{
        Request.Builder requestBuilder=new Request.Builder();
        for (Map.Entry<String, String> entry :headers.entries()){
            requestBuilder.header(entry.getKey(),entry.getValue());
        }
        Request request=requestBuilder.url(url).build();
        try(Response response=client.newCall(request).execute()){
            return response.body().string();
        }


    }

    public static void main(String[] args) throws Exception {

        String url = "http://localhost:8088";
       /* String text = OkHttpConnect.doGet(url, headers);
        System.out.println("url: " + url + " ; response: \n" + text);*/
        OkHttpConnect.client = null;
    }
}
