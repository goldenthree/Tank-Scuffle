package com.me.mygdxgame;



import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;




public class MyGestureListener implements GestureListener {

	public MyGdxGame myGame;
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	private ArrayList<Vector2> points = new ArrayList<Vector2>();
	int numPoints=0;
	@Override
	public boolean tap(float x, float y, int count, int button) {
		if(button == 0)
		{
			float myX = ((x/Gdx.graphics.getWidth())-0.5f);
			float myY = (.5f-(y/Gdx.graphics.getHeight()))/1.66666666f;
			System.out.println("x:" + myX + "   " + "y:" + myY + "  button:" + button);
			points.add(new Vector2(myX, myY));
			numPoints++;
		}
		else
		{
			numPoints = 0;
			myGame.addBox(points.toArray(new Vector2[0]));
			points.clear();
		}
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		myGame.shoot(velocityX,-velocityY);
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

	  

	
	}