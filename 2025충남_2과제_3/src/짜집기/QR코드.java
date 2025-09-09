package 짜집기;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class QR코드 extends BF {
	public JLabel label;
	public JButton button;
	public JLabel label_1;

	public QR코드(String qr) {
		setTitle("QR\uCF54\uB4DC");
		setBounds(100, 100, 235, 295);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel(getQRcode(195, 195));
		label.setBounds(37, 10, 146, 146);
		getContentPane().add(label);
		
		button = new RoundButton("\uC608\uB9E4\uD655\uC778");
		button.setBackground(blue);
		button.setForeground(new Color(255, 255, 255));
		button.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		button.setBounds(12, 213, 195, 28);
		getContentPane().add(button);
		
		label_1 = new JLabel(qr);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(23, 188, 171, 15);
		getContentPane().add(label_1);
		
	}

}
