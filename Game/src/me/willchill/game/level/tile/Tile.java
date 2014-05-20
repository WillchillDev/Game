package me.willchill.game.level.tile;

import java.io.FileInputStream;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Tile {
	
	private Texture texture;
	public boolean isSolid = false;
	private String name = "UNNAMED TILE";
	
	
	public static GrassTile grassTile = new GrassTile("res/tiles/grass.png");
	public static VoidTile voidTile = new VoidTile("res/tiles/void.png");
	public static StoneTile stoneTile = new StoneTile("res/tiles/stone.png");
	
	public Tile(String name, String texturePath, boolean isSolid){
		this.isSolid = isSolid;
		this.name = name;
		try {
			texture = TextureLoader.getTexture("png", new FileInputStream(texturePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Tile " + name + " was created and a texture successfully loaded!");
	}
	
	public void render(double x, double y){
		
		if(this.texture == null){
			System.out.println("ERROR: " + name + " has no loaded texture! Cannot render!");
			return;
		}
		
		this.texture.bind();
		
		
		GL11.glBegin(GL11.GL_QUADS);
		    GL11.glTexCoord2f(0, 1); GL11.glVertex2d(x * 16 * 3, y * 16 * 3); //Top-left
		    GL11.glTexCoord2f(1, 1); GL11.glVertex2d((x * 16 + this.texture.getTextureWidth()) * 3, y * 16 * 3);//Top-right
		    GL11.glTexCoord2f(1, 0); GL11.glVertex2d((x * 16 + this.texture.getTextureWidth()) * 3, (y * 16 + this.texture.getTextureHeight()) * 3);//Bottom-right
		    GL11.glTexCoord2f(0, 0); GL11.glVertex2d(x * 16 * 3, (y * 16 + this.texture.getTextureHeight()) * 3);//Bottom-left
		GL11.glEnd();
	}
	
}
