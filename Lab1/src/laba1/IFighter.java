package laba1;

import java.awt.Graphics;

public interface IFighter {
    void setPosition(int x, int y, int width, int height);

    void moveTransport(Direction direction);

    void DrawFighter(Graphics g);
}