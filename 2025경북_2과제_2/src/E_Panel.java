import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.GridLayout;

public class E_Panel extends JPanel {
	public JCheckBox checkBox;
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
	public E_Panel(ImageIcon img, String name, int price, int quantity, int sum) {
		setLayout(new BorderLayout(0, 0));
		
		checkBox = new JCheckBox("");
		checkBox.setHorizontalAlignment(SwingConstants.CENTER);
		checkBox.setBackground(Color.WHITE);
		add(checkBox, BorderLayout.WEST);
		
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setPreferredSize(new Dimension(100, 10));
		add(panel, BorderLayout.EAST);
		panel.setLayout(new GridLayout(4, 1, 0, 0));
		
		label_1 = new JLabel("상품명 : "+name);
		panel.add(label_1);
		
		label_2 = new JLabel("가격 : "+String.format("%,d원", price));
		panel.add(label_2);
		
		label_3 = new JLabel("수량 : "+quantity+"개");
		panel.add(label_3);
		
		label_4 = new JLabel("합계 : "+String.format("%,d원", sum));
		panel.add(label_4);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		label = new JLabel(img);
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(label);

	}

}
