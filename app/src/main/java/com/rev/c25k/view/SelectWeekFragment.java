package com.rev.c25k.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.rev.c25k.R;

public class SelectWeekFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_select_week, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListView(view);
    }

    private void initListView(View view) {
        ListView listWeek = view.findViewById(R.id.list_view_weeks);
        WeekAdapter weekAdapter = new WeekAdapter(requireContext());
        listWeek.setAdapter(weekAdapter);
        listWeek.setOnItemClickListener((parent, view1, position, id) -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("week", weekAdapter.getItem(position));
            NavHostFragment.findNavController(SelectWeekFragment.this)
                    .navigate(R.id.action_SelectFragment_to_ChronometerFragment, bundle);
        });
    }
}