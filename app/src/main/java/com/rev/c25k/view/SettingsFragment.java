package com.rev.c25k.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rev.c25k.R;
import com.rev.c25k.model.Settings;

public class SettingsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        retrievePreferences(view);
        configSaveButton(view);
    }

    private void retrievePreferences(View view) {
        EditText warmUpText = view.findViewById(R.id.edit_text_warm_up);
        warmUpText.setText(Settings.getWarmUpTime(requireContext()));
    }

    private void configSaveButton(View view) {
        view.findViewById(R.id.button_save).setOnClickListener(v -> save());
    }

    private void save() {
        Context context = requireContext();
        EditText warmUpText = requireView().findViewById(R.id.edit_text_warm_up);
        Settings.saveWarmUpTime(warmUpText.getText().toString(), context);
        Toast.makeText(context, R.string.settings_saved, Toast.LENGTH_SHORT).show();
    }
}