package com.me.mygdxgame;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.World;


public class TerrainMaker {
	private ShapeRenderer shapeRenderer;
	private Camera myCamera;
	public ArrayList<Point2d> points = new ArrayList<Point2d>();
	
	public TerrainMaker(Camera camera) {
		myCamera = camera;
	}
	void generateTerrain(int numberOfHills, int pixelStep)
	{
        // setting a starting y coordinate, around the vertical center of the stage
        float hillStartY = (float) (140+ Math.random()*200);
        // defining hill width, in pixels, that is the stage width divided by the number of hills
        float hillWidth = 640/numberOfHills;
        // defining the number of slices of the hill. This number is determined by the width of the hill in pixels divided by the amount of pixels between two points
        float hillSlices= hillWidth/pixelStep;
        // drawing stuff
  
        // looping through the hills
        for (int i=0; i < numberOfHills; i++) {
            // setting a random hill height in pixels
            float randomHeight = (float) (Math.random()*100);
            // this is necessary to make all hills (execept the first one) begin where the previous hill ended
            if(i!=0){
                hillStartY-=randomHeight;
            }
            // looping through hill slices
            for (int j = 0; j<=hillSlices; j++) {
                    // defining the point of the hill
            	
            		Point2d hillPoint =new Point2d(
                    		(j*pixelStep+hillWidth*i)/640f-.5f,
                    		(float)(hillStartY+randomHeight*Math.cos(2*Math.PI/hillSlices*j))/640f-.4f);

            		System.out.println(hillPoint.x + ":" + hillPoint.y);
                    points.add(hillPoint);
                    }
            // this is also necessary to make all hills (execept the first one) begin where the previous hill ended
            hillStartY = hillStartY+randomHeight;
        }
	}
	
	// this is the core function: drawHills
    // arguments: the number of hills to generate, and the horizontal step, in pixels, between two hill points
    public void drawHills(){

		ShapeRenderer shapeRenderer = new ShapeRenderer();
    	shapeRenderer.setProjectionMatrix(myCamera.combined);
    	for(int i=1; i < points.size();++i)
    	{
            shapeRenderer.begin(ShapeType.Line);
            Point2d lastHillPoint = points.get(i-1);
            Point2d hillPoint = points.get(i);
            shapeRenderer.setColor(1, 0, 0, 1);
            shapeRenderer.line((float) lastHillPoint.x,
            		(float) lastHillPoint.y,
            		(float) hillPoint.x,
            		(float) hillPoint.y);
            shapeRenderer.end();
    	}
    }
    
    public void buildGround(World world){
    	
    	
    }
}


