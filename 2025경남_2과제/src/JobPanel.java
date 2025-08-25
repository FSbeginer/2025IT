import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;

public class JobPanel extends JPanel {
	public JLabel label;
	public JLabel label_1;

	/**
	 * Create the panel.
	 * @param name 
	 * @param pay 
	 */
	public JobPanel(String name, String work, int pay) {
		setBorder(new LineBorder(Color.ORANGE));
		setLayout(new GridLayout(0, 1, 0, 0));
		
		label = new JLabel("<html>"+name);
		label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		add(label);
		
		label_1 = new JLabel("<html>"+work+"<br>½Ã±Þ:"+pay+"¿ø");
		label_1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 11));
		add(label_1);

	}

}
