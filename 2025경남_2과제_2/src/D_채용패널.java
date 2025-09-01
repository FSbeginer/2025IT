import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class D_채용패널 extends JPanel {
	public JLabel label;
	public JLabel label_1;

	/**
	 * Create the panel.
	 */
	public D_채용패널(String txt) {
		setSize(508, 90);
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel("");
		add(label, BorderLayout.WEST);
		
		label_1 = new JLabel("<html>"+txt);
		label_1.setFont(new Font("굴림", Font.BOLD, 16));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		add(label_1, BorderLayout.CENTER);
		
		setBorder(new LineBorder(Color.black));
	}

}
