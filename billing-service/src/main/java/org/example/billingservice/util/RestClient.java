package org.example.billingservice.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestClient<T> {
    private String urlApi;
    private RestTemplate restTemplate;
    private HttpHeaders headers;

    public RestClient(String urlApi) {
        this.urlApi = urlApi;
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.add("Accept", "*/*");
        headers.add("Content-Type", "application/json");
    }

    public T getRequest(Class<T> responseType) {
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<T> response = restTemplate.exchange(urlApi, HttpMethod.GET, request, responseType);

        if (response.hasBody()) {
            return response.getBody();
        }
        return null;
    }
}
