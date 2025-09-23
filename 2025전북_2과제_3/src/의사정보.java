import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
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
	public JButton button;
	public JLabel label_4;

	public 의사정보(int dno) {
		this.dno = dno;
		setTitle("\uC758\uC0AC\uC815\uBCF4");
		setBounds(100, 100, 500, 452);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		label = new JLabel("");
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(12, 10, 460, 172);
		getContentPane().add(label);

		label_1 = new JLabel("New label");
		label_1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_1.setBounds(12, 192, 313, 48);
		getContentPane().add(label_1);

		label_2 = new JLabel("New label");
		label_2.setBounds(12, 250, 372, 29);
		getContentPane().add(label_2);

		label_3 = new JLabel("New label");
		label_3.setBounds(12, 289, 447, 29);
		getContentPane().add(label_3);

		button = new JButton("\uC608\uC57D\uD558\uAE30");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(76, 369, 318, 34);
		getContentPane().add(button);
		
		label_4 = new JLabel("\uC18C\uAC1C\uAE00 : <dynamic>");
		label_4.setBounds(12, 328, 447, 26);
		getContentPane().add(label_4);

		load();
	}

	List<Integer> work = new ArrayList<Integer>(); 
	private void load() {
		try (var rs = res("select dno, d.name dn, c.name cn, h.name hn, title, day from doctor d join hospital h using(hno) join category c using(cno) where dno =" + dno)) {
			rs.next();
			label.setIcon(getIcon("doctor/" + dno + ".png", label.getWidth(), label.getHeight()));
			label_1.setText("<html><font size = 6><b>"+rs.getString("dn")+" 의사</b></font> "+rs.getString("cn")+" 전문의");
			label_2.setText("소속 : "+rs.getString("hn"));
			label_3.setText("소개글 : "+rs.getString("title"));
			label_4.setText("진료 날짜/시간 : "+rs.getString("day"));
			String[] week = "일 월 화 수 목 금 토".split(" ");
			var day  = rs.getString("day").replaceAll("오전|오후|\\s", "").split(",");
			for (String string : day) {
				work.add(Arrays.asList(week).indexOf(string));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			showPage(new H_달력(work,dno),"H_달력");
		}
	}
}
