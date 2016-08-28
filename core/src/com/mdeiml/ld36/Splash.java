package com.mdeiml.ld36;

import com.nilunder.bdx.*;

public class Splash extends GameObject {

    public void main() {
        for(GameObject go : touchingObjects) {
            WagonComponent wo = (WagonComponent)go.components.get("WagonComponent");
            if(wo != null) {
                wo.slow();
            }
        }
    }

}
