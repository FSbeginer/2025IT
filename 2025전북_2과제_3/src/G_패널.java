import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;

public class G_패널 extends JPanel {
	public JLabel label;
	public JPanel panel;
	public JLabel label_1;
	public JLabel label_2;

	/**
	 * Create the panel.
	 */
	public G_패널(ImageIcon img, String name, String txt) {
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel(img);
		add(label, BorderLayout.WEST);
		
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		label_1 = new JLabel(name+" 의사");
		panel.add(label_1);
		
		label_2 = new JLabel(txt);
		panel.add(label_2);

	}

}
