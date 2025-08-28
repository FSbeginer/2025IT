import javax.swing.JPanel;
import javax.swing.JToolTip;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;

public class J_∏∑¥Î∆–≥Œ extends JPanel {
	public JLabel label;
	public JPanel panel;
	public JPanel panel_1;
	public JLabel label_1;
	public JLabel label_2;

	/**
	 * Create the panel.
	 */
	public J_∏∑¥Î∆–≥Œ() {
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel("New label");
		label.setVerticalAlignment(SwingConstants.BOTTOM);
		label.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 14));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(label, BorderLayout.NORTH);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(25, 10));
		add(panel, BorderLayout.WEST);
		
		panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(25, 10));
		add(panel_1, BorderLayout.EAST);
		
		label_1 = new JLabel("New label");
		label_1.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 11));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		add(label_1, BorderLayout.SOUTH);
		
		label_2 = new JLabel("") {
			@Override
			public JToolTip createToolTip() {
				JToolTip tip = new JToolTip();
				tip.setBackground(Color.white);
				return tip;
			}
		};
		label_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_2.setOpaque(true);
		add(label_2, BorderLayout.CENTER);

	}
	
	
}
