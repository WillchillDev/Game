package me.willchill.game.entity;

import me.willchill.game.Game;
import me.willchill.game.level.block.Block;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class Player extends Entity {

	public Player(String name, String texturePath, float speed) {
		super(name, texturePath, 0, 0, speed);
		for(int i = 0; i <= Game.game.level.height; i++){
			if(Game.game.level.getBlock(0, i) == null
				|| Game.game.level.getBlock(0, i) == Block.airBlock){
				this.y = i;
				break;
			}
		}

	}
	
	public void update(){
		checkMove();
	}
	
	public void render(){
		if(this.texture == null){ //If the entity has no texture...
			System.out.println("ERROR: " + name + " has no loaded texture! Cannot render!"); //Print this line to the console.
			return;
		}
		//OpenGL stuff
		this.texture.bind();
		GL11.glBegin(GL11.GL_QUADS);
		    GL11.glTexCoord2f(0, 1); GL11.glVertex2f(Game.game.width / 2, Game.game.height / 2); //Top-left
		    GL11.glTexCoord2f(1, 1); GL11.glVertex2f(Game.game.width / 2 + this.texture.getTextureWidth(), Game.game.height / 2);//Top-right
		    GL11.glTexCoord2f(1, 0); GL11.glVertex2f(Game.game.width / 2 + this.texture.getTextureWidth(), Game.game.height / 2 + this.texture.getTextureHeight());//Bottom-right
		    GL11.glTexCoord2f(0, 0); GL11.glVertex2f(Game.game.width / 2, Game.game.height / 2 + this.texture.getTextureHeight());//Bottom-left
		GL11.glEnd();
	}
	
	public void checkMove(){

		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			this.move(0, speed);
			return;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			this.move(-speed, 0);
			return;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			this.move(0, -speed);
			return;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			this.move(speed, 0);
			return;
		}
	}
	
	public void move(double x, double y){
		this.x += x; //Changes the render position of all of the tiles in the game relative to the x axis.
		this.y += y; //Changes the render position of all the tiles in the game relative to the y axis.
	}
	
	public float getPositionX(){
		return x;
	}
	
	public float getPositionY(){
		return y;
	}
}
