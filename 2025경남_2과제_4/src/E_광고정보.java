import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.sql.SQLException;

public class E_±§∞Ì¡§∫∏ extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					E_±§∞Ì¡§∫∏ frame = new E_±§∞Ì¡§∫∏(1);
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
	int ano;
	public JLabel label;
	public JLabel label_1;
	public JPanel panel;

	public E_±§∞Ì¡§∫∏(int ano) {
		setTitle("\uAD11\uACE0 \uC815\uBCF4");
		this.ano = ano;
		setBounds(100, 100, 450, 384);
		setDefaultCloseOperation(2);
		setIconImage(BF.getIcon("icon/icon.png").getImage());
		setModal(true);
		getContentPane().setLayout(null);

		label = new JLabel("New label");
		label.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 15));
		label.setBounds(12, 10, 410, 60);
		getContentPane().add(label);

		label_1 = new JLabel("New label");
		label_1.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 13));
		label_1.setBounds(12, 257, 410, 78);
		getContentPane().add(label_1);

		panel = new JPanel();
		panel.setBounds(12, 80, 410, 153);
		getContentPane().add(panel);
		panel.setLayout(null);

		try (var rs = BF.res("select * from advertise where ano = " + ano)) {
			rs.next();
			label.setText("<html>" + rs.getString("aname"));
			label_1.setText("<html>" + rs.getString("adetail"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JLabel[] jls = new JLabel[2];
		for (int i = 0; i < jls.length; i++) {
			jls[i] = new JLabel(
					BF.getIcon("advertise/" + ano + "-" + (i + 1) + ".jpg", panel.getWidth(), panel.getHeight()));
			jls[i].setSize(panel.getSize());
			jls[i].setLocation(panel.getWidth() * i, 0);
			panel.add(jls[i]);
		}
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						for (int i = 0; i < 5000; i++) {
							for (JLabel jl : jls) {
								jl.setLocation(jl.getX()-1, 0);
								if(jl.getX()==-jl.getWidth()) {
									jl.setLocation(jl.getWidth(), 0);
								}
							}
							Thread.sleep(1);
						}
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		setLocationRelativeTo(null);
	}

}
