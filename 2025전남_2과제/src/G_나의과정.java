import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class G_나의과정 extends BF {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					G_나의과정 frame = new G_나의과정(true);
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
	public G_나의과정(boolean flag) {
		addWindowListener(new ThisWindowListener());
		setTitle("나의 과정폼");
		setBounds(100, 100, 365, 478);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		label = new JLabel(getIcon("icon/logo.png", 30, 30));
		label.setText("");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setBounds(12, 10, 325, 40);
		getContentPane().add(label);

		label_1 = new JLabel("   나의 강의실");
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(12, 60, 325, 61);
		getContentPane().add(label_1);

		label_2 = new JLabel("<");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(12, 142, 46, 170);
		getContentPane().add(label_2);

		label_3 = new JLabel(">");
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(291, 142, 46, 170);
		getContentPane().add(label_3);

		label_4 = new JLabel("");
		label_4.addMouseListener(new Label_4MouseListener());
		label_4.setBounds(57, 142, 232, 170);
		getContentPane().add(label_4);

		label_5 = new JLabel("New label");
		label_5.setBounds(56, 322, 233, 83);
		getContentPane().add(label_5);

		if (flag) {
			try {
				var rs = res("select count(*) from course_registration where uno=  " + uno);
				rs.next();
				idx = rs.getInt(1) - 1;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				var rs = res("select count(*) from test where uno=  " + uno);
				rs.next();
				idx = rs.getInt(1) - 1;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try (var rs = res("select count(*) from (select cno, start_date, 1 t from course_registration where uno = " + uno
				+ " union select cno, exam_date, 2 t from test where uno = " + uno + ") sub join certi using(cno);")) {
			rs.next();
			max =rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		load();
	}

	int idx, max, cno;

	private void load() {
		try (var rs = res("select * from (select cno, start_date, 1 t from course_registration where uno = " + uno
				+ " union select cno, exam_date, 2 t from test where uno = " + uno + ") sub join certi using(cno) limit "+idx+",1")) {
			rs.next();
			label.setText(rs.getInt("t")==1? "수강신청이 완료되었습니다.":"시험신청이 완료되었습니다.");
			label_1.setBackground(rs.getInt("t")==1?Color.blue:Color.red);
			label_1.setOpaque(true);
			label_4.setIcon(getIcon("certification/"+rs.getInt("cno")+".png"));
			cno = rs.getInt("cno");
			if(rs.getInt("t")==1)
				label_5.setText(String.format("<html>%s %d급<br>%s~%s<br>시청률은 60%%이상이어야 합니다.", rs.getString("cname"), rs.getInt("ratring"),rs.getString("start_date"),rs.getDate("start_date").toLocalDate().plusDays(28).toString()));
			else
				label_5.setText(String.format("<html>%s %d급<br>%s<br>점수는 60점이상이어야 합니다.", rs.getString("cname"), rs.getInt("ratring"),rs.getString("start_date")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		label_2.setEnabled(idx!=0);
		label_3.setEnabled(idx!=max-1);
	}

	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(label_3.isEnabled()) {
				idx++;
				load();
			}
		}
	}
	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(label_2.isEnabled()) {
				idx--;
				load();
			}
		}
	}
	private class ThisWindowListener extends WindowAdapter {
		@Override
		public void windowClosed(WindowEvent e) {
			showPage("A_메인");
		}
	}
	private class Label_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount()==2) {
				showPage(new K_강의(cno),"K_강의");
			}
		}
	}
}
