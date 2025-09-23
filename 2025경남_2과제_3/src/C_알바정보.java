import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
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
	int jno ;
	public JLabel label;
	public JScrollPane scrollPane;
	public JPanel panel;
	public JLabel label_1;
	public JButton button;
	public C_알바정보(int jno) {
		setTitle("\uC54C\uBC14 \uC815\uBCF4");
		this.jno = jno;
		setBounds(100, 100, 404, 491);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("New label");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		label.setBounds(13, 13, 408, 71);
		getContentPane().add(label);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(18, 96, 350, 290);
		getContentPane().add(scrollPane);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(0, 580));
		scrollPane.setViewportView(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		label_1 = new JLabel("");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setBounds(13, 397, 57, 45);
		getContentPane().add(label_1);
		
		button = new JButton("\uC9C0\uC6D0\uD558\uAE30 \u2192");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(244, 411, 124, 31);
		getContentPane().add(button);
		
		load();
	}
	private void load() {
		try (var rs = res("select * from job where jno = "+jno)) {
			rs.next();
			label.setText(rs.getString("jname"));
			panel.add(new C_패널(jno));
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	}
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			try (var rs = res("select * from likes where uno = "+uno+" and jno ="+jno)) {
				if(rs.next()) {
					label_1.setIcon(getIcon("icon/하트2.png",40,40));
					execute("delete from likes where lno = "+rs.getInt("lno"));
				}
				else {
					label_1.setIcon(getIcon("icon/하트1.png",40,40));
					var pre = BF.pre("insert into likes values(0,?,?,?)");
					preSet(pre, jno, LocalDate.now(), uno);
					pre.execute();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try (var rs = res("select * from apply where uno = "+uno+" and jno = "+jno)) {
				if(rs.next()) {
					msgErr("이미 지원한 적 있는 알바입니다.");
				}
				else {
					var pre = BF.pre("insert into apply values(0,?,?,?,0)");
					preSet(pre, jno, LocalDate.now(), uno);
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
