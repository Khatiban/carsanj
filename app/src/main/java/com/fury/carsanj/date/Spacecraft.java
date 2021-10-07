package com.fury.carsanj.date;

import io.realm.RealmObject;

/**
 * Created by fury on 9/14/2017.
 */
public class Spacecraft extends RealmObject {

    String Date;
    String Text;
    int bak;
    int W;

    @Override
    public String toString() {
        return "Spacecraft{" +
                "Date='" + Date + '\'' +
                ", Text='" + Text + '\'' +
                ", bak=" + bak +
                ", W=" + W +
                '}';
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public int getBak() {
        return bak;
    }

    public void setBak(int bak) {
        this.bak = bak;
    }

    public int getW() {
        return W;
    }

    public void setW(int w) {
        W = w;
    }
}
