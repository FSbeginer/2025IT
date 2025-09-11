import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

public class I_°í°´¼¾ÅÍ extends BF {
	public JPanel panel;
	public JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					I_°í°´¼¾ÅÍ frame = new I_°í°´¼¾ÅÍ();
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
	public I_°í°´¼¾ÅÍ() {
		setTitle("\uACE0\uAC1D\uC13C\uD130");
		setBounds(100, 100, 450, 554);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(12, 10, 410, 462);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		label = new JLabel("\uC9C8\uBB38 \uB4F1\uB85D\uD558\uAE30>");
		label.setFont(new Font("±¼¸²", Font.PLAIN, 14));
		label.setBounds(12, 482, 185, 23);
		getContentPane().add(label);
		
		addQuestion();
	}

	private void addQuestion() {
		try (var rs = res("select * from question;")) {
			while(rs.next()) {
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
