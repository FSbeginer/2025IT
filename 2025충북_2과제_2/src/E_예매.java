import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class E_예매 extends BF {
	public JPanel panel;
	public JLabel label;
	public JLabel label_1;
	public JScrollPane scrollPane;
	public JPanel panel_1;
	public JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					E_예매 frame = new E_예매(16);
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
		setBounds(100, 100, 765, 525);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 163, 493);
		getContentPane().add(panel);
		panel.setLayout(null);

		label = new JLabel("New label");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setBounds(0, 47, 163, 48);
		panel.add(label);

		label_1 = new JLabel("New label");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_1.setBounds(0, 0, 163, 48);
		panel.add(label_1);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 103, 163, 390);
		panel.add(scrollPane);

		panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(10, 1200));
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(new GridLayout(31, 1, 0, 5));

		button = new JButton("좌석조회");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(650, 460, 97, 23);
		getContentPane().add(button);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(164, 0, 585, 437);
		getContentPane().add(scrollPane_1);
		
		panel_2 = new JPanel();
		scrollPane_1.setViewportView(panel_2);
		panel_2.setLayout(null);

		addTimeLabel();
	}

	List<JPanel> pps = new ArrayList<JPanel>();
	public JScrollPane scrollPane_1;
	public JPanel panel_2;

	private void addTimeLabel() {
		var now = LocalDate.now();
		label.setText(now.getYear() + "년");
		label_1.setText(now.getMonthValue() + "월");

		LocalDate first = LocalDate.of(now.getYear(), now.getMonthValue(), 1);
		String[] week = "일,월,화,수,목,금,토".split(",");
		for (int i = 0; i < YearMonth.from(first).lengthOfMonth(); i++) {
			var date = first.plusDays(i);
			E_날짜패널 pp = new E_날짜패널(week[date.getDayOfWeek().getValue() % 7], date.getDayOfMonth());
			if (date.isBefore(now)) {
				pp.setBackground(Color.gray);
			} else {
				pps.add(pp);
			}
			pp.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (pps.contains(pp)) {
						for (var pp : pps) {
							pp.setBackground(Color.white);
						}
						pp.setBackground(Color.yellow);
						selDate(date);
					}
				}
			});
			panel_1.add(pp);
		}

	}
	
	List<JPanel> pps2 = new ArrayList<JPanel>();
	int srmno=-1,scno=-1;
	private void selDate(LocalDate date) {
		panel_2.removeAll();
		pps2.clear();
		srmno = -1;
		scno = -1;
		selDate = date;
		try (var rs = res("select *, count(length(r_setname)-length(replace(r_setname,',','')+1)) cnt from schedule sc left join reservation r on sc.m_no = r.m_no and sc_time = r_time and sc_date = r_date where sc_date = '2025-09-02' and sc.m_no = "+mno+" group by sc_time;")) {
			int i =0,w=0,h=0;
			while(rs.next()) {
				if(date.equals(LocalDate.now())&&LocalTime.now().isAfter(rs.getTime("sc_time").toLocalTime())) continue;
				
				E_스케줄 pp =new E_스케줄(rs.getString("srm_srmno")+"관 (총 81석)", rs.getString("sc_time")+" "+(81-rs.getInt("cnt"))+"석 남음");
				int left = 81-rs.getInt("cnt");
				int scno = rs.getInt("sc_no");
				int srmno = rs.getInt("srm_srmno");
				var time = rs.getTime("sc_time").toLocalTime();
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(left ==0) {
							msgErr("남은 자리가 없습니다.");
							return;
						}
						for (JPanel pp : pps2) {
							pp.setBackground(Color.white);
						}
						pp.setBackground(Color.yellow);
						E_예매.this.srmno= srmno;
						E_예매.this.scno= scno;
						selTime = time;
					}
				});
				pp.setLocation(0, (pp.getHeight()+10)*i);
				w= pp.getWidth();
				h=pp.getHeight();
				panel_2.add(pp);
				pps2.add(pp);
				i++;
			}
			panel_2.setPreferredSize(new Dimension(0,(h+10)*i));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel_2.revalidate();
		panel_2.repaint();
	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(scno==-1) {
				msgErr("스케줄을 선택해주세요.");
				return;
			}
			if(scno==-1) {
				msgErr("스케줄을 선택해주세요.");
				return;
			}
			msgInfo("좌석예매 폼으로 이동하겠습니다.");
			selScno =scno;
			showPage(new F_상영관배치도(srmno), "F_상영관배치도");
		}
	}
}
