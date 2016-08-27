package com.mdeiml.ld36;

import com.nilunder.bdx.*;

public class Wagon extends GameObject {

    public void main() {
        children.get("G_Wagon").orientation(scene.camera.orientation());
    }

}
