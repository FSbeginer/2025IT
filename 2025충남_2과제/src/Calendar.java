import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class Calendar extends JPanel {
	public JPanel panel;
	public JPanel panel_1;
	public JPanel panel_2;
	public JLabel label;
	public JLabel label_2;
	public JLabel label_3;
	public JPanel WeekTxt;
	public JPanel dayPanel;
	MyLabel[] days = new MyLabel[42];
	List<LocalDate> list;
	LocalDate start, end;
	public Calendar(List<LocalDate> list) {
		this.list = list;
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		WeekTxt = new JPanel();
		WeekTxt.setBackground(Color.WHITE);
		panel_1.add(WeekTxt, BorderLayout.NORTH);
		WeekTxt.setLayout(new GridLayout(0, 7, 0, 0));

		dayPanel = new JPanel();
		dayPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		dayPanel.setBackground(Color.WHITE);
		panel_1.add(dayPanel, BorderLayout.CENTER);
		dayPanel.setLayout(new GridLayout(6, 7, 0, 0));

		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));

		label = new JLabel("    <");
		label.addMouseListener(new LabelMouseListener());
		label.setBackground(Color.WHITE);
		label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		panel_2.add(label, BorderLayout.WEST);

		label_2 = new JLabel("New label");
		label_2.setBackground(Color.WHITE);
		label_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_2, BorderLayout.CENTER);

		label_3 = new JLabel(">    ");
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setBackground(Color.WHITE);
		label_3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		panel_2.add(label_3, BorderLayout.EAST);

		addWeek();
		loadDate();
		label.setEnabled(now.getMonthValue()!=LocalDate.now().getMonthValue());
		label_3.setEnabled(now.getMonthValue()!=12);
	}

	LocalDate now = LocalDate.now();
	int min = now.getMonthValue();
	
	private void loadDate() {
		int start = now.getDayOfWeek().getValue() % 7;
		LocalDate date = LocalDate.of(now.getYear(), now.getMonth(), 1);
		for (int i = 0; i < days.length; i++) {
			LocalDate d = date.plusDays(i - start);
			days[i].setVisible(date.getMonthValue() == d.getMonthValue());
			int week = d.getDayOfWeek().getValue() % 7;
			days[i].date = d;
			days[i].jl.setText(d.getDayOfMonth()+"");
			days[i].jl.setEnabled(!d.isBefore(LocalDate.now()));
			days[i].chk = list!=null&&list.contains(d);
		}
		label_2.setText(now.format(DateTimeFormatter.ofPattern("yyyy³âMM¿ù")));
	}

	private void addWeek() {
		String[] txt = "ÀÏ,¿ù,È­,¼ö,¸ñ,±Ý,Åä".split(",");

		for (int i = 0; i < txt.length; i++) {
			JLabel jl = new JLabel(txt[i], 0);
			if (i == 0)
				jl.setForeground(Color.red);
			else if (i == 6)
				jl.setForeground(Color.blue);
			WeekTxt.add(jl);
		}

		for (int i = 0; i < 42; i++) {
			days[i] = new MyLabel();
			days[i].setVisible(false);
			if (i % 7 == 0)
				days[i].setForeground(Color.red);
			else if (i % 7 == 6)
				days[i].setForeground(Color.blue);

			dayPanel.add(days[i]);
		}
	}
	
	class MyLabel extends JLabel {
		boolean chk, border, ³ªµµ¸ð¸£°Ù´Ù;
		LocalDate date;
		JLabel jl = new JLabel("",0);
		
		public MyLabel() {
			setLayout(new BorderLayout());
			add(jl);
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(list!=null) {
						if(!jl.isEnabled()) return; 
						
						for (MyLabel myLabel : days) {
							myLabel.border = false;
						}
						border = true;
						dayPanel.repaint();
					}
					else {
						if(start == null) start = date;
						else end = date;
						
						
						if(end!=null)
							for (MyLabel myLabel : days) {
								if(myLabel.date.isBefore(start)||myLabel.date.isAfter(end)) myLabel.³ªµµ¸ð¸£°Ù´Ù = false;
								else myLabel.³ªµµ¸ð¸£°Ù´Ù = true;
							}
						else
							³ªµµ¸ð¸£°Ù´Ù = true;
						Calendar.this.repaint();
					}
				}
			});
		}

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setStroke(new BasicStroke(2));
			if (chk&&jl.isEnabled()&&isVisible()) {
				g2.setColor(new Color(255,150,150));
				g2.fillOval(5, 5, getWidth()-10, getHeight()-10);
			}
			if(border&&jl.isEnabled()&&isVisible()) {
				g2.setColor(Color.red);
				g2.drawOval(5, 5, getWidth()-10, getHeight()-10);
			}
			if(³ªµµ¸ð¸£°Ù´Ù) {
				g2.setColor(new Color(255,150,150));
				g2.fillOval(5, 5, getWidth()-10, getHeight()-10);
			}
			super.paintComponent(g);
		}
	}
	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(label_3.isEnabled()) {
				now = now.plusMonths(1);
				loadDate();
				int y = LocalDate.now().getYear()-now.getYear();
				label.setEnabled(!(y==0&&LocalDate.now().getMonthValue()==now.getMonthValue()));
			}
		}
	}
	private class LabelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(label.isEnabled()) {
				now = now.plusMonths(-1);
				loadDate();
				int y = LocalDate.now().getYear()-now.getYear();
				label.setEnabled(!(y==0&&LocalDate.now().getMonthValue()==now.getMonthValue()));
			}
		}
	}
}
