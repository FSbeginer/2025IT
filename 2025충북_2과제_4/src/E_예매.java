import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
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
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class E_예매 extends BF {

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
	public JPanel panel;
	public JLabel label;
	public JLabel label_1;
	public JScrollPane scrollPane;
	public JPanel panel_1;
	public JScrollPane scrollPane_1;
	public JPanel panel_2;
	public JButton button;
	public E_예매(int mno) {
		setTitle("예매");
		this.mno = mno;
		setBounds(100, 100, 790, 462);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 195, 422);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		label = new JLabel("New label");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label.setBounds(0, 0, 195, 53);
		panel.add(label);
		
		label_1 = new JLabel("New label");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label_1.setBounds(0, 43, 195, 37);
		panel.add(label_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 90, 195, 332);
		panel.add(scrollPane);
		
		panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(10, 1200));
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(new GridLayout(31, 0, 0, 2));
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(196, 0, 578, 364);
		getContentPane().add(scrollPane_1);
		
		panel_2 = new JPanel();
		scrollPane_1.setViewportView(panel_2);
		panel_2.setLayout(null);
		
		button = new JButton("좌석조회");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(677, 399, 97, 23);
		getContentPane().add(button);

		load();
	}
	List<JPanel> pps = new ArrayList<JPanel>();
	List<JPanel> pps2 = new ArrayList<JPanel>();
	private void load() {
		label.setText(LocalDate.now().getYear()+"년");
		label_1.setText(LocalDate.now().getMonthValue()+"월");
		
		LocalDate now = LocalDate.now();
		LocalDate first = LocalDate.of(now.getYear(), now.getMonthValue(), 1);
		String[] week = "일 월 화 수 목 금 토".split(" ");
		for (int i = 0; i < YearMonth.from(first).lengthOfMonth(); i++) {
			var date = first.plusDays(i);
			E_패널 pp = new E_패널(week[date.getDayOfWeek().getValue()%7],date.getDayOfMonth());
			if(date.isBefore(now)) {
				pp.setBackground(Color.lightGray);
			}
			else {
				pp.setBackground(Color.white);
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						for (var pp : pps) {
							pp.setBackground(Color.white);
						}
						pp.setBackground(Color.yellow);
						selDate(date);
					}

				});
				pps.add(pp);
			}
			panel_1.add(pp);
		}
	}
	LocalDate seldate;
	int sno=-1;
	int srmno;
	private void selDate(LocalDate date) {
		seldate = date;
		sno = -1;
		panel_2.removeAll();
		pps2.clear();
		try (var rs = res("select *, count(length(r_setname)-length(replace(r_setname,',','')+1)) cnt from schedule sc left join reservation r on r.r_date = sc.sc_date and r.r_time = sc.sc_time and sc.m_no = r.m_no where sc.m_no = "+mno+" and sc_date = '"+date+"' group by sc_time;")) {
			int w = 549, h =101, i=0;
			while(rs.next()) {
				if(date.equals(LocalDate.now())) {
					var datetime =  rs.getTime("sc_time").toLocalTime();
					if(datetime.isBefore(LocalTime.now())) {
						continue;
					}
				}
				E_패널2 pp = new E_패널2(rs.getInt("srm_srmno"), rs.getString("sc_time"), rs.getInt("cnt"));
				pp.setLocation(0, (h+5)*i);
				int scno = rs.getInt("sc_no");
				int left = rs.getInt("cnt");
				int srmno = rs.getInt("srm_srmno");
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(left>=81) {
							msgErr("남은 자리가 없습니다.");
							return;
						}
						sno = scno;
						E_예매.this.srmno = srmno;
						for (var pp : pps2) {
							pp.setBackground(Color.white);
						}
						pp.setBackground(Color.yellow);
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
			if(sno==-1) {
				msgErr("스케줄을 선택해주세요.");
				return;
			}
			msgInfo("좌석예매 폼으로 이동하겠습니다.");
			showPage(new F_상영관배치도(srmno), "F_상영관배치도");
		}
	}
}
