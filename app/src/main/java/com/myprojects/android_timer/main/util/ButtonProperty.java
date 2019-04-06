package com.myprojects.android_timer.main.util;

import android.widget.TextView;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ButtonProperty {
    private Timer timer;
    private TimerTask task = null;
    private Runnable runnable = null;
    private Boolean isReadyToCancel = false;
    private TimeUtil timeUtil = new TimeUtil();
    private String title;

    public ButtonProperty(String title) {
        this.title = title;
    }

    public Timer getTimer() {
        return timer;
    }

    public TimerTask getTask() {
        return task;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public void setNewTimer() {
        timer = new Timer();
    }

    public void setTask(final TextView textView) {
        task = new TimerTask() {
            @Override
            public void run() {

                runnable = new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(timeUtil.getTimeRepresentationAndIncrement());
                    }
                };
                textView.post(runnable);
            }
        };
        isReadyToCancel = true;
    }

    public void scheduleAtFixedRate(int delay, int period) {
        timer.scheduleAtFixedRate(task, delay, period);
    }

    public void cancel() {
        if (isReadyToCancel) {
            timer.cancel();
        }
    }

    public void clearTime() {
        timeUtil.clear();
    }

    public boolean isActive() {
        return isReadyToCancel;
    }

    public TimeUtil getTimeUtil() {
        return timeUtil;
    }

    public String getTitle() {
        return title;
    }
}
