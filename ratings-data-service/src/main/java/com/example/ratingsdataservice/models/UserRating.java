package com.example.ratingsdataservice.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRating {
    private List<Rating> userRating;
}
