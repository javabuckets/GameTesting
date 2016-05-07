import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class SimpleNimGame 
{
	private static final int WIDTH = 1920, HEIGHT = 1080;

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
		
		// Infinite While loop
		while(true)
		{
			try
			{
				// Count the FPS.
				instance.countFPS();

				// clear back buffer...
				g2d = bufferedImage.createGraphics();
				g2d.setColor(new Color(41, 143, 204));
				g2d.fillRect(0, 0, WIDTH, HEIGHT);
				
				// Render Start
				
				
				
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