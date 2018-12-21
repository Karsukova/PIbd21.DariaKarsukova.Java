package laba1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class FormPlane extends JFrame {

	private JPanel contentPane;
	private FighterPlane fighter;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormPlane frame = new FormPlane();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	@Override
	public void paint (Graphics g) {
		super.paint(g);
		try {
			if (fighter != null) {
				fighter.DrawFighter(g);
			}
		}
		catch(Exception ex){
			
		}
	}	

	
	public void moveButton(JButton sender) {
		try {
			String name = sender.getToolTipText();	
			switch (name)
			{
            	case "Up":
            		fighter.moveTransport(Direction.Up);
            		break;
            	case "Down":
            		fighter.moveTransport(Direction.Down);
            		break;
            	case "Left":
            		fighter.moveTransport(Direction.Left);
            		break;
            	case "Right":
            		fighter.moveTransport(Direction.Right);
            		break;
			}
			this.repaint();
		}
		catch (Exception ex) {	
			System.out.print("Самолёт не создан");			
		}
	}
	
	/**
	 * Create the frame.
	 */
	public FormPlane() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
					
		
		JButton buttonCreate = new JButton("\u0421\u043E\u0437\u0434\u0430\u0442\u044C");
		buttonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
	            try {
					fighter = new FighterPlane(100 + (int) (Math.random() * 300), 1000 + (int) (Math.random() * 2000), Color.YELLOW, Color.RED, true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	            try {
					fighter.setPosition(70 + (int) (Math.random() * 160), 70 + (int) (Math.random() * 160), FormPlane.this.getWidth(), FormPlane.this.getHeight());					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	            FormPlane.this.repaint(); 
			}
		});
		buttonCreate.setBounds(10, 11, 89, 42);
		contentPane.add(buttonCreate);		
		
		
		JButton buttonUp = new JButton("");
		buttonUp.setToolTipText("Up");
		buttonUp.setIcon(new ImageIcon(FormPlane.class.getResource("/img/up.png")));
		buttonUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				moveButton(buttonUp);	
			}
		});
		buttonUp.setBounds(515, 215, 65, 65);
		contentPane.add(buttonUp);
		
		JButton buttonLeft = new JButton("");
		buttonLeft.setToolTipText("Left");
		buttonLeft.setIcon(new ImageIcon(FormPlane.class.getResource("/img/left.png")));
		buttonLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				moveButton(buttonLeft);	
			}
		});
		buttonLeft.setBounds(440, 291, 65, 65);
		contentPane.add(buttonLeft);
		
		JButton buttonDown = new JButton("");
		buttonDown.setToolTipText("Down");
		buttonDown.setIcon(new ImageIcon(FormPlane.class.getResource("/img/down.png")));
		buttonDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				moveButton(buttonDown);			
			}
		});
		buttonDown.setBounds(515, 291, 65, 65);
		contentPane.add(buttonDown);
		
		JButton buttonRight = new JButton("");
		buttonRight.setToolTipText("Right");
		buttonRight.setIcon(new ImageIcon(FormPlane.class.getResource("/img/right.png")));
		buttonRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				moveButton(buttonRight);
			}
		});
		buttonRight.setBounds(590, 291, 65, 65);
		contentPane.add(buttonRight);
	}
}
