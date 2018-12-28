package laba1;

import javax.swing.*;

import laba1.MultiLevelHangar;

import java.awt.*;

public class JPanelHangar extends JPanel {
	private Hangar<IFighter> hangar;
	private JList list;

    public void setHangar(Hangar<IFighter> hangar)
    {
        this.hangar = hangar;
    }
    public void setList(JList list)
    {
        this.list = list;
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        if (hangar != null) {
            hangar.Draw(g);
        }
    }
}
