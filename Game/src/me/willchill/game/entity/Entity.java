package me.willchill.game.entity;

import java.io.FileInputStream;
import java.io.IOException;

import me.willchill.game.Game;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Entity {
	
	private String name = "UNNAMED ENTITY";
	public float x, y;
	protected float speed;
	private Texture texture = null;

	public static Player player;
	
	public Entity(String name, String texturePath, float x, float y, float speed){
		this.name = name;
		this.x = x;
		this.y = y;
		this.speed = speed;
		try {
			texture = TextureLoader.getTexture("png", new FileInputStream(texturePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Entity " + name + " was created and a texture loaded!");
	}
	
	public void render(){
		
		if(this.texture == null){
			System.out.println("ERROR: " + name + " has no loaded texture! Cannot render!");
			return;
		}
		
		this.texture.bind();
		GL11.glBegin(GL11.GL_QUADS);
		    GL11.glTexCoord2f(0, 1); GL11.glVertex2f(this.x, this.y); //Top-left
		    GL11.glTexCoord2f(1, 1); GL11.glVertex2f(this.x + this.texture.getTextureWidth(), this.y);//Top-right
		    GL11.glTexCoord2f(1, 0); GL11.glVertex2f(this.x + this.texture.getTextureWidth(), this.y + this.texture.getTextureHeight());//Bottom-right
		    GL11.glTexCoord2f(0, 0); GL11.glVertex2f(this.x, this.y + this.texture.getTextureHeight());//Bottom-left
		GL11.glEnd();
	}
	
	public void update(){
		if(this == player){
			player.checkPlayerMove(Game.keyboard);
		}
	}
	
	protected void move(/*Tight jeans,*/double d/*s, making me go woo-woop*/ , double e){
		this.x += d;
		this.y += e;
	}
	
	
}
