package com.example.ratingsdataservice.resources;

import com.example.ratingsdataservice.models.Rating;
import com.example.ratingsdataservice.models.UserRating;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
@Slf4j
public class RatingsResource {

    @Autowired
    Environment environment;

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new Rating(movieId, 5);
    }

    @RequestMapping("users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId){
        String serverPort = environment.getProperty("local.server.port");
        log.info("ENTERED RatingsResource ON PORT : "+serverPort);

        List<Rating> ratings = Arrays.asList(
                new Rating("100", 5),
                new Rating("101", 1)
        );
        UserRating userRating = new UserRating();
        userRating.setUserId(userId);
        userRating.setUserRating(ratings);
        return userRating;
    }
}
