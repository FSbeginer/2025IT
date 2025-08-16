import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QR extends JDialog {
	public JLabel label;
	public JLabel label_1;
	public JButton button;

	public QR(String code) {
		setTitle("QR\uCF54\uB4DC");
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 291, 366);
		getContentPane().setLayout(null);
		
		label = new JLabel(new ImageIcon(BF.getQR().getScaledInstance(250, 250, 1)));
		label.setBounds(12, 10, 251, 220);
		getContentPane().add(label);
		
		label_1 = new JLabel(code);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(22, 240, 226, 20);
		getContentPane().add(label_1);
		
		button = new RoundButton("예매확인");
		button.setBounds(12, 270, 251, 34);
		getContentPane().add(button);
		setModal(true);
	}
}
