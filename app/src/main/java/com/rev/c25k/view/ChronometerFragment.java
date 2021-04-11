package com.rev.c25k.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.rev.c25k.R;
import com.rev.c25k.model.Settings;
import com.rev.c25k.model.Status;
import com.rev.c25k.model.T5KWeeks;
import com.rev.c25k.model.Training;
import com.rev.c25k.model.Workout;
import com.rev.c25k.model.WorkoutDAO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;

import static androidx.navigation.fragment.NavHostFragment.findNavController;
import static com.rev.c25k.view.Utils.formatTimeText;
import static java.lang.String.format;

public class ChronometerFragment extends Fragment {

    private final int ACTION_WARM_UP = 1;
    private final int ACTION_WALK = 2;
    private final int ACTION_RUN = 3;

    Chronometer mChronometer;
    private T5KWeeks mWeek;
    private int mCurrentSet;
    private int mCurrentAction = ACTION_WARM_UP;
    private Button mStartButton;
    private Button mCancelButton;
    private Button mBackButton;
    private TextView mTvTime;
    private TextView mTvSets;
    private Instant startTime;
    private Status mStatus;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_chronometer, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initChronometer(view);
        initStartButton(view);
        initCancelButton(view);
        initBackButton(view);
        getWeek(view);
        showOnlyStartButton();
    }

    private void showOnlyStartButton() {
        mStartButton.setVisibility(View.VISIBLE);
        mCancelButton.setVisibility(View.GONE);
        mBackButton.setVisibility(View.GONE);
    }

    private void showOnlyCancelButton() {
        mCancelButton.setVisibility(View.VISIBLE);
        mStartButton.setVisibility(View.GONE);
        mBackButton.setVisibility(View.GONE);
    }

    private void showOnlyBackButton() {
        mBackButton.setVisibility(View.VISIBLE);
        mStartButton.setVisibility(View.GONE);
        mCancelButton.setVisibility(View.GONE);
    }

    private void getWeek(View view) {
        Bundle arguments = getArguments();
        assert arguments != null;
        mWeek = (T5KWeeks) arguments.getSerializable("week");

        ((TextView) view.findViewById(R.id.text_view_week)).setText(mWeek.getLabel());

        Context context = requireContext();
        String runInfo = String.format("%s %s", context.getString(R.string.run),
                formatTimeText(mWeek.getSecondsToRun(), context));
        String walkInfo = String.format("%s %s", context.getString(R.string.walk),
                formatTimeText(mWeek.getSecondsToWalk(), context));
        String weekInfo = String.format("%s %s", runInfo, walkInfo);
        ((TextView) view.findViewById(R.id.text_view_week_info)).setText(weekInfo);
    }

    private void initStartButton(View view) {
        mStartButton = view.findViewById(R.id.button_start_chronometer);
        mStartButton.setOnClickListener(view1 -> start());
    }

    private void initCancelButton(View view) {
        mCancelButton = view.findViewById(R.id.button_cancel);
        mCancelButton.setOnClickListener(view1 -> cancel());
    }

    private void initBackButton(@NonNull View view) {
        mBackButton = view.findViewById(R.id.button_back);
        mBackButton.setOnClickListener(view1 -> backHome());
    }

    private void backHome() {
        findNavController(ChronometerFragment.this)
                .navigate(R.id.action_Chronometer_to_HomeFragment);
    }

    private void initChronometer(View view) {
        mTvTime = view.findViewById(R.id.text_view_time);
        mTvTime.setVisibility(View.GONE);
        mChronometer = view.findViewById(R.id.chronometer);
        mChronometer.setOnChronometerTickListener(chronometer -> {
            long elapsed = SystemClock.elapsedRealtime() - chronometer.getBase();
            if (elapsed > 0) {
                chronometer.stop();
                handleActionFinished(chronometer);
            }
        });
    }

    private void handleActionFinished(Chronometer chronometer) {

        switch (mCurrentAction) {
            case ACTION_WARM_UP:
            case ACTION_WALK:
                if (mCurrentSet == mWeek.getSets()) {
                    finish(true);
                    return;
                }
                mCurrentSet++;
                mCurrentAction = ACTION_RUN;
                break;
            default:
                mCurrentAction = ACTION_WALK;
        }

        updateChronometer(chronometer);
    }

    private void updateChronometer(Chronometer chronometer) {
        mTvSets = requireView().findViewById(R.id.text_view_sets);
        mTvSets.setText(format("%s %s/%s", getString(R.string.current_sets), mCurrentSet, mWeek.getSets()));
        chronometer.setBase(SystemClock.elapsedRealtime() + getBase());
        chronometer.start();
        updateAction(getActionRes(), true);
    }

    private long getBase() {
        switch (mCurrentAction) {
            case ACTION_WARM_UP:
                int mWarmUpTime = Integer.parseInt(Settings.getWarmUpTime(requireContext()));
                return mWarmUpTime * 60 * 1000;
            case ACTION_WALK:
                return mWeek.getSecondsToWalk() * 1000;
            default:
                return mWeek.getSecondsToRun() * 1000;
        }
    }

    private void updateAction(int action, boolean blink) {
        TextView textViewAction = requireView().findViewById(R.id.text_view_action);
        textViewAction.setText(action);
        if (blink) {
            setActionAnimation(textViewAction);
        } else {
            textViewAction.clearAnimation();
        }
    }

    private int getActionRes() {
        switch (mCurrentAction) {
            case ACTION_WARM_UP:
                return R.string.warm_up;
            case ACTION_WALK:
                return R.string.walk;
            default:
                return R.string.run;
        }
    }

    private void setActionAnimation(TextView textViewAction) {
        if (mCurrentSet > 1) return;

        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(800);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        textViewAction.startAnimation(anim);
    }

    private void start() {
        startTime = Instant.now();
        updateChronometer(mChronometer);
        showOnlyCancelButton();
    }

    private void finish(boolean finished) {
        mStatus = finished ? Status.FINISHED : Status.CANCELLED;
        int status = mStatus.equals(Status.FINISHED) ? R.string.status_finished
                : R.string.status_cancelled;
        updateAction(status, false);
        handleResult();
        showOnlyBackButton();
    }

    @SuppressLint("SimpleDateFormat")
    private void handleResult() {
        Instant finish = Instant.now();
        long elapsed = Duration.between(startTime, finish).toMillis();
        DateFormat df = new SimpleDateFormat("mm:ss");
        String time = df.format(elapsed);
        showResult(time);
        saveResult(time);
    }

    @SuppressLint("SimpleDateFormat")
    private void saveResult(String time) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Calendar cal = Calendar.getInstance();
        String date = df.format(cal.getTime());
        String sets = format("%s/%s", mCurrentSet, mWeek.getSets());
        Workout workout = new Workout(null, Training.T5K, date, mWeek,
                sets, mStatus, time);
        new WorkoutDAO(requireContext()).save(workout);
    }

    private void showResult(String time) {
        mChronometer.setVisibility(View.GONE);
        mTvTime.setVisibility(View.VISIBLE);
        mTvTime.setText(String.format("%s: %s", getString(R.string.time), time));
    }

    private void cancel() {
        mChronometer.stop();

        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setMessage(R.string.confirm_cancel)
                .setPositiveButton(R.string.yes, (dialog, which) -> finish(false))
                .setNegativeButton(R.string.no, (dialog, which) -> mChronometer.start())
                .create()
                .show();
    }

    public void onBackPressed() {
        if (startTime != null && mStatus == null) {
            cancel();
        } else {
            backHome();
        }
    }
}