package com.jcircle.ratinginfo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Artist {

    private String artistId;
    private String artistFirstName;
    private String artistLastName;
    private String artistField;
    private ArtistDetail artistDetail;


}
