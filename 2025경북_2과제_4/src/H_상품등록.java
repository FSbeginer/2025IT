import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class H_상품등록 extends BF {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					H_상품등록 frame = new H_상품등록(1);
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
	int pno;
	public JLabel label;
	public JLabel label_1;
	public JTextField textField;
	public JButton button;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;
	public JTextField textField_1;
	public JTextField textField_2;
	public JTextField textField_3;
	public JComboBox comboBox;

	public H_상품등록(int pno) {
		setTitle("\uC0C1\uD478");
		this.pno = pno;
		setBounds(100, 100, 384, 498);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		label = new JLabel("");
		label.addMouseListener(new LabelMouseListener());
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(12, 10, 340, 222);
		getContentPane().add(label);

		label_1 = new JLabel("\uCE74\uD14C\uACE0\uB9AC");
		label_1.setBounds(12, 242, 57, 25);
		getContentPane().add(label_1);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(81, 274, 271, 31);
		getContentPane().add(textField);

		button = new JButton("\uAD6C\uB9E4");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(12, 417, 340, 31);
		getContentPane().add(button);
		
		label_2 = new JLabel("\uC0C1\uD488\uBA85");
		label_2.setBounds(12, 277, 57, 25);
		getContentPane().add(label_2);
		
		label_3 = new JLabel("\uC124\uBA85");
		label_3.setBounds(12, 312, 57, 25);
		getContentPane().add(label_3);
		
		label_4 = new JLabel("\uAC00\uACA9");
		label_4.setBounds(12, 347, 57, 25);
		getContentPane().add(label_4);
		
		label_5 = new JLabel("\uC218\uB7C9:");
		label_5.setBounds(12, 382, 57, 25);
		getContentPane().add(label_5);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(81, 309, 271, 31);
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(81, 341, 271, 31);
		getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(81, 376, 271, 31);
		getContentPane().add(textField_3);
		
		comboBox = new JComboBox();
		comboBox.setBounds(81, 242, 271, 25);
		getContentPane().add(comboBox);

		addCate();
		if (pno == 0) {
			button.setText("등록");
			setTitle("상품등록");
		} else {
			button.setText("수정");
			setTitle("상품수정");
			load();
		}
		
	}

	private void addCate() {
		try (var rs = res("select * from category")) {
			while(rs.next()) {
				comboBox.addItem(rs.getString("cnam"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		comboBox.setSelectedIndex(-1);
	}
	
	byte[] data;
	private void load() {
		try (var rs = res("select * from product where pno ="+pno)) {
			while(rs.next()) {
				label.setIcon(getIcon(rs.getBytes("img"),label.getWidth(),label.getHeight()));
				comboBox.setSelectedIndex(rs.getInt("cno")-1);
				textField.setText(rs.getString("pname"));
				textField_1.setText(rs.getString("description"));
				textField_2.setText(rs.getString("price"));
				textField_3.setText(rs.getString("quantity"));
				data = rs.getBytes("img");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class LabelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			JFileChooser jfc=  new JFileChooser();
			jfc.setMultiSelectionEnabled(false);
			jfc.setAcceptAllFileFilterUsed(false);
			jfc.setFileFilter(new FileNameExtensionFilter("jpg images", "jpg"));
			if(jfc.showOpenDialog(null)==0) {
				try {
					data = Files.readAllBytes(jfc.getSelectedFile().toPath());
					label.setIcon(getIcon(data,label.getWidth(),label.getHeight()));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			var name =textField.getText();
			var desc = textField_1.getText();
			var input = textField_2.getText();
			var input2 = textField_3.getText();
			if(name.isBlank()||desc.isBlank()||input.isBlank()||input2.isBlank()||comboBox.getSelectedIndex()==-1) {
				msgErr("빈칸이 있습니다.");
				return;
			}
			
			try {
				int price = Integer.parseInt(input), cnt = Integer.parseInt(input2);
				if(pno==0) {
					try {
						var pre = pre("insert into product values (0,?,?,?,?,?,?)");
						preSet(pre, name, desc, price, cnt, comboBox.getSelectedIndex()+1, data);
						pre.execute();
						msgInfo("등록이 완료되었습니다.");
						dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				else {
					try {
						var pre = pre("update product set pname =?, description =?, price = ?, quantity = ?, cno = ?, img = ? where pno = ?");
						preSet(pre, name, desc, price, cnt, comboBox.getSelectedIndex()+1, data, pno);
						pre.execute();
						msgInfo("수정이 완료되었습니다.");
						dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
			} catch (NumberFormatException e1) {
				msgErr("가격과 수량을 1이상의 숫자로 입력하세요.");
				return;
			}
		}
	}
}
