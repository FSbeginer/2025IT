import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.border.LineBorder;

public class PanelSelectProgram extends JPanel {
	public JLabel label;
	public JPanel panel;
	public JLabel label_1;
	public JLabel label_2;

	/**
	 * Create the panel.
	 * @param name 
	 * @param text 
	 */
	public PanelSelectProgram(ImageIcon img,String name, String text) {
		setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel(img);
		label.setBackground(Color.WHITE);
		label.setPreferredSize(new Dimension(150, 15));
		add(label, BorderLayout.EAST);
		
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		label_1 = new JLabel(name);
		label_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		label_1.setBackground(Color.WHITE);
		panel.add(label_1, BorderLayout.NORTH);
		
		label_2 = new JLabel("<html><br><br>"+text);
		label_2.setBackground(Color.WHITE);
		panel.add(label_2, BorderLayout.CENTER);
		
		
	}
	
}
