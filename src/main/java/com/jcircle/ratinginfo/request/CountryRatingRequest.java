package com.jcircle.ratinginfo.request;

import com.jcircle.ratinginfo.model.Movie;
import com.jcircle.ratinginfo.model.MovieRatingFactor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CountryRatingRequest {

    private String countryCode;
    private MovieRatingFactor movieRatingFactor;
}
