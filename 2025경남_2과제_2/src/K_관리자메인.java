import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class K_관리자메인 extends BF {
	public JComboBox comboBox;
	public JLabel label;
	public JScrollPane scrollPane;
	public JPanel panel;

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
		addWindowListener(new ThisWindowListener());
		setTitle("\uAD00\uB9AC\uC790 \uBA54\uC778");
		setBounds(100, 100, 660, 612);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uCD5C\uC2E0\uC21C", "\uC624\uB798\uB41C\uC21C"}));
		comboBox.setBounds(12, 30, 127, 28);
		getContentPane().add(comboBox);
		
		label = new JLabel();
		label.addMouseListener(new LabelMouseListener());
		label.setBounds(547, 15, 66, 60);
		getContentPane().add(label);

		
		new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Random rand = new Random();
				Color c =new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
				Image img = getIcon("icon/insert.png",60,60).getImage();
				BufferedImage bi = new BufferedImage(60, 60, 2);
				var g = bi.createGraphics();
				g.drawImage(img, 0, 0, null);
				for (int i = 0; i < 60; i++) {
					for (int j = 0; j < 60; j++) {
						if(bi.getRGB(i, j)!=-1) {
							bi.setRGB(i, j,c.getRGB());
						}
					}
				}
				label.setIcon(new ImageIcon(bi));
			}
		}).start();
		Random rand = new Random();
		Color c =new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
		Image img = getIcon("icon/insert.png",60,60).getImage();
		BufferedImage bi = new BufferedImage(60, 60, 2);
		var g = bi.createGraphics();
		g.drawImage(img, 0, 0, null);
		for (int i = 0; i < 60; i++) {
			for (int j = 0; j < 60; j++) {
				if(bi.getRGB(i, j)!=-1) {
					bi.setRGB(i, j,c.getRGB());
				}
			}
		}
		label.setIcon(new ImageIcon(bi));
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 79, 620, 484);
		getContentPane().add(scrollPane);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.control);
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		load();
	}
	String order = "desc";
	private void load() {
		panel.removeAll();
		try (var rs = res("select * from apply join job using(jno) join user using(uno) order by apno "+order)) {
			int w = (scrollPane.getWidth()-20-60-20)/2;
			int h = (scrollPane.getHeight()-60)/2;
			int i = 0;
			while(rs.next()) {
				K_패널 pp = new K_패널(rs.getInt("apno"),rs.getString("uname"), rs.getString("apdate"), rs.getString("jname"));
				pp.setSize(w, h);
				pp.setLocation(30+(w+20)*(i%2), 20+(h+20)*(i/2));
				int apno = rs.getInt("apno");
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new L_승인하기(apno), "L_승인하기");
					}
				});
				if(rs.getInt("apok")==1) {
					pp.setBackground(orange);
					pp.label.setForeground(Color.white);
					pp.setBorder(new LineBorder(Color.black));
				}
				panel.add(pp);
				i++;
			}
			panel.setPreferredSize(new Dimension(0, 20+(h+20)*((i+1)/2)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel.revalidate();
		panel.repaint();
	}

	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(comboBox.getSelectedIndex()==0)
				order = "desc";
			else
				order = "";
			load();
		}
	}
	@Override
	public void updateForm() {
		load();
	}
	private class LabelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new M_등록하기(),"M_등록하기");
		}
	}
	private class ThisWindowListener extends WindowAdapter {
		@Override
		public void windowClosed(WindowEvent e) {
			showPage("A_로그인");
		}
	}
}
