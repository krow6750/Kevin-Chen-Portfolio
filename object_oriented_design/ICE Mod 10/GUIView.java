
/*
 * Graphical user interface using Java Swing
 * 3/30/23
 * -Dr. G
 * */

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;



public class GUIView extends JFrame implements IView
{
	
	JPanel drawingPain;
	EventController c;
	final int DOT_SIZE = 50;
	
	public GUIView() {
		//Set some default values and actions
		this.setTitle("Fill Demo");
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		//JPanel
		drawingPain = new JPanel();
		drawingPain.setBounds(123, 0, 461, 561);
		getContentPane().add(drawingPain);
		drawingPain.setLayout(null);
		
		//Buttons
		JButton btnLoad = new JButton("LoadData");
		btnLoad.setBounds(10, 11, 89, 23);
		getContentPane().add(btnLoad);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(10, 45, 89, 23);
		getContentPane().add(btnReset);
		JButton btnRandomizer = new JButton("Randomizer");
		btnRandomizer.setBounds(10, 79, 89, 23);
		getContentPane().add(btnRandomizer);
		
		
		//Event Handlers
		
		//Screen click
		drawingPain.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//The screen x and y aren't the same as the index x and y
				//This took me a few minutes to realize :)
				int y = e.getX();
				int x = e.getY();
				
				c.callBackMutate(x/DOT_SIZE, y/DOT_SIZE);				
			}
		});
		
		
		//Button click to load data
		btnLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				c.callBackLoad();
			}
		});
		
		//button click to reset data
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				c.callBackReset();
			}
		});
		
		//button click to randomize data
		btnRandomizer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				c.callBackRandomize();
			}
		});
		
		
	}
	

	//Connection to the host controller
	public void setController(EventController c) {
		this.c = c;
		}
	
	//Show the window
	public void showWindow() {
		this.setVisible(true);
		}
	

	//Update the window
	public void update(int[][] data) {
		int xPos = 0;
		int yPos = 0;
		Color c;
		
		
		for (int x = 0; x < data.length; ++x)
		{
			for (int y = 0; y< data[x].length; ++y)
			{
				c = getColor(data[x][y]);
								
				drawOval(xPos,yPos, DOT_SIZE, DOT_SIZE, c);
				xPos+=DOT_SIZE; 
			}	
			xPos = 0;
			yPos+=DOT_SIZE;
		}
	}
	
	
	//Helper function for drawing ovals
		private void drawOval(int x, int y, int width, int height, Color c){
				Graphics g = drawingPain.getGraphics();
				g.setColor(c);
				g.fillOval(x, y, width, height);
		}
	
	//Helper function for color translation 
	private Color getColor(int num) {
		
		switch (num) {
			case 0: 
				return Color.black; 
			case 1: 
				return Color.blue; 
			case 2: 
				return Color.red; 
			case 3: 
				return Color.green; 
			default: 
				return  Color.gray;
		}
		
	}
}
	
	
