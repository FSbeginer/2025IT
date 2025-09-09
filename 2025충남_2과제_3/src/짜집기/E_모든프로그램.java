package 짜집기;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.Stack;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class E_모든프로그램 extends JPanel {
	public JLabel label;
	public JPanel panel;
	public JLabel label_1;
	public JLabel label_2;
	public JPanel panel_1;

	/**
	 * Create the panel.
	 */
	int sno;
	
	public E_모든프로그램(int sno) {
		this.sno = sno;
		setSize(842, 282);
		setLayout(null);

		label = new JLabel("New label");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(0, 0, 842, 40);
		add(label);

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 38, 842, 244);
		add(panel);
		panel.setLayout(null);

		label_1 = new JLabel("<");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setEnabled(false);
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 21));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(0, 0, 63, 244);
		panel.add(label_1);

		label_2 = new JLabel(">");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 21));
		label_2.setBounds(779, 0, 63, 244);
		panel.add(label_2);

		panel_1 = new JPanel();
		panel_1.setBounds(75, 0, 693, 240);
		panel.add(panel_1);
		panel_1.setLayout(null);

		load();
	}

	List<JPanel> pps = new Stack<JPanel>();
	private void load() {
		try (var rs = BF.res(
				"select * from (select pno, lno, sno, p.name pn, p.explanation ,s.name sn,p_img ,start_date ,end_date, datediff(start_date, curdate()) s,datediff(end_date, curdate()) e,  curdate() between start_date and end_date flag from location l join program p using(pno) join science s using(sno)) sub where sno = "
						+ sno + " order by flag!=1, s<0, s;")) {
			int w = (panel_1.getWidth() - 30) / 3;
			int h = panel_1.getHeight() - 20;
			int i = 0;
			while (rs.next()) {
				E_프로그램패널 pp = new E_프로그램패널(BF.getIcon(rs.getBytes("p_img"), w, h), rs.getString("pn"),
						rs.getString("start_date") + "~" + rs.getString("end_date"));
				pp.setSize(w, h);

				DarkLabel dl = new DarkLabel();
				dl.setHorizontalAlignment(0);
				dl.setForeground(Color.white);
				dl.setFont(new Font("맑은 고딕", 1, 15));
				if(rs.getBoolean("flag")) {
					dl.setText("종료 " + rs.getString("e") + "일 전");
				}
				else if(rs.getInt("e")>0) {
					dl.setText(rs.getString("s")+ "일후 시작");
				}
				else
					dl.setText("종료");
				DarkPanel dp = new DarkPanel(pp, dl);

				int pno = rs.getInt(1), lno = rs.getInt(2), sno = rs.getInt(3);
				dp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(BF.isAdmin) {
							((MainFrame) SwingUtilities.getWindowAncestor(label)).showPage(new I_과학관관리(pno, lno, sno), "과학관 관리");
						}
					}
				});
				dp.setLocation(i * (w + 10), 10);
				panel_1.add(dp);
				pps.add(dp);
				i++;
				label.setText(rs.getString("sn"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(label_1.isEnabled()) {
				for (JPanel pp : pps) {
					pp.setLocation(pp.getX()+panel_1.getWidth(), pp.getY());
				}
				repaint();
				if(pps.get(0).getX()+panel_1.getWidth()>=panel_1.getWidth()) label_1.setEnabled(false);
				else label_1.setEnabled(true);
				if(pps.get(pps.size()-1).getX()-panel_1.getWidth()<0) label_2.setEnabled(false);
				else label_2.setEnabled(true);
			}
		}
	}
	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(label_2.isEnabled()) {
				for (JPanel pp : pps) {
					pp.setLocation(pp.getX()-panel_1.getWidth(), pp.getY());
				}
				repaint();
				if(pps.get(0).getX()+panel_1.getWidth()>=panel_1.getWidth()) label_1.setEnabled(false);
				else label_1.setEnabled(true);
				if(pps.get(pps.size()-1).getX()-panel_1.getWidth()<0) label_2.setEnabled(false);
				else label_2.setEnabled(true);
			}
		}
	}
}
