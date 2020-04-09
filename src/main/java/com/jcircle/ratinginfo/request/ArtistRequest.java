package com.jcircle.ratinginfo.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ArtistRequest {

    private List<String> artistIdList = null;
    private String artistFirstName = null;
    private String artistLastName = null;

}
