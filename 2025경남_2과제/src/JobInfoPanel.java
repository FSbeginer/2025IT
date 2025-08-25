import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JobInfoPanel extends JPanel {
	public JLabel label;
	public JLabel label_1;

	/**
	 * Create the panel.
	 */
	Point mypoint;
	String txt;
	int bno,jno;
	public JobInfoPanel(int jno) {
		this.jno = jno;
		setSize(350, 580);
		setLayout(new GridLayout(0, 1, 0, 0));
		label = new JLabel(txt);
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label.setBorder(new EmptyBorder(10, 10, 10, 10));
		label.setVerticalAlignment(SwingConstants.TOP);
		add(label);
		
		
		label_1 = new JLabel("") {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image map = BF.getIcon("지도.png",300,275).getImage();
				g.drawImage(map, 15, 0, null);
				g.setColor(Color.red);
				g.fillOval(mypoint.x-4, mypoint.y-4, 8,	8);
			}
		};
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(label_1);
		
		try {
			var rs = BF.res("select * from job join brand using(bno) where jno = "+jno);
			rs.next();
			mypoint = new Point(rs.getInt("bxx")/2, rs.getInt("byy")/2);
			bno = rs.getInt("bno");
			txt = String.format("<html>[근무조건]<br>급여: %,d원<br>근무요일: 주 %d회<br>근무시간: %d시간<br>고용형태: %s<br><br>[모집조건]<br>지원자격: %s<br>모집인원: %d명 <br><br>[기업정보]<br>%s<br>▽위치보기", rs.getInt("jmoney"),rs.getInt("jday"),rs.getInt("jtime"),rs.getBoolean("jwork")? "계약직":"정규직", rs.getInt("jgrade")==0?"무관":rs.getInt("jgrade")==1?"대학":"고등", rs.getInt("jpeople"), rs.getString("bname"));
			label.setText(txt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(mypoint.distance(e.getPoint())<=4) {
				((BF)SwingUtilities.getWindowAncestor(JobInfoPanel.this)).showPage(new G_브랜드정보(ABORT),"G_브랜드정보");
			}
		}
	}
}
