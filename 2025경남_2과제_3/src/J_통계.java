import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		setBounds(100, 100, 637, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.addMouseListener(new ComboBoxMouseListener());
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uB0B4\uAC00 \uC88B\uC544\uD558\uB294 \uC54C\uBC14", "\uC778\uAE30 \uB9CE\uC740 \uBE0C\uB79C\uB4DC"}));
		comboBox.setBounds(12, 10, 147, 32);
		contentPane.add(comboBox);
		
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				int ang = 0;
				var jls = new JLabel[] {label, label_1, label_2, label_3,label_4};
				Random rand = new Random();
				for (int i = 0; i < jls.length; i++) {
					Color c = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
					jls[i].setIcon(getIcon(c));
					g.setColor(c);
					int deg = (int)Math.round((double)cnt[i]/Arrays.stream(cnt).sum()*360);
					g.fillArc(getWidth()/2-150, getHeight()/2-150,300, 300, ang, deg);
					ang += deg;
				}
			}
		};
		panel.setBounds(12, 63, 597, 314);
		contentPane.add(panel);
		
		panel_1 = new JPanel();
		panel_1.setBounds(12, 407, 597, 41);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 5, 0, 0));
		
		label = new JLabel("");
		label.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label);
		
		label_1 = new JLabel("");
		label_1.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_1);
		
		label_2 = new JLabel("");
		label_2.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_2);
		
		label_3 = new JLabel("");
		label_3.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_3);
		
		label_4 = new JLabel("");
		label_4.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_4);
		
		load();
	}

	public static ImageIcon getIcon(Color c) {
		BufferedImage bi = new BufferedImage(30, 30, 2);
		var g = bi.createGraphics();
		g.setColor(c);
		g.fillRect(0, 0, 30, 30);
		return new ImageIcon(bi);
	}
	
	int[] cnt = new int[5];
	String get = "cname", group = "cno" , where = "where uno = "+uno, order = "cno";
	private void load() {
		var jls = new JLabel[] {label, label_1, label_2, label_3,label_4};
		try {
			var rs =res("select "+get+",count(*) cnt from apply join job  using(jno) join brand b using(bno) join category c using(cno) "+where+" group by "+group+" order by cnt desc, "+order+" limit 5;");
			int i = 0;
			while(rs.next()) {
				cnt[i] =rs.getInt("cnt");
				jls[i].setText(rs.getString(1));
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(comboBox.getSelectedIndex()==0) {
				get = "cname";
				group = "cno";
				where = "where uno = "+uno;
				order = "cno";
			}
			else {
				where = "";
				get = "bname";
				group = "bno";
				order = "bno";
			}
			load();
			repaint();
		}
	}
	private class ComboBoxMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			repaint();
		}
	}
}
