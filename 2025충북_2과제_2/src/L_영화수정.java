import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;

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
	public JButton button;
	public JComboBox comboBox;
	public JComboBox comboBox_1;
	public L_영화수정(int mno) {
		setTitle("영화수정");
		this.mno= mno;
		setBounds(100, 100, 903, 424);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("");
		label.setBounds(12, 10, 241, 314);
		getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(275, 10, 583, 47);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\uC124\uBA85", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		scrollPane.setBounds(275, 82, 583, 194);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollPane.setViewportView(textArea);
		
		button = new JButton("수정");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(746, 333, 105, 31);
		getContentPane().add(button);
		
		comboBox = new JComboBox();
		comboBox.setBounds(739, 286, 112, 31);
		getContentPane().add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(615, 286, 112, 31);
		getContentPane().add(comboBox_1);
		
		additem();
		load();
	}
	String ori1, ori2;
	int gno,lno;
	private void load() {
		try (var rs = res("select * from movie where m_no = "+mno)) {
			rs.next();
			label.setIcon(getIcon("movies/"+mno+".jpg",label.getWidth(),label.getHeight()));
			textField.setText(rs.getString("m_name"));
			textArea.setText(rs.getString("m_plot"));
			ori1=  textField.getText();
			ori2 = textArea.getText();
			comboBox.setSelectedIndex(rs.getInt("l_no")-1);
			comboBox_1.setSelectedIndex(rs.getInt("g_no")-1);
			gno = rs.getInt("g_no");
			lno = rs.getInt("l_no");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void additem() {
		try (var rs = res("select * from genre")) {
			while(rs.next())
				comboBox_1.addItem(rs.getString("g_name"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = res("select * from movie_limit")) {
			while(rs.next()) {
				comboBox.addItem(rs.getString("l_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			var name = textField.getText();
			var txt = textArea.getText();
			int cmb1 = comboBox_1.getSelectedIndex()+1;
			int cmb2 = comboBox.getSelectedIndex()+1;
			if(name.isBlank()||txt.isBlank()) {
				msgErr("빈칸이 있습니다.");
				return;
			}
			if(name.equals(ori1)&&txt.equals(ori2)&&cmb1==gno&&cmb2==lno) {
				msgErr("수정된 부분이 없습니다.");
				return;
			}
			if(txt.contains("시발")||txt.contains("개새끼")||txt.contains("존나")||txt.contains("병신")) {
				msgErr("욕설을 포함하고 있습니다.");
				return;
			}
			try (var pre = pre("update movie set m_name = ?, l_no = ?,m_plot = ?, g_no = ? where m_no = ?")) {
				preSet(pre, name,cmb2,txt,cmb1,mno);
				pre.execute();
				msgInfo("정보가 수정되었습니다.");
				dispose();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
