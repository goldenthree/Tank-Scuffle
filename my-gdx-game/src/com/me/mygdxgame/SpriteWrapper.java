package com.me.mygdxgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;

public class SpriteWrapper {
	public Sprite mySprite;
	private Texture myTexture;
	public float xVelocity = 0;
	public float yVelocity = 0;
	private Body myBody;
	
	public SpriteWrapper(Body body, Sprite sprite, Texture texture) {
		myBody = body;
		mySprite=sprite;
		myTexture=texture;
	}
	
	
	public void Update()
	{
		mySprite.setX(myBody.getPosition().x-mySprite.getWidth()/2);
		mySprite.setY(myBody.getPosition().y-mySprite.getHeight()/2);		
	}
}
