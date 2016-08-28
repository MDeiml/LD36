package com.mdeiml.ld36;

import com.nilunder.bdx.*;
import com.nilunder.bdx.components.SpriteAnim;

public class SpikeComponent extends Component {

    private GameObject sensor;

    public SpikeComponent(GameObject g) {
        super(g);
        sensor = g.scene.add("SpikeSensor");
        sensor.parent(g);
        state(off);
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

        public void enter() {
            GameObject gr = g.children.get("G_Player");
            if(gr == null) {
                gr = g.children.get("G_Wagon");
            }
            SpriteAnim sa = (SpriteAnim)gr.components.get("SpriteAnim");
            sa.play("spikes");
        }

        public void exit() {
            GameObject gr = g.children.get("G_Player");
            if(gr == null) {
                gr = g.children.get("G_Wagon");
            }
            SpriteAnim sa = (SpriteAnim)gr.components.get("SpriteAnim");
            sa.play("default");
        }

        public void main() {
            WagonComponent w = (WagonComponent)g.components.get("WagonComponent");
            w.slow(3.5f);
            for(GameObject go : sensor.touchingObjects) {
                WagonComponent wo = (WagonComponent)go.components.get("WagonComponent");
                if(go == g) {
                    continue;
                }
                if(wo != null) {
                    wo.slow(0);
                }
            }
        }
    };

}
