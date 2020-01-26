package com.example.moviecatalogservice.services;

import com.example.moviecatalogservice.models.CatalogItem;
import com.example.moviecatalogservice.models.Movie;
import com.example.moviecatalogservice.models.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MovieInfo {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem",
    commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value = "5"),
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value = "50"),
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value = "5000")
    })
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
        return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
    }

    public CatalogItem getFallbackCatalogItem(Rating rating) {
        return new CatalogItem("Movie name not found", "", rating.getRating());
    }
}

/*
Alternate WebClient way:
Movie movie = webClientBuilder.build()
                .get()
                .uri("http://localhost:8082/movies/"+rating.getMovieId())
                .retrieve()
                .bodyToMono(Movie.class)
                .block();*/
