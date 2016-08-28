package com.mdeiml.ld36;

import com.nilunder.bdx.*;
import com.nilunder.bdx.components.SpriteAnim;
import java.util.ArrayList;
import javax.vecmath.*;

public class Player extends GameObject {

    private GameObject cameraCross;
    private float camRot;
    private Level level;

    public void init() {
        cameraCross = children.get("CameraCross");
        camRot = 0;
        components.add(new WagonComponent(this));
        components.add(new FlameThrower(this));
        components.add(new SpikeComponent(this));
        GameObject gPlayer = children.get("G_Player");
        gPlayer.components.add(new BillboardComponent(gPlayer));
        SpriteAnim playerAnim = new SpriteAnim(gPlayer, 85, 85);
        playerAnim.add("default", 0, new int[]{0, 1}, 5, true);
        playerAnim.add("spikes", 1, new int[]{0, 1}, 5, true);
        gPlayer.components.add(playerAnim);
        playerAnim.play("default");
        GameObject horse = children.get("PHorse");
        horse.components.add(new BillboardComponent(horse));
        SpriteAnim horseAnim = new SpriteAnim(horse, 101, 101);
        horseAnim.add("default", 0, new int[]{0, 1}, 5, true);
        horse.components.add(horseAnim);
        horseAnim.play("default");
        GameObject wing = scene.objects.get("Wing");
        wing.parent(this);
    }

    public void main() {
        //Controls
        if(Bdx.keyboard.keyHit("esc")) {
            Bdx.end();
        }
        if(components.get("FlameThrower") != null) {
            if(Bdx.keyboard.keyHit("h")) {
                ((FlameThrower)components.get("FlameThrower")).toggle(true);
            }
            if(Bdx.keyboard.keyUp("h")) {
                ((FlameThrower)components.get("FlameThrower")).toggle(false);
            }
        }
        if(components.get("SpikeComponent") != null) {
            if(Bdx.keyboard.keyHit("j")) {
                ((SpikeComponent)components.get("SpikeComponent")).toggle(true);
            }
            if(Bdx.keyboard.keyUp("j")) {
                ((SpikeComponent)components.get("SpikeComponent")).toggle(false);
            }
        }
        if(children.get("Wing") != null) {
            if(Bdx.keyboard.keyHit("space")) {
                ((Wing)children.get("Wing")).use();
            }
        }

        if(touching("Ground")) {
            float va = 0;
            if(Bdx.keyboard.keyDown("a")) {
                va += 2;
            }
            if(Bdx.keyboard.keyDown("d")) {
                va -= 2;
            }
            rotate(0, 0, va*Bdx.TICK_TIME);
            cameraCross.rotate(0,0,-va*Bdx.TICK_TIME);
            camRot += va*Bdx.TICK_TIME;
            if(camRot > 0.2f) {
                cameraCross.rotate(0,0,camRot-0.2f);
                camRot = 0.2f;
            }
            if(camRot < -0.2f) {
                cameraCross.rotate(0,0,camRot+0.2f);
                camRot = -0.2f;
            }
            if(camRot > 0) {
                float rot = Math.min(camRot,0.2f*Bdx.TICK_TIME);
                cameraCross.rotate(0,0,rot);
                camRot -= rot;
            }
            if(camRot < 0) {
                float rot = Math.max(camRot,-0.2f*Bdx.TICK_TIME);
                cameraCross.rotate(0,0,rot);
                camRot -= rot;
            }
        }
    }

}
