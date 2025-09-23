import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.Random;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class K_관리자메인 extends BF {
	public JComboBox comboBox;
	public JScrollPane scrollPane;
	public JPanel panel;
	public JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					K_관리자메인 frame = new K_관리자메인();
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
	public K_관리자메인() {
		setTitle("\uAD00\uB9AC\uC790 \uBA54\uC778");
		setBounds(100, 100, 493, 528);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uCD5C\uC2E0\uC21C", "\uC624\uB798\uB41C\uC21C"}));
		comboBox.setBounds(12, 29, 123, 31);
		getContentPane().add(comboBox);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 70, 453, 409);
		getContentPane().add(scrollPane);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.control);
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		label = new JLabel("");
		label.addMouseListener(new LabelMouseListener());
		label.setBounds(408, 10, 57, 50);
		getContentPane().add(label);

		Random rand = new Random();
		label.setIcon(getIcon("icon/insert.png", 50, 50, new Color(rand.nextInt(255), rand.nextInt(255),rand.nextInt(255))));
		new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				label.setIcon(getIcon("icon/insert.png", 50, 50, new Color(rand.nextInt(255), rand.nextInt(255),rand.nextInt(255))));
			}
		}).start();
		comboBox.setSelectedIndex(0);
	}
	@Override
	public void updateForm() {
		load();
	}
	
	private void load() {
		panel.removeAll();
		try (var rs = res("select * from apply join user using(uno) order by apno "+(comboBox.getSelectedIndex()==0?"desc":""))) {
			int w = (scrollPane.getWidth()-80)/2;
			int h = (scrollPane.getHeight()-60)/2;
			int i = 0;
			while(rs.next()) {
				K_패널 pp = new K_패널(rs.getInt("apno"), rs.getString("uname"), rs.getString("apdate"), rs.getString("udetail"));
				int apno = rs.getInt("apno");
				if(!rs.getBoolean("apok")) {
					pp.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							showPage(new L_승인하기(apno), "L_승인하기");
						}
					});
					pp.setBackground(Color.white);
					pp.setBorder(new LineBorder(orange));
				}
				else {
					pp.setBackground(orange);
					pp.setBorder(new LineBorder(Color.black));
					pp.label.setForeground(Color.white);
				}
				
				pp.setSize(w, h);
				pp.setLocation(20+(w+20)*(i%2), 20+(h+20)*(i/2));
				panel.add(pp);
				i++;
			}
			i++;
			panel.setPreferredSize(new Dimension(0,20+(h+20)*(i/2)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel.revalidate();
		panel.repaint();
	}

	public static ImageIcon getIcon(String path, int w, int h, Color c) {
		Image img = getIcon(path,w,h).getImage();
		BufferedImage bi = new BufferedImage(w, h, 2);
		var g = bi.createGraphics();
		g.drawImage(img, 0, 0, null);
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				if(bi.getRGB(i, j)!=-1) {
					bi.setRGB(i, j, c.getRGB());
				}
			}
		}
		return new ImageIcon(bi);
	}

	private class LabelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new M_등록하기(),"M_등록하기");
		}
	}
	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			load();
		}
	}
}
