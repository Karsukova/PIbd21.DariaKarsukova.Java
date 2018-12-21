package laba1;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormHangar extends JFrame {

    private JPanel contentPane;
    private JTextField textField;

    private IFighter transport;
    private Hangar<IFighter> hangar;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                   FormHangar frame = new FormHangar();
                   frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public FormHangar() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 458);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanelHangar panelHangar = new JPanelHangar();
        panelHangar.setBounds(10, 11, 608, 398);
        contentPane.add(panelHangar);

        hangar = new Hangar<IFighter>(15, panelHangar.getWidth(), panelHangar.getHeight());
        panelHangar.setHangar(hangar);

        JButton buttonSetFighterPlane = new JButton("Создать истребитель");
        buttonSetFighterPlane.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Color firstColor = JColorChooser.showDialog(null, "Choose a Color", Color.WHITE);
                Color secondColor = JColorChooser.showDialog(null, "Choose a Color", Color.WHITE);
                
					transport = new FighterPlane(100, 1000, firstColor, secondColor, true, true);
				
                hangar.addTransport(transport);
                
                panelHangar.repaint();
                firstColor =null;
                secondColor = null;
            }
        });
        buttonSetFighterPlane.setBounds(648, 21, 206, 68);
        contentPane.add(buttonSetFighterPlane);

        JButton buttonSetAirplane = new JButton("Создать самолёт");
       buttonSetAirplane.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Color firstColor = JColorChooser.showDialog(null, "Choose a Color", Color.WHITE);
                transport = new Airplane(100, 1000, firstColor);
                hangar.addTransport(transport);
                panelHangar.repaint();
            }
        });
        buttonSetAirplane.setBounds(648, 100, 206, 68);
        contentPane.add(buttonSetAirplane);

        JPanel panelGroupElements = new JPanel();
        panelGroupElements.setBounds(648, 204, 206, 205);
        contentPane.add(panelGroupElements);
        panelGroupElements.setLayout(null);

        JLabel lblNewLabel = new JLabel("\u041C\u0435\u0441\u0442\u043E");
        lblNewLabel.setBounds(10, 14, 40, 14);
        panelGroupElements.add(lblNewLabel);

        JPanelDraw panelTakePlane = new JPanelDraw();
        panelTakePlane.setBounds(10, 72, 186, 122);
        panelGroupElements.add(panelTakePlane);

        JButton buttonTakePlane = new JButton("Забрать самолёт");
        buttonTakePlane.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int numberOfPlace = 0;
                try {
                    numberOfPlace = Integer.parseInt(textField.getText());
                } catch (Exception ex)
                {
                    textField.setText("Invalid input");
                    return;
                }

                if (numberOfPlace >= hangar._places.size() || numberOfPlace < 0)
                {
                    textField.setText("Invalid input");
                    return;
                }

                transport = hangar.removeTransport(numberOfPlace);
                if (transport != null) {
                    transport.setPosition(5, 5, panelTakePlane.getWidth(), panelTakePlane.getHeight());
                }

                panelTakePlane.setTransport(transport);
                panelTakePlane.repaint();
               panelHangar.repaint();
            }
        });
        buttonTakePlane.setBounds(10, 39, 186, 23);
        panelGroupElements.add(buttonTakePlane);

        textField = new JTextField();
        textField.setBounds(60, 11, 136, 20);
        panelGroupElements.add(textField);
        textField.setColumns(10);
    }
}

