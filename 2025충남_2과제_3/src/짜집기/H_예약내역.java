package Â¥Áý±â;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
public class H_¿¹¾à³»¿ª extends BP {
	public JPanel panel;
	public JPanel panel_1;
	public JPanel panel_2;
	public JPanel panel_3;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JButton button;
	public JLabel label_3;
	public JPanel panel_4;
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

	/**
	 * Create the panel.
	 */
	public H_¿¹¾à³»¿ª() {
		
		panel = new JPanel();
		panel.setBounds(12, 37, 463, 421);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		panel_1.setBounds(487, 37, 463, 449);
		add(panel_1);
		panel_1.setLayout(new CardLayout(0, 0));
		
		panel_2 = new JPanel();
		panel_1.add(panel_2, "name_24380387137700");
		panel_2.setLayout(null);
		
		label = new JLabel("");
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(153, 41, 160, 134);
		panel_2.add(label);
		
		label_3 = new JLabel("New label");
		label_3.setForeground(new Color(0, 0, 0));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(108, 183, 254, 20);
		panel_2.add(label_3);
		
		panel_4 = new JPanel();
		panel_4.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel_4.setBounds(108, 205, 254, 215);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		
		label_4 = new JLabel("| \uC804\uC2DC\uAD00\uBA85");
		label_4.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		label_4.setBounds(0, 10, 105, 22);
		panel_4.add(label_4);
		
		label_5 = new JLabel("| \uD504\uB85C\uADF8\uB7A8\uBA85");
		label_5.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		label_5.setBounds(0, 42, 105, 22);
		panel_4.add(label_5);
		
		label_6 = new JLabel("| \uAE08\uC561");
		label_6.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		label_6.setBounds(0, 74, 105, 22);
		panel_4.add(label_6);
		
		label_7 = new JLabel("| \uD3EC\uC778\uD2B8");
		label_7.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		label_7.setBounds(0, 106, 105, 22);
		panel_4.add(label_7);
		
		label_8 = new JLabel("| \uC778\uC6D0");
		label_8.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		label_8.setBounds(0, 138, 105, 22);
		panel_4.add(label_8);
		
		label_9 = new JLabel("New label");
		label_9.setForeground(Color.GRAY);
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		label_9.setBounds(117, 12, 125, 22);
		panel_4.add(label_9);
		
		label_10 = new JLabel("New label");
		label_10.setForeground(Color.GRAY);
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		label_10.setBounds(117, 42, 125, 22);
		panel_4.add(label_10);
		
		label_11 = new JLabel("New label");
		label_11.setForeground(Color.GRAY);
		label_11.setHorizontalAlignment(SwingConstants.RIGHT);
		label_11.setBounds(117, 74, 125, 22);
		panel_4.add(label_11);
		
		label_12 = new JLabel("New label");
		label_12.setForeground(Color.GRAY);
		label_12.setHorizontalAlignment(SwingConstants.RIGHT);
		label_12.setBounds(117, 106, 125, 22);
		panel_4.add(label_12);
		
		label_13 = new JLabel("New label");
		label_13.setForeground(Color.GRAY);
		label_13.setVerticalAlignment(SwingConstants.TOP);
		label_13.setHorizontalAlignment(SwingConstants.RIGHT);
		label_13.setBounds(117, 138, 125, 67);
		panel_4.add(label_13);
		
		panel_3 = new JPanel();
		panel_1.add(panel_3, "name_24382610647800");
		panel_3.setLayout(null);
		
		label_1 = new JLabel("\uC608\uC57D \uB0B4\uC5ED\uC774 \uC874\uC7AC\uD558\uC9C0 \uC54A\uC2B5\uB2C8\uB2E4.");
		label_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(54, 121, 367, 39);
		panel_3.add(label_1);
		
		label_2 = new JLabel("\uC608\uC57D\uD558\uC2DC\uACA0\uC2B5\uB2C8\uAE4C?");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		label_2.setBounds(54, 170, 367, 39);
		panel_3.add(label_2);
		
		button = new JButton("\uC608\uC57D\uD558\uB7EC \uAC00\uAE30");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(93, 232, 291, 30);
		button.setBackground(BF.blue);
		button.setForeground(Color.white);
		panel_3.add(button);

		load();
		localReser(LocalDate.now());
	}

	private void load() {
		try (var rs = res("select * from ticket where uno = "+BF.uno+" order by date;")) {
			List<LocalDate> dates = new ArrayList<LocalDate>();
			while(rs.next()) {
				dates.add(rs.getDate("date").toLocalDate());
			}
			var d = new ´Þ·Â(dates);
			panel.add(d);
			for (int i = 0; i < 42; i++) {
				var lbl = d.lbls[i];
				if(lbl.date==LocalDate.now()) {
					lbl.border = true;
				}
				d.lbls[i].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						for (var lbl : d.lbls) {
							lbl.border = false;
						}
						lbl.border = true;
						d.repaint();
						localReser(lbl.date);
					}

				});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void localReser(LocalDate date) {
		try {
			var rs = res("select code, s.name sn, p.name pn, price, point, adult, youth, kid from ticket t join location l using(lno) join program p using(pno) join science s using(sno) where uno = "+BF.uno+" and t.date = '"+date+"'");
			if(rs.next()) {
				((CardLayout)panel_1.getLayout()).first(panel_1);
				label.setIcon(BF.getQRcode(label.getWidth(), label.getHeight()));
				label_3.setText(rs.getString(1));
				label_8.setText(rs.getString(2));
				label_9.setText(rs.getString(3));
				label_10.setText(rs.getString(4));
				label_11.setText(String.format("%,d", rs.getInt(4)));
				label_12.setText(String.format("%,d", rs.getInt(5)));
				label_13.setText("<html>¼ºÀÎ : "+rs.getString(6)+"¸í<br>Ã»¼Ò³â :"+rs.getString(7)+"¸í<br>¾î¸°ÀÌ : "+rs.getString(8)+"¸í");
			}
			else {
				((CardLayout)panel_1.getLayout()).last(panel_1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			((MainFrame)SwingUtilities.getWindowAncestor(button)).showPage(new D_¿¹¸Å(), "D_¿¹¸Å");
		}
	}
}
