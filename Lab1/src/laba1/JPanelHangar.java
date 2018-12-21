package laba1;

import javax.swing.*;

import laba1.MultiLevelHangar;

import java.awt.*;

public class JPanelHangar extends JPanel {
	private MultiLevelHangar hangar;
	private JList list;

    public void setHangar(MultiLevelHangar hangar)
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
        try {
            if (hangar != null) {
                if (list.getSelectedIndex() != -1 ) {
                    hangar.getAt(list.getSelectedIndex()).Draw(g);
                }
            }
        }
        catch(Exception ex){

        }
    }
}
