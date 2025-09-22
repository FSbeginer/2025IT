import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;

public class E_날짜패널 extends JPanel {
	public JLabel label;
	public JLabel label_1;

	/**
	 * Create the panel.
	 */
	public E_날짜패널(String week, int day) {
		setLayout(new GridLayout(0, 2, 0, 0));
		
		label = new JLabel(week);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		add(label);
		
		label_1 = new JLabel(day+"일");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		add(label_1);

	}

}
