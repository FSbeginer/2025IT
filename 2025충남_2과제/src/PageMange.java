import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PageMange extends BP {
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JTextArea textArea;
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
	public JButton button;

	/**
	 * Create the panel.
	 */
	int lno;
	public PageMange(int lno) {
		setLayout(null);
		setName("과학관 관리");
		this.lno=lno;
		label = new JLabel("");
		label.setBounds(0, 0, 120, 48);
		add(label);
		
		label_1 = new JLabel("");
		label_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_1.setBounds(130, 37, 277, 235);
		add(label_1);
		
		label_2 = new JLabel("\uD504\uB85C\uADF8\uB7A8 \uC124\uBA85");
		label_2.setBounds(130, 296, 107, 15);
		add(label_2);
		
		textArea = new JTextArea();
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBounds(130, 321, 630, 126);
		add(textArea);
		
		label_3 = new JLabel("\uACFC\uD559\uAD00\uBA85");
		label_3.setBounds(464, 52, 77, 34);
		add(label_3);
		
		label_4 = new JLabel("\uD504\uB85C\uADF8\uB7A8\uBA85");
		label_4.setBounds(464, 97, 77, 34);
		add(label_4);
		
		label_5 = new JLabel("\uC804\uC2DC\uC77C\uC815");
		label_5.setBounds(464, 151, 77, 34);
		add(label_5);
		
		label_6 = new JLabel("\uC804\uC2DC\uC704\uCE58");
		label_6.setBounds(464, 206, 77, 34);
		add(label_6);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(552, 56, 190, 27);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(552, 104, 190, 27);
		add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.addMouseListener(new TextField_4MouseListener());
		textField_2.setColumns(10);
		textField_2.setBounds(552, 158, 85, 27);
		add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.addMouseListener(new TextField_3MouseListener());
		textField_3.setColumns(10);
		textField_3.setBounds(552, 213, 190, 27);
		add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.addMouseListener(new TextField_4MouseListener());
		textField_4.setColumns(10);
		textField_4.setBounds(657, 158, 85, 27);
		add(textField_4);
		
		label_7 = new JLabel("-");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(618, 161, 57, 15);
		add(label_7);
		
		button = new RoundButton("\uC218\uC815");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(190, 459, 541, 33);
		add(button);
		
		load();
	}

	int pno, sno;
	LocalDate start, end;
	private void load() {
		try (var rs = res("select p.p_img, s.name sname, p.name pname, l.start_date, l.end_date, l.x, l.y, p.explanation, pno, sno from location l join program p using(pno) join science s using(sno) where lno = "+lno)) {
			rs.next();
			pno = rs.getInt("pno");
			sno = rs.getInt("sno");
			label_1.setIcon(getIcon(rs.getBytes(1), label_1.getWidth(),label_1.getHeight()));
			textField.setText(rs.getString(2));
			textField_1.setText(rs.getString(3));
			textField_2.setText(rs.getString(4));
			textField_4.setText(rs.getString(5));
			textField_3.setText(rs.getString(6)+","+rs.getString(7));
			start = rs.getDate("start_date").toLocalDate();
			end = rs.getDate("end_date").toLocalDate();
			x = rs.getInt(6);
			y = rs.getInt(7);
			textArea.setText(rs.getString(8));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	int x,y;
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try (var pre = pre("update program set p_img = ?,name = ?, start_date = ? and end_date = ?, x = ?, y = ?, explanation = ? where pno = "+pno)) {
				ByteArrayOutputStream bios = new ByteArrayOutputStream();
				Image img = ((ImageIcon)label_1.getIcon()).getImage();
				BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), 2);
				bi.getGraphics().drawImage(img, 0, 0, null);
				try {
					ImageIO.write(bi, "png", bios);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				preSet(pre, bios.toByteArray(), textField_1.getText(), textField_2.getText(), textField_3.getText(), x,y, textArea.getText());
				pre.execute();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	private class TextField_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			((MainFrame)SwingUtilities.getWindowAncestor(PageMange.this)).showPage(new 위치선택(sno, new Point(x,y)), "위치선택");
		}
	}

	private class TextField_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			var c =new CalendarForm(start, end);
			c.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					start =c.start;
					end = c.end;
					textField_2.setText(start.toString());
					textField_4.setText(end.toString());
				}
			});
			((MainFrame)SwingUtilities.getWindowAncestor(PageMange.this)).showPage(c, "CalendarForm");
		}
	}
	
}
