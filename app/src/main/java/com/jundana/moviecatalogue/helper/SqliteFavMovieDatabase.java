package com.jundana.moviecatalogue.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import com.jundana.moviecatalogue.model.MovieFav;

public class SqliteFavMovieDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "movie_favorite";
    private static final String TABLE_FAV_MOVIE = "favorite_movie";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_MOVIE_NAME = "movie_name";
    private static final String COLUMN_MOVIE_DETAIL = "movie_detail";

    public SqliteFavMovieDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FAV_MOVIE_TABLE =
                "CREATE	TABLE " + TABLE_FAV_MOVIE + "(" +
                        COLUMN_ID + " INTEGER PRIMARY KEY," +
                        COLUMN_MOVIE_NAME + " TEXT UNIQUE," +
                        COLUMN_MOVIE_DETAIL + " TEXT" + ")";

        db.execSQL(CREATE_FAV_MOVIE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAV_MOVIE);
        onCreate(db);
    }

    public ArrayList<MovieFav> listFavMovie() {
        String sql = "select * from " + TABLE_FAV_MOVIE;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<MovieFav> storeFavMovie = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String movieName = cursor.getString(1);
                String movieDetail = cursor.getString(2);
                storeFavMovie.add(new MovieFav(id, movieName, movieDetail));
            } while (cursor.moveToNext());
        }


        cursor.close();
        return storeFavMovie;
    }

    public void addFavMovie(MovieFav movieFav) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, movieFav.getId());
        values.put(COLUMN_MOVIE_NAME, movieFav.getMovieName());
        values.put(COLUMN_MOVIE_DETAIL, movieFav.getMovieDetail());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_FAV_MOVIE, null, values);
    }

    public void deleteFavoriteMovie(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FAV_MOVIE, COLUMN_ID + "	= ?", new String[]{String.valueOf(id)});
    }
}
