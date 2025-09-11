import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
public class I_과학관관리 extends BP {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JTextArea textArea;
	public JButton button;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;
	public JLabel label_6;
	public JTextField textField;
	public JTextField textField_1;
	public JTextField textField_2;
	public JTextField textField_3;
	public JTextField textField_4;
	public JLabel label_7;

	/**
	 * Create the panel.
	 */
	public I_과학관관리() {
		
		label = new JLabel(getIcon("아이콘/이전.png",100,30));
		label.setBounds(12, 10, 111, 46);
		add(label);
		
		label_1 = new JLabel("");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_1.setBounds(95, 72, 407, 240);
		add(label_1);
		
		label_2 = new JLabel("\uD504\uB85C\uADF8\uB7A8 \uC124\uBA85");
		label_2.setBounds(95, 322, 76, 15);
		add(label_2);
		
		textArea = new JTextArea();
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setBounds(95, 347, 847, 100);
		add(textArea);
		
		button = new RoundButton("\uC218\uC815");
		button.setBackground(BF.blue);
		button.setForeground(Color.white);
		button.setBounds(237, 470, 548, 34);
		add(button);
		
		label_3 = new JLabel("\uACFC\uD559\uAD00\uBA85");
		label_3.setBounds(529, 120, 76, 25);
		add(label_3);
		
		label_4 = new JLabel("\uD504\uB85C\uADF8\uB7A8\uBA85");
		label_4.setBounds(529, 151, 76, 25);
		add(label_4);
		
		label_5 = new JLabel("\uC804\uC2DC\uC77C\uC815");
		label_5.setBounds(529, 186, 76, 25);
		add(label_5);
		
		label_6 = new JLabel("\uC804\uC2DC\uC704\uCE58");
		label_6.setBounds(529, 217, 76, 25);
		add(label_6);
		
		textField = new JTextField();
		textField.setBounds(603, 122, 259, 23);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(603, 153, 259, 23);
		add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(603, 186, 122, 23);
		add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(751, 187, 111, 23);
		add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(603, 221, 259, 23);
		add(textField_4);
		
		label_7 = new JLabel("~");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(715, 191, 57, 15);
		add(label_7);

	}
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			JFileChooser jfc = new JFileChooser();
			jfc.setAcceptAllFileFilterUsed(false);
			jfc.setMultiSelectionEnabled(false);
			jfc.setFileFilter(new FileNameExtensionFilter("PNG FIles", "png"));
			jfc.setCurrentDirectory(new File("./datafiles/프로그램"));
			if(jfc.showOpenDialog(null)==jfc.APPROVE_OPTION) {
				
			}
		}
	}
}
