package xxx.gamedev.engine.animation;

import java.awt.Image;
import java.util.ArrayList;

public class Animation
{
	private ArrayList<AnimationFrame> frames;
	private long totalTime;
	private int currentIndex;
	private long animTime;

	public Animation()
	{
		frames = new ArrayList<AnimationFrame>();
		totalTime = 0;
		start();

	}

	/**
	 * Adds an image to animation frame along with duration
	 * 
	 * @param img
	 *            - image of the animation
	 * @param duration
	 *            - duration of the animation
	 */
	public synchronized void addFrame(Image img, long duration)
	{
		totalTime = totalTime + duration;
		frames.add(new AnimationFrame(img, totalTime));
	}

	private void start()
	{
		animTime = 0;
		currentIndex = 0;

	}

	/**
	 * update the animation when the time is reached
	 * 
	 * @param timeElapsed
	 */
	public synchronized void update(long timeElapsed)
	{
		if (frames.size() > 1)
		{
			animTime = animTime + timeElapsed;
			if (animTime >= totalTime)
			{
				animTime = animTime % totalTime;
				currentIndex = 0;
			}
			while (animTime > getFrame(currentIndex).endTime)
			{
				currentIndex++;
			}
		}

	}

	private AnimationFrame getFrame(int currentIndex)
	{
		return frames.get(currentIndex);
	}

	public synchronized Image getImage()
	{
		if (frames.size() == 0)
		{
			return null;
		}
		return getFrame(currentIndex).img;
	}

	private class AnimationFrame
	{
		Image img;
		long endTime;

		AnimationFrame(Image img, long endTime)
		{
			this.img = img;
			this.endTime = endTime;
		}
	}

}
