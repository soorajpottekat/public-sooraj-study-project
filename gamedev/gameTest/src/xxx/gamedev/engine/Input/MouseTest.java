package xxx.gamedev.engine.Input;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.LinkedList;

import xxx.gamedev.engine.core.GameCore;

public class MouseTest extends GameCore implements KeyListener,MouseListener,MouseMotionListener,MouseWheelListener
{
	private static final int TRAIL_SIZE = 10;
    private static final Color[] COLORS = {
        Color.white, Color.black, Color.yellow, Color.magenta
    };
    private LinkedList <Point>trailList;
    private boolean trailMode;
    private int colorIndex;
	public static void main(String[] args)
	{
		new MouseTest().run();
	}
	@Override
	public void init()
	{
		super.init();
		trailList = new LinkedList<Point>();
		Window window = screen.getFullScreenWindow();
		window.addMouseListener(this);
		window.addMouseMotionListener(this);
		window.addMouseWheelListener(this);
		window.addKeyListener(this);
		
	}
	

	@Override
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		colorIndex = (colorIndex + e.getWheelRotation()) % COLORS.length;
		if(colorIndex < 0)
		{
			colorIndex += COLORS.length;
		}
		Window window = screen.getFullScreenWindow();
		window.setForeground(COLORS[colorIndex]);
	}

	@Override
	public void mouseDragged(MouseEvent arg0)
	{
		mouseMoved(arg0);
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		Point p =  new Point(e.getX(), e.getY());
		trailList.addFirst(p);
		while (trailList.size() > TRAIL_SIZE)
		{
			trailList.removeLast();
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		// ignore 
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		mouseMoved(arg0);
	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		mouseMoved(arg0);
	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		trailMode = !trailMode;
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		//Do noting
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_ESCAPE)
		{
			stop();
		}
		e.consume();		
	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
		// do noting
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// do noting
	}

	@Override
	public void draw(Graphics2D g)
	{
		int count =  trailList.size();
		if(count>1 && !trailMode)
		{
			count = 1;
		}
		Window window = screen.getFullScreenWindow();
		g.setColor(window.getBackground());
		g.fillRect(0, 0, screen.getWidth(), screen.getHeight());
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setColor(window.getForeground());
		g.drawString("I Love you cheemi", 5, FONT_SIZE);
		for (int i = 0; i < count; i++)
		{
			Point point = trailList.get(i);
			g.drawString("I Love you cheekutty", point.x, point.y);			
		}
		
	}

}
