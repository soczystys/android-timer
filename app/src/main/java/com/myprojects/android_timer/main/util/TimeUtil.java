package com.myprojects.android_timer.main.util;

import java.time.LocalDateTime;

public class TimeUtil {
    private Integer sec = 0;
    private Integer min = 0;
    private Integer hour = 0;
    private LocalDateTime beginning;
    private LocalDateTime end;

    private void increment() {
        sec++;
        if(sec == 60) {
            min++;
            sec = 0;
        }
        if (min == 60) {
            hour++;
            min = 0;
        }
    }

    public void clear() {
        sec = min = hour = 0;
    }

    public String getTimeRepresentation() {
        StringBuilder b = new StringBuilder();
        b.append(
                (hour/10)).append(hour%10).
                append(":").append(min/10).append(min%10).
                append(":").append(sec/10).append(sec%10);
        return b.toString();
    }

    public String getTimeRepresentationAndIncrement() {
        String time = getTimeRepresentation();
        increment();
        return time;
    }

    public LocalDateTime getBeginning() {
        return beginning;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public Integer getSec() {
        return sec;
    }

    public Integer getMin() {
        return min;
    }

    public Integer getHour() {
        return hour;
    }

    public void setBeginning() {
        this.beginning = LocalDateTime.now();
    }

    public void setEnd() {
        this.end = LocalDateTime.now();
    }
}
