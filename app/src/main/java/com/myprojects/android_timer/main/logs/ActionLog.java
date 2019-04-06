package com.myprojects.android_timer.main.logs;

class ActionLog {
    private String title;
    private String beginningDate;
    private String endDate;
    private String time;

    public ActionLog(String title, String beginningDate, String endDate, String time) {
        this.title = title;
        this.beginningDate = beginningDate;
        this.endDate = endDate;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public String getBeginningDate() {
        return beginningDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getTime() {
        return time;
    }
}
