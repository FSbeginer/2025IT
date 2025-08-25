import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class J_≈Î∞Ë extends BF {
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
	
	int[] cnt = new int[5];
	String[] name = new String[5];
	int sum;
	public J_≈Î∞Ë() {
		addWindowListener(new ThisWindowListener());
		setTitle("\uD1B5\uACC4");
		setBounds(100, 100, 717, 539);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				int prev = 0;
				JLabel[] jls = {label, label_1, label_2, label_3, label_4};
				int x =getWidth()/2-175;
				for (int i = 0; i < cnt.length; i++) {
					int ang = (int) Math.round((double)cnt[i]/sum *360);
					Random rand = new Random();
					Color c = new Color(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256));
					jls[i].setIcon(getIcon(c));
					jls[i].setText(name[i]);
					g.setColor(c);
					g.fillArc(x, 0, 350, 350, prev, ang);
					prev += ang;
				}
			}

			private Icon getIcon(Color c) {
				BufferedImage bi = new BufferedImage(30, 30, 2);
				var g = bi.createGraphics();
				g.setColor(c);
				g.fillRect(0, 0, 30, 30);
				return new ImageIcon(bi);
			}
		};
		panel.setBounds(12, 53, 677, 377);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBounds(12, 440, 677, 50);
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(0, 5, 0, 0));
		
		label = new JLabel("New label");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 13));
		panel_1.add(label);
		
		label_1 = new JLabel("New label");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 13));
		panel_1.add(label_1);
		
		label_2 = new JLabel("New label");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 13));
		panel_1.add(label_2);
		
		label_3 = new JLabel("New label");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 13));
		panel_1.add(label_3);
		
		label_4 = new JLabel("New label");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 13));
		panel_1.add(label_4);
		
		comboBox = new JComboBox();
		comboBox.setBounds(12, 10, 171, 34);
		getContentPane().add(comboBox);
		comboBox.addMouseListener(new ComboBoxMouseListener());
		comboBox.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uB0B4\uAC00 \uC88B\uC544\uD558\uB294 \uC54C\uBC14", "\uC778\uAE30 \uB9CE\uC740 \uBE0C\uB79C\uB4DC"}));
		
		getData();
	}

	private void getData() {
		try {
			String sql1 = comboBox.getSelectedIndex()==0? "where uno = "+uno : "";
			String sql2 = comboBox.getSelectedIndex()==0? "cno ": "bno";
			
			var rs = res("select *, count(*) cnt from apply join job using(jno) join brand using(bno) join category using(cno) "+sql1+" group by "+sql2+" order by cnt desc limit 5;");
			int i = 0;
			while(rs.next()) {
				cnt[i] = rs.getInt("cnt");
				name[i] = rs.getString("cname");
				i++;
			}
			sum = Arrays.stream(cnt).sum();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			getData();
			repaint();
		}

	}
	private class ThisWindowListener extends WindowAdapter {
		@Override
		public void windowClosed(WindowEvent e) {
			showPage("B_∏ﬁ¿Œ");
		}
	}
	private class ComboBoxMouseListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			panel.repaint();
		}
	}
}
