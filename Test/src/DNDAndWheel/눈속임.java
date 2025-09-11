package DNDAndWheel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class 传加烙 extends JFrame {

	private JPanel contentPane;
	public JScrollPane scrollPane;
	public JPanel panel;
	public JLabel label;
	public JPanel panel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					传加烙 frame = new 传加烙();
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
	public 传加烙() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 555, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 10, 534, 414);
		contentPane.add(scrollPane);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 800));
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		label = new JLabel("\uC6C0\uC9C0\uAE30\uB0B4");
		label.setBounds(32, 87, 349, 197);
		panel.add(label);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.RED);
		panel_1.setBounds(12, 361, 409, 216);
		panel.add(panel_1);
	}
}
