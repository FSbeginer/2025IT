import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;

public class B_메인 extends BF {

	private JPanel contentPane;
	public JLabel label;
	public JTextField textField;
	public JButton button;
	public JPanel panel;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JPanel panel_1;
	public JLabel label_5;
	public JLabel label_6;
	public JScrollPane scrollPane;
	public JPanel panel_2;
	public JLabel label_7;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					B_메인 frame = new B_메인();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public B_메인() {
		setTitle("\uBA54\uC778");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 516, 631);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label = new JLabel("Medinow");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 500, 70);
		contentPane.add(label);
		
		textField = new LineTextField();
		textField.setBounds(58, 57, 337, 43);
		contentPane.add(textField);
		textField.setColumns(10);
		
		button = new JButton("\uAC80\uC0C9");
		button.setBounds(403, 57, 65, 43);
		contentPane.add(button);
		
		panel = new JPanel();
		panel.setBounds(41, 100, 428, 51);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 4, 0, 0));
		
		label_1 = new MyLabel("\uB9C8\uC774\uD648");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_1);
		
		label_2 = new MyLabel("\uACE0\uAC1D\uC13C\uD130");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2);
		
		label_3 = new MyLabel("\uBD84\uC11D");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_3);
		
		label_4 = new MyLabel("\uC9C0\uB3C4");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_4);
		
		panel_1 = new JPanel();
		panel_1.setBounds(12, 172, 476, 70);
		contentPane.add(panel_1);
		
		label_5 = new JLabel("\uBCD1\uC6D0");
		label_5.setBounds(22, 147, 55, 15);
		contentPane.add(label_5);
		
		label_6 = new JLabel("\uC9C4\uB8CC \uACFC\uBAA9");
		label_6.setBounds(22, 260, 55, 15);
		contentPane.add(label_6);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 285, 476, 219);
		contentPane.add(scrollPane);
		
		panel_2 = new JPanel();
		scrollPane.setViewportView(panel_2);
		panel_2.setLayout(null);
		
		label_7 = new JLabel("\uC99D\uC0C1");
		label_7.setBounds(20, 509, 57, 25);
		contentPane.add(label_7);
	}
	
}
class MyLabel extends JLabel{
	boolean isEnter = false;
	public MyLabel(String txt) {
		super(txt);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				isEnter = true;
				repaint();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				isEnter = false;
				repaint();
			}
		});
	}
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(BF.blue);
		int x = getWidth()/2-25;
		if(isEnter)
			g.fillOval(x, 0, 50, 50);
		super.paintComponent(g);
	}
}
