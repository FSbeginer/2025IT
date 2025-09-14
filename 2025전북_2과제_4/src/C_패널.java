import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class C_패널 extends JPanel {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;

	/**
	 * Create the panel.
	 */
	public C_패널(ImageIcon img, String name,String txt, String txt2) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setSize( 189, 95);
		setLayout(null);
		
		label = new JLabel(img);
		label.setBounds(0, 0, 84, 95);
		add(label);
		
		label_1 = new JLabel("<html>"+name);
		label_1.setBounds(96, 0, 93, 45);
		add(label_1);
		
		label_2 = new JLabel(txt+" 의사");
		label_2.setBounds(95, 50, 94, 22);
		add(label_2);
		
		label_3 = new JLabel(txt2);
		label_3.setBounds(95, 73, 94, 22);
		add(label_3);
	}

}
