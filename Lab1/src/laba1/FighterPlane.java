package laba1;

import java.awt.Color;
import java.awt.Graphics;

public class FighterPlane extends Airplane {

	private Color DopColor;

	public Color getDopColor() {
		return DopColor;
	}

	public void setDopColor(Color value) {
		DopColor = value;
	}

	private boolean Weapon;

	public boolean getWeapon() {
		return Weapon;
	}

	public void setWeapon(boolean value) {
		Weapon = value;
	}

	private boolean Line;

	public boolean getLine() {
		return Line;
	}

	public void setLine(boolean value) {
		Line = value;
	}

	public FighterPlane(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean weapon, boolean line) {
		super(maxSpeed, weight, mainColor);
		MaxSpeed = maxSpeed;
		Weight = weight;
		MainColor = mainColor;
		DopColor = dopColor;
		Weapon = weapon;
		Line = line;

	}

	@Override
	public void DrawFighter(Graphics g) {
		g.setColor(Color.RED);
		if (Weapon) {
			g.setColor(DopColor);
			g.fillOval((int) StartPosX + 145/ 3 , (int) StartPosY + 40/ 3 , (int) 7/ 3 , (int) 25/ 3);
			g.fillOval((int) StartPosX + 134/ 3 , (int) StartPosY + 35/ 3 , (int) 7/ 3, (int) 25/ 3);
			g.fillOval((int) StartPosX + 122/ 3, (int) StartPosY + 32/ 3, (int) 7/ 3, (int) 25/ 3);

			g.fillOval((int) StartPosX + 48/ 3 , (int) StartPosY + 40/ 3, (int) 7/ 3, (int) 25/ 3);
			g.fillOval((int) StartPosX + 59/ 3 , (int) StartPosY + 35/ 3 , (int) 7/ 3, (int) 25/ 3);
			g.fillOval((int) StartPosX + 70/ 3 , (int) StartPosY + 32/ 3 , (int) 7/ 3, (int) 25/ 3);

		}
		super.DrawFighter(g);

		if (Line) {
			g.setColor(DopColor);
			g.drawLine((int) StartPosX + 100/ 3, (int) StartPosY + 125/ 3, (int) StartPosX + 100/ 3, (int) StartPosY + 40/ 3 );
			g.drawLine((int) StartPosX + 80/ 3, (int) StartPosY + 105/ 3, (int) StartPosX + 120/ 3, (int) StartPosY + 105/ 3 );
		}
	}
}
