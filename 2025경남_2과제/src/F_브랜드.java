import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class F_브랜드 extends BF {
	public JComboBox comboBox;
	public JPanel panel;
	public JLabel label;
	public JLabel label_1;
	private String where = "";
	int idx1=0,idx2=6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					F_브랜드 frame = new F_브랜드();
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
	public F_브랜드() {
		setTitle("\uBE0C\uB79C\uB4DC");
		setBounds(100, 100, 615, 410);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uC804\uCCB4"}));
		comboBox.setBounds(12, 10, 170, 34);
		getContentPane().add(comboBox);
		
		panel = new JPanel();
		panel.setBounds(52, 78, 490, 240);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		label = new JLabel("<");
		label.addMouseListener(new LabelMouseListener());
		label.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(12, 78, 41, 240);
		getContentPane().add(label);
		
		label_1 = new JLabel(">");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(546, 78, 41, 240);
		getContentPane().add(label_1);
		
		load();
		addCate();
	}

	private void addCate() {
		try {
			var rs = res("select * from category");
			while(rs.next()) {
				comboBox.addItem(rs.getString("cname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void load() {
		panel.removeAll();
		
		int max = 0;
		try (var rs = res("select *, count(*) over() cnt from brand where true "+where+" limit "+idx1+","+idx2)) {
			int w = (panel.getWidth()-45)/3;
			int h = (panel.getHeight()-15)/2;
			int i =0;
			while(rs.next()) {
				JLabel jl = new JLabel(getIcon("brand/"+rs.getInt("bno")+".png",w,h));
				jl.setSize(w, h);
				int bno = rs.getInt("bno");
				jl.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new G_브랜드정보(bno), "G_브랜드정보");
					}
				});
				jl.setBorder(new LineBorder(Color.black));
				jl.setLocation((w+15)*(i%3), (h+15)*(i/3));
				panel.add(jl);
				
				max = rs.getInt("cnt");
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		label.setEnabled(idx1!=0);
		label_1.setEnabled(idx1<max-6);
		panel.revalidate();
		panel.repaint();
	}

	private class LabelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(label.isEnabled()) {
				idx1 -= 6;
				load();
			}
		}
	}
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(label_1.isEnabled()) {
				idx1 += 6;
				load();
			}
		}
	}
	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			idx1 = 0;
			if(comboBox.getSelectedIndex()==0) {
				where = "";
			}
			else {
				where = "and cno = "+comboBox.getSelectedIndex();
			}
			load();
		}
	}
}
