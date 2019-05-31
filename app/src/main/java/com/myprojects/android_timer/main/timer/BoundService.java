package com.myprojects.android_timer.main.timer;

import android.app.Service;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myprojects.android_timer.main.data.newdata.entity.ActionEntity;
import com.myprojects.android_timer.main.data.newdata.entity.LogEntity;
import com.myprojects.android_timer.main.util.TimeUtil;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class BoundService extends Service {
    private final IBinder binder = new LocalBinder();
    private static TimeUtil timeUtil = new TimeUtil();
    private static Timer timer;
    private TextView textView;
    private static String timeString = "00:00:00";
    private static boolean cycle = false;



    private static final String TAG = "BoundService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void clear() {
        timeUtil.clear();
        printText();
        Log.d(TAG, "clear: should print actual string");
        Log.d(TAG, "clear: textView: " + textView);
    }

    public void printText() {
        if (textView != null) {
            textView.post(new Runnable() {
                @Override
                public void run() {
                    timeString = timeUtil.getTimeRepresentation();
                    textView.setText(timeString);
//                    Log.d(TAG, "print text");
                }
            });
        }
    }

    public void init() {
        printText();
        if (isCycle()) {
            startTicking();
        }
    }

    public class LocalBinder extends Binder {

        BoundService getService() {
            return BoundService.this;
        }
    }
    public void startTicking() {
        pause();
        timer = new Timer();
        Log.d(TAG, "startTicking: inside method");
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeString = timeUtil.getTimeRepresentationAndIncrement();
                printText();
            }
        }, 0, 1000);
        cycle = true;
    }

    public void pause() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
        cycle = false;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public String getTimeString() {
        return timeString;
    }

    public static boolean isCycle() {
        return cycle;
    }



    //from PlayButtonHandler properties

    private static Boolean previouslyPlayed = false;
    private static LocalDateTime beginningDate;
    private static Drawable play;
    private static Drawable pause;
    private static ActionEntity actionPreviouslyClicked;
    private static ActionEntity actionCurrentlyClicked;
    public void setDrawable(Drawable play, Drawable pause) {
        this.play = play;
        this.pause = pause;
        Log.d(TAG, String.valueOf(actionCurrentlyClicked));
        Log.d(TAG, String.valueOf(actionPreviouslyClicked));
        Log.d(TAG, String.valueOf(previouslyPlayed));
        Log.d(TAG, String.valueOf(buttonsHandlers));
    }

    private static Map<ActionEntity, FloatingActionButton> buttonsHandlers = new HashMap<>();

    public LogEntity getBunchOfDataToSave() {
        LocalDateTime endDate = LocalDateTime.now();
        return new LogEntity(
                actionCurrentlyClicked.getId(),
                beginningDate.getYear(),
                beginningDate.getMonthValue(),
                beginningDate.getDayOfMonth(),
                endDate.getYear(),
                endDate.getMonthValue(),
                endDate.getDayOfMonth(),
                timeUtil.getHour(),
                timeUtil.getMin(),
                timeUtil.getSec());
    }


    public void handlePlayButton(final FloatingActionButton button, ActionEntity actionEntity) {
        actionCurrentlyClicked = actionEntity;
        if (!buttonsHandlers.containsKey(actionEntity)) {
            buttonsHandlers.put(actionEntity, button);
            if (isCycle()) {
                button.setImageDrawable(pause);
            }
        }
        if (isSameAsPreviousClicked()) {
            if(previouslyPlayed) {
                button.setImageDrawable(play);
                pause();
                previouslyPlayed = false;
            } else {
                button.setImageDrawable(pause);
                startTicking();
                previouslyPlayed = true;
            }
        } else {
            clear();
            button.setImageDrawable(pause);
            if (buttonsHandlers.get(actionPreviouslyClicked) != null) {
                buttonsHandlers.get(actionPreviouslyClicked).setImageDrawable(play);
            }
            startTicking();
            beginningDate = LocalDateTime.now();
            previouslyPlayed = true;
        }
        actionPreviouslyClicked = actionEntity;
    }

    private boolean isSameAsPreviousClicked() {
        if (actionCurrentlyClicked.equals(actionPreviouslyClicked)) {
            return true;
        }
        else {
            return false;
        }
    }

    public static ActionEntity getActionCurrentlyClicked() {
        return actionCurrentlyClicked;
    }
}