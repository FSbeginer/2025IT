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
import java.util.stream.Collectors;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class G_의사정보 extends BF {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					G_의사정보 frame = new G_의사정보(1);
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
	public JLabel label_4;
	public G_의사정보(int dno) {
		this.dno = dno;
		setTitle("\uC758\uC0AC\uC815\uBCF4");
		setBounds(100, 100, 570, 471);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("");
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(12, 10, 530, 180);
		getContentPane().add(label);
		
		label_1 = new JLabel("New label");
		label_1.setFont(new Font("굴림", Font.PLAIN, 12));
		label_1.setBounds(12, 200, 305, 43);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("New label");
		label_2.setFont(new Font("굴림", Font.PLAIN, 14));
		label_2.setBounds(12, 253, 305, 43);
		getContentPane().add(label_2);
		
		label_3 = new JLabel("New label");
		label_3.setFont(new Font("굴림", Font.PLAIN, 14));
		label_3.setBounds(12, 289, 530, 43);
		getContentPane().add(label_3);
		
		button = new JButton("\uC608\uC57D\uD558\uAE30");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(160, 387, 237, 35);
		getContentPane().add(button);
		
		label_4 = new JLabel("New label");
		label_4.setFont(new Font("굴림", Font.PLAIN, 14));
		label_4.setBounds(12, 330, 530, 43);
		getContentPane().add(label_4);
		load();
	}
	List<Integer> week = new ArrayList<Integer>();
	private void load() {
		try (var rs = res("select *, d.name as dname, h.name as hname from doctor d join hospital h using(hno) where dno = "+dno)) {
			rs.next();
			label.setIcon(getIcon("doctor/"+dno+".png",label.getWidth(),label.getHeight()));
			label_1.setText("<html><font size = 6>"+rs.getString("dname")+" 의사 </font> "+" 전문의");
			label_2.setText("소속 : "+rs.getString("hname"));
			label_3.setText("소개글 : "+rs.getString("title"));
			label_4.setText("진료 날짜/시간 : "+rs.getString("day"));
			var imsi = rs.getString("day").replaceAll("\s|오전|\s오후", "").split(",");
			String[] txt = "일,월,화,수,목,금,토".split(",");
			for (var s : imsi) {
				week.add(Arrays.asList(txt).indexOf(s));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			showPage(new H_달력(dno, label_4.getText().contains("오전"), week),"H_달력");
		}
	}
}
