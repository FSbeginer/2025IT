import java.awt.EventQueue;

import javax.swing.JFrame;

public class H_상품등록 extends BF {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					H_상품등록 frame = new H_상품등록();
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
	int pno;
	public H_상품등록(int pno) {
		this();
		this.pno = pno;
	}
	public H_상품등록() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
