package com.jcircle.ratinginfo.service.impl;

import com.jcircle.ratinginfo.request.*;
import com.jcircle.ratinginfo.response.*;
import com.jcircle.ratinginfo.service.BaseService;
import com.jcircle.ratinginfo.service.IMovieRatingService;
import com.jcircle.ratinginfo.utils.CommonUtils;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.influx.InfluxDbProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@Service("movieRatingService")
public class MovieRatingServiceImpl extends BaseService implements IMovieRatingService {


    @Autowired
    WebClient.Builder webClientBuilder;

    @Autowired
    private KieSession kieSession;

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
     */
    protected MovieResponse getMovieInfo(List movieIdList) {

        MovieResponse movieResponseObj = null;
        RestTemplate restTemplate = this.getRestTemplate();
        MovieRequest movieRequest = this.populateMovieRequest(movieIdList);
        //To perform  the REST TEMPLATE CALL
        //  movieResponseObj = restTemplate.postForObject("http://localhost:8082/MovieInfo/api/v1/movies/info", movieRequest, MovieResponse.class);
        //Using WebClient - sync call
        movieResponseObj = webClientBuilder.build().post().uri("http://localhost:8082/MovieInfo/api/v1/movies/info").bodyValue(movieRequest).retrieve().bodyToMono(MovieResponse.class).block();

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
        artistResponseObj = restTemplate.postForObject("http://localhost:8081/ArtistInfo/api/v1/artist/info", artistRequest, ArtistResponse.class);
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


    public RatingTypeRequest getRatingsRecommendation(RatingTypeRequest ratingTypeRequest) {

        RatingTypeResponse ratingTypeResponse = null;
        kieSession.insert(ratingTypeRequest);

        kieSession.fireAllRules();

        return ratingTypeRequest;
    }

    public CountryRatingResponse getCountryRatingRecommendation(CountryRatingRequest countryRatingRequest) {
        CountryRatingResponse countryRatingResponse = new CountryRatingResponse();

        kieSession.insert(countryRatingRequest);
        kieSession.setGlobal("countryRatingResponse",countryRatingResponse);
        kieSession.fireAllRules();


        return countryRatingResponse;
    }


}
