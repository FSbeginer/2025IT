import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import javax.swing.JSlider;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class E_시험스케줄 extends BF {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					E_시험스케줄 frame = new E_시험스케줄(1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	int scno;
	public JLabel label;
	public JLabel label_1;
	public JSlider slider;
	public JLabel label_2;
	public JLabel label_3;
	public JPanel panel;
	public JPanel panel_1;
	public JLabel label_4;
	public JLabel label_5;
	public JLabel label_6;
	public JLabel label_7;
	private LocalDate date;
	public JLabel label_8;

	public E_시험스케줄(int scno) {
		setTitle("시험 스케줄");
		this.scno = scno;
		setBounds(100, 100, 509, 303);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		label = new JLabel("New label");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		label.setBounds(12, 10, 233, 56);
		getContentPane().add(label);

		label_1 = new JLabel("New label");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(211, 76, 100, 23);
		getContentPane().add(label_1);

		slider = new JSlider();
		slider.addMouseListener(new SliderMouseListener());
		slider.addChangeListener(new SliderChangeListener());
		slider.setBackground(Color.WHITE);
		slider.setBounds(12, 145, 469, 26);
		getContentPane().add(slider);

		label_2 = new JLabel("1");
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label_2.setBounds(12, 120, 57, 15);
		getContentPane().add(label_2);

		label_3 = new JLabel("30");
		label_3.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(424, 120, 57, 15);
		getContentPane().add(label_3);

		panel = new JPanel();
		panel.setBounds(12, 210, 469, 44);
		getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));

		panel_1 = new JPanel();
		panel.add(panel_1, "name_22350754186100");
		panel_1.setLayout(null);

		label_4 = new JLabel();
		label_4.addMouseListener(new Label_4MouseListener());
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_4.setBounds(43, 10, 89, 26);
		panel_1.add(label_4);

		label_5 = new JLabel();
		label_5.addMouseListener(new Label_5MouseListener());
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_5.setBounds(178, 10, 89, 26);
		panel_1.add(label_5);

		label_6 = new JLabel();
		label_6.addMouseListener(new Label_6MouseListener());
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_6.setBounds(312, 10, 89, 26);
		panel_1.add(label_6);

		label_7 = new JLabel("시험이 존재하지 않습니다.");
		label_7.setForeground(Color.GRAY);
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_7, "name_22433451661100");

		label_8 = new JLabel("");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setBounds(237, 181, 25, 15);
		getContentPane().add(label_8);

		load();
	}

	private void load() {
		try (var rs = res("select * from schedule join certi using(cno) where scno = " + scno)) {
			rs.next();
			date = rs.getDate("exam_date").toLocalDate();
			label.setText(rs.getString("cname") + " " + rs.getInt("ratring") + "급");
			label_1.setText(rs.getDate("exam_date").toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM")));
			label_3.setText(YearMonth.from(date).lengthOfMonth() + "");
			String[] stime = rs.getString("stime").split(",");
			label_4.setText(stime[0]);
			label_5.setText(stime[1]);
			label_6.setText(stime[2]);
			int day = getday();

			if (day < date.getDayOfMonth() || day > date.getDayOfMonth() + 7) {
				((CardLayout) panel.getLayout()).last(panel);
			} else
				((CardLayout) panel.getLayout()).first(panel);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class SliderChangeListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			int day = getday();

			if (day < date.getDayOfMonth() || day > date.getDayOfMonth() + 7) {
				((CardLayout) panel.getLayout()).last(panel);
			} else
				((CardLayout) panel.getLayout()).first(panel);

			label_8.setText(day + "");
			int x = (int) ((double) slider.getValue() / slider.getMaximum() * slider.getWidth());
			int y = slider.getY() + 30;
			label_8.setLocation(x, y);
		}

	}

	private int getday() {
		int day = Integer.parseInt(label_3.getText()) - 1;
		int currnt = (int) ((double) slider.getValue() / slider.getMaximum() * day) + 1;
		return currnt;
	}

	private class SliderMouseListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			int day = getday();
			label_8.setText(day + "");
			label_8.setBorder(new LineBorder(Color.black));
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			label_8.setText("");
			label_8.setBorder(null);
		}
	}

	private class Label_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			var seldate = date.plusDays(getday()-1);
			showPage(new F_결제하기(scno, seldate, LocalTime.parse(label_4.getText())), "F_결제하기");
		}
	}
	private class Label_5MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			var seldate = date.plusDays(getday()-1);
			showPage(new F_결제하기(scno, seldate, LocalTime.parse(label_5.getText())), "F_결제하기");
		}
	}
	private class Label_6MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			var seldate = date.plusDays(getday()-1);
			showPage(new F_결제하기(scno, seldate, LocalTime.parse(label_6.getText())), "F_결제하기");
		}
	}
}
