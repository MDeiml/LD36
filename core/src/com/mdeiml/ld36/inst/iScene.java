package com.mdeiml.ld36.inst;

import com.badlogic.gdx.utils.JsonValue;
import com.nilunder.bdx.Instantiator;import com.nilunder.bdx.GameObject;
import com.mdeiml.ld36.*;
public class iScene extends Instantiator {

	public GameObject newObject(JsonValue gobj){
		String name = gobj.name;

		if (gobj.get("class").asString().equals("Level"))
			return new com.mdeiml.ld36.Level();
		if (gobj.get("class").asString().equals("Player"))
			return new com.mdeiml.ld36.Player();
		if (gobj.get("class").asString().equals("Barrel"))
			return new com.mdeiml.ld36.Barrel();
		if (gobj.get("class").asString().equals("Ground"))
			return new com.mdeiml.ld36.Ground();
		if (gobj.get("class").asString().equals("Wagon"))
			return new com.mdeiml.ld36.Wagon();

		return super.newObject(gobj);
	}
	
}
