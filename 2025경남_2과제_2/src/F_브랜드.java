import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class F_브랜드 extends BF {
	public JComboBox comboBox;
	public JLabel label;
	public JPanel panel;
	public JLabel label_1;

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
		setBounds(100, 100, 658, 411);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "\uC804\uCCB4" }));
		comboBox.setBounds(12, 10, 157, 34);
		getContentPane().add(comboBox);

		label = new JLabel("<");
		label.addMouseListener(new LabelMouseListener());
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(12, 66, 32, 205);
		getContentPane().add(label);

		panel = new JPanel();
		panel.setBounds(84, 66, 470, 223);
		getContentPane().add(panel);
		panel.setLayout(null);

		label_1 = new JLabel(">");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(598, 66, 32, 205);
		getContentPane().add(label_1);

		addcom();
		load();
	}

	String where = "";
	private void load() {
		panel.removeAll();
		int w = (panel.getWidth()-40)/3;
		int h = (panel.getHeight()-20)/2;
		try (var rs = res("select *, count(*) over() max from brand where true "+where+" limit "+idx+",6")) {
			int i = 0;
			while(rs.next()) {
				JLabel jl = new JLabel(getIcon("brand/"+rs.getInt("bno")+".png",w,h));
				jl.setSize(w, h);
				jl.setBorder(new LineBorder(Color.black));
				int bno = rs.getInt(1);
				jl.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new G_브랜드정보(bno),"G_브랜드정보");
					}
				});
				jl.setLocation((w+20)*(i%3), (h+20)*(i/3));
				panel.add(jl);
				max = rs.getInt("max");
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		panel.revalidate();
		panel.repaint();
		
		label.setEnabled(idx!=0);
		label_1.setEnabled(idx!=(max-max%6));
	}

	int idx = 0;
	int max = 0;

	private void addcom() {
		try (var rs = res("select * from category")) {
			while (rs.next())
				comboBox.addItem(rs.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class LabelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(label.isEnabled()) {
				idx -= 6;
				load();
			}
		}
	}
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(label_1.isEnabled()) {
				idx += 6;
				load();
			}
		}
	}
	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(comboBox.getSelectedIndex()==0)
				where = "";
			else {
				where = "and cno = "+comboBox.getSelectedIndex();
			}
			idx = 0;
			load();
		}
	}
}
