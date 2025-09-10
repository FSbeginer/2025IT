
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Reservation extends BF {

	int mno;
	public JPanel panel;
	public JPanel panel_1;
	public JScrollPane scrollPane;
	public JPanel panel_2;
	public JLabel label;
	public JLabel label_1;
	public JPanel panel_3;
	public JPanel panel_4;
	public JPanel panel_5;
	public JScrollPane scrollPane_1;
	public JPanel panel_6;
	public JButton button;

	public Reservation(int mno) {
		setTitle("예매");
		this.mno = mno;

		setBounds(100, 100, 715, 452);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		panel.setPreferredSize(new Dimension(180, 10));
		getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(10, 775));
		scrollPane.setViewportView(panel_3);
		panel_3.setLayout(new GridLayout(31, 1, 0, 5));

		panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(10, 80));
		panel.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));

		label = new JLabel("New label");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		panel_2.add(label);

		label_1 = new JLabel("New label");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(label_1);

		panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		panel_4 = new JPanel();
		panel_1.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));

		scrollPane_1 = new JScrollPane();
		panel_4.add(scrollPane_1, BorderLayout.CENTER);

		panel_6 = new JPanel();
		scrollPane_1.setViewportView(panel_6);
		panel_6.setLayout(null);

		panel_5 = new JPanel();
		panel_5.setPreferredSize(new Dimension(10, 80));
		panel_1.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(null);

		button = new JButton("좌석조회");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(392, 46, 127, 34);
		panel_5.add(button);

		addTimeLabel();
	}

	List<TimeLabel> times = new ArrayList<TimeLabel>();

	private void addTimeLabel() {
		LocalDate now = LocalDate.now();
		LocalDate first = LocalDate.of(now.getYear(), now.getMonth(), 1);
		String[] week = "일,월,화,수,목,금,토".split(",");
		for (int i = 0; i < YearMonth.from(first).lengthOfMonth(); i++) {
			TimeLabel lbl = new TimeLabel();
			var date = first.plusDays(i);
			lbl.label.setText(week[date.getDayOfWeek().getValue() % 7]);
			lbl.label_1.setText(date.getDayOfMonth() + "일");
			if (now.isAfter(date)) {
				lbl.setBackground(Color.lightGray);
			} else {
				times.add(lbl);
				lbl.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						for (var l : times) {
							l.setBackground(Color.white);
						}
						lbl.setBackground(Color.yellow);
						loadPanel6(date);
					}

				});
			}
			panel_3.add(lbl);
		}
		label.setText(first.getYear() + "년");
		label_1.setText(first.getMonthValue() + "월");
	}

	List<JPanel> list = new ArrayList<JPanel>();
	int sc_no = -1;

	private void loadPanel6(LocalDate date) {
		panel_6.removeAll();
		list.clear();
		try {
			var rs = res(
					"select *, count(length(r_setname)-length(replace(r_setname, ',',''))+1) cnt from reservation r right join schedule sc on sc_date = r_date and r_time = sc_time and sc.m_no = r.m_no where sc.m_no = "
							+ mno + " and sc_date = '" + date + "' group by sc_time order by sc_time");
			int w = panel_6.getWidth() - 25;
			int h = (panel_6.getHeight() - 20) / 4;
			int i = 0;
			while (rs.next()) {
				ReservationPanel panel = new ReservationPanel();
				panel.label.setText(rs.getInt("srm_srmno") + "관 (총 81석)");
				panel.label_1.setText(rs.getString("sc_time") + " " + (81 - rs.getInt("cnt")) + "석 남음");
				panel.setSize(w, h);
				panel.setLocation(0, (h + 5) * i);
				int scno = rs.getInt("sc_no");
				int 모르겠다  =  81 -rs.getInt("cnt");
				panel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(모르겠다==0) {
							msgErr("남은 자리가 없습니다.");
							return;
						}
						for (JPanel jPanel : list) {
							jPanel.setBackground(Color.white);
						}
						panel.setBackground(Color.yellow);
						sc_no = scno;
						
					}
				});
				panel_6.add(panel);
				list.add(panel_6);
				i++;
			}
			panel_6.setPreferredSize(new Dimension(0, (h+5)*i));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel_6.revalidate();
		panel_6.repaint();
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(sc_no==-1) {
				msgErr("스케줄을 선택해주세요.");
				return;
			}
			msgInfo("좌석예매 폼으로 이동하겠습니다.");
			
		}
	}
}
