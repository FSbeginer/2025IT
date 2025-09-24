import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class E_프로그램앨범 extends JPanel {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JPanel panel;

	/**
	 * Create the panel.
	 */
	int sno;
	public E_프로그램앨범(int sno) {
		this.sno = sno;
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setSize(869, 352);
		setLayout(null);
		
		label = new JLabel("New label");
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(0, 0, 869, 53);
		add(label);
		
		label_1 = new JLabel("<");
		label_1.setEnabled(false);
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setFont(new Font("굴림", Font.BOLD, 38));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(0, 54, 67, 298);
		add(label_1);
		
		label_2 = new JLabel(">");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setFont(new Font("굴림", Font.BOLD, 38));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(802, 54, 67, 298);
		add(label_2);
		
		panel = new JPanel();
		panel.setBounds(79, 56, 720, 286);
		add(panel);
		panel.setLayout(null);
		
		load();
	}
	List<JPanel> pps = new ArrayList<>();
	private void load() {
		try (var rs = BF.res("select *,s.name sname, p.name pname, datediff(end_date, curdate()) `left`, datediff(start_date, curdate()) `start`, curdate() between start_date and end_date r from location l join program p using(pno) join science s using(sno) where sno = "+sno+" order by r!=1,`left`<0, `start`;")) {
			int w = (panel.getWidth()-60)/3;
			int h = (panel.getHeight()-20);
			while(rs.next()) {
				E_프로그램패널 pp =new E_프로그램패널(BF.getIcon(rs.getBytes("p_img"),w,h-60),rs.getString("pname"),rs.getString("start_date")+"~"+rs.getString("end_date"));
				pp.label_1.setHorizontalAlignment(0);
				pp.setSize(w, h);
				
				DarkLabel dl = new DarkLabel();
				dl.setForeground(Color.white);
				dl.setFont(new Font( "맑은 고딕", 1, 20));
				if(rs.getInt("r")==1) {
					dl.setText("종료 "+rs.getInt("left")+"일 전");
				}
				else if(rs.getInt("start")>=0) {
					dl.setText(rs.getInt("start")+"일후 시작");
				}
				else {
					dl.setText("종료");
				}
				DarkPanel dp = new DarkPanel(pp, dl);
				int lno = rs.getInt("lno"), pno = rs.getInt("pno");
				dp.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if(BF.isAdmin) {
							((MainFrame) SwingUtilities.windowForComponent(dp)).showPage(new I_과학관관리(sno, lno, pno),
									"과학관 관리");
						}
					};
				});
				dp.setLocation((w+20)*pps.size(), 10);
				panel.add(dp);
				pps.add(dp);
				label.setText(rs.getString("sname"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(label_2.isEnabled()) {
				moveright();
			}
		}

		private void moveright() {
			int w = (panel.getWidth()-60)/3;
			int h = (panel.getHeight()-20);
			for (JPanel pp: pps) {
				pp.setLocation(pp.getX()+(w+20), pp.getY());
			}
			if(pps.get(0).getX()==0) label_1.setEnabled(false);
			else label_1.setEnabled(true);
			if(pps.get(pps.size()-1).getX()>=panel.getWidth()) label_2.setEnabled(true);
			else label_2.setEnabled(false);
		}
	}
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(label_1.isEnabled()) {
				moveLeft();
			}
		}

		private void moveLeft() {
			int w = (panel.getWidth()-60)/3;
			int h = (panel.getHeight()-20);
			for (JPanel pp: pps) {
				pp.setLocation(pp.getX()-(w+20), pp.getY());
			}
			if(pps.get(0).getX()==0) label_1.setEnabled(false);
			else label_1.setEnabled(true);
			if(pps.get(pps.size()-1).getX()>=panel.getWidth()) label_2.setEnabled(true);
			else label_2.setEnabled(false);
		}
	}
}
