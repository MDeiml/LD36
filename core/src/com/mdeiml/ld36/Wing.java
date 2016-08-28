package com.mdeiml.ld36;

import com.nilunder.bdx.*;
import com.nilunder.bdx.components.SpriteAnim;

public class Wing extends GameObject {

    public void init() {
        components.add(new BillboardComponent(this));
        SpriteAnim sa = new SpriteAnim(this, 130, 40);
        sa.add("fly", 0, new int[]{0, 1, 2, 1}, 5, true);
        components.add(sa);
        sa.play("fly");
    }

    public void use() {
        if(!parent().touching("Ground"))
            return;
        parent().applyForce(0,0,4/Bdx.TICK_TIME);
    }

    public void main() {

    }

}
