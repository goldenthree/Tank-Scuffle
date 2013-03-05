package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class TankManager extends SpriteManager {
		
	
	
		public TankManager() {
		super();
		IsTank = true;
		// TODO Auto-generated constructor stub
	}

		public void CreateTank(World world, float x, float y, float xVelocity, float yVelocity){
			
			

			// First we create a body definition
			BodyDef bodyDef = new BodyDef();
			// We set our body to dynamic, for something like ground which doesnt move we would set it to StaticBody
			bodyDef.type = BodyType.DynamicBody;
			// Set our body's starting position in the world
			bodyDef.position.set(x, y);

			// Create our body in the world using our body definition
			Body body = world.createBody(bodyDef);

			// Create a circle shape and set its radius to 6
			PolygonShape shape = new PolygonShape();
			shape.setAsBox(.06f, .04f);

			// Create a fixture definition to apply our shape to
			FixtureDef fixtureDef = new FixtureDef();
			fixtureDef.shape = shape;
			fixtureDef.density = 0.5f; 
			fixtureDef.friction = 0.4f;
			fixtureDef.restitution = 0f; // Make it bounce a little bit

			// Create our fixture and attach it to the body
			Fixture fixture = body.createFixture(fixtureDef);

			
			
			
			
			Texture tankTexture =  new Texture(Gdx.files.internal("data/Tank.png"));
			tankTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			
			TextureRegion tankRegion = new TextureRegion(tankTexture);
			
			Sprite tankSprite = new Sprite(tankRegion);
			tankSprite.setSize(0.25f, 0.25f * tankSprite.getHeight() / tankSprite.getWidth());
			tankSprite.setOrigin(-tankSprite.getRegionWidth(),-tankSprite.getHeight());
			
			SpriteWrapper tank = new SpriteWrapper(body,tankSprite, tankTexture);
			
			body.setLinearVelocity(xVelocity*20,yVelocity*20);
			
			
			sprites.add(tank);
			
		}
}
