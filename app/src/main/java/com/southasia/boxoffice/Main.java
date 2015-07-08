package com.southasia.boxoffice;


import java.util.List;
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

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

public class Main extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener, YouTubePlayer.OnFullscreenListener{



private static final int RECOVERY_DIALOG_REQUEST = 10;
        public static final String API_KEY = "AIzaSyBpG49QQqm_Vuiu0Xwl5nLeB_YeFrbR8Vg";
        private Controller controller;
        private YouTubePlayer player1;
        private boolean isFullScreen = false;

    //From URL -> https://www.youtube.com/watch?v=kHue-HaXXzg
        // Let It Go : "Frozen"
        //private String VIDEO_ID = "Wf355GerKPk";

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Youtube Player View.
        YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(API_KEY, this);
        //Custom related videos generation
        controller = new Controller();
        controller.setSelectedVideoId("Wf355GerKPk");
//        List<Trailer> listOfTrailers = controller.getMovie().getTrailers();
//        ListAdapter relatedVideosAdapter = new CustomAdapter(this, controller.getMovie().getStringArrayOfVideoIds(), listOfTrailers);
//        ListView relatedVideosView = (ListView) findViewById(R.id.relatedVideos);
//        relatedVideosView.setAdapter(relatedVideosAdapter);
//        relatedVideosView.setOnItemClickListener(
//                new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        String videoID = String.valueOf(parent.getItemAtPosition(position));
//                        Toast.makeText(Main.this, videoID, Toast.LENGTH_SHORT).show();
//                        controller.setSelectedVideoId(videoID);
//                        player1.cueVideo(controller.getSelectedVideoId());
//                    }
//                });
//        final Button playButton = (Button) findViewById(R.id.play);
//        final Button pauseButton = (Button) findViewById(R.id.pause);

//        playButton.setOnClickListener(
//                new Button.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        player1.play();
//                        playButton.setEnabled(false);
//                        pauseButton.setEnabled(true);
//                    }
//                }
//        );
//        pauseButton.setOnClickListener(
//                new Button.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        player1.pause();
//                        pauseButton.setEnabled(false);
//                        playButton.setEnabled(true);
//                    }
//                }
//        );
        final CheckBox checkBox = (CheckBox) findViewById(R.id.fullScreen);
        checkBox.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    player1.setFullscreen(true);
                    checkBox.setChecked(false);
                }
            }
        });
    }
        @Override
        public void onInitializationFailure(YouTubePlayer.Provider provider,
                                            YouTubeInitializationResult errorReason) {
            if (errorReason.isUserRecoverableError()) {
                errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
            } else {
                String errorMessage = String.format("YouTube Error (%1$s)",
                        errorReason.toString());
                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
            }

        }

        @Override
        public void onInitializationSuccess(Provider provider,
                                            YouTubePlayer player, boolean wasRestored) {
            if (!wasRestored) {
                    this.player1=player;
                    player1.cueVideo(controller.getSelectedVideoId());
            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == RECOVERY_DIALOG_REQUEST) {
                // Retry initialization if user performed a recovery action
                getYouTubePlayerProvider().initialize(API_KEY, this);
            }
        }

        protected YouTubePlayer.Provider getYouTubePlayerProvider() {
            return (YouTubePlayerView) findViewById(R.id.youtube_view);
        }

    @Override
    public void onFullscreen(boolean isFullscreen) {
        if(isFullscreen)
        {
            Toast.makeText(this, "Full Screen", Toast.LENGTH_LONG).show();
        }
    }

    public void gotoAddMovieScreen(View view)
    {
        Intent intent = new Intent(this, AddMovie.class);
        startActivity(intent);
    }
}