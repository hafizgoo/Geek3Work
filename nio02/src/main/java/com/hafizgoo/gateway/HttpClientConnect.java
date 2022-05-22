package com.hafizgoo.gateway;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientConnect {

    public  static CloseableHttpClient httpclient = HttpClients.createDefault();


    public  static  String getUrl(String url) throws IOException{
        HttpGet httpget = new HttpGet(url);
        ResponseHandler<String> responseHandler = response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
        String responseBody = httpclient.execute(httpget, responseHandler);
        return responseBody;
    }

    public static void main(String[] args) throws IOException {
        String url = "http://locahost:8088";
        String text = HttpClientConnect.getUrl(url);
        System.out.println("url: " + url + " ; response: \n" + text);

    }
}
