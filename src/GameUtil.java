import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameUtil 
{
	public BufferedImage loadImage(String path)
	{
		BufferedImage img = null;
		try 
		{
		    img = ImageIO.read(new File(path));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return img;
	}
}