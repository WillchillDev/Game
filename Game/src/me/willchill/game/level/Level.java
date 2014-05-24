package me.willchill.game.level;

import java.util.Random;

import me.willchill.game.level.tile.Tile;

public class Level {
	
	private int width, height;
	public double xoffset = 0, yoffset = 0;
	protected int[][] tiles;
	private static final Random random = new Random();
	
	public Level(int width, int height){
		this.width = width;
		this.height = height;
		tiles = new int[width][height];
		generateLevel();
	}
	
	public Level(String levelPath){
		loadLevel(levelPath);
	}
	
	private void loadLevel(String levelPath){
		//Load level from file, should be line-by-line
	}
	
	private void generateLevel(){
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				tiles[x][y] = random.nextInt(3); //Fills the int array with an ints ranging from from 0-2
			}
		}
	}
	
	public Tile getTile(int x, int y){
		if(x < 0 || x >= width || y < 0 || y >= height) return Tile.voidTile;
		else if(tiles[x][y] == 0) return Tile.voidTile;  //If the tile at co-ords x,y == 0, return voidTile
		else if(tiles[x][y] == 1) return Tile.grassTile; //If the tile at co-ords x,y == 1, return grassTile
		else if(tiles[x][y] == 2) return Tile.stoneTile; //If the tile at co-ords x,y == 2, return stoneTile
		else return Tile.voidTile;
		
	}
	
	public void render(){
		for(int x = 0; x <= width; x++){
			for(int y = 0; y <= height; y++){
				getTile(x,y).render(x + xoffset, y + yoffset); //Render each tile according to the offset of the player.
			}
		}
	}
}
