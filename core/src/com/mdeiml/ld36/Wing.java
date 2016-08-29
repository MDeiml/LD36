package com.mdeiml.ld36;

import com.nilunder.bdx.*;
import com.nilunder.bdx.components.SpriteAnim;

public class Wing extends GameObject {

    private SpriteAnim sa;

    public void init() {
        components.add(new BillboardComponent(this));
        sa = new SpriteAnim(this, 130, 40);
        sa.add("fly", 0, new int[]{0, 1, 2, 1}, 5, true);
        sa.add("default", 0, new int[]{0}, 1, true);
        components.add(sa);
        sa.play("default");
    }

    public boolean use() {
        if(!parent().touching("Ground")) {
            return false;
        }
        parent().applyForce(0,0,4/Bdx.TICK_TIME);
        return true;
    }

    public void main() {
        if(parent() == null) {
            end();
            return;
        }
        if(parent().touching("Ground"))
            sa.play("default");
        else
            sa.play("fly");
    }

}
