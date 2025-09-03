import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class F_지도 extends BF {
	public JLabel label;
	public JTextField textField;
	public JButton button;


	Point p;
	String name;
	public F_지도(Point p, String name) {
		this.p = p;
		this.name = name;
		setTitle("\uC9C0\uB3C4");
		setBounds(100, 100, 515, 591);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("") {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				Image img = BF.getIcon("map.png",500,500).getImage();
				g.drawImage(img, 0, 0, null);
				g.setColor(Color.blue);
				g.fillOval(p.x-4, p.y-4, 8, 8);
				
				g.setColor(Color.black);
				g.drawString(name, p.x-25, p.y-10);
				for (var p : ps) {
					g.fillOval(p.x-4, p.y-4, 8, 8);	
				}
				if(idx!=-1) {
					g.drawString(names.get(idx), ps.get(idx).x-g.getFontMetrics().stringWidth(names.get(idx))/2, ps.get(idx).y-10);
				}
				repaint();
			}

		};
		label.addMouseMotionListener(new LabelMouseMotionListener());
		label.setBounds(0, 51, 500, 500);
		getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBorder(new LineBorder(blue));
		textField.setBounds(27, 10, 315, 31);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		button = new JButton("\uAC70\uB9AC \uD655\uC778");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(354, 12, 119, 27);
		getContentPane().add(button);

		load();
	}

	List<Point> ps = new ArrayList<Point>();
	List<String> names = new ArrayList<String>();
	int idx =-1;
	private void load() {
		try (var rs = res("select * from pharmacy")) {
			while (rs.next()) {
				var pp  = new Point(rs.getInt("x"), rs.getInt("y"));
				if(pp.distance(p)<=90) {
					ps.add(pp);
					names.add(rs.getString(2));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class LabelMouseMotionListener extends MouseMotionAdapter {
		@Override
		public void mouseMoved(MouseEvent e) {
			var p = ps.stream().filter(x->x.distance(e.getPoint())<=4).findAny().orElse(null);
			if(p!=null) {
				idx = ps.indexOf(p);
			}
			else {
				idx = -1;
			}
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			var txt = textField.getText();
			var n = names.stream().filter(x->x.contains(txt)).findAny().orElse(null);
			if(n!=null) {
				int idx = names.indexOf(n);
				double d = p.distance(ps.get(idx));
				msgInfo(name+"에서 "+n+"까지의 거리: "+String.format("%.1f m", d));
			}
			else {
				msgErr("해당 약국이 존재하지 않습니다.");
			}
		}
	}
}
