import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetSocketAddress;
import java.sql.SQLException;

import javax.swing.JScrollPane;
public class E_프로그램 extends BP {
	public JToggleButton toggleButton;
	public JToggleButton toggleButton_1;
	public JPanel panel;
	public JPanel panel_1;
	public JPanel panel_2;
	CardLayout card;
	public JLabel label;
	public JScrollPane scrollPane;
	public JPanel panel_3;
	public JLabel label_1;
	public JScrollPane scrollPane_1;
	public JPanel panel_4;

	/**
	 * Create the panel.
	 */
	public E_프로그램() {
		
		toggleButton = new MyButton("\uC9C4\uD589\uC911");
		toggleButton.addChangeListener(new ToggleButtonChangeListener());
		toggleButton.setBounds(254, 10, 176, 35);
		add(toggleButton);
		
		toggleButton_1 = new MyButton("\uBAA8\uB4E0 \uD504\uB85C\uADF8\uB7A8");
		toggleButton_1.addChangeListener(new ToggleButton_1ChangeListener());
		toggleButton_1.setBounds(532, 10, 176, 35);
		add(toggleButton_1);
		
		panel = new JPanel();
		panel.setBounds(35, 53, 919, 423);
		add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		panel_1 = new JPanel();
		panel.add(panel_1, "name_5524194905800");
		panel_1.setLayout(null);
		
		label = new JLabel("   \uD604\uC7AC \uC9C4\uD589\uC911\uC778 \uD504\uB85C\uADF8\uB7A8");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(0, 0, 919, 51);
		panel_1.add(label);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 49, 919, 374);
		panel_1.add(scrollPane);
		
		panel_3 = new JPanel();
		scrollPane.setViewportView(panel_3);
		panel_3.setLayout(null);
		
		panel_2 = new JPanel();
		panel.add(panel_2, "name_5526353252100");
		panel_2.setLayout(null);
		
		label_1 = new JLabel("   \uBAA8\uB4E0 \uD504\uB85C\uADF8\uB7A8");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_1.setBounds(0, 0, 919, 51);
		panel_2.add(label_1);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 49, 919, 374);
		panel_2.add(scrollPane_1);
		
		panel_4 = new JPanel();
		scrollPane_1.setViewportView(panel_4);
		panel_4.setLayout(null);
		
		
		card = (CardLayout)panel.getLayout();
		ButtonGroup bg =new ButtonGroup();
		bg.add(toggleButton);
		bg.add(toggleButton_1);
		toggleButton_1.setSelected(true);
		toggleButton.setSelected(true);
		
		load();
		load2();
	}
	private void load2() {
		try (var rs = res("select * from science ")) {
			int i = 0;
			while(rs.next()) {
				int sno = rs.getInt("sno");
				int idx = i;
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						E_프로그램앨범 pp = new E_프로그램앨범(sno);
						pp.setLocation(10, 10+(pp.getHeight()+10)*idx);
						panel_4.add(pp);
					}
				});
				i++;
			}
			panel_4.setPreferredSize(new Dimension(0, 10+(362)*i));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void load() {
		try (var rs = res("select *, datediff(end_date, start_date) `left` from (select *, curdate() between start_date and end_date r, row_number() over(partition by pno order by curdate() between start_date and end_date desc) from location join program using(pno)) sub where r = 1 group by pno;")) {
			int i = 0;
			int w = 241, h = 280;
			while(rs.next()) {
				var tl = res("select * from science where sno ="+rs.getInt("sno"));
				tl.next();
				E_프로그램패널 pp = new E_프로그램패널(getIcon(rs.getBytes("p_img"),w,h-60), rs.getString("name"), tl.getString("name"));
				tl.close();
				pp.setSize(w, h);
				DarkLabel dl = new DarkLabel();
				dl.setForeground(Color.white);
				dl.setFont(new Font( "맑은 고딕", 1, 20));
				dl.setText("종료 "+rs.getString("left")+"일 전");
				DarkPanel dp = new DarkPanel(pp, dl);
				int sno = rs.getInt("sno"), lno = rs.getInt("lno"), pno = rs.getInt("pno");
				dp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(BF.uno!=0) {
							((MainFrame)SwingUtilities.windowForComponent(dp)).showPage(new D_예매(sno,lno,pno), "예매");
						}
					}
				});
				dp.setLocation(10+(w+10)*i, 40);
				panel_3.add(dp);
				i++;
			}
			panel_3.setPreferredSize(new Dimension(10+(w+10)*i,0));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private class ToggleButton_1ChangeListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			if(toggleButton_1.isSelected()) {
				toggleButton_1.setBackground(blue);
				toggleButton_1.setForeground(Color.white);
				card.last(panel);
			}
			else {
				toggleButton_1.setBackground(Color.white);
				toggleButton_1.setForeground(blue);
			}
		}
	}
	private class ToggleButtonChangeListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			if(toggleButton.isSelected()) {
				toggleButton.setBackground(blue);
				toggleButton.setForeground(Color.white);
				card.first(panel);
			}
			else {
				toggleButton.setBackground(Color.white);
				toggleButton.setForeground(blue);
			}
		}
	}
}
