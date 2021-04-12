package com.rev.c25k.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rev.c25k.R;
import com.rev.c25k.model.T5KWeeks;

import java.util.Arrays;
import java.util.List;

import static com.rev.c25k.view.Utils.getRunInfo;
import static com.rev.c25k.view.Utils.getWalkInfo;

public class WeekAdapter extends BaseAdapter {
    private List<T5KWeeks> mWeeks = Arrays.asList(T5KWeeks.values());
    private Context mContext;

    public WeekAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(mContext).
                    inflate(R.layout.list_item_week, parent, false);
        }

        T5KWeeks week = mWeeks.get(position);
        String sets = String.format("%s %s", week.getSets(), mContext.getString(R.string.sets));
        ((TextView) view.findViewById(R.id.text_view_week)).setText(week.getLabel());
        ((TextView) view.findViewById(R.id.text_view_run)).setText(getRunInfo(week, mContext));
        ((TextView) view.findViewById(R.id.text_view_walk)).setText(getWalkInfo(week, mContext));
        ((TextView) view.findViewById(R.id.text_view_sets)).setText(sets);

        return view;
    }

    @Override
    public int getCount() {
        return mWeeks.size();
    }

    @Override
    public T5KWeeks getItem(int position) {
        return mWeeks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
