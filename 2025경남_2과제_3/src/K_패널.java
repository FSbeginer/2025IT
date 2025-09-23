import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;

public class K_ÆÐ³Î extends JPanel {
	public JLabel label;

	/**
	 * Create the panel.
	 */
	public K_ÆÐ³Î(int no, String name, String date, String txt) {
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel(String.format("<html><b>(%d) %s<br><br>%s<br><br></b>%s", no, name, date,txt));
		label.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		add(label, BorderLayout.CENTER);

	}

}
