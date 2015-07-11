package com.southasia.boxoffice;


import java.util.ArrayList;
import java.util.Arrays;
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
            Map<String,List<String>> movieToVideoIds = dbHandler.databaseToMap();
            ListAdapter relatedVideosAdapter = new CustomAdapter(this, movieToVideoIds.get("bahubali"));
            ListView relatedVideosView = (ListView) findViewById(R.id.relatedVideos);
            relatedVideosView.setAdapter(relatedVideosAdapter);
        }
    }

    public void gotoAddMovieScreen(View view) {
        Intent intent = new Intent(this, AddMovie.class);
        startActivity(intent);
    }
}