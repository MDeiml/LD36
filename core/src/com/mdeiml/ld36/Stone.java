package com.mdeiml.ld36;

import com.nilunder.bdx.*;

public class Stone extends GameObject {

    public void init() {
        children.get("G_Stone").components.add(new BillboardComponent(children.get("G_Stone")));
    }

}
