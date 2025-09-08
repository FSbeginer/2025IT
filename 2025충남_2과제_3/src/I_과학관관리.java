import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Image;

import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
public class I_과학관관리 extends BP {

	/**
	 * Create the panel.
	 */
	int pno, lno, sno;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JTextArea textArea;
	public JButton button;
	public JLabel label_3;
	public JTextField textField;
	public JLabel label_4;
	public JTextField textField_1;
	public JLabel label_5;
	public JTextField textField_2;
	public JLabel label_6;
	public JTextField textField_3;
	public JTextField textField_4;
	public JLabel label_7;
	public I_과학관관리(int pno, int lno, int sno) {
		this.pno = pno;
		this.lno = lno;
		this.sno = sno;
		label = new JLabel(getIcon("아이콘/이전.png",70,30));
		label.addMouseListener(new LabelMouseListener());
		label.setBounds(12, 10, 106, 59);
		add(label);
		
		label_1 = new JLabel("");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_1.setBounds(78, 79, 364, 234);
		add(label_1);
		
		label_2 = new JLabel("\uD504\uB85C\uADF8\uB7A8 \uC124\uBA85");
		label_2.setBounds(78, 324, 126, 15);
		add(label_2);
		
		textArea = new JTextArea();
		textArea.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBounds(78, 349, 779, 96);
		add(textArea);
		
		button = new RoundButton("\uC218\uC815");
		button.setBounds(278, 455, 467, 31);
		button.setForeground(Color.white);
		button.setBackground(BF.blue);
		add(button);
		
		label_3 = new JLabel("\uACFC\uD559\uAD00\uBA85");
		label_3.setBounds(476, 112, 98, 20);
		add(label_3);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(567, 107, 237, 31);
		add(textField);
		textField.setColumns(10);
		
		label_4 = new JLabel("\uD504\uB85C\uADF8\uB7A8\uBA85");
		label_4.setBounds(476, 153, 98, 20);
		add(label_4);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(567, 148, 237, 31);
		add(textField_1);
		
		label_5 = new JLabel("\uC804\uC2DC\uC77C\uC815");
		label_5.setBounds(476, 194, 98, 20);
		add(label_5);
		
		textField_2 = new JTextField();
		textField_2.addMouseListener(new TextField_2MouseListener());
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setColumns(10);
		textField_2.setBounds(567, 189, 106, 31);
		add(textField_2);
		
		label_6 = new JLabel("\uC804\uC2DC\uC704\uCE58");
		label_6.setBounds(476, 235, 98, 20);
		add(label_6);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setColumns(10);
		textField_3.setBounds(567, 230, 237, 31);
		add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.addMouseListener(new TextField_4MouseListener());
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setColumns(10);
		textField_4.setBounds(698, 189, 106, 31);
		add(textField_4);
		
		label_7 = new JLabel("-");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(654, 197, 57, 15);
		add(label_7);
		load();
	}

	private void load() {
		try (var rs = res("select p_img,s.name sn, p.name pn, start_date ,end_date, x,y, p.explanation pex from location l join program p using(pno) join science s using(sno) where sno = "+sno+" and pno = "+pno +" and lno = "+lno)) {
			if(rs.next()) {
				label_1.setIcon(getIcon(rs.getBytes(1),label_1.getWidth(),label_1.getHeight()));
				textField.setText(rs.getString(2));
				textField_1.setText(rs.getString(3));
				textField_2.setText(rs.getString(4));
				textField_4.setText(rs.getString(5));
				textField_3.setText(rs.getString(6)+","+rs.getString(7));
				textArea.setText(rs.getString("pex"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class LabelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			((MainFrame)SwingUtilities.getWindowAncestor(label)).showPage(BF.prevPage.pop(), BF.prevName.pop());
		}
	}
	
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			JFileChooser jfc = new JFileChooser();
			jfc.setCurrentDirectory(new File("./datafiles/프로그램"));
			jfc.setMultiSelectionEnabled(false);
			jfc.setAcceptAllFileFilterUsed(false);
			jfc.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));
			if(jfc.showOpenDialog(null)==jfc.APPROVE_OPTION) {
				Image img;
				try {
					img = ImageIO.read(jfc.getSelectedFile());
					label_1.setIcon(new ImageIcon(img.getScaledInstance(label_1.getWidth(), label_1.getHeight(), 1)));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	private class TextField_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			((MainFrame)SwingUtilities.getWindowAncestor(button)).showPage(new  K_일정선택(), "K_일정선택");
		}
	}
	private class TextField_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			((MainFrame)SwingUtilities.getWindowAncestor(button)).showPage(new  K_일정선택(), "K_일정선택");
		}
	}
}
