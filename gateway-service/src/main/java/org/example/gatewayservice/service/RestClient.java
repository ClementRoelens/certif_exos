package org.example.gatewayservice.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class RestClient<T> {
    private String apiUrl;
    private RestTemplate restTemplate;
    private HttpHeaders headers;


    public RestClient(String apiUrl){
        this.apiUrl = apiUrl;
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.add("Accept", "*/*");
        headers.add("Content-Type", "application/json");
    }

    public T postRequest(String json, Class<T> responseType){
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<T> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, responseType);

        if (response.hasBody()){
            return response.getBody();
        }
        return null;
    }

    public T getRequest(Class<T> responseType){
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<T> response = restTemplate.exchange(apiUrl, HttpMethod.GET, request, responseType);

        if (response.hasBody()){
            return response.getBody();
        }
        return null;
    }

    public T putRequest(String json, Class<T> responseType){
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<T> response = restTemplate.exchange(apiUrl, HttpMethod.PUT, request, responseType);

        if (response.hasBody()){
            return response.getBody();
        }
        return null;
    }
}
