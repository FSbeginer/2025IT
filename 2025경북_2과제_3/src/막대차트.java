import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

public class 막대차트 extends JPanel {
	public JLabel label;
	public JPanel panel;

	/**
	 * Create the panel.
	 */
	public 막대차트() {
		setSize(913, 482);
		setLayout(null);
		
		label = new JLabel("\u3145\u3131\uD488");
		label.addMouseWheelListener(new LabelMouseWheelListener());
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(349, 10, 214, 62);
		add(label);
		
		panel = new JPanel();
		panel.setBounds(83, 79, 740, 321);
		add(panel);
		panel.setLayout(new GridLayout(0, 7, 0, 0));
		
		load();
	}

	int cno = 1;
	private void load() {
		panel.removeAll();
		try (var rs = BF.res("select pname, sum(o.quantity) cnt, rank() over(order by  sum(o.quantity) desc)  r,p.description from `order` o join product p using(pno) join category using(cno) where cno = "+cno+" group by pno limit 7;")) {
			int max = 0;
			int mh = panel.getHeight()-30;
			while(rs.next()) {
				막대 pp = new 막대();
				if(rs.getInt("r")==1) {
					pp.panel.setBackground(Color.red);
				}
				else {
					pp.panel.setBackground(Color.blue);
				}
				pp.panel.setBorder(new LineBorder(Color.black));
				pp.panel.setToolTipText(rs.getString("description"));
				pp.label_3.setText(rs.getInt("cnt")+"");
				pp.label_2.setText(rs.getString("pname"));
				max = Math.max(rs.getInt("cnt"), max);
				pp.label_3.setVerticalAlignment(SwingConstants.BOTTOM);
				pp.label_3.setPreferredSize(new Dimension(0, 15+ (int) (mh -( (double) rs.getInt("cnt")/max * mh))));
				panel.add(pp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = BF.res("select * from category where cno = "+cno)) {
			rs.next();
			label.setText(rs.getString("cnam"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel.revalidate();
		panel.repaint();
	}

	private class LabelMouseWheelListener implements MouseWheelListener {
		public void mouseWheelMoved(MouseWheelEvent e) {
			if(e.getWheelRotation()>0) {
				cno = cno % 10 +1;
			}
			else {
				--cno;
				if(cno ==0) {
					cno = 10;
				}
			}
			load();
		}
	}
}
