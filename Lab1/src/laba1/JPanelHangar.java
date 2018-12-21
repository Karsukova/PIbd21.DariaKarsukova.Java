package laba1;

import javax.swing.*;
import java.awt.*;

public class JPanelHangar extends JPanel {
    private Hangar<IFighter> hangar;

    public void setHangar(Hangar hangar)
    {
       this.hangar = hangar;
  }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        try {
           if (hangar != null) {
                hangar.Draw(g);
            }
        }
        catch(Exception ex){

        }
    }
}
