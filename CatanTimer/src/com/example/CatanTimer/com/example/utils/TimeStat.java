package com.example.CatanTimer.com.example.utils;

import java.sql.Time;

/**
 * Created by timi on 26.12.2016.
 */
public class TimeStat {
    public int h, m, s;
    public TimeStat(int h, int m, int s) {
        this.h = h;
        this.m = m;
        this.s = s;
    }
    public TimeStat() {
        this(0, 0, 0);
    }

    /*
        Returns false if time reached 00:00:00
    */
    public boolean decrement() {
        s--;
        if (s < 0) {
            s += 60;
            m--;
            if (m < 0) {
                m += 60;
                h--;
                if (h < 0) {
                    s = m = h = 0;
                    return false;
                }
            }
        }
        return  true;
    }

    public TimeStat getCopy()
    {
        return new TimeStat(this.h, this.m, this.s);
    }

    @Override
    public String toString() {
        StringBuilder t = new StringBuilder();
        if (h < 10)
            t.append(0);
        t.append(h);
        t.append(':');
        if (m < 10)
            t.append(0);
        t.append(m);
        t.append(':');
        if (s < 10)
            t.append(0);
        t.append(s);
        return  t.toString();
    }
}
