import javax.swing.JPanel;
import javax.swing.JToolTip;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class ¸·´ë extends JPanel {
	public JPanel panel;
	public JPanel panel_1;
	public JPanel panel_2;
	public JLabel label;
	public JLabel label_1;

	/**
	 * Create the panel.
	 */
	public ¸·´ë() {
		setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel() {
			@Override
			public JToolTip createToolTip() {
				JToolTip jt = new JToolTip();
				jt.setBackground(Color.white);
				return jt;
			}
		};
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panel, BorderLayout.CENTER);
		
		panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(30, 10));
		add(panel_1, BorderLayout.WEST);
		
		panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(30, 10));
		add(panel_2, BorderLayout.EAST);
		
		label = new JLabel("New label");
		label.setVerticalAlignment(SwingConstants.BOTTOM);
		label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(label, BorderLayout.NORTH);
		
		label_1 = new JLabel("New label");
		label_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		add(label_1, BorderLayout.SOUTH);
		
	}

}
