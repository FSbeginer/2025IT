import java.awt.EventQueue;

import javax.swing.JFrame;

public class J_분석 extends BF {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					J_분석 frame = new J_분석();
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
	public J_분석() {
		setTitle("\uBD84\uC11D");
		setBounds(100, 100, 649, 522);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

	}

}
