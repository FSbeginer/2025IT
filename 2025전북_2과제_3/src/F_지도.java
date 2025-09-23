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
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
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
		setBounds(100, 100, 515, 604);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image img = getIcon("map.png",500,500).getImage();
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.drawImage(img, 0, 0, null);
				
				g2.setColor(Color.blue);
				g2.fillOval(me.x-4, me.y-4, 8, 8);
				g2.setColor(Color.black);
				g2.drawString(myname, me.x-25, me.y-10);
				
				for (var p: plist) {
					if(p==selP) {
						int idx = plist.indexOf(selP);
						g2.drawString(name.get(idx), p.x-25, p.y-10);
					}
					g2.fillOval(p.x-4, p.y-4, 8, 8);
				}
				
			}
		};
		panel.addMouseMotionListener(new PanelMouseMotionListener());
		panel.setBounds(0, 62, 500, 500);
		getContentPane().add(panel);
		
		textField = new JTextField();
		textField.setBorder(new LineBorder(blue));
		textField.setBounds(12, 10, 347, 34);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		button = new JButton("\uAC70\uB9AC \uD655\uC778");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(371, 10, 116, 34);
		getContentPane().add(button);

		load();
	}
	
	List<Point> plist = new ArrayList<Point>();
	List<String> name = new ArrayList<String>();
	Point selP;
	private void load() {
		try (var rs = res("select * from pharmacy;")) {
			while(rs.next()) {
				var p = new Point(rs.getInt("x"), rs.getInt("y"));
				if(p.distance(me)<=120) {
					plist.add(p);
					name.add(rs.getString(2));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class PanelMouseMotionListener extends MouseMotionAdapter {
		@Override
		public void mouseMoved(MouseEvent e) {
			var p = plist.stream().filter(x->x.distance(e.getPoint())<=4).findAny().orElse(null);
			if(p!=null) {
				selP = p;
			}
			else {
				selP = null;
			}
			repaint();
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			var txt = textField.getText();
			var n= name.stream().filter(x->x.contains(txt)).findAny().orElse(null);
			if(n==null) {
				msgErr("해당 약국이 존재하지 않습니다.");
				return;
			}
			else {
				int idx = name.indexOf(n);
				double dist = me.distance(plist.get(idx));
				msgInfo(myname+"에서 "+n+"까지의 거리: " +String.format("%.1fm", dist));
			}
		}
	}
}
