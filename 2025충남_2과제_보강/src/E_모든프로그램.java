import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class E_모든프로그램 extends JPanel {

	/**
	 * Create the panel.
	 */
	int sno; 
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JPanel panel;
	public E_모든프로그램(int sno) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		this.sno = sno;
		setSize(900, 329);
		setLayout(null);
		
		label = new JLabel("  \uACFC\uD559\uAD00");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(0, 0, 903, 58);
		add(label);
		
		label_1 = new JLabel("<");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(0, 58, 65, 271);
		label_1.setEnabled(false);
		add(label_1);
		
		label_2 = new JLabel(">");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		label_2.setBounds(838, 58, 65, 271);
		add(label_2);
		
		panel = new JPanel();
		panel.setBounds(123, 58, 690, 261);
		add(panel);
		panel.setLayout(null);
		
		load();
	}
	List<JPanel> pps = new ArrayList<JPanel>();
	private void load() {
		try (var rs = BF.res("select * from (select sno,pno,lno,p.name pn, s.name sn, start_date, end_date, p_img, s_img, row_number() over(partition by pno order by e < 0, s > 0),datediff(start_date, curdate()) s ,datediff(end_date, curdate()) e from program p join location l using(pno) join science s using(sno) where sno = "+sno+" ) sub  group by pno order by e < 0, s > 0,e, sno;")) {
			int w = (panel.getWidth()-60)/3;
			int h = panel.getHeight()-30, i= 0;
			while(rs.next()) {
				E_패널 pp = new E_패널(BF.getIcon(rs.getBytes("p_img"),w,h-60), rs.getString("pn"), rs.getString("start_date")+"~"+rs.getString("end_date"));
				pp.label_2.setHorizontalAlignment(0);
				pp.setSize(w, h);
				DarkLabel dl = new DarkLabel();
				if(rs.getInt("s")<=0&&rs.getInt("e")>=0) {
					dl.setText("종료 "+rs.getInt("e")+"일 전");
				}
				else if(rs.getInt("e")<0) {
					dl.setText("죵료");
				}
				else {
					dl.setText(rs.getInt("s")+"일 후 시작");
				}
				DarkPanel dp = new DarkPanel(pp, dl);
				dp.setLocation((w+20)*i, 10);
				pps.add(dp);
				panel.add(dp);
				label.setText(rs.getString("sn"));
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		label_1.setEnabled(pps.get(0).getX()<0);
		label_2.setEnabled(pps.get(pps.size()-1).getX()>=panel.getWidth());
	}

	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(label_1.isEnabled()) {
				for (JPanel jp : pps) {
					jp.setLocation(jp.getX()+panel.getWidth(), jp.getY());
				}
				label_1.setEnabled(pps.get(0).getX()<0);
				label_2.setEnabled(pps.get(pps.size()-1).getX()>=panel.getWidth());
			}
		}
	}
	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(label_2.isEnabled()) {
				for (JPanel jp : pps) {
					jp.setLocation(jp.getX()-panel.getWidth(), jp.getY());
				}
				label_1.setEnabled(pps.get(0).getX()<0);
				label_2.setEnabled(pps.get(pps.size()-1).getX()>=panel.getWidth());
			}
		}
	}
}
