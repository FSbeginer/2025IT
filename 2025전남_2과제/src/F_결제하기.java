import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class F_결제하기 extends BF {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					F_결제하기 frame = new F_결제하기(1);
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
	int cno= -1, scno = -1;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JPanel panel;
	public JLabel label_3;
	public JButton button;
	public JTextField textField;
	public JTextField textField_1;
	public JTextField textField_2;
	public JTextField textField_3;
	public JTextField textField_4;
	public JTextField textField_5;
	public JTextField textField_6;
	public JLabel label_4;
	public JLabel label_5;
	public JLabel label_6;
	LocalDate date;
	LocalTime time;
	public F_결제하기(int scno, LocalDate date , LocalTime time) {
		this(-1);
		this.scno = scno;
		this.date = date;
		this.time = time;
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public F_결제하기(int cno) {
		setTitle("결제하기");
		this.cno = cno;
		setBounds(100, 100, 744, 404);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("카드번호");
		label.setFont(new Font("굴림", Font.BOLD, 15));
		label.setBounds(28, 10, 122, 54);
		getContentPane().add(label);
		
		label_1 = new JLabel("주민등록번호");
		label_1.setFont(new Font("굴림", Font.BOLD, 15));
		label_1.setBounds(28, 74, 122, 54);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("비밀번호");
		label_2.setFont(new Font("굴림", Font.BOLD, 15));
		label_2.setBounds(28, 152, 122, 54);
		getContentPane().add(label_2);
		
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(77, 238, 594, 1);
		getContentPane().add(panel);
		
		label_3 = new JLabel("New label");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_3.setBounds(12, 257, 704, 32);
		getContentPane().add(label_3);
		
		button = new JButton("결제하기");
		button.addActionListener(new ButtonActionListener());
		button.setFont(new Font("굴림", Font.PLAIN, 15));
		button.setBounds(556, 299, 147, 42);
		getContentPane().add(button);
		
		textField = new JTextField();
		textField.addKeyListener(new TextFieldKeyListener());
		textField.setBounds(143, 22, 99, 32);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new TextField_1KeyListener());
		textField_1.setColumns(10);
		textField_1.setBounds(263, 22, 99, 32);
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.addKeyListener(new TextField_2KeyListener());
		textField_2.setColumns(10);
		textField_2.setBounds(385, 22, 99, 32);
		getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.addKeyListener(new TextField_3KeyListener());
		textField_3.setColumns(10);
		textField_3.setBounds(511, 22, 99, 32);
		getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.addKeyListener(new TextField_4KeyListener());
		textField_4.setBounds(143, 80, 170, 44);
		getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.addKeyListener(new TextField_5KeyListener());
		textField_5.setBounds(358, 79, 35, 46);
		getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.addKeyListener(new TextField_6KeyListener());
		textField_6.setColumns(10);
		textField_6.setBounds(143, 152, 122, 44);
		getContentPane().add(textField_6);
		
		label_4 = new JLabel("-");
		label_4.setFont(new Font("굴림", Font.BOLD, 20));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(308, 94, 57, 15);
		getContentPane().add(label_4);
		
		label_5 = new JLabel("●●●●●●●");
		label_5.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_5.setBounds(405, 74, 203, 45);
		getContentPane().add(label_5);
		
		label_6 = new JLabel("●●");
		label_6.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_6.setBounds(277, 152, 203, 45);
		getContentPane().add(label_6);

		load();
	}

	private void load() {
		if(cno!=-1) {
			try {
				var rs = res("select * from certi left join sale using(cno) where cno = "+cno);
				rs.next();
				int price = rs.getInt("caftprice");
				int sale=0;
				if(!LocalDate.now().isAfter(rs.getDate("sale_end_date").toLocalDate()))
					sale = (int) (price * rs.getInt("sale")/100.0);
				label_3.setText(String.format("<html>원가 %,d원 - <font color = red>할인 가격 %,d</font>원=     <font color = blue>최종결제금액: %,d원", price, sale, price-sale));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(scno!=-1) {
			try {
				var rs = res("select * from schedule join certi using(cno) left join sale using(cno) where scno ="+scno);
				rs.next();
				int price = rs.getInt("ccrprice");
				int sale = 0;
				if(!LocalDate.now().isAfter(rs.getDate("sale_end_date").toLocalDate()))
					sale = (int) (price * rs.getInt("sale")/100.0);
				label_3.setText(String.format("<html>원가 %,d원 - <font color = red>할인 가격 %,d</font>원=     <font color = blue>최종결제금액: %,d원", price, sale, price-sale));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	private class TextFieldKeyListener extends KeyAdapter {
		@Override
		public void keyTyped(KeyEvent e) {
			if(!(Character.isDigit(e.getKeyChar())||e.getKeyCode()==KeyEvent.VK_BACK_SPACE)) {
				e.consume();
			}
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					if(textField.getText().length()==4)
						textField_1.requestFocus();
				}
			});
		}
	}
	private class TextField_1KeyListener extends KeyAdapter {
		@Override
		public void keyTyped(KeyEvent e) {
			if(!(Character.isDigit(e.getKeyChar())||e.getKeyCode()==KeyEvent.VK_BACK_SPACE)) {
				e.consume();
			}
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					if(textField_1.getText().length()==4)
						textField_2.requestFocus();
				}
			});
		}
	}
	private class TextField_2KeyListener extends KeyAdapter {
		@Override
		public void keyTyped(KeyEvent e) {
			if(!(Character.isDigit(e.getKeyChar())||e.getKeyCode()==KeyEvent.VK_BACK_SPACE)) {
				e.consume();
			}
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					if(textField_2.getText().length()==4)
						textField_3.requestFocus();
				}
			});
		}
	}
	private class TextField_3KeyListener extends KeyAdapter {
		@Override
		public void keyTyped(KeyEvent e) {
			if(!(Character.isDigit(e.getKeyChar())||e.getKeyCode()==KeyEvent.VK_BACK_SPACE)) {
				e.consume();
			}
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					if(textField_3.getText().length()==4)
						textField_4.requestFocus();
				}
			});
		}
	}
	private class TextField_5KeyListener extends KeyAdapter {
		@Override
		public void keyTyped(KeyEvent e) {
			int n = e.getKeyChar()-'0';
			if(n<1||n>4) {
				e.consume();
			}
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					if(textField_5.getText().length()>0)
						textField_5.setText(textField_5.getText().substring(0,1));
				}
			});
		}
	}
	private class TextField_6KeyListener extends KeyAdapter {
		@Override
		public void keyTyped(KeyEvent e) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					if(textField_6.getText().length()>=2)
						textField_6.setText(textField_6.getText().substring(0,2));
				}
			});
		}
	}
	private class TextField_4KeyListener extends KeyAdapter {
		@Override
		public void keyTyped(KeyEvent e) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					if(textField_4.getText().length()==6)
						textField_5.requestFocus();
				}
			});
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(isEmpty(textField, textField_1, textField_2, textField_3, textField_4, textField_5, textField_6)) {
				msgErr("빈칸이 있습니다.");
				return;
			}
			if(cno != -1) {
				try {
					var pre = pre("insert into course_registration values(0,?,?,?,'')");
					preSet(pre, cno, uno, LocalDate.now());
					pre.execute();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			else {
				try {
					var pre = pre("insert into test values(0,?,?,?, ?, 0)");
					preSet(pre, cno, uno, date, time);
					pre.execute();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			msgInfo("결제가 완료되었습니다.");
			showPage(new G_나의과정(cno!=-1),"G_나의과정");
		}
	}
	public boolean isEmpty(JTextField...fields) {
		for (JTextField jTextField : fields) {
			if(jTextField.getText().isBlank()) {
				return true;
			}
		}
		return false;
	}
}
