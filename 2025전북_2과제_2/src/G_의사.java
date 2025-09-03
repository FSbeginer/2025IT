import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class G_의사 extends BF {
	public JComboBox comboBox;
	public JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					G_의사 frame = new G_의사(1);
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
	int hno ;
	public G_의사(int hno) {
		this.hno = hno;
		setTitle("\uC758\uC0AC");
		setBounds(100, 100, 468, 540);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "\uC804\uCCB4" }));
		comboBox.setBounds(12, 10, 160, 32);
		getContentPane().add(comboBox);

		panel = new JPanel();
		panel.setBounds(12, 52, 428, 439);
		getContentPane().add(panel);
		panel.setLayout(null);

		addcatego();
		comboBox.setSelectedIndex(hno);
//		load();
	}

	private void addcatego() {
		try (var rs = res("select * from hospital")) {
			while(rs.next())
				comboBox.addItem(rs.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	String where = "";

	private int cx;
	List<JPanel> pps =new ArrayList<JPanel>();
	private void load() {
		panel.removeAll();
		try {
			var rs = res("select dno, hno, d.name dname, h.name hname from doctor d join hospital h using(hno) where true " + where);
			int i = 0;
			int w = 394;
			int h = 126;
			while(rs.next()) {
				G_패널 pp = new G_패널(getIcon("doctor/"+rs.getInt("dno")+".png",200,120),rs.getString(3),rs.getString(4));
				int dno  = rs.getInt(1);
				pp.label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new 의사정보(dno), "의사정보");
					}
					@Override
					public void mousePressed(MouseEvent e) {
						cx = e.getY();
					}
				});
				pp.addMouseMotionListener(new MouseAdapter() {
					@Override
					public void mouseDragged(MouseEvent e) {
							int dx = e.getY() - cx;
							if (pps.size() == 0 || pps.get(0).getY() + dx > 0
									|| pps.get(pps.size() - 1).getY() + dx < panel.getHeight() - h - 10)
								return;
							for (var b_패널 : pps) {
								b_패널.setLocation(0, b_패널.getY() + dx);
							}
					}
				});
				pp.setLocation(0, (h+10)*i);
				pps.add(pp);
				panel.add(pp);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel.revalidate();
		panel.repaint();
	}
	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(comboBox.getSelectedIndex()==0)
				where = "";
			else
				where = " and hno ="+comboBox.getSelectedIndex();
			load();
		}
	}
}
