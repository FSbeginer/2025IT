package 짜집기;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class 달력 extends JPanel {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JPanel panel;
	public JPanel panel_1;

	/**
	 * Create the panel.
	 */
	List<LocalDate> dates;
	public 달력(List<LocalDate> dates) {
		setSize(463, 421);
		setLayout(null);
		this.dates = dates;
		label = new JLabel("New label");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 463, 53);
		add(label);
		
		label_1 = new JLabel("<");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setFont(new Font("굴림", Font.BOLD, 20));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(72, 10, 48, 43);
		add(label_1);
		
		label_2 = new JLabel(">");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setFont(new Font("굴림", Font.BOLD, 20));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(365, 8, 48, 43);
		add(label_2);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(24, 87, 414, 298);
		add(panel);
		panel.setLayout(new GridLayout(6, 7, 0, 0));
		
		panel_1 = new JPanel();
		panel_1.setBounds(24, 47, 414, 43);
		add(panel_1);
		panel_1.setLayout(new GridLayout(0, 7, 0, 0));
		addlabel();
		lendering();
	}
	private void lendering() {
		LocalDate first = LocalDate.of(now.getYear(), now.getMonth(), 1);
		int week = first.getDayOfWeek().getValue() %7;
		for (int i = 0; i < 42; i++) {
			LocalDate date = first.plusDays(i-week);
			lbls[i].date = date;
			if(date.isBefore(LocalDate.now())) 
				lbls[i].jl.setEnabled(false);
			else
				lbls[i].jl.setEnabled(true);
			if(date.getMonthValue()!=first.getMonthValue()) {
				lbls[i].jl.setText("");
				lbls[i].jl.setEnabled(false);
			}
			else {
				lbls[i].jl.setEnabled(true);
				lbls[i].jl.setText(date.getDayOfMonth()+"");
			}
			if(dates!=null&&dates.contains(date)) {
				lbls[i].chek = true;
			}
			else {
				lbls[i].chek = false;
			}
		}
		label.setText(first.format(DateTimeFormatter.ofPattern("yyyy년 MM월")));
		label_1.setEnabled(!(now.getYear()==LocalDate.now().getYear()&&now.getMonthValue()==LocalDate.now().getMonthValue()));
	}
	LocalDate now = LocalDate.now();
	MyLabel[] lbls = new MyLabel[42];
	
	private void addlabel() {
		String[] week = "일 월 화 수 목 금 토".split(" ");
		for (int i = 0; i < 7; i++) {
			JLabel jl = new JLabel(week[i], 0);
			if(i==0) {
				jl.setForeground(Color.red);
			}
			else if(i==6) {
				jl.setForeground(Color.blue);
			}
			panel_1.add(jl);
		}
		for (int i = 0; i < 42; i++) {
			lbls[i] = new MyLabel();
			if(i%7==0) {
				lbls[i].jl.setForeground(Color.red);
			}
			else if(i%7==6) {
				lbls[i].jl.setForeground(Color.blue);
			}
			panel.add(lbls[i]);
		}
	}

	class MyLabel extends JLabel {
		LocalDate date;
		boolean chek, border;
		JLabel jl = new JLabel("", 0);

		public MyLabel() {
			setLayout(new BorderLayout());
			add(jl);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			if(chek&&jl.isEnabled()) {
				g2.setColor(new Color(255, 150, 150));
				g2.fillOval(0, 0, getWidth()-1, getHeight()-1);
			}
			if (jl.isEnabled()&&border) {
				g2.setColor(Color.red);
				g2.drawOval(0, 0, getWidth()-1, getHeight()-1);
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
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(label_1.isEnabled()) {
				now = now.plusMonths(-1);
				lendering();
			}
		}
	}
}
