package com.mdeiml.ld36;

import com.nilunder.bdx.*;
import javax.vecmath.*;

public class FlameThrower extends Component {

    private GameObject sensor;

    public FlameThrower(GameObject g) {
        super(g);
        state(off);
        sensor = g.scene.add("FlameThrowerSensor");
        sensor.parent(g);
    }

    public void toggle() {
        toggle(state == off);
    }

    public void toggle(boolean b) {
        if(b) {
            state(on);
        }else {
            state(off);
        }
    }

    private State off = new State() {
        public void main() {

        }
    };


    private State on = new State() {

        private static final int PPS = 30;
        private static final float PT = 1f/PPS;

        private float acc = 0;

        public void enter() {
            Bdx.sounds.get("Fire").play(1f/g.position().minus(g.scene.camera.position()).length());
        }

        public void exit() {
            Bdx.sounds.get("Fire").stop();
        }

        public void main() {
            ((WagonComponent)g.components.get("WagonComponent")).slow(2.5f);
            Vector3f forward = g.orientation().mult(new Vector3f(0,3,0));
            Vector3f vel = forward.plus(g.velocity());
            acc += Bdx.TICK_TIME;
            while(acc >= PT) {
                acc -= PT;
                FlameParticle particle = (FlameParticle)g.scene.add("FlameParticle");
                particle.position(g.position().plus(forward.mul(0.25f)));
                particle.setVel(new Vector3f((float)Math.random()*2-1+vel.x,
                                             (float)Math.random()*2-1+vel.y,
                                             (float)Math.random()*2-1+vel.z));
            }
            for(GameObject go : sensor.touchingObjects) {
                if(go == g) {
                    continue;
                }
                WagonComponent wc = (WagonComponent)go.components.get("WagonComponent");
                if(wc != null) {
                    wc.slow(0);
                }
            }
        }
    };

}
