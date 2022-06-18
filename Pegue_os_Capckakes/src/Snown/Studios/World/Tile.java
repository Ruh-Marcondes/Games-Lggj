package Snown.Studios.World;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Snown.Studios.main.Game;

public class Tile {

	public static BufferedImage TILE_FLOOR_UP = Game.spritesheet.getSprite(128, 64, 32, 32);
	public static BufferedImage TILE_FLOOR_DOWN = Game.spritesheet.getSprite(128, 96, 32,32);
	public static BufferedImage TILE_CHAO_3 = Game.spritesheet.getSprite(128, 128, 32, 32);
	
	public static BufferedImage TILE_LOLLIPOP = Game.spritesheet.getSprite(32, 128, 32, 32);
	public static BufferedImage TILE_POP_INVE = Game.spritesheet.getSprite(64, 128, 32, 32);
	public static BufferedImage TILE_PADRAO = Game.spritesheet.getSprite(128, 0, 32, 32);
	
	private BufferedImage sprite;
	private int x,y;
	
	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite,x ,y,null);	
		}
}
