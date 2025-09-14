import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class 의사정보 extends BF {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					의사정보 frame = new 의사정보(1);
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
	int dno;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JButton button;
	public 의사정보(int dno) {
		setTitle("\uC758\uC0AC\uC815\uBCF4");
		this.dno = dno;
		setBounds(100, 100, 559, 465);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("");
		label.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label.setBounds(12, 10, 519, 197);
		getContentPane().add(label);
		
		label_1 = new JLabel("New label");
		label_1.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_1.setBounds(12, 217, 425, 38);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("New label");
		label_2.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_2.setBounds(12, 265, 268, 23);
		getContentPane().add(label_2);
		
		label_3 = new JLabel("New label");
		label_3.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_3.setBounds(12, 298, 519, 23);
		getContentPane().add(label_3);
		
		label_4 = new JLabel("New label");
		label_4.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_4.setBounds(12, 331, 519, 23);
		getContentPane().add(label_4);
		
		button = new JButton("\uC608\uC57D\uD558\uAE30");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(118, 376, 290, 40);
		getContentPane().add(button);
		
		load();

	}
	List<Integer> workday = new ArrayList<Integer>();
	private void load() {
		try (var rs = res("select d.name dn, c.name cn,h.name hn, title, day from doctor d join hospital h using(hno) join category c using(cno) where dno = "+dno)) {
			if(rs.next()) {
				label.setIcon(getIcon("doctor/"+dno+".png",label.getWidth(),label.getHeight()));
				label_1.setText("<html><font size = 6><b>"+rs.getString(1)+" 의사<b></font> "+rs.getString(2)+" 전문의");
				label_2.setText("소속 : "+rs.getString(3));
				label_3.setText("소개글 : "+rs.getString("title"));
				label_4.setText("진료 날짜/시간 : "+rs.getString("day"));
				String[] week = "일 월 화 수 목 금 토".split(" ");
				for (String s: rs.getString("day").replaceAll("\\s|오전|오후", "").split(",")) {
					workday.add(Arrays.asList(week).indexOf(s));
				}
				System.out.println(workday);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			showPage(new H_딜력(dno,workday), "H_딜력");
		}
	}
}
