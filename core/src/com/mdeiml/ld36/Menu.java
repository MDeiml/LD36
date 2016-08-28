package com.mdeiml.ld36;

import com.nilunder.bdx.*;
import java.util.ArrayList;
import javax.vecmath.*;

public class Menu extends GameObject {

    public void init() {
        Bdx.mouse.visible(true);
    }

    public void main() {
        if(Bdx.mouse.btnHit("left")) {
            ArrayList<RayHit> hits = Bdx.mouse.xray();
            for(RayHit hit : hits) {
                String name = hit.object.name;
                if(name.equals("Button0")) {
                    System.out.println(0);
                }
                if(name.equals("Button1")) {
                    System.out.println(1);
                }
                if(name.equals("Button2")) {
                    System.out.println(2);
                }
                if(name.equals("Button3")) {
                    System.out.println(3);
                }
            }
        }
    }

}
