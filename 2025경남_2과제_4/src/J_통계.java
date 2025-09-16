import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class J_≈Î∞Ë extends BF {

	private JPanel contentPane;
	public JComboBox comboBox;
	public JPanel panel;
	public JPanel panel_1;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					J_≈Î∞Ë frame = new J_≈Î∞Ë();
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
	public J_≈Î∞Ë() {
		setTitle("\uD1B5\uACC4");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 748, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "\uB0B4\uAC00 \uC88B\uC544\uD558\uB294 \uC54C\uBC14",
				"\uC778\uAE30 \uB9CE\uC740 \uBE0C\uB79C\uB4DC" }));
		comboBox.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		comboBox.setBounds(12, 10, 158, 23);
		contentPane.add(comboBox);

		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				int ang =0;
				for (int i = 0; i < 5; i++) {
					int deg = (int)(Math.round((double)cnt[i]/Arrays.stream(cnt).sum()*360));
					g.setColor(c[i]);
					g.fillArc(getWidth()/2-150, getHeight()/2-150, 300, 300, ang, deg);
					ang += deg;
				}
			}
		};
		panel.setBounds(173, 55, 380, 343);
		contentPane.add(panel);

		panel_1 = new JPanel();
		panel_1.setBounds(12, 421, 708, 45);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 5, 0, 0));

		label = new JLabel("New label");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		panel_1.add(label);

		label_1 = new JLabel("New label");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		panel_1.add(label_1);

		label_2 = new JLabel("New label");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		panel_1.add(label_2);

		label_3 = new JLabel("New label");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		panel_1.add(label_3);

		label_4 = new JLabel("New label");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		panel_1.add(label_4);
		getdata();
	}
	public ImageIcon geticon(Color c) {
		BufferedImage bi = new BufferedImage(25, 25, 2);
		var g = bi.createGraphics();
		g.setColor(c);
		g.fillRect(0, 0, 25, 25);
		return new ImageIcon(bi);
	}

	Color[] c = new Color[5];
	int[] cnt = new int[5];

	private void getdata() {
		String where = "where uno = " + uno, sql = "cno";
		var jls = new JLabel[] { label, label_1, label_2, label_3, label_4 };
		Random r = new Random();
		if (comboBox.getSelectedIndex() == 1) {
			where = "";
			sql = "bno";
		}
		try (var rs = res(
				"select cname, bname, count(*) cnt from apply join job using(jno) right join brand using(bno) join category using(cno) "
						+ where + " group by " + sql + " order by cnt  desc, " + sql + " limit 5;")) {
			int i = 0;
			while (rs.next()) {
				c[i] = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
				cnt[i] =rs.getInt("cnt");
				jls[i].setText(rs.getString(comboBox.getSelectedIndex()+1));
				jls[i].setIcon(geticon(c[i]));
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			getdata();
			repaint();
		}
	}
}
