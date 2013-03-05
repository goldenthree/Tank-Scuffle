package com.me.mygdxgame;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class LevelLoader {
	
	
	
	public ArrayList<Vector2> GetDefaultLevelVertices()
	{
		ArrayList<Vector2> defaultLevel = new ArrayList<Vector2>();
		defaultLevel.add(new Vector2(-.5f, -.2f));
		defaultLevel.add(new Vector2(-.25f, -.1f));
		defaultLevel.add(new Vector2(-.0f, 0f));
		defaultLevel.add(new Vector2(.25f, -.1f));
		defaultLevel.add(new Vector2(5f, -.2f));
		
		
		
		
		return defaultLevel;
	}
	
	public ArrayList<PolygonShape> TransformLevelToSetOfPolygons(ArrayList<Vector2> points)
	{
		ArrayList<PolygonShape> shapes = new ArrayList<PolygonShape>();
		for(int i= 1;i < points.size();++i)
		{
			PolygonShape shape = new PolygonShape();
			Vector2 topLeft = points.get(i-1);
			Vector2 topRight = points.get(i);
			Vector2 bottomRight = new Vector2(topRight.x,-.3f);
			Vector2 bottomLeft = new Vector2(topLeft.x,-.3f);
			Vector2[] shapeVertices = new Vector2[]{topRight,topLeft,bottomLeft,bottomRight};
			shape.set(shapeVertices);
			shapes.add(shape);
		}
		return shapes;
	}
	
	
	public void LoadDefaultLevel(World world)
	{

		// Create our body definition
		BodyDef levelBodyDef =new BodyDef();  
		levelBodyDef.position.set(new Vector2(0, 0));  
		levelBodyDef.type = BodyType.StaticBody;
		Body levelBody = world.createBody(levelBodyDef);  

		
		for(PolygonShape shape : TransformLevelToSetOfPolygons(GetDefaultLevelVertices()))
		{
			
			FixtureDef fixtureDef = new FixtureDef();
			fixtureDef.shape = shape;
			// Set its world position
			levelBody.createFixture(fixtureDef); 
		}
		// Set the polygon shape as a box which is twice the size of our view port and 20 high
		// (setAsBox takes half-width and half-height as arguments)
		// Create a fixture from our polygon shape and add it to our ground body  
		// Clean up after ourselves
		
	}
}
