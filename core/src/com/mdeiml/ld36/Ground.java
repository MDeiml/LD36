package com.mdeiml.ld36;

import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nilunder.bdx.*;

public class Ground extends GameObject {

    private int id;

    public void setId(int id) {
        this.id = id;
        TextureAttribute ta = (TextureAttribute)modelInstance.materials.get(0).get(TextureAttribute.Diffuse);
        ta.set(new TextureRegion(new Texture("bdx/textures/Ground"+id+".png")));
    }

    public int getId() {
        return id;
    }

}
