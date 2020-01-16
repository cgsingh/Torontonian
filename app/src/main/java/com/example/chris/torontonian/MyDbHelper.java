package com.example.chris.torontonian;

/**
 * Created by chris on 4/2/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by chris on 4/2/2017.
 */

    public class MyDbHelper extends SQLiteOpenHelper {

        public static class MyDataEntry implements BaseColumns {
            public static final String TABLE_NAME = "favLocations";
            public static final String LOC_ID_COLUMN = "locID";
            public static final String LOC_CAT_ID_COLUMN = "locCatID";
        }

        public static final String DB_NAME = "Torontonian.db";
        public static final int DB_VERSION = 1;

        private final String SQL_CREATE_TABLE_QUERY = "CREATE TABLE " + MyDataEntry.TABLE_NAME + " ("
                +MyDataEntry._ID + " INTEGER PRIMARY KEY," + MyDataEntry.LOC_ID_COLUMN + " TEXT,"
                + MyDataEntry.LOC_CAT_ID_COLUMN + " TEXT )";
        private static final String SQL_DELETE_QUERY = "DROP TABLE IF EXISTS " + MyDataEntry.TABLE_NAME;

        public MyDbHelper(Context context){
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            System.out.println("Executing Query: SQL_CREATE_TABLE " + SQL_CREATE_TABLE_QUERY);
            db.execSQL(SQL_CREATE_TABLE_QUERY);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_QUERY);
            onCreate(db);
        }
    }
