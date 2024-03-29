package com.myprojects.android_timer.main.util;

import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.widget.TextView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PlayButtonHandler {

    private Map<FloatingActionButton, ButtonProperty> buttonsHandlers = new HashMap<>();
    private FloatingActionButton buttonPreviouslyClicked;
    private FloatingActionButton buttonCurrentlyClicked;
    private Boolean previouslyPlayed = false;
    private Drawable play;
    private Drawable pause;
    private Date beginningDate;

    public PlayButtonHandler(Drawable play, Drawable pause) {
        this.play = play;
        this.pause = pause;
    }

    public BunchOfDataToSave getBunchOfDataToSave() {
        ButtonProperty buttonProperty = buttonsHandlers.get(buttonCurrentlyClicked);
        TimeUtil timeUtil = buttonProperty.getTimeUtil();
        return new BunchOfDataToSave(buttonProperty.getTitle(),
                beginningDate, new Date(), timeUtil);
    }

    public void handlePlayButton(final TextView textView, final FloatingActionButton button, String title) {
        buttonCurrentlyClicked = button;
        if (!buttonsHandlers.containsKey(button)) {
            buttonsHandlers.put(button, new ButtonProperty(title));
        }

        if (isSameAsPreviousClicked()) {
            if(previouslyPlayed) {
                stop(button);
            } else {
                play(textView, button);
            }
        } else {
            stop(buttonPreviouslyClicked);
            clearPrevious();
            play(textView, button);
            beginningDate = new Date();
        }

        buttonPreviouslyClicked = button;
    }

    private void clearPrevious() {
        for (Map.Entry<FloatingActionButton, ButtonProperty> entry : buttonsHandlers.entrySet()) {
            if (entry.getKey().equals(buttonPreviouslyClicked)) {
                entry.getValue().clearTime();
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
}
