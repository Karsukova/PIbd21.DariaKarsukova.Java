package laba1;

import javax.swing.*;
import java.awt.*;

public class JPanelDraw extends JPanel {

    private IFighter transport;

    public void setTransport(IFighter transport)
    {
        this.transport = transport;
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        if (transport != null) {
            transport.DrawFighter(g);
        }
    }
}
