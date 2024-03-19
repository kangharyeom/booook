package com.example.bookbackend.common.utill;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

public class RestUtil {

//    public static <T> T get(String url, Class<T> responseType) {
//        RestClient restClient = RestClient.create();
//        String result = restClient.get()
//                .uri(url)
//                .retrieve()
//                .body(String.class);
//        return restTemplate.getForObject(url, responseType);
//    }
//
//    public static <T, R> R post(String url, T request, Class<R> responseType) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<T> entity = new HttpEntity<>(request, headers);
//        return restTemplate.postForObject(url, entity, responseType);
//    }
//
//    public static <T> void put(String url, T request) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<T> entity = new HttpEntity<>(request, headers);
//        restTemplate.put(url, entity);
//    }
//
//    public static void delete(String url) {
//        restTemplate.delete(url);
//    }
}
