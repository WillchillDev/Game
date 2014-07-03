package me.willchill.game.entity;

import java.io.FileInputStream;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Entity {
	
	protected String name = "UNNAMED ENTITY";
	protected float x;
	protected float y;
	protected float speed; 
	protected Texture texture = null;

	public static Player player;
	
	public Entity(String name, String texturePath, float x, float y, float speed){
		this.name = name; //Basic initialisation stuff.
		this.x = x;
		this.y = y;
		this.speed = speed;
		
		try {
			texture = TextureLoader.getTexture("png", new FileInputStream(texturePath)); //Attempt to load texture.
		} catch (IOException e) {
			e.printStackTrace(); //If loading failed (file not found, for example), printStackTrace();
		}
		System.out.println("Entity " + name + " was created and a texture loaded!");
	}
	
	public void render(){
		
		if(this.texture == null){ //If the entity has no texture...
			System.out.println("ERROR: " + name + " has no loaded texture! Cannot render!"); //Print this line to the console.
			return;
		}
		//OpenGL stuff
		this.texture.bind();
		GL11.glBegin(GL11.GL_QUADS);
		    GL11.glTexCoord2f(0, 1); GL11.glVertex2f(this.x, this.y); //Top-left
		    GL11.glTexCoord2f(1, 1); GL11.glVertex2f(this.x + this.texture.getTextureWidth(), this.y);//Top-right
		    GL11.glTexCoord2f(1, 0); GL11.glVertex2f(this.x + this.texture.getTextureWidth(), this.y + this.texture.getTextureHeight());//Bottom-right
		    GL11.glTexCoord2f(0, 0); GL11.glVertex2f(this.x, this.y + this.texture.getTextureHeight());//Bottom-left
		GL11.glEnd();
	}
	
	public void update(){

	}
	
	protected void move(double x, double y){
		this.x += x;
		this.y += y;
	}
	
	protected float getPositionX(){
		return x;
	}
	
	protected float getPositionY(){
		return y;
	}
}
