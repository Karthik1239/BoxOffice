package com.southasia.boxoffice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * To display all the movies titles using listview.
 * Created by kn031416 on 8/18/15.
 */
public class ViewMovie extends ActionBarActivity {
    private ListView titleView;
    private ArrayAdapter<String> adapter;
    private List<String> list;
    private HashMap<String, List<String>> map;
    private MyMovieDBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_movies);

        titleView = (ListView) findViewById(R.id.viewVideos);
        dbHandler = new MyMovieDBHandler(getBaseContext(), null, null, 1);

        updateList();

        final EditText title = (EditText) findViewById(R.id.titleDisplay);
        final EditText primaryVideoID = (EditText) findViewById(R.id.primaryVideoID);
        Button updateButton = (Button) findViewById(R.id.updateButton);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(title.getText().toString()!= null && primaryVideoID.getText().toString()!=null &&
                        !title.getText().toString().isEmpty()&& !primaryVideoID.getText().toString().isEmpty())
                {list.add(0, title.getText().toString());
                Movie movie = new Movie();
                String titleText = title.getText().toString();
                movie.setTitle(titleText);
                List<String> videoIDList = new ArrayList<String>();
                    if(map.get(title)!=null)
                    {
                        videoIDList=movie.getVideoIds();
                    }
                videoIDList.add(primaryVideoID.getText().toString());
                movie.setVideoIds(videoIDList);
                dbHandler.addMovie(movie);
                adapter.notifyDataSetChanged();
                title.setText("");
                primaryVideoID.setText("");
                map.put(movie.getTitle(),movie.getVideoIds());
                updateList();
                }

            }
        });

        titleView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String title1 = adapter.getItem(position).toString();
                Toast.makeText(ViewMovie.this, title1, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), EditMovie.class);
                intent.putExtra("list", (ArrayList<String>) map.get(title1));
                startActivity(intent);
            }
        });
    }

    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
        updateList();
    }

    public void updateList(){
        map = (HashMap<String, List<String>>)dbHandler.databaseToMap();
        list = new ArrayList<String>(map.keySet());
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, list);
        titleView.setAdapter(adapter);
    }
}
