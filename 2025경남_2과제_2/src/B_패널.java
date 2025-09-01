import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;

public class B_패널 extends JPanel {
	public JLabel label;
	public JLabel label_1;

	/**
	 * Create the panel.
	 */
	public B_패널(String txt, int day, int time, int work, int money) {
		setBackground(new Color(255, 255, 255));
		setBorder(new LineBorder(new Color(255, 128, 0)));
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel("<html>"+txt);
		label.setFont(new Font("굴림", Font.BOLD, 13));
		add(label, BorderLayout.CENTER);
		
		label_1 = new JLabel(String.format("<html>[주 %d일, %d시간, %s]<br>시급: %d원", day, time, work==0?"정규직":"계약직", money));
		label_1.setVerticalAlignment(SwingConstants.TOP);
		label_1.setFont(new Font("굴림", Font.PLAIN, 13));
		label_1.setPreferredSize(new Dimension(57, 60));
		add(label_1, BorderLayout.SOUTH);

	}

}
