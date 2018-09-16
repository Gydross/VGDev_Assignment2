package com.libgdx.csc361_f18_g8.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.libgdx.csc361_f18_g8.util.Constants;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

/**
 * Assets class from pages 149-156
 * @author Connor Orischak
 *
 */
public class Assets implements Disposable, AssetErrorListener
{
	public static final String TAG = Assets.class.getName();
	public static final Assets instance = new Assets();
	
	private AssetManager assetManager;
	
	//singleton: prevent instantiation from other classes
	
	private Assets() {}
	
	public AssetBunny bunny;
	public AssetRock rock;
	public AssetGoldCoin goldCoin;
	public AssetFeather feather;
	public AssetLevelDecoration levelDecoration;
	TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
	
	public void init(AssetManager assetManager)
	{
		this.assetManager = assetManager;
		// set asset manager error handler
		assetManager.setErrorListener(this);
		// load texture atlas
		assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS,TextureAtlas.class);
		// start loading assets and wait until finished
		assetManager.finishLoading();
		Gdx.app.debug(TAG, "# of assets loaded: "+assetManager.getAssetNames().size);
		for (String a: assetManager.getAssetNames()) 
			Gdx.app.debug(TAG, "asset: "+a);
		
	}
	/**
	 * Check here. There is an extra set of braces
	 * that enclose some instance variables.
	 * This is the only way I found that gets
	 * rid of the syntax errors.
	 */
	{
	// enable texture filtering for pixel smoothing
	for (Texture t: atlas.getTextures())
	{
		t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}
	
	//create game resource objects
	bunny = new AssetBunny(atlas);
	rock = new AssetRock(atlas);
	goldCoin = new AssetGoldCoin(atlas);
	feather = new AssetFeather(atlas);
	levelDecoration = new AssetLevelDecoration(atlas);}
	
	@Override
	public void dispose()
	{
		assetManager.dispose();
	}
	/*
	 * really don't know why it's telling me to get rid of the override
	 */
	@Override
	public void error (String filename, Class type, Throwable throwable)
	{
		Gdx.app.error(TAG, "Couldn't load asset '"+filename+"'", (Exception) throwable);
	}
	
	@Override
	public void error(AssetDescriptor asset, Throwable throwable)
	{
		Gdx.app.error(TAG, "Couldn't load asses '"+asset.fileName+"'", (Exception) throwable);
	}
	/**
	 * 
	 * @author Connor
	 * Bunny picture object
	 */
	public class AssetBunny
	{
		public final AtlasRegion head;
		public AssetBunny(TextureAtlas atlas)
		{
			head = atlas.findRegion("bunny_head");
		}
	}
	/**
	 * Sets up rock objects
	 * @author csuser
	 *
	 */
	public class AssetRock
	{
		public final AtlasRegion edge;
		public final AtlasRegion middle;
		
		public AssetRock(TextureAtlas atlas)
		{
			edge = atlas.findRegion("rock_edge");
			middle = atlas.findRegion("rock_middle");
		}
	}
	/**
	 * Sets up the gold coin object
	 * @author csuser
	 *
	 */
	public class AssetGoldCoin
	{
		public final AtlasRegion goldCoin;
		
		public AssetGoldCoin(TextureAtlas atlas)
		{
			goldCoin = atlas.findRegion("item_gold_coin");
		}
	}
	/**
	 * Sets up the feather object
	 * @author csuser
	 *
	 */
	public class AssetFeather
	{
		public final AtlasRegion feather;
		
		public AssetFeather(TextureAtlas atlas)
		{
			feather = atlas.findRegion("item_feather");
		}
	}
	/**
	 * imports all the level decoration objects
	 * @author csuser
	 *
	 */
	public class AssetLevelDecoration
	{
		public final AtlasRegion cloud1;
		public final AtlasRegion cloud2;
		public final AtlasRegion cloud3;
		public final AtlasRegion mountainLeft;
		public final AtlasRegion mountainRight;
		public final AtlasRegion waterOverlay;
		
		public AssetLevelDecoration (TextureAtlas atlas)
		{
			cloud1 = atlas.findRegion("cloud1");
			cloud2 = atlas.findRegion("cloud2");
			cloud3 = atlas.findRegion("cloud3");
			mountainLeft = atlas.findRegion("mountain_left");
			mountainRight = atlas.findRegion("mountain_right");
			waterOverlay = atlas.findRegion("water_overlay");
		}
	}
}
















