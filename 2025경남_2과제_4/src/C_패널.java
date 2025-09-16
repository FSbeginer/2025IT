import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.sql.SQLException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class C_패널 extends JPanel {

	/**
	 * Create the panel.
	 */
	int jno;
	public JLabel label;
	public JPanel panel;
	private int bno;
	public C_패널(int jno) {
		this.jno = jno;
		setSize(330,590);
		setLayout(null);
		
		
		
		label = new JLabel("New label");
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label.setBounds(0, 0, 330, 275);
		add(label);
		
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image img  =BF.getIcon("지도.png",300,275).getImage();
				g.drawImage(img, 0, 0, null);
				g.setColor(Color.red);
				g.fillOval(me.x-4, me.y-4, 8, 8);
			}
		};
		panel.addMouseListener(new PanelMouseListener());
		panel.setBounds(10, 285, 300, 275);
		add(panel);
		
		try (var rs = BF.res("select * from job join brand using(bno) where jno = "+jno)) {
			rs.next();
			label.setText(String.format("<html>[근무조건]<br>급여: %,d원<br>근무요일: 주 %d회<br>근무시간: %d시간<br>고용형태: %s<br><br>[모집조건]<br>지원자격: %s<br>모집인원: %d명<br><br>[기업정보]<br>%s<br>▽위치보기", rs.getInt("jmoney"), rs.getInt("jday"),rs.getInt("jtime"),rs.getInt("jwork")==1?"계약직":"정규직", BF.getGrade(rs.getInt("jgrade")),rs.getInt("jpeople"), rs.getString("bname")));
			me = new Point(rs.getInt("bxx")/2,rs.getInt("byy")/2);
			bno = rs.getInt("bno");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	Point me;
	private class PanelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getPoint().distance(me)<=4) {
				((BF)SwingUtilities.getWindowAncestor(label)).showPage(new G_브랜드정보(bno),"G_브랜드정보");
			}
		}
	}
}
