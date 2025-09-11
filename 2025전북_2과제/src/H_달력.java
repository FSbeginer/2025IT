import java.awt.EventQueue;

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
import java.util.ArrayList;
import java.util.List;

public class H_달력 extends BF {


	boolean isMoring;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JPanel panel;
	List<Integer> workDay;
	int dno;
	public H_달력(int dno, boolean isMoring, List<Integer> workDay) {
		setTitle("\uB2EC\uB825");
		this.isMoring = isMoring;
		this.workDay = workDay;
		this.dno = dno;
		setBounds(100, 100, 653, 544);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("2025\uB144 09\uC6D4");
		label.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		label.setFont(new Font("굴림", Font.BOLD, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 637, 76);
		getContentPane().add(label);
		
		label_1 = new JLabel("\u25C0");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(12, 0, 66, 76);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("\u25B6");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(546, 0, 66, 76);
		getContentPane().add(label_2);
		
		panel = new JPanel();
		panel.setBounds(0, 77, 637, 428);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(7, 7, 0, 0));
		
		addWeek();
		lendering();
	}
	LocalDate now = LocalDate.now();
	private void lendering() {
		LocalDate first = LocalDate.of(now.getYear(), now.getMonth(), 1);
		int week = first.getDayOfWeek().getValue()%7;
		for (int i = 0; i < 42; i++) {
			var jl = jls.get(i);
			LocalDate date = first.plusDays(i-week);
			jl.setText(date.getDayOfMonth()+"");
			jl.setVisible(date.getMonthValue()==first.getMonthValue());
		}
		label.setText(first.format(DateTimeFormatter.ofPattern("yyyy년 MM월")));
		label_1.setEnabled(first.getMonthValue()!=LocalDate.now().getMonthValue());
	}

	List<JLabel> jls = new ArrayList<JLabel>();
	private void addWeek() {
		var txt = "일,월,화,수,목,금,토".split(",");
		for (int i = 0; i < 7; i++) {
			JLabel jl = new JLabel(txt[i],0);
			jl.setFont(new Font("굴림",Font.BOLD, 12));
			if(i%7==0||i%7==6)
				jl.setForeground(Color.red);
			panel.add(jl);
		}
		for (int i = 0; i < 42; i++) {
			JLabel jl = new JLabel("",0);
			jl.setFont(new Font("굴림",Font.BOLD, 12));
			if(i%7==0||i%7==6)
				jl.setForeground(Color.red);
			jl.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					LocalDate date = LocalDate.of(now.getYear(), now.getMonth(), Integer.parseInt(jl.getText()));
					if(date.isAfter(LocalDate.now())) {
						if(workDay.contains(date.getDayOfWeek().getValue()%7)) {
							try {
								var pre = pre("insert into reservation values(0,?,?,?) ");
								preSet(pre, date, uno, dno);
								pre.execute();
								msgInfo("예약이 완료되었습니다.");
								showPage("B_메인");
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
						else {
							msgErr("선택된 날짜에 일정이 없습니다.");
						}
					}
					else {
						msgErr("오늘 이후의 날짜만 선택 가능합니다.");
					}
				}
			});
			jls.add(jl);
			panel.add(jl);
		}
	}

	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(label_1.isEnabled()) {
				now = now.plusMonths(-1);
			}
			else {
				msgErr("이전 날짜는 선택할 수 없습니다.");
			}
			lendering();
		}
	}
	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			now = now.plusMonths(1);
			lendering();
		}
	}
}
