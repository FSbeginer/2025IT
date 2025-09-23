import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class G_의사 extends BF {

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
	int hno;
	public JComboBox comboBox;
	public JPanel panel;
	public G_의사(int hno) {
		setTitle("\uC758\uC0AC");
		this.hno = hno;
		setBounds(100, 100, 450, 524);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uC804\uCCB4"}));
		comboBox.setBounds(12, 10, 158, 30);
		getContentPane().add(comboBox);
		
		panel = new JPanel();
		panel.addMouseWheelListener(new PanelMouseWheelListener());
		panel.setBounds(12, 60, 410, 401);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		addcate();
		comboBox.setSelectedIndex(hno);
	}
	private void addcate() {
		try (var rs = res("select * from hospital")) {
			while(rs.next()) {
				comboBox.addItem(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	List<JPanel> pps = new ArrayList<JPanel>();
	private int cx;
	private void load() {
		panel.removeAll();
		pps.clear();
		String where = comboBox.getSelectedIndex()>0?" where hno = "+comboBox.getSelectedIndex() : ""; 
		try (var rs = res("select dno, hno, d.name dn, h.name hn  from doctor d join hospital h using(hno) "+where)) {
			int w = panel.getWidth();
			int h = panel.getHeight()/4;
			int i = 0;
			while(rs.next()) {
				G_패널 pp = new G_패널(getIcon("doctor/"+rs.getInt(1)+".png",w*2/3,h), rs.getString("dn"), rs.getString("hn"));
				pp.setSize(w, h);
				pp.setLocation(0, (h+10)*i);
				pp.label.setPreferredSize(new Dimension(w*2/3, 0));
				int dno = rs.getInt(1);
				pp.label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new 의사정보(dno),"의사정보");
					}
				});
				pp.addMouseListener(new MouseAdapter() {

					@Override
					public void mousePressed(MouseEvent e) {
						cx =e.getY();
					}
				});
				pp.addMouseMotionListener(new MouseAdapter() {
					@Override
					public void mouseDragged(MouseEvent e) {
						int dx = e.getY() - cx;
						if(pps.size()==0||pps.get(0).getY()+dx>0||pps.get(pps.size()-1).getY()+dx<panel.getHeight()-pps.get(0).getHeight()) return;
						for (var pp : pps) {
							pp.setLocation(0, pp.getY()+dx);
						}
					}
				});
				panel.add(pp);
				pps.add(pp);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel.revalidate();
		panel.repaint();
	}

	private class PanelMouseWheelListener implements MouseWheelListener {
		public void mouseWheelMoved(MouseWheelEvent e) {
			int dx = e.getWheelRotation()>0?-10:10;
			if(pps.size()==0||pps.get(0).getY()+dx>0||pps.get(pps.size()-1).getY()+dx<panel.getHeight()-pps.get(0).getHeight()) return;
			for (var pp : pps) {
				pp.setLocation(0, pp.getY()+dx);
			}
		}
	}
	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			load();
		}
	}
}
