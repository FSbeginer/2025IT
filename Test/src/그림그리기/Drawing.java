package 그림그리기;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class Drawing extends JFrame {

	private JPanel contentPane;
	public JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Drawing frame = new Drawing();
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
	public Drawing() {
		setTitle("\uC608\uC57D \uB0B4\uC5ED");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 529, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				for (List<Point> list : plist) {
					for (int i = 0; i < list.size()-1; i++) {
						g.drawLine(list.get(i).x, list.get(i).y, list.get(i+1).x, list.get(i+1).y);
					}
				}
			}
		};
		panel.addMouseListener(new PanelMouseListener());
		panel.addMouseMotionListener(new PanelMouseMotionListener());
		contentPane.add(panel);
		panel.setLayout(null);
		
	}

	
	List<List<Point>> plist = new ArrayList<List<Point>>();
	int idx = 0;
	private class PanelMouseMotionListener extends MouseMotionAdapter {
		@Override
		public void mouseDragged(MouseEvent e) {
			plist.get(idx).add(e.getPoint());
			repaint();
		}
	}
	private class PanelMouseListener extends MouseAdapter {
		@Override
		public void mouseReleased(MouseEvent e) {
			idx ++;
		}
		@Override
		public void mousePressed(MouseEvent e) {
			plist.add(new ArrayList<Point>());
		}
	}
}
