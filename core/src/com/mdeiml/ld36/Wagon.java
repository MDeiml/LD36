package com.mdeiml.ld36;

import com.nilunder.bdx.*;
import java.util.ArrayList;
import javax.vecmath.*;

public class Wagon extends GameObject {

    public void main() {
        Vector3f velocity = velocity();
        float vel = (float)Math.sqrt(velocity.x*velocity.x+velocity.y*velocity.y);
        vel += Bdx.TICK_TIME+0.1f;
        vel = Math.min(10, vel);
        Vector3f forward = orientation().mult(new Vector3f(0,1,0));
        velocity(forward.x*vel,forward.y*vel,velocity().z);
        children.get("G_Wagon").orientation(scene.camera.orientation());
        children.get("Horse").orientation(scene.camera.orientation());
        float va = 0;
        rotate(0, 0, va*Bdx.TICK_TIME);
    }

}
