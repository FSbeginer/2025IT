import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class J_통계 extends BF {
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
					J_통계 frame = new J_통계();
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
	public J_통계() {
		setTitle("\uD1B5\uACC4");
		setBounds(100, 100, 701, 506);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "\uB0B4\uAC00 \uC88B\uC544\uD558\uB294 \uC54C\uBC14",
				"\uC778\uAE30 \uB9CE\uC740 \uBE0C\uB79C\uB4DC" }));
		comboBox.setBounds(12, 10, 165, 31);
		getContentPane().add(comboBox);

		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				int bx = getWidth()/2-150;
				int by = getHeight()/2-150;
				JLabel[] jls = {label, label_1,label_2,label_3,label_4};
				int ang = 0;
				for (int i = 0; i < 5; i++) {
					jls[i].setIcon(getIcon(c[i]));
					g.setColor(c[i]);
					int deg = (int) Math.round((double)list.get(i)/list.stream().mapToInt(x->x).sum() * 360);
					g.fillArc(bx, by, 300, 300, ang, deg);
					ang += deg;
				}
			}
		};
		panel.setBounds(12, 51, 661, 340);
		getContentPane().add(panel);

		panel_1 = new JPanel();
		panel_1.setBounds(12, 401, 661, 56);
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(0, 5, 20, 0));

		label = new JLabel("New label");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label);

		label_1 = new JLabel("New label");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_1);

		label_2 = new JLabel("New label");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_2);

		label_3 = new JLabel("New label");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_3);

		label_4 = new JLabel("New label");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_4);

		getData();
	}

	List<Integer> list = new ArrayList<Integer>();
	Color[] c = new Color[5];
	String group = "cno";
	String where = "where uno = " + uno;

	private void getData() {
		JLabel[] jls = {label, label_1,label_2,label_3,label_4};
		list.clear();
		try (var rs = res(
				"select *,count(*) cnt from apply join job using(jno) join brand using(bno) join category using(cno) "
						+ where + " group by " + group + " order by cnt desc," + group + " limit 5")) {
			int i = 0;
			while (rs.next()) {
				list.add(rs.getInt("cnt"));
				jls[i].setText(rs.getString(group.equals("cno")?"cname":"bname"));
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Random rand = new Random();
		for (int i = 0; i < jls.length; i++) {
			c[i] = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
		}
	}

	public ImageIcon getIcon(Color c) {
		BufferedImage bi = new BufferedImage(30, 30, 2);
		var g = bi.createGraphics();
		g.setColor(c);
		g.fillRect(0, 0, 30, 30);
		return new ImageIcon(bi);
	}

	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (comboBox.getSelectedIndex() == 0) {
				group = "cno";
				where = "where uno = " + uno;
			} else {
				group = "bno";
				where = "";
			}
			getData();
			repaint();
		}
	}

}
