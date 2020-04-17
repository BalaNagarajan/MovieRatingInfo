package com.jcircle.ratinginfo.response;

import com.jcircle.ratinginfo.model.Movie;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class RatingTypeResponse {

    private List<String> category;

}
