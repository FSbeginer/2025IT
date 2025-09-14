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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					D_병원 frame = new D_병원(1);
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
	int cno;
	public JLabel label;
	public JScrollPane scrollPane;
	public JPanel panel;
	public D_병원(int cno) {
		setTitle("\uBCD1\uC6D0");
		this.cno = cno;
		setBounds(100, 100, 453, 515);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("New label");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		label.setBounds(12, 10, 392, 28);
		getContentPane().add(label);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 57, 413, 408);
		getContentPane().add(scrollPane);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		load();
	}
	private void load() {
		try (var rs = res("select * from hospital where find_in_set("+cno+",cno);")) {
			int w = scrollPane.getWidth()-30;
			int h = scrollPane.getHeight()/4, i = 0;
			while(rs.next()) {
				System.out.println(1);
				D_패널 pp = new D_패널(getIcon("hospital/"+rs.getInt("hno")+".png",w/2-10,h), rs.getString("name"));
				pp.label_2.setPreferredSize(new Dimension(w/2,0));
				pp.setSize(w, h);
				pp.setLocation(10, 10+(h+10)*i);
				int hno = rs.getInt("hno");
				pp.label_1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new E_병원정보(hno),"E_병원정보");
					}
				});
				panel.add(pp);
				i++;
			}
			panel.setPreferredSize(new Dimension(0, 10+(h+10)*i));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = res("select * from category where cno = "+cno)) {
			while(rs.next()) {
				label.setText(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
