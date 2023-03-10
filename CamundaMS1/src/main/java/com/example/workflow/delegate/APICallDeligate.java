//package com.example.workflow.delegate;
//
//import org.camunda.bpm.engine.delegate.DelegateExecution;
//import org.camunda.bpm.engine.delegate.JavaDelegate;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//public class APICallDeligate implements JavaDelegate {
//
//
//    @Override
//    public void execute(DelegateExecution execution) throws Exception {
//        String URLString = "http://localhost:8080/engine-rest/task";
//        URL url = new URL(URLString);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("GET");  // configure the url with its request method
//
//        int responseCode = connection.getResponseCode();
//        if(responseCode == HttpURLConnection.HTTP_OK){
//            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String inputLine = null;
//            StringBuffer response = new StringBuffer();
//            while ((inputLine = in.readLine()) != null){
//                response.append(inputLine);
//            }
//            in.close();
//
//            execution.setVariable("apiResponse", response.toString());
//        }else {
//            throw new Exception("Failed to call the API"+responseCode);
//        }
//        System.out.println("API Response");
//
//
//    }
//}
