import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import java.awt.Font;

public class PanelComment extends JPanel {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;

	/**
	 * Create the panel.
	 */
	public PanelComment(String name, String txt, String date) {
		setBackground(Color.WHITE);
		setSize(370, 145);
		setLayout(null);
		
		label = new JLabel(name+"´Ô");
		label.setBorder(new LineBorder(Color.LIGHT_GRAY));
		label.setBounds(12, 10, 59, 23);
		add(label);
		
		label_1 = new JLabel("<html>"+txt);
		label_1.setVerticalAlignment(SwingConstants.TOP);
		label_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		label_1.setBounds(83, 10, 211, 110);
		add(label_1);
		
		label_2 = new JLabel("X");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(306, 27, 52, 52);
		add(label_2);
		
		label_3 = new JLabel(date);
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setBounds(83, 130, 174, 15);
		add(label_3);
	}

}
