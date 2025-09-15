import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class E_패널2 extends JPanel {
	public JLabel label;
	public JLabel label_1;

	/**
	 * Create the panel.
	 */
	public E_패널2(int srmno, String time, int left) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setSize(549, 101);
		setLayout(null);
		
		label = new JLabel(srmno+"관 (총 81석)");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		label.setBounds(12, 10, 331, 33);
		add(label);
		
		label_1 = new JLabel(time+" "+(81-left)+"석 남음");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		label_1.setBounds(12, 53, 331, 33);
		add(label_1);
	}

}
