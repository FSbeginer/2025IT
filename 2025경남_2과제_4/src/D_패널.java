import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class D_ÆÐ³Î extends JPanel {
	public JLabel label;
	public JLabel label_1;

	/**
	 * Create the panel.
	 */
	public D_ÆÐ³Î(String txt) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setSize(505, 96);
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel("");
		add(label, BorderLayout.WEST);
		
		label_1 = new JLabel("<html>"+txt);
		label_1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 14));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		add(label_1, BorderLayout.CENTER);
	}

}
