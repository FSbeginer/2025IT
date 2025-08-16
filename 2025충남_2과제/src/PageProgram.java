import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
public class PageProgram extends BP {
	public JScrollPane scrollPane;
	public JLabel label;
	public JToggleButton toggleButton;
	public JToggleButton toggleButton_1;

	public PageProgram() {
		setLayout(null);
		setName("프로그램");
		scrollPane = new JScrollPane();
		scrollPane.setBounds(66, 103, 757, 331);
		add(scrollPane);
		
		label = new JLabel("   \uD604\uC7AC \uC9C4\uD589\uC911\uC778 \uD504\uB85C\uADF8\uB7A8");
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setFont(new Font("맑은 고딕", Font.BOLD, 23));
		label.setBounds(66, 61, 756, 42);
		add(label);
		
		toggleButton = BF.createToggle("\uC9C4\uD589\uC911");
		toggleButton.setBounds(255, 10, 135, 23);
		add(toggleButton);
		
		toggleButton_1 = BF.createToggle("\uBAA8\uB4E0 \uD504\uB85C\uADF8\uB7A8");
		toggleButton_1.setBounds(460, 10, 135, 23);
		add(toggleButton_1);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(toggleButton);
		bg.add(toggleButton_1);
		toggleButton.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				scrollPane.setViewportView(getPanel1());
				label.setText("   현재 진행중인 프로그램");
			}
		});
		toggleButton_1.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				scrollPane.setViewportView(getPanel2());
				label.setText("   모든 프로그램");
			}

		});
		
		bg.getElements().nextElement().setSelected(true);
	}
	private Component getPanel2() {
		JPanel jp = new JPanel();
		jp.setLayout(null);
		
		for (int i = 0; i < 6; i++) {
			int idx = i;
			new Thread(new Runnable() {
				@Override
				public void run() {
					PanelProgram2 pp = new PanelProgram2(idx+1);
					pp.setLocation(10, 10+(pp.getHeight()+10)*idx);
					jp.add(pp);
					scrollPane.revalidate();
					repaint();
				}
			}).start();
		}
		jp.setPreferredSize(new Dimension(0, 10+(310)*5));
		
		return jp;
	}
	private Component getPanel1() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setLayout(null);
		
		try (var rs = res("select pno, sno, lno ,p_img, p.name as pname, s.name as sname, datediff(end_date, curdate()) diff from location l join program p using(pno) join science s using(sno) where (curdate() between start_date and end_date) = 1 order by end_date, sno;")) {
			int w = (int) (scrollPane.getWidth()/4.5);
			int h = (int) (scrollPane.getHeight()/1.5);
			int i = 0;
			
			while(rs.next()) {
				PanelProgram pp = new PanelProgram(getIcon(rs.getBytes("p_img"), w, h-40), rs.getString(5), rs.getString(6));
				DarkLabel jl = new DarkLabel();
				pp.setSize(w, h);
				jl.setText("종료 "+rs.getInt("diff")+"일 전");
				DarkerPanel dp = new DarkerPanel(pp, jl);
				dp.setLocation(10+(w+10)*i, 30);
				
				int pno = rs.getInt(1), sno = rs.getInt(2), lno = rs.getInt(3);
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(BF.uno!=0) {
							var main = ((MainFrame)SwingUtilities.getWindowAncestor(PageProgram.this));
							prevPage.add(new Object[] { PageProgram.this, main.label_3});
							main.showPage(new PageReservation(sno,pno,lno));
							main.label_2.setForeground(BF.blue);
						}
					}
				});
				panel.add(dp);
				i++;
			}
			panel.setPreferredSize(new Dimension(10+(w+10)*i, 0));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return panel;
	}
}
