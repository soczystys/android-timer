package com.myprojects.android_timer.main.util;

import android.icu.util.GregorianCalendar;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Timer;

public class BunchOfDataToSave {
    private String title;
    private Date beginningDate;
    private Date endDate;
    private Integer sec;
    private Integer min;
    private Integer hour;

    public BunchOfDataToSave(String title, Date beginningDate, Date endDate, TimeUtil timeUtil) {
        this.beginningDate = beginningDate;
        this.endDate = endDate;
        this.title = title;
        hour = timeUtil.getHour();
        min = timeUtil.getMin();
        sec = timeUtil.getSec();
    }

    public String getTitle() {
        return title;
    }

    public Date getBeginningDate() {
        return beginningDate;
    }

    public Date getEndDate() {
        return endDate;
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

    public static void main(String[] args) {
        Date d = new Date();
        System.out.println(d);
        System.out.println(d.getMonth());
//        System.out.println(d.);
        long l = System.currentTimeMillis();
        System.out.println(l/1000/60/60);
//        String[] strings = d.toString();
        StringTokenizer stringTokenizer = new StringTokenizer(d.toString(), " ");
        List<String> stringList = new ArrayList<>();
        while (stringTokenizer.hasMoreTokens()) {
            stringList.add(stringTokenizer.nextToken());
        }
        System.out.println(stringList);
        System.out.println("Time: " + stringList.get(3));
        System.out.println("Year: " + stringList.get(5));
        System.out.println("Month: " + stringList.get(1));
        System.out.println("Day: " + stringList.get(2));
    }
}
