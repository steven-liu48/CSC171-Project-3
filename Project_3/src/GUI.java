//Project 3

//Xiaoxiang Liu (Steven)
//xliu102
//MW 2:00PM - 3:15PM

//Grant Yap
//gyap
//TH 6:15PM - 7:30PM

//Statement: We did not collaborate with other people on this project.

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//It's doing everything!
public class GUI extends JFrame implements ActionListener, ChangeListener{
	
	//Instance Variables
	double angle = 0;
	double velocity = 0;
	double explodeTime = 0;
	double time = 0;
	Color colorChoice = Color.BLACK;
	String explosionChoice = "Choose explosion type";
	
	//GUI elements
	JFrame frame;
	JPanel panel;
	JButton launch;
	JButton clear;
	JSlider ang;
	JSlider vlc;
	JSlider tim;
	JComboBox color;
	JComboBox explosion;
	
	//Main method
	public static void main (String[] args) {
		GUI a = new GUI();
		a.go();
	}
	
	//creates a user interface and does everything
	public void go() {

		//The main frame
		frame = new JFrame();
		
		//A panel for buttons with GridLayout
		panel = new JPanel();
		GridLayout g = new GridLayout(10,1);
		panel.setLayout(g);
		
		//Label for time slider
		JLabel ExplosionTime = new JLabel("     Choose the Explosion Time: ");
		panel.add(ExplosionTime);
		//Time slider
		tim = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
		tim.setMajorTickSpacing(2);
		tim.setMinorTickSpacing(1);
		tim.setPaintTicks(true);
		tim.setPaintLabels(true);
		tim.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		tim.addChangeListener(this);
		panel.add(tim);
		
		//Label for angle slider
		JLabel Angle = new JLabel("     Choose the Angle: ");
		panel.add(Angle);
		//Angle slider
		ang = new JSlider(JSlider.HORIZONTAL, 0, 90, 0);
		ang.setMajorTickSpacing(30);
		ang.setMinorTickSpacing(1);
		ang.setPaintTicks(true);
		ang.setPaintLabels(true);
		ang.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		ang.addChangeListener(this);
		panel.add(ang);
		
		//Label for Velocity slider
		JLabel Velocity = new JLabel("     Choose the Velocity: ");
		panel.add(Velocity);
		//Velocity slider
		vlc = new JSlider(JSlider.HORIZONTAL, 0, 50, 0);
		vlc.setMajorTickSpacing(10);
		vlc.setMinorTickSpacing(1);
		vlc.setPaintTicks(true);
		vlc.setPaintLabels(true);
		vlc.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		vlc.addChangeListener(this);
		panel.add(vlc);
		
		//Color ComboBox
		color = new JComboBox();
		color.addItem("Choose firework color");
		color.addItem("Red");
		color.addItem("Blue");
		color.addItem("Green");
		color.addItem("Pink");
		color.addItem("Orange");
		color.addActionListener(this);
		panel.add(color);
		
		//Explosion ComboBox
		explosion = new JComboBox();
		explosion.addItem("Choose explosion type");
		explosion.addItem("Snowflake");
		explosion.addItem("Target");
		explosion.addItem("Isreal");
		explosion.addItem("Rings");
		explosion.addItem("Words");
		explosion.addItem("Random Rings"); //Ramdom!
		explosion.addItem("Random Dots");  //Ramdom!
		explosion.addItem("Random Lines"); //Ramdom!
		explosion.addActionListener(this);
		panel.add(explosion);
		
		//Launch Button
		launch = new JButton("Launch");
		launch.addActionListener(this);
		launch.setPreferredSize(new Dimension(20,20));
		panel.add(launch);
		
		//Add panel into BorderLayout
		frame.add(BorderLayout.WEST, panel);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	//Class canvas for drawing things
	class canvas extends JComponent{
		private static final long serialVersionUID = 1L;
		public void paintComponent(Graphics g) {
			
			//Change color
			g.setColor(colorChoice);
			
			//Draw trail
			for(int i = 0; i < 200; i += 1) {
				time = explodeTime / 200 * i;
				double X = velocity * Math.cos((angle / 180) * Math.PI) * time;
				double Y = velocity * Math.sin((angle / 180) * Math.PI) * time - 1.0/2.0 * 9.8 * time * time;
				g.drawOval((int)(5 * X), (int)(getHeight() - 5 * Y), 1, 1);
			}
			int endX = 5 * (int) (velocity * Math.cos((angle / 180) * Math.PI) * explodeTime);
			int endY = getHeight() - 5 * (int) (velocity * Math.sin((angle / 180) * Math.PI) * explodeTime - 1.0/2.0 * 9.8 * explodeTime * explodeTime);
			
			//Draw explosion
			//Draw Snowflake
			if(explosionChoice.equals("Snowflake")) {
				g.drawLine(endX, endY, endX + 20, endY);
				g.drawLine(endX, endY, endX, endY + 20);
				g.drawLine(endX, endY, endX - 20, endY);
				g.drawLine(endX, endY, endX, endY - 20);
				g.drawLine(endX, endY, endX + 14, endY - 14);
				g.drawLine(endX, endY, endX - 14, endY - 14);
				g.drawLine(endX, endY, endX + 14, endY + 14);
				g.drawLine(endX, endY, endX - 14, endY + 14);
			}
			//Draw Target
			else if(explosionChoice.equals("Target")) {
				g.drawOval(endX - 20, endY - 20, 40, 40);
				g.drawOval(endX - 15, endY - 15, 30, 30);
				g.drawOval(endX - 25, endY - 25, 50, 50);
			}
			//Draw Random Dots
			else if(explosionChoice.equals("Random Dots")) {
				for(int i = 0; i < 50; i++) {
					double x = Math.random() * 60 - 30;
					double y = Math.random() * 60 - 30;
					g.drawOval((int)(endX - x), (int)(endY - y), 2, 2);
				}
				
				
				//Easter Egg!!!!!!!!
				//(For extra credits)
				//LOL
				if(velocity == 0 && angle == 0 && explodeTime == 0) {
					for(int i = 0; i < 50000; i++) {
						double x = Math.random() * 2000;
						double y = Math.random() * 2000;
						g.drawOval((int)(x), (int)(y), 2, 2);
					}
					JFrame frame2 = new JFrame("Easter Egg");
					frame2.setSize(600, 100);
					frame2.setLayout(new FlowLayout());
					JLabel egg = new JLabel(" BOOOOOOOM!!! You found an egg! Remember to set parameters next time! Have fun!!!");
					JLabel egg2 = new JLabel("Best, Grant and Steven");
					frame2.add(egg);
					frame2.add(egg2);
					frame2.setVisible(true);
				}
				
				
			}
			//Draw Random Lines
			else if(explosionChoice.equals("Random Lines")) {
				for(int i = 0; i < 40; i++) {
					double x = Math.random() * 60 - 30;
					double y = Math.random() * 60 - 30;
					double x1 = Math.random() * 60 - 30;
					double y1 = Math.random() * 60 - 30;
					g.drawLine((int)(endX - x), (int)(endY - y), (int)(endX - x1), (int)(endY - y1));
				}
			}
			//Draw Isreal Icon
			else if(explosionChoice.equals("Isreal")) {
				g.drawLine(endX, endY - 50, (int)(endX - 25 * 1.6), endY + 25);
				g.drawLine(endX, endY - 50, (int)(endX + 25 * 1.6), endY + 25);
				g.drawLine((int)(endX - 25 * 1.6), endY + 25, (int)(endX + 25 * 1.6), endY + 25);
				g.drawLine(endX, endY + 50, (int)(endX + 25 * 1.6), endY - 25);
				g.drawLine(endX, endY + 50, (int)(endX - 25 * 1.6), endY - 25);
				g.drawLine((int)(endX + 25 * 1.6), endY - 25, (int)(endX - 25 * 1.6), endY - 25);
			}
			//Draw Rings
			else if(explosionChoice.equals("Rings")) {
				g.drawOval(endX - 14, endY - 30, 35, 35);
				g.drawOval(endX - 29, endY - 10, 35, 35);
				g.drawOval(endX - 4, endY - 10, 35, 35);
			}
			//Draw Random Rings
			else if(explosionChoice.equals("Random Rings")) {
				for(int i = 0; i < 15; i++) {
					double x = Math.random() * 40 - 20;
					double y = Math.random() * 40 - 20;
					double r = Math.random() * 40;
					g.drawOval((int)(endX - x), (int)(endY - y), (int)(r), (int)(r));
				}
			}
			//Draw Words
			else if(explosionChoice.equals("Words")) {
				g.drawString("LONG LIVE PAWLICKI!!!", endX - 60, endY);
			}
			//If the user does not make a choice, there will not be an explosion
			else if(explosionChoice.equals("Choose explosion type")){
				
			}
		}
	}
	
	//Create a new canvas object and draws things
	canvas a = new canvas();
	
	//Action Listener for button and boxes
	public void actionPerformed(ActionEvent e) {
		//Launch Button
		if (e.getSource() == launch) {
			frame.add(a);
			frame.setVisible(true);
			frame.repaint();
		}
		//Color ComboBox
		else if(e.getSource() == color) {
			if(color.getSelectedItem().equals("Red")) {
				colorChoice = Color.RED;
			}
			else if(color.getSelectedItem().equals("Blue")) {
				colorChoice = Color.BLUE;
			}
			else if(color.getSelectedItem().equals("Green")) {
				colorChoice = Color.GREEN;
			}
			else if(color.getSelectedItem().equals("Pink")) {
				colorChoice = Color.PINK;
			}
			else if(color.getSelectedItem().equals("Orange")) {
				colorChoice = Color.ORANGE;
			}
			System.out.println(colorChoice);
		}
		//Explosion ComboBox
		else if(e.getSource() == explosion) {
			explosionChoice = (String) explosion.getSelectedItem();
			System.out.println(explosionChoice);
			
		}
	}
	
	//Change Listener for sliders
	@Override
	public void stateChanged(ChangeEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource() == tim) {
			explodeTime = tim.getValue();
			System.out.println(explodeTime);
		}
		else if(event.getSource() == ang) {
			angle = ang.getValue();
			System.out.println(angle);
		}
		else {
			velocity = vlc.getValue();
			System.out.println(velocity);
		}
	}
}