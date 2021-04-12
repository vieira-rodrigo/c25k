package com.rev.c25k.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class WorkoutDAO {

    private SQLiteDatabase db;
    private WorkoutsDBHelper dbHelper;

    public WorkoutDAO(Context context) {
        this.dbHelper = new WorkoutsDBHelper(context);
        this.db = dbHelper.getReadableDatabase();
    }

    public void save(Workout workout) {
        ContentValues values = new ContentValues();
        values.put(Workout.COLUMN_NAME_TRAINING, workout.getTraining().toString());
        values.put(Workout.COLUMN_NAME_DATE, workout.getDate());
        values.put(Workout.COLUMN_NAME_WEEK, workout.getWeek().toString());
        values.put(Workout.COLUMN_NAME_SETS, workout.getSets());
        values.put(Workout.COLUMN_NAME_STATUS, workout.getStatus().toString());
        values.put(Workout.COLUMN_NAME_TIME, workout.getTime());
        values.put(Workout.COLUMN_NAME_DISTANCE, workout.getDistance());

        if (workout.getId() == null) {
            long rowID = db.insert(Workout.TABLE_NAME, null, values);
            workout.setId(rowID);
        } else {
            String selection = Workout._ID + " = ?";
            String[] selectionArgs = {String.valueOf(workout.getId())};
            db.update(Workout.TABLE_NAME, values, selection, selectionArgs);
        }
    }

    public List<Workout> getAll() {
        String[] projection = {
                Workout._ID,
                Workout.COLUMN_NAME_TRAINING,
                Workout.COLUMN_NAME_DATE,
                Workout.COLUMN_NAME_WEEK,
                Workout.COLUMN_NAME_SETS,
                Workout.COLUMN_NAME_STATUS,
                Workout.COLUMN_NAME_TIME,
                Workout.COLUMN_NAME_DISTANCE
        };

        String sortOrder = Workout._ID + " DESC";

        Cursor cursor = db.query(
                Workout.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        return mountWorkouts(cursor);
    }

    public void delete(Workout workout) {
        String selection = Workout._ID + " = ?";
        String[] selectionArgs = {String.valueOf(workout.getId())};
        db.delete(Workout.TABLE_NAME, selection, selectionArgs);
    }

    private List<Workout> mountWorkouts(Cursor cursor) {
        List<Workout> workouts = new ArrayList<>();

        while (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndexOrThrow(Workout._ID);
            int trainingIndex = cursor.getColumnIndexOrThrow(Workout.COLUMN_NAME_TRAINING);
            int dateIndex = cursor.getColumnIndexOrThrow(Workout.COLUMN_NAME_DATE);
            int weekIndex = cursor.getColumnIndexOrThrow(Workout.COLUMN_NAME_WEEK);
            int setsIndex = cursor.getColumnIndexOrThrow(Workout.COLUMN_NAME_SETS);
            int statusIndex = cursor.getColumnIndexOrThrow(Workout.COLUMN_NAME_STATUS);
            int timeIndex = cursor.getColumnIndexOrThrow(Workout.COLUMN_NAME_TIME);
            int distanceIndex = cursor.getColumnIndexOrThrow(Workout.COLUMN_NAME_DISTANCE);

            Long id = cursor.getLong(idIndex);
            Training training = Training.valueOf(cursor.getString(trainingIndex));
            String date = cursor.getString(dateIndex);
            T5KWeeks week = T5KWeeks.valueOf(cursor.getString(weekIndex));
            String sets = cursor.getString(setsIndex);
            Status status = Status.valueOf(cursor.getString(statusIndex));
            String time = cursor.getString(timeIndex);
            String distance = cursor.getString(distanceIndex);

            workouts.add(new Workout(id, training, date, week, sets, status, time, distance));
        }

        return workouts;
    }
}
