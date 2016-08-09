package com.example.tacademy.samplenetwork.manager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016-08-05.
 */
public abstract class NetworkRequest<T> implements Runnable {
    T result;
    NetworkManager.OnResultListener<T> listener;

    public abstract URL getURL() throws MalformedURLException;

    public void setOnResultListener(NetworkManager.OnResultListener<T> listener) {
        this.listener = listener;
    }

    public void setNetworkConfig(HttpURLConnection conn) {
        conn.setConnectTimeout(30000);
        conn.setReadTimeout(10000);
    }

    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String DELETE = "DELETE";

    protected String getRequestMethod() {
        return GET;
    }

    protected void setRequestProperty(HttpURLConnection conn) {

    }

    protected void write(OutputStream out) {
    }

    protected void process(InputStream is) {
        T result = parse(is);
        sendSuccess(result);
    }

    private void sendSuccess(T result) {
        this.result = result;
        NetworkManager.getInstance().sendSuccess(this);
    }

    void sendSuccess() {
        if (listener != null) {
            listener.onSuccess(this, result);
        }
    }


    abstract protected T parse(InputStream is);

    @Override
    public void run() {
        try {
            URL url = getURL();
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            setNetworkConfig(conn);
            String method = getRequestMethod();

            int code = conn.getResponseCode();
            if (code >= 200 && code < 300) {
                InputStream is = conn.getInputStream();
                process(is);
                return;
            } else {
                sendError(code, conn.getResponseMessage());
                return;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sendError(-1, "exception");
    }

    //            if (method.equals(POST) || method.equals(PUT)) {
//                conn.setDoOutput(true);
//            }
//            setRequestProperty(conn);
//
//            if (conn.getDoOutput()) {
//                write(conn.getOutputStream());
//            }



    int code;
    String errorMessage;
    protected void sendError(int code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
        NetworkManager.getInstance().sendFail(this);
    }

    void sendFail() {
        if (listener != null) {
            listener.onFail(this, code, errorMessage);
        }
    }
}
