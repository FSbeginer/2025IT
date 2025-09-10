import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;

public class TimeLabel extends JPanel {
	public JLabel label;
	public JLabel label_1;

	/**
	 * Create the panel.
	 */
	public TimeLabel() {
		setLayout(new GridLayout(0, 2, 0, 0));
		
		label = new JLabel("New label");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		add(label);
		
		label_1 = new JLabel("New label");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		add(label_1);

	}

}
