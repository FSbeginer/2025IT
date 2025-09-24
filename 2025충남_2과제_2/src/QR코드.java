import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class QR코드 extends JDialog {

	private JPanel contentPane;
	public JLabel label;
	public JLabel label_1;
	public JButton button;

	public QR코드(String code) {
		setTitle("QR\uCF54\uB4DC");
		setIconImage(BF.getLogoIcon(100, 100).getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 247, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label = new JLabel(BF.getQrCode(184, 184));
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(24, 10, 184, 184);
		contentPane.add(label);
		
		label_1 = new JLabel(code);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(12, 204, 207, 24);
		contentPane.add(label_1);
		
		button = new RoundButton("예매확인");
		button.setBounds(22, 238, 186, 24);
		contentPane.add(button);
		setModal(true);
	
	}
}
