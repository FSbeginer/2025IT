import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class L_영화수정 extends BF {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					L_영화수정 frame = new L_영화수정(1);
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
	public JLabel label;
	public JTextField textField;
	public JScrollPane scrollPane;
	public JTextArea textArea;
	public JComboBox comboBox;
	public JComboBox comboBox_1;
	public JButton button;
	private String name;
	private String txt;
	private int gno;
	private int lno;
	public L_영화수정(int mno) {
		setTitle("영화수정");
		this.mno = mno;
		setBounds(100, 100, 736, 396);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("");
		label.setBounds(12, 10, 205, 264);
		getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(229, 10, 476, 33);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBorder(new TitledBorder(null, "\uC124\uBA85", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(229, 53, 476, 217);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(textArea);
		
		comboBox = new JComboBox();
		comboBox.setBounds(483, 280, 105, 33);
		getContentPane().add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(600, 280, 105, 33);
		getContentPane().add(comboBox_1);
		
		button = new JButton("수정");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(608, 323, 97, 23);
		getContentPane().add(button);
		
		addCAte();
		load();
	}
	private void addCAte() {
		try {
			var rs=res("select * from genre");
			while(rs.next()) {
				comboBox.addItem(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			var rs =res("select * from movie_limit");
			while(rs.next())
				comboBox_1.addItem(rs.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void load() {
		try (var rs = res("select * from movie where m_no = "+mno)) {
			rs.next();
			name =rs.getString("m_name");
			txt =rs.getString("m_plot");
			textField.setText(name);
			textArea.setText(txt);
			label.setIcon(getIcon("movies/"+mno+".jpg",label.getWidth(),label.getHeight()));
			gno = rs.getInt("g_no");
			lno = rs.getInt("l_no");
			comboBox.setSelectedIndex(rs.getInt("g_no")-1);
			comboBox_1.setSelectedIndex(rs.getInt("l_no")-1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			var t1 = textField.getText();
			var t2 = textArea.getText();
			int ngno = comboBox.getSelectedIndex()+1;
			int nlno = comboBox_1.getSelectedIndex()+1;
			if(t1.isBlank()||t2.isBlank()) {
				msgErr("빈칸이 있습니다.");
				return;
			}
			if(t1.equals(name)&&t2.equals(txt)&&gno==ngno&&lno==nlno) {
				msgErr("수정된 부분이 없습니다.");
				return;
			}
			if(t2.matches("^.*(시발|개새끼|존나|병신)+.*")) {
				msgErr("욕설을 포함하고 있습니다.");
				return;
			}
			try (var pre = pre("update movie set m_name = ?, g_no = ?, l_no = ?, m_plot =? where m_no = ?")) {
				preSet(pre, t1, ngno, nlno, t2, mno);
				pre.execute();
				msgInfo("정보가 수정되었습니다.");
				dispose();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
