package com.mdeiml.ld36;

import com.nilunder.bdx.components.SpriteAnim;
import com.nilunder.bdx.utils.Timer;
import com.nilunder.bdx.*;
import javax.vecmath.*;

public class FlameParticle extends GameObject {

    private Timer timer;
    private Vector3f vel;

    public void init() {
        timer = new Timer(1);
        SpriteAnim anim = new SpriteAnim(this, 8, 8);
        anim.add("burn", (int)(Math.random()*2), new int[] {0,1,2,3}, 4, false);
        components.add(anim);
        anim.play("burn");
        components.add(new BillboardComponent(this));
    }

    public void setVel(Vector3f vel) {
        this.vel = vel;
    }

    public void main() {
        if(vel != null)
            move(vel.mul(Bdx.TICK_TIME));
        if(timer.done()) {
            end();
        }
    }

}
