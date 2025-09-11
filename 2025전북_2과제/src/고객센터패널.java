import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Dimension;

public class 고객센터패널 extends JPanel {
	public JLabel label;
	public JLabel label_1;

	/**
	 * Create the panel.
	 * @param txt 
	 */
	public 고객센터패널(String txt) {
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel("");
		label.setPreferredSize(new Dimension(50, 0));
		add(label, BorderLayout.WEST);
		
		label_1 = new JLabel("<html>"+txt);
		add(label_1, BorderLayout.CENTER);

	}

}
