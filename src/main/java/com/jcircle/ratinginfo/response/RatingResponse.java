package com.jcircle.ratinginfo.response;

import com.jcircle.ratinginfo.model.Artist;
import com.jcircle.ratinginfo.model.Movie;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class RatingResponse extends BaseResponse {

    private List<Movie> movieList;

    private List<Artist> artistList;

}
