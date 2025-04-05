package com.jlss.placelive.tracker.client;



import com.fasterxml.jackson.databind.ObjectMapper;

import com.jlss.placelive.commonlib.dto.ResponseDto;
import com.jlss.placelive.tracker.dto.NotificationDto;
import com.jlss.placelive.tracker.entity.Tracker;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NotificationServiceClient {

    private final RestTemplate restTemplate;

    public NotificationServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<ResponseDto<NotificationDto>> getNotificationByTracker(Tracker tracker) {
        String url =   "http://localhost:8082/placelive-geofencing/v1/api/message/victim";
        ResponseEntity<ResponseDto<NotificationDto>> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new org.springframework.http.HttpEntity<>(tracker),
                new ParameterizedTypeReference<ResponseDto<NotificationDto>>() {}
        );
        return response;

    }

}
