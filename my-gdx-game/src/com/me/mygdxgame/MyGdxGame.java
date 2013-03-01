package com.me.mygdxgame;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class MyGdxGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	private Texture ballTexture;
	private Sprite ballSprite;
	
	private Texture tank1Texture;
	private Sprite tank1Sprite;
	

	private Texture tank2Texture;
	private Sprite tank2Sprite;
	
	BallManager manager = new BallManager();
	
	private float xOffset = 0;
	private float xChange = 0.01f;
	private float xMaxChange = .5f;
	private float xOriginal;
	public float xVelocity = 0;
	Box2DDebugRenderer debugRenderer;
	ArrayList<TextToDisplay> myTexts = new ArrayList<TextToDisplay>();
	TerrainMaker tm;
	World world; 
	
	@Override
	public void create() {		
		world = new World(new Vector2(0, -10), true); 
		debugRenderer = new Box2DDebugRenderer();
		
		world.setContactListener(new MyContactListener(this));
		
		MyGestureListener mylistener = new MyGestureListener();
		mylistener.myGame = this;
		Gdx.input.setInputProcessor(new GestureDetector(mylistener));
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		
		texture = new Texture(Gdx.files.internal("data/libgdx.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureRegion region = new TextureRegion(texture, 0, 0, 1025, 550);
		
		sprite = new Sprite(region);
		sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		xOriginal = -sprite.getWidth()/2;
		sprite.setPosition(xOriginal,-sprite.getHeight()/2 );
		

		tank1Texture = new Texture(Gdx.files.internal("data/Tank.png"));
		tank1Texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureRegion tankRegion = new TextureRegion(tank1Texture, 0, 0, 1025, 550);
		
		tank1Sprite = new Sprite(tankRegion);
		tank1Sprite.setSize(0.25f, 0.25f * sprite.getHeight() / sprite.getWidth());
		tank1Sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		xOriginal = -sprite.getWidth()*3/4;
		
		 
		//BoxObjectManager.BOX_TO_WORLD = 100f
		//Scale it by 100 as our box physics bodies are scaled down by 100
		debugRenderer=new Box2DDebugRenderer();
		box2d(0,0);
		box2d(.2f, .2f);
		box2d(.1f, .3f);
		tm = new TerrainMaker(camera);
		tm.generateTerrain(7, 10);
		addGround(tm.);
	}
	
	public void addGround(ArrayList<Point2d> points)
	{
		
		// Create our body definition
		BodyDef groundBodyDef =new BodyDef();  
		// Set its world position
		groundBodyDef.position.set(new Vector2(0, -0.4f));  
		
		
		// Create a body from the defintion and add it to the world
		Body groundBody = world.createBody(groundBodyDef);  

		// Create a polygon shape
		PolygonShape groundBox = new PolygonShape();  
		// Set the polygon shape as a box which is twice the size of our view port and 20 high
		// (setAsBox takes half-width and half-height as arguments)
		Vector2[] vectors = new Vector2[points.size()];
		for(int i=0; i<points.size();++i)
		{
			vectors[i] = new Vector2(points.get(i).x, points.get(i).y);
		}
		groundBox.set(vectors);
		//groundBox.setAsBox(camera.viewportWidth, 0.2f);
		// Create a fixture from our polygon shape and add it to our ground body  
		groundBody.createFixture(groundBox, 0.0f); 
		// Clean up after ourselves
		groundBox.dispose();
	}
	
	public void box2d(float x, float y)
	{

		// Remember to dispose of any shapes after you're done with them!
		// BodyDef and FixtureDef don't need disposing, but shapes do.
		//circle.dispose();
		
		
		
		
	}
	
	public void shoot(float velocityX, float velocityY)
	{
		manager.CreateBall(world,tank1Sprite.getX(), tank1Sprite.getY(), velocityX/80000f, velocityY/80000);
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
	}

	@Override
	public void render() {	
		
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		world.step(1/300f, 6, 2);
		
		sprite.setPosition(sprite.getX() + xVelocity, sprite.getY());
		manager.updateBalls();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		//sprite.draw(batch);
		manager.draw(batch);
		tank1Sprite.draw(batch);
		tm.drawHills();
		for(TextToDisplay text : myTexts)
		{
			//BitmapFont font = new BitmapFont();
			//font.draw(batch, text.text, text.x, text.y);
			text.timer--;
			if(text.timer <= 0)
			{
				myTexts.remove(text);
			}
		}
		boolean isRightPressed = Gdx.input.isKeyPressed(Keys.RIGHT);
		if(isRightPressed)
		{
			tank1Sprite.setX(tank1Sprite.getX()+.01f);
		}
		boolean isLeftPressed = Gdx.input.isKeyPressed(Keys.LEFT);
		if(isLeftPressed)
		{
			tank1Sprite.setX(tank1Sprite.getX()-.01f);
		}
		boolean isUpPressed = Gdx.input.isKeyPressed(Keys.UP);
		if(isUpPressed)
		{
			tank1Sprite.setY(tank1Sprite.getY()+.01f);
		}
		boolean isDownPressed = Gdx.input.isKeyPressed(Keys.DOWN);
		if(isDownPressed)
		{
			tank1Sprite.setY(tank1Sprite.getY()-.01f);
		}
		//debugRenderer.render(world, camera.combined);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	public void GenerateBangMessage(float x, float y) {
		TextToDisplay ttd = new TextToDisplay();
		ttd.text = "BANG";
		ttd.timer = 60;
		myTexts.add(ttd);
		
	}
}
