package com.mdeiml.ld36.inst;

import com.badlogic.gdx.utils.JsonValue;
import com.nilunder.bdx.Instantiator;import com.nilunder.bdx.GameObject;
import com.mdeiml.ld36.*;
public class iScene extends Instantiator {

	public GameObject newObject(JsonValue gobj){
		String name = gobj.name;

		if (gobj.get("class").asString().equals("Wagon"))
			return new com.mdeiml.ld36.Wagon();

		return super.newObject(gobj);
	}
	
}
