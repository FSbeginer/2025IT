import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class 답변 extends BF {
	public JLabel label;
	public JTextArea textArea;
	public JLabel label_1;
	public JTextArea textArea_1;
	public JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					답변 frame = new 답변(2);
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
	int ino;
	public 답변(int ino) {
		this.ino = ino;
		setBounds(100, 100, 600, 574);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new BufferedImage(100, 100, 2));
		getContentPane().setLayout(null);
		
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setBounds(12, 10, 264, 221);
		getContentPane().add(label);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setBounds(311, 10, 257, 221);
		getContentPane().add(textArea);
		
		label_1 = new JLabel("\uB2F5\uBCC0");
		label_1.setBounds(12, 241, 57, 15);
		getContentPane().add(label_1);

		JLabel jl = new JLabel("현재 답변이 없습니다.");
		jl.setVerticalAlignment(SwingConstants.TOP);
		textArea_1 = new JTextArea() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				jl.setVisible(getText().isBlank());
			}
		};
		textArea_1.setForeground(Color.red);
		textArea_1.setLayout(new BorderLayout());
		textArea_1.add(jl);
		textArea_1.setLineWrap(true);
		textArea_1.setWrapStyleWord(true);
		textArea_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea_1.setBounds(12, 266, 560, 198);
		getContentPane().add(textArea_1);
		
		button = new JButton("\uD655\uC778");
		button.setForeground(Color.WHITE);
		button.setBackground(Color.BLUE);
		button.setBounds(390, 474, 178, 32);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isAdmin) {
					try {
						execute("update inquiry set answer = '"+textArea_1.getText()+"' where ino = "+ino);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					msgInfo("답변이 제출되었습니다.");
					dispose();
				}
			}
		});
		getContentPane().add(button);
		load();
	}
	private void load() {
		try (var rs = res("select * from inquiry where ino = "+ino)) {
			rs.next();
			label.setIcon(getIcon("Error/"+rs.getInt("iimg")+".png",label.getWidth(),label.getHeight()));
			textArea.setText(rs.getString("icontent"));
			textArea_1.setText(rs.getString("answer"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
