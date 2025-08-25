import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

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
		setTitle("\uAD00\uB9AC\uC790 \uBA54\uC778");
		setBounds(100, 100, 485, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uCD5C\uC2E0\uC21C", "\uC624\uB798\uB41C\uC21C"}));
		comboBox.setBounds(12, 25, 138, 31);
		getContentPane().add(comboBox);
		
		label = new JLabel("");
		label.setBounds(386, 10, 57, 56);
		getContentPane().add(label);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 76, 431, 411);
		getContentPane().add(scrollPane);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.control);
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		load();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Random rand = new Random();
				while(true) {
					Color c = Color.getHSBColor(rand.nextFloat(), 0.6f, 0.9f);
					label.setIcon(getIcon(c));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			private Icon getIcon(Color c) {
				BufferedImage bi = new BufferedImage(50, 50, 2);
				var g = bi.getGraphics();
				Image img = BF.getIcon("icon/insert.png", 50, 50).getImage();
				
				g.drawImage(img, 0, 0, null);
				for (int i = 0; i < 50; i++) {
					for (int j = 0; j < 50; j++) {
						if(bi.getRGB(i, j)!=-1) {
							bi.setRGB(i, j, c.getRGB());
						}
					}
				}
				return new ImageIcon(bi);
			}
		}).start();
	}
	
	@Override
	public void updateForm() {
		load();
	}
	
	private void load() {
		panel.removeAll();
		try (var rs = res("select * from apply join job using(jno) join user using(uno) order by apno "+(comboBox.getSelectedIndex()==0?"desc":"asc"))) {
			int w = (scrollPane.getWidth()-45-25)/2;
			int h = (scrollPane.getHeight()-45)/2;
			int i = 0;
			while(rs.next()) {
				JLabel jl = new JLabel();
				jl.setSize(w, h);
				jl.setLocation(15+(15+w)*(i%2), 15+(15+h)*(i/2));
				jl.setOpaque(true);
				if(rs.getBoolean("apok")) {
					jl.setBackground(orange);
					jl.setBorder(new LineBorder(Color.black));
					jl.setForeground(Color.white);
				}
				else {
					jl.setBackground(Color.white);
					jl.setBorder(new LineBorder(Color.orange));
					jl.setForeground(Color.black);
				}
				int apno = rs.getInt("apno");
				jl.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new L_승인하기(apno),"L_승인하기");
					}
				});
				jl.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
				jl.setText(String.format("<html><b>(%d) %s<br><br>%s<br><br></b>%s", rs.getInt("apno"), rs.getString("uname"), rs.getString("apdate"), rs.getString("jname")));
				panel.add(jl);
				i++;
			}
			panel.setPreferredSize(new Dimension(0, 15+(15+h)*((i+1)/2)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel.revalidate();
		panel.repaint();
	}
	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			load();
		}
	}
}
