package com.example.barbertime.OtherClasses;

import java.util.ArrayList;

public class AvailableDaysTimes {

    private String dayShow;
    private String dayDate;
    private ArrayList times;

    public AvailableDaysTimes(String dayShow, String dayDate, ArrayList times) {
        this.dayShow = dayShow;
        this.dayDate = dayDate;
        this.times = times;
    }

    public String getDayShow() {
        return dayShow;
    }

    public void setDayShow(String dayShow) {
        this.dayShow = dayShow;
    }

    public String getDayDate() {
        return dayDate;
    }

    public void setDayDate(String dayDate) {
        this.dayDate = dayDate;
    }

    public ArrayList getTimes() {
        return times;
    }

    public void setTimes(ArrayList times) {
        this.times = times;
    }
}