import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;

public class H_딜력 extends BF {

	int dno;
	List<Integer> workday;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JPanel panel;

	public H_딜력(int dno, List<Integer> workday) {
		setTitle("\uB2EC\uB825");
		this.dno = dno;
		this.workday = workday;
		setBounds(100, 100, 552, 443);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		label = new JLabel("2025\uB144 9\uC6D4");
		label.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 536, 53);
		getContentPane().add(label);

		label_1 = new JLabel("\u25C0");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(12, 10, 57, 38);
		getContentPane().add(label_1);

		label_2 = new JLabel("\u25B6");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(467, 10, 57, 38);
		getContentPane().add(label_2);

		panel = new JPanel();
		panel.setBounds(10, 63, 514, 316);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(7, 7, 0, 0));

		addLabel();
		lendering();
	}

	LocalDate now = LocalDate.now();

	private void lendering() {
		dates.clear();
		LocalDate first = LocalDate.of(now.getYear(), now.getMonthValue(), 1);
		int week = first.getDayOfWeek().getValue() % 7;
		label.setText(first.format(DateTimeFormatter.ofPattern("yyyy년 MM월")));
		for (int i = 0; i < 42; i++) {
			var date = first.plusDays(i-week);
			if(date.getMonthValue()==first.getMonthValue()) {
				jls[i].setText(date.getDayOfMonth()+"");
			}
			else {
				jls[i].setVisible(false);
			}
			dates.add(date);
		}
		label_1.setEnabled(!now.plusMonths(-1).isBefore(LocalDate.now()));
	}

	List<LocalDate> dates = new ArrayList<LocalDate>();

	private void addLabel() {
		String[] week = "일 월 화 수 목 금 토".split(" ");
		for (int i = 0; i < week.length; i++) {
			JLabel jl = new JLabel(week[i], 0);
			if (i % 7 == 0 || i % 7 == 6) {
				jl.setForeground(Color.red);
			}
			jl.setFont(new Font("맑은 고딕", 1, 13));
			panel.add(jl);
		}
		for (int i = 0; i < 42; i++) {
			jls[i] = new JLabel("", 0);
			if (i % 7 == 0 || i % 7 == 6) {
				jls[i].setForeground(Color.red);
			}
			jls[i].setFont(new Font("맑은 고딕", 1, 13));
			int idx = i;
			jls[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					var d = dates.get(idx);
					int wv = d.getDayOfWeek().getValue() % 7;
					if (!d.isAfter(LocalDate.now())) {
						msgErr("오늘 이후의 날짜만 선택 가능합니다.");
						return;
					}
					if (workday.contains(wv)) {
						try {
							var pre = pre("insert into reservation values(0,?,?,?)");
							preSet(pre, d, uno, dno);
							pre.execute();
							msgInfo("예약이 완료되었습니다.");
							showPage("B_메인");
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					} else {
						msgErr("근무일이 아닙니다.");
						return;
					}
				}
			});
			panel.add(jls[i]);
		}

	}

	JLabel[] jls = new JLabel[42];

	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			now = now.plusMonths(1);
			lendering();
		}
	}

	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (label_1.isEnabled()) {
				now = now.plusMonths(-1);
				lendering();
			}
		}
	}
}
