package Snown.Studios.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import Snown.Studios.World.Camera;
import Snown.Studios.World.World;
import Snown.Studios.main.Game;

public class Player extends Entities {

	public boolean left, right, jump,moved;  
	public int speed = 5,frames = 0,maxFrames = 10,index = 0,maxIndex=3;
	public int dir =0, right_dir = 0, left_dir =1;
	private BufferedImage[] right_img,left_img;
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
			right_img = new BufferedImage[4];
			left_img = new BufferedImage[4];
			
			for(int i = 0; i < 4; i++) {
				right_img[i] = Game.spritesheet.getSprite(0+(32*i),32, 32, 32);
			}
			for(int i = 0; i < 4; i++) {
				left_img[i] = Game.spritesheet.getSprite(0+(32*i),64, 32, 32);
			}

	}

		public void tick() {
			moved = false;
			if(left) {
				this.x -= speed;
				dir = left_dir;
				moved = true;
			}else if(right) {
				this.x += speed;
				dir = right_dir;
				moved = true;
			}
			
			if(moved) {
				frames++;
				if(frames == maxFrames) {
					index++;
					frames = 0;
					if(index > maxIndex)
							index = 0;
				}
			}
			
			Camera.x = Camera.clamp(this.getX() - (Game.WIDTH / 2), 0, World.WIDTH * 16 - Game.WIDTH);
			System.out.println(Camera.x);
			//Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT / 2), 0, World.HEIGHT * 32 - Game.HEIGHT);
		}
		
		public void render(Graphics g) {
			if(dir==left_dir) {
				// ght_img left_img
				g.drawImage(left_img[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
					if(!moved) {
						g.drawImage(left_img[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
					}
			}
			if(dir==right_dir) {
				g.drawImage(right_img[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
					if(!moved) {
						g.drawImage(right_img[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
					}
			}
	}

}
