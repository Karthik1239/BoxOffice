package com.southasia.boxoffice;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends Activity {

    private static final int RECOVERY_DIALOG_REQUEST = 10;
    private MyMovieDBHandler dbHandler;
    private  Map<String,List<String>> movieToVideoIds;
    private List<String> primaryVideoIdsOfAllMovies;
    private Map<String, String> primaryVideoIdToTitleMap;
    private ListAdapter relatedVideosAdapter;
    private ListView relatedVideosView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHandler = new MyMovieDBHandler(this, null, null, 1);
        if (!(dbHandler.databaseToString().equals(""))) {
            UpdateMovies();
            relatedVideosView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Intent newActivity = new Intent(view.getContext(),TrailersActivity.class);
                    String videoID = relatedVideosAdapter.getItem(position).toString();
                    String title = primaryVideoIdToTitleMap.get(videoID);
                    HashMap<String, ArrayList<String>> mapOfMovieTitleToListOfVideoIds = new HashMap<String, ArrayList<String>>();
                    ArrayList<String> listOfVideoIds = (ArrayList<String>) movieToVideoIds.get(title);
                    mapOfMovieTitleToListOfVideoIds.put(title, listOfVideoIds);
                    newActivity.putExtra("trailersForSelectedMovie", mapOfMovieTitleToListOfVideoIds );
                    startActivity(newActivity);

                }
            });
        }
    }

    public void gotoAddMovieScreen(View view) {
        Intent intent = new Intent(this, ViewMovie.class);
        startActivity(intent);
    }

    private Map<String, String> primaryVideoIdToTitleMap(Map<String,List<String>> movieToVideoIds)
    {
        Map<String, String> primaryVideoIdToTitle = new HashMap<String, String>();
        for (String movie : movieToVideoIds.keySet())
        {
            List<String> videoIds = movieToVideoIds.get(movie);
            primaryVideoIdToTitle.put(videoIds.get(0), movie);
        }
        return primaryVideoIdToTitle;
    }

    private List<String> primaryVideoIdsOfAllMovies(Map<String, List<String>> movieToVideoIds)
    {
        List<String> primaryVideos = new ArrayList<String>();
        for (String movie : movieToVideoIds.keySet())
        {
            List<String> videoIds = movieToVideoIds.get(movie);
            primaryVideos.add(videoIds.get(0));
        }
        return primaryVideos;
    }

    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
        UpdateMovies();
    }

    public void UpdateMovies()
    {
        movieToVideoIds = dbHandler.databaseToMap();
        primaryVideoIdToTitleMap = primaryVideoIdToTitleMap(movieToVideoIds);
        primaryVideoIdsOfAllMovies = primaryVideoIdsOfAllMovies(movieToVideoIds);
        relatedVideosAdapter = new CustomAdapter(this, primaryVideoIdsOfAllMovies, primaryVideoIdToTitleMap);
        relatedVideosView = (ListView) findViewById(R.id.relatedVideos);
        relatedVideosView.setAdapter(relatedVideosAdapter);
    }
}