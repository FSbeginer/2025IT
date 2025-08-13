import javax.swing.JPanel;
import javax.swing.JToolTip;

import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Dimension;
import java.awt.Font;

public class LineItem extends JPanel {
	public JLabel top;
	public JLabel name;
	public JPanel panel;
	public JLabel value;

	/**
	 * Create the panel.
	 */
	public LineItem() {
		setBackground(new Color(255, 255, 255));
		setLayout(new BorderLayout(0, 0));
		
		top = new JLabel("");
		top.setVerticalAlignment(SwingConstants.BOTTOM);
		top.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		top.setPreferredSize(new Dimension(0, 20));
		top.setHorizontalAlignment(SwingConstants.CENTER);
		add(top, BorderLayout.NORTH);
		
		name = new JLabel("");
		name.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		name.setPreferredSize(new Dimension(0, 20));
		name.setHorizontalAlignment(SwingConstants.CENTER);
		add(name, BorderLayout.SOUTH);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		value = new JLabel("");
		value.setOpaque(true);
		value.setBorder(new LineBorder(new Color(0, 0, 0)));
		value.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(value, BorderLayout.CENTER);

	}
	
	@Override
	public JToolTip createToolTip() {
		JToolTip to = new JToolTip();
		to.setBackground(Color.white);
		return to;
	}

}
