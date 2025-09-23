import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

public class D_ÆÐ³Î extends JPanel {
	public JLabel label;
	public JLabel label_1;

	/**
	 * Create the panel.
	 */
	public D_ÆÐ³Î(String	xtx) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel("");
		add(label, BorderLayout.WEST);
		
		label_1 = new JLabel("<html>"+xtx);
		label_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		add(label_1, BorderLayout.CENTER);

	}

}
