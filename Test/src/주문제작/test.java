package 주문제작;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Point;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class test extends JFrame {

	private JPanel contentPane;
	Point stp;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
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
	public test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		//드래그
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				stp = e.getPoint();
			}
		});
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int dy = e.getY() - stp.y;
				panel.setLocation(0, panel.getY() + dy);
			}
		});
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 310, 386);
		contentPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		for(int i = 0; i < 10; i++) {
			QuestionControl control = new QuestionControl();
			panel.add(control);
			control.setMinimumSize(new Dimension(0,60));
		}
		
	}
	
	public static void SetPreferredSize(Component component, int height) {
		Dimension d = new Dimension(component.getWidth(), height);
		component.setMinimumSize(d);
		component.setPreferredSize(d);
		component.revalidate();
		
		var parent = component.getParent();
		Dimension pref = parent.getPreferredSize();
		parent.setSize(pref.width, pref.height);
	}
}
