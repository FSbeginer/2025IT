package 풀이본;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class G_나의과정 extends BF {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;
	public JLabel label_6;

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
		setTitle("나의 과정폼");
		setBounds(100, 100, 450, 559);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel(getIcon("icon/logo.png",50,49));
		label.setBounds(12, 10, 57, 49);
		getContentPane().add(label);
		
		label_1 = new JLabel("Skills Qualification Association");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label_1.setBounds(81, 2, 271, 57);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("     나의 강의실");
		label_2.setForeground(Color.WHITE);
		label_2.setOpaque(true);
		label_2.setBounds(12, 61, 410, 57);
		getContentPane().add(label_2);
		
		label_3 = new JLabel("<");
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(12, 128, 28, 194);
		getContentPane().add(label_3);
		
		label_4 = new JLabel(">");
		label_4.addMouseListener(new Label_4MouseListener());
		label_4.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(394, 128, 28, 194);
		getContentPane().add(label_4);
		
		label_5 = new JLabel("");
		label_5.addMouseListener(new Label_5MouseListener());
		label_5.setBounds(52, 128, 337, 194);
		getContentPane().add(label_5);
		
		label_6 = new JLabel("New label");
		label_6.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		label_6.setBounds(52, 332, 337, 137);
		getContentPane().add(label_6);
		
		try {
			var rs = res("select count(*) from (select ano as no, exam_date, 1 as flag, c.* from test join certi c using(cno) where uno = "+uno+" union all select  cno,start_date, 2 as flag, c.* from course_registration join certi c using(cno) where uno = "+uno+" order by exam_date desc) sub");
			rs.next();
			max = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		load();

	}
	int idx = 0, max;
	private void load() {
		try {
			var rs =res("select * from (select ano as no, exam_date, 1 as flag, c.* from test join certi c using(cno) where uno = "+uno+" union all select  cno,start_date, 2 as flag, c.* from course_registration join certi c using(cno) where uno = "+uno+" order by exam_date desc) sub limit "+idx+",1;");
			if(rs.next()) {
				if(rs.getInt("flag")==1) {
					label_1.setText("시험신청이 완료되었습니다.");
					label_2.setBackground(Color.red);
					label_5.setName(rs.getInt("cno")+"");
					label_5.setIcon(getIcon("certification/"+rs.getInt("cno")+".png",label_5.getWidth(),label_5.getHeight()));
					label_6.setText(String.format("<html>%s %d급<br><br>%s<br><br>점수는 60점이상이어야 합니다.", rs.getString("cname"), rs.getInt("ratring"),rs.getString("exam_date")));
				}
				else {
					label_1.setText("수강신청이 완료되었습니다.");
					label_2.setBackground(Color.blue);
					label_5.setName(rs.getInt("cno")+"");
					label_5.setIcon(getIcon("certification/"+rs.getInt("cno")+".png",label_5.getWidth(),label_5.getHeight()));
					label_6.setText(String.format("<html>%s %d급<br><br>%s~%s<br><br>시청률은 60%%이상이어야 합니다.", rs.getString("cname"), rs.getInt("ratring"),rs.getString("exam_date"), rs.getDate("exam_date").toLocalDate().plusDays(28).toString()));
				}
			}
			label_3.setEnabled(idx!=max-1);
			label_4.setEnabled(idx!=0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	private class Label_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(label_4.isEnabled()) {
				idx--;
				load();
			}
		}
	}
	private class Label_5MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount()==2) {
				int cno = Integer.parseInt(label_5.getName());
				showPage(new K_강의(cno),"K_강의");
			}
		}
	}
}
