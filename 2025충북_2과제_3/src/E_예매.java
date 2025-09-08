import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class E_예매 extends BF {
	public JPanel panel;
	public JLabel label;
	public JLabel label_1;
	public JScrollPane scrollPane;
	public JPanel panel_1;
	public JScrollPane scrollPane_1;
	public JButton button;
	public JPanel panel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					E_예매 frame = new E_예매(1);
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
	int mno;
	public E_예매(int mno) {
		this.mno = mno;
		setTitle("예매");
		setBounds(100, 100, 800, 511);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 195, 472);
		getContentPane().add(panel);
		panel.setLayout(null);

		label = new JLabel("New label");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 19));
		label.setBounds(0, 0, 195, 46);
		panel.add(label);

		label_1 = new JLabel("New label");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 19));
		label_1.setBounds(0, 56, 195, 46);
		panel.add(label_1);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 112, 195, 360);
		panel.add(scrollPane);

		panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(10, 1200));
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(new GridLayout(31, 1, 0, 3));

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(193, 0, 591, 429);
		getContentPane().add(scrollPane_1);

		panel_2 = new JPanel();
		scrollPane_1.setViewportView(panel_2);
		panel_2.setLayout(null);

		button = new JButton("좌석조회");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(687, 449, 97, 23);
		getContentPane().add(button);

		load();
	}

	List<JPanel> pps = new ArrayList<JPanel>();
	List<JPanel> pps2 = new ArrayList<JPanel>();

	private void load() {
		LocalDate now = LocalDate.now();
		label.setText(now.getYear() + "년");
		label_1.setText(now.getMonthValue() + "월");
		LocalDate first = LocalDate.of(now.getYear(), now.getMonthValue(), 1);
		String[] week = "일 월 화 수 목 금 토".split(" ");
		int weekv = first.getDayOfWeek().getValue() % 7;
		for (int i = 0; i < YearMonth.from(first).lengthOfMonth(); i++) {
			LocalDate date = first.plusDays(i - weekv);
			E_날짜패널 pp = new E_날짜패널(week[date.getDayOfWeek().getValue() % 7], date.getDayOfMonth() + "일");
			if (date.isBefore(now)) {
				pp.setBackground(Color.gray);
			} else {
				pp.setBackground(Color.white);
				pps.add(pp);
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						for (var pp : pps) {
							pp.setBackground(Color.white);
						}
						pp.setBackground(Color.yellow);
						loadDAta(date);
					}

				});
			}
			panel_1.add(pp);
		}
	}

	int selscno = -1;
	int left = 0;
	private void loadDAta(LocalDate date) {
		panel_2.removeAll();
		pps2.clear();
		selscno = -1;
		try (var rs = res("select *, count(length(r_setname)-length(replace(r_setname,',',''))+1) cnt from schedule sc left join reservation r  on sc_date = r_date and sc_time = r_time and sc.m_no = r.m_no where sc.m_no = "+mno+" and sc_date = '"+date+"' group by sc_time;")) {
			int w= scrollPane_1.getWidth()-20;
			int h = scrollPane_1.getHeight()/4, i =0;
			while(rs.next()) {
				E_스케쥴 pp =new E_스케쥴(rs.getInt("srm_srmno"), rs.getString("sc_time"), 81-rs.getInt("cnt"));
				pp.setSize(w, h);
				pp.setLocation(0, (h+5)*i);
				int scno = rs.getInt("sc_no");
				int l =81-rs.getInt("cnt");
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						for (var pp : pps2) {
							pp.setBackground(Color.white);
						}
						pp.setBackground(Color.yellow);
						selscno = scno;
						left = l;
					}
				});
				panel_2.add(pp);
				pps2.add(pp);
				i++;
			}
			panel_2.setPreferredSize(new Dimension(0,(h+5)*i));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel_2.revalidate();
		panel_2.repaint();
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(selscno==-1) {
				msgErr("스케줄을 선택해주세요");
				return;
			}
			if(left==0) {
				msgErr("남은 자리가 없습니다.");
				return;
			}
			msgInfo("좌석예매 폼으로 이동하겠습니다.");
			try {
				var rs =res("select srm_srmno from schedule where sc_no = "+selscno);
				rs.next();
				showPage(new F_상영관배치도(rs.getInt(1)),"F_상영관배치도");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
