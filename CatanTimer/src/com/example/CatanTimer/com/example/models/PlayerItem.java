package com.example.CatanTimer.com.example.models;

import com.example.CatanTimer.com.example.utils.TimeStat;

/**
 * Created by timi on 26.12.2016.
 */
public class PlayerItem {
    public String name;
    public TimeStat timeLeft;

    public PlayerItem(String name, TimeStat left) {
        this.name = name;
        this.timeLeft = left;
    }
}
