package com.mdeiml.ld36;

import com.nilunder.bdx.*;
import com.nilunder.bdx.components.SpriteAnim;
import javax.vecmath.*;

public class WagonComponent extends Component {

    private float extSlow;
    private boolean check;
    private int lap;
    private GameObject horse;
    private boolean unicorn;

    public WagonComponent(GameObject g, GameObject horse) {
        super(g);
        state(def);
        extSlow = 100;
        check = false;
        lap = 0;
        this.horse = horse;
    }

    public boolean isUnicorn() {
        return unicorn;
    }

    public void setUnicorn(boolean u) {
        this.unicorn = u;
        if(u) {
            ((SpriteAnim)horse.components.get("SpriteAnim")).play("unicorn");
        }else {
            ((SpriteAnim)horse.components.get("SpriteAnim")).play("default");
        }
    }

    public void slow() {
        slow(1.5f);
    }

    public void slow(float s) {
        extSlow = Math.min(extSlow, s);
    }

    private State def = new State() {
        public void main() {
            if(g.scene.objects.get("Menu").visible())
                return;
            Level level = (Level)g.scene.objects.get("Level");
            if(level == null)
                return;
            Vector3f velocity = g.velocity();
            float vel = (float)Math.sqrt(velocity.x*velocity.x+velocity.y*velocity.y);
            vel += Bdx.TICK_TIME+0.1f;
            vel = Math.min(unicorn ? 6 : 5, vel);

            //Ground
            float x1 = (g.position().x+5)/10;
            float y1 = (g.position().y+5)/10;
            int x = (int)x1;
            int y = (int)y1;
            if(x == level.getCheckX() && y == level.getCheckY()) {
                check = true;
            }
            x1 -= x;
            y1 -= y;
            Ground ground = level.getGround(x, y);
            boolean slow = false;
            switch(ground.getId()) {
                case 7:
                    if(check) {
                        lap++;
                        if(lap >= 1) {
                            int nr = level.finish();
                            if(g.name.equals("Player")) {
                                ((Menu)g.scene.objects.get("Menu")).on();
                                switch(nr) {
                                    case 1:
                                        ((Player)g).money += 2000;
                                        break;
                                    case 2:
                                        ((Player)g).money += 1000;
                                        break;
                                    case 3:
                                        ((Player)g).money += 750;
                                        break;
                                }
                            }
                        }
                        check = false;
                    }
                case 0:
                    slow = x1 < 0.25f || x1 > 0.75f;
                    break;
                case 1:
                    slow = (x1 < 0.25f && y1 > 0.75f) || x1 > 0.75f || y1 < 0.25f;
                    break;
                case 2:
                    slow = (x1 > 0.75f && y1 < 0.25f) || x1 < 0.25f || y1 > 0.75f;
                    break;
                case 3:
                    slow = y1 < 0.25f || y1 > 0.75f;
                    break;
                case 4:
                    slow = (x1 > 0.75f && y1 > 0.75f) || x1 < 0.25f || y1 < 0.25f;
                    break;
                case 5:
                    slow = (x1 < 0.25f && y1 < 0.25f) || x1 > 0.75f || y1 > 0.75f;
                    break;
                case 6:
                    slow = true;
                    break;
            }
            if(slow && g.touching("Ground")) {
                vel = Math.min(vel, 1.5f);
            }
            vel = Math.min(extSlow, vel);

            Vector3f forward = g.orientation().mult(new Vector3f(0,1,0));
            g.velocity(forward.x*vel,forward.y*vel,g.velocity().z);
            extSlow = 100;
        }
    };

}
