package com.southasia.boxoffice;

import java.util.*;
import java.util.ArrayList;

/**
 * Created by karthik on 5/3/2015.
 */
public class Controller {
    private String[] videoIds={"kWGWTkms6CM","_pqngUO_pP4","33pMNv6k6bA","NKTUbFsN8WA"};
    private String selectedVideoId;

    public String getSelectedVideoId() {
        return selectedVideoId;
    }

    public void setSelectedVideoId(String selectedVideoId) {
        this.selectedVideoId = selectedVideoId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    private Movie movie;
//    public String[] getListOfRelatedTrailerVideIds() {
//        return movie.getStringArrayOfVideoIds();
//    }

    // Create new ArrayList.
    private List<String> listOfRelatedTrailers = new ArrayList<String>(); //no fixed size mentioned


    public Controller() {
        listOfRelatedTrailers = Arrays.asList(videoIds);
////        Trailer trailer1 = new Trailer(listOfRelatedTrailers.get(0),"Temper", "This is a very Long Description temper1");
////        Trailer trailer2 = new Trailer(listOfRelatedTrailers.get(1),"Yevade Subrahmanyam", "This is a very Long Description for let it go");
////        Trailer trailer3 = new Trailer(listOfRelatedTrailers.get(2),"Race Gurram", "This is a very Long Description temper action");
////        Trailer trailer4 = new Trailer(listOfRelatedTrailers.get(3),"1 Nenokkadine", "This is a very long description for Temper Theatrical Trailer");
//        List<Trailer> trailers = new ArrayList<>();
//        trailers.add(trailer1);
//        trailers.add(trailer2);
//        trailers.add(trailer3);
//        trailers.add(trailer4);
//        trailers.add(trailer1);
//        trailers.add(trailer2);
//        trailers.add(trailer3);
//        trailers.add(trailer4);
        movie = new Movie();
        movie.setTitle("Temper");
//        movie.setTrailers(trailers);
   }
}
