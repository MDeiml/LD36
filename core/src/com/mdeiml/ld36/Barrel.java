package com.mdeiml.ld36;

import com.nilunder.bdx.*;

public class Barrel extends GameObject {

    public void main() {
        children.get("G_Barrel").orientation(scene.camera.orientation());
        GameObject wagon = touchingObjects.get("Wagon");
        if(wagon != null) {
            //TEOSLKJDGPJM;:w
            
        }
    }

}
