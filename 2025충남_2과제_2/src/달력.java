import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;

public class ´Þ·Â extends JPanel {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JPanel panel;
	public JPanel panel_1;

	/**
	 * Create the panel.
	 */
	public ´Þ·Â() {
		setSize(450, 450);
		setLayout(null);
		
		label = new JLabel("New label");
		label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 450, 41);
		add(label);
		
		label_1 = new JLabel("<");
		label_1.setEnabled(false);
		label_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(62, 3, 57, 41);
		add(label_1);
		
		label_2 = new JLabel(">");
		label_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(320, 0, 57, 41);
		add(label_2);
		
		panel = new JPanel();
		panel.setBounds(20, 51, 418, 41);
		add(panel);
		panel.setLayout(new GridLayout(0, 7, 0, 0));
		
		panel_1 = new JPanel();
		panel_1.setBounds(20, 102, 418, 338);
		add(panel_1);
		panel_1.setLayout(new GridLayout(6, 7, 0, 0));
	}

}
