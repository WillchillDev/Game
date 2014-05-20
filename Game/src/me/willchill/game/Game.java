package me.willchill.game;

import me.willchill.game.entity.Entity;
import me.willchill.game.entity.Player;
import me.willchill.game.input.GameKeyboard;
import me.willchill.game.level.Level;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Game{
	
	public static Game game;
	public static GameKeyboard keyboard;
	private String gameStats = " ";
	public static Level level;
	
	private void start(){
		
		try{
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
			Display.setTitle("Game");
			Display.setVSyncEnabled(true);
		} catch (LWJGLException e){
			e.printStackTrace();
			System.exit(0);
		}
		
		initGL();
		keyboard = new GameKeyboard();
		Entity.player = new Player("player", "res/entities/player.png", 400, 300, 0.1f);
		
		level = new Level(200,200);
		
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		
		
		while(!Display.isCloseRequested()){
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1){
				update();
				updates++;
				delta--;
			}
			
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				gameStats = "FPS: " + frames + ", UPS: " + updates;
				Display.setTitle("Game " + gameStats);
				System.out.println(gameStats);
				updates = 0;
				frames = 0;
			}
			
			render();
			Display.update();
		}
		
		Display.destroy();
	}
	
	private void render(){
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		level.render();
		Entity.player.render();
	}
	
	private void update() {
		keyboard.pollInput();
		Entity.player.checkPlayerMove(keyboard);
	}

	public void initGL() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glViewport(0, 0, 800, 600);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public static void main(String args[]){
		game = new Game();
		game.start();
	}
}