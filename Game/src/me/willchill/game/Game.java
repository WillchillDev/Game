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
	
	public int updates;
	
	private void start(){
		
		try{
			//Window initialisation.
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
			Display.setTitle("Game");
			Display.setVSyncEnabled(true);
		} catch (LWJGLException e){
			e.printStackTrace();
			System.exit(0);
		}
		
		initGL(); //Initialise OpenGL.
		keyboard = new GameKeyboard(); //Initialise keyboard.
		Entity.player = new Player("player", "res/entities/player.png", 400, 300, 1f); //Create player.
		
		level = new Level(200,200); //Initialise level.
		
		//Fixed timestep code.
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		updates = 0;
		
		
		while(!Display.isCloseRequested()){
			
			//More fixed timestep code.
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
		
		Display.destroy(); //If Display.isCloseRequested(), destroy (close) the window.
	}
	
	private void render(){
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		level.render();
		Entity.player.render();
	}
	
	private void update() {
		keyboard.pollInput();
		Entity.player.update();
	}

	public void initGL() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glViewport(0, 0, 800, 600); //Set window size at 800x600p
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR); //Enable texture scaling
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR); //Enable texture scaling
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA); //Enable texture transparency
	}
	
	public static void main(String args[]){
		game = new Game();
		game.start();
	}
}