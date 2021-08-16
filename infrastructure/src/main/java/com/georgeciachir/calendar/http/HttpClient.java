package com.georgeciachir.calendar.http;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class HttpClient {

    private final RestTemplate restTemplate;

    public <T> ResponseEntity<T> doGet(URI uri, Class<T> clazz, HttpHeaders headers) {
        return restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers), clazz);
    }
}
