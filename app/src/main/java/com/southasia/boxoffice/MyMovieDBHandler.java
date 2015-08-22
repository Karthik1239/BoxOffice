package com.southasia.boxoffice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by karthik on 6/20/2015.
 */
public class MyMovieDBHandler  extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Movies.db";
    public static final String TABLE_MOVIE = "Movie";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "Title";
    private static final String COLUMN_PrimaryVideoID = "primaryVideoId";

    //We need to pass database information along to superclass
    public MyMovieDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_MOVIE + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_PrimaryVideoID + " TEXT " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        onCreate(db);
    }

    //Add a new row to the database i.e., add a movie
    public void addMovie(Movie movie){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, movie.getTitle());
        values.put(COLUMN_PrimaryVideoID, movie.getVideoIds().get(0));
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_MOVIE, null, values);
        db.close();
    }

    //Delete a title from the database
    public void deleteMovie(String movieTitle){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_MOVIE + " WHERE " + COLUMN_TITLE + "=\"" + movieTitle + "\";");
    }

    //Delete a videoId from the database
    public void deleteVideoId(String videoID){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_MOVIE + " WHERE " + COLUMN_PrimaryVideoID + "=\"" + videoID + "\";");
    }

    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_MOVIE + " WHERE 1";

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_TITLE)) != null) {
                dbString += c.getString(c.getColumnIndex(COLUMN_TITLE));
                dbString += " ";
                dbString += c.getString(c.getColumnIndex(COLUMN_PrimaryVideoID));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }

    public Map<String, List<String>> databaseToMap(){
        String key;
        String videoId;
        List<String> listOfVideoIds;
        Map<String, List<String>> movieToVideoId = new HashMap<String, List<String>>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_MOVIE + " WHERE 1";

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_TITLE)) != null) {
                key = c.getString(c.getColumnIndex(COLUMN_TITLE));
                videoId = c.getString(c.getColumnIndex(COLUMN_PrimaryVideoID));
                if(movieToVideoId.get(key)!=null)
                {
                    listOfVideoIds = movieToVideoId.get(key);
                }
                else {
                    listOfVideoIds = new ArrayList<String>();
                }
                    listOfVideoIds.add(videoId);
                    movieToVideoId.put(key,listOfVideoIds);
            }
            c.moveToNext();
        }
        db.close();
        return movieToVideoId;
    }

    public List<Movie> moviesInDatabase(){
        List<String> listOfVideoIds;
        Map<String, List<String>> movieToVideoId = new HashMap<String, List<String>>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_MOVIE + " WHERE 1";

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_TITLE)) != null) {
                String key = c.getString(c.getColumnIndex(COLUMN_TITLE));
                String videoId = c.getString(c.getColumnIndex(COLUMN_PrimaryVideoID));
                if(movieToVideoId.get(key)!=null)
                {
                    listOfVideoIds = movieToVideoId.get(key);
                }
                else {
                    listOfVideoIds = new ArrayList<String>();
                }
                listOfVideoIds.add(videoId);
                movieToVideoId.put(key,listOfVideoIds);
            }
            c.moveToNext();
        }
        db.close();
        List movies = new ArrayList<Movie>();
        for (String key : movieToVideoId.keySet()) {
            Movie movie = new Movie();
            movie.setTitle(key);
            movie.setVideoIds(movieToVideoId.get(key));
            movies.add(movie);
        }
        return movies;
    }
}
