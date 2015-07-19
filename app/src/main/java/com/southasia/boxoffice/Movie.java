package com.southasia.boxoffice;

import java.util.List;

/**
 * Created by karthik on 5/2/2015.
 */
public class Movie {

    private List<String> videoIds;
    private String title;
    private int _id ;

    public List<String> getVideoIds() {
        return videoIds;
    }

    public void setVideoIds(List<String> videoIds) {
        this.videoIds = videoIds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
     public Movie() {
    }


}
