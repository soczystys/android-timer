package com.myprojects.android_timer.main.util;

import java.time.LocalDateTime;

public class BunchOfDataToSave {
    private String title;
    private Integer sec = 0;
    private Integer min = 0;
    private Integer hour = 0;
    private LocalDateTime beginning;
    private LocalDateTime end;

    public BunchOfDataToSave(String title, Integer sec, Integer min, Integer hour, LocalDateTime beginning, LocalDateTime end) {
        this.title = title;
        this.sec = sec;
        this.min = min;
        this.hour = hour;
        this.beginning = beginning;
        this.end = end;
    }

    @Override
    public String toString() {
        return "BunchOfDataToSave{" +
                "title='" + title + '\'' +
                ", sec=" + sec +
                ", min=" + min +
                ", hour=" + hour +
                ", beginning=" + beginning +
                ", end=" + end +
                '}';
    }
}
