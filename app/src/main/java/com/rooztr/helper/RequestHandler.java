package com.rooztr.helper;

import android.util.Log;

import com.google.gson.Gson;
import com.rooztr.model.CallRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RequestHandler{
    private static final String baseUrl = "http://10.0.2.2:8080/Rooztr/api/v1/rest/"; //for simulator
    private final static String charset = "UTF-8";

    //register
    public static int register(String phone){
        try {
            URL url = new URL(baseUrl + "initiate");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true); // trigger POST.
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
            String query = String.format("phone=%s",
                    URLEncoder.encode(phone, charset));
            try {
                OutputStream output = connection.getOutputStream();
                output.write(query.getBytes(charset));
            }catch(Exception e){
                throw e;
            }
            Log.d("response code", String.valueOf(connection.getResponseCode()));
            return 0;
        }catch(Exception e) {
            Log.d("exception", e.getMessage());
            return 1;
        }
    }

    //login
    public static String login(String phone, String code){
        String token = null;
        try{
            URL url = new URL(baseUrl + "login");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true); // trigger POST.
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
            String query = String.format("phone=%s&code=%s",
                    URLEncoder.encode(phone, charset),
                    URLEncoder.encode(code, charset));
            try {
                OutputStream output = connection.getOutputStream();
                output.write(query.getBytes(charset));
            }catch(Exception e){
                throw e;
            }
            Log.d("response code", String.valueOf(connection.getResponseCode()));
            token = connection.getHeaderField("token");
        }catch(Exception e){
            Log.d("exception", e.getMessage());
        }
        return token;
    }

    //request
    public static CallRequest request(String phone, String token, String toPhone, String start, String end, String message){
        CallRequest cr = null;
        try{
            URL url = new URL(baseUrl + "request");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true); // trigger POST.
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
            connection.setRequestProperty("phone", phone);
            connection.setRequestProperty("token", token);
            String query = String.format("toPhone=%s&start=%s&end=%s&message=%s",
                    URLEncoder.encode(toPhone, charset),
                    URLEncoder.encode(start, charset),
                    URLEncoder.encode(end, charset),
                    URLEncoder.encode(message, charset));
            try {
                OutputStream output = connection.getOutputStream();
                output.write(query.getBytes(charset));
            }catch(Exception e){
                throw e;
            }
            Log.d("response code", String.valueOf(connection.getResponseCode()));
            InputStream response = connection.getInputStream();
            cr = new Gson().fromJson(readStream(response), CallRequest.class);
        }catch(Exception e){
            e.printStackTrace();
        }
        return cr;
    }

    //requestlist
    public static List<CallRequest> requestlist(String phone, String token, String start, String end){
        List<CallRequest> crlist = new ArrayList<CallRequest>();
        try{
            URL url = new URL(baseUrl + "requestlist?from="+start+"&end="+end);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("phone", phone);
            connection.setRequestProperty("token", token);
            Log.d("response code", String.valueOf(connection.getResponseCode()));
            InputStream response = connection.getInputStream();
            crlist = Arrays.asList(new Gson().fromJson(readStream(response), CallRequest[].class));
        }catch(Exception e){
            e.printStackTrace();
        }
        return crlist;
    }

    //waitlist
    public static List<CallRequest> waitlist(String phone, String token, String start, String end){
        List<CallRequest> crlist = new ArrayList<CallRequest>();
        try{
            URL url = new URL(baseUrl + "waitlist?from="+start+"&end="+end);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("phone", phone);
            connection.setRequestProperty("token", token);
            Log.d("response code", String.valueOf(connection.getResponseCode()));
            InputStream response = connection.getInputStream();
            crlist = Arrays.asList(new Gson().fromJson(readStream(response), CallRequest[].class));
        }catch(Exception e){
            e.printStackTrace();
        }
        return crlist;
    }

    //withdraw
    public static int withdraw(String phone, String token, String id){
        try {
            URL url = new URL(baseUrl + "withdraw");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true); // trigger POST.
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
            connection.setRequestProperty("phone", phone);
            connection.setRequestProperty("token", token);
            String query = String.format("id=%s",
                    URLEncoder.encode(id, charset));
            try {
                OutputStream output = connection.getOutputStream();
                output.write(query.getBytes(charset));
            }catch(Exception e){
                throw e;
            }
            Log.d("response code", String.valueOf(connection.getResponseCode()));
            return 0;
        }catch(Exception e) {
            Log.d("exception", e.getMessage());
            return 1;
        }
    }

    //finish
    public static int finish(String phone, String token, String id){
        try {
            URL url = new URL(baseUrl + "finish");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true); // trigger POST.
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
            connection.setRequestProperty("phone", phone);
            connection.setRequestProperty("token", token);
            String query = String.format("id=%s",
                    URLEncoder.encode(id, charset));
            try {
                OutputStream output = connection.getOutputStream();
                output.write(query.getBytes(charset));
            }catch(Exception e){
                throw e;
            }
            Log.d("response code", String.valueOf(connection.getResponseCode()));
            return 0;
        }catch(Exception e) {
            Log.d("exception", e.getMessage());
            return 1;
        }
    }

    //refuse
    public static int refuse(String phone, String token, String id){
        try {
            URL url = new URL(baseUrl + "refuse");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true); // trigger POST.
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
            connection.setRequestProperty("phone", phone);
            connection.setRequestProperty("token", token);
            String query = String.format("id=%s",
                    URLEncoder.encode(id, charset));
            try {
                OutputStream output = connection.getOutputStream();
                output.write(query.getBytes(charset));
            }catch(Exception e){
                throw e;
            }
            Log.d("response code", String.valueOf(connection.getResponseCode()));
            return 0;
        }catch(Exception e) {
            Log.d("exception", e.getMessage());
            return 1;
        }
    }

    //requesterdelete
    public static int requesterdelete(String phone, String token, String id){
        try {
            URL url = new URL(baseUrl + "requesterdelete");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true); // trigger POST.
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
            connection.setRequestProperty("phone", phone);
            connection.setRequestProperty("token", token);
            String query = String.format("id=%s",
                    URLEncoder.encode(id, charset));
            try {
                OutputStream output = connection.getOutputStream();
                output.write(query.getBytes(charset));
            }catch(Exception e){
                throw e;
            }
            Log.d("response code", String.valueOf(connection.getResponseCode()));
            return 0;
        }catch(Exception e) {
            Log.d("exception", e.getMessage());
            return 1;
        }
    }

    //requesteedelete
    public static int requesteedelete(String phone, String token, String id){
        try {
            URL url = new URL(baseUrl + "requesteedelete");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true); // trigger POST.
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
            connection.setRequestProperty("phone", phone);
            connection.setRequestProperty("token", token);
            String query = String.format("id=%s",
                    URLEncoder.encode(id, charset));
            try {
                OutputStream output = connection.getOutputStream();
                output.write(query.getBytes(charset));
            }catch(Exception e){
                throw e;
            }
            Log.d("response code", String.valueOf(connection.getResponseCode()));
            return 0;
        }catch(Exception e) {
            Log.d("exception", e.getMessage());
            return 1;
        }
    }

    private static String readStream(InputStream in) {
        StringBuilder sb = null;
        try {
            sb = new StringBuilder();
            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            for (String line = r.readLine(); line != null; line = r.readLine()) {
                sb.append(line);
            }
            in.close();
            r.close();
        } catch (IOException e) {

        }
        return sb.toString();
    }
}