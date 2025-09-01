import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;

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
	public JPanel panel;
	public JLabel label_1;

	public E_±§∞Ì¡§∫∏(int ano) {
		this.ano = ano;
		setTitle("\uAD11\uACE0 \uC815\uBCF4");
		setBounds(100, 100, 476, 405);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setIconImage(BF.getIcon("icon/icon.png").getImage());
		setModal(true);
		getContentPane().setLayout(null);

		label = new JLabel("New label");
		label.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 17));
		label.setBounds(12, 10, 436, 78);
		getContentPane().add(label);

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(12, 98, 436, 159);
		getContentPane().add(panel);
		panel.setLayout(null);

		label_1 = new JLabel("New label");
		label_1.setForeground(Color.GRAY);
		label_1.setBounds(12, 284, 436, 64);
		getContentPane().add(label_1);

		load();
	}

	private void load() {
		try (var rs = BF.res("select * from advertise where ano = " + ano)) {
			rs.next();
			label.setText("<html>" + rs.getString("aname"));
			label_1.setText("<html>" + rs.getString("adetail"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 2; i++) {
			jls[i] = new JLabel(
					BF.getIcon("advertise/" + ano + "-" + (i + 1) + ".jpg", panel.getWidth(), panel.getHeight()));
			jls[i].setSize(panel.getSize());
			jls[i].setLocation(panel.getWidth() * i, 0);
			panel.add(jls[i]);
		}

		new Thread(new Runnable() {
			boolean stop = true;

			@Override
			public void run() {
				while (true) {
					try {
						if (stop) {
							Thread.sleep(5000);
							stop = false;
						} else {
							Thread.sleep(1);
						}
						
						for (JLabel jl : jls) {
							jl.setLocation(jl.getX()-1, 0);
							if(jl.getX()==-jl.getWidth()) {
								stop = true;
								jl.setLocation(jl.getWidth(), 0);
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	JLabel[] jls = new JLabel[2];

}
