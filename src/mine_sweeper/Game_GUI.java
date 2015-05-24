package mine_sweeper;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game_GUI extends JFrame implements ActionListener {
	JPanel panel = new JPanel();

	JLabel answer = new JLabel("");
	JButton pressme = new JButton("Press Me");

	Game_GUI() {
		super("Mine Sweeper");
		setBounds(700, 400, 300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contain = this.getContentPane();
		contain.add(panel);

		pressme.addActionListener(this); // register button listener
		contain.add(answer);
		contain.add(pressme);
		//pressme.requestFocus();

		setVisible(true);
	}

	// here is the basic event handler
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == pressme) {
			answer.setText("Button pressed!");
			JOptionPane.showMessageDialog(null, "I hear you!",
					"Message Dialog", JOptionPane.PLAIN_MESSAGE);
			setVisible(true); // show something
		}
	}

	public static void main(String args[]) {
		new Game_GUI();
	}
}
