package laba1;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

 class FormHangar extends JFrame {
     private MultiLevelHangar hangar;
     private final int countLevel = 5;
     JFrame frame;
     private JMenuBar menuBar;
     private static JList list;
     private FormPlaneConfig select;
     private IFighter transport;

     public void getPlane() {
         select = new FormPlaneConfig(frame);
         if (select.res()) {
             IFighter pl = select.plane;
             int place = hangar.get(list.getSelectedIndex()).addTransport(pl);
             if (place < 0) {
                 JOptionPane.showMessageDialog(null, "No free place");
             }
         }
     }
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

     FormHangar() {
        JFrame frame = new JFrame();
        frame.setBounds(100, 100, 815, 510);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

         JPanelDraw panelTakePlane = new JPanelDraw();
         panelTakePlane.setBounds(642, 257, 210, 220);
         frame.getContentPane().add(panelTakePlane);
         panelTakePlane.setLayout(null);


         JPanelHangar panelHangar = new JPanelHangar();
         hangar = new MultiLevelHangar(countLevel, panelHangar.getWidth(), panelHangar.getHeight());
         panelHangar.setHangar(hangar.get(0));
        panelHangar.setBounds(10, 11, 622, 429);
        frame.getContentPane().add(panelHangar);

         DefaultListModel listModel = new DefaultListModel();
         for (int i = 1; i <= countLevel; i++) {
             listModel.addElement("Уровень " + i);
         }
         list = new JList(listModel);
         list.setBounds(642, 11, 132, 107);
         frame.getContentPane().add(list);
         list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
         list.setSelectedIndex(0);
         list.addListSelectionListener(e -> {
             panelHangar.setHangar(hangar.get(list.getSelectedIndex()));
             panelHangar.repaint();
         });

        JLabel label = new JLabel("Забрать");
        label.setBounds(10, 11, 72, 14);
         panelTakePlane.add(label);

        JLabel lblNewLabel = new JLabel("самолет");
        lblNewLabel.setBounds(10, 27, 93, 14);
         panelTakePlane.add(lblNewLabel);

        JLabel label_1 = new JLabel("Место:");
        label_1.setBounds(10, 55, 46, 14);
         panelTakePlane.add(label_1);

        JTextField textField = new JTextField();
        textField.setBounds(55, 52, 67, 20);
         panelTakePlane.add(textField);
        textField.setColumns(10);

         Font font = new Font("Verdana", Font.PLAIN, 11);
         menuBar = new JMenuBar();
         menuBar.setFont(font);

         JMenu newMenu = new JMenu("Файл");
         newMenu.setFont(font);
         menuBar.add(newMenu);

         JMenuItem saveFileItem = new JMenuItem("Сохранить");
         saveFileItem.setFont(font);
         newMenu.add(saveFileItem);

         JMenuItem loadFileItem = new JMenuItem("Загрузить");
         loadFileItem.setFont(font);
         newMenu.add(loadFileItem);

         JPanelDraw panelPlane = new JPanelDraw();
         panelPlane.setBounds(10, 117, 140, 120);
         panelTakePlane.add(panelPlane);

         saveFileItem.addActionListener(e -> {
                 JFileChooser fileChoser = new JFileChooser();
                 fileChoser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
                 int ret = fileChoser.showDialog(null, "Сохранить файл");
                 if (ret == JFileChooser.APPROVE_OPTION) {
                     File file = fileChoser.getSelectedFile();
                     if (hangar.saveData(file.getAbsolutePath())) {
                         JOptionPane.showMessageDialog(frame, "Сохранение прошло успешно");
                     } else {
                         JOptionPane.showMessageDialog(frame, "Произошла ошибка");
                     }
                 }
         });

         loadFileItem.addActionListener(e -> {
                 JFileChooser fileChoser = new JFileChooser();
                 fileChoser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
                 int ret = fileChoser.showDialog(null, "Открыть файл");
                 if (ret == JFileChooser.APPROVE_OPTION) {
                     File file = fileChoser.getSelectedFile();
                     if (hangar.loadData(file.getAbsolutePath())) {
                         JOptionPane.showMessageDialog(frame, "Загрузка прошло успешно");
                         panelPlane.repaint();
                         panelHangar.repaint();
                     } else {
                         JOptionPane.showMessageDialog(frame, "Произошла ошибка");
                     }
                 }
         });

         frame.setJMenuBar(menuBar);



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

                 if (numberOfPlace >= hangar.get(list.getSelectedIndex())._places.size() || numberOfPlace < 0)
                 {
                     textField.setText("Invalid input");
                     return;
                 }

                 transport = hangar.get(list.getSelectedIndex()).removeTransport(numberOfPlace);
                 if (transport != null) {
                     transport.setPosition(5, 5, panelTakePlane.getWidth(), panelTakePlane.getHeight());
                 }

                 panelTakePlane.setTransport(transport);
                 panelTakePlane.repaint();
                 panelPlane.repaint();
             }
         });
         buttonTakePlane.setBounds(20, 39, 176, 23);

        JButton buttonParkPlane = new JButton();
        buttonParkPlane.addActionListener(e -> {
            getPlane();
            panelHangar.repaint();
        });
        buttonParkPlane.setLayout(null);
        JLabel label3 = new JLabel("Заказать самолёт");
        label3.setBounds(5, 5,120, 15);
        
        buttonParkPlane.add(label3);
        buttonParkPlane.setBounds(642, 175, 132, 62);
        frame.getContentPane().add(buttonParkPlane);
        frame.setVisible(true);
    }
}