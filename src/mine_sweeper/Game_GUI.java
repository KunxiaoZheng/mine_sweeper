package mine_sweeper;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Game_GUI extends JFrame implements ActionListener {

	ArrayList<JButton> buttonList = new ArrayList<JButton>();

	JPanel topPanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	JPanel bottomPanel = new JPanel();

	JButton easyButton = new JButton("Easy");
	JButton midButton = new JButton("Mid");
	JButton hardButton = new JButton("Hard");
	JButton resetButton = new JButton("Reset");
	
	JButton temp;

	Game_GUI(int x, int y) {

		super("Mine Sweeper");
		setBounds(700, 400, 300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container contain = this.getContentPane();

		topPanel.setLayout(new FlowLayout());
		topPanel.setPreferredSize(new Dimension(300, 40));
		
		topPanel.add(easyButton);
		topPanel.add(midButton);
		topPanel.add(hardButton);
		topPanel.add(resetButton);

		bottomPanel.setPreferredSize(new Dimension(x*10+1, y*1+1));
		buttonPanel.setLayout(new GridLayout(x, y, 1, 1));
		for (int i = 0; i < x * y; i++) {
			temp = new JButton();
			buttonList.add(temp);
			buttonPanel.add(buttonList.get(i));
		}


		bottomPanel.setPreferredSize(new Dimension(300, 40));
		
		contain.add(topPanel, BorderLayout.NORTH);
		contain.add(buttonPanel, BorderLayout.CENTER);
		contain.add(bottomPanel, BorderLayout.SOUTH);
		
		this.setPreferredSize(new Dimension(300, 500));
		this.pack();
		setVisible(true);
	}

	public static void main(String args[]) {
		new Game_GUI(9, 9);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
