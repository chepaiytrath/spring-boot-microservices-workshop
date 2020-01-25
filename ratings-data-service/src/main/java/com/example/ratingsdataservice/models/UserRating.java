package com.example.ratingsdataservice.models;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRating {
    private String userId;
    private List<Rating> userRating;
}
