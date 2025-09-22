import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class E_스케줄 extends JPanel {
	public JLabel label;
	public JLabel label_1;

	/**
	 * Create the panel.
	 */
	public E_스케줄(String txt, String txt2) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setSize(557, 119);
		setLayout(null);
		
		label = new JLabel(txt);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		label.setBounds(12, 10, 397, 31);
		add(label);
		
		label_1 = new JLabel(txt2);
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		label_1.setBounds(12, 51, 397, 31);
		add(label_1);
	}

}
