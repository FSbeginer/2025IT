import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
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
	public JLabel label_4;
	public JButton button;
	public 의사정보(int hno) {
		setTitle("\uC758\uC0AC\uC815\uBCF4");
		this.dno = hno;
		setBounds(100, 100, 450, 404);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("");
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(12, 10, 410, 147);
		getContentPane().add(label);
		
		label_1 = new JLabel("New label");
		label_1.setBounds(12, 167, 316, 47);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("New label");
		label_2.setBounds(12, 224, 410, 21);
		getContentPane().add(label_2);
		
		label_3 = new JLabel("New label");
		label_3.setBounds(12, 255, 410, 21);
		getContentPane().add(label_3);
		
		label_4 = new JLabel("New label");
		label_4.setBounds(12, 286, 410, 21);
		getContentPane().add(label_4);
		
		button = new JButton("\uC608\uC57D\uD558\uAE30");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(71, 317, 280, 35);
		getContentPane().add(button);

		load();
	}
	List<Integer> workday = new ArrayList<Integer>();
	private void load() {
		try (var rs = res("select dno,hno,cno,d.name dname, h.name hname, c.name as cname, d.title,d.day from doctor d join hospital h using(hno) join category c using(cno) where dno =" +dno)) {
			rs.next();
			label.setIcon(getIcon("doctor/"+dno+".png",label.getWidth(),label.getHeight()));
			label_1.setText("<html> <font size = 6>"+rs.getString("dname")+" </font>"+rs.getString("cname")+" 전문의");
			label_2.setText("소속 : "+rs.getString("hname"));
			label_3.setText("소개글 : "+rs.getString("title"));
			label_4.setText("진료 날짜/시간 : "+rs.getString("day"));
			
			String[] week = "일,월,화,수,목,금,토".split(",");
			
			workday = Arrays.stream(rs.getString("day").replaceAll("\\s|오전|오전", "").split(",")).mapToInt(x->Arrays.asList(week).indexOf(x)).boxed().collect(Collectors.toList());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			showPage(new H_달력(workday,dno), "H_달력");
		}
	}
}
