package laba1;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import laba1.Airplane;
import laba1.FighterPlane;
import laba1.JPanelDraw;
import laba1.JPanelHangar;
import laba1.MultiLevelHangar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormHangar extends JFrame {

    private JPanel contentPane;
    private JTextField textField;

    private IFighter transport;
    private MultiLevelHangar hangar;
    private final int countLevel = 5;
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

        JPanelHangar panelPlane = new JPanelHangar();
        panelPlane.setBounds(10, 11, 608, 398);
        contentPane.add(panelPlane);

        DefaultListModel listModel = new DefaultListModel();
        for (int i = 0; i < countLevel; i++) {
            listModel.addElement("\u0423\u0440\u043E\u0432\u0435\u043D\u044C " + Integer.toString(i + 1));
        }

        JList list = new JList(listModel);
        list.setBounds(648, 11, 206, 107);
        contentPane.add(list);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setSelectedIndex(0);

        ListSelectionListener listSelectionListener = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //panelDepot.setList(list);
                panelPlane.repaint();
            }
        };
        list.addListSelectionListener(listSelectionListener);

        hangar = new MultiLevelHangar(countLevel, panelPlane.getWidth(), panelPlane.getHeight());
        panelPlane.setHangar(hangar);
        panelPlane.setList(list);

        JButton buttonSetFighterPlane = new JButton("Ñîçäàòü èñòðåáèòåëü");
        buttonSetFighterPlane.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Color firstColor = JColorChooser.showDialog(null, "Choose a Color", Color.WHITE);
                Color secondColor = JColorChooser.showDialog(null, "Choose a Color", Color.WHITE);
                transport = new FighterPlane(100 , 1000 , firstColor, secondColor, true, true);
                int place = hangar.getAt(list.getSelectedIndex()).addTransport(transport);
                if (place == -1) {
                    JOptionPane.showMessageDialog(null, "ÐÐµÑ‚ ÑÐ²Ð¾Ð±Ð¾Ð´Ð½Ñ‹Ñ… Ð¼ÐµÑÑ‚");
                }

                panelPlane.repaint();
            }
        });
        buttonSetFighterPlane.setBounds(648, 181, 206, 40);
        contentPane.add(buttonSetFighterPlane);

        JButton buttonSetAirplane = new JButton("Ñîçäàòü ñàìîë¸ò");
        buttonSetAirplane.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Color firstColor = JColorChooser.showDialog(null, "Choose a Color", Color.WHITE);
                transport = new Airplane(100, 1000 , firstColor);

                int place = hangar.getAt(list.getSelectedIndex()).addTransport(transport);
                if (place == -1) {
                    JOptionPane.showMessageDialog(null, "ÐÐµÑ‚ ÑÐ²Ð¾Ð±Ð¾Ð´Ð½Ñ‹Ñ… Ð¼ÐµÑÑ‚");
                }
                panelPlane.repaint();
            }
        });
        buttonSetAirplane.setBounds(648, 130, 206, 40);
        contentPane.add(buttonSetAirplane);

        JPanel panelGroupElements = new JPanel();
        panelGroupElements.setBounds(648, 232, 206, 177);
        contentPane.add(panelGroupElements);
        panelGroupElements.setLayout(null);

        JLabel lblNewLabel = new JLabel("\u041C\u0435\u0441\u0442\u043E");
        lblNewLabel.setBounds(10, 14, 31, 14);
        panelGroupElements.add(lblNewLabel);

        JPanelDraw panelTakePlane = new JPanelDraw();
        panelTakePlane.setBounds(10, 73, 186, 93);
        panelGroupElements.add(panelTakePlane);

        JButton buttonTakePlane = new JButton("Çàáðàòü ñàìîë¸ò");
        buttonTakePlane.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (list.getSelectedIndex() == -1) {
                    return;
                }

                int numberOfPlace = 0;
                try {
                    numberOfPlace = Integer.parseInt(textField.getText());
                } catch (Exception ex)
                {
                    textField.setText("Invalid input");
                    return;
                }

                if (numberOfPlace >= hangar.getAt(list.getSelectedIndex())._places.size() || numberOfPlace < 0)
                {
                    textField.setText("Invalid input");
                    return;
                }

                transport = hangar.getAt(list.getSelectedIndex()).removeTransport(numberOfPlace);
                if (transport != null) {
                    transport.setPosition(5, 5, panelTakePlane.getWidth(), panelTakePlane.getHeight());
                }

                panelTakePlane.setTransport(transport);
                panelTakePlane.repaint();
                panelPlane.repaint();
            }
        });
        buttonTakePlane.setBounds(20, 39, 176, 23);
        panelGroupElements.add(buttonTakePlane);

        textField = new JTextField();
        textField.setBounds(60, 11, 136, 20);
        panelGroupElements.add(textField);
        textField.setColumns(10);
    }
}

