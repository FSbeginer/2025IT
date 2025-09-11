import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

public class G_의사 extends BF {

	private JPanel contentPane;

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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 563);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uC804\uCCB4"}));
		comboBox.setBounds(12, 10, 149, 34);
		contentPane.add(comboBox);
		
		panel = new JPanel();
		panel.addMouseWheelListener(new PanelMouseWheelListener());
		panel.setBounds(12, 54, 410, 460);
		contentPane.add(panel);
		
		addItem();
		comboBox.setSelectedIndex(hno);
	}
	
	List<JPanel> pps = new ArrayList<JPanel>();
	int cy = 0;
	private void addDoctor(String sql) {
		panel.removeAll();
		try (var rs = res("select dno,hno,d.name as dname, h.name as hname from doctor d join hospital h using(hno) where true "+sql)) {
			int i = 0;
			int w = panel.getWidth()-20;
			int h = panel.getHeight()/4;
			while(rs.next()) {
				DoctorPanel pp = new DoctorPanel(getIcon("doctor/"+rs.getInt(1)+".png", w*2/3,h), rs.getString(3), rs.getString(4));
				pp.setSize(w, h);
				pp.setLocation(10, (h+10)*i);
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						cy = e.getY();
					}
				});
				int dno = rs.getInt(1);
				pp.label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new G_의사정보(dno),"G_의사정보");
					}
				});
				pp.addMouseMotionListener(new MouseAdapter() {
					@Override
					public void mouseDragged(MouseEvent e) {
						int dy = e.getY() - cy;
						if(pps.size()==0||pps.get(0).getY()+dy>0||pps.get(pps.size()-1).getY()<panel.getHeight()-pps.get(0).getHeight()) return;
						for (JPanel pp : pps) {
							pp.setLocation(pp.getX(), pp.getY()+dy);
						}
					}
				});
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
	private void addItem() {
		try (var rs = res("select * from hospital")) {
			while(rs.next()) {
				comboBox.addItem(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(comboBox.getSelectedIndex()==0)
				addDoctor("");
			else {
				addDoctor("and hno = "+comboBox.getSelectedIndex());
			}
		}
	}
	private class PanelMouseWheelListener implements MouseWheelListener {
		public void mouseWheelMoved(MouseWheelEvent e) {
			int dy = e.getWheelRotation()>0 ? -10 : 10;
			if(pps.size()==0||pps.get(0).getY()+dy>0||pps.get(pps.size()-1).getY()<panel.getHeight()-pps.get(0).getHeight()) return;
			for (JPanel pp : pps) {
				pp.setLocation(pp.getX(), pp.getY()+dy);
			}
		}
	}
}
