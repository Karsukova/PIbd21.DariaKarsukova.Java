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
	public void DopColorSet(String colorName) {
        switch (colorName) {
            case "yellow":
                DopColor = Color.YELLOW;
                break;
            case "blue":
                DopColor = Color.BLUE;
                break;
            case "red":
                DopColor = Color.RED;
                break;
            case "green":
                DopColor = Color.GREEN;
                break;
            case "black":
                DopColor = Color.BLACK;
                break;
            case "orange":
                DopColor = Color.ORANGE;
                break;
            case "gray":
                DopColor = Color.GRAY;
                break;
            case "white":
                DopColor = Color.WHITE;
                break;
        }
    }

	public FighterPlane(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean weapon, boolean line) {
		super(maxSpeed, weight, mainColor);
		DopColor = dopColor;
		Weapon = weapon;
		Line = line;

	}
	public FighterPlane(String info) {
        super(info);
        String[] parameters = info.split(";");
        if (parameters.length == 6) {
            MaxSpeed = Integer.parseInt(parameters[0]);
            Weight = Integer.parseInt(parameters[1]);
            getMainColor(parameters[2]);
            DopColorSet(parameters[3]);
            Weapon = Boolean.parseBoolean(parameters[4]);
            Line = Boolean.parseBoolean(parameters[5]);
            
        }
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
	public String toString() {
        String str = "";
        if (DopColor.equals(Color.WHITE)) {
            str = "white";
        } else if (DopColor.equals(Color.BLACK)) {
            str = "black";
        } else if (DopColor.equals(Color.RED)) {
            str = "red";
        } else if (DopColor.equals(Color.YELLOW)) {
            str = "yellow";
        } else if (DopColor.equals(Color.ORANGE)) {
            str = "orange";
        } else if (DopColor.equals(Color.BLUE)) {
            str = "blue";
        } else if (DopColor.equals(Color.GRAY)) {
            str = "gray";
        } else if (DopColor.equals(Color.GREEN)) {
            str = "green";
        }
        else str = "white";
        return (super.toString() + ";" + str + ";" + Weapon + ";" + Line);
    }
}
