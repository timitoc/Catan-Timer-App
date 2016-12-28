package com.example.CatanTimer.com.example.utils;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by timi on 26.12.2016 .
 */
public class Global {

    private static Random random;
    private static int[] colors = {Color.rgb(255, 255, 0), Color.rgb(0, 255, 0), Color.rgb(0, 0, 255), Color.rgb(0, 0, 0), Color.rgb(0, 0, 0), Color.rgb(0, 0, 0)};

    /// [lo, hi]
    public static int getRand(int lo, int hi) {
        if (random == null)
            random = new Random();
        return random.nextInt(hi - lo + 1) + lo;
    }

    public static int getRandColor() {
        return  colors[getRand(0, 5)];
    }

}
