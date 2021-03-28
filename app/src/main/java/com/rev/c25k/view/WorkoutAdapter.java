package com.rev.c25k.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rev.c25k.R;
import com.rev.c25k.model.Status;
import com.rev.c25k.model.Workout;

import java.util.List;

public class WorkoutAdapter extends BaseAdapter {
    private List<Workout> mList;
    private Context mContext;

    public WorkoutAdapter(List<Workout> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(mContext).
                    inflate(R.layout.list_item_workout, parent, false);
        }

        loadView(view, getItem(position));
        return view;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Workout getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private void loadView(View view, Workout workout) {
        loadStatus(view, workout.getStatus());
        loadData(view, workout.getDate());
        loadTraining(view, workout);
        loadSets(view, workout.getSets());
        loadTime(view, workout.getTime());
    }

    private void loadData(View view, String date) {
        date = date.substring(0, 5);
        ((TextView) view.findViewById(R.id.text_view_date)).setText(date);
    }

    private void loadStatus(View view, Status status) {
        int statusDrawable = status.equals(Status.FINISHED) ?
                R.drawable.ic_status_finished : R.drawable.ic_status_cancelled;

        view.findViewById(R.id.image_status).setBackgroundResource(statusDrawable);
    }

    private void loadTraining(View view, Workout workout) {
        String text = String.format("%s-%s", workout.getTraining().getLabel(),
                workout.getWeek().getLabel(), workout.getTime());
        ((TextView) view.findViewById(R.id.text_view_training)).setText(text);
    }

    private void loadSets(View view, String sets) {
        ((TextView) view.findViewById(R.id.text_view_sets)).setText(sets);
    }

    private void loadTime(View view, String time) {
        ((TextView) view.findViewById(R.id.text_view_time)).setText(time);
    }
}
