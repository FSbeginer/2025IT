import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class E_프로그램 extends BP {
	public JToggleButton toggleButton;
	public JToggleButton toggleButton_1;
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
		
		toggleButton = new MyButton("\uC9C4\uD589\uC911");
		toggleButton.addActionListener(new ToggleButtonActionListener());
		toggleButton.setBounds(272, 10, 135, 31);
		add(toggleButton);
		
		toggleButton_1 = new MyButton("\uBAA8\uB4E0 \uD504\uB85C\uADF8\uB7A8");
		toggleButton_1.addActionListener(new ToggleButton_1ActionListener());
		toggleButton_1.setBounds(534, 10, 135, 31);
		add(toggleButton_1);
		
		toggleButton.setSelected(true);
		ButtonGroup bg=  new ButtonGroup();
		bg.add(toggleButton);
		bg.add(toggleButton_1);
		
		panel = new JPanel();
		panel.setBounds(34, 104, 939, 329);
		add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane, "name_32741576264000");
		
		panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		panel.add(scrollPane_1, "name_32762513227700");
		
		panel_2 = new JPanel();
		scrollPane_1.setViewportView(panel_2);
		panel_2.setLayout(null);
		
		label = new JLabel("   \uBAA8\uB4E0 \uD504\uB85C\uADF8\uB7A8");
		label.setBounds(34, 63, 939, 44);
		add(label);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 21));
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		load();
		load2();
	}

	private void load2() {
		try (var rs = res("select * from science")) {
			
			int h = 329, i = 0;
			while(rs.next()) {
				E_모든프로그램 p = new E_모든프로그램(rs.getInt(1));
				p.setLocation(10,10 +(h+10)*i);
				panel_2.add(p);
				i++;
			}
			panel_2.setPreferredSize(new Dimension(0, 10+(10+h)*i));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void load() {
		try (var rs = res("select sno,pno,lno,p.name pn, s.name sn, p_img, s_img, start_date, end_date, datediff(end_date, curdate()) e from program p join location l using(pno) join science s using(sno) where curdate() between start_date and end_date order by e, sno;")) {
			int w = panel.getWidth()/5-10;
			int h =panel.getHeight()-60, i = 0;
			while(rs.next()) {
				E_패널 pp = new E_패널(getIcon(rs.getBytes("p_img"),w,h), rs.getString("pn"),rs.getString("sn"));
				pp.setSize(w, h);
				DarkLabel dl = new DarkLabel();
				dl.setText("종료 "+rs.getString("e")+"일 전");
				DarkPanel dp = new DarkPanel(pp, dl);
				dp.setLocation((w+10)*i, 30);
				dp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
					}
				});
				panel_1.add(dp);
				i++;
			}
			panel_1.setPreferredSize(new Dimension((w+10)*i,0));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private class ToggleButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			((CardLayout)panel.getLayout()).first(panel);
		}
	}
	private class ToggleButton_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			((CardLayout)panel.getLayout()).last(panel);
		}
	}
}
class MyButton extends JToggleButton {
	public MyButton(String txt) {
		super(txt);
		setContentAreaFilled(false);
		setOpaque(true);
		setBorder(new LineBorder(BF.blue));
		setBackground(Color.white);
		setForeground(BF.blue);
		addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if(isSelected()) {
					setBackground(BF.blue);
					setForeground(Color.white);
				}
				else {
					setBackground(Color.white);
					setForeground(BF.blue);
				}
			}
		});
	}
	
}