package me.willchill.game.entity;

import me.willchill.game.Game;
import me.willchill.game.input.GameKeyboard;

import org.lwjgl.input.Keyboard;

public class Player extends Entity{

	public Player(String name, String texturePath, float x,float y, float speed) {
		super(name, texturePath, x, y, speed);
	}
	
	public void update(){
		
		if(Game.game.updates % 10 == 0){   //Every 10 updates...
			this.checkMove(Game.keyboard); //Check to see if the player has moved
		}

	}
	
	public void checkMove(GameKeyboard keyboard){
		
		if(keyboard.pressedKeys.contains((Integer) Keyboard.KEY_W)){
			this.move(0, speed); //If the W key is pressed, move up.
			return;
		} 
		if(keyboard.pressedKeys.contains((Integer) Keyboard.KEY_A)){
			this.move(-speed, 0); //If the A key is pressed, move left.
			return;
		}
		if(keyboard.pressedKeys.contains((Integer) Keyboard.KEY_S)){
			this.move(0, -speed); //If the S key is pressed, move down.
			return;
		}
		if(keyboard.pressedKeys.contains((Integer) Keyboard.KEY_D)){
			this.move(speed, 0); //If the D key is pressed, move right.
			return;
		}
	}
	
	public void move(double x, double y){
		Game.level.xoffset -= x; //Changes the render position of all of the tiles in the game relative to the x axis.
		Game.level.yoffset -= y; //Changes the render position of all the tiles in the game relative to the y axis.
	}
}
