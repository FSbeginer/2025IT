import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class A_메인 extends BF {
	public JLabel label;
	public JLabel label_1;
	public JTextField textField;
	public JLabel label_2;
	public JPanel panel;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;
	public JLabel label_6;
	public JPanel panel_1;
	public JPanel panel_2;
	public JPanel panel_3;
	public JButton button;
	public JButton button_1;
	public JPanel panel_4;
	public JButton button_2;
	public JButton button_3;
	public JPanel panel_5;
	public JLabel label_7;
	public JScrollPane scrollPane;
	public JPanel panel_6;
	public JPanel panel_7;
	public JLabel label_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					A_메인 frame = new A_메인();
					frame.setLocationRelativeTo(null);
					frame.setName("A_메인");
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
	public A_메인() {
		setTitle("\uC790\uACA9\uC99D \uBA54\uC778 \uD654\uBA74");
		setBounds(100, 100, 1003, 647);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new Logo(43,43,false);
		label.setBounds(12, 10, 57, 43);
		getContentPane().add(label);
		
		label_1 = new JLabel("SKills Qualification Association");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_1.setBounds(81, 10, 220, 43);
		getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.setBounds(313, 17, 274, 29);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		label_2 = new JLabel(getIcon("icon/search.png",49,49));
		label_2.setBounds(599, 10, 57, 49);
		getContentPane().add(label_2);
		
		panel = new JPanel();
		panel.setBounds(23, 74, 930, 49);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 4, 0, 0));
		
		label_3 = new JLabel("\uC790\uACA9\uC99D \uBAA9\uB85D");
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_3);
		
		label_4 = new JLabel("\uC2DC\uD5D8 \uC77C\uC815");
		label_4.addMouseListener(new Label_4MouseListener());
		label_4.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_4);
		
		label_5 = new JLabel("\uACE0\uAC1D\uC13C\uD130");
		label_5.addMouseListener(new Label_5MouseListener());
		label_5.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_5);
		
		label_6 = new JLabel("\uC790\uACA9\uC99D\uBC1C\uAE09");
		label_6.addMouseListener(new Label_6MouseListener());
		label_6.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_6);
		
		panel_1 = new JPanel();
		panel_1.setBounds(23, 147, 455, 253);
		getContentPane().add(panel_1);
		
		panel_2 = new JPanel();
		panel_2.setBounds(488, 147, 230, 253);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		panel_3 = new JPanel();
		panel_3.setBounds(0, 51, 232, 202);
		panel_2.add(panel_3);
		
		button = new JButton("\uCD94\uCC9C\uC21C");
		button.setBounds(12, 10, 97, 31);
		panel_2.add(button);
		
		button_1 = new JButton("\uBCC4\uC810\uC21C");
		button_1.setBounds(123, 10, 97, 31);
		panel_2.add(button_1);
		
		panel_4 = new JPanel();
		panel_4.setBounds(730, 147, 245, 253);
		getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		button_2 = new JButton("\uB85C\uADF8\uC778");
		button_2.addActionListener(new Button_2ActionListener());
		button_2.setBounds(12, 10, 107, 31);
		panel_4.add(button_2);
		
		button_3 = new JButton("\uB0B4 \uC815\uBCF4");
		button_3.addActionListener(new Button_3ActionListener());
		button_3.setBounds(126, 10, 107, 31);
		panel_4.add(button_3);
		
		panel_5 = new JPanel();
		panel_5.setBounds(0, 48, 245, 205);
		panel_4.add(panel_5);
		panel_5.setLayout(new CardLayout(0, 0));
		
		label_7 = new JLabel("<html><font color = red>\uB85C\uADF8\uC778\uC774 \uD544\uC694\uD569\uB2C8\uB2E4.</font><br><b>1.</b> \uC720\uD6A8\uD55C \uC0AC\uC6A9\uC790 \uC815\uBCF4\uB97C \uC785\uB825\uD558\uC138\uC694.<br><b>2.</b> \uC778\uC99D \uC808\uCC28\uB97C \uC644\uB8CC\uD558\uC138\uC694.<br><b>3.</b> \uB85C\uADF8\uC778 \uD6C4 \uC774\uC6A9 \uAC00\uB2A5\uD569\uB2C8\uB2E4.<br><b>4.</b>\uC624\uB958\uAC00 \uC9C0\uC18D\uB418\uBA74 \uAD00\uB9AC\uC790\uC5D0\uAC8C \uBB38\uC758\uD558\uC138\uC694.");
		label_7.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		label_7.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_5.add(label_7, "name_31395232314300");
		
		scrollPane = new JScrollPane();
		panel_5.add(scrollPane, "name_31432306097200");
		
		panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(panel_6);
		
		panel_7 = new JPanel();
		panel_7.setBounds(0, 467, 987, 187);
		getContentPane().add(panel_7);
		
		label_8 = new JLabel("\uC790\uACA9\uC99D\uC744 \uC120\uD0DD\uD574 \uC8FC\uC0C8\uC694.");
		label_8.setIcon(getIcon("icon/medel.png",40,40));
		label_8.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_8.setBounds(23, 412, 274, 45);
		getContentPane().add(label_8);

	}
	
	@Override
	public void updateForm() {
		button_3.setVisible(uno==0);
		if(uno==0) {
			button_2.setText("로그인");
		}else {
			button_2.setText("로그아웃");
		}
	}
	
	private class Label_5MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(uno==0) {
				msgErr("로그인이 되어있지 않습니다.");
				showPage(new B_로그인(),"B_로그인");
				return;
			}
			showPage(new I_고객센터(),"I_고객센터");
		}
	}
	private class Label_6MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(uno==0) {
				msgErr("로그인이 되어있지 않습니다.");
				showPage(new B_로그인(),"B_로그인");
				return;
			}
			showPage(new J_자격증폼(),"J_자격증폼");
		}
	}
	private class Label_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(uno==0) {
				msgErr("로그인이 되어있지 않습니다.");
				showPage(new B_로그인(),"B_로그인");
				return;
			}
			showPage(new D_시험일정(),"D_시험일정");
		}
	}
	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(uno==0) {
				msgErr("로그인이 되어있지 않습니다.");
				showPage(new B_로그인(),"B_로그인");
				return;
			}
			showPage(new C_자격증목록(),"C_자격증목록");
		}
	}
	private class Button_2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			showPage(new B_로그인(),"B_로그인"); 
		}
	}
	private class Button_3ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			showPage(new G_나의과정(),"G_나의과정");
		}
	}
}
