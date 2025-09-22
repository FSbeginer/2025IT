import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class H_자격증상세내용 extends BF {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					H_자격증상세내용 frame = new H_자격증상세내용(1);
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
	public JPanel panel;
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
	public JLabel label_12;
	public JLabel label_13;
	public JLabel label_14;
	public JLabel label_15;
	public JButton button;
	public H_자격증상세내용(int cno) {
		setTitle("상세내용");
		this.cno = cno;
		setBounds(100, 100, 837, 517);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("<html><pre>누구나 탐내는<br>   자격증<font color = orange>1</font>순위");
		label.setBounds(12, 10, 128, 54);
		getContentPane().add(label);
		
		label_1 = new JLabel("");
		label_1.setBounds(12, 74, 426, 280);
		getContentPane().add(label_1);
		
		panel = new JPanel();
		panel.setBounds(0, 364, 821, 114);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		label_12 = new JLabel("");
		label_12.setBounds(12, 10, 100, 94);
		panel.add(label_12);
		
		label_13 = new JLabel("New label");
		label_13.setBounds(123, 10, 248, 21);
		panel.add(label_13);
		
		label_14 = new JLabel("New label");
		label_14.setBounds(124, 41, 546, 63);
		panel.add(label_14);
		
		label_15 = new JLabel("New label");
		label_15.setBounds(685, 10, 124, 21);
		panel.add(label_15);
		
		button = new JButton("수강신청");
		button.addActionListener(new ButtonActionListener());
		button.setForeground(Color.WHITE);
		button.setBackground(Color.BLUE);
		button.setBounds(682, 41, 127, 51);
		panel.add(button);
		
		label_2 = new JLabel("자격증명");
		label_2.setOpaque(true);
		label_2.setFont(new Font("굴림", Font.PLAIN, 16));
		label_2.setForeground(Color.WHITE);
		label_2.setBackground(Color.BLUE);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(450, 74, 121, 29);
		getContentPane().add(label_2);
		
		label_3 = new JLabel("담당교수");
		label_3.setOpaque(true);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("굴림", Font.PLAIN, 16));
		label_3.setBackground(Color.BLUE);
		label_3.setBounds(450, 130, 121, 29);
		getContentPane().add(label_3);
		
		label_4 = new JLabel("강의기간");
		label_4.setOpaque(true);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("굴림", Font.PLAIN, 16));
		label_4.setBackground(Color.BLUE);
		label_4.setBounds(450, 183, 121, 29);
		getContentPane().add(label_4);
		
		label_5 = new JLabel("수업방식");
		label_5.setOpaque(true);
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("굴림", Font.PLAIN, 16));
		label_5.setBackground(Color.BLUE);
		label_5.setBounds(450, 239, 121, 29);
		getContentPane().add(label_5);
		
		label_6 = new JLabel("시험방식");
		label_6.setOpaque(true);
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setForeground(Color.WHITE);
		label_6.setFont(new Font("굴림", Font.PLAIN, 16));
		label_6.setBackground(Color.BLUE);
		label_6.setBounds(450, 295, 121, 29);
		getContentPane().add(label_6);
		
		label_7 = new JLabel("New label");
		label_7.setBounds(594, 74, 215, 29);
		getContentPane().add(label_7);
		
		label_8 = new JLabel("New label");
		label_8.setBounds(594, 130, 215, 29);
		getContentPane().add(label_8);
		
		label_9 = new JLabel("New label");
		label_9.setBounds(594, 183, 215, 29);
		getContentPane().add(label_9);
		
		label_10 = new JLabel("New label");
		label_10.setBounds(594, 239, 215, 29);
		getContentPane().add(label_10);
		
		label_11 = new JLabel("New label");
		label_11.setBounds(594, 295, 215, 29);
		getContentPane().add(label_11);
		
		load();
	}
	private void load() {
		try (var rs = res("select * from certi join teacher using(tno) where cno = "+cno)) {
			rs.next();
			label_1.setIcon(getIcon("certification",label_1.getWidth(), label_1.getHeight()));
			label_7.setText(rs.getString("cname")+" "+rs.getInt("ratring")+"급");
			label_8.setText(rs.getString("tname")+" 교수");
			label_9.setText(rs.getString("type1")+"(28일간)");
			label_10.setText(rs.getString("type1"));
			label_11.setText(rs.getString("days"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			showPage(new F_결제하기(cno), "F_결제하기");
		}
	}
}
