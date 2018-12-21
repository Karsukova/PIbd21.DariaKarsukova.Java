package laba1;

import java.awt.Graphics;
import java.awt.Color;

public interface IFighter {
    void setPosition(int x, int y, int width, int height);

    void moveTransport(Direction direction);
    
    void setMainColor(Color color);

    void DrawFighter(Graphics g);
}