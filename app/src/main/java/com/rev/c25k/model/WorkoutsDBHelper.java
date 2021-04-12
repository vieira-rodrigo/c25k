package com.rev.c25k.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WorkoutsDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Workouts.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Workout.TABLE_NAME + " (" +
                    Workout._ID + " INTEGER PRIMARY KEY," +
                    Workout.COLUMN_NAME_TRAINING + " TEXT," +
                    Workout.COLUMN_NAME_DATE + " TEXT," +
                    Workout.COLUMN_NAME_WEEK + " TEXT," +
                    Workout.COLUMN_NAME_SETS + " TEXT," +
                    Workout.COLUMN_NAME_STATUS + " TEXT," +
                    Workout.COLUMN_NAME_TIME + " TEXT," +
                    Workout.COLUMN_NAME_DISTANCE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Workout.TABLE_NAME;

    public WorkoutsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
