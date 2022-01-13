package com.maximalibri.bookreview.service;

import com.maximalibri.bookreview.dto.FloatWrapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetReviewRatingService {
//    private static final String URL = "http://localhost:8083/book-rating/{isbn}/rating/user/{userId}";
private static final String URL = "http://book-rating:8080/book-rating/{isbn}/rating/user/{userId}";
    private final RestTemplate restTemplate;

    public GetReviewRatingService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Float get(Integer userId, String isbn) {
        ResponseEntity<FloatWrapper> response = restTemplate.getForEntity(URL, FloatWrapper.class, isbn, userId);
        if(response.getStatusCode() == HttpStatus.OK) {
            if(response.getBody() == null) return 0f;
            return response.getBody().getValue();
        } else {
            return 0f;
        }
    }
}
