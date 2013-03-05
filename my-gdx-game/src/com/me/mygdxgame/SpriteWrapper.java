package com.me.mygdxgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;

public class SpriteWrapper {
	public Sprite mySprite;
	private Texture myTexture;
	public Body myBody;
	
	public SpriteWrapper(Body body, Sprite sprite, Texture texture) {
		myBody = body;
		mySprite=sprite;
		myTexture=texture;
	}
	
	public float getX()
	{
		return myBody.getPosition().x;
	}
	
	public float getY()
	{
		return myBody.getPosition().y;
	}
	
	public void Update()
	{
		mySprite.setX(myBody.getPosition().x-mySprite.getWidth()/2);
		mySprite.setY(myBody.getPosition().y-mySprite.getHeight()/2);		
	}
}
