package com.me.mygdxgame;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;



public class BallManager {
	public ArrayList<SpriteWrapper> balls = new ArrayList<SpriteWrapper>();
	
	
	public void CreateBall(World world, float x, float y, float xVelocity, float yVelocity){
		
		

		// First we create a body definition
		BodyDef bodyDef = new BodyDef();
		// We set our body to dynamic, for something like ground which doesnt move we would set it to StaticBody
		bodyDef.type = BodyType.DynamicBody;
		// Set our body's starting position in the world
		bodyDef.position.set(x, y);

		// Create our body in the world using our body definition
		Body body = world.createBody(bodyDef);

		// Create a circle shape and set its radius to 6
		CircleShape circle = new CircleShape();
		circle.setRadius(.02f);

		// Create a fixture definition to apply our shape to
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 0.5f; 
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.9f; // Make it bounce a little bit

		// Create our fixture and attach it to the body
		Fixture fixture = body.createFixture(fixtureDef);

		
		
		
		
		Texture ballTexture =  new Texture(Gdx.files.internal("data/Ball.png"));
		ballTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureRegion ballRegion = new TextureRegion(ballTexture);
		
		Sprite ballSprite = new Sprite(ballRegion);
		ballSprite.setSize(0.1f, 0.1f * ballSprite.getHeight() / ballSprite.getWidth());
		ballSprite.setOrigin(-ballSprite.getRegionWidth(),-ballSprite.getHeight());
		
		SpriteWrapper ball = new SpriteWrapper(body,ballSprite, ballTexture);
		
		body.setLinearVelocity(xVelocity*20,yVelocity*20);
		
		
		ball.xVelocity=xVelocity;
		ball.yVelocity=yVelocity;
		balls.add(ball);
		
	}
	

	public void updateBalls()
	{
		for (SpriteWrapper ball : balls) {
			ball.Update();
		}
	}
	
	public void draw(SpriteBatch spriteBatch)
	{

		for (SpriteWrapper ball : balls) {
			ball.mySprite.draw(spriteBatch);
		}
		
	}
	
}
