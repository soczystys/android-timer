/*
package com.myprojects.android_timer.main.util;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myprojects.android_timer.main.data.newdata.entity.LogEntity;
import com.myprojects.android_timer.main.timer.BoundService;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PlayButtonHandler {

//    private Map<FloatingActionButton, ButtonProperty> buttonsHandlers = new HashMap<>();
    private FloatingActionButton buttonPreviouslyClicked;
    private FloatingActionButton buttonCurrentlyClicked;
    private Boolean previouslyPlayed = false;
    private LocalDateTime beginningDate;
    private Drawable play;
    private Drawable pause;
    private int actionId;
    private BoundService boundService;

    public PlayButtonHandler(Drawable play, Drawable pause) {
        this.play = play;
        this.pause = pause;
    }

    public LogEntity getBunchOfDataToSave() {
//        ButtonProperty buttonProperty = buttonsHandlers.get(buttonCurrentlyClicked);
//        TimeUtil timeUtil = buttonProperty.getTimeUtil();
        LocalDateTime endDate = LocalDateTime.now();
        return new LogEntity(
                actionId,
                beginningDate.getYear(),
                beginningDate.getMonthValue(),
                beginningDate.getDayOfMonth(),
                endDate.getYear(),
                endDate.getMonthValue(),
                endDate.getDayOfMonth(),
//                timeUtil.getHour(),
//                timeUtil.getMin(),
//                timeUtil.getSec());
    }

    public void handlePlayButton(final TextView textView, final FloatingActionButton button, String title, int actionId) {
        setActionId(actionId);
        buttonCurrentlyClicked = button;
//        if (!buttonsHandlers.containsKey(button)) {
//            buttonsHandlers.put(button, new ButtonProperty(title));
//        }

        if (isSameAsPreviousClicked()) {
            if(previouslyPlayed) {
//                stop(button);
                button.setImageDrawable(play);
                boundService.pause();
            } else {
//                play(textView, button);
                button.setImageDrawable(pause);
                boundService.startTicking();
            }
        } else {
//            stop(buttonPreviouslyClicked);
            clearPrevious();
//            play(textView, button);
            button.setImageDrawable(pause);
            boundService.startTicking();
            beginningDate = LocalDateTime.now();
        }

        buttonPreviouslyClicked = button;
    }

    private void clearPrevious() {
//        for (Map.Entry<FloatingActionButton, ButtonProperty> entry : buttonsHandlers.entrySet()) {
//            if (entry.getKey().equals(buttonPreviouslyClicked)) {
//                entry.getValue().clearTime();
            }
        }
    }

    private boolean isSameAsPreviousClicked() {
        if (buttonCurrentlyClicked.equals(buttonPreviouslyClicked)) {
            return true;
        }
        else {
            return false;
        }
    }

    private void play(final TextView textView, final FloatingActionButton button) {
        ButtonProperty buttonProperty = buttonsHandlers.get(button);
        buttonProperty.setNewTimer();
        buttonProperty.setTask(textView);
        buttonProperty.scheduleAtFixedRate(0, 1000);
        button.setImageDrawable(pause);
        previouslyPlayed = true;
    }

    private void stop(final FloatingActionButton button) {
        if (button != null) {
            buttonsHandlers.get(button).cancel();
            button.setImageDrawable(play);
        }
        previouslyPlayed = false;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public void setBoundService(BoundService boundService) {
        this.boundService = boundService;
    }
}
*/
