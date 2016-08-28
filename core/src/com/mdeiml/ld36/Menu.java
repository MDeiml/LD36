package com.mdeiml.ld36;

import com.nilunder.bdx.*;
import java.util.ArrayList;
import javax.vecmath.*;

public class Menu extends GameObject {

    private static final int[] prize = new int[] {3000,2000,1000,4000};

    public void on() {
        Bdx.mouse.visible(true);
        visible(true);
        scene.objects.get("MenuBackground").visible(true);
        scene.objects.get("Money").visible(true);
        scene.objects.get("Time").visible(false);
    }

    public void off() {
        Bdx.mouse.visible(false);
        visible(false);
        scene.objects.get("MenuBackground").visible(false);
        scene.objects.get("Money").visible(false);
        scene.objects.get("Time").visible(true);
    }

    public void main() {
        int money = ((Player)scene.objects.get("Player")).money;
        if(Bdx.mouse.btnHit("left")) {
            ArrayList<RayHit> hits = Bdx.mouse.xray();
            for(RayHit hit : hits) {
                String name = hit.object.name;
                if(name.startsWith("Button")) {
                    int i = Integer.parseInt(name.substring(6));
                    if(money >= prize[i]) {
                        scene.objects.get("Check"+i).visible(true);
                        money -= prize[i];
                    }
                }
            }
        }
        ((Text)scene.objects.get("Money")).set(String.format("%05d$", money));
        ((Player)scene.objects.get("Player")).money = money;
    }

}
