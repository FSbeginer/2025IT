import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MovieModify extends BF {
	public JLabel label;
	public JTextField textField;
	public JScrollPane scrollPane;
	public JTextArea textArea;
	public JComboBox comboBox;
	public JComboBox comboBox_1;
	public JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovieModify frame = new MovieModify(1);
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
	int mno;
	private int gno;
	private int lno;
	private String title;
	private String text;
	public MovieModify(int mno) {
		this.mno = mno;
		setTitle("영화수정");
		setBounds(100, 100, 816, 420);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("");
		label.setBounds(12, 33, 179, 288);
		getContentPane().add(label);
		
		textField = new JTextField();
		textField.setFont(new Font("굴림", Font.PLAIN, 14));
		textField.setBounds(203, 10, 564, 44);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBorder(new TitledBorder(null, "\uC124\uBA85", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(203, 64, 564, 229);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(textArea);
		
		comboBox = new JComboBox();
		comboBox.setBounds(526, 303, 115, 32);
		getContentPane().add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(653, 303, 115, 32);
		getContentPane().add(comboBox_1);
		
		button = new JButton("수정");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(663, 345, 104, 26);
		getContentPane().add(button);

		load();
	}

	private void load() {
		try (var rs = res("select * from movie where m_no = "+mno)) {
			label.setIcon(getIcon("movies/"+mno+".jpg"));
			textField.setText(rs.getString("m_name"));
			textArea.setText(rs.getString("m_plot"));
			title = rs.getString("m_name"); 
			text = rs.getString("m_plot");
			gno = rs.getInt("g_no");
			lno = rs.getInt("l_no");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = res("select * from genre")) {
			while(rs.next()) {
				comboBox.addItem(rs.getString("g_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = res("select * from movie_limit")) {
			while(rs.next()) {
				comboBox_1.addItem(rs.getString("l_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		comboBox.setSelectedIndex(gno-1);
		comboBox_1.setSelectedIndex(lno-1);
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int cmb1 = comboBox.getSelectedIndex()+1;
			int cmb2 = comboBox_1.getSelectedIndex()+1;
			var txt1 = textField.getText();
			var txt2 = textArea.getText();
			if(txt1.isBlank()||txt2.isBlank()) {
				msgErr("빈칸이 있습니다.");
				return;
			}
			if(cmb1==gno&&cmb2==lno&&txt1.equals(title)&&txt2.equals(text)) {
				msgErr("수정된 부분이 없습니다.");
				return;
			}
			if(욕있음(txt1)&&욕있음(txt2)) {
				msgErr("욕설을 포함하고 있습니다.");
				return;
			}
			try (var pre = pre("update movie set m_name = ?, m_plot =?, l_no = ?, g_no = ?")) {
				preSet(pre, txt1,txt2,cmb2,cmb1);
				pre.execute();
				msgInfo("정보가 수정되었습니다.");
				dispose();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		private boolean 욕있음(String txt1) {
			var 욕 = "시발 개새끼 존나 병신".split(" ");
			for (int i = 0; i < 욕.length; i++) {
				if(txt1.contains(욕[i])) {
					return true;
				}
			}
			return false;
		}
	}
}
