import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class D_패널 extends JPanel {
	public JLabel label;
	public JLabel label_1;
	public JScrollPane scrollPane;
	public JTextArea textArea;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JPanel panel;
	public JLabel label_5;
	public JLabel label_6;
	public JLabel label_7;
	public JLabel label_8;
	public JScrollPane scrollPane_1;
	public JPanel panel_1;
	public JLabel label_9;

	/**
	 * Create the panel.
	 */
	int mno;
	public JButton button;
	private int l_no;

	public D_패널(int mno) {
		this.mno = mno;
		setSize(914, 379 * 2);
		setLayout(null);

		label_9 = new JLabel();
		label_9.setBounds(12, 12, 47, 39);
		add(label_9);

		label = new JLabel("");
		label.setBounds(12, 10, 200, 245);
		add(label);

		label_1 = new JLabel("New label");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_1.setBounds(224, 10, 502, 58);
		add(label_1);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 282, 867, 188);
		add(scrollPane);

		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);

		label_2 = new JLabel("New label");
		label_2.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_2.setBounds(224, 78, 502, 26);
		add(label_2);

		label_3 = new JLabel("New label");
		label_3.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_3.setBounds(224, 133, 502, 26);
		add(label_3);

		label_4 = new JLabel("New label");
		label_4.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_4.setBounds(224, 183, 502, 26);
		add(label_4);

		panel = new JPanel();
		panel.setBounds(12, 480, 306, 268);
		add(panel);

		label_5 = new JLabel("성인");
		label_5.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		label_5.setBounds(330, 480, 85, 35);
		add(label_5);

		label_6 = new JLabel("청소년");
		label_6.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		label_6.setBounds(330, 525, 85, 35);
		add(label_6);

		label_7 = new JLabel("어린이");
		label_7.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		label_7.setBounds(330, 570, 85, 35);
		add(label_7);

		label_8 = new JLabel("유아");
		label_8.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		label_8.setBounds(330, 615, 85, 35);
		add(label_8);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(496, 480, 383, 268);
		add(scrollPane_1);

		panel_1 = new JPanel();
		scrollPane_1.setViewportView(panel_1);
		panel_1.setLayout(null);

		button = new JButton("예매하기");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(221, 230, 97, 23);
		add(button);

		load();

	}

	private void load() {
		try {
			var rs = BF.res("select * from movie join genre using(g_no) where m_no = " + mno);
			rs.next();
			label_9.setIcon(BF.getIcon("limits/" + rs.getInt("l_no") + ".png", 35, 35));
			label.setIcon(BF.getIcon("movies/" + rs.getInt("m_no") + ".jpg", label.getWidth(), label.getHeight()));
			label_1.setText("제목: " + rs.getString("m_name"));
			label_2.setText("감독: " + rs.getString("m_dir"));
			label_3.setText("장르: " + rs.getString("g_name"));
			label_4.setText("개봉일: " + rs.getString("m_startday"));
			textArea.setText(rs.getString("m_plot"));
			l_no = rs.getInt("l_no");
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					scrollPane.getVerticalScrollBar().setValue(0);
				}
			});
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (BF.uno == 0) {
				BF.msgErr("로그인을 해주세요.");
				((BF) SwingUtilities.getWindowAncestor(scrollPane)).showPage(new B_로그인(), "B_로그인");
				return;
			}
			try {
				var rs = BF.res("select * from user where u_no = " + BF.uno);
				rs.next();
				var birth = rs.getDate("u_birth").toLocalDate();
				int age = LocalDate.now().getYear() - birth.getYear();
				if (birth.plusYears(age).isAfter(LocalDate.now()))
					age--;
				if (age < 19 && l_no == 4) {
					BF.msgErr("미성년자는 시청 금지입니다.");
					return;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			((BF) SwingUtilities.getWindowAncestor(scrollPane)).showPage(new E_예매(mno), "E_예매");
		}
	}
}
