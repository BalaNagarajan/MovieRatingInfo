package com.jcircle.ratinginfo.service;

import com.jcircle.ratinginfo.request.RatingRequest;
import com.jcircle.ratinginfo.response.RatingResponse;

public interface IMovieRatingService {


       RatingResponse getMovieRatingInformation(RatingRequest ratingRequest);

}
