import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class 채용패널 extends JPanel {
	public JLabel label;
	public JLabel label_1;

	/**
	 * Create the panel.
	 */
	public 채용패널(String txt) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel();
		add(label, BorderLayout.WEST);
		
		label_1 = new JLabel("<html>"+txt);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_1.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(label_1, BorderLayout.CENTER);

	}

}
