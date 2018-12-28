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
		g.setColor(MainColor);
		g.fillOval((int) StartPosX + 28/ 3, (int) StartPosY + 40/ 3, (int) 140/ 3, (int) 30/ 3);
 	g.setColor(Color.black);
		g.drawOval((int) StartPosX + 28/ 3, (int) StartPosY + 40/ 3, (int) 140/ 3, (int) 30/ 3);

		g.setColor(MainColor);
	    g.fillOval((int) StartPosX + 60 / 3, (int) StartPosY + 130/ 3, (int) 80/ 3, (int) 15/ 3);
		g.setColor(Color.black);
		g.drawOval((int) StartPosX + 60 / 3, (int) StartPosY + 130/ 3, (int) 80/ 3, (int) 15/ 3);

		g.setColor(MainColor);
		g.fillOval((int) StartPosX + 80/ 3, (int) StartPosY - 6/ 3, (int) 40/ 3, (int) 160/ 3);
		g.setColor(Color.black);
		g.drawOval((int) StartPosX + 80/ 3, (int) StartPosY - 6/ 3, (int) 40/ 3, (int) 160/ 3);

		g.setColor(MainColor);
		g.fillOval((int) StartPosX + 85/ 3, (int) StartPosY + 26/ 3, (int) 30/ 3, (int) 15/ 3);
		g.setColor(Color.black);
		g.drawOval((int) StartPosX + 85/ 3, (int) StartPosY + 26/ 3, (int) 30/ 3, (int) 15/ 3);

		g.setColor(MainColor);
		g.fillOval((int) StartPosX + 97/ 3, (int) StartPosY + 120/ 3, (int) 7/ 3, (int) 30/ 3);
		g.setColor(Color.black);
		g.drawOval((int) StartPosX + 97/ 3, (int) StartPosY + 120/ 3, (int) 7/ 3, (int) 30/ 3);

		g.drawLine((int) StartPosX + 100/ 3, (int) StartPosY + 25/ 3, (int) StartPosX + 100/ 3, (int) StartPosY + 40/ 3);
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
	public void setMainColor(Color value) {
        MainColor = value;
    }
	
	public Airplane(String info) {
        String[] parameters = info.split(";");
        if (parameters.length == 3) {
            MaxSpeed = Integer.parseInt(parameters[0]);
            Weight = Integer.parseInt(parameters[1]);
            getMainColor(parameters[2]);
        }
    }
	public String toString() {
        String str = "";
            if (MainColor.equals(Color.WHITE)) {
                str = "white";
            } else if (MainColor.equals(Color.BLACK)) {
                str = "black";
            } else if (MainColor.equals(Color.RED)) {
                str = "red";
            } else if (MainColor.equals(Color.YELLOW)) {
                str = "yellow";
            } else if (MainColor.equals(Color.ORANGE)) {
                str = "orange";
            } else if (MainColor.equals(Color.BLUE)) {
                str = "blue";
            } else if (MainColor.equals(Color.GRAY)) {
                str = "gray";
            } else if (MainColor.equals(Color.GREEN)) {
                str = "green";
            }
            else str = "white";
        return MaxSpeed + ";" + 100 + ";" + str;
    }
    public void getMainColor(String colorName) {
        switch (colorName) {
            case "yellow":
                MainColor = Color.YELLOW;
                break;
            case "blue":
                MainColor = Color.BLUE;
                break;
            case "red":
                MainColor = Color.RED;
                break;
            case "green":
                MainColor = Color.GREEN;
                break;
            case "black":
                MainColor = Color.BLACK;
                break;
            case "orange":
                MainColor = Color.ORANGE;
                break;
            case "gray":
                MainColor = Color.GRAY;
                break;
            case "white":
                MainColor = Color.WHITE;
                break;
        }
    }

}
