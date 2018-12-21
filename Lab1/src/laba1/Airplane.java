package laba1;

import java.awt.Color;
import java.awt.Graphics;

public class Airplane extends Plane {

	protected final int planelWidth = 163;
	protected final int planeHeight = 160;

	public Airplane(int maxSpeed, float weight, Color mainColor) {
		MaxSpeed = maxSpeed;
		Weight = weight;
		MainColor = mainColor;
	}

	@Override
	public void DrawFighter(Graphics g) {

		g.setColor(Color.ORANGE);
		g.fillOval((int) StartPosX + 28, (int) StartPosY + 40, (int) 140, (int) 30);
		g.setColor(Color.black);
		g.drawOval((int) StartPosX + 28, (int) StartPosY + 40, (int) 140, (int) 30);

		g.setColor(Color.ORANGE);
		g.fillOval((int) StartPosX + 60, (int) StartPosY + 130, (int) 80, (int) 15);
		g.setColor(Color.black);
		g.drawOval((int) StartPosX + 60, (int) StartPosY + 130, (int) 80, (int) 15);

		g.setColor(Color.YELLOW);
		g.fillOval((int) StartPosX + 80, (int) StartPosY - 6, (int) 40, (int) 160);
		g.setColor(Color.black);
		g.drawOval((int) StartPosX + 80, (int) StartPosY - 6, (int) 40, (int) 160);

		g.setColor(Color.GRAY);
		g.fillOval((int) StartPosX + 85, (int) StartPosY + 26, (int) 30, (int) 15);
		g.setColor(Color.black);
		g.drawOval((int) StartPosX + 85, (int) StartPosY + 26, (int) 30, (int) 15);

		g.setColor(Color.ORANGE);
		g.fillOval((int) StartPosX + 97, (int) StartPosY + 120, (int) 7, (int) 30);
		g.setColor(Color.black);
		g.fillOval((int) StartPosX + 97, (int) StartPosY + 120, (int) 7, (int) 30);

		g.drawLine((int) StartPosX + 100, (int) StartPosY + 25, (int) StartPosX + 100, (int) StartPosY + 40);
	}

	@Override
	public void moveTransport(Direction direction) {
		float step = getMaxSpeed() * 100 / getWeight();
		switch (direction) {
		// вправо
		case Right:
			if (StartPosX + step < PictureWidth - planelWidth) {
				StartPosX += step;
			}
			break;
		// влево
		case Left:
			if (StartPosX - step > 0) {
				StartPosX -= step;
			}
			break;
		// вверх
		case Up:
			if (StartPosY - step > 0) {
				StartPosY -= step;
			}
			break;
		// вниз
		case Down:
			if (StartPosY + step < PictureHeight - planeHeight) {
				StartPosY += step;
			}
			break;
		}
	}

}
