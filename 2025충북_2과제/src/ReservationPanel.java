import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.GridLayout;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

public class ReservationPanel extends JPanel {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;

	/**
	 * Create the panel.
	 */
	public ReservationPanel() {
		setLayout(new GridLayout(0, 1, 0, 0));
		setBorder(new LineBorder(Color.black));
		
		label = new JLabel("New label");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		add(label);
		
		label_1 = new JLabel("New label");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		add(label_1);
		
		label_2 = new JLabel();
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		add(label_2);

	}

}
