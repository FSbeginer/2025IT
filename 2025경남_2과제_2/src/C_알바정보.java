import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

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
	public JLabel label_1;
	public JButton button;
	public JScrollPane scrollPane;
	public JPanel panel;
	public JPanel panel_1;

	public C_알바정보(int jno) {
		setTitle("\uC54C\uBC14 \uC815\uBCF4");
		this.jno = jno;
		setBounds(100, 100, 450, 530);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		label = new JLabel("New label");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setBounds(12, 0, 410, 93);
		getContentPane().add(label);

		label_1 = new JLabel("");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setBounds(12, 430, 57, 51);
		getContentPane().add(label_1);

		button = new JButton("\uC9C0\uC6D0\uD558\uAE30 \u2192");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(288, 440, 134, 41);
		getContentPane().add(button);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 109, 410, 311);
		getContentPane().add(scrollPane);

		panel = new JPanel();
		panel.setPreferredSize(new Dimension(350, 590));
		scrollPane.setViewportView(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 128, 0));
		panel_1.setBounds(12, 103, 410, 53);
		getContentPane().add(panel_1);

		load();
	}

	private void load() {
		try (var rs = res("select * from job join brand using(bno) where jno = " + jno)) {
			rs.next();
			label.setText("<html>" + rs.getString("jname"));
			panel.add(new C_알바패널(rs.getInt("jno"), rs.getInt("jmoney"), rs.getInt("jday"), rs.getInt("jtime"),
					rs.getInt("jwork"), rs.getInt("jgrade"), rs.getInt("jpeople"), rs.getString("bname")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = res("select * from likes where uno = " + BF.uno + " and jno = " + jno)) {
			if (rs.next()) {
				label_1.setIcon(getIcon("icon/하트1.png", 50, 50));
			} else {
				label_1.setIcon(getIcon("icon/하트2.png", 50, 50));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			try (var rs = res("select * from likes where uno = " + BF.uno + " and jno = " + jno)) {
				if (rs.next()) {
					label_1.setIcon(getIcon("icon/하트2.png", 50, 50));
					execute("delete from likes where lno = " + rs.getInt("lno"));
				} else {
					label_1.setIcon(getIcon("icon/하트1.png", 50, 50));
					var pre = pre("insert into likes values(0,?,?,?)");
					preSet(pre, jno, LocalDate.now(), uno);
					pre.execute();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try (var rs = res("select * from apply where jno = " + jno)) {
				if (rs.next()) {
					msgErr("이미 지원한 적 있는 알바입니다.");
					return;
				}
				else {
					var pre = pre("insert into apply values(0,?,?,?,0)");
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
