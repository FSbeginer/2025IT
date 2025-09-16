import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;

public class A_패널 extends JPanel {
	public JLabel label;
	public JPanel panel;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;

	/**
	 * Create the panel.
	 */
	public A_패널(ImageIcon img, String txt, int price, int selling, double star) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel(img);
		label.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new LineBorder(new Color(0, 0, 0))));
		add(label, BorderLayout.CENTER);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 80));
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		label_1 = new JLabel("상품명 : "+txt);
		panel.add(label_1);
		
		label_2 = new JLabel("가격 : "+String.format("%,d원", price));
		panel.add(label_2);
		
		label_3 = new JLabel("판매량 : "+selling);
		panel.add(label_3);
		
		label_4 = new JLabel("평점 : "+String.format("%.1f", star));
		panel.add(label_4);

	}

}
