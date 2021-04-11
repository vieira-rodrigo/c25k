package com.rev.c25k.view;

import android.app.PendingIntent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.navigation.fragment.NavHostFragment;

import com.rev.c25k.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            openSettingsFragment();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openSettingsFragment() {
        PendingIntent pendingIntent = new NavDeepLinkBuilder(this.getApplicationContext())
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.SettingsFragment)
                .createPendingIntent();

        try {
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            Log.e(String.valueOf(this.getClass()), e.toString());
        }
    }

    @Override
    public void onBackPressed() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment == null) {
            super.onBackPressed();
            return;
        }

        Fragment currentFragment = navHostFragment.getChildFragmentManager().getFragments().get(0);
        if (currentFragment instanceof ChronometerFragment) {
            ((ChronometerFragment) currentFragment).onBackPressed();
        } else if (currentFragment instanceof HomeFragment) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}