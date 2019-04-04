package com.myprojects.android_timer;

import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

public class PlayButtonHandler {

    private Boolean isAboutToPlayNow = false;
    private boolean isCancelled = false;
    private Map<FloatingActionButton, ButtonProperty> buttonsHandlers = new HashMap<>();
    private FloatingActionButton buttonPreviouslyClicked;
    private Drawable play;
    private Drawable pause;

    public PlayButtonHandler(Drawable play, Drawable pause) {
        this.play = play;
        this.pause = pause;
    }

    public void handlePlayButton(final TextView textView, final FloatingActionButton button) {
        isCancelled = false;
        if (!buttonsHandlers.containsKey(button)) {
            buttonsHandlers.put(button, new ButtonProperty());
        }

        changeStatusPlaying();
        if (isForPlay(button)) {
            stopAllHandlers(button);
            play(textView, button);
        } else {
            stop(isCancelled, button);
        }

        buttonPreviouslyClicked = button;
    }

    private boolean isForPlay(FloatingActionButton button) {
        if (!button.equals(buttonPreviouslyClicked)) {
            return true;
        }
        else {
            if (isAboutToPlayNow) {
                return true;
            }
        }
        return false;
    }

    private void stopAllHandlers(FloatingActionButton button) {
        for (Map.Entry<FloatingActionButton, ButtonProperty> entry: buttonsHandlers.entrySet()) {
            if (!entry.getKey().equals(button)) {
                entry.getValue().clearTime();
            }
            entry.getValue().cancel();
        }
    }

    private void play(final TextView textView, final FloatingActionButton button) {
        ButtonProperty buttonProperty = buttonsHandlers.get(button);
        buttonProperty.setNewTimer();
        buttonProperty.setTask(textView);
        buttonProperty.scheduleAtFixedRate(0, 1000);
//        button.setText("stop");
        button.setImageDrawable(pause);
    }

    private void stop(boolean isCancelled, final FloatingActionButton button) {
        stopAllHandlers(button);
//        button.setText("play");

        button.setImageDrawable(play);
    }

    private void changeStatusPlaying() {
        isAboutToPlayNow = !isAboutToPlayNow;
    }
}