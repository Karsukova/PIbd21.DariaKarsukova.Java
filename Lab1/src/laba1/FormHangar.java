package laba1;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FormHangar extends JFrame {

    JFrame frame;
    private JPanel contentPane;
    private JTextField textField;
    private static JList list;

    private IFighter transport;
    private MultiLevelHangar hangar;

    private final int countLevel = 5;

    private FormPlaneConfig select;
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

    public void getTrain() {
        select = new FormPlaneConfig(frame);
        if (select.res()) {
        	IFighter plane = select.plane;
            int place = hangar.getAt(list.getSelectedIndex()).addTransport(plane);
            if (place < 0) {
                JOptionPane.showMessageDialog(null, "No free place");
            }
        }
    }

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

        list = new JList(listModel);
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

        JButton buttonAddPlane = new JButton("Добавить самолёт");
        buttonAddPlane.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                getTrain();
                panelPlane.repaint();
            }
        });
        buttonAddPlane.setBounds(648, 130, 206, 70);
        contentPane.add(buttonAddPlane);

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

        JButton buttonTakePlane = new JButton("Забрать самолёт");
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

