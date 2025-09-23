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
import java.time.format.DateTimeFormatter;

public class H_달력 extends BF {

	List<Integer> work;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JPanel panel;
	int dno;
	public H_달력(List<Integer> work, int dno) {
		setTitle("\uB2EC\uB825");
		this.work = work;
		this.dno = dno;
		setBounds(100, 100, 574, 455);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("New label");
		label.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		label.setFont(new Font("굴림", Font.BOLD, 19));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 558, 60);
		getContentPane().add(label);
		
		label_1 = new JLabel("\u25C0");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(0, 10, 63, 34);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("\u25B6");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(495, 10, 63, 34);
		getContentPane().add(label_2);
		
		panel = new JPanel();
		panel.setBounds(0, 61, 558, 355);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(7, 7, 0, 0));
		
		addlabel();
		lendering();
	}
	private void lendering() {
		dates.clear();
		LocalDate first = LocalDate.of(now.getYear(), now.getMonthValue(), 1);
		label.setText(first.format(DateTimeFormatter.ofPattern("yyyy년 MM월")));
		int week = first.getDayOfWeek().getValue()%7;
		for (int i = 0; i < jls.length; i++) {
			LocalDate date = first.plusDays(i-week);
			dates.add(date);
			if(first.getMonthValue()==date.getMonthValue())
				jls[i].setText(date.getDayOfMonth()+"");
			else
				jls[i].setText("");
		}
		label_1.setEnabled(!(first.getYear()==LocalDate.now().getYear() && first.getMonthValue()==LocalDate.now().getMonthValue()));
	}
	JLabel[] jls = new JLabel[42];
	LocalDate now = LocalDate.now();
	List<LocalDate> dates = new ArrayList<LocalDate>();
	private void addlabel() {
		String[] week = "일 월 화 수 목 금 토".split(" ");
		for (int i = 0; i < 7; i++) {
			JLabel jl = new JLabel(week[i],0);
			jl.setFont(new Font("맑은 고딕",1, 13));
			if(i%7==0||i%7==6) jl.setForeground(Color.red);
			panel.add(jl);
		}
		for (int i = 0; i < 42; i++) {
			jls[i] = new JLabel("",0);
			jls[i].setFont(new Font("맑은 고딕",1, 13));
			if(i%7==0||i%7==6) jls[i].setForeground(Color.red);
			int idx = i;
			jls[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int week = idx%7;
					if(!dates.get(idx).isAfter(LocalDate.now())) {
						msgErr("오늘 이후의 날짜만 선택 가능합니다.");
						return;
					}
					if(work.contains((Integer)week)) {
						try {
							var pre = pre("insert into reservation values(0, ?, ?, ?)");
							preSet(pre, dates.get(idx), uno, dno);
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
			});
			panel.add(jls[i]);
		}
	}

	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(label_1.isEnabled()) {
				now = now.plusMonths(-1);
				lendering();
			}
		}
	}
	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(label_2.isEnabled()) {
				now = now.plusMonths(1);
				lendering();
			}
		}
	}
}
