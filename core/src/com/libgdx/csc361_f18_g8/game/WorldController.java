package com.libgdx.csc361_f18_g8.game;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
public class WorldController extends InputAdapter
{
	private static final String TAG = 
			WorldController.class.getName();
	
	public Sprite[] testSprites;
	public int selectedSprite;
	
	public WorldController() 
	{
		init();
	}
	private void init() 
	{
		Gdx.input.setInputProcessor(this);
		initTestObjects();
	}
	private void initTestObjects()
	{
		//Create array of 5 test sprites
		testSprites = new Sprite[5];
		//Create empty POT-sized Pixmap with 8 bit RGBA pixel data
		int width = 32;
		int height = 32;
		Pixmap pixmap = createProceduralPixmap(width,height);
		Texture texture = new Texture(pixmap);
		for(int i=0;i<testSprites.length;i++)
		{
			Sprite spr = new Sprite(texture);
			spr.setSize(1, 1);
			spr.setOrigin(spr.getWidth()/ 2.0f, spr.getHeight()/2.0f);
			float randomX = MathUtils.random(-2.0f,2.0f);
			float randomY = MathUtils.random(-2.0f,2.0f);
			spr.setPosition(randomX, randomY);
			testSprites[i] = spr;
		}
		selectedSprite = 0;
	}
	
	private Pixmap createProceduralPixmap(int width, int height)
	{
		Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
		pixmap.setColor(1, 0, 0, 0.5f);
		pixmap.fill();
		//Draw yellow colored X shape on square
		pixmap.setColor(1, 1, 0, 1);
		pixmap.drawLine(0, 0, width, height);
		pixmap.drawLine(width, 0, 0, height);
		//Draw cyan colored border around square
		pixmap.setColor(0, 1, 1, 1);
		pixmap.drawRectangle(0, 0, width, height);
		return pixmap;
	}
	public void update(float deltaTime) 
	{
		handleDebugInput(deltaTime);
		updateTestObjects(deltaTime);
		
	}
	private void handleDebugInput(float deltaTime)
	{
		if (Gdx.app.getType() != ApplicationType.Desktop) return;
		//Selected Sprite controls
		float sprMoveSpeed = 5 * deltaTime;
		if (Gdx.input.isKeyPressed(Keys.A)) moveSelectedSprite(
				-sprMoveSpeed,0);
		if (Gdx.input.isKeyPressed(Keys.D))
				moveSelectedSprite(sprMoveSpeed,0);
		if (Gdx.input.isKeyPressed(Keys.W)) moveSelectedSprite(0,
				sprMoveSpeed);
		if (Gdx.input.isKeyPressed(Keys.S)) moveSelectedSprite(0,
				-sprMoveSpeed);
	}
	
	private void moveSelectedSprite(float x, float y)
	{
		testSprites[selectedSprite].translate(x, y);
	}
	private void updateTestObjects(float deltaTime)
	{
		//Get current rotation from selected sprite
		float rotation = testSprites[selectedSprite].getRotation();
		//Rotate sprite by 90 degrees per second
		rotation += 90 * deltaTime;
		//Wrap around at 360 degrees
		rotation %= 360;
		// set new rotation value to selected sprite
		testSprites[selectedSprite].setRotation(rotation);
	}
	
	@Override
	public boolean keyUp(int keycode)
	{
		// Reset game world
		if (keycode == Keys.R)
		{
			init();
			Gdx.app.debug(TAG, "Game world resetted");
		}
		else if (keycode == Keys.SPACE)
		{
			// Select next sprite
			selectedSprite = (selectedSprite +1) % testSprites.length;
			Gdx.app.debug(TAG, "Sprite #"+selectedSprite+" selected.");
		}
		return false;
	}
	
}




