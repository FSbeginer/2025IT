import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.SQLException;
import java.awt.Color;
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
	public JLabel label_6;
	private int no;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					G_나의과정 frame = new G_나의과정();
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
	public G_나의과정() {
		addWindowListener(new ThisWindowListener());
		setTitle("나의 과정폼");
		setBounds(100, 100, 454, 521);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel(getIcon("icon/logo.png",40,40));
		label.setBounds(8, 5, 71, 60);
		getContentPane().add(label);
		
		label_1 = new JLabel("New label");
		label_1.setBounds(83, 11, 297, 51);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("  나의 강의실");
		label_2.setForeground(Color.WHITE);
		label_2.setOpaque(true);
		label_2.setBounds(8, 81, 418, 69);
		getContentPane().add(label_2);
		
		label_3 = new JLabel("<");
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(8, 160, 57, 199);
		getContentPane().add(label_3);
		
		label_4 = new JLabel(">");
		label_4.addMouseListener(new Label_4MouseListener());
		label_4.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(369, 160, 57, 199);
		getContentPane().add(label_4);
		
		label_5 = new JLabel("");
		label_5.addMouseListener(new Label_5MouseListener());
		label_5.setBounds(83, 174, 267, 168);
		getContentPane().add(label_5);
		
		label_6 = new JLabel("New label");
		label_6.setBounds(60, 369, 325, 92);
		getContentPane().add(label_6);

		try (var rs = res("select * from (select 1 as flag, start_date from course_registration where uno = "+uno+" union all select 2 as flag, exam_date from test where uno ="+uno+" ) sub order by start_date desc;")) {
			rs.next();
			no = rs.getInt(1);
			if(no==1) {
				idx = getmax2()-1;
				loadCourse();
			}
			else {
				idx = getmax()-1;
				loadTest();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private int getmax2() {
		try (var rs = res("select count(*) from course_registration where uno = "+uno)) {
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	private int getmax() {
		try (var rs = res("select count(*) from test join certi using(cno)  where uno = "+uno)) {
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	int idx = 0;
	private void loadTest() {
		try (var rs = res("select * from test join certi using(cno)  where uno = "+uno+" order by exam_date limit "+idx+",1")) {
			rs.next();
			label_1.setText("시험신청이 완료되었습니다.");
			label_2.setBackground(Color.red);
			label_5.setIcon(getIcon("certification/"+rs.getInt("cno")+".png"));
			label_6.setText("<html>"+rs.getString("cname")+" "+rs.getInt("ratring")+"급<br>"+rs.getString("exam_date")+"<br>점수는 60점이상이어야 합니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		label_3.setEnabled(idx!=0);
		label_4.setEnabled(idx!=getmax()-1);
	}

	private void loadCourse() {
		try (var rs = res("select * from course_registration join certi using(cno) where uno = "+uno+" limit "+idx+",1")) {
			rs.next();
			label_1.setText("수강신청이 완료되었습니다.");
			label_2.setBackground(Color.blue);
			label_5.setIcon(getIcon("certification/"+rs.getInt("cno")+".png"));
			label_6.setText("<html>"+rs.getString("cname")+" "+rs.getInt("ratring")+"급<br>"+rs.getString("start_date")+"~"+rs.getDate("start_date").toLocalDate().plusDays(28).toString()+"<br>시청률은 60%이상이어야 합니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		label_3.setEnabled(idx!=0);
		label_4.setEnabled(idx!=getmax2()-1);
	}
	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(label_3.isEnabled()) {
				idx = idx-1;
				if(no==1) {
					loadCourse();
				}
				else {
					loadTest();
				}
			}
		}
	}
	private class Label_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(label_4.isEnabled()) {
				idx = idx+1;
				if(no==1) {
					loadCourse();
				}
				else {
					loadTest();
				}
			}
		}
	}
	private class Label_5MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount()==2) {
				showPage(new K_강의(),"K_강의");
			}
		}
	}
	private class ThisWindowListener extends WindowAdapter {
		@Override
		public void windowClosed(WindowEvent e) {
			showPage("A_메인");
		}
	}
}
