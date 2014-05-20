package me.willchill.game.level;

import java.nio.file.Files;
import java.nio.file.Paths;
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
		
		try{
			byte[] encoded = Files.readAllBytes(Paths.get(levelPath));
			width = encoded.length / 2;
			height = encoded.length / 2;
			for(int x = 0; x <= width; x++){
				for(int y = 0; y <= height; y++){
					tiles[x][y] = encoded[x*y];
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void generateLevel(){
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				tiles[x][y] = random.nextInt(3);
			}
		}
	}
	
	public Tile getTile(int x, int y){
		
		if(x < 0 || x >= width || y < 0 || y >= height) return Tile.voidTile;
		else if(tiles[x][y] == 0) return Tile.voidTile;
		else if(tiles[x][y] == 1) return Tile.grassTile;
		else if(tiles[x][y] == 2) return Tile.stoneTile;
		else return Tile.voidTile;
		
	}
	
	public void render(){
		for(int x = 0; x <= width; x++){
			for(int y = 0; y <= height; y++){
				getTile(x,y).render(x + xoffset, y + yoffset);
			}
		}
	}
}
