import java.awt.EventQueue;

import javax.swing.JFrame;

public class I_∞Ì∞¥ºæ≈Õ extends BF {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					I_∞Ì∞¥ºæ≈Õ frame = new I_∞Ì∞¥ºæ≈Õ();
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
	public I_∞Ì∞¥ºæ≈Õ() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
