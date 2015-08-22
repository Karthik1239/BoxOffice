package com.southasia.boxoffice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kn031416 on 8/19/15.
 */
public class EditMovie  extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trailer);
        Intent intent = getIntent();
        List<String> titleList = (ArrayList<String>)intent.getSerializableExtra("list");
        ListView relatedVideosView = (ListView) findViewById(R.id.videos);
        final ListAdapter relatedVideosAdapter = new CustomAdapterDel(this, titleList);
        relatedVideosView.setAdapter(relatedVideosAdapter);
    }
}
