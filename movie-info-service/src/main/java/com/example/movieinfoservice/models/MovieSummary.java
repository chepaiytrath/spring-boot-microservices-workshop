package com.example.movieinfoservice.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieSummary {
    private String id;
    private String title;
    private String overview;
}
