package com.jcircle.ratinginfo.service;

import com.jcircle.ratinginfo.request.RatingRequest;
import com.jcircle.ratinginfo.request.RatingTypeRequest;
import com.jcircle.ratinginfo.response.RatingResponse;
import com.jcircle.ratinginfo.response.RatingTypeResponse;

public interface IMovieRatingService {


       RatingResponse getMovieRatingInformation(RatingRequest ratingRequest);


       RatingTypeRequest getRatingsRecommendation(RatingTypeRequest ratingTypeRequest);

}
