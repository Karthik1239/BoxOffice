package com.southasia.boxoffice;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class AddMovie extends ActionBarActivity {

    MyMovieDBHandler dbHandler;
    TextView movieTitle;
    TextView primaryVideoID;
    TextView textDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        movieTitle = (TextView) findViewById(R.id.movieName);
        primaryVideoID = (TextView) findViewById(R.id.videoID);
        textDisplay = (TextView) findViewById(R.id.textDisplay);
        dbHandler = new MyMovieDBHandler(this, null, null, 1);
        printDatabase();
    }

    //Add a product to the databae
    public void addButtonClicked(View view){
        Movie movie = new Movie();
        movie.setTitle(movieTitle.getText().toString());
        movie.setPrimaryVideoId(primaryVideoID.getText().toString());
        dbHandler.addMovie(movie);
        printDatabase();
    }

    //Delete Items
    public void deleteButtonClicked(View view){
        String inputText = movieTitle.getText().toString();
        dbHandler.deleteMovie(inputText);
        printDatabase();
    }

    public void printDatabase() {
        String dbString = dbHandler.databaseToString();
        textDisplay.setText(dbString);
        movieTitle.setText("");
        primaryVideoID.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
