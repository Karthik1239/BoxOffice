package com.southasia.boxoffice;

import java.util.List;

/**
 * Created by karthik on 5/2/2015.
 */
public class Movie {

    private String primaryVideoId;
    private String Title;
    private int _id ;

    public int get_id() {
        return _id;
    }

    public String getPrimaryVideoId() {
        return primaryVideoId;
    }

    public void setPrimaryVideoId(String primaryVideoId) {
        this.primaryVideoId = primaryVideoId;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

     public Movie() {
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

//    public String[] getStringArrayOfVideoIds()
//    {
//        if(Trailers!=null){
//            int i=0;
//            String[] arrayOfTrailers = new String[Trailers.size()];
//            for(Trailer trailer : Trailers)
//            {
//                arrayOfTrailers[i] = trailer.getVideoID();
//                i++;
//            }
//            return arrayOfTrailers;
//        }
//        else {
//            return null;
//        }
//        }
}
