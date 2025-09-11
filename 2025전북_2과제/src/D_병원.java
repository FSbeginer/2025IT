import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class D_병원 extends BF {

	int cno;
	public JLabel label;
	public JScrollPane scrollPane;
	public JPanel panel;
	public D_병원(int cno) {
		setTitle("\uBCD1\uC6D0");
		this.cno = cno;
		setBounds(100, 100, 450, 572);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("\uB0B4\uACFC");
		label.setFont(new Font("굴림", Font.BOLD, 14));
		label.setBounds(12, 10, 367, 27);
		getContentPane().add(label);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 47, 410, 476);
		getContentPane().add(scrollPane);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		load();
	}
	private void load() {
		try (var rs = res("select * from hospital where find_in_set("+cno+", cno)")) {
			int w = scrollPane.getWidth()-35;
			int h = scrollPane.getHeight()/4+10;
			int i = 0;
			while(rs.next()) {
				HospitalPanel pp = new HospitalPanel(getIcon("hospital/"+rs.getInt(1)+".png", w-120,h-20), rs.getString("name"));
				pp.setSize(w, h);
				pp.setLocation(10, 10+(h+10)*i);
				int hno = rs.getInt(1);
				pp.label_1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new E_병원정보(hno), "E_병원정보");
					}
				});
				panel.add(pp);
				i++;
			}
			panel.setPreferredSize(new Dimension(0, 10+(h+10)*i));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = res("select name from category where cno= "+cno)) {
			rs.next();
			label.setText(rs.getString(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
