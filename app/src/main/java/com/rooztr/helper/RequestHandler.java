package com.rooztr.helper;

import android.util.Log;

import com.google.gson.Gson;
import com.rooztr.exceptions.InvalidInputException;
import com.rooztr.exceptions.UnauthenticatedException;
import com.rooztr.exceptions.UserNotExistException;
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
    public static void register(String phone) throws Exception{
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
            int stats = connection.getResponseCode();
            Log.d("response code", String.valueOf(stats));
            if(stats == 201) {
            }else if(stats >=500 ){
                throw new Exception();
            }else{
                throw new InvalidInputException();
            }
        }catch(NullPointerException e) {
            //phone is null
            throw new InvalidInputException();
        }catch(Exception e){
            throw e;
        }
    }

    //login
    public static String login(String phone, String code) throws Exception{
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
            int stats = connection.getResponseCode();
            Log.d("response code", String.valueOf(stats));
            if(stats == 200) {
                return connection.getHeaderField("token");
            }else if(stats >=500 ){
                throw new Exception();
            }else{
                throw new InvalidInputException();
            }
        }catch(NullPointerException e) {
            //phone or code is null
            throw new InvalidInputException();
        }catch(Exception e){
            throw e;
        }
    }

    //validate
    public static boolean validate(String phone, String token) throws Exception{
        try{
            if(phone == null || token == null) throw new NullPointerException();
            URL url = new URL(baseUrl + "login");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("phone", phone);
            connection.setRequestProperty("token", token);
            int stats = connection.getResponseCode();
            Log.d("response code", String.valueOf(stats));
            if(stats == 200){
                return true;
            }else if(stats >=500 ){
                throw new Exception();
            }else{
                return false;
            }
        }catch(NullPointerException e) {
            //phone or code is null
            throw new InvalidInputException();
        }catch(Exception e){
            throw e;
        }
    }

    //request
    private static CallRequest request(String phone, String token, String toPhone, long start, long end, String message) throws Exception{
        try{
            if(phone == null || token == null) throw new NullPointerException();
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
                    URLEncoder.encode(String.valueOf(start), charset),
                    URLEncoder.encode(String.valueOf(end), charset),
                    URLEncoder.encode(message, charset));
            try {
                OutputStream output = connection.getOutputStream();
                output.write(query.getBytes(charset));
            }catch(Exception e){
                throw e;
            }
            int stats = connection.getResponseCode();
            Log.d("response code", String.valueOf(stats));
            InputStream response = connection.getInputStream();
            if(stats == 200) {
                return new Gson().fromJson(readStream(response), CallRequest.class);
            }else if(stats == 204) {
                throw new UserNotExistException();
            }else if(stats == 401){
                throw new UnauthenticatedException();
            }else if(stats >=500 ){
                throw new Exception();
            }else{
                throw new InvalidInputException();
            }
        }catch(NullPointerException e) {
            //toPhone, message is null
            throw new InvalidInputException();
        }catch(Exception e){
            throw e;
        }
    }

    //requestlist
    public static List<CallRequest> requestlist(String phone, String token, String start, String end) throws Exception{
        try{
            if(phone == null || token == null) throw new NullPointerException();
            if(start == null) start = "";
            if(end == null) end = "";
            URL url = new URL(baseUrl + "requestlist?from="+start+"&end="+end);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("phone", phone);
            connection.setRequestProperty("token", token);
            int stats = connection.getResponseCode();
            Log.d("response code", String.valueOf(stats));
            if(stats == 200) {
                InputStream response = connection.getInputStream();
                return Arrays.asList(new Gson().fromJson(readStream(response), CallRequest[].class));
            }else if(stats == 401){
                throw new UnauthenticatedException();
            }else if(stats >=500 ){
                throw new Exception();
            }else{
                throw new InvalidInputException();
            }
        }catch(NullPointerException e) {
            //phone or token is null
            throw new InvalidInputException();
        }catch(Exception e){
            throw e;
        }
    }

    //waitlist
    public static List<CallRequest> waitlist(String phone, String token, String start, String end) throws Exception{
        try{
            if(phone == null || token == null) throw new NullPointerException();
            if(start == null) start = "";
            if(end == null) end = "";
            URL url = new URL(baseUrl + "waitlist?from="+start+"&end="+end);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("phone", phone);
            connection.setRequestProperty("token", token);
            int stats = connection.getResponseCode();
            Log.d("response code", String.valueOf(stats));
            if(stats == 200) {
                InputStream response = connection.getInputStream();
                return Arrays.asList(new Gson().fromJson(readStream(response), CallRequest[].class));
            }else if(stats == 401){
                throw new UnauthenticatedException();
            }else if(stats >=500 ){
                throw new Exception();
            }else{
                throw new InvalidInputException();
            }
        }catch(NullPointerException e) {
            //phone or token is null
            throw new InvalidInputException();
        }catch(Exception e){
            throw e;
        }
    }

    //withdraw
    public static void withdraw(String phone, String token, String id) throws Exception{
        try {
            if(phone == null || token == null) throw new NullPointerException();
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
            int stats = connection.getResponseCode();
            Log.d("response code", String.valueOf(stats));
            if(stats == 204) {
                return;
            }else if(stats == 401){
                throw new UnauthenticatedException();
            }else if(stats >=500 ){
                throw new Exception();
            }else{
                throw new InvalidInputException();
            }
        }catch(NullPointerException e) {
            //id is null
            throw new InvalidInputException();
        }catch(Exception e){
            throw e;
        }
    }

    //finish
    public static void finish(String phone, String token, String id) throws Exception{
        try {
            if(phone == null || token == null) throw new NullPointerException();
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
            int stats = connection.getResponseCode();
            Log.d("response code", String.valueOf(stats));
            if(stats == 204) {
                return;
            }else if(stats == 401){
                throw new UnauthenticatedException();
            }else if(stats >=500 ){
                throw new Exception();
            }else{
                throw new InvalidInputException();
            }
        }catch(NullPointerException e) {
            //id is null
            throw new InvalidInputException();
        }catch(Exception e){
            throw e;
        }
    }

    //refuse
    public static void refuse(String phone, String token, String id) throws Exception{
        try {
            if(phone == null || token == null) throw new NullPointerException();
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
            int stats = connection.getResponseCode();
            Log.d("response code", String.valueOf(stats));
            if(stats == 204) {
                return;
            }else if(stats == 401){
                throw new UnauthenticatedException();
            }else if(stats >=500 ){
                throw new Exception();
            }else{
                throw new InvalidInputException();
            }
        }catch(NullPointerException e) {
            //id is null
            throw new InvalidInputException();
        }catch(Exception e){
            throw e;
        }
    }

    //requesterdelete
    public static void requesterdelete(String phone, String token, String id) throws Exception{
        try {
            if(phone == null || token == null) throw new NullPointerException();
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
            int stats = connection.getResponseCode();
            Log.d("response code", String.valueOf(stats));
            if(stats == 204) {
                return;
            }else if(stats == 401){
                throw new UnauthenticatedException();
            }else if(stats >=500 ){
                throw new Exception();
            }else{
                throw new InvalidInputException();
            }
        }catch(NullPointerException e) {
            //id is null
            throw new InvalidInputException();
        }catch(Exception e){
            throw e;
        }
    }

    //requesteedelete
    public static void requesteedelete(String phone, String token, String id) throws Exception{
        try {
            if(phone == null || token == null) throw new NullPointerException();
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
            int stats = connection.getResponseCode();
            Log.d("response code", String.valueOf(stats));
            if(stats == 204) {
                return;
            }else if(stats == 401){
                throw new UnauthenticatedException();
            }else if(stats >=500 ){
                throw new Exception();
            }else{
                throw new InvalidInputException();
            }
        }catch(NullPointerException e) {
            //id is null
            throw new InvalidInputException();
        }catch(Exception e){
            throw e;
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