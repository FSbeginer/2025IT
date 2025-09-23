import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

public class I_마이페이지 extends BF {
	public JButton button;
	public JButton button_1;
	public JButton button_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					I_마이페이지 frame = new I_마이페이지();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public I_마이페이지() {
		setTitle("\uB9C8\uC774\uD398\uC774\uC9C0");
		setBounds(100, 100, 592, 476);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		button = new JButton("\uB0B4\uAC00 \uC88B\uC544\uD55C \uC54C\uBC14");
		button.setBounds(12, 10, 161, 29);
		getContentPane().add(button);
		
		button_1 = new JButton("\uB300\uAE30\uC911\uC778 \uC54C\uBC14");
		button_1.setBounds(196, 10, 161, 29);
		getContentPane().add(button_1);
		
		button_2 = new JButton("\uD569\uACA9\uD55C \uC54C\uBC14");
		button_2.setBounds(381, 10, 161, 29);
		getContentPane().add(button_2);

	}

}
