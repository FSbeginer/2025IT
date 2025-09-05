import java.awt.EventQueue;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class H_상세내용 extends BF {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					H_상세내용 frame = new H_상세내용(11);
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
	int cno;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JPanel panel;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;
	public JLabel label_6;
	public JLabel label_7;
	public JLabel label_8;
	public JLabel label_9;
	public JLabel label_10;
	public JLabel label_11;
	public JLabel label_12;
	public JPanel panel_1;
	public JPanel panel_2;
	public JLabel label_13;
	public JLabel label_14;
	public JLabel label_15;
	public JLabel label_16;
	public JButton button;
	public JLabel label_17;
	public JLabel label_18;
	public JLabel label_19;
	public JLabel label_20;
	public JLabel label_21;
	public JLabel label_22;
	public JLabel label_23;
	public JLabel label_24;
	public JLabel label_25;
	public JLabel label_26;
	public JLabel label_27;
	public JLabel label_28;
	public JLabel label_29;

	public H_상세내용(int cno) {
		setTitle("상세내용");
		this.cno = cno;
		setBounds(100, 100, 853, 476);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		label = new JLabel("누구나 탐내는");
		label.setBounds(12, 10, 121, 20);
		getContentPane().add(label);

		label_1 = new JLabel("<html>자격증<font color = orange>1</font>순위");
		label_1.setBounds(40, 40, 121, 20);
		getContentPane().add(label_1);

		label_2 = new JLabel("");
		label_2.setBounds(12, 82, 397, 224);
		getContentPane().add(label_2);

		panel = new JPanel();
		panel.setBounds(0, 316, 837, 121);
		getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 255));
		panel.add(panel_1, "name_48388276397300");
		panel_1.setLayout(null);

		label_17 = new JLabel("특별할인");
		label_17.setForeground(new Color(255, 255, 255));
		label_17.setHorizontalAlignment(SwingConstants.CENTER);
		label_17.setBounds(12, 10, 113, 21);
		panel_1.add(label_17);

		label_18 = new JLabel(getIcon("icon/cc.png", 110, 20));
		label_18.setBounds(12, 41, 113, 21);
		panel_1.add(label_18);

		label_19 = new JLabel(getIcon("icon/time.png", 30, 30));
		label_19.setBounds(12, 73, 32, 38);
		panel_1.add(label_19);

		label_20 = new JLabel("New label");
		label_20.setForeground(new Color(255, 255, 255));
		label_20.setBounds(56, 83, 84, 28);
		panel_1.add(label_20);

		label_21 = new JLabel("0");
		label_21.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_21.setHorizontalAlignment(SwingConstants.CENTER);
		label_21.setBackground(new Color(255, 255, 255));
		label_21.setOpaque(true);
		label_21.setBounds(163, 41, 73, 57);
		panel_1.add(label_21);

		label_22 = new JLabel("New label");
		label_22.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_22.setOpaque(true);
		label_22.setHorizontalAlignment(SwingConstants.CENTER);
		label_22.setBackground(Color.WHITE);
		label_22.setBounds(248, 41, 73, 57);
		panel_1.add(label_22);

		label_23 = new JLabel("New label");
		label_23.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_23.setOpaque(true);
		label_23.setHorizontalAlignment(SwingConstants.CENTER);
		label_23.setBackground(Color.WHITE);
		label_23.setBounds(343, 41, 73, 57);
		panel_1.add(label_23);

		label_24 = new JLabel("New label");
		label_24.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_24.setOpaque(true);
		label_24.setHorizontalAlignment(SwingConstants.CENTER);
		label_24.setBackground(Color.WHITE);
		label_24.setBounds(429, 41, 73, 57);
		panel_1.add(label_24);

		label_25 = new JLabel("New label");
		label_25.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_25.setOpaque(true);
		label_25.setHorizontalAlignment(SwingConstants.CENTER);
		label_25.setBackground(Color.WHITE);
		label_25.setBounds(521, 41, 73, 57);
		panel_1.add(label_25);

		label_26 = new JLabel("New label");
		label_26.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_26.setOpaque(true);
		label_26.setHorizontalAlignment(SwingConstants.CENTER);
		label_26.setBackground(Color.WHITE);
		label_26.setBounds(606, 41, 73, 57);
		panel_1.add(label_26);

		label_27 = new JLabel("수강신청");
		label_27.addMouseListener(new Label_27MouseListener());
		label_27.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_27.setOpaque(true);
		label_27.setHorizontalAlignment(SwingConstants.CENTER);
		label_27.setBackground(Color.WHITE);
		label_27.setBounds(721, 41, 73, 57);
		panel_1.add(label_27);

		label_28 = new JLabel(":");
		label_28.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_28.setForeground(new Color(255, 255, 255));
		label_28.setBounds(329, 50, 27, 45);
		panel_1.add(label_28);

		label_29 = new JLabel(":");
		label_29.setForeground(Color.WHITE);
		label_29.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_29.setBounds(508, 50, 27, 45);
		panel_1.add(label_29);

		panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(panel_2, "name_48392715464200");
		panel_2.setLayout(null);

		label_13 = new JLabel("");
		label_13.setBounds(12, 10, 117, 101);
		panel_2.add(label_13);

		label_14 = new JLabel("New label");
		label_14.setBounds(141, 10, 206, 26);
		panel_2.add(label_14);

		label_15 = new JLabel("New label");
		label_15.setBounds(137, 46, 550, 20);
		panel_2.add(label_15);

		label_16 = new JLabel("New label");
		label_16.setBounds(668, 10, 137, 15);
		panel_2.add(label_16);

		button = new JButton("수강신청");
		button.setBackground(Color.BLUE);
		button.setForeground(Color.WHITE);
		button.setBounds(708, 49, 117, 48);
		panel_2.add(button);
		
		label_30 = new JLabel("New label");
		label_30.setBounds(137, 76, 550, 35);
		panel_2.add(label_30);

		label_3 = new JLabel("자격증명");
		label_3.setForeground(Color.WHITE);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBackground(Color.BLUE);
		label_3.setOpaque(true);
		label_3.setBounds(421, 82, 121, 27);
		getContentPane().add(label_3);

		label_4 = new JLabel("담당교수");
		label_4.setOpaque(true);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Color.WHITE);
		label_4.setBackground(Color.BLUE);
		label_4.setBounds(421, 131, 121, 27);
		getContentPane().add(label_4);

		label_5 = new JLabel("강의기간");
		label_5.setOpaque(true);
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setForeground(Color.WHITE);
		label_5.setBackground(Color.BLUE);
		label_5.setBounds(421, 178, 121, 27);
		getContentPane().add(label_5);

		label_6 = new JLabel("수업방식");
		label_6.setOpaque(true);
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setForeground(Color.WHITE);
		label_6.setBackground(Color.BLUE);
		label_6.setBounds(421, 225, 121, 27);
		getContentPane().add(label_6);

		label_7 = new JLabel("시험방식");
		label_7.setOpaque(true);
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setForeground(Color.WHITE);
		label_7.setBackground(Color.BLUE);
		label_7.setBounds(421, 273, 121, 27);
		getContentPane().add(label_7);

		label_8 = new JLabel("New label");
		label_8.setBounds(553, 84, 242, 25);
		getContentPane().add(label_8);

		label_9 = new JLabel("New label");
		label_9.setBounds(553, 131, 242, 25);
		getContentPane().add(label_9);

		label_10 = new JLabel("New label");
		label_10.setBounds(553, 178, 242, 25);
		getContentPane().add(label_10);

		label_11 = new JLabel("New label");
		label_11.setBounds(553, 225, 242, 25);
		getContentPane().add(label_11);

		label_12 = new JLabel("New label");
		label_12.setBounds(553, 273, 242, 25);
		getContentPane().add(label_12);

		load();

	}

	LocalDateTime end;
	public JLabel label_30;

	private void load() {
		try (var rs = res("select * from certi join teacher using(tno) where cno = " + cno)) {
			if (rs.next()) {
				label_8.setText(rs.getString("cname") + " " + rs.getInt("ratring") + "급");
				label_9.setText(rs.getString("tname") + " 교수");
				label_10.setText(rs.getString("type1") + "(28일간)");
				label_11.setText(rs.getString("type1"));
				label_12.setText(rs.getString("days"));
				label_2.setIcon(getIcon("certification/" + cno + ".png", label_2.getWidth(), label_2.getHeight()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = res("select * from sale where cno = " + cno)) {
			if (rs.next()) {
				((CardLayout) panel.getLayout()).first(panel);
				end = rs.getTimestamp("sale_end_date").toLocalDateTime();
				new Thread(new Runnable() {
					@Override
					public void run() {
						while (true) {

							Duration d = Duration.between(LocalDateTime.now(), end);
							label_20.setText(String.format("%02d일", d.toDays()));
							
							long sec =d.getSeconds();
							
							label_21.setText((d.toHours()%24)/10+"");
							label_22.setText((d.toHours()%24)%10+"");
							label_23.setText((d.toMinutes()%60)/10+"");
							label_24.setText((d.toMinutes()%60)%10+"");
							label_25.setText((d.toSeconds()%60)/10+"");
							label_26.setText((d.toSeconds()%60)%10+"");
							
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}).start();
			} else {
				((CardLayout) panel.getLayout()).last(panel);
				load2();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void load2() {
		new Thread(new Runnable() {
			
			String[] path = "certi,certi2,certi3,certi4".split(",");
			@Override
			public void run() {
				int i = 0,max=0,idx=0;;
				while(true) {
					label_13.setIcon(getIcon("review/"+path[i]+".jpg",label_13.getWidth(),label_13.getHeight()));
					try {
						var rs = res("select *,count(*) over() max from review join user using(uno) where cno = "+cno+" limit "+idx+",1");
						if(rs.next()) {
							label_14.setText("작성자: "+rs.getString("uname"));
							label_15.setText(""+rs.getString("rtitle"));
							label_30.setText("<html>"+rs.getString("rcontent"));
							label_16.setText("날짜: "+rs.getString("rdate"));
							max = rs.getInt("max");
						}
						else {
							label_14.setText("작성자: ");
							label_15.setText("리뷰가 없습니다.");
							label_30.setText("<html>");
							label_16.setText("날짜: ");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if(max!=0)
						idx = ++idx%max;
					i = ++i % 4;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
	}

	private class Label_27MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new F_결제(cno,-1),"F_결제");
		}
	}
}
