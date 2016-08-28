package com.mdeiml.ld36;

import com.nilunder.bdx.*;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.Gdx;

public class Level extends GameObject {

    private static final int[] colors = new int[] {0xfd0101ff, 0x180891ff, 0x098d1bff, 0xea11d1ff, 0xe96b10ff, 0x07c5cdff, 0xfaf714ff, 0x9ea214ff};

    private Ground[][] grounds;
    private int startX = -1;
    private int startY;

    @Override
    public void init() {
        Pixmap pixmap = new Pixmap(Gdx.files.internal("bdx/textures/track.png"));
        grounds = new Ground[pixmap.getWidth()][pixmap.getHeight()];
        for(int x = 0; x < pixmap.getWidth(); x++) {
            for(int y = 0; y < pixmap.getHeight(); y++) {
                int color = pixmap.getPixel(x, y);
                int i;
                for(i = 0; i < colors.length; i++) {
                    if(color == colors[i]) {
                        break;
                    }
                }
                GameObject go = scene.add("Ground");
                go.position(x*10, y*10, 0);
                ((Ground)go).setId(i);
                if(i == 7) {
                    startX = x;
                    startY = y;
                    scene.objects.get("Player").position(x*10, (y-1.5f)*10, 0.25f);
                    scene.objects.get("Stages").position(x*10, y*10, 0);
                }
                if(i != 6 && i != 7) {
                    int num = (int)(Math.random()*3);
                    for(int j = 0; j < num; j++) {
                        GameObject obstacle = null;
                        int rdm = (int)(Math.random()*4);
                        if(rdm == 0) {
                            obstacle = scene.add("Barrel");
                        }else if(rdm == 1) {
                            obstacle = scene.add("Fence");
                            if(i == 3) {
                                obstacle.rotate(0,0,(float)(Math.PI/2));
                            }
                        }else if(rdm == 2) {
                            obstacle = scene.add("Splash");
                        }else if(rdm == 3) {
                            obstacle = scene.add("Stone");
                        }
                        obstacle.position((x-0.25f+(float)Math.random()*0.5f)*10, (y-0.25f+(float)Math.random()*0.5f)*10, obstacle.position().z);
                    }
                }
                grounds[x][y] = (Ground)go;
            }
        }
        for(int i = 1; i < 6; i++) {
            Wagon w = (Wagon)scene.add("Wagon");
            w.setStartPos(i);
        }
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public Ground getGround(int x, int y) {
        return grounds[x][y];
    }

}
