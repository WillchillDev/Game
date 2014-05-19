package me.willchill.game.entity;

import me.willchill.game.Game;
import me.willchill.game.input.GameKeyboard;

import org.lwjgl.input.Keyboard;

public class Player extends Entity{

	public Player(String name, String texturePath, float x,float y, float speed) {
		super(name, texturePath, x, y, speed);
	}
	
	public void checkPlayerMove(GameKeyboard keyboard){
		
		if(keyboard.pressedKeys.contains((Integer) Keyboard.KEY_W) && keyboard.pressedKeys.contains((Integer) Keyboard.KEY_A)){
			this.playerMove(-speed * Math.sqrt(0.5), speed * Math.sqrt(0.5));
			return;
		}
		if(keyboard.pressedKeys.contains((Integer) Keyboard.KEY_W) && keyboard.pressedKeys.contains((Integer) Keyboard.KEY_D)){
			this.playerMove(speed * Math.sqrt(0.5), speed * Math.sqrt(0.5));
			return;
		}
		if(keyboard.pressedKeys.contains((Integer) Keyboard.KEY_S) && keyboard.pressedKeys.contains((Integer) Keyboard.KEY_A)){
			this.playerMove(-speed * Math.sqrt(0.5), -speed * Math.sqrt(0.5));
			return;
		}
		if(keyboard.pressedKeys.contains((Integer) Keyboard.KEY_S) && keyboard.pressedKeys.contains((Integer) Keyboard.KEY_D)){
			this.playerMove(speed * Math.sqrt(0.5), -speed * Math.sqrt(0.5));
			return;
		}
		if(keyboard.pressedKeys.contains((Integer) Keyboard.KEY_W)){
			this.playerMove(0, speed);
			return;
		} 
		if(keyboard.pressedKeys.contains((Integer) Keyboard.KEY_A)){
			this.playerMove(-speed, 0);
			return;
		}
		if(keyboard.pressedKeys.contains((Integer) Keyboard.KEY_S)){
			this.playerMove(0, -speed);
			return;
		}
		if(keyboard.pressedKeys.contains((Integer) Keyboard.KEY_D)){
			this.playerMove(speed, 0);
			return;
		}
	}
	
	public void playerMove(double x, double y){
		Game.level.xoffset -= (int) x;
		Game.level.yoffset -= (int) y;
	}
}
