package com.jcircle.ratinginfo.controller;

import com.jcircle.ratinginfo.request.CountryRatingRequest;
import com.jcircle.ratinginfo.request.RatingRequest;
import com.jcircle.ratinginfo.request.RatingTypeRequest;
import com.jcircle.ratinginfo.response.BaseResponse;
import com.jcircle.ratinginfo.response.CountryRatingResponse;
import com.jcircle.ratinginfo.response.RatingResponse;
import com.jcircle.ratinginfo.response.RatingTypeResponse;
import com.jcircle.ratinginfo.service.IMovieRatingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class MovieRatingController {

    @Autowired
    IMovieRatingService movieRatingService;


    @CrossOrigin
    @PostMapping(value = "/v1/ratings/info")
    @ApiOperation(value = "Get Movie Rating Information",
        notes = "Choose the appropriate request parameters.")
    public ResponseEntity<BaseResponse> getMovieInfo(@ApiParam(value = " {\n"
        + "  \"movieIdList\": \"[1]\"\n"
        + "  \"artistIdList\": \"[100]\"\n"
        + "}") @RequestBody RatingRequest ratingRequest) {

        ResponseEntity<BaseResponse> responseEntityObj = null;

        RatingResponse ratingResponseObj = movieRatingService.getMovieRatingInformation(ratingRequest);

        responseEntityObj = new ResponseEntity(ratingResponseObj, HttpStatus.OK);
        return responseEntityObj;

    }


    @CrossOrigin
    @PostMapping(value = "/v1/movie/ratings")
    @ApiOperation(value = "Get Ratings Recommendation",
        notes = "What is your age.")
    public ResponseEntity<RatingTypeRequest> getRatingsRecommendation(@ApiParam(value = " {\n"
        + "  \"age\": \"1 or 8 or 15\"\n"
        + "}") @RequestBody RatingTypeRequest ratingTypeRequest) {

        ResponseEntity<RatingTypeRequest> responseEntityObj = null;

        RatingTypeRequest ratingTypeRequestObj = movieRatingService.getRatingsRecommendation(ratingTypeRequest);

        responseEntityObj = new ResponseEntity(ratingTypeRequestObj, HttpStatus.OK);
        return responseEntityObj;

    }

    @CrossOrigin
    @PostMapping(value = "/v1/country/movie/rating")
    @ApiOperation(value = "Get Country Ratings Recommendation",
        notes = "Enter Country Code.")
    public ResponseEntity<CountryRatingResponse> getCountryRatingRecommendation(@ApiParam(value = " {\n"
        + "  \"countryCode\": \"USA or JPN or IND or AUS\"\n"
        + "  \"movieRatingFactor\": \"{ 'isViolent' : 'Y','violenceIntensity' : '4', 'isFrighteningImages' , 'Y', 'languageUsage' : 'Y' }\"\n"
        + "}") @RequestBody CountryRatingRequest countryRatingRequest) {

        ResponseEntity<CountryRatingResponse> responseEntityObj = null;

        CountryRatingResponse countryRatingResponse = movieRatingService.getCountryRatingRecommendation(countryRatingRequest);

        responseEntityObj = new ResponseEntity(countryRatingResponse, HttpStatus.OK);
        return responseEntityObj;

    }



}
