package com.mdeiml.ld36;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

public class LD36Game extends ApplicationAdapter {

	private Texture floorTex;
	private Texture wagonTex;
	private PerspectiveCamera cam;
	private CameraInputController controller;
	private Model floor;
	private ModelInstance floorInstance;
	private Model wagon;
	private ModelInstance wagonInstance;
	private ModelBatch modelBatch;

	private float yRot;
	private float xRot;
	private Vector3 pos;

	@Override
	public void create() {
		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(0, 1, 3);
		cam.lookAt(0, 1, 2);
		cam.near = 0.1f;
		cam.far = 300;
		cam.update();
		controller = new CameraInputController(cam);
		// Gdx.input.setInputProcessor(controller);
		ModelBuilder modelBuilder = new ModelBuilder();
		wagonTex = new Texture("Wagen.png");
		floorTex = new Texture("Ground 1.jpg.png");
		floor = modelBuilder.createRect( 5, 0, -5,
										-5, 0, -5,
		 								-5, 0,  5,
										 5, 0,  5,
										 0, 1,  0,
										new Material(TextureAttribute.createDiffuse(floorTex)), Usage.Position | Usage.TextureCoordinates);
		floorInstance = new ModelInstance(floor);
		wagon = modelBuilder.createRect(-1, 0, 0,
										 1, 0, 0,
		 								 1, 2, 0,
										-1, 2, 0,
										 0, 0, 1,
										new Material(TextureAttribute.createDiffuse(wagonTex), new BlendingAttribute()), Usage.Position | Usage.TextureCoordinates);
		wagonInstance = new ModelInstance(wagon);
		yRot = 0;
		xRot = -20;
		modelBatch = new ModelBatch();
	}

	@Override
	public void render() {
		// controller.update();
		wagonInstance.transform.setToRotation(new Vector3(0,1,0), yRot);
		wagonInstance.transform.rotate(new Vector3(1,0,0), xRot);
		cam.direction.set(0,0,-1);
		cam.up.set(0,1,0);
		cam.rotate(new Vector3(0,1,0), yRot);
		cam.rotate(new Vector3(1,0,0), xRot);
		cam.update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		modelBatch.begin(cam);
		modelBatch.render(floorInstance);
		modelBatch.render(wagonInstance);
		modelBatch.end();
	}

	@Override
	public void dispose() {
		floor.dispose();
		modelBatch.dispose();
	}
}
