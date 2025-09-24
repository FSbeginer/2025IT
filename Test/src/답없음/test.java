package ´ä¾øÀ½;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import java.awt.event.MouseMotionAdapter;
import java.awt.Color;

public class test extends JFrame {

	private JPanel contentPane;
	public JTextField textField;
	public JTextArea textArea;
	public JPanel panel;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.addMouseMotionListener(new ContentPaneMouseMotionListener());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.addMouseListener(new TextFieldMouseListener());
		textField.setBounds(51, 76, 192, 64);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(70, 172, 201, 52);
		contentPane.add(textArea);
		
		panel = new JPanel();
		panel.setBackground(Color.RED);
		panel.setBounds(259, 53, 99, 83);
		contentPane.add(panel);
	}

	private class TextFieldMouseListener extends MouseAdapter {
		@Override
		public void mouseEntered(MouseEvent e) {
			System.out.println(1);
		}
	}
	private class ContentPaneMouseMotionListener extends MouseMotionAdapter {
		@Override
		public void mouseMoved(MouseEvent e) {
			System.out.println("move ");
		}
	}
}
