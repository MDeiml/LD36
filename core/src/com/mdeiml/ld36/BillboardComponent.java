package com.mdeiml.ld36;

import com.nilunder.bdx.*;
import java.util.ArrayList;
import javax.vecmath.*;

public class BillboardComponent extends Component {

    public BillboardComponent(GameObject parent) {
        super(parent);
        state(def);
    }

    private State def = new State() {
        public void main() {
            g.orientation(g.scene.camera.orientation());
        }
    };
}
