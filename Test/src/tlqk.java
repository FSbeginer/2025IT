import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class tlqk extends javax.swing.JFrame {

	private JPanel contentPane;
	public JTextField textField;
	public JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tlqk frame = new tlqk();
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
	public tlqk() {
		setDefaultCloseOperation(tlqk.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField() {
			JLabel jl;
			{
				jl = new JLabel("tlqkf");
				getContentPane().setLayout(new BorderLayout());
				getContentPane().add(jl);
			}
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				jl.setVisible(!hasFocus()&&getText().isBlank());
				repaint();
//				if(getText().isBlank())
//					jl.setVisible(true);
//				else
//					jl.setVisible(false);
			}
		};
		textField.setBounds(97, 71, 182, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		button = new JButton("New button");
		button.addMouseListener(new ButtonMouseListener());
		button.addActionListener(new ButtonActionListener());
		button.setEnabled(false);
		button.setBounds(125, 157, 97, 23);
		contentPane.add(button);
	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println(1);
		}
	}
	private class ButtonMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println(1);
		}
	}
}
