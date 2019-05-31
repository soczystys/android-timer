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

/**
 * Service used to count time even when application isn't on the foreground
 * and print info when {@link TimerActivity} is active
 * @see TimerActivity
 * @see ActionListAdapter
 */
public class BoundService extends Service {
    private final IBinder binder = new LocalBinder();
    private static TimeUtil timeUtil = new TimeUtil();
    private static Timer timer;
    private TextView textView;
    private static String timeString = "00:00:00";
    private static boolean cycle = false;

    private static Boolean previouslyPlayed = false;
    private static LocalDateTime beginningDate;
    private static Drawable play;
    private static Drawable pause;
    /**
     * Remembers the {@link ActionEntity} represented by the clicked button before last clicked
     */
    private static ActionEntity actionPreviouslyClicked;
    /**
     * Remembers the {@link ActionEntity} represented by the last clicked button
     */
    private static ActionEntity actionCurrentlyClicked;
    /**
     * Connects actionEntities with buttons represent them.
     * @see ActionEntity
     */
    private static Map<ActionEntity, FloatingActionButton> buttonsHandlers = new HashMap<>();

    private static final String TAG = "BoundService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    /**
     * sets time to 00:00:00, and updates {@link BoundService#textView}. Doesn't interefere with {@link BoundService#startTicking()}, or {@link BoundService#pause()}.
     * @see TimeUtil#clear()
     * @see BoundService#printText()
     */
    public void clear() {
        timeUtil.clear();
        printText();
    }

    /**
     * updates {@link BoundService#textView} with the current {@link BoundService#timeString}
     */
    public void printText() {
        if (textView != null) {
            textView.post(new Runnable() {
                @Override
                public void run() {
                    timeString = timeUtil.getTimeRepresentation();
                    textView.setText(timeString);
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

    /**
     * starts counting time. Updates {@link BoundService#textView} with the time when service is bounded.
     * Otherwise only increments time.
     * @see TimeUtil
     * @see BoundService#pause()
     */
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

    /**
     * stops incrementing time
     * @see TimeUtil
     * @see BoundService#startTicking()
     */
    public void pause() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
        cycle = false;
    }

    /**
     * sets textView on which time will be displayed
     * @param textView set {@link TextView} ready to display text.
     * @see BoundService#printText()
     */
    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    /**
     * @return true if time is counted
     */
    public static boolean isCycle() {
        return cycle;
    }

    /**
     * sets icons that will be displayed depending whether button is played or paused.
     * @param play icon that is displayed when the button is paused.
     * @param pause icon that is displayed when the button is played.
     */
    public void setDrawable(Drawable play, Drawable pause) {
        this.play = play;
        this.pause = pause;
    }

    /**
     * Saves time recorded as {@link LogEntity}
     * @return data to save in database.
     */
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

    /**
     * Invoked when button to play is cliked. Decides whether to pause, play button, or to clear time.
     * Should be used in setOnClickListener of the play button.
     * It manages changing icons after click, deciding whether time should be counted or paused and setting {@link BoundService#beginningDate} for saving purposes.
     * @param button the FloatingActionButton that is clicked
     * @param actionEntity name of ActionEntity that the button represents.
     */
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
        return (actionCurrentlyClicked.equals(actionPreviouslyClicked)) ? true: false;
    }

    public static ActionEntity getActionCurrentlyClicked() {
        return actionCurrentlyClicked;
    }
}