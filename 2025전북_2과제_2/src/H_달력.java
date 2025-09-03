import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class H_달력 extends BF {

	List<Integer> list;
	public JLabel label;
	public JLabel label_1;
	public JPanel panel;
	public JPanel panel_1;
	public JLabel label_2;
	int dno;
	public H_달력(List<Integer> list, int dno) {
		setTitle("\uB2EC\uB825");
		this.list = list;
		this.dno = dno;
		setBounds(100, 100, 536, 387);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("\u25C0");
		label.addMouseListener(new LabelMouseListener());
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(12, 10, 57, 43);
		getContentPane().add(label);
		
		label_1 = new JLabel("\u25B6");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(451, 10, 57, 43);
		getContentPane().add(label_1);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 520, 58);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		label_2 = new JLabel("New label");
		label_2.setFont(new Font("굴림", Font.BOLD, 15));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(75, 10, 372, 38);
		panel.add(label_2);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(0, 59, 520, 289);
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(7, 7, 0, 0));

		
		addlabel();
		lendering();
	}
	
	LocalDate now = LocalDate.now();
	
	private void lendering() {
		dates.clear();
		
		LocalDate first = LocalDate.of(now.getYear(), now.getMonthValue(), 1);
		int week = first.getDayOfWeek().getValue()%7;
		label_2.setText(first.format(DateTimeFormatter.ofPattern("yyyy년 MM월")));
		
		for (int i = 0; i < 42; i++) {
			LocalDate date = first.plusDays(i-week);
			jls[i].setVisible(date.getMonthValue()==first.getMonthValue());
			jls[i].setText(date.getDayOfMonth()+"");
			dates.add(date);
		}
		label.setEnabled(!now.plusMonths(-1).isBefore(LocalDate.now()));
	}

	JLabel[] jls = new JLabel[42];
	List<LocalDate> dates = new ArrayList<LocalDate>();
	
	private void addlabel() {
		String[] week = "일,월,화,수,목,금,토".split(",");
		for (int i = 0; i < 7; i++) {
			JLabel jl = new JLabel(week[i],0);
			jl.setFont(new Font("맑은 고딕", 1, 12));
			if(i==0||i==6) jl.setForeground(Color.red);
			panel_1.add(jl);
		}
		
		for (int i = 0; i < 42; i++) {
			jls[i] = new JLabel("",0);
			jls[i].setFont(new Font("맑은 고딕", 1, 12));
			if(i%7==0||i%7==6) jls[i].setForeground(Color.red);
			int idx = i;
			jls[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					var date = dates.get(idx);
					if(date.isAfter(LocalDate.now())) {
						if(list.contains(date.getDayOfWeek().getValue()%7)) {
							try {
								var pre = pre("insert into reservation values(0,?,?,?)");
								preSet(pre, date, uno, dno);
								pre.execute();
								msgInfo("예약이 완료되었습니다.");
								showPage("B_메인");
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
						else {
							msgErr("근무일이 아닙니다.");
						}
					}
					else {
						msgErr("오늘 이후의 날짜만 선택 가능합니다.");
					}
				}
			});
			panel_1.add(jls[i]);
		}
	}

	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(label_1.isEnabled()) {
				now = now.plusMonths(1);
				lendering();
			}
		}
	}
	private class LabelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(label.isEnabled()) {
				now = now.plusMonths(-1);
				lendering();
			}
		}
	}
}

