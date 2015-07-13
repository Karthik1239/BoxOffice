package com.southasia.boxoffice;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;

import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

public class Main extends Activity {

    private static final int RECOVERY_DIALOG_REQUEST = 10;
    private MyMovieDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHandler = new MyMovieDBHandler(this, null, null, 1);
        if (!(dbHandler.databaseToString().equals(""))) {
            final Map<String,List<String>> movieToVideoIds = dbHandler.databaseToMap();
            final Map<String, String> primaryVideoIdToTitleMap = primaryVideoIdToTitleMap(movieToVideoIds);
            final List<String> primaryVideoIdsOfAllMovies = primaryVideoIdsOfAllMovies(movieToVideoIds);
            final ListAdapter relatedVideosAdapter = new CustomAdapter(this, primaryVideoIdsOfAllMovies, primaryVideoIdToTitleMap);
            ListView relatedVideosView = (ListView) findViewById(R.id.relatedVideos);
            relatedVideosView.setAdapter(relatedVideosAdapter);
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
        Intent intent = new Intent(this, AddMovie.class);
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
}