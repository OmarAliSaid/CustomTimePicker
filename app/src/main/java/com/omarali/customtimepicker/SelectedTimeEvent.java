package com.omarali.customtimepicker;

public class SelectedTimeEvent {
    String day , hour, minutes , Am_Pm;

    public SelectedTimeEvent() {
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getAm_Pm() {
        return Am_Pm;
    }

    public void setAm_Pm(String am_Pm) {
        Am_Pm = am_Pm;
    }

    public String toString(){
        return day + "  "+hour+":"+minutes+" "+Am_Pm;
    }
}
