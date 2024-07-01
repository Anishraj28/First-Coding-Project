package Pong;

import javax.swing.JFrame;

import Pong.PongPanel;

public class PongFrame 
{

	public static void main(String[] args) 
	{
		JFrame frame = new JFrame("Pong");
		frame.setDefaultCloseOperation(3);
		frame.getContentPane().add(new PongPanel(800, 480));
		frame.pack();
		frame.setVisible(true);

	}

}
