package com.myprojects.android_timer;

import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

class RecyclerItem {

    private String title;

    public RecyclerItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
