package me.willchill.game.level;

import java.util.Random;

import me.willchill.game.level.tile.Tile;

public class Level {
	
	private int width, height;
	protected int[] tiles;
	private static final Random random = new Random();
	
	public Level(int width, int height){
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		generateLevel();
	}
	
	public Level(String levelPath){
		loadLevel(levelPath);
	}
	
	private void loadLevel(String levelPath){
		
	}
	
	private void generateLevel(){
		
		for(int i = 0; i < tiles.length; i++){
				tiles[i] = random.nextInt(2);
		}
	}
	
	public Tile getTile(int x, int y){
		
		if(x < 0 || x >= width || y < 0 || y >= height) return Tile.voidTile;
		else if(tiles[x + y * width] == 0) return Tile.stoneTile;
		else if(tiles[x + y * width] == 1) return Tile.grassTile;
		else if(tiles[x + y * width] == 2) return Tile.stoneTile;
		else return Tile.voidTile;
		
	}
	
	public void render(){
		for(int x = 0; x <= width; x++){
			for(int y = 0; y <= height; y++){
				getTile(x,y).render(x, y);
			}
		}
	}
}
