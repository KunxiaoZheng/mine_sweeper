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
		
		int length = x * 30 + 1;
		int width = y * 30 + 1;

		Container contain = this.getContentPane();
		if (length > 300) {
			this.setPreferredSize(new Dimension(length, width + 80));
		} else {
			this.setPreferredSize(new Dimension(300, width + 80));
		}
		
		topPanel.setLayout(new FlowLayout());
		topPanel.setPreferredSize(new Dimension(300, 40));

		topPanel.add(easyButton);
		topPanel.add(midButton);
		topPanel.add(hardButton);
		topPanel.add(resetButton);

		buttonPanel.setLayout(new GridLayout(x, y, 1, 1));
		for (int i = 0; i < x * y; i++) {
			temp = new JButton();
			buttonList.add(temp);
			buttonPanel.add(buttonList.get(i));
		}

		bottomPanel.setPreferredSize(new Dimension(300, 40));

		easyButton.addActionListener(this);
		
		contain.add(topPanel, BorderLayout.NORTH);
		contain.add(buttonPanel, BorderLayout.CENTER);
		contain.add(bottomPanel, BorderLayout.SOUTH);

		this.pack();
		setVisible(true);
	}

	public static void main(String args[]) {
		new Game_GUI(9, 9);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==easyButton){
			this.dispose();
			new Game_GUI(20,20);
		}else if(e.getSource()==easyButton){
			
		}

	}
}
