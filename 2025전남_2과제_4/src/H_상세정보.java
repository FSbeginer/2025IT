import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class H_상세정보 extends BF {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					H_상세정보 frame = new H_상세정보(1);
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
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;
	public JLabel label_6;
	public JLabel label_7;
	public JLabel label_8;
	public JLabel label_9;
	public JLabel label_10;
	public JLabel label_11;
	public JPanel panel;
	public JPanel panel_1;
	public JPanel panel_2;
	public JLabel label_12;
	public JLabel label_13;
	public JLabel label_14;
	public JLabel label_15;
	public JLabel label_16;
	public JLabel label_17;
	public JLabel label_18;
	public JLabel label_19;
	public JLabel label_20;
	public JLabel label_21;
	public JLabel label_22;
	public H_상세정보(int cno) {
		setTitle("상세내용");
		this.cno = cno;
		setBounds(100, 100, 847, 501);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("<html><pre>누구나 탐내는<br>   자격증 <font color = orange>1</font>순위");
		label.setBounds(12, 10, 101, 39);
		getContentPane().add(label);
		
		label_1 = new JLabel("");
		label_1.setBounds(12, 59, 412, 255);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("자격증명");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setOpaque(true);
		label_2.setBackground(Color.BLUE);
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(436, 59, 125, 39);
		getContentPane().add(label_2);
		
		label_3 = new JLabel("담당교수");
		label_3.setOpaque(true);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(Color.WHITE);
		label_3.setBackground(Color.BLUE);
		label_3.setBounds(436, 108, 125, 39);
		getContentPane().add(label_3);
		
		label_4 = new JLabel("강의기간");
		label_4.setOpaque(true);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Color.WHITE);
		label_4.setBackground(Color.BLUE);
		label_4.setBounds(436, 157, 125, 39);
		getContentPane().add(label_4);
		
		label_5 = new JLabel("수업방식");
		label_5.setOpaque(true);
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setForeground(Color.WHITE);
		label_5.setBackground(Color.BLUE);
		label_5.setBounds(436, 206, 125, 39);
		getContentPane().add(label_5);
		
		label_6 = new JLabel("시험방식");
		label_6.setOpaque(true);
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setForeground(Color.WHITE);
		label_6.setBackground(Color.BLUE);
		label_6.setBounds(436, 255, 125, 39);
		getContentPane().add(label_6);
		
		label_7 = new JLabel("New label");
		label_7.setBounds(573, 59, 222, 39);
		getContentPane().add(label_7);
		
		label_8 = new JLabel("New label");
		label_8.setBounds(573, 108, 222, 39);
		getContentPane().add(label_8);
		
		label_9 = new JLabel("New label");
		label_9.setBounds(573, 157, 222, 39);
		getContentPane().add(label_9);
		
		label_10 = new JLabel("New label");
		label_10.setBounds(573, 206, 222, 39);
		getContentPane().add(label_10);
		
		label_11 = new JLabel("New label");
		label_11.setBounds(573, 255, 222, 39);
		getContentPane().add(label_11);
		
		panel = new JPanel();
		panel.setBounds(0, 339, 831, 123);
		getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(120,150,255).brighter());
		panel.add(panel_1, "name_11289062277800");
		panel_1.setLayout(null);
		
		label_12 = new JLabel("특별할인");
		label_12.setForeground(Color.WHITE);
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setBounds(12, 10, 96, 20);
		panel_1.add(label_12);
		
		label_13 = new JLabel(getIcon("icon/cc.png",96,34));
		label_13.setBounds(12, 40, 96, 34);
		panel_1.add(label_13);
		
		label_14 = new JLabel(getIcon("time.png",34,34));
		label_14.setBounds(12, 79, 34, 34);
		panel_1.add(label_14);
		
		label_15 = new JLabel("New label");
		label_15.setForeground(Color.WHITE);
		label_15.setBounds(58, 84, 57, 29);
		panel_1.add(label_15);
		
		label_16 = new JLabel("0");
		label_16.setOpaque(true);
		label_16.setBackground(Color.WHITE);
		label_16.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label_16.setHorizontalAlignment(SwingConstants.CENTER);
		label_16.setBounds(147, 28, 78, 69);
		panel_1.add(label_16);
		
		label_17 = new JLabel("0");
		label_17.setOpaque(true);
		label_17.setHorizontalAlignment(SwingConstants.CENTER);
		label_17.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label_17.setBackground(Color.WHITE);
		label_17.setBounds(237, 28, 78, 69);
		panel_1.add(label_17);
		
		label_18 = new JLabel("0");
		label_18.setOpaque(true);
		label_18.setHorizontalAlignment(SwingConstants.CENTER);
		label_18.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label_18.setBackground(Color.WHITE);
		label_18.setBounds(351, 28, 78, 69);
		panel_1.add(label_18);
		
		label_19 = new JLabel("0");
		label_19.setOpaque(true);
		label_19.setHorizontalAlignment(SwingConstants.CENTER);
		label_19.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label_19.setBackground(Color.WHITE);
		label_19.setBounds(441, 28, 78, 69);
		panel_1.add(label_19);
		
		label_20 = new JLabel("0");
		label_20.setOpaque(true);
		label_20.setHorizontalAlignment(SwingConstants.CENTER);
		label_20.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label_20.setBackground(Color.WHITE);
		label_20.setBounds(546, 28, 78, 69);
		panel_1.add(label_20);
		
		label_21 = new JLabel("0");
		label_21.setOpaque(true);
		label_21.setHorizontalAlignment(SwingConstants.CENTER);
		label_21.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label_21.setBackground(Color.WHITE);
		label_21.setBounds(636, 28, 78, 69);
		panel_1.add(label_21);
		
		label_22 = new JLabel("수강신청");
		label_22.addMouseListener(new Label_22MouseListener());
		label_22.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_22.setOpaque(true);
		label_22.setHorizontalAlignment(SwingConstants.CENTER);
		label_22.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		label_22.setBackground(Color.WHITE);
		label_22.setBounds(741, 28, 78, 69);
		panel_1.add(label_22);
		
		panel_2 = new JPanel();
		panel.add(panel_2, "name_11290641147600");
		
		try (var rs = res("select * from certi left join sale using(cno) join teacher using(tno) where cno = "+cno)) {
			if(rs.next()&&rs.getString("sno")!=null) {
				var end = rs.getTimestamp("sale_end_date").toLocalDateTime();
				new Timer(1000, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Duration d = Duration.between(LocalDateTime.now(), end);
						label_15.setText(String.format("%02d일", d.toDays()));
						label_16.setText(String.format("%d", d.toHours()%24/10));
						label_17.setText(String.format("%d", d.toHours()%10));
						label_18.setText(String.format("%d", d.toMinutes()%60/10));
						label_19.setText(String.format("%d", d.toMinutes()%60%10));
						label_20.setText(String.format("%d", d.toSeconds()%60/10));
						label_21.setText(String.format("%d", d.toSeconds()%60%10));
					}
				}).start();
				Duration d = Duration.between(LocalDateTime.now(), end);
				label_15.setText(String.format("%02d일", d.toDays()));
				label_16.setText(String.format("%d", d.toHours()%24/10));
				label_17.setText(String.format("%d", d.toHours()%10));
				label_18.setText(String.format("%d", d.toMinutes()%60/10));
				label_19.setText(String.format("%d", d.toMinutes()%60%10));
				label_20.setText(String.format("%d", d.toSeconds()%60/10));
				label_21.setText(String.format("%d", d.toSeconds()%60%10));
			}
			else {
				((CardLayout)panel.getLayout()).last(panel);
			}
			label_1.setIcon(getIcon("certification/"+cno+".png",label_1.getWidth(),label_1.getHeight()));
			label_7.setText(rs.getString("cname")+" "+rs.getInt("ratring")+"급");
			label_8.setText(rs.getString("tname")+" 교수");
			label_9.setText(rs.getString("type1")+"(28일간)");
			label_10.setText(rs.getString("type1"));
			label_11.setText(rs.getString("days"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private class Label_22MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new F_결제(),"F_결제");
		}
	}
}
