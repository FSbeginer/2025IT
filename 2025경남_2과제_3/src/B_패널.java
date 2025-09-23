import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;

public class B_패널 extends JPanel {
	public JLabel label;

	/**
	 * Create the panel.
	 */
	public B_패널(String jname, int day, int time, int work, int price) {
		setBorder(new LineBorder(new Color(255, 128, 0)));
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel(String.format("<html><b>%s<br><br></b>[주 %d일, %d시간 %s]<br>시급: %d원", jname,day, time, work==0?"정규직":"계약직", price));
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		add(label, BorderLayout.CENTER);

	}

}
