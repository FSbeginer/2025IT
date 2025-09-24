import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;

public class H_¿¹¾à³»¿ª extends BP {
	public JPanel panel;
	public JLabel label;
	public JLabel label_1;
	public JPanel panel_1;
	public JPanel panel_2;
	public JPanel panel_3;
	public JLabel label_2;
	public JLabel label_3;
	public JButton button;
	public JPanel panel_4;
	public JLabel label_4;
	public JLabel label_5;
	public JLabel label_6;
	public JLabel label_7;
	public JLabel label_8;
	public JLabel label_9;
	public JLabel label_10;
	public JLabel label_11;
	public JLabel label_12;
	public JLabel label_13;

	/**
	 * Create the panel.
	 */
	public H_¿¹¾à³»¿ª() {
		
		panel = new JPanel();
		panel.setBounds(12, 35, 450, 450);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		panel_1.setBounds(484, 21, 502, 494);
		add(panel_1);
		panel_1.setLayout(new CardLayout(0, 0));
		
		panel_2 = new JPanel();
		panel_1.add(panel_2, "name_13089786803100");
		panel_2.setLayout(null);
		
		label = new JLabel(BF.getQrCode(172, 172));
		label.setBounds(175, 36, 172, 170);
		panel_2.add(label);
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		label_1 = new JLabel("New label");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(195, 211, 132, 25);
		panel_2.add(label_1);
		
		panel_4 = new JPanel();
		panel_4.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel_4.setBounds(110, 239, 311, 232);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		
		label_4 = new JLabel("| \uC804\uC2DC\uAD00 \uBA85");
		label_4.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
		label_4.setBounds(12, 10, 113, 25);
		panel_4.add(label_4);
		
		label_5 = new JLabel("| \uD504\uB85C\uADF8\uB7A8 \uBA85");
		label_5.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
		label_5.setBounds(12, 45, 113, 25);
		panel_4.add(label_5);
		
		label_6 = new JLabel("| \uAE08\uC561");
		label_6.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
		label_6.setBounds(12, 80, 113, 25);
		panel_4.add(label_6);
		
		label_7 = new JLabel("| \uD3EC\uC778\uD2B8");
		label_7.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
		label_7.setBounds(12, 115, 113, 25);
		panel_4.add(label_7);
		
		label_8 = new JLabel("| \uC778\uC6D0");
		label_8.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
		label_8.setBounds(12, 150, 113, 25);
		panel_4.add(label_8);
		
		label_9 = new JLabel("New label");
		label_9.setForeground(Color.GRAY);
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		label_9.setBounds(137, 10, 162, 25);
		panel_4.add(label_9);
		
		label_10 = new JLabel("New label");
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		label_10.setForeground(Color.GRAY);
		label_10.setBounds(137, 45, 162, 25);
		panel_4.add(label_10);
		
		label_11 = new JLabel("New label");
		label_11.setHorizontalAlignment(SwingConstants.RIGHT);
		label_11.setForeground(Color.GRAY);
		label_11.setBounds(137, 80, 162, 25);
		panel_4.add(label_11);
		
		label_12 = new JLabel("New label");
		label_12.setHorizontalAlignment(SwingConstants.RIGHT);
		label_12.setForeground(Color.GRAY);
		label_12.setBounds(137, 115, 162, 25);
		panel_4.add(label_12);
		
		label_13 = new JLabel("New label");
		label_13.setVerticalAlignment(SwingConstants.TOP);
		label_13.setHorizontalAlignment(SwingConstants.RIGHT);
		label_13.setForeground(Color.GRAY);
		label_13.setBounds(137, 150, 162, 72);
		panel_4.add(label_13);
		
		panel_3 = new JPanel();
		panel_1.add(panel_3, "name_13091245790300");
		panel_3.setLayout(null);
		
		label_2 = new JLabel("\uC608\uC57D \uB0B4\uC5ED\uC774 \uC874\uC7AC\uD558\uC9C0 \uC54A\uC2B5\uB2C8\uB2E4.");
		label_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(73, 126, 367, 47);
		panel_3.add(label_2);
		
		label_3 = new JLabel("\uC608\uC57D\uD558\uC2DC\uACA0\uC2B5\uB2C8\uAE4C?");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		label_3.setBounds(73, 177, 367, 47);
		panel_3.add(label_3);
		
		button = new JButton("\uC608\uC57D\uD558\uB7EC \uAC00\uAE30");
		button.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		button.setBounds(96, 237, 344, 36);
		panel_3.add(button);
		
		load();
	}

	private void load() {
		
	}

}
