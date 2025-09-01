import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class K_ÆÐ³Î extends JPanel {
	public JLabel label;

	/**
	 * Create the panel.
	 */
	public K_ÆÐ³Î(int apno, String name,String date, String jname) {
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel(String.format("<html><b>(%d) %s<br><br>%s<br><br></b>%s", apno, name, date, jname));
		label.setBorder(new LineBorder(new Color(255, 128, 0)));
		label.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 13));
		add(label, BorderLayout.CENTER);

	}

}
