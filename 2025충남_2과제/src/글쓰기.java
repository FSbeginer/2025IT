import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.MatteBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class ±Û¾²±â extends BF {
	public JLabel label;
	public JComboBox comboBox;
	public JTextField textField;
	public JTextArea textArea;
	public JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					±Û¾²±â frame = new ±Û¾²±â();
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
	public ±Û¾²±â() {
		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		setBounds(100, 100, 450, 419);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("    \uD3F0\uD2B8");
		label.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		label.setBackground(Color.WHITE);
		label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
		label.setBounds(0, 0, 434, 51);
		getContentPane().add(label);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uB9D1\uC740 \uACE0\uB515", "\uAD81\uC11C", "\uAD74\uB9BC"}));
		comboBox.setBounds(59, 13, 152, 29);
		getContentPane().add(comboBox);
		
		textField = new PlaceHolder("Á¦¸ñ");
		textField.setBorder(new LineBorder(new Color(0, 0, 0)));
		textField.setBounds(28, 61, 371, 29);
		getContentPane().add(textField);
		textField.setColumns(10);
		JLabel jl = new JLabel("³»¿ë", JLabel.LEFT);
		jl.setForeground(Color.LIGHT_GRAY);
		jl.setVerticalAlignment(SwingConstants.TOP);
		textArea = new JTextArea() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				jl.setVisible(getText().isBlank());
			}
		};
		textArea.setLayout(new BorderLayout());
		textArea.add(jl);
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setLineWrap(true);
		textArea.setBounds(28, 98, 371, 231);
		getContentPane().add(textArea);
		
		button = new RoundButton("\uB4F1\uB85D");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(28, 339, 371, 31);
		getContentPane().add(button);

		textField.setFont(new Font("¸¼Àº °íµñ", 0, 14));
		textArea.setFont(new Font("¸¼Àº °íµñ", 0, 14));
		
	}
	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(comboBox.getSelectedIndex()==0) {
				textField.setFont(new Font("¸¼Àº °íµñ", 0, 14));
				textArea.setFont(new Font("¸¼Àº °íµñ", 0, 14));
				repaint();
			}
			else if(comboBox.getSelectedIndex()==1) {
				textField.setFont(new Font("±Ã¼­", 0, 14));
				textArea.setFont(new Font("±Ã¼­", 0, 14));
				repaint();
			}
			else {
				textField.setFont(new Font("±¼¸²", 0, 14));
				textArea.setFont(new Font("±¼¸²", 0, 14));
				repaint();
			}
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try (var pre = pre("insert into community values(0,?,?,?,?,0,?)")) {
				preSet(pre, BF.uno, textField.getText(), textArea.getText(), LocalDate.now(), comboBox.getSelectedIndex());
				pre.execute();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			dispose();
		}
	}
}
