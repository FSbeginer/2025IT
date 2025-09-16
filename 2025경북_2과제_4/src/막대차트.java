import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseWheelListener;
import java.sql.SQLException;
import java.awt.event.MouseWheelEvent;

public class 막대차트 extends JPanel {
	public JLabel label;
	public JPanel panel;

	/**
	 * Create the panel.
	 */
	public 막대차트() {
		setSize(944, 395);
		setLayout(null);
		
		label = new JLabel("\uC2DD\uD488");
		label.addMouseWheelListener(new LabelMouseWheelListener());
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(363, 10, 206, 50);
		add(label);
		
		panel = new JPanel();
		panel.setBounds(57, 68, 823, 296);
		add(panel);
		panel.setLayout(new GridLayout(0, 7, 0, 0));
		load();
	}

	int cno = 1;
	private class LabelMouseWheelListener implements MouseWheelListener {
		public void mouseWheelMoved(MouseWheelEvent e) {
			int dx = e.getWheelRotation() > 0 ? 1:-1;
			if(dx>0) {
				cno = cno % 10 +1;
			}
			else {
				cno = cno -1;
				if(cno ==0) {
					cno = 10;
				}
			}
			load();
		}

	}
	private void load() {
		panel.removeAll();
		try (var rs = BF.res("select *, sum(o.quantity) cnt, rank() over(order by sum(o.quantity) desc) r from `order` o right join product p using(pno) join category using(cno)  where cno = "+cno+" group by pno limit 7;")) {
			int max = 0;
			while(rs.next()) {
				max = Math.max(rs.getInt("cnt"),max);
				막대 pp = new 막대();
				pp.label.setText(rs.getInt("cnt")+"");
				pp.label_1.setText(rs.getString("pname"));
				pp.panel.setToolTipText(rs.getString("description"));
				pp.panel.setBackground(rs.getInt("r")==1?Color.red:Color.blue);
				int mh = panel.getHeight()-30;
				int h =  (int) ((double)rs.getInt("cnt")/max*mh);
				pp.label.setPreferredSize(new Dimension(0, 15 + (mh-h)));
				panel.add(pp);
				label.setText(rs.getString("cnam"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel.revalidate();
		panel.repaint();
	}
}
