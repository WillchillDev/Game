package me.willchill.game.level.block;

import java.io.FileInputStream;
import java.io.IOException;

import me.willchill.game.Game;
import me.willchill.game.entity.Entity;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Block {
	
	private Texture texture;
	private boolean solid = false;
	private String name = "UNNAMED BLOCK";
	
	
	public static GrassBlock grassBlock = new GrassBlock("res/blocks/grass.png");
	public static AirBlock airBlock = new AirBlock("res/blocks/air.png");
	public static StoneBlock stoneBlock = new StoneBlock("res/blocks/stone.png");
	
	private double screenWidthInBlocks = Game.game.width / 16;
	private double screenHeightInBlocks = Game.game.height / 16;
	
	public Block(String name, String texturePath, boolean solid){
		this.solid = solid;
		this.name = name;
		try {
			texture = TextureLoader.getTexture("png", new FileInputStream(texturePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Block " + name + " was created and a texture successfully loaded!");
	}
	
	public void render(double x, double y){
		
		if(this.texture == null){
			System.out.println("ERROR: " + name + " has no loaded texture! Cannot render!");
			return;
		}
		if(!this.isOnScreen(x, y)) return;
		this.texture.bind();
		
		GL11.glBegin(GL11.GL_QUADS);
	    	GL11.glTexCoord2f(0, 1); GL11.glVertex2d(x * 16, y * 16); //Top-left
	    	GL11.glTexCoord2f(1, 1); GL11.glVertex2d((x * 16 + this.texture.getTextureWidth()), y * 16);//Top-right
	    	GL11.glTexCoord2f(1, 0); GL11.glVertex2d((x * 16 + this.texture.getTextureWidth()), (y * 16 + this.texture.getTextureHeight()));//Bottom-right
	    	GL11.glTexCoord2f(0, 0); GL11.glVertex2d(x * 16, (y * 16 + this.texture.getTextureHeight()));//Bottom-left
	    GL11.glEnd();
	}
	
	public boolean isSolid(){
		return solid;
	}
	
	private boolean isOnScreen(double x, double y){
		if(x >= Entity.player.getPositionX() - screenWidthInBlocks
		|| x <= Entity.player.getPositionX() + screenWidthInBlocks
		|| y >= Entity.player.getPositionY() - screenHeightInBlocks
		|| y <= Entity.player.getPositionY() + screenHeightInBlocks) return true;
		else return false;
	}
	
}
