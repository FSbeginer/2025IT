import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;

public class PanelDisplay extends JPanel {
	public JLabel label;
	public JPanel panel;
	public JLabel label_1;
	public JPanel panel_1;
	public JButton button;
	public JLabel label_2;

	/**
	 * Create the panel.
	 */
	public PanelDisplay(ImageIcon img, String name ,String txt) {
		setBackground(new Color(255, 255, 255));
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel(img);
		label.setPreferredSize(new Dimension(200, 15));
		add(label, BorderLayout.WEST);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		label_1 = new JLabel("<html>"+txt);
		panel.add(label_1, BorderLayout.CENTER);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.add(panel_1, BorderLayout.SOUTH);
		
		button = new JButton("\uC608\uC57D");
		button.setForeground(new Color(255, 255, 255));
		button.setBackground(BF.blue);
		panel_1.add(button);
		
		label_2 = new JLabel("  "+name);
		label_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		panel.add(label_2, BorderLayout.NORTH);
		
	}

}
