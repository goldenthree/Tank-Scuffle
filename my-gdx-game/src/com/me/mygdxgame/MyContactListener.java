package com.me.mygdxgame;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class MyContactListener implements ContactListener {
	public MyGdxGame myGame;
	
	public MyContactListener( MyGdxGame game) {
		game = myGame;
		
		
	}
	
	
	@Override
	public void beginContact(Contact contact) {
		// TODO Auto-generated method stub
		//Vector2 position = contact.getFixtureA().getBody().getPosition();
		//myGame.GenerateBangMessage(position.x,position.y+.10f);
		System.out.println("Bang");
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
	}

}
