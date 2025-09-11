import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Font;

public class DoctorPanel extends JPanel {
	public JLabel label;
	public JPanel panel;
	public JLabel label_1;
	public JLabel label_2;

	/**
	 * Create the panel.
	 * @param dname 
	 * @param hname 
	 */
	public DoctorPanel(ImageIcon img, String dname, String hname) {
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel(img);
		add(label, BorderLayout.CENTER);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(200, 10));
		add(panel, BorderLayout.EAST);
		panel.setLayout(new GridLayout(2, 0, 0, 0));
		
		label_1 = new JLabel("  "+dname+" ÀÇ»ç");
		label_1.setFont(new Font("±¼¸²", Font.BOLD, 14));
		label_1.setPreferredSize(new Dimension(120, 15));
		panel.add(label_1);
		
		label_2 = new JLabel("  "+hname);
		panel.add(label_2);

	}

}
