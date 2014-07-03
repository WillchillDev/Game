package me.willchill.game.level;

import java.util.Random;

import org.lwjgl.input.Keyboard;

import me.willchill.game.Game;
import me.willchill.game.entity.Entity;
import me.willchill.game.level.block.Block;
import me.willchill.game.level.generation.SimplexNoise;

public class Level {
	
	public int width, height;
	int stoneLevel;
	int grassDepth;
	public double xoffset = 0, yoffset = 0;
	public int[][] tiles;
	private static final Random random = new Random();
	
	public Level(int width, int height, int stoneLevel, int grassDepth){
		this.width = width;
		this.height = height;
		
		if(stoneLevel < height){
		this.stoneLevel = stoneLevel;
		} else {
			System.out.println("ERROR: HEIGHT < STONELEVEL");
		}
		this.grassDepth = grassDepth;
		tiles = new int[width][height];
		generateLevel(50f);
	}
	
	public Level(int width, int height){
		this.width = width;
		this.height = height;
		
		//Generate stone level
		this.stoneLevel = random.nextInt(3);
		if(stoneLevel == 0) stoneLevel = height * 2/3;
		if(stoneLevel == 1) stoneLevel = height * 1/3;
		if(stoneLevel == 2) stoneLevel = height * 7/9;
		
		//Generate grass depth
		this.grassDepth = random.nextInt(3);
		if(grassDepth == 0) grassDepth = 3;
		if(grassDepth == 1) grassDepth = 5;
		if(grassDepth == 2) grassDepth = 7;
		
		tiles = new int[width][height];
		generateLevel(100f);
	}
	
	public void update(){
		if(Game.game.updates % 30 == 0)
		if(Keyboard.isKeyDown(Keyboard.KEY_R)){
			System.out.println("Resetting level");
			for(int x = 0; x < width; x++){
				for(int y = 0; y < height; y++){
					tiles[x][y] = 0;
				}
			}
			generateLevel(100f);
		}
	}
	
	public void render(){
		for(int x = 0; x <= width; x++){
			for(int y = 0; y <= height; y++){
				if(getBlock(x,y) == null) continue;
				else getBlock(x,y).render(x - Entity.player.getPositionX(), y - Entity.player.getPositionY()); //Render each tile according to the offset of the player.
			}
		}
	}
	
	private void generateLevel(float freq){
		
		SimplexNoise noise = new SimplexNoise();
				
		for(int x = 0; x < width; x++){
			
			float h = (noise.noise((float) x / freq, 0) + 3) / 5; // make noise 0 to 1
			for(int y = 0; y < height; y++) {
				
				float current = (float) (height - y) / height;
				
				if(current > h) tiles[x][y] = 1;
				else tiles[x][y]= 0;
			}
		}
	}
	
	public Block getBlock(int x, int y){
		if(x < 0 || x >= width || y < 0 || y >= height) return null;
		else if(tiles[x][y] == 0) return null;  //If the tile at co-ords x,y == 0, return voidTile
		else if(tiles[x][y] == 1) return Block.grassBlock; //If the tile at co-ords x,y == 1, return grassTile
		else if(tiles[x][y] == 2) return Block.stoneBlock; //If the tile at co-ords x,y == 2, return stoneTile
		else return null;
	}
	
}
