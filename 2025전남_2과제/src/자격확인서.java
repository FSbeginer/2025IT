import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.awt.Color;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.event.ActionEvent;

public class 자격확인서 extends BF {
	public JLabel label;
	public JPanel panel;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;
	public JLabel label_6;
	public JLabel label_7;
	public JButton button;
	public JLabel label_8;
	public JLabel label_9;
	public JLabel label_10;
	public JLabel label_11;
	public JLabel label_12;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					자격확인서 frame = new 자격확인서(2);
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
	int cno;
	public 자격확인서(int cno) {
		this.cno = cno;
		setTitle("자격확인서");
		setBounds(100, 100, 450, 468);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("자격확인서");
		label.setOpaque(true);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(12, 10, 410, 54);
		getContentPane().add(label);
		
		panel = new JPanel();
		panel.setBounds(12, 88, 121, 194);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(5, 0, 0, 0));
		
		label_1 = new JLabel("이름");
		label_1.setForeground(Color.WHITE);
		label_1.setBackground(Color.BLUE);
		label_1.setOpaque(true);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_1);
		
		label_2 = new JLabel("영문명");
		label_2.setForeground(Color.WHITE);
		label_2.setBackground(Color.BLUE);
		label_2.setOpaque(true);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2);
		
		label_3 = new JLabel("주민등록번호");
		label_3.setForeground(Color.WHITE);
		label_3.setBackground(Color.BLUE);
		label_3.setOpaque(true);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_3);
		
		label_4 = new JLabel("자격번호");
		label_4.setForeground(Color.WHITE);
		label_4.setBackground(Color.BLUE);
		label_4.setOpaque(true);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_4);
		
		label_5 = new JLabel("자격명");
		label_5.setForeground(Color.WHITE);
		label_5.setBackground(Color.BLUE);
		label_5.setOpaque(true);
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_5);
		
		label_6 = new JLabel("New label");
		label_6.setBounds(12, 302, 387, 61);
		getContentPane().add(label_6);
		
		label_7 = new JLabel("New label");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(287, 348, 135, 15);
		getContentPane().add(label_7);
		
		button = new JButton("자격증 인쇄");
		button.addActionListener(new ButtonActionListener());
		button.setForeground(Color.WHITE);
		button.setBackground(Color.BLUE);
		button.setBounds(287, 383, 135, 36);
		getContentPane().add(button);
		
		label_8 = new JLabel("김하");
		label_8.setBounds(140, 96, 185, 28);
		getContentPane().add(label_8);
		
		label_9 = new JLabel("New label");
		label_9.setBounds(140, 134, 185, 28);
		getContentPane().add(label_9);
		
		label_10 = new JLabel("New label");
		label_10.setBounds(140, 172, 185, 28);
		getContentPane().add(label_10);
		
		label_11 = new JLabel("New label");
		label_11.setBounds(140, 206, 185, 28);
		getContentPane().add(label_11);
		
		label_12 = new JLabel("New label");
		label_12.setBounds(140, 244, 185, 28);
		getContentPane().add(label_12);

		load();
	}

	private void load() {
		try (var rs = res("select * from certi, user where uno = "+uno+" and cno = "+cno)) {
			rs.next();
			label_8.setText(rs.getString("uname"));
			label_9.setText(rs.getString("ueng"));
			//이렇게 민증 번호 갖고 오지 마셈 안되는거임
			label_10.setText(rs.getString("birth").replaceAll("-", "")+"-"+(rs.getString("gender")=="W"?2:1)+"*******");
			label_11.setText(rs.getString("cnum"));
			label_12.setText(rs.getString("cname")+" "+rs.getInt("ratring")+"급");
			label_6.setText("<html>위와 같이 "+rs.getString("cname")+" "+rs.getInt("ratring")+"급 자격을 취득하였음<br>을 증명함.");
			label_7.setText("2025-06-10");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			PrinterJob job= PrinterJob.getPrinterJob();
			job.setPrintable(new Printable() {
				
				@Override
				public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
					if(pageIndex>0)
						return NO_SUCH_PAGE;
					Graphics2D g2 = (Graphics2D) graphics;
					g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
					자격확인서.this.printAll(g2);
					return 0;
				}
			});
			for (var ser : PrintServiceLookup.lookupPrintServices(null, null)) {
				if(ser.getName().equalsIgnoreCase("microsoft print to pdf")) {
					try {
						job.setPrintService(ser);
					} catch (PrinterException e1) {
						e1.printStackTrace();
					}
				}
			}
			button.setVisible(false);
			if(job.printDialog()) {
				try {
					job.print();
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}
			}
			button.setVisible(true);
		}
	}
}
