package com.jlss.placelive.tracker.client;

import com.jlss.placelive.tracker.entity.Tracker;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class SearchServiceTrackerClient {

    private final RestTemplate restTemplate;

    public SearchServiceTrackerClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    /**
     * Sending tracker logs too in the search service so that fast user presence can be accessed
     * indexing the trackers, updates, and deletions (we will also apply to delete the tracker
     * log from search servive if the user is not active. and once active we will send it from
     * the tracker this logic is quite tough but we have to to do so)
     * **/
    // TODO use requestEntity to get the response and verify that the elastic searhc is indexed or not

    public void postTrackerToSearchService(Tracker tracker){
        String url = "http://localhost:8085/placelive-search-service/v1/api/elasticSearch/tracker";
        restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(tracker),
                new ParameterizedTypeReference<Void>() {
                }
        );
    }

    public void putTrackerToSearchService(Long trackerId,Tracker tracker){
        String url = "http://localhost:8085/placelive-search-service/v1/api/elasticSearch/tracker/{trackerId}";
        restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(tracker),
                new ParameterizedTypeReference<Void>() {
                },
                trackerId
        );
    }

    public void deleteTrackerToSearchService(Long trackerId){
        String url = "Http://localhost:8085/placelive-search-service/v1/api/elasticSearch/tracker/{trackerId}";
        restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<Void>() {
                },
                trackerId
        );
    }
}

// TODO i got a solution so i can make the urls in a apigateway from a request data and url will come so waht we will do is ki we will set the urls from the api gateways so that no need to set them again and again manually. individually,
// or you can say it as a url gatway.