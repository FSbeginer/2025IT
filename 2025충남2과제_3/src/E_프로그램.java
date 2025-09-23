import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
public class E_프로그램 extends BP {
	public JButton button;
	public JButton button_1;
	public JPanel panel;
	public JLabel label;
	public JScrollPane scrollPane;
	public JScrollPane scrollPane_1;
	public JPanel panel_1;
	public JPanel panel_2;

	/**
	 * Create the panel.
	 */
	public E_프로그램() {
		
		button = new JButton("\uC9C4\uD589\uC911");
		button.addActionListener(new ButtonActionListener());
		button.setForeground(new Color(255, 255, 255));
		button.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		button.setBounds(239, 24, 168, 31);
		add(button);
		
		button_1 = new JButton("\uC9C4\uD589\uC911");
		button_1.addActionListener(new Button_1ActionListener());
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		button_1.setBounds(483, 24, 168, 31);
		add(button_1);
		
		panel = new JPanel();
		panel.setBounds(38, 104, 888, 324);
		add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane, "name_15822678884300");
		
		panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		panel.add(scrollPane_1, "name_15830587729700");
		
		panel_2 = new JPanel();
		scrollPane_1.setViewportView(panel_2);
		panel_2.setLayout(null);
		
		label = new JLabel("    \uD604\uC7AC \uC9C4\uD589\uC911\uC778 \uD504\uB85C\uADF8\uB7A8");
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label.setBounds(38, 63, 888, 41);
		add(label);
		button.setBorder(new LineBorder(BF.blue));
		button_1.setBorder(new LineBorder(BF.blue));
		button.setBackground(BF.blue);
		button.setForeground(Color.white);
		button_1.setBackground(Color.white);
		button_1.setForeground(BF.blue);
		card = (CardLayout) panel.getLayout();
		load1();
		load2();
	}
	private void load2() {
		try (var rs = res("select * from science")) {
			int i = 0;
			int w =  842, h =282;
			while(rs.next()) {
				E_모든프로그램 pp = new E_모든프로그램(rs.getInt(1));
				pp.setLocation(10, 10+(h+10)*i);
				panel_2.add(pp);
				i++;
			}
			panel_2.setPreferredSize(new Dimension(0, 10+(h+10)*i));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	CardLayout card;
	private void load1() {
		try (var rs = res("select * from (select pno, lno, sno, p.name pn, p.explanation ,s.name sn,p_img ,start_date ,end_date, datediff(end_date, curdate()) e,  curdate() between start_date and end_date flag from location l join program p using(pno) join science s using(sno)) sub where flag = 1 order by e;")) {
			int w = (panel.getWidth() / 5);
			int h = panel.getHeight() - 20-60;
			int i = 0;
			while(rs.next()) {
				E_프로그램패널 pp = new E_프로그램패널(getIcon(rs.getBytes("p_img"),w,h-30),rs.getString("pn"),rs.getString("sn"));
				pp.setSize(w, h);
				
				DarkLabel dl = new DarkLabel();
				dl.setHorizontalAlignment(0);
				dl.setForeground(Color.white);
				dl.setFont(new Font("맑은 고딕", 1, 15));
				dl.setText("종료 "+rs.getString("e")+"일 전");
				DarkPanel dp = new DarkPanel(pp,dl);
				
				int pno = rs.getInt(1), lno = rs.getInt(2), sno = rs.getInt(3);
				dp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						((MainFrame)SwingUtilities.getWindowAncestor(label)).showPage(new D_예매(sno, lno, pno), "예매");
					}
				});
				dp.setLocation(10+i*(w+10), 10);
				panel_1.add(dp);
				i++;
			}
			panel_1.setPreferredSize(new Dimension(10+(w+10)*i, 0));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			button.setBackground(BF.blue);
			button.setForeground(Color.white);
			button_1.setBackground(Color.white);
			button_1.setForeground(BF.blue);
			card.first(panel);
			label.setText("    현재 진행중인 프로그램");
		}
	}
	private class Button_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			button_1.setBackground(BF.blue);
			button_1.setForeground(Color.white);
			button.setBackground(Color.white);
			button.setForeground(BF.blue);
			card.last(panel);
			label.setText("    모든 프로그램");
		}
	}
}
