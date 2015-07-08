package com.southasia.boxoffice;

/**
 * Created by karthik on 5/3/2015.
 */
public class Trailer {

    private String _id;
    private String MovieID;
    private String VideoID;
    private String ShortDescription;
    private String LongDescription;

    public Trailer(String movieID, String videoID, String shortDescription, String longDescription) {
        MovieID = movieID;
        VideoID = videoID;
        ShortDescription = shortDescription;
        LongDescription = longDescription;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getMovieID() {
        return MovieID;
    }

    public void setMovieID(String movieID) {
        this.MovieID = movieID;
    }

    public String getShortDescription() {
        return ShortDescription;
    }

    public void setShortDescription(String shortDescription) {
        ShortDescription = shortDescription;
    }

    public String getVideoID() {
        return VideoID;
    }

    public void setVideoID(String videoID) {
        VideoID = videoID;
    }

    public String getLongDescription() {
        return LongDescription;
    }

    public void setLongDescription(String longDescription) {
        LongDescription = longDescription;
    }

}
