package 풀이본;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.PrintJob;

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					자격확인서 frame = new 자격확인서(16);
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
	public JLabel label;
	public JPanel panel;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;
	public JPanel panel_1;
	public JLabel label_6;
	public JLabel label_7;
	public JLabel label_8;
	public JLabel label_9;
	public JLabel label_10;
	public JLabel label_11;
	public JLabel label_12;
	public JButton button;
	public 자격확인서(int cno) {
		setTitle("자격확인서");
		this.cno = cno;
		setBounds(100, 100, 450, 546);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("자격확인서");
		label.setOpaque(true);
		label.setBackground(SystemColor.control);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(12, 10, 410, 55);
		getContentPane().add(label);
		
		panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(12, 94, 135, 192);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(5, 0, 0, 0));
		
		label_1 = new JLabel("이름");
		label_1.setForeground(Color.WHITE);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_1);
		
		label_2 = new JLabel("영문명");
		label_2.setForeground(Color.WHITE);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2);
		
		label_3 = new JLabel("주민등록번호");
		label_3.setForeground(Color.WHITE);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_3);
		
		label_4 = new JLabel("자격번호");
		label_4.setForeground(Color.WHITE);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_4);
		
		label_5 = new JLabel("자격명");
		label_5.setForeground(Color.WHITE);
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_5);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(159, 94, 252, 192);
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(5, 0, 0, 0));
		
		label_6 = new JLabel("New label");
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		label_6.setForeground(Color.BLACK);
		panel_1.add(label_6);
		
		label_7 = new JLabel("New label");
		label_7.setHorizontalAlignment(SwingConstants.LEFT);
		label_7.setForeground(Color.BLACK);
		panel_1.add(label_7);
		
		label_8 = new JLabel("New label");
		label_8.setHorizontalAlignment(SwingConstants.LEFT);
		label_8.setForeground(Color.BLACK);
		panel_1.add(label_8);
		
		label_9 = new JLabel("New label");
		label_9.setHorizontalAlignment(SwingConstants.LEFT);
		label_9.setForeground(Color.BLACK);
		panel_1.add(label_9);
		
		label_10 = new JLabel("New label");
		label_10.setHorizontalAlignment(SwingConstants.LEFT);
		label_10.setForeground(Color.BLACK);
		panel_1.add(label_10);
		
		label_11 = new JLabel("New label");
		label_11.setBounds(12, 296, 398, 55);
		getContentPane().add(label_11);
		
		label_12 = new JLabel("New label");
		label_12.setBounds(289, 408, 116, 15);
		getContentPane().add(label_12);
		
		button = new JButton("자격증 인쇄");
		button.addActionListener(new ButtonActionListener());
		button.setBackground(Color.BLUE);
		button.setForeground(Color.WHITE);
		button.setBounds(272, 445, 150, 31);
		getContentPane().add(button);

		load();
	}
	private void load() {
		try (var rs = res("select * from certi join test using(cno) join user using(uno) where uno ="+uno+" and cno="+cno)) {
			rs.next();
			label_6.setText(rs.getString("uname"));
			label_7.setText(rs.getString("ueng"));
			label_8.setText(getIDNumber(rs.getDate("birth").toLocalDate(), rs.getString("gender"))+"******");
			label_9.setText(rs.getString("cnum"));
			label_10.setText(rs.getString("cname")+" "+rs.getString("ratring")+"급");
			label_11.setText("<html>위와 같이 "+rs.getString("cname")+" "+rs.getString("ratring")+"급 자격을 취득하였음<br>을 증명함.");
			label_12.setText(LocalDate.now()+"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			PrinterJob job = PrinterJob.getPrinterJob();
			for (var ser : PrintServiceLookup.lookupPrintServices(null, null)) {
				if(ser.getName().equalsIgnoreCase("microsoft print to pdf")) {
					try {
						job.setPrintService(ser);
					} catch (PrinterException e1) {
						e1.printStackTrace();
					}
					break;
				}
			}
			job.setPrintable(new Printable() {
				
				@Override
				public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
					if(pageIndex > 0) {
						return NO_SUCH_PAGE;
					}
					Graphics2D g2 =(Graphics2D) graphics;
					g2.translate(-pageFormat.getImageableX(), -pageFormat.getImageableY());
					자격확인서.this.printAll(g2);
					return 0;
				}
			});
			if(job.printDialog()) {
				button.setVisible(false);
				try {
					job.print();
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}
				button.setVisible(true);
			}
		}
	}
}
