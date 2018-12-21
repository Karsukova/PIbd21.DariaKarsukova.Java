package laba1;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.datatransfer.*;
import java.io.IOException;

public class FormPlaneConfig extends JDialog {

    private JPanel contentPane;
    public boolean r;
    public IFighter plane;

    /**
     * Launch the application.
     */

    /**
     * Create the frame.
     */
    public boolean res() {
        setVisible(true);
        return r;
    }

    public Color selectColor(String s) {
        switch (s) {
            case "white":
                return Color.WHITE;
            case "black":
                return Color.BLACK;
            case "red":
                return Color.RED;
            case "green":
                return Color.GREEN;
            case "orange":
                return Color.ORANGE;
            case "pink":
                return Color.PINK;
            case "blue":
                return Color.BLUE;
            case "yellow":
                return Color.YELLOW;
        }

        return null;
    }

    public void draw(JPanel panel, IFighter plane) {
        if (plane != null) {
            Graphics gr = panel.getGraphics();
            gr.clearRect(0, 0, panel.getWidth(), panel.getHeight());
            plane.setPosition(30, 35, panel.getWidth(), panel.getHeight());
            plane.DrawFighter(gr);
        }
    }

    public FormPlaneConfig(JFrame parent) {
        super(parent, true);

        this.getContentPane().setBackground(SystemColor.controlHighlight);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setBounds(100, 100, 433, 292);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton buttonAdd = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
        buttonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                r = true;
                dispose();
            }
        });
        buttonAdd.setBounds(10, 145, 122, 49);
        contentPane.add(buttonAdd);

        JButton buttonCancel = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                r = false;
                dispose();
            }
        });
        buttonCancel.setBounds(10, 199, 122, 49);
        contentPane.add(buttonCancel);

        Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        JLabel lblFighterPlane = new JLabel("FighterPlane");
        lblFighterPlane.setHorizontalAlignment(SwingConstants.CENTER);
        lblFighterPlane.setBorder(solidBorder);
        lblFighterPlane.setBounds(10, 11, 122, 56);

        contentPane.add(lblFighterPlane);

        JLabel lblAirplane = new JLabel("Airplane");
        lblAirplane.setHorizontalAlignment(SwingConstants.CENTER);
        lblAirplane.setBorder(solidBorder);
        lblAirplane.setBounds(10, 78, 122, 56);
        contentPane.add(lblAirplane);

        JPanel panelBorder = new JPanel();
        panelBorder.setBounds(144, 11, 157, 123);
        panelBorder.setBorder(solidBorder);
        panelBorder.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(1, 1, 155, 121);

        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        panel.setDropTarget(new DropTarget() {
            public void drop(DropTargetDropEvent e) {
                try {
                    for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
                        String str = (String)e.getTransferable().getTransferData(df);
                        if (e.getTransferable().getTransferData(df) == "Airplane") {                            
                            plane = new Airplane(100, 100, Color.WHITE);
                        } else if (e.getTransferable().getTransferData(df) == "FighterPlane") {
                            plane = new FighterPlane(100, 100, Color.WHITE, Color.BLACK,
                                    true, true);                            
                        }
                        draw(panel, plane);
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }

            public void dragEnter(DropTargetDragEvent e) {
                for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
                    try {
                        if (e.getTransferable().getTransferData(df) instanceof String)
                            e.acceptDrag(DnDConstants.ACTION_COPY);
                        else
                            e.acceptDrag(DnDConstants.ACTION_NONE);
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        });
        panelBorder.add(panel);
        contentPane.add(panelBorder);

        JLabel lblMainColor = new JLabel("\u041E\u0441\u043D\u043E\u0432\u043D\u043E\u0439 \u0446\u0432\u0435\u0442");
        lblMainColor.setHorizontalAlignment(SwingConstants.CENTER);
        lblMainColor.setBorder(solidBorder);
        lblMainColor.setBounds(142, 145, 159, 49);
        lblMainColor.setDropTarget(new DropTarget() {
            public void drop(DropTargetDropEvent e) {
                if (plane != null) {
                    try {
                        for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
                            plane.setMainColor((selectColor(e.getTransferable().getTransferData(df).toString())));
                            draw(panel, plane);
                        }
                    } catch (Exception ex) {
                        System.out.println(ex + "FF");
                    }
                }
            }

            public void dragEnter(DropTargetDragEvent e) {
                for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
                    try {
                        if (e.getTransferable().getTransferData(df) instanceof String)
                            e.acceptDrag(DnDConstants.ACTION_COPY);
                        else
                            e.acceptDrag(DnDConstants.ACTION_NONE);
                    } catch (UnsupportedFlavorException | IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        });
        contentPane.add(lblMainColor);

        JLabel lblDopColor = new JLabel("\u0414\u043E\u043F\u043E\u043B\u043D\u0438\u0442\u0435\u043B\u044C\u043D\u044B\u0439 \u0446\u0432\u0435\u0442");
        lblDopColor.setHorizontalAlignment(SwingConstants.CENTER);
        lblDopColor.setBorder(solidBorder);
        lblDopColor.setBounds(142, 199, 159, 49);
        lblDopColor.setDropTarget(new DropTarget() {
            public void drop(DropTargetDropEvent e) {
                if (plane != null) {
                    try {
                        for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
                            ((FighterPlane) plane).setDopColor((selectColor(e.getTransferable().getTransferData(df).toString())));
                            draw(panel, plane);
                        }
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }

            public void dragEnter(DropTargetDragEvent e) {
                for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
                    try {
                        if (e.getTransferable().getTransferData(df) instanceof String)
                            e.acceptDrag(DnDConstants.ACTION_COPY);
                        else
                            e.acceptDrag(DnDConstants.ACTION_NONE);
                    } catch (UnsupportedFlavorException | IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        });
        contentPane.add(lblDopColor);

        MouseListener mouseL = new MouseListener() {

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                JComponent jc = (JComponent) e.getSource();
                TransferHandler th = jc.getTransferHandler();
                th.exportAsDrag(jc, e, TransferHandler.COPY);
            }

            @Override
            public void mouseClicked(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }
        };

        lblAirplane.addMouseListener(mouseL);
        lblFighterPlane.addMouseListener(mouseL);
        lblAirplane.setTransferHandler(new TransferHandler("text"));
        lblFighterPlane.setTransferHandler(new TransferHandler("text"));

        JPanel panelWhite = new JPanel();
        panelWhite.setBackground(Color.WHITE);
        panelWhite.setBounds(311, 11, 40, 40);
        panelWhite.setName("white");
        contentPane.add(panelWhite);

        JPanel panelBlack = new JPanel();
        panelBlack.setBackground(Color.BLACK);
        panelBlack.setBounds(361, 11, 40, 40);
        panelBlack.setName("black");
        contentPane.add(panelBlack);

        JPanel panelRed = new JPanel();
        panelRed.setBackground(Color.RED);
        panelRed.setBounds(311, 78, 40, 40);
        panelRed.setName("red");
        contentPane.add(panelRed);

        JPanel panelOrange = new JPanel();
        panelOrange.setBackground(Color.ORANGE);
        panelOrange.setBounds(361, 78, 40, 40);
        panelOrange.setName("orange");
        contentPane.add(panelOrange);

        JPanel panelPink = new JPanel();
        panelPink.setBackground(Color.PINK);
        panelPink.setBounds(311, 145, 40, 40);
        panelPink.setName("pink");
        contentPane.add(panelPink);

        JPanel panelBlue = new JPanel();
        panelBlue.setBackground(Color.BLUE);
        panelBlue.setBounds(361, 145, 40, 40);
        panelBlue.setName("blue");
        contentPane.add(panelBlue);

        JPanel panelYellow = new JPanel();
        panelYellow.setBackground(Color.YELLOW);
        panelYellow.setBounds(311, 208, 40, 40);
        panelYellow.setName("yellow");
        contentPane.add(panelYellow);

        JPanel panelGreen = new JPanel();
        panelGreen.setBackground(Color.GREEN);
        panelGreen.setBounds(361, 208, 40, 40);
        panelGreen.setName("green");
        contentPane.add(panelGreen);

        panelWhite.addMouseListener(mouseL);
        panelWhite.setTransferHandler(new TransferHandler("name"));

        panelBlack.addMouseListener(mouseL);
        panelBlack.setTransferHandler(new TransferHandler("name"));

        panelRed.addMouseListener(mouseL);
        panelRed.setTransferHandler(new TransferHandler("name"));

        panelOrange.addMouseListener(mouseL);
        panelOrange.setTransferHandler(new TransferHandler("name"));

        panelPink.addMouseListener(mouseL);
        panelPink.setTransferHandler(new TransferHandler("name"));

        panelBlue.addMouseListener(mouseL);
        panelBlue.setTransferHandler(new TransferHandler("name"));

        panelYellow.addMouseListener(mouseL);
        panelYellow.setTransferHandler(new TransferHandler("name"));

        panelGreen.addMouseListener(mouseL);
        panelGreen.setTransferHandler(new TransferHandler("name"));

    }
}
