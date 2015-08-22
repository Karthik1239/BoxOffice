package com.southasia.boxoffice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by kn031416 on 8/19/15.
 */
public class CustomAdapterDel extends ArrayAdapter<String> {
private Context context;
private List<String> videoIds;
 private MyMovieDBHandler dbHandler;

        public CustomAdapterDel(Context context, List<String> videoIds) {
        super(context, R.layout.custom_row, videoIds);
        this.context = context;
        this.videoIds = videoIds;
        }

//returns custom view for the position
@Override
public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater trailerSuggestionInflater = LayoutInflater.from(getContext());
        View customView = trailerSuggestionInflater.inflate(R.layout.custom_trailer_row, parent, false);
        final String videoID = videoIds.get(position);
        final int pos = position;
        TextView trailerSuggestionText = (TextView) customView.findViewById(R.id.trlrText);
        ImageView thumbnailImage = (ImageView) customView.findViewById(R.id.trailerThumb);
        if(videoID!=null) {
        trailerSuggestionText.setText(videoID);
        }
        else {
        trailerSuggestionText.setText("Description not available");
        }
        Picasso.with(context).load("http://img.youtube.com/vi/" + videoID + "/default.jpg").into(thumbnailImage);
        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button)customView.findViewById(R.id.delButton);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        dbHandler = new MyMovieDBHandler(getContext(), null, null, 1);
                        dbHandler.deleteVideoId(videoID);
                        //do something
                        videoIds.remove(pos); //or some other task
                        notifyDataSetChanged();

                }
        });
        return customView;
        }
}
