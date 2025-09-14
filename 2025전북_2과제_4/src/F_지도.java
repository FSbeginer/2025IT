import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class F_지도 extends BF {


	Point me;
	String myname;
	public JPanel panel;
	public JTextField textField;
	public JButton button;
	public F_지도(Point me, String myname) {
		setTitle("\uC9C0\uB3C4");
		this.me = me;
		this.myname = myname;
		setBounds(100, 100, 516, 626);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				Image img= getIcon("map.png",500,500).getImage();
				g.drawImage(img, 0, 0, null);
				g.setColor(Color.blue);
				g.fillOval(me.x-4, me.y-4, 8, 8);
				g.setColor(Color.black);
				g.drawString(myname, me.x-20, me.y-6);
				for (var p : ps) {
					if(selP.equals(p)) {
						String n = names.get(ps.indexOf(selP));
						g.drawString(n, p.x-g.getFontMetrics().stringWidth(n)/2, p.y-6);
					}
					g.fillOval(p.x-4, p.y-4, 8, 8);
				}
			}
		};
		panel.addMouseMotionListener(new PanelMouseMotionListener());
		panel.setBounds(0, 87, 500, 500);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setFocusable(true);
		textField.setColumns(10);
		textField.setBorder(new LineBorder(new Color(155, 208, 232)));
		textField.setBounds(12, 24, 349, 30);
		getContentPane().add(textField);
		
		button = new JButton("\uAC70\uB9AC \uD655\uC778");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(375, 27, 97, 23);
		getContentPane().add(button);
		
		load();
		System.out.println(1);
	}
	
	List<Point> ps = new ArrayList<Point>();
	List<String> names = new ArrayList<String>();
	Point selP = new Point();
	private void load() {
		try (var rs = res("select * from pharmacy")) {
			while(rs.next()) {
				Point p = new Point(rs.getInt("x"),rs.getInt("y"));
				if(p.distance(me)<=100) {
					ps.add(p);
					names.add(rs.getString("name"));
					System.out.println(p);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private class PanelMouseMotionListener extends MouseMotionAdapter {
		@Override
		public void mouseMoved(MouseEvent e) {
			var p = ps.stream().filter(x->x.distance(e.getPoint())<=4).findAny().orElse(null);
			if(p!=null) {
				selP = p;
				System.out.println(1);
			}
			else {
				selP = new Point();
			}
			repaint();
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			var txt =textField.getText();
			if(!names.stream().anyMatch(x->x.contains(txt))||txt.isBlank()) {
				msgErr("해당 약국이 존재하지 않습니다.");
			}
			else {
				var n = names.stream().filter(x->x.contains(txt)).findAny().orElse("");
				int idx = names.indexOf(n);
				Point p = ps.get(idx);
				msgInfo(String.format("%s에서 %s까지의 거리: %.1fm", myname, n, Math.hypot(me.x-p.x, me.y-p.y)));
			}
		}
	}
}
