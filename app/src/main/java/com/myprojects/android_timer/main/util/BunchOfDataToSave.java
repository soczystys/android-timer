package com.myprojects.android_timer.main.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

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
}
