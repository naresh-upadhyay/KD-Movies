package com.example.naresh.kdmovies.network.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class MovieCastsOfPersonResponse {

    @SerializedName("cast")
    private List<MovieCastOfPerson> casts;
    //crew missing
    @SerializedName("id")
    private Integer id;

    public MovieCastsOfPersonResponse(List<MovieCastOfPerson> casts, Integer id) {
        this.casts = casts;
        this.id = id;
    }

    public List<MovieCastOfPerson> getCasts() {
        return casts;
    }

    public void setCasts(List<MovieCastOfPerson> casts) {
        this.casts = casts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
