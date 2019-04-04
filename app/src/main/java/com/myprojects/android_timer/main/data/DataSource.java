package com.myprojects.android_timer.main.data;

import com.myprojects.android_timer.main.util.BunchOfDataToSave;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
    private List<BunchOfDataToSave> logs;
    private static DataSource instance;

    private DataSource() {
        logs = new ArrayList<>();
    }

    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    public List getLogs() {
        return logs;
    }

    public void setLog(BunchOfDataToSave bunchOfDataToSave) {
        logs.add(bunchOfDataToSave);
    }
}
