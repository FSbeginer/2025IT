import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class Kiosk extends BF {
	public JLabel label;
	public JScrollPane scrollPane;
	public JComboBox comboBox;
	public JPanel panel;
	private String where ="";


	public Kiosk() {
		setTitle("키오스크");
		setBounds(100, 100, 450, 628);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		label = new JLabel("키오스크");
		label.setIcon(getIcon("로고2.jpg",434,142));
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setBounds(0, 0, 434, 142);
		getContentPane().add(label);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 152, 434, 404);
		getContentPane().add(scrollPane);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"전체", "세트O", "세트X"}));
		comboBox.setBounds(351, 557, 83, 32);
		getContentPane().add(comboBox);

		addFood();
	}


	private void addFood() {
		panel.removeAll();
		try (var rs = res("SELECT * FROM moviedb.food where true "+where )) {
			int w = (scrollPane.getWidth()-25)/3, h = 180, i = 0;
			while(rs.next()) {
				푸드패널 pp = new 푸드패널(getIcon("foods/"+rs.getInt(1)+".jpg",w-40,h-60), rs.getString(2), rs.getString(4), rs.getInt(3));
				int pno = rs.getInt(1);
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new 메뉴정보(pno), "메뉴정보");
					}
				});
				pp.setSize(w, h);
				pp.setLocation(w*(i%3), h*(i/3));
				panel.add(pp);
				i++;
			}
			panel.setPreferredSize(new Dimension(0, h*((i+2)/3)));
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
				where = " and f_set = 1";
			}
			else{
				where = " and f_set = 0";
			}
			addFood();
		}
	}
}
