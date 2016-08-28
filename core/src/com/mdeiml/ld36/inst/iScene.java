package com.mdeiml.ld36.inst;

import com.badlogic.gdx.utils.JsonValue;
import com.nilunder.bdx.Instantiator;import com.nilunder.bdx.GameObject;
import com.mdeiml.ld36.*;
public class iScene extends Instantiator {

	public GameObject newObject(JsonValue gobj){
		String name = gobj.name;

		if (gobj.get("class").asString().equals("Time"))
			return new com.mdeiml.ld36.Time();
		if (gobj.get("class").asString().equals("Splash"))
			return new com.mdeiml.ld36.Splash();
		if (gobj.get("class").asString().equals("Barrel"))
			return new com.mdeiml.ld36.Barrel();
		if (gobj.get("class").asString().equals("Wing"))
			return new com.mdeiml.ld36.Wing();
		if (gobj.get("class").asString().equals("Player"))
			return new com.mdeiml.ld36.Player();
		if (gobj.get("class").asString().equals("Stone"))
			return new com.mdeiml.ld36.Stone();
		if (gobj.get("class").asString().equals("FlameParticle"))
			return new com.mdeiml.ld36.FlameParticle();
		if (gobj.get("class").asString().equals("Level"))
			return new com.mdeiml.ld36.Level();
		if (gobj.get("class").asString().equals("Ground"))
			return new com.mdeiml.ld36.Ground();
		if (gobj.get("class").asString().equals("Wagon"))
			return new com.mdeiml.ld36.Wagon();

		return super.newObject(gobj);
	}
	
}
