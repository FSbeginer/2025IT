import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.Random;
import java.awt.event.ActionListener;
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
		setBounds(100, 100, 580, 573);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uCD5C\uC2E0\uC21C", "\uC624\uB798\uB41C\uC21C"}));
		comboBox.setBounds(12, 10, 133, 31);
		getContentPane().add(comboBox);
		
		label = new JLabel("");
		label.addMouseListener(new LabelMouseListener());
		label.setBounds(479, 0, 57, 55);
		getContentPane().add(label);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 66, 540, 458);
		getContentPane().add(scrollPane);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.control);
		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		load();
		new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				chagecolor();
			}

		}).start();
		chagecolor();
	}
	private void chagecolor() {
		BufferedImage bi = new BufferedImage(55, 55, 2);
		Image ori = getIcon("icon/insert.png",55,55).getImage();
		var g = bi.createGraphics();
		g.drawImage(ori, 0, 0,null);
		Random r = new Random();
		Color c= Color.getHSBColor(r.nextFloat(), 0.6f, 0.9f);
		for (int i = 0; i < 55; i++) {
			for (int j = 0; j < 55; j++) {
				if(bi.getRGB(i, j)!=-1) {
					bi.setRGB(i, j, c.getRGB());
				}
			}
		}
		label.setIcon(new ImageIcon(bi));
	}
	@Override
	public void updateForm() {
		load();
	}
	
	private void load() {
		panel.removeAll();
		try (var rs = res("select * from apply join user using(uno) join job using(jno) order by apno "+(comboBox.getSelectedIndex()==0?"desc":"asc"))) {
			int w = (scrollPane.getWidth()-20-60-20)/2;
			int h = (scrollPane.getHeight()-40-20)/2, i= 0;
			while(rs.next()) {
				JLabel jl =  new JLabel();
				jl.setFont(new Font("맑은 고딕", 0, 12));
				jl.setOpaque(true);
				int apno = rs.getInt("apno");
				if(rs.getInt("apok")==0) {
					jl.setBackground(Color.white);
					jl.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							showPage(new L_승인하기(apno),"L_승인하기");
						}
					});
					jl.setBorder(new LineBorder(orange));
				}
				else {
					jl.setBackground(orange);
					jl.setForeground(Color.white);
					jl.setBorder(new LineBorder(Color.black));
				}
				jl.setText(String.format("<html><b>(%d) %s<br><br>%s<br><br></b>%s", apno, rs.getString("uname"),rs.getString("apdate"),rs.getString("jname")));
				jl.setSize(w, h);
				jl.setLocation(30+(w+20)*(i%2), 20+(h+20)*(i/2));
				panel.add(jl);
				i++;
			}
			i++;
			panel.setPreferredSize(new Dimension(0, 20+(h+20)*(i/2)));
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
	private class LabelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new M_등록하기(),"M_등록하기");
		}
	}
}
