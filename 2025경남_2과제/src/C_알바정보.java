import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class C_알바정보 extends BF {

	int jno;
	public JLabel label;
	public JPanel panel;
	public JScrollPane scrollPane;
	public JLabel label_1;
	public JButton button;
	public JPanel panel_1;
	public C_알바정보(int jno) {
		setTitle("\uC54C\uBC14 \uC815\uBCF4");
		this.jno = jno;
		setBounds(100, 100, 385, 512);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("New label");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		label.setBounds(12, 10, 350, 95);
		getContentPane().add(label);
		
		panel = new JPanel();
		panel.setBackground(orange);
		panel.setBounds(12, 115, 350, 300);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 10, 350, 290);
		panel.add(scrollPane);
		
		panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(null);
		
		label_1 = new JLabel("");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setBounds(12, 414, 49, 46);
		getContentPane().add(label_1);
		
		button = new JButton("\uC9C0\uC6D0\uD558\uAE30 \u2192");
		button.addActionListener(new ButtonActionListener());
		button.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		button.setBounds(171, 427, 191, 33);
		getContentPane().add(button);

		try {
			var rs = res("select lno from user join likes using(uno) where uno = "+uno+" and jno = "+jno);
			if(rs.next()) {
				label_1.setIcon(getIcon("icon/하트1.png",45,45));
			}
			else {
				label_1.setIcon(getIcon("icon/하트2.png",45,45));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			var rs =res("select * from job where jno = "+jno);
			rs.next();
			label.setText("<html>"+rs.getString("jname"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel_1.add(new JobInfoPanel(jno));
		panel_1.setPreferredSize(new Dimension(0, 580));
	}
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			try {
				var rs = res("select lno from user join likes using(uno) where uno = "+uno+" and jno = "+jno);
				if(rs.next()) {
					int lno = rs.getInt(1);
					execute("delete from likes where lno ="+lno);
					label_1.setIcon(getIcon("icon/하트2.png",45,45));
				}
				else {
					var pre = pre("insert into likes values(0,?,?,?)");
					preSet(pre, jno, LocalDate.now(), uno);
					pre.execute();
					label_1.setIcon(getIcon("icon/하트1.png",45,45));
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int grade = 0;
			try {
				var rs = BF.res("select * from job join brand using(bno) where jno = "+jno);
				rs.next();
				grade = rs.getInt("jgrade");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				var rs  = res("select * from apply where uno = "+uno+" and jno = "+jno);
				if(rs.next()) {
					msgErr("이미 지원한 적 있는 알바입니다.");
				}
				else {
					if(grade!=0&&grade>ugrade) {
						msgErr("지원자격 미달입니다.");
						return;
					}
					var pre = pre("insert into apply values(0,?,?,?,0)");
					preSet(pre, jno,LocalDate.now(),uno);
					pre.execute();
					msgInfo("지원이 완료되었습니다.");
					dispose();
				}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
