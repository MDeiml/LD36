package com.mdeiml.ld36;

import com.nilunder.bdx.*;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.Gdx;

public class Level extends GameObject {

    private static final int[] colors = new int[] {0xfd0101ff, 0x180891ff, 0x098d1bff, 0xea11d1ff, 0xe96b10ff, 0x07c5cdff};

    @Override
    public void init() {
        System.out.println("test");
        Pixmap pixmap = new Pixmap(Gdx.files.internal("bdx/textures/track.png"));
        for(int x = 0; x < pixmap.getWidth(); x++) {
            for(int y = 0; y < pixmap.getHeight(); y++) {
                int color = pixmap.getPixel(x, y);
                int i;
                System.out.println(color >> 24);
                for(i = 0; i < colors.length; i++) {
                    if(color == colors[i]) {
                        break;
                    }
                }
                GameObject go = scene.add("Ground");
                go.position(x*10, y*10, 0);
                ((Ground)go).setId(i);
                if(id == 7) {
                    scene.objects.get("Player").position(x, y, 0.5f);
                }
            }
        }
    }

}
