import java.awt.EventQueue;

import javax.swing.JFrame;

public class K_강의 extends BF {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					K_강의 frame = new K_강의(1);
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
	int cno;
	
	public K_강의(int cno) {
		setTitle("강의");
		this.cno = cno;
		setBounds(100, 100, 391, 483);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
