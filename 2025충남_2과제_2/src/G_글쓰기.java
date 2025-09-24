import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class G_±Û¾²±â extends BF {
	public JPanel panel;
	public JLabel label;
	public JComboBox comboBox;
	public JTextField textField;
	public JTextArea textArea;
	public JButton button;
	public JLabel label_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					G_±Û¾²±â frame = new G_±Û¾²±â();
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
	public G_±Û¾²±â() {
		setTitle("\uAE00\uC4F0\uAE30");
		setBounds(100, 100, 450, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label_1 = new JLabel("\uB0B4\uC6A9");
		label_1.setForeground(Color.GRAY);
		label_1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 13));
		label_1.setBounds(35, 105, 47, 29);
		getContentPane().add(label_1);
		
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 49, 434, 1);
		getContentPane().add(panel);
		
		label = new JLabel("\uD3F0\uD2B8");
		label.setBounds(43, 10, 48, 29);
		getContentPane().add(label);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uB9D1\uC740 \uACE0\uB515", "\uAD81\uC11C", "\uAD74\uB9BC"}));
		comboBox.setBounds(103, 10, 111, 29);
		getContentPane().add(comboBox);
		
		textField = new PlaceHolder("Á¦¸ñ");
		textField.setBounds(25, 60, 381, 35);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textArea = new JTextArea() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if(getText().isBlank())
					label_1.setVisible(true);
				else
					label_1.setVisible(false);
			}
		};
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBounds(25, 105, 381, 246);
		getContentPane().add(textArea);
		
		button = new RoundButton("\uB4F1\uB85D");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(25, 361, 381, 40);
		getContentPane().add(button);
		comboBox.setSelectedIndex(0);

	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			var title = textField.getText();
			var txt = textArea.getText();
			try (var pre = pre("insert into community values(0,?,?,?,?,0,?)")) {
				preSet(pre, uno, title,txt, LocalDate.now(), comboBox.getSelectedIndex());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if( comboBox.getSelectedIndex()==0) {
				textArea.setFont(new Font("¸¼Àº °íµñ", 1, 13));
				textField.setFont(new Font("¸¼Àº °íµñ", 1, 13));
			}
			else if(comboBox.getSelectedIndex()==1) {
				textArea.setFont(new Font("±Ã¼­", 1, 13));
				textField.setFont(new Font("±Ã¼­", 1, 13));
			}
			else {
				textArea.setFont(new Font("±¼¸²", 1, 13));
				textField.setFont(new Font("±¼¸²", 1, 13));
			}
			
		}
	}
}
