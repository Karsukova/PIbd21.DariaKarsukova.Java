package laba1;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Plane implements IFighter {
    protected float StartPosX;

    protected float StartPosY;

    protected int PictureWidth;

    protected int PictureHeight;

    public int MaxSpeed;
    
    public int getMaxSpeed() {
        return MaxSpeed;
    }

    public void setMaxSpeed(int value) {
    	MaxSpeed = value;
    }

    public float Weight;
    
    public float getWeight() {
        return Weight;
    }

    public void setWeight(float value) {
    	Weight = value;
    }

    public Color MainColor;
    
    public Color getMainColor() {
        return MainColor;
    }

    public void setMainColor(Color value) {
    	MainColor = value;
    }

    public void setPosition(int x, int y, int width, int height)
    {
        StartPosX = x;
        StartPosY = y;
        PictureWidth = width;
        PictureHeight = height;
    }

    public abstract void DrawFighter(Graphics g);

    public abstract void moveTransport(Direction direction);
}

