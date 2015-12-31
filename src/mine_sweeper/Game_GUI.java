package mine_sweeper;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.EventObject;

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

	static boolean endGame = false;

	Game_GUI(int x, int y) {

		super("Mine Sweeper");

		gameControl.new_game(x, y);
		gameControl.printMap();
		currentGUISizeX = x;
		currentGUISizeY = y;
		endGame=false;

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
			temp.setFont(new Font("Courier", Font.BOLD, 10));
			temp.setMargin(new Insets(0, 0, 0, 0));
			temp.setPreferredSize(new Dimension(25, 25));
			buttonList.add(temp);
			buttonPanel.add(buttonList.get(i));
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
		new Game_GUI(9, 9);
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

		}
	}

	private void clickOnSafeButton(JButton button, int x, int y, int position) {
		int surroundingBomb = gameControl.checkSourrending(x, y);
		if (surroundingBomb != 0) {
			showSurrounding(button, x, y, surroundingBomb);
			if (x - 1 >= 0 && y - 1 >= 0
					&& gameControl.checkSourrending(x - 1, y - 1) == 0
					&& !gameControl.checkForBomb(x - 1, y - 1)) {
				openSurroundingButton(x - 1, y - 1,
						buttonList.get(position - currentGUISizeY - 1),
						position - currentGUISizeY - 1);

			}
			if (y - 1 >= 0 && gameControl.checkSourrending(x, y - 1) == 0
					&& !gameControl.checkForBomb(x, y - 1)) {
				openSurroundingButton(x, y - 1, buttonList.get(position - 1),
						position - 1);
			}
			if (x + 1 < currentGUISizeX && y - 1 >= 0
					&& gameControl.checkSourrending(x + 1, y - 1) == 0
					&& !gameControl.checkForBomb(x + 1, y - 1)) {
				openSurroundingButton(x + 1, y - 1,
						buttonList.get(position + currentGUISizeY - 1),
						position + currentGUISizeY - 1);
			}

			if (x - 1 >= 0 && gameControl.checkSourrending(x - 1, y) == 0
					&& !gameControl.checkForBomb(x - 1, y)) {
				openSurroundingButton(x - 1, y,
						buttonList.get(position - currentGUISizeY), position
								- currentGUISizeY);
			}
			if (x + 1 < currentGUISizeX
					&& gameControl.checkSourrending(x + 1, y) == 0
					&& !gameControl.checkForBomb(x + 1, y)) {
				openSurroundingButton(x + 1, y,
						buttonList.get(position + currentGUISizeY), position
								+ currentGUISizeY);
			}

			if (x + 1 < currentGUISizeX && y + 1 < currentGUISizeY
					&& gameControl.checkSourrending(x + 1, y + 1) == 0
					&& !gameControl.checkForBomb(x + 1, y + 1)) {
				openSurroundingButton(x + 1, y + 1,
						buttonList.get(position + currentGUISizeY + 1),
						position + currentGUISizeY + 1);
			}
			if (x - 1 >= 0 && y + 1 < currentGUISizeY
					&& gameControl.checkSourrending(x - 1, y + 1) == 0
					&& !gameControl.checkForBomb(x - 1, y + 1)) {
				openSurroundingButton(x - 1, y + 1,
						buttonList.get(position - currentGUISizeY + 1),
						position - currentGUISizeY + 1);
			}
			if (y + 1 < currentGUISizeY
					&& gameControl.checkSourrending(x, y + 1) == 0
					&& !gameControl.checkForBomb(x, y + 1)) {
				openSurroundingButton(x, y + 1, buttonList.get(position + 1),
						position + 1);
			}
		} else {
			openSurroundingButton(x, y, button, position);
		}
		if (endGame) {
			JOptionPane.showMessageDialog(this, "Congz,You cleared all bombs!!");
			endGameDisplay();
		}
	}

	private void showSurrounding(JButton button, int x, int y, int bombNum) {
		String temp = "" + bombNum;
		if (bombNum != 0) {
			button.setText(temp);
		}
		button.setEnabled(false);
		if (!endGame) {
			endGame = gameControl.userClick(x, y);
		}
	}

	private void openSurroundingButton(int x, int y, JButton button,
			int position) {
		int surroundingBomb = gameControl.checkSourrending(x, y);
		if (surroundingBomb != 0 && (button.getIcon() == null)) {
			showSurrounding(button, x, y, surroundingBomb);
		} else if (button.getIcon() != null) {

		} else {
			if (button.isEnabled()) {
				button.setEnabled(false);
				if (!endGame) {
					endGame = gameControl.userClick(x, y);
				}
				if (x - 1 >= 0 && y - 1 >= 0) {
					openSurroundingButton(x - 1, y - 1,
							buttonList.get(position - currentGUISizeY - 1),
							position - currentGUISizeY - 1);
				}
				if (y - 1 >= 0) {
					openSurroundingButton(x, y - 1,
							buttonList.get(position - 1), position - 1);
				}
				if (x + 1 < currentGUISizeX && y - 1 >= 0) {
					openSurroundingButton(x + 1, y - 1,
							buttonList.get(position + currentGUISizeY - 1),
							position + currentGUISizeY - 1);
				}

				if (x - 1 >= 0) {
					openSurroundingButton(x - 1, y,
							buttonList.get(position - currentGUISizeY),
							position - currentGUISizeY);
				}
				if (x + 1 < currentGUISizeX) {
					openSurroundingButton(x + 1, y,
							buttonList.get(position + currentGUISizeY),
							position + currentGUISizeY);
				}

				if (x + 1 < currentGUISizeX && y + 1 < currentGUISizeY) {
					openSurroundingButton(x + 1, y + 1,
							buttonList.get(position + currentGUISizeY + 1),
							position + currentGUISizeY + 1);
				}
				if (x - 1 >= 0 && y + 1 < currentGUISizeY) {
					openSurroundingButton(x - 1, y + 1,
							buttonList.get(position - currentGUISizeY + 1),
							position - currentGUISizeY + 1);
				}
				if (y + 1 < currentGUISizeY) {
					openSurroundingButton(x, y + 1,
							buttonList.get(position + 1), position + 1);
				}
			}
		}
	}

	private void endGameDisplay() {
		ImageIcon mineUnopen = new ImageIcon(this.getClass().getResource(
				"/mine1.jpg"));
		ImageIcon mineOpen = new ImageIcon(this.getClass().getResource(
				"/bomb2.gif"));
		ImageIcon flag = new ImageIcon(this.getClass().getResource("/flag.png"));
		ImageIcon wrongFlag = new ImageIcon(this.getClass().getResource(
				"/wrongFlag.png"));
		ImageIcon flaggedBomb = new ImageIcon(this.getClass().getResource(
				"/flaggedBomb.PNG"));
		int x, y;
		for (JButton button : buttonList) {
			x = (buttonList.indexOf(button)) / currentGUISizeY;
			y = (buttonList.indexOf(button)) % currentGUISizeY;
			if (gameControl.checkForBomb(x, y) && (button.getIcon() == null)) {
				if (button.getIcon() == null) {
					if (button.isEnabled()) {
						button.setIcon(mineUnopen);
					} else {
						button.setIcon(null);
						button.setIcon(mineOpen);
					}
				}
			} else if (button.getIcon() == null) {
				showSurrounding(button, x, y,
						gameControl.checkSourrending(x, y));
			} else {
				System.out.println("test");
				if (gameControl.checkForBomb(x, y)) {
					button.setIcon(null);
					button.setIcon(flaggedBomb);
				} else {
					button.setIcon(null);
					button.setIcon(wrongFlag);
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() instanceof JButton) {
			Object source = e.getSource();
			JButton button = (JButton) source;
			int position = buttonList.indexOf(source);
			if (!button.isEnabled() && SwingUtilities.isLeftMouseButton(e)
					&& SwingUtilities.isRightMouseButton(e)) {
				int bombNum = gameControl.checkSourrending(position
						/ currentGUISizeY, position % currentGUISizeY);
			} else if (button.isEnabled()
					&& SwingUtilities.isLeftMouseButton(e)
					&& !SwingUtilities.isRightMouseButton(e)
					&& button.getIcon() == null) {
				if (gameControl.checkForBomb(position / currentGUISizeY,
						position % currentGUISizeY)) {
					JOptionPane.showMessageDialog(this, "hit a bomb");
					endGameDisplay();
				} else {
					clickOnSafeButton((JButton) source, position
							/ currentGUISizeY, position % currentGUISizeY,
							position);
				}
			} else if (button.isEnabled()
					&& !SwingUtilities.isLeftMouseButton(e)
					&& SwingUtilities.isRightMouseButton(e)) {
				if (button.getIcon() == null) {
					ImageIcon temp = new ImageIcon(this.getClass().getResource(
							"/flag.png"));
					button.setIcon(temp);
				} else {
					button.setIcon(null);
				}
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
