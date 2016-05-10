import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

public class SimpleNimGame 
{
	private static final int WIDTH = 1920, HEIGHT = 1080;

	static GameUtil gameUtil = new GameUtil();
	
	static ArrayList<Integer> ballsA = new ArrayList<Integer>();
	static ArrayList<Integer> ballsB = new ArrayList<Integer>();
	static ArrayList<Integer> ballsC = new ArrayList<Integer>();
	
	public static void main(String[] args) 
	{
		SimpleNimGame instance = new SimpleNimGame();
		JFrame frame = new JFrame();

		frame.setIgnoreRepaint(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		// Canvas for painting on.
		Canvas canvas = new Canvas();

		canvas.setIgnoreRepaint(true);
		canvas.setSize(WIDTH, HEIGHT);

		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);

		// Graphics & Buffers
		canvas.createBufferStrategy(2);
		BufferStrategy buffer = canvas.getBufferStrategy();

		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
		GraphicsConfiguration graphicsConfiguration = graphicsDevice.getDefaultConfiguration();

		// Off-screen drawing surface
		BufferedImage bufferedImage = graphicsConfiguration.createCompatibleImage(WIDTH, HEIGHT);

		// Render Objects
		Graphics g = null;
		Graphics2D g2d = null;
		
		// Initializing Game Objects
		for (int i = 0; i < 5; i++)
		{
			ballsA.add(1);
			ballsB.add(1);
			ballsC.add(1);
		}
		
		frame.addKeyListener( new KeyAdapter() {

			public void keyPressed( KeyEvent e ) {

				if( e.getKeyCode() == KeyEvent.VK_A )
					ballsA.remove(0);
				if( e.getKeyCode() == KeyEvent.VK_B )
					ballsB.remove(0);
				if( e.getKeyCode() == KeyEvent.VK_C )
					ballsC.remove(0);

			}

		});
		
		canvas.addKeyListener( new KeyAdapter() {

			public void keyPressed( KeyEvent e ) {

				if( e.getKeyCode() == KeyEvent.VK_A )
					ballsA.remove(0);
				if( e.getKeyCode() == KeyEvent.VK_B )
					ballsB.remove(0);
				if( e.getKeyCode() == KeyEvent.VK_C )
					ballsC.remove(0);

			}

		});
		
		// Infinite While loop
		while(true)
		{
			try
			{
				// Key Handler
				//KeyBindings kb = new KeyBindings();
				
				// Count the FPS.
				instance.countFPS();

				// clear back buffer...
				g2d = bufferedImage.createGraphics();
				g2d.setColor(new Color(41, 143, 204));
				g2d.fillRect(0, 0, WIDTH, HEIGHT);
				
				// Render Start
				
				for (int i = 0; i < ballsA.size(); i++)
				{
					g2d.drawImage(gameUtil.loadImage("./images/nim/NimBall.png"), 55*(i+1)+50, 50, 50, 50, null);
				}
				
				for (int i = 0; i < ballsB.size(); i++)
				{
					g2d.drawImage(gameUtil.loadImage("./images/nim/NimBall.png"), 55*(i+1)+50, 125, 50, 50, null);
				}
				
				for (int i = 0; i < ballsC.size(); i++)
				{
					g2d.drawImage(gameUtil.loadImage("./images/nim/NimBall.png"), 55*(i+1)+50, 200, 50, 50, null);
				}
				
				g2d.setColor(Color.black);
				g2d.setFont(new Font(Font.SERIF, Font.BOLD, 28));
				g2d.drawString("A", 50, 75+7);
				g2d.drawString("B", 50, 150+7);
				g2d.drawString("C", 50, 225+7);
				
				instance.renderFPS(g2d);
				
				// Render Stop
				
				// Blit image and flip...
				g = buffer.getDrawGraphics();
				g.drawImage(bufferedImage, 0, 0, null);

				if(!buffer.contentsLost())
					buffer.show();

				// Let the OS have a little time...
				Thread.yield();
			}
			finally
			{
				// release resources
				if(g != null ) 
					g.dispose();
				if(g2d != null) 
					g2d.dispose();
			}
		}
	}

	
	
	// Variables for counting frames per seconds
	int fps = 0;
	int frames = 0;
	long totalTime = 0;
	long curTime = System.currentTimeMillis();
	long lastTime = curTime;
	
	private void countFPS() 
	{
		// count Frames per second...
		lastTime = curTime;
		curTime = System.currentTimeMillis();
		totalTime += curTime - lastTime;

		if( totalTime > 1000 ) 
		{
			totalTime -= 1000;
			fps = frames;
			frames = 0;
		} 
		++frames;
	}
	
	public void renderFPS(Graphics2D g2d)
	{
		// display frames per second...
		g2d.setFont(new Font("Courier New", Font.PLAIN, 12));
		g2d.setColor(Color.GREEN);
		g2d.drawString(String.format("FPS: %s", fps), 20, 20);
	}
}