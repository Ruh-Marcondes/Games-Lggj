package Snown.Studios.World;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Snown.Studios.main.Game;

public class World {
	
	private static Tile[] tiles;
	public static  int WIDTH, HEIGHT;
	public static int TILE_SIZE = 32;
	
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth()*map.getHeight()];
			WIDTH =  map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth()*map.getHeight()];
			map.getRGB(0,0,map.getHeight(),map.getHeight(),pixels,0,map.getWidth());
			
			for(int xx = 0; xx<map.getWidth();xx++) {
				for(int yy = 0;yy<map.getHeight();yy++) {
					
					int pixelAtual = pixels[xx+(yy*map.getWidth())];
					
						tiles[xx+(yy*WIDTH)] = new Tile(xx*32,yy*32,Tile.TILE_PADRAO);
						switch(pixelAtual) {
						case 0xff6a090c:
							// Lolipop
							tiles[xx+(yy*WIDTH)] = new FloorTile(xx*32,yy*32,Tile.TILE_LOLLIPOP);
							break;
						case 0xffec0007:
							//Lolopop Invertido
							tiles[xx+(yy*WIDTH)] = new FloorTile(xx*32,yy*32,Tile.TILE_POP_INVE);
							break;
						case 0xff6f0f00:
							//Chão - 1
							tiles[xx+(yy*WIDTH)] = new FloorTile(xx*32,yy*32,Tile.TILE_FLOOR_UP);
							break;
						case 0xff410900:
							//Chão - 2
							tiles[xx+(yy*WIDTH)] = new FloorTile(xx*32,yy*32,Tile.TILE_FLOOR_DOWN);
							break;
						case 0xffb22f00:
							tiles[xx+(yy*WIDTH)] = new FloorTile(xx*32,yy*32,Tile.TILE_CHAO_3);
							break;
						case 0xffffffff:
							//Branco - Player
							Game.player.setX(xx*32);
						Game.player.setY(yy*32);
							break;
							default:
								//umaImagemTransparenteComoPadrão
								tiles[xx+(yy*WIDTH)] = new Tile(xx*32,yy*32,Tile.TILE_PADRAO);
						}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void render(Graphics g) {
		
		//System.out.println(xfinal+" "+yfinal);
		for(int xx = 0;xx< WIDTH ; xx++) {
			for(int yy = 0; yy < HEIGHT;yy++) {
				//if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT ) // chega uma hora o vai ser negativo e o indice ão tem negativo ai é só mandar continuar que tá tudo certo
					//continue;
				Tile tile = tiles[xx+(yy*WIDTH)];
				tile.render(g);
			//}
		}
	}
}
}