package com.google.chineserestaurant.httpHelper;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class HttpAdapter {

    public String httpGet(String url) {
        String strResult = "";
        HttpGet get = new HttpGet(url);

        try {
            HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, 10000);
            HttpClient httpClient = new DefaultHttpClient(httpParameters);

            HttpResponse httpResponse = null;
            httpResponse = httpClient.execute(get);

            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                strResult = EntityUtils.toString(httpResponse.getEntity());
            }
        } catch (Exception e) {
            return null;
        }
        return strResult;
    }
    
    public String convertURL(String str) {

        String url = null;
        try {
            url = new String(str.trim().replace(" ", "%20"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }
}
