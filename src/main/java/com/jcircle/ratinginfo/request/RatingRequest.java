package com.jcircle.ratinginfo.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
public class RatingRequest {


    private List<String> movieIdList = null;

    private List<String> artistIdList = null;

}
