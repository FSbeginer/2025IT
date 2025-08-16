import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class PanelProgram2 extends JPanel {
	public JLabel label;
	public JPanel panel;
	public JLabel label_1;
	public JLabel label_2;
	public JPanel panel_1;

	/**
	 * Create the panel.
	 */
	int sno;
	public PanelProgram2(int sno) {
		this.sno = sno;
		setBackground(new Color(255, 255, 255));
		setSize(700, 300);
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel("New label");
		label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 17));
		label.setPreferredSize(new Dimension(57, 50));
		label.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(0, 10, 0, 0)));
		add(label, BorderLayout.NORTH);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(255, 255, 255));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		label_1 = new JLabel("<");
		label_1.setEnabled(false);
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 26));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_1, BorderLayout.WEST);
		
		label_2 = new JLabel(">");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 26));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2, BorderLayout.EAST);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		load();
	}
	
	List<JPanel> pps = new ArrayList<JPanel>();
	private void load() {
		try (var rs = BF.res("select *, p.name as pname, s.name as sname, datediff(start_date, curdate()) sdiff, datediff(end_date, curdate()) ediff,  curdate() between start_date and end_date n from location l join program p using(pno) join science s using(sno) where sno = "+sno+" order by n desc, ediff < 0, sdiff asc;")) {
			int i = 0;
			int w = (660-30)/3, h=248-20;
			while(rs.next()) {
				PanelProgram pp = new PanelProgram(BF.getIcon(rs.getBytes("p_img"),w,h), rs.getString("pname"), rs.getString("start_date")+"-"+rs.getString("end_date"));
				pp.setSize(w,h);
				DarkLabel dr = new DarkLabel();
				int ediff = rs.getInt("ediff");
				int sdiff = rs.getInt("sdiff");
				boolean progress = rs.getBoolean("n");

				if(ediff<0)
					dr.setText("Á¾·á");
				if(progress) 
					dr.setText("Á¾·á "+ediff+"ÀÏ Àü");
				else
					dr.setText(sdiff+"ÀÏ ÈÄ ½ÃÀÛ");
				
				DarkerPanel dp = new DarkerPanel(pp, dr);
				int sno = rs.getInt("sno"), pno = rs.getInt("pno"), lno = rs.getInt("lno");
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(progress&&BF.uno!=0) {
							var main = (MainFrame)SwingUtilities.getWindowAncestor(dp);
							main.showPage(new PageReservation(sno, pno, lno));
							main.label_2.setForeground(BF.blue);
						}
						if(BF.isAdmin && !progress && sdiff>0) {
							var main = (MainFrame)SwingUtilities.getWindowAncestor(dp);
							main.showPage(new PageMange(lno));
							main.label_1.setForeground(BF.blue);
							BP.prevPage.add(new Object[] {main.panel_1.getComponent(0), main.label_3});
						}
					}
				});
				dp.setLocation((w+10)*i, 10);
				panel_1.add(dp);
				pps.add(dp);
				label.setText(rs.getString("sname"));
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(!label_1.isEnabled()) return;
			for (JPanel jPanel : pps) {
				jPanel.setLocation(jPanel.getX()+panel_1.getWidth(), 10);
			}
			label_1.setEnabled(pps.get(0).getX()!=0);
			label_2.setEnabled(pps.get(pps.size()-3).getX()!=0);
		}
	}
	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(!label_2.isEnabled()) return;
			for (JPanel jPanel : pps) {
				jPanel.setLocation(jPanel.getX()-panel_1.getWidth(), 10);
			}
			label_1.setEnabled(pps.get(0).getX()!=0);
			label_2.setEnabled(pps.get(pps.size()-3).getX()!=0);
		}
	}
}
