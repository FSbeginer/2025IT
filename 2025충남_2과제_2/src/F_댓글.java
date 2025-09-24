import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;

public class F_´ñ±Û extends JPanel {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;

	/**
	 * Create the panel.
	 */
	public F_´ñ±Û(String name, String txt, String date) {
		setSize(388, 156);
		setLayout(null);
		
		label = new JLabel(name);
		label.setBounds(12, 10, 69, 15);
		add(label);
		
		label_1 = new JLabel("<html>"+txt);
		label_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_1.setVerticalAlignment(SwingConstants.TOP);
		label_1.setBounds(93, 10, 204, 113);
		add(label_1);
		
		label_2 = new JLabel("X");
		label_2.setVisible(false);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		label_2.setBounds(319, 49, 57, 45);
		add(label_2);
		
		label_3 = new JLabel(date);
		label_3.setForeground(Color.GRAY);
		label_3.setBounds(93, 131, 192, 15);
		add(label_3);
	}

}
