package com.mdeiml.ld36;

import com.nilunder.bdx.*;
import java.util.ArrayList;
import javax.vecmath.*;

public class Menu extends GameObject {

    private static final int[] prize = new int[] {3000,2000,1000,4000};

    public void init() {
        on();
    }

    public void on() {
        Bdx.mouse.visible(true);
        visible(true);
        scene.objects.get("MenuBackground").visible(true);
        scene.objects.get("Money").visible(true);
        scene.objects.get("Time").visible(false);
        GameObject player = scene.objects.get("Player");
        if(player.components.get("FlameThrower") != null) {
            scene.objects.get("Check0").visible(true);
        }
        if(player.children.get("Wing") != null) {
            scene.objects.get("Check1").visible(true);
        }
        if(player.components.get("SpikeComponent") != null) {
            scene.objects.get("Check2").visible(true);
        }
        if(((WagonComponent)player.components.get("WagonComponent")).isUnicorn()) {
            scene.objects.get("Check3").visible(true);
        }
        player.position(2, 0, 0.25f);
        Matrix3f ori = new Matrix3f();
        ori.setIdentity();
        player.orientation(ori);
        player.dynamics(false);
        for(GameObject o : scene.objects) {
            if(o.name.equals("Wagon")) {
                o.end();
            }
        }
        scene.objects.get("Level").end();
    }

    public void off() {
        Bdx.mouse.visible(false);
        visible(false);
        scene.objects.get("MenuBackground").visible(false);
        scene.objects.get("Money").visible(false);
        scene.objects.get("Time").visible(true);
        for(int i = 0; i < 4; i++) {
            scene.objects.get("Check"+i).visible(false);
        }
        for(GameObject o : scene.objects) {
            if(o.name.equals("Wagon")) {
                o.end();
            }
        }
        scene.add("Level");
        scene.objects.get("Player").dynamics(true);
    }

    public void main() {
        if(!visible())
            return;
        int money = ((Player)scene.objects.get("Player")).money;
        if(Bdx.mouse.btnHit("left")) {
            ArrayList<RayHit> hits = Bdx.mouse.xray();
            for(RayHit hit : hits) {
                String name = hit.object.name;
                if(name.startsWith("Button")) {
                    int i = Integer.parseInt(name.substring(6));
                    if(money >= prize[i] && !scene.objects.get("Check"+i).visible()) {
                        scene.objects.get("Check"+i).visible(true);
                        money -= prize[i];
                        Player player = (Player)(scene.objects.get("Player"));
                        switch(i) {
                            case 0:
                                player.components.add(new FlameThrower(player));
                                break;
                            case 1:
                                GameObject wing = scene.add("Wing");
                                wing.parent(player);
                                break;
                            case 2:
                                player.components.add(new SpikeComponent(player));
                                break;
                            case 3:
                                ((WagonComponent)player.components.get("WagonComponent")).setUnicorn(true);
                                break;
                        }
                    }
                }
                if(name.equals("Play")) {
                    off();
                }
            }
        }
        ((Text)scene.objects.get("Money")).set(String.format("%05d$", money));
        ((Player)scene.objects.get("Player")).money = money;
    }

}
