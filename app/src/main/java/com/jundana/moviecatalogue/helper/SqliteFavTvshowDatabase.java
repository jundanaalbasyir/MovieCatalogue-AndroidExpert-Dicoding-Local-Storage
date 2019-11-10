package com.jundana.moviecatalogue.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jundana.moviecatalogue.model.TvshowFav;

import java.util.ArrayList;

public class SqliteFavTvshowDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "tvshow_favorite";
    private static final String TABLE_FAV_TVSHOW = "favorite_tvshow";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TVSHOW_NAME = "tvshow_name";
    private static final String COLUMN_TVSHOW_DETAIL = "tvshow_detail";

    public SqliteFavTvshowDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FAV_TVSHOW_TABLE =
                "CREATE	TABLE " + TABLE_FAV_TVSHOW + "(" +
                        COLUMN_ID + " INTEGER PRIMARY KEY," +
                        COLUMN_TVSHOW_NAME + " TEXT UNIQUE," +
                        COLUMN_TVSHOW_DETAIL + " TEXT" + ")";

        db.execSQL(CREATE_FAV_TVSHOW_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAV_TVSHOW);
        onCreate(db);
    }

    public ArrayList<TvshowFav> listFavTvshow() {
        String sql = "select * from " + TABLE_FAV_TVSHOW;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<TvshowFav> storeFavTvshow = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String tvshowName = cursor.getString(1);
                String tvshowDetail = cursor.getString(2);
                storeFavTvshow.add(new TvshowFav(id, tvshowName, tvshowDetail));
            } while (cursor.moveToNext());
        }


        cursor.close();
        return storeFavTvshow;
    }

    public void addFavTvshow(TvshowFav tvshowFav) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, tvshowFav.getId());
        values.put(COLUMN_TVSHOW_NAME, tvshowFav.getTvshowName());
        values.put(COLUMN_TVSHOW_DETAIL, tvshowFav.getTvshowDetail());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_FAV_TVSHOW, null, values);

    }

    public void deleteFavoriteTvshow(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FAV_TVSHOW, COLUMN_ID + "	= ?", new String[]{String.valueOf(id)});
    }
}
