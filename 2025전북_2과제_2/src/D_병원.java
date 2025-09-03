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
		setBounds(100, 100, 450, 537);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		label = new JLabel("New label");
		label.setFont(new Font("굴림", Font.PLAIN, 14));
		label.setBounds(12, 10, 390, 36);
		getContentPane().add(label);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 58, 410, 430);
		getContentPane().add(scrollPane);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		load();
	}

	private void load() {
		try {
			var rs =res("select * from hospital where find_in_set("+cno+", cno) order by hno");
			int w = scrollPane.getWidth()-20-20;
			int h = scrollPane.getHeight()/4;
			int i = 0;
			while(rs.next()) {
				D_패널 pp = new D_패널(getIcon("hospital/"+rs.getInt("hno")+".png",w/2,h), rs.getString("name"));
				pp.label_2.setPreferredSize(new Dimension(w/2,0));
				pp.setSize(w, h);
				pp.setLocation(10, 10+(h+10)*i);
				int  hno =rs.getInt(1);
				pp.label_1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new E_병원정보(hno),"E_병원정보");
					}
				});
				panel.add(pp);
				i++;
			}
			panel.setPreferredSize(new Dimension(0,10+(h+10)*i));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			var rs =res("select * from category where cno = "+cno);
			rs.next();
			label.setText(rs.getString("name"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
