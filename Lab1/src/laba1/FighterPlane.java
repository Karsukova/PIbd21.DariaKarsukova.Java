package laba1;

import java.awt.Color;
import java.awt.Graphics;

import javafx.scene.shape.Ellipse;

public class FighterPlane   
{
    private float StartPosX;
    private float StartPosY;
    private int PictureWidth;
    private int PictureHeight;
    
    // Ўирина отрисовки самолета
    
    private static final int planeWidth = 100;
    
    // Ўирина отрисовки самолета
    
    private static final int planeHeight = 60;
    private int MaxSpeed;
    public int getMaxSpeed() {
        return MaxSpeed;
    }

    public void setMaxSpeed(int value) {
        MaxSpeed = value;
    }

    private float Weight;
    public float getWeight() {
        return Weight;
    }

    public void setWeight(float value) {
        Weight = value;
    }

    private Color MainColor;
    public Color getMainColor() {
        return MainColor;
    }

    public void setMainColor(Color value) {
        MainColor = value;
    }

    private Color DopColor;
    public Color getDopColor() {
        return DopColor;
    }

    public void setDopColor(Color value) {
        DopColor = value;
    }

    private boolean MainEllipse;
    public boolean getMainEllipse() {
        return MainEllipse;
    }

    public void setMainEllipse(boolean value) {
        MainEllipse = value;
    }


    public FighterPlane(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean mainEllipse) throws Exception {
        setMaxSpeed(maxSpeed);
        setWeight(weight);
        setMainColor(mainColor);
        setDopColor(dopColor);
        setMainEllipse(mainEllipse);
    }

    public void setPosition(int x, int y, int width, int height) throws Exception {
        StartPosX = x;
        StartPosY = y;
        PictureWidth = width;
        PictureHeight = height;
    }

    public void moveTransport(Direction direction) throws Exception {
        float step = getMaxSpeed() * 100 / getWeight();
        switch(direction)
        {
            case Right: 
                // вправо
                if (StartPosX + step < PictureWidth - planeWidth)
                {
                    StartPosX += step;
                }
                 
                break;
            case Left: 
                //влево
                if (StartPosX - step > 5)
                {
                    StartPosX -= step;
                }
                 
                break;
            case Up: 
                //вверх
                if (StartPosY - step > 30)
                {
                    StartPosY -= step;
                }
                 
                break;
            case Down: 
                //вниз
                if (StartPosY + step < PictureHeight - planeHeight)
                {
                    StartPosY += step;
                }
                 
                break;
        
        }
    }

    public void DrawFighter(Graphics g) throws Exception {
        
    	   	
    	if (getMainEllipse()) {
    		g.setColor(Color.RED);
            g.fillOval((int)StartPosX + 145, (int)StartPosY + 40, (int)7, (int)25);
            g.fillOval((int)StartPosX + 134, (int)StartPosY + 35, (int)7, (int)25);
            g.fillOval((int)StartPosX + 122, (int)StartPosY + 32, (int)7, (int)25);
            
            g.fillOval((int)StartPosX + 48, (int)StartPosY + 40, (int)7, (int)25);
            g.fillOval((int)StartPosX + 59, (int)StartPosY + 35, (int)7, (int)25);
            g.fillOval((int)StartPosX + 70, (int)StartPosY + 32, (int)7, (int)25);
            
            g.setColor(Color.ORANGE);
            g.fillOval((int)StartPosX + 28, (int)StartPosY + 40, (int)140, (int)30);
            g.setColor(Color.black); 
            g.drawOval((int)StartPosX + 28, (int)StartPosY + 40, (int)140, (int)30);
            
            g.setColor(Color.ORANGE);
            g.fillOval((int)StartPosX + 60, (int)StartPosY + 130, (int)80, (int)15);
            g.setColor(Color.black); 
            g.drawOval((int)StartPosX + 60, (int)StartPosY + 130, (int)80, (int)15);
            
            g.setColor(Color.YELLOW);
            g.fillOval((int)StartPosX + 80, (int)StartPosY -6, (int)40, (int)160);
            g.setColor(Color.black); 
            g.drawOval((int)StartPosX + 80, (int)StartPosY -6, (int)40, (int)160);
            
            g.setColor(Color.GRAY);
            g.fillOval((int)StartPosX + 85, (int)StartPosY +26, (int)30, (int)15);
            g.setColor(Color.black); 
            g.drawOval((int)StartPosX + 85, (int)StartPosY +26, (int)30, (int)15);
            
            g.setColor(Color.ORANGE);
            g.fillOval((int)StartPosX + 97, (int)StartPosY +120, (int)7, (int)30);
            g.setColor(Color.black); 
            g.fillOval((int)StartPosX + 97, (int)StartPosY +120, (int)7, (int)30);   
            
            g.drawLine((int)StartPosX + 100, (int)StartPosY + 25, (int)StartPosX + 100, (int)StartPosY + 40);
            
    	}
        
         
    }

}