import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class E_±§∞Ì¡§∫∏ extends JDialog {

	int ano;
	public JLabel label;
	public JPanel panel;
	public JLabel label_1;

	public E_±§∞Ì¡§∫∏(int ano) {
		setTitle("\uAD11\uACE0 \uC815\uBCF4");
		this.ano = ano;
		setBounds(100, 100, 450, 436);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		setIconImage(new ImageIcon("./datafiles/icon/icon.png").getImage());
		setModal(true);
		getContentPane().setLayout(null);

		label = new JLabel("New label");
		label.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 15));
		label.setBounds(12, 10, 410, 70);
		getContentPane().add(label);

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(12, 90, 410, 182);
		getContentPane().add(panel);
		panel.setLayout(null);

		label_1 = new JLabel("New label");
		label_1.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 15));
		label_1.setBounds(12, 282, 410, 96);
		getContentPane().add(label_1);

		try {
			var rs = BF.res("select * from advertise where ano = " + ano);
			rs.next();
			label.setText("<html>" + rs.getString("aname"));
			label_1.setText("<html>" + rs.getString("adetail"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JLabel[] jls = new JLabel[2];
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
							if(jl.getX()==0) stop = true;
							if(jl.getX()==-jl.getWidth()) jl.setLocation(jl.getWidth()*1, 0);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

}
