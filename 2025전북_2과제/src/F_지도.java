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
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class F_지도 extends BF {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					F_지도 frame = new F_지도();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	int hno;
	public JLabel label;
	public JTextField textField;
	public JButton button;
	Point base;
	String name;
	List<Point> points = new ArrayList<Point>();
	List<String> names = new ArrayList<>();
	public JLabel label_1;
	public F_지도(int hno) {
		this();
		this.hno = hno;
		getdata("hospital where hno = "+hno);
	} 	
	private void getdata(String sql) {
		try (var rs = res("select * from "+sql)) {
			rs.next();
			base = new Point(rs.getInt("x"),rs.getInt("y"));
			name = rs.getString("name");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = res("select * from pharmacy")) {
			while(rs.next()) {
				var p = new Point(rs.getInt("x"), rs.getInt("y"));
				if(p.distance(base)<=100) {
					points.add(p);
					names.add(rs.getString("name"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public F_지도() {
		setBounds(100, 100, 518, 540+40);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				Image img = BF.getIcon("map.png",500,500).getImage();
				g2.drawImage(img, 0, 0, null);
				
				for (Point p : points) {
					g2.fillOval(p.x-4, p.y-4, 8, 8);
				}
				g2.drawString(name, base.x-15, base.y-10);
				g2.setColor(blue.darker());
				g2.fillOval(base.x-4, base.x-4, 8, 8);
			}
		};
		label.addMouseMotionListener(new LabelMouseMotionListener());
		
		label_1 = new JLabel("");
		label_1.setBounds(127, 209, 205, 15);
		getContentPane().add(label_1);
		label.setBounds(0, 40, 500, 500);
		getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBorder(new LineBorder(blue));
		textField.setBounds(12, 10, 340, 27);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		button = new JButton("\uAC70\uB9AC \uD655\uC778");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(364, 10, 112, 25);
		getContentPane().add(button);
		
		getdata(" user where uno = "+uno);
	}

	private class LabelMouseMotionListener extends MouseMotionAdapter {
		@Override
		public void mouseMoved(MouseEvent e) {
			var p = points.stream().filter(x->x.distance(e.getPoint())<=4).findAny().orElse(null);
			if(p!=null) {
				int idx = points.indexOf(p);
//				label.setToolTipText(names.get(idx));
				label_1.setText(names.get(idx));
			}
			else {
//				label.setToolTipText(null);
				label_1.setText("");
			}
			label_1.setLocation(e.getX()-20, e.getY()+10);
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			var txt = textField.getText();
			if(names.contains(txt)) {
				int idx = names.indexOf(txt);
				Point p = points.get(idx);
				msgInfo(name+"에서 "+txt+"까지의 거리: "+String.format("%.1fm", Math.hypot(p.x-base.x, p.y-base.y)));
			}
			else {
				msgErr("해당 약국이 존재하지 않습니다.");
			}
		}
	}
}
