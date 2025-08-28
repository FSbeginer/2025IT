import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

public class J_막대차트 extends JPanel {
	public JLabel label;
	public JPanel panel;

	/**
	 * Create the panel.
	 */
	public J_막대차트() {
		setSize(852, 393);
		setLayout(null);
		
		label = new JLabel("New label");
		label.addMouseWheelListener(new LabelMouseWheelListener());
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(315, 10, 192, 51);
		add(label);
		
		panel = new JPanel();
		panel.setBounds(70, 71, 709, 312);
		add(panel);
		panel.setLayout(new GridLayout(0, 7, 0, 0));
		
		addLine();
		load();
	}

	int cno = 1;
	List<J_막대패널> pps = new ArrayList<J_막대패널>();
	private void addLine() {
		for (int i = 0; i < 7; i++) {
			J_막대패널 pp =new J_막대패널();
			panel.add(pp);
			pps.add(pp);
		}
	}
	private void load() {
		try (var rs = BF.res("select pname, sum(o.quantity), rank() over(order by sum(o.quantity) desc) '순위', p.description, cnam from `order` o join product p using(pno) join category c using(cno) where cno = "+cno+" group by pno limit 7;")) {
			int i = 0;
			int max = 0;
			int height = (panel.getHeight()-30);
			while(rs.next()) {
				max = Math.max(max, rs.getInt(2));
				var pp = pps.get(i);
				pp.label.setText(rs.getInt(2)+"");
				pp.label_1.setText(rs.getString(1));
				pp.label_2.setBackground(rs.getInt(3)==1?Color.red:Color.blue);
				pp.label_2.setToolTipText(rs.getString(4));
				int h =  (int) (rs.getDouble(2) / max * height);
				pp.label.setPreferredSize(new Dimension(0, 15+height-h));
				label.setText(rs.getString("cnam"));
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private class LabelMouseWheelListener implements MouseWheelListener {
		public void mouseWheelMoved(MouseWheelEvent e) {
			if(e.getWheelRotation()<0) {
				cno = (cno % 10)+1;
			}
			else {
				cno--;
				if(cno==0) cno = 10;
			}
			load();
		}

	}
}
