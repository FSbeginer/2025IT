import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.border.LineBorder;
import java.awt.GridLayout;

public class E_패널 extends JPanel {
	public JCheckBox checkBox;
	public JLabel label;
	public JPanel panel;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;

	/**
	 * Create the panel.
	 */
	int ctno,pno,price,cnt;
	public E_패널(ImageIcon img,int ctno, int pno, int cnt, int price,String name) {
		this.ctno = ctno;
		this.pno = pno;
		this.price = price;
		this.cnt = cnt;
		setLayout(new BorderLayout(0, 0));
		
		checkBox = new JCheckBox("");
		checkBox.setBackground(new Color(255, 255, 255));
		add(checkBox, BorderLayout.WEST);
		
		label = new JLabel(img);
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(label, BorderLayout.CENTER);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(120, 10));
		add(panel, BorderLayout.EAST);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		label_1 = new JLabel("상품명: "+name);
		panel.add(label_1);
		
		label_2 = new JLabel("가격: "+String.format("%,d원", price));
		panel.add(label_2);
		
		label_3 = new JLabel("수량: "+cnt+"개");
		panel.add(label_3);
		
		label_4 = new JLabel("합계: "+String.format("%,d원", price*cnt));
		panel.add(label_4);

	}

}
