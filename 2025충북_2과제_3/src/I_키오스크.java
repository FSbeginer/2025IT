import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class I_키오스크 extends BF {
	public JLabel label;
	public JScrollPane scrollPane;
	public JComboBox comboBox;
	public JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					I_키오스크 frame = new I_키오스크();
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
	public I_키오스크() {
		setTitle("키오스크");
		setBounds(100, 100, 450, 604);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("키오스크");
		label.setIcon(getIcon("로고2.jpg",434,128));
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 434, 128);
		getContentPane().add(label);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 138, 434, 403);
		getContentPane().add(scrollPane);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"전체", "세트O", "세트X"}));
		comboBox.setBounds(342, 541, 92, 23);
		getContentPane().add(comboBox);

		load();
	}

	String where = "";
	private void load() {
		panel.removeAll();
		int w = scrollPane.getWidth()/3;
		int h = scrollPane.getHeight()/3+20;
		try (var rs = res("select * from food where true "+where)) {
			int i = 0;
			while(rs.next()) {
				A_패널 pp =new A_패널(getIcon("foods/"+rs.getInt("f_no")+".jpg",100,100),String.format("<html>%s<br>%s<br>%,d", rs.getString("f_name"),rs.getString("f_cc"),rs.getInt("f_price")));
				pp.label_1.setPreferredSize(new Dimension(0, 60));
				pp.label_1.setFont(new Font("맑은 고딕",1,12));
				pp.label_1.setHorizontalAlignment(0);
				pp.setSize(w, h);
				pp.setLocation(w*(i%3), h*(i/3));
				int fno = rs.getInt("f_no");
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new J_메뉴정보(fno),"J_메뉴정보");
					}
				});
				panel.add(pp);
				i++;
			}
			i += 2;
			panel.setPreferredSize(new Dimension(0, h*(i/3)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel.revalidate();
		panel.repaint();
	}

	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(comboBox.getSelectedIndex()==0) {
				where = "";
			}
			else if(comboBox.getSelectedIndex()==1) {
				where = "and f_set=1";
			}
			else
				where = "and f_set=0";
			load();
		}
	}
}
