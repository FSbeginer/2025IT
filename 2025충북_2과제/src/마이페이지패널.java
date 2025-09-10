import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.SwingConstants;

public class 마이페이지패널 extends JPanel {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;

	/**
	 * Create the panel.
	 */
	public 마이페이지패널() {
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel("");
		add(label, BorderLayout.CENTER);
		
		label_1 = new JLabel("New label");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		add(label_1, BorderLayout.SOUTH);
		
		label_2 = new JLabel("");
		label_2.setPreferredSize(new Dimension(40, 15));
		add(label_2, BorderLayout.WEST);
		
		label_3 = new JLabel("");
		label_3.setPreferredSize(new Dimension(40, 15));
		add(label_3, BorderLayout.EAST);

	}

}
