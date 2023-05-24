//package com.vso.camunda.service;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//@Slf4j
//public class TMFService {
//
//    @Value("${tmf641.get.serviceorder}")
//    private String tmf641Endpoint;
//    private RestTemplate restTemplate;
//
//    TMFService() {
//        restTemplate = new RestTemplate();
//    }
//
//    public String callTMFService(String orderId) {
//        Map<String, String> pathVariables = new HashMap<>();
//        pathVariables.put("id", orderId);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        ResponseEntity<String> serviceOrderResponse =
//                restTemplate.exchange(tmf641Endpoint, HttpMethod.GET, entity, String.class, pathVariables);
//        log.info("Output {} for TMFService for OrderId {} ", serviceOrderResponse.getBody(), orderId);
//        return serviceOrderResponse.getBody();
//    }
//}
