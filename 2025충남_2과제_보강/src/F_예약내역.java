import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class F_¿¹¾à³»¿ª extends BP {
	public JPanel panel;
	public JPanel panel_1;
	public JPanel panel_2;
	public JPanel panel_3;
	public JLabel label;
	public JPanel panel_4;
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
	public JLabel label_12;
	public JLabel label_13;
	public JButton button;

	/**
	 * Create the panel.
	 */
	public F_¿¹¾à³»¿ª() {
		
		panel = new JPanel();
		panel.setBounds(35, 33, 371, 398);
		add(panel);
		
		panel_1 = new JPanel();
		panel_1.setBounds(502, 33, 456, 446);
		add(panel_1);
		panel_1.setLayout(new CardLayout(0, 0));
		
		panel_2 = new JPanel();
		panel_1.add(panel_2, "name_40231839684300");
		panel_2.setLayout(null);
		
		label = new JLabel(BF.getQr(180, 146));
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(170, 28, 124, 116);
		panel_2.add(label);
		
		panel_4 = new JPanel();
		panel_4.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel_4.setBounds(75, 176, 314, 260);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		
		label_2 = new JLabel("| \uC804\uC2DC\uAD00\uBA85");
		label_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		label_2.setBounds(12, 10, 81, 22);
		panel_4.add(label_2);
		
		label_3 = new JLabel("| \uD504\uB85C\uADF8\uB7A8\uBA85");
		label_3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		label_3.setBounds(12, 47, 81, 22);
		panel_4.add(label_3);
		
		label_4 = new JLabel("| \uAE08\uC561");
		label_4.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		label_4.setBounds(12, 82, 81, 22);
		panel_4.add(label_4);
		
		label_5 = new JLabel("| \uD3EC\uC778\uD2B8");
		label_5.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		label_5.setBounds(12, 114, 81, 22);
		panel_4.add(label_5);
		
		label_6 = new JLabel("| \uC778\uC6D0");
		label_6.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		label_6.setBounds(12, 146, 81, 22);
		panel_4.add(label_6);
		
		label_7 = new JLabel("New label");
		label_7.setForeground(Color.LIGHT_GRAY);
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setBounds(143, 10, 159, 22);
		panel_4.add(label_7);
		
		label_8 = new JLabel("New label");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setForeground(Color.LIGHT_GRAY);
		label_8.setBounds(143, 47, 159, 22);
		panel_4.add(label_8);
		
		label_9 = new JLabel("New label");
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		label_9.setForeground(Color.LIGHT_GRAY);
		label_9.setBounds(143, 84, 159, 22);
		panel_4.add(label_9);
		
		label_10 = new JLabel("New label");
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		label_10.setForeground(Color.LIGHT_GRAY);
		label_10.setBounds(143, 116, 159, 22);
		panel_4.add(label_10);
		
		label_11 = new JLabel("New label");
		label_11.setVerticalAlignment(SwingConstants.TOP);
		label_11.setHorizontalAlignment(SwingConstants.RIGHT);
		label_11.setForeground(Color.LIGHT_GRAY);
		label_11.setBounds(143, 148, 159, 77);
		panel_4.add(label_11);
		
		label_1 = new JLabel("New label");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(156, 153, 153, 24);
		panel_2.add(label_1);
		
		panel_3 = new JPanel();
		panel_1.add(panel_3, "name_40233456453300");
		panel_3.setLayout(null);
		
		label_12 = new JLabel("\uC608\uC57D \uB0B4\uC5ED\uC774 \uC874\uC7AC\uD558\uC9C0 \uC54A\uC2B5\uB2C8\uB2E4.");
		label_12.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 22));
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setBounds(48, 94, 358, 59);
		panel_3.add(label_12);
		
		label_13 = new JLabel("\uC608\uC57D\uD558\uC2DC\uACA0\uC2B5\uB2C8\uAE4C?");
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 22));
		label_13.setBounds(48, 142, 358, 59);
		panel_3.add(label_13);
		
		button = new JButton("\uC608\uC57D\uD558\uB7EC \uAC00\uAE30");
		button.addActionListener(new ButtonActionListener());
		button.setBackground(BF.blue);
		button.setForeground(Color.white);
		button.setBounds(79, 222, 300, 39);
		panel_3.add(button);
		panel.setLayout(new BorderLayout(0, 0));
		
		try {
			var rs = res("select * from ticket where uno = "+BF.uno);
			List<LocalDate> dates = new ArrayList<LocalDate>();
			while(rs.next()) {
				dates.add(rs.getDate("date").toLocalDate());
			}
			´Þ·Â d = new ´Þ·Â(dates);
			
			panel.add(d);
			for (var jl : d.jls) {
				jl.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						d.selDate = jl.date;
						d.lendering();
						loadDate(jl.date);	
					}

				});
				if(jl.date.equals(LocalDate.now())) {
					d.selDate = jl.date;
					d.lendering();
					loadDate(jl.date);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			getmf(button).showpage(new D_¿¹¸Å(), "¿¹¸Å");
		}
	}
	private void loadDate(LocalDate date) {
		try (var rs = res("select code,s.name sn,p.name pn, price, point, adult, youth, kid from ticket join location l using(lno) join science s using(sno)  join program p using(pno) where uno = "+BF.uno+" and ticket.date = '"+date+"'")) {
			if(rs.next()) {
				((CardLayout)panel_1.getLayout()).first(panel_1);
				label_1.setText(rs.getString(1));
				label_7.setText(rs.getString(2));
				label_8.setText(rs.getString(3));
				label_9.setText(String.format("%,d",rs.getInt(4)));
				label_10.setText(String.format("%,d",rs.getInt(5)));
				label_11.setText(String.format("<html>¼ºÀÎ : %d¸í<br>Ã»¼Ò³â : %d¸í<br>¾î¸°ÀÌ : %d¸í", rs.getInt(6),rs.getInt(7),rs.getInt(8)));
			}
			else {
				((CardLayout)panel_1.getLayout()).last(panel_1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
