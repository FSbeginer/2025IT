import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class A_별점패널 extends A_패널 {
	public JPanel panel;
	public JLabel label;
	public JLabel label_1;

	/**
	 * Create the panel.
	 * 
	 * @param img
	 * @param txt
	 */
	public A_별점패널(ImageIcon img, String txt, double star) {
		super(img, txt);

		panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		label = new JLabel("★");
		label.setForeground(new Color(255, 255, 0));
		label.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(label, BorderLayout.CENTER);

		label_1 = new JLabel(String.format("%.1f", star));
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		panel.add(label_1, BorderLayout.EAST);
	}

}
