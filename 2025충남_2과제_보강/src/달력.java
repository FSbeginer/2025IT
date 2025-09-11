import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.border.LineBorder;
import java.awt.Color;

public class ´Þ·Â extends JPanel {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JPanel panel;
	public JPanel panel_1;

	/**
	 * Create the panel.
	 * @param dates 
	 */
	public ´Þ·Â(List<LocalDate> dates) {
		this.dates = dates;
		setSize( 371, 398);
		setLayout(null);
		
		label = new JLabel("New label");
		label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 371, 58);
		add(label);
		
		label_1 = new JLabel("<");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 21));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(34, 10, 44, 36);
		add(label_1);
		
		label_2 = new JLabel(">");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 21));
		label_2.setBounds(299, 10, 44, 36);
		add(label_2);
		
		panel = new JPanel();
		panel.setBounds(0, 53, 371, 50);
		add(panel);
		panel.setLayout(new GridLayout(0, 7, 0, 0));
		
		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(0, 103, 371, 285);
		add(panel_1);
		panel_1.setLayout(new GridLayout(6, 7, 0, 0));
		addlabel();
		lendering();
	}

	List<LocalDate> dates;
	LocalDate selDate,d1,d2;
	
	public void lendering() {
		LocalDate first = LocalDate.of(now.getYear(), now.getMonth(), 1);
		int week  = first.getDayOfWeek().getValue() % 7;
		label.setText(first.format(DateTimeFormatter.ofPattern("yyyy³âMM¿ù")));
		for (int i = 0; i < jls.length; i++) {
			LocalDate date = first.plusDays(i-week);
			jls[i].setText(date.getDayOfMonth()+"");
			jls[i].setVisible(date.getMonthValue()==first.getMonthValue());
			jls[i].setEnabled(!date.isBefore(LocalDate.now()));
			jls[i].date = date;
			if(dates!=null)
				jls[i].check = dates.contains(date);
			else if(d1!=null) {
				var imsi = d2;
				if(d2==null) imsi = d1;
				jls[i].check = !date.isBefore(d1)&&!date.isAfter(imsi);
			}
			jls[i].border = date.equals(selDate);
				
		}
		label_1.setEnabled(!now.plusMonths(-1).isBefore(LocalDate.now()));
		repaint();
	}

	private void addlabel() {
		String[] week = "ÀÏ ¿ù È­ ¼ö ¸ñ ±Ý Åä".split(" ");
		for (int i = 0; i < 7; i++) {
			JLabel jl = new JLabel();
			if(i%7==0)
				jl.setForeground(Color.red);
			else if(i%7==6)
				jl.setForeground(Color.blue);
			jl.setText(week[i]);
			jl.setHorizontalAlignment(0);
			panel.add(jl);
		}
		for (int i = 0; i < 42; i++) {
			jls[i] = new MyLabel();
			if(i%7==0)
				jls[i].setForeground(Color.red);
			else if(i%7==6)
				jls[i].setForeground(Color.blue);
			jls[i].setHorizontalAlignment(0);
			panel_1.add(jls[i]);
		}
	}

	LocalDate now = LocalDate.now();
	MyLabel[] jls = new MyLabel[42];
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
class MyLabel extends JLabel {
	boolean border, check;
	LocalDate date;
	@Override
	protected void paintComponent(Graphics g) {
		if(isEnabled()&&border) {
			g.setColor(Color.red);
			g.drawOval(getWidth()/2-15, getHeight()/2-15, 30, 30);
		}
		if(isEnabled()&&check) {
			g.setColor(new Color(255,120,120));			
			g.fillOval(getWidth()/2-15, getHeight()/2-15, 30, 30);
		}
		super.paintComponent(g);
	}
}