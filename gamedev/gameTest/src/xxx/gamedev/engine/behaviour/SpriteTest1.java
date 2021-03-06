package xxx.gamedev.engine.behaviour;

import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import xxx.gamedev.engine.animation.Animation;
import xxx.gamedev.screens.ScreenManager;

public class SpriteTest1
{
	private static final long DEMO_TIME = 10000;
    private ScreenManager screen;
    private Image bgImage;
    private Sprite sprite;
	public static void main(String[] args)
	{	
		SpriteTest1 test = new SpriteTest1();
		test.run();
	}
	private static final DisplayMode POSSIBLE_MODES[] = {
		new DisplayMode(800, 600, 32, 0),
        new DisplayMode(800, 600, 24, 0),
        new DisplayMode(800, 600, 16, 0),
        new DisplayMode(640, 480, 32, 0),
        new DisplayMode(640, 480, 24, 0),
        new DisplayMode(640, 480, 16, 0)
    };
	private void run()
	{
		screen = new ScreenManager();
		try
		{
			DisplayMode displayMode = screen
					.findFirstCompatibleMode(POSSIBLE_MODES);
			screen.setFullScreen(displayMode);
			loadImages();
			animationLoop();
		}
		catch (Exception ex )
		{
			
		}
		finally
		{
			screen.restoreScreen();
		}
	}
	private void animationLoop()
	{
		long startTime =  System.currentTimeMillis();
		long currentTime = startTime;
		while (currentTime - startTime < DEMO_TIME)
		{
			long elapsedTime  = System.currentTimeMillis() - currentTime;
			currentTime += elapsedTime;
			update(elapsedTime);
			Graphics2D graphics = screen.getGraphics();
			draw(graphics);
			graphics.dispose();
			screen.update();
			try
			{
				Thread.sleep(20);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	private void draw(Graphics2D graphics)
	{
		graphics.drawImage(bgImage, 0, 0,null);
		graphics.drawImage(sprite.getImage(), Math.round(sprite.getX()), Math.round(sprite.getY()), null);
	}
	private void update(long elapsedTime)
	{
		if(sprite.getX()<0)
		{
			sprite.setVelocityX(Math.abs(sprite.getVelocityX() ));
		}
		else if(sprite.getX() + sprite.getWidth() >= screen.getWidth())
		{	
			sprite.setVelocityX(-Math.abs(sprite.getVelocityX()));
		}
		if(sprite.getY() < 0)
		{
			sprite.setVelocityY(Math.abs(sprite.getVelocityY()));
		}
		else if (sprite.getY() + sprite.getHeight() >= screen.getHeight()) 
		{
			sprite.setVelocityY(-Math.abs(sprite.getVelocityY()));
		}
		sprite.update(elapsedTime);
		
	}
	private void loadImages()
	{
		bgImage = getImage("resources/background.jpg");
		Image player1 = getImage("resources/player1.png");
		Image player2 = getImage("resources/player2.png");
		Image player3 = getImage("resources/player3.png");
		Animation anim = new Animation();
        anim.addFrame(player1, 250);
        anim.addFrame(player2, 150);
        anim.addFrame(player1, 150);
        anim.addFrame(player2, 150);
        anim.addFrame(player3, 200);
        anim.addFrame(player2, 150);
        sprite = new Sprite(anim);
        // setting up the velocity
        sprite.setVelocityX(0.2f);
        sprite.setVelocityY(0.2f);        
	}
	private Image getImage(String imageUrl)
	{
		return new ImageIcon(imageUrl).getImage();
	}

}
