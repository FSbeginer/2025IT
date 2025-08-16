import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PageReservationList extends BP {
	public JPanel panel;
	public JPanel panel_1;
	public JPanel panel_2;
	public JPanel panel_3;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;
	public JLabel label_6;
	public JPanel panel_4;
	public JLabel label_7;
	public JLabel label_8;
	public JLabel label_9;
	public JLabel label_10;
	public JLabel label_11;
	public JPanel panel_5;

	public PageReservationList() {
		setLayout(null);
		setName("¿¹¾à³»¿ª");
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 10, 392, 423);
		add(panel);
		panel.setLayout(null);

		panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 255, 255));
		panel_5.setBounds(29, 33, 333, 306);
		panel.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));

		panel_1 = new JPanel();
		panel_1.setBounds(416, 10, 427, 423);
		add(panel_1);
		panel_1.setLayout(new CardLayout(0, 0));

		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_1.add(panel_2, "name_45667598820500");
		panel_2.setLayout(null);

		label = new JLabel("");
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(142, 29, 153, 148);
		panel_2.add(label);

		label_1 = new JLabel("New label");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(112, 179, 227, 21);
		panel_2.add(label_1);

		panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel_4.setBounds(112, 199, 227, 214);
		panel_2.add(panel_4);
		panel_4.setLayout(null);

		label_2 = new JLabel("| \uC804\uC2DC\uAD00\uBA85");
		label_2.setBounds(12, 10, 80, 21);
		panel_4.add(label_2);
		label_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));

		label_3 = new JLabel("| \uD504\uB85C\uADF8\uB7A8\uBA85");
		label_3.setBounds(12, 41, 80, 21);
		panel_4.add(label_3);
		label_3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));

		label_4 = new JLabel("| \uAE08\uC561");
		label_4.setBounds(12, 72, 80, 21);
		panel_4.add(label_4);
		label_4.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));

		label_5 = new JLabel("| \uD3EC\uC778\uD2B8");
		label_5.setBounds(12, 103, 80, 21);
		panel_4.add(label_5);
		label_5.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));

		label_6 = new JLabel("| \uC778\uC6D0");
		label_6.setBounds(12, 134, 80, 21);
		panel_4.add(label_6);
		label_6.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));

		label_7 = new JLabel("New label");
		label_7.setForeground(Color.GRAY);
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setBounds(117, 10, 110, 21);
		panel_4.add(label_7);

		label_8 = new JLabel("New label");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setForeground(Color.GRAY);
		label_8.setBounds(117, 41, 110, 21);
		panel_4.add(label_8);

		label_9 = new JLabel("New label");
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		label_9.setForeground(Color.GRAY);
		label_9.setBounds(117, 72, 110, 21);
		panel_4.add(label_9);

		label_10 = new JLabel("New label");
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		label_10.setForeground(Color.GRAY);
		label_10.setBounds(117, 103, 110, 21);
		panel_4.add(label_10);

		label_11 = new JLabel("New label");
		label_11.setVerticalAlignment(SwingConstants.TOP);
		label_11.setHorizontalAlignment(SwingConstants.LEFT);
		label_11.setForeground(Color.GRAY);
		label_11.setBounds(147, 139, 80, 58);
		panel_4.add(label_11);

		panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_1.add(panel_3, "name_45672165837600");
		panel_3.setLayout(null);
		
		label_12 = new JLabel("\uC608\uC57D \uB0B4\uC5ED\uC774 \uC874\uC7AC\uD558\uC9C0 \uC54A\uC2B5\uB2C8\uB2E4.");
		label_12.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 21));
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setBounds(41, 101, 345, 68);
		panel_3.add(label_12);
		
		label_13 = new JLabel("\uC608\uC57D\uD558\uC2DC\uACA0\uC2B5\uB2C8\uAE4C?");
		label_13.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 21));
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setBounds(41, 148, 345, 31);
		panel_3.add(label_13);
		
		button = new JButton("\uC608\uC57D\uD558\uB7EC \uAC00\uAE30");
		button.addActionListener(new ButtonActionListener());
		button.setForeground(Color.white);
		button.setBackground(BF.blue);
		button.setBounds(79, 242, 296, 31);
		panel_3.add(button);

		addmonth();
		((CardLayout)panel_1.getLayout()).last(panel_1);
	}

	List<LocalDate> list = new ArrayList<LocalDate>();
	public JLabel label_12;
	public JLabel label_13;
	public JButton button;

	private void addmonth() {

		try (var rs = res("select * from ticket where uno= " + BF.uno)) {
			while (rs.next()) {
				list.add(rs.getDate("date").toLocalDate());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		var cal = new Calendar(list);
		for (int i = 0; i < 42; i++) {
			int idx = i;
			cal.days[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						if (cal.days[idx].chk) {
							var rs = res(
									"select s.name, p.name, t.price, t.point, adult, youth, kid, code from ticket t join location l using(lno) join program p using(pno) join science s using(sno) where uno = "
											+ BF.uno + " and t.date = '" + cal.days[idx].date + "'");
							rs.next();
							label.setIcon(new ImageIcon(BF.getQR().getScaledInstance(label.getWidth(), label.getHeight(), 1)));
							label_1.setText(rs.getString("code"));
							label_7.setText(rs.getString(1));
							label_8.setText(rs.getString(2));
							label_9.setText(String.format("%,d", rs.getInt(3)));
							label_10.setText(String.format("%,d", rs.getInt(4)));
							label_11.setText(String.format("<html>¼ºÀÎ %d¸í<br>Ã»¼Ò³â %d¸í<br>¾î¸°ÀÌ %d¸í", rs.getInt(5), rs.getInt(6), rs.getInt(7)));
							((CardLayout)panel_1.getLayout()).first(panel_1);
						}
						else {
							((CardLayout)panel_1.getLayout()).last(panel_1);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		panel_5.add(cal);
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			((MainFrame)SwingUtilities.getWindowAncestor(PageReservationList.this)).showPage(new PageReservation());
		}
	}
}
