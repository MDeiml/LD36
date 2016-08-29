package com.mdeiml.ld36;

import com.nilunder.bdx.*;

public class Time extends Text {

    private float time;

    public void init() {
        time = 0;
    }

    public void reset() {
        time = 0;
    }

    public void main() {
        time += Bdx.TICK_TIME;
        int sek = (int)time;
        int min = sek/60;
        sek = sek%60;
        set(String.format("%d:%02d", min, sek));
    }

}
