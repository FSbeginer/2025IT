import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.GridLayout;

public class PanelMain extends JPanel {
	public JPanel panel;
	public JPanel panel_1;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;

	/**
	 * Create the panel.
	 */
	public PanelMain(ImageIcon img, String name, int price, int selling, double star) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setBackground(new Color(255, 255, 255));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		label = new JLabel(img);
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(label, BorderLayout.CENTER);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setPreferredSize(new Dimension(10, 60));
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		label_1 = new JLabel("상품명: "+name);
		panel_1.add(label_1);
		
		label_2 = new JLabel(String.format("가격: %,d원", price));
		panel_1.add(label_2);
		
		label_3 = new JLabel("판매량: "+selling);
		panel_1.add(label_3);
		
		label_4 = new JLabel(String.format("평점: %.1f", star));
		panel_1.add(label_4);

	}

}
