package com.jcircle.ratinginfo.service.impl;

import com.jcircle.ratinginfo.request.ArtistRequest;
import com.jcircle.ratinginfo.request.MovieRequest;
import com.jcircle.ratinginfo.request.RatingRequest;
import com.jcircle.ratinginfo.response.ArtistResponse;
import com.jcircle.ratinginfo.response.MovieResponse;
import com.jcircle.ratinginfo.response.RatingResponse;
import com.jcircle.ratinginfo.service.BaseService;
import com.jcircle.ratinginfo.service.IMovieRatingService;
import com.jcircle.ratinginfo.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("movieRatingService")
public class MovieRatingServiceImpl extends BaseService implements IMovieRatingService {

    public MovieRatingServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        super(restTemplateBuilder);
    }

    /**
     *
     */
    @Override
    public RatingResponse getMovieRatingInformation(RatingRequest ratingRequest) {

        RatingResponse ratingResponse = new RatingResponse();

        List<String> movieIdList = null;
        List<String> artistIdList = null;
        MovieResponse movieResponse = null;
        ArtistResponse artistResponse = null;


        if (CommonUtils.isNotEmpty(ratingRequest.getMovieIdList())) {
            movieIdList = ratingRequest.getMovieIdList();
            movieResponse = this.getMovieInfo(movieIdList);
            ratingResponse.setMovieList(movieResponse.getMovieList());
        }
        if (CommonUtils.isNotEmpty(ratingRequest.getArtistIdList())) {
            artistIdList = ratingRequest.getArtistIdList();
            artistResponse = this.getArtistInfo(artistIdList);
            ratingResponse.setArtistList(artistResponse.getArtistList());

        }

        return ratingResponse;
    }

    /**
     *
     * @param movieIdList
     * @return
     */
    protected MovieResponse getMovieInfo(List movieIdList) {

        MovieResponse movieResponseObj = null;
        RestTemplate restTemplate = this.getRestTemplate();
        MovieRequest movieRequest = this.populateMovieRequest(movieIdList);
        movieResponseObj = restTemplate.postForObject("http://localhost:8082/MovieInfo/api/v1/movies/info", movieRequest, MovieResponse.class);
        if (movieResponseObj != null && CommonUtils.isNotEmpty(movieResponseObj.getMovieList())) {

        }

        return movieResponseObj;
    }


    /**
     *
     */
    protected ArtistResponse getArtistInfo(List artistIdList) {

        ArtistResponse artistResponseObj = null;
        RestTemplate restTemplate = this.getRestTemplate();
        ArtistRequest artistRequest = this.populateArtistRequest(artistIdList);
        artistResponseObj = restTemplate.postForObject("http://localhost:8081/ArtistInfo/api/v1/artist/info",artistRequest,ArtistResponse.class);
        if (artistResponseObj != null && CommonUtils.isNotEmpty(artistResponseObj.getArtistList())) {

        }

        return artistResponseObj;
    }

    protected MovieRequest populateMovieRequest(List movieIdList) {
        MovieRequest movieRequest = new MovieRequest();
        movieRequest.setMovieIdList(movieIdList);

        return movieRequest;

    }

    protected ArtistRequest populateArtistRequest(List artistIdList) {
        ArtistRequest artistRequest = new ArtistRequest();
        artistRequest.setArtistIdList(artistIdList);

        return artistRequest;

    }


}
