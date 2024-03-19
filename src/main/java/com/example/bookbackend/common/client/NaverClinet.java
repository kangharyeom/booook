package com.example.bookbackend.common.client;

import org.springframework.web.client.RestClient;
public class NaverClinet {
    private final static RestClient restClient = RestClient.create();
}
