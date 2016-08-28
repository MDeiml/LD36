package com.mdeiml.ld36;

import com.mdeiml.ld36.WagonComponent;
import com.nilunder.bdx.*;
import com.nilunder.bdx.components.SpriteAnim;
import java.util.ArrayList;
import javax.vecmath.*;

public class Wagon extends GameObject {

    private int targetX;
    private int targetY;
    private int lastTargetX;
    private int lastTargetY;
    private Level level;
    private boolean onStart;
    private int turnMode;
    private float turnTimer;
    private float fireCooldown;

    public void init() {
        components.add(new WagonComponent(this));
        level = (Level)scene.objects.get("Level");
        onStart = false;
        GameObject wagon = children.get("G_Wagon");
        wagon.components.add(new BillboardComponent(wagon));
        SpriteAnim wagonAnim = new SpriteAnim(wagon, 85, 85);
        wagonAnim.add("default", 0, new int[]{0, 1}, 5, true);
        wagon.components.add(wagonAnim);
        wagonAnim.play("default");
        GameObject horse = children.get("Horse");
        horse.components.add(new BillboardComponent(horse));
        SpriteAnim horseAnim = new SpriteAnim(horse, 101, 101);
        horseAnim.add("default", 0, new int[]{0, 1}, 5, true);
        horse.components.add(horseAnim);
        horseAnim.play("default");
        turnMode = 0;
        turnTimer = 0;

        components.add(new FlameThrower(this));
        fireCooldown = (float)(Math.random()*5+5);
    }

    public void main() {
        //Extras
        if(components.get("FlameThrower") != null) {
            GameObject sensor = children.get("FlameThrowerSensor");
            boolean shoot = false;
            for(GameObject go : sensor.touchingObjects) {
                if(go == this) {
                    continue;
                }
                WagonComponent wc = (WagonComponent)go.components.get("WagonComponent");
                if(wc != null) {
                    shoot = true;
                }
            }
            if(fireCooldown <= 0 && shoot) {
                ((FlameThrower)components.get("FlameThrower")).toggle(true);
            }else {
                ((FlameThrower)components.get("FlameThrower")).toggle(false);
            }
            fireCooldown -= Bdx.TICK_TIME;
            if(fireCooldown <= -1) {
                System.out.println("a");
                fireCooldown = 10;
            }
        }else {
            if(fireCooldown < 0) {
                fireCooldown = -10*fireCooldown;
            }
        }
        if(fireCooldown > 0) {
            fireCooldown = Math.max(0, fireCooldown-Bdx.TICK_TIME);
        }

        if(!onStart && level.getStartX() != -1) {
            targetX = level.getStartX();
            targetY = level.getStartY();
            lastTargetX = targetX;
            lastTargetY = targetY-1;
            onStart = true;
        }
        int x = (int)((position().x+5)/10);
        int y = (int)((position().y+5)/10);
        if(x == targetX && y == targetY) {
            switch(level.getGround(x, y).getId()) {
                case 7:
                case 0:
                    if(lastTargetY == targetY-1) {
                        targetY++;
                    }else {
                        targetY--;
                    }
                    break;
                case 1:
                    if(lastTargetY == targetY+1) {
                        targetX--;
                    }else {
                        targetY++;
                    }
                    break;
                case 2:
                    if(lastTargetY == targetY-1) {
                        targetX++;
                    }else {
                        targetY--;
                    }
                    break;
                case 3:
                    if(lastTargetX == targetX-1) {
                        targetX++;
                    }else {
                        targetX--;
                    }
                    break;
                case 4:
                    if(lastTargetY == targetY+1) {
                        targetX++;
                    }else {
                        targetY++;
                    }
                    break;
                case 5:
                    if(lastTargetY == targetY-1) {
                        targetX--;
                    }else {
                        targetY--;
                    }
                    break;
            }
            lastTargetY = y;
            lastTargetX = x;
        }
        if(velocity().length() < 1 && (touching("Barrel") || touching("Fence") || touching("Stone") || touching("Player") || touching("Wagon"))) {
            if(turnMode == 0) {
                turnMode = Math.random() > 0.5 ? -1 : 1;
            }
            rotate(0, 0, turnMode*2*Bdx.TICK_TIME);
            turnTimer = 0.2f;
        }else {
            turnTimer = Math.max(0, turnTimer - Bdx.TICK_TIME);
            if(turnTimer == 0)
                turnMode = 0;
            Vector3f dir = new Vector3f((lastTargetX + targetX) * 5, (lastTargetY + targetY) * 5, 0.5f).minus(position());
            dir.normalize();
            Vector3f forward = orientation().mult(new Vector3f(0,1,0));
            forward.normalize();
            dir.cross(dir, forward);
            float f = dir.z;
            if(f < -0.05f) {
                rotate(0, 0, 2*Bdx.TICK_TIME);
            }else if(f > 0.05f) {
                rotate(0, 0, -2*Bdx.TICK_TIME);
            }
        }
    }

}
