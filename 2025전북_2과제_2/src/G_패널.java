import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;

public class G_패널 extends JPanel {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;

	/**
	 * Create the panel.
	 */
	public G_패널(ImageIcon img, String name, String hos) {
		setSize(394, 126);
		setLayout(null);
		
		label = new JLabel(img);
		label.setBounds(0, 0, 205, 126);
		add(label);
		
		label_1 = new JLabel(name+" 의사");
		label_1.setFont(new Font("굴림", Font.PLAIN, 13));
		label_1.setBounds(217, 10, 152, 35);
		add(label_1);
		
		label_2 = new JLabel(hos);
		label_2.setBounds(217, 56, 152, 35);
		add(label_2);
	}

}
