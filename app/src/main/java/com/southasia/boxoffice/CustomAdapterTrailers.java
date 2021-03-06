package com.southasia.boxoffice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Karthik on 5/3/2015.
 * Generates custom adapter to display trailer thumbnail(generated by Thumbnails.java) and short description.
 */
public class CustomAdapterTrailers extends ArrayAdapter<String>{
    private Context context;
    private String title;
    public CustomAdapterTrailers(Context context, List<String> videoIds,String title) {
        super(context, R.layout.custom_row, videoIds);
        this.context = context;
        this.title = title;
    }

    //returns custom view for the position
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater trailerSuggestionInflater = LayoutInflater.from(getContext());
        View customView = trailerSuggestionInflater.inflate(R.layout.custom_row, parent, false);
        String videoID = getItem(position);
        TextView trailerSuggestionText = (TextView) customView.findViewById(R.id.trailerText);
        ImageView thumbnailImage = (ImageView) customView.findViewById(R.id.trailerThumbnail);
        if(videoID!=null) {
            trailerSuggestionText.setText(title + " " + Integer.toString(position+1));
        }
        else {
            trailerSuggestionText.setText("Description not available");
        }
        Picasso.with(context).load("http://img.youtube.com/vi/" + videoID + "/default.jpg").into(thumbnailImage);
        return customView;
    }
}
