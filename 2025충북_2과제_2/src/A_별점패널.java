import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class A_별점패널 extends A_패널 {
	public JPanel panel;
	public JPanel panel_1;
	public JPanel panel_2;
	public JLabel label;
	public JLabel label_1;

	/**
	 * Create the panel.
	 * @param img 
	 * @param txt 
	 */
	public A_별점패널(ImageIcon img, String txt, double star) {
		super(img, txt);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 50));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		label_1 = new JLabel("★");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		label_1.setForeground(new Color(255, 255, 0));
		panel_1.add(label_1, BorderLayout.CENTER);
		
		panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		label = new JLabel(String.format("%.1f", star));
		label.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		panel_2.add(label);
		
	}

}
