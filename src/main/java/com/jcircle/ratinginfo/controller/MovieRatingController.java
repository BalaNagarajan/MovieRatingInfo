package com.jcircle.ratinginfo.controller;

import com.jcircle.ratinginfo.request.RatingRequest;
import com.jcircle.ratinginfo.response.BaseResponse;
import com.jcircle.ratinginfo.response.RatingResponse;
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

}
