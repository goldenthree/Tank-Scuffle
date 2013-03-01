package com.me.mygdxgame;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpriteManager  {

	public ArrayList<SpriteWrapper> sprites = new ArrayList<SpriteWrapper>();
	
	public boolean IsTank = false;

	public void updateSprites()
	{
		for (SpriteWrapper sprite : sprites) {
			sprite.Update();
		}
	}
	

	public void draw(SpriteBatch spriteBatch)
	{

		for (SpriteWrapper sprite : sprites) {
			sprite.mySprite.draw(spriteBatch);
			if(IsTank)
			{
				int x = 1;
			}
		}
		
	}
}
