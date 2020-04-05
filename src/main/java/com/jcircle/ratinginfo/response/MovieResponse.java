package com.jcircle.ratinginfo.response;

import com.jcircle.ratinginfo.model.Movie;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Setter
@Getter
public class MovieResponse extends BaseResponse {

    private List<Movie> movieList;


}
