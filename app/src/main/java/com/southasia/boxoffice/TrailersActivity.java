package com.southasia.boxoffice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class TrailersActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener, YouTubePlayer.OnFullscreenListener {
    private static final int RECOVERY_DIALOG_REQUEST = 10;
    public static final String API_KEY = "AIzaSyBpG49QQqm_Vuiu0Xwl5nLeB_YeFrbR8Vg";
    private YouTubePlayer player1;
    private String title;
    private List<String> videoIds;
    private String primaryVideoId;

    //From URL -> https://www.youtube.com/watch?v=kHue-HaXXzg
    // Let It Go : "Frozen"
    //private String VIDEO_ID = "Wf355GerKPk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailers);

        if (getIntent().getExtras() != null) {
            Map<String, List<String>> mapOfSelectedMovieTitleToListOfVideoIds = (Map<String, List<String>>)getIntent().getSerializableExtra("trailersForSelectedMovie");
            title = (String) mapOfSelectedMovieTitleToListOfVideoIds.keySet().toArray()[0];
            videoIds = mapOfSelectedMovieTitleToListOfVideoIds.get(title);
            primaryVideoId = videoIds.get(0);
        }
        // Youtube Player View.
        YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view1);
        youTubeView.initialize(API_KEY, this);
        //Custom related videos generation
        ListAdapter relatedVideosAdapter = new CustomAdapterTrailers(this, videoIds, title);
        ListView relatedVideosView = (ListView) findViewById(R.id.relatedVideos1);
        relatedVideosView.setAdapter(relatedVideosAdapter);
        relatedVideosView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String videoID = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(TrailersActivity.this, videoID, Toast.LENGTH_SHORT).show();
                        if(player1!=null)
                        player1.cueVideo(videoID);
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
            player1.cueVideo(primaryVideoId);
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
        return (YouTubePlayerView) findViewById(R.id.youtube_view1);
    }

    @Override
    public void onFullscreen(boolean isFullscreen) {
        if(isFullscreen)
        {
            Toast.makeText(this, "Full Screen", Toast.LENGTH_LONG).show();
        }
    }
}
