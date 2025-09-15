import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Font;

public class I_패널 extends JPanel {
	public JLabel label;
	public JPanel panel;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;

	/**
	 * Create the panel.
	 */
	public I_패널(ImageIcon img, String name, String txt,int price) {
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel(img);
		add(label, BorderLayout.CENTER);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 60));
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(3, 0, 0, 0));
		
		label_1 = new JLabel(name);
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_1);
		
		label_2 = new JLabel(txt);
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2);
		
		label_3 = new JLabel(String.format("%,d", price));
		label_3.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_3);

	}

}
