package Pong;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;

public class PongPanel extends JPanel
{
	
	private int width, height;
	
	
	private int px, py, pw, ph;
	private Rectangle playerR;
	private boolean u, d;
	private int score = 0;
	
	
	private int ex, ey, ew, eh, movex, movey, goY, goX;
	private Timer t;
	private Rectangle enemyR;
	
	private int lx, ly, lw, lh;
	
	private Color c;
	
	
	
	private String message, bounds, Score, win;
	
	
	public PongPanel(int width, int height)
	{
		this.width = width;
		this.height = height;
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(Color.black);
		
		message = "";
		bounds = "";
		Score = "";
		win = "";
		
		px = 0;
		py = 0;
		pw = 10;
		ph = 75;
		goY = 30;
		goX = 30;
		
		
		ex = width/2;
		ey = height/2;
		ew = 40;
		eh = 40;
		movex = 10;
		movey = 10;
		c = Color.white;
		
		
		lx = 0;
		ly = 465;
		lw = 800;
		lh = 10;
		
		
		playerR = new Rectangle(px, py, pw, ph);
		enemyR = new Rectangle(ex, ey, ew, eh);
		
		u = d = false;
		
		this.addKeyListener(new MML());
		
		this.setFocusable(true);
		
		
		t = new Timer(20, new AL());
		
		
		t.restart();
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.setColor(Color.cyan);
		g.setFont(new Font("Serif Bold", Font.BOLD, 36));
		g.drawString(win, width/2-60, height/2-40);
		
		g.setColor(Color.green);
		g.setFont(new Font("Serif Bold", Font.BOLD, 20));
		g.drawString(Score, width/2-60, height/2-10);
		
		g.setColor(Color.yellow);
		g.setFont(new Font("Serif Bold", Font.BOLD, 30));
		g.drawString(bounds, width/2-60, height/2-40);
		
		g.setColor(Color.red);
		g.setFont(new Font("Serif Bold", Font.BOLD, 36));
		g.drawString(message, width/2-60, height/2-40);
		
		g.setColor(Color.red);
		g.fillRect(px, py, pw, ph);
		
		
		g.setColor(c);
		g.fillOval(ex, ey, ew, eh);
		
		
		g.setColor(Color.blue);
		g.fillRect(lx, ly, lw, lh);
		
		
		Shape clip = null;
		g.setClip(clip);
		
	}
	
	public void update()
	{
		if(u)
			py -= goY;
		if(d)
			py += goX;
		
		repaint();
	}
	
	private class MML implements KeyListener
	{

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) 
		{
			if(e.getKeyCode() == KeyEvent.VK_UP)
				u = true;
			if(e.getKeyCode() == KeyEvent.VK_DOWN)
				d = true;
			update();
		}

		@Override
		public void keyReleased(KeyEvent e) 
		{
			if(e.getKeyCode() == KeyEvent.VK_UP)
				u = false;
			if(e.getKeyCode() == KeyEvent.VK_DOWN)
				d = false;
			update();
			
		}

		
		
		
	}
	
	private class AL implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
		
		ex += movex;
		ey += movey;
		
		
		enemyR.setBounds(ex, ey, ew, eh);
		
		
		if(ex > width - ew)
		{
			
			movex *= -1;
		}
		
		
		if(ey > height - eh || ey < 0)
		{
			movey *= -1;
		}
		
			if(ex < 0)
				c = Color.white;
		
			if(ex > width - ew)
				c = Color.orange;
			
			if(ey < 0)
				c = Color.magenta;
			
			if(ey > height - eh)
				c = Color.cyan;
		
		Score = "You have "+score+" hits";
		
		playerR.setBounds(px, py, pw, ph);
		
		if(playerR.intersects(enemyR))
		{
			score = score + 1;
			movey *= 1;
			movex *= -1;
		}
		
		if(score == 5)
		{
			win = "YOU WIN";
			px = -100;
			py = -100;
			goY = 0;
			goX = 0;
			if(ex < 0)
			{
				movex *= -1;
			}
				
		}
		
		if(ex < 0 && score == 0)
		{
			message = "YOUR TRASH";
			px = 0;
			py = 0;
			ex = 0;
			ey = 0;
			t.stop();
		}
		if(ex < 0 && score < 5)
		{
			message = "YOU LOST";
			px = 0;
			py = 0;
			ex = 0;
			ey = 0;
			t.stop();
		}
		
		
		if(py > 400 && score <= 4 || py < 0 && score <= 4)
		{
			bounds = "You Went Out Of Bounds";
			px = 0;
			py = 0;
			ex = 0;
			ey = 0;
			t.stop();
		}
		
		
		
		
		repaint();
			
		}
	}
	
	
	
	
}