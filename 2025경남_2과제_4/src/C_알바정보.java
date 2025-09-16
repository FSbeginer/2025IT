import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class C_알바정보 extends BF {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					C_알바정보 frame = new C_알바정보(1);
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
	int jno;
	public JLabel label;
	public JPanel panel;
	public JScrollPane scrollPane;
	public JPanel panel_1;
	public JLabel label_1;
	public JButton button;
	public C_알바정보(int jno) {
		setTitle("\uC54C\uBC14 \uC815\uBCF4");
		this.jno = jno;
		setBounds(100, 100, 388, 490);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("New label");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label.setBounds(12, 10, 350, 64);
		getContentPane().add(label);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 128, 0));
		panel.setBounds(12, 84, 350, 10);
		getContentPane().add(panel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 94, 350, 297);
		getContentPane().add(scrollPane);
		
		panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		
		label_1 = new JLabel("");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setBounds(12, 401, 57, 40);
		getContentPane().add(label_1);
		
		button = new JButton("\uC9C0\uC6D0\uD558\uAE30 \u2192");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(214, 401, 148, 40);
		getContentPane().add(button);
		
		try (var rs = res("select * from likes where uno = "+uno+" and jno ="+jno)) {
			if(rs.next()) {
				label_1.setIcon(getIcon("icon/하트1.png",40,40));
			}
			else {
				label_1.setIcon(getIcon("icon/하트2.png",40,40));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = res("select * from job where jno = "+jno)) {
			rs.next();
			label.setText("<html>"+rs.getString("jname"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel_1.setPreferredSize(new Dimension(330, 590));
		panel_1.setLayout(new BorderLayout(0, 0));
		var c= new C_패널(jno);
		panel_1.add(c);
	}

	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			try (var rs = res("select * from likes where uno = "+uno+" and jno ="+jno)) {
				if(rs.next()) {
					label_1.setIcon(getIcon("icon/하트2.png",40,40));
					execute("delete from likes where uno = "+uno+" and jno = "+jno);
				}
				else {
					label_1.setIcon(getIcon("icon/하트1.png",40,40));
					execute("insert into likes values(0, "+jno+", curdate(),"+uno+")");
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try (var rs = res("select * from apply where jno = "+jno+" and uno ="+uno)) {
				if(rs.next()) {
					msgErr("이미 지원한 적 있는 알바입니다.");
				}
				else {
					var pre = pre("insert into apply values(0,?,curdate(),?,0)");
					preSet(pre, jno, uno);
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
