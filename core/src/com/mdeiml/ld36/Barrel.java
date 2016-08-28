package com.mdeiml.ld36;

import com.nilunder.bdx.*;

public class Barrel extends GameObject {

    public void init() {
        children.get("G_Barrel").components.add(new BillboardComponent(children.get("G_Barrel")));
    }

}
