package com.mdeiml.ld36;

import com.nilunder.bdx.*;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.Gdx;

public class Level extends GameObject {

    private static final int[] colors = new int[] {0xfd0101ff, 0x180891ff, 0x098d1bff, 0xea11d1ff, 0xe96b10ff, 0x07c5cdff, 0xfaf714ff, 0x9ea214ff, 0xd4c5b4ff, 0xab8f6dff, 0x700686ff};

    private Ground[][] grounds;
    private int startX = -1;
    private int startY;
    private int checkX;
    private int checkY;
    private int finished;

    @Override
    public void init() {
        if(scene.objects.get("Menu").visible())
            return;
        Gdx.gl.glClearColor(0.6f,0.7f,1,1);
        Pixmap pixmap = new Pixmap(Gdx.files.internal("bdx/textures/track"+((int)(Math.random()*3))+".png"));
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
                if(i == 6) {
                    int num = (int)(Math.random()*4);
                    for(int j = 0; j < num; j++) {
                        GameObject bush = scene.add("Bush");
                        bush.position((x+(float)Math.random())*10-5,(y+(float)Math.random())*10-5,bush.position().z);
                        GameObject gb = bush.children.get("G_Bush");
                        gb.components.add(new BillboardComponent(gb));
                    }
                }
                if(i == 8) {
                    i = 6;
                    GameObject gob = scene.add("Theatre");
                    gob.position(x*10,y*10,4);
                }
                if(i == 9) {
                    i = 6;
                    GameObject gob = scene.add("Pillar");
                    gob.position(x*10,y*10,1.5f);
                }
                if(i == 10) {
                    checkX = x;
                    checkY = y;
                    i = 0;
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
        for(int i = 0; i < 6; i++) {
            Wagon w = (Wagon)scene.add("Wagon");
            int startPosY = i/2;
            int startPosX = i%2;
            w.position(startX*10+(startPosX == 0 ? -2 : +2), (startY-startPosY*0.5f)*10, 0.25f);
        }
        for(int i = 0; i < pixmap.getWidth(); i++) {
            GameObject wall = scene.add("Wall");
            wall.position(i*10, -5, 5);
            wall.rotate(0, 0, (float)(Math.PI));
            wall = scene.add("Wall");
            wall.position(i*10, pixmap.getHeight()*10-5, 5);
        }
        for(int i = 0; i < pixmap.getHeight(); i++) {
            GameObject wall = scene.add("Wall");
            wall.position(-5, i*10, 5);
            wall.rotate(0, 0, (float)(Math.PI/2));
            wall = scene.add("Wall");
            wall.position(pixmap.getWidth()*10-5, i*10, 5);
            wall.rotate(0, 0, -(float)(Math.PI/2));
        }
    }

    public int finish() {
        finished++;
        return finished;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getCheckX() {
        return checkX;
    }

    public int getCheckY() {
        return checkY;
    }

    public Ground getGround(int x, int y) {
        return grounds[x][y];
    }

}
