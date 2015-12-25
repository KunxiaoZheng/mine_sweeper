package mine_sweeper;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Game_GUI extends JFrame implements ActionListener, MouseListener {

	controller gameControl = new controller();

	ArrayList<JButton> buttonList = new ArrayList<JButton>();

	JPanel topPanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	JPanel bottomPanel = new JPanel();

	JButton easyButton = new JButton("Easy");
	JButton midButton = new JButton("Mid");
	JButton hardButton = new JButton("Hard");
	JButton resetButton = new JButton("Reset");

	JButton temp;

	int currentGUISizeX;
	int currentGUISizeY;

	Game_GUI(int x, int y) {

		super("Mine Sweeper");

		gameControl.new_game(x, y);
		gameControl.printMap();
		currentGUISizeX = x;
		currentGUISizeY = y;

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

		// topPanel.setLayout(new FlowLayout());
		// topPanel.setPreferredSize(new Dimension(300, 40));

		topPanel.add(easyButton);
		topPanel.add(midButton);
		topPanel.add(hardButton);
		topPanel.add(resetButton);

		buttonPanel.setLayout(new GridLayout(x, y, 1, 1));

		for (int i = 0; i < x * y; i++) {
			temp = new JButton();
			buttonList.add(temp);
			buttonPanel.add(buttonList.get(i));
			temp.addActionListener(this);
			temp.addMouseListener(this);
		}

		bottomPanel.setPreferredSize(new Dimension(300, 40));

		easyButton.addActionListener(this);
		midButton.addActionListener(this);
		hardButton.addActionListener(this);
		resetButton.addActionListener(this);

		contain.add(topPanel, BorderLayout.NORTH);
		contain.add(buttonPanel, BorderLayout.CENTER);
		contain.add(bottomPanel, BorderLayout.SOUTH);

		this.pack();
		setVisible(true);
	}

	public static void main(String args[]) {
		new Game_GUI(10, 10);
	}

	// top row buttons' action listener
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == easyButton) {
			this.dispose();
			new Game_GUI(10, 10);
		} else if (e.getSource() == midButton) {
			this.dispose();
			new Game_GUI(20, 20);
		} else if (e.getSource() == hardButton) {
			this.dispose();
			new Game_GUI(30, 30);
		} else if (e.getSource() == resetButton) {
			this.dispose();
			new Game_GUI(currentGUISizeX, currentGUISizeY);
		} else if (e.getSource() instanceof JButton) {
			Object source = e.getSource();
			int position = buttonList.indexOf(source);
			if (gameControl.checkForBomb(position / currentGUISizeX, position
					% currentGUISizeX)) {
				JOptionPane.showMessageDialog(this, "hit a bomb");
				this.dispose();
				new Game_GUI(currentGUISizeX, currentGUISizeY);
			} else {
				clickOnSafeButton((JButton) source, position / currentGUISizeX,
						position % currentGUISizeX);
			}
		}
	}

	private void clickOnSafeButton(JButton button, int x, int y) {
		int surroundingBomb = gameControl.checkSourrending(x, y);
		if (surroundingBomb != 0) {
			String temp = "" + surroundingBomb;
			button.setText(temp);
			button.setFont(new Font("Courier", Font.BOLD, 10));
			button.setMargin(new Insets(0, 0, 0, 0));
			button.setPreferredSize(new Dimension(25, 25));
		}
		button.setEnabled(false);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() instanceof JButton
				&& SwingUtilities.isRightMouseButton(e)) {
			Object source = e.getSource();
			if (((JButton) source).getIcon()==null) {
				if (((JButton) source).isEnabled()) {
					int position = buttonList.indexOf(source);
					ImageIcon temp = new ImageIcon(this.getClass().getResource(
							"/flag.png"));
					((JButton) source).setIcon(temp);
					System.out.print(position / currentGUISizeX);
					System.out.print(position % currentGUISizeX);
				}
			}else{
				((JButton) source).setIcon(null);
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
