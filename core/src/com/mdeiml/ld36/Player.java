package com.mdeiml.ld36;

import com.nilunder.bdx.*;
import java.util.ArrayList;
import javax.vecmath.*;

public class Player extends GameObject {

    private ArrayList<GameObject> grounds;
    private int gMin;
    private int gMax;
    private GameObject cameraCross;
    private float camRot;

    public void init() {
        grounds = new ArrayList<GameObject>();
        gMin = 0;
        gMax = 0;
        cameraCross = scene.objects.get("CameraCross");
        camRot = 0;
    }

    public void main() {
        Vector3f velocity = velocity();
        float vel = (float)Math.sqrt(velocity.x*velocity.x+velocity.y*velocity.y);
        vel += Bdx.TICK_TIME+0.1f;
        vel = Math.min(10, vel);
        Vector3f forward = orientation().mult(new Vector3f(0,1,0));
        velocity(forward.x*vel,forward.y*vel,velocity().z);
        children.get("G_Player").orientation(scene.camera.orientation());
        children.get("PHorse").orientation(scene.camera.orientation());
        float va = 0;
        if(Bdx.keyboard.keyDown("a")) {
            va += 2;
        }
        if(Bdx.keyboard.keyDown("d")) {
            va -= 2;
        }
        rotate(0, 0, va*Bdx.TICK_TIME);
        cameraCross.rotate(0,0,-va*Bdx.TICK_TIME);
        camRot += va*Bdx.TICK_TIME;
        if(camRot > 0.2f) {
            cameraCross.rotate(0,0,camRot-0.2f);
            camRot = 0.2f;
        }
        if(camRot < -0.2f) {
            cameraCross.rotate(0,0,camRot+0.2f);
            camRot = -0.2f;
        }
        if(camRot > 0) {
            float rot = Math.min(camRot,0.2f*Bdx.TICK_TIME);
            cameraCross.rotate(0,0,rot);
            camRot -= rot;
        }
        if(camRot < 0) {
            float rot = Math.max(camRot,-0.2f*Bdx.TICK_TIME);
            cameraCross.rotate(0,0,rot);
            camRot -= rot;
        }
        // if(position().y > gMax * 10 - 15) {
        //     GameObject ground = scene.add("Ground");
        //     ground.position(0,gMax*10, 0);
        //     grounds.add(ground);
        //     gMax++;
        // }
    }

}
