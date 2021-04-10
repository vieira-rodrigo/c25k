package com.rev.c25k.view;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rev.c25k.R;
import com.rev.c25k.model.Status;
import com.rev.c25k.model.Workout;
import com.rev.c25k.model.WorkoutDAO;

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
        configDelete(view, workout);
    }

    private void configDelete(View view, Workout workout) {
        view.findViewById(R.id.button_delete).setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setMessage(R.string.confirm_delete)
                    .setPositiveButton(R.string.yes, (dialog, which) -> delete(workout))
                    .setNegativeButton(R.string.no, null)
                    .create()
                    .show();
        });
    }

    private void delete(Workout workout) {
        new WorkoutDAO(mContext).delete(workout);
        mList.remove(workout);
        notifyDataSetChanged();
    }

    private void loadData(View view, String date) {
        ((TextView) view.findViewById(R.id.text_view_date)).setText(date);
    }

    private void loadStatus(View view, Status status) {
        int statusDrawable = R.drawable.ic_status_cancelled;
        int statusText = R.string.status_cancelled;
        if (status.equals(Status.FINISHED)) {
            statusDrawable = R.drawable.ic_status_finished;
            statusText = R.string.status_finished;
        }

        view.findViewById(R.id.image_status).setBackgroundResource(statusDrawable);
        ((TextView) view.findViewById(R.id.text_view_status)).setText(statusText);
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
