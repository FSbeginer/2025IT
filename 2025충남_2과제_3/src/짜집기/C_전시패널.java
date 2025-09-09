package 짜집기;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.SwingConstants;

public class C_전시패널 extends JPanel {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JButton button;

	/**
	 * Create the panel.
	 */
	public C_전시패널(ImageIcon img, String title, String explan) {
		setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
		setSize( 737, 151);
		setLayout(null);
		
		label = new JLabel(img);
		label.setBounds(30, 10, 203, 129);
		add(label);
		
		label_1 = new JLabel(title);
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_1.setBounds(245, 0, 414, 40);
		add(label_1);
		
		label_2 = new JLabel("<html>"+explan);
		label_2.setVerticalAlignment(SwingConstants.TOP);
		label_2.setBounds(245, 45, 362, 94);
		add(label_2);
		
		button = new JButton("\uC608\uB9E4");
		button.setBackground(BF.blue);
		button.setForeground(new Color(255, 255, 255));
		button.setVisible(false);
		button.setBounds(628, 116, 97, 23);
		add(button);
	}

}
