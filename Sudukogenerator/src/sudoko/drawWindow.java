package sudoko;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;

public class drawWindow extends JFrame {
	
	public Graphics gw;
	int[] sudokuNr;
	public drawWindow(int[] sudokuNr)
	{
		setSize(410, 460); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		JButton button = new JButton();
		
		button.setVisible(true);
		button.setText("Radmoise");
		button.setLayout(null);
		button.setLocation(0,30);
		button.setSize(100,30);
		this.sudokuNr = sudokuNr;
	}
	
	public void paint(Graphics g)
	{
		gw=g;;
		super.paint(g);
		
		g.setColor(Color.black);
		g.drawRect(50,60,300,300);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); 

		for (int j = 0 ; j<4; j++)
		{
			for (int i = 0 ; i<4; i++)
			{
				g.drawRect(50+(75*i),60+(75*j),75,75);
			}
		}
		int i = 0;
		while (i<16)
		{
			
			int y =0;
			if (i>=0 & i<4)
			{
				y= 115;
				for (int x = 0 ; x<4; x++)
				{
					gw.drawString(String.valueOf(sudokuNr[i]), 70+(75*x), y);
					i++;
					
				}
			}
			if (i>=4 & i<8)
			{
				y= 190;
				for (int x = 0 ; x<4; x++)
				{
					gw.drawString(String.valueOf(sudokuNr[i]), 70+(75*x), y);
					i++;
				}
			}
			if (i>=8 & i<12)
			{
				y= 265;
				for (int x = 0 ; x<4; x++)
				{
					gw.drawString(String.valueOf(sudokuNr[i]), 70+(75*x), y);
					i++;
				}	
			}
			if (i>=12 & i<16)
			{
				y= 340;
				for (int x = 0 ; x<4; x++)
				{
					gw.drawString(String.valueOf(sudokuNr[i]), 70+(75*x), y);
					i++;
				}	
			}
				
			
		}
	}

}
