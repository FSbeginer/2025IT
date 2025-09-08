import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class E_스케쥴 extends JPanel {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;

	/**
	 * Create the panel.
	 */
	public E_스케쥴(int srmno, String time, int left) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new GridLayout(3, 0, 0, 0));
		
		label = new JLabel("  "+srmno+"관 (총 81석)");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		add(label);
		
		label_1 = new JLabel("  "+time+" "+(left)+"석 남음");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		add(label_1);
		
		label_2 = new JLabel("");
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		add(label_2);

	}

}
