package xxx.gamedev.engine.keyhandling;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import xxx.gamedev.engine.core.GameCore;

public class KeyTest extends GameCore implements KeyListener
{
	private LinkedList<String>messages = new LinkedList<String>();
	public static void main(String[] args)
	{
		new KeyTest().run();
	}
	
	@Override
	public void init()
	{
		// TODO Auto-generated method stub
		super.init();
		Window window = screen.getFullScreenWindow();
		window.setFocusTraversalKeysEnabled(false);
		window.addKeyListener(this);
		addMessage("Key input test press escape to exit");
	}

	private void addMessage(String message)
	{
		messages.add(message);
		if(messages.size() >= screen.getHeight() / FONT_SIZE)
		{
			messages.remove(0);
		}
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_ESCAPE)
		{
			stop();
		}
		else
		{
			addMessage("Key pressed : " + KeyEvent.getKeyText(keyCode));
			e.consume();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		addMessage("Key released: " + KeyEvent.getKeyText(keyCode));
		e.consume();
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		e.consume();
	}

	@Override
	public void draw(Graphics2D g)
	{
		Window window = screen.getFullScreenWindow();
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setColor(window.getBackground());
		g.fillRect(0, 0, screen.getWidth(), screen.getHeight());
		g.setColor(window.getForeground());
		int y = FONT_SIZE;
		for (int i = 0; i < messages.size(); i++)
		{
			g.drawString(messages.get(i), 5, y);
			y+= FONT_SIZE;
		}
	}

}
