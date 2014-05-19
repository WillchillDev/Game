package me.willchill.game.input;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

public class GameKeyboard {
	
	public List<Integer> pressedKeys = new ArrayList<Integer>();
	
	public void pollInput(){
		
		while(Keyboard.next()){
			
			if(Keyboard.getEventKeyState()){
				//if pressed...
					if (Keyboard.getEventKey() == Keyboard.KEY_W) {
						System.out.println("W Key Pressed");
						pressedKeys.add((Integer) Keyboard.KEY_W);
					}
			        if (Keyboard.getEventKey() == Keyboard.KEY_A) {
					    System.out.println("A Key Pressed");
					    pressedKeys.add((Integer) Keyboard.KEY_A);
					}
					if (Keyboard.getEventKey() == Keyboard.KEY_S) {
					    System.out.println("S Key Pressed");
					    pressedKeys.add((Integer) Keyboard.KEY_S);
					}
					if (Keyboard.getEventKey() == Keyboard.KEY_D) {
					    System.out.println("D Key Pressed");
					    pressedKeys.add((Integer) Keyboard.KEY_D);
					}  
				} else {
					//if not pressed...
					if (Keyboard.getEventKey() == Keyboard.KEY_W) {
					    System.out.println("W Key Released");
					    pressedKeys.remove((Integer) Keyboard.KEY_W);
					}
			        if (Keyboard.getEventKey() == Keyboard.KEY_A) {
					    System.out.println("A Key Released");
					    pressedKeys.remove((Integer) Keyboard.KEY_A);
					}
					if (Keyboard.getEventKey() == Keyboard.KEY_S) {
					    System.out.println("S Key Released");
					    pressedKeys.remove((Integer) Keyboard.KEY_S);
					}
					if (Keyboard.getEventKey() == Keyboard.KEY_D) {
					    System.out.println("D Key Released");
					    pressedKeys.remove((Integer) Keyboard.KEY_D);
					}
				}
		}
	}
}
