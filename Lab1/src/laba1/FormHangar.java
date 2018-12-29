package laba1;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

 class FormHangar extends JFrame {
     private MultiLevelHangar hangar;
     private final int countLevel = 5;
     JFrame frame;
     private JMenuBar menuBar;
     private static JList list;
     private FormPlaneConfig select;
     private IFighter transport;
     private Logger logger;

     public void getPlane() {
         select = new FormPlaneConfig(frame);
         if (select.res()) {
        	 try {
             IFighter pl = select.plane;
             int place = hangar.get(list.getSelectedIndex()).addTransport(pl);
             logger.info("Добавлен самолет "+pl.toString()+" на место "+place);
        	 }
        	 catch(HangarOverflowException ex){
                 ex.printStackTrace();
                 JOptionPane.showMessageDialog(null,"Ангар заполнен!");
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

    public FormHangar() {
    	logger = Logger.getLogger(FormHangar.class.getName());
        try {
            FileHandler fh = new FileHandler("C:\\Users\\Дарья\\Documents\\logger.txt");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        }catch(IOException e){
            e.printStackTrace();
        }
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


        

        JLabel label_1 = new JLabel("Место:");
        label_1.setBounds(10, 55, 106, 14);
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
         panelPlane.setBounds(30, 117, 190, 120);
         panelTakePlane.add(panelPlane);

         saveFileItem.addActionListener(e -> {
                 JFileChooser fileChoser = new JFileChooser();
                 if(fileChoser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
                     try {
                         File file = fileChoser.getSelectedFile();
                         hangar.saveData(file.getPath());
                         JOptionPane.showMessageDialog(null, "Сохранение прошло успешно!");
                         logger.info("Сохранено в файл "+file.getPath());
                     }catch(Exception ex){
                         JOptionPane.showMessageDialog(null, "Не сохранилось");
                         ex.printStackTrace();
                     }
                 }
                 
         });

         loadFileItem.addActionListener(e -> {
                 JFileChooser fileChoser = new JFileChooser();
                 if(fileChoser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
                     try {
                         File file = fileChoser.getSelectedFile();
                         hangar.loadData(file.getPath());
                         JOptionPane.showMessageDialog(null, "Загрузка прошла успешно!");
                         logger.info("Загружено из файла "+ file.getPath());
                     }catch(Exception ex){
                         JOptionPane.showMessageDialog(null, "Не загрузилось");
                         ex.printStackTrace();
                     }
                 }
         });

         frame.setJMenuBar(menuBar);



         JButton buttonTakePlane = new JButton("Забрать самолёт");
         buttonTakePlane.setBounds(10, 109, 156, 23);
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
                 try{
                 transport = hangar.get(list.getSelectedIndex()).removeTransport(numberOfPlace);
					if (transport != null) {
						transport.setPosition(5, 5, buttonTakePlane.getWidth(), buttonTakePlane.getHeight());
						logger.info("Изъят самолет " + transport.toString() + " с места " + numberOfPlace);
					}
                 } catch(HangarNotFoundException ex){
                     JOptionPane.showMessageDialog(null,"Не найдено!");
                 }

                 panelTakePlane.setTransport(transport);
                 panelTakePlane.repaint();
                 panelPlane.repaint();
             }
         });
         panelTakePlane.add(buttonTakePlane);

        
         
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