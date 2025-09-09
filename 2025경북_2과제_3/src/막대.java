import javax.swing.JPanel;
import javax.swing.JToolTip;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Font;

public class ¸·´ë extends JPanel {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JPanel panel;

	/**
	 * Create the panel.
	 */
	public ¸·´ë() {
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel("");
		label.setPreferredSize(new Dimension(30, 0));
		add(label, BorderLayout.WEST);
		
		label_1 = new JLabel("");
		label_1.setPreferredSize(new Dimension(30, 0));
		add(label_1, BorderLayout.EAST);
		
		label_2 = new JLabel("New label");
		label_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		add(label_2, BorderLayout.SOUTH);
		
		label_3 = new JLabel("New label");
		label_3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		add(label_3, BorderLayout.NORTH);
		
		panel = new JPanel() {
			@Override
			public JToolTip createToolTip() {
				JToolTip jt = new JToolTip();
				jt.setBackground(Color.white);
				return jt;
			}
		};
		add(panel, BorderLayout.CENTER);

	}
	

}
