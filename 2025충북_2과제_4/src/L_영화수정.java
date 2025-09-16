import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
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
	private int gno;
	private int lno;
	private String otitle;
	private String otxt;
	public L_영화수정(int mno) {
		setTitle("영화수정");
		this.mno = mno;
		setBounds(100, 100, 871, 354);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("");
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(12, 10, 225, 277);
		getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(249, 10, 582, 41);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\uC124\uBA85", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		scrollPane.setBounds(249, 75, 582, 148);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(textArea);
		
		comboBox = new JComboBox();
		comboBox.setBounds(607, 244, 106, 23);
		getContentPane().add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(725, 244, 106, 23);
		getContentPane().add(comboBox_1);
		
		button = new JButton("수정");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(734, 282, 97, 23);
		getContentPane().add(button);

		load();
	}
	private void load() {
		try (var rs = res("select * from genre")) {
			while(rs.next()) {
				comboBox.addItem(rs.getString("g_name"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try (var rs = res("select * from movie_limit")) {
			while(rs.next()) {
				comboBox_1.addItem(rs.getString(2));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try (var rs = res("select * from movie where m_no = "+mno)) {
			rs.next();
			label.setIcon(getIcon("movies/"+mno+".jpg",label.getWidth(),label.getHeight()));
			textField.setText(rs.getString("m_name"));
			textArea.setText(rs.getString("m_plot"));
			comboBox.setSelectedIndex(rs.getInt("g_no")-1);
			comboBox_1.setSelectedIndex(rs.getInt("l_no")-1);
			gno = rs.getInt("g_no");
			lno = rs.getInt("l_no");
			otitle = rs.getString("m_name");
			otxt= rs.getString("m_plot");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			var title=  textField.getText();
			var txt = textArea.getText();
			if(title.isBlank()||txt.isBlank()) {
				msgErr("빈칸이 있습니다.");
				return;
			}
			if(title.equals(otitle)&&txt.equals(otxt)&&lno == comboBox_1.getSelectedIndex()+1&&gno==comboBox.getSelectedIndex()+1) {
				msgErr("수정된 부분이 없습니다.");
				return;
			}
			Pattern p = Pattern.compile("시발|개새끼|존나|병신");
			Matcher m = p.matcher(txt);
			if(m.find()) {
				msgErr("욕설을 포함하고 있습니다.");
				return;
			}
			try {
				var pre = pre("update movie set m_name = ?, m_plot = ?, g_no = ?, l_no = ? where m_no = ? ");
				preSet(pre, title, txt, comboBox.getSelectedIndex()+1, comboBox_1.getSelectedIndex()+1, mno);
				pre.execute();
				msgInfo("정보가 수정되었습니다.");
				dispose();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
