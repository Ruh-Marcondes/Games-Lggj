package Snown.Studios.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Snown.Studios.World.World;
import Snown.Studios.entity.Entities;
import Snown.Studios.entity.Player;
import Snown.Studios.graficos.Spritesheet;


public class Game extends Canvas implements Runnable, KeyListener, MouseListener {

	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private boolean isRunning = true;
	private Thread thread;
	
	public final static int WIDTH = 320;
	public final static int HEIGHT = 240;
	public final static int SCALE = 2;
	
	private BufferedImage image;
	
	public static Game game;
	public static Random rand; // está estatico para chamar o rand em qualquer classe ao inves de intancialo
	
	public static Spritesheet spritesheet;
	public static List<Entities> entities;
	public static Player player;
	public static World world;

	//Para criar o game Over é preciso criar states
	public Game() {
		addKeyListener(this);
		addMouseListener(this);
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		//tem q ser antes de inicializar o JFrame
		initFrame();

		// Inicializando OBJS na ordem certa
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		spritesheet =  new Spritesheet("/Sprite.png");
		entities = new ArrayList<Entities>();
		player = new Player(0,0,32,32,spritesheet.getSprite(0, 0, 32, 32));
		entities.add(player);
		world = new World("/mapa.png");
	}

	private void initFrame() {
		frame = new JFrame("Pegue o Cupcake");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	public synchronized void start() {
		thread = new Thread(this);// Precisa desse this
		isRunning = true;
		thread.start();

	}

	public synchronized void stop() {}

	public static void main(String[] args) {
		game = new Game();
		game.start();
	}

	public void tick() {// Lógica do jogo
		for (int i = 0; i < entities.size(); i++) {
			Entities e = entities.get(i);
			e.tick();
		}
}


	public void render() {// O que vai rendererizar "gráficos"
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		// Renderizando o fundo
		Graphics g = image.getGraphics();
		g.setColor(new Color(25,25,112));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		//g.drawImage(this.fundo,0,0,Game.WIDTH*Game.SCALE,Game.HEIGHT*Game.SCALE, null);
		
		world.render(g);
		for (int i = 0; i < entities.size(); i++) {
			Entities e = entities.get(i);
			e.render(g);
		}
		g.dispose();
		bs.show();
	}

	@Override
	public void run() {// FPS
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		requestFocus();
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				render();
				delta--;
			}
		}

	}

	// Metodos keyListener
	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_D) {
			player.right = true;
		}
		if(e.getKeyCode()== KeyEvent.VK_A) {
			player.left = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_D) {
			player.right = false;
		}
		if(e.getKeyCode()== KeyEvent.VK_A) {
			player.left = false;
		}
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			System.out.println("jump");
		}
	}

	// Mouse

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
