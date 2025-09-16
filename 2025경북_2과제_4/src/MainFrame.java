import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends BF {
	public JPanel panel;
	public JPanel panel_1;
	public JPanel panel_2;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;
	public JLabel label_6;
	public JLabel label_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setLocationRelativeTo(null);
					frame.setName("MainFrame");
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
	public MainFrame() {
		setBounds(100, 100, 984, 626);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 70));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		
		label = new JLabel("Roupang");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 968, 60);
		panel.add(label);
		
		label_1 = new JLabel("");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setBounds(879, 10, 45, 40);
		panel.add(label_1);
		
		label_2 = new JLabel("New label");
		label_2.setBounds(879, 55, 57, 15);
		panel.add(label_2);
		
		panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(10, 60));
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 5, 0, 0));
		
		label_3 = new JLabel("");
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_3);
		
		label_4 = new JLabel("");
		label_4.addMouseListener(new Label_4MouseListener());
		label_4.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_4);
		
		label_5 = new JLabel("");
		label_5.addMouseListener(new Label_5MouseListener());
		label_5.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_5);
		
		label_6 = new JLabel("");
		label_6.addMouseListener(new Label_6MouseListener());
		label_6.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_6);
		
		label_7 = new JLabel("");
		label_7.addMouseListener(new Label_7MouseListener());
		label_7.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_7);
		
		panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(10, 60));
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		
		showPage(new A_메인(),"메인");
		label_3.setIcon(getIcon("logo/메인.png",40,40,blue));
	}

	public void showPage(JPanel jp, String name) {
		setTitle(name);
		panel_2.removeAll();
		panel_2.add(jp);
		panel_2.revalidate();
		panel_2.repaint();
		updateForm();
		reseticon();
	}
	@Override
	public void updateForm() {
		if(uno==0&&!isAdmin) {
			label_1.setIcon(getIcon("logo/유저.png",40,40));
			label_2.setText("");
		}
		else if(uno!=0) {
			label_1.setIcon(getIcon("logo/유저.png",40,40,blue));
			label_2.setText(uname);
		}
		else {
			label_1.setIcon(getIcon("logo/유저.png",40,40,Color.red));
			label_2.setText("관리자");
		}
	}

	private void reseticon() {
		label_3.setIcon(getIcon("logo/메인.png",40,40));
		label_4.setIcon(getIcon("logo/검색.png",40,40));
		if(isAdmin) {
			label_5.setIcon(getIcon("logo/등록.png",40,40));
			label_6.setIcon(getIcon("logo/배송처리.png",40,40));
			label_7.setIcon(getIcon("logo/분석.png",40,40));
		}
		else {
			label_5.setIcon(getIcon("logo/장바구니.png",40,40));
			label_6.setIcon(getIcon("logo/구매목록.png",40,40));
			label_7.setIcon(getIcon("logo/배송정보.png",40,40));
		}
	}
	private class Label_5MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(uno==0&&!isAdmin) {
				msgErr("로그인 후 사용 가능합니다.");
				return;
			}
			if(isAdmin) {
				showPage(new H_상품등록(0), "H_상품등록");
			}
			else {
				showPage(new E_장바구니(), "장바구니");
				label_5.setIcon(getIcon("logo/장바구니.png",40,40,blue));
			}
		}
	}
	private class Label_7MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(uno==0&&!isAdmin) {
				msgErr("로그인 후 사용 가능합니다.");
				return;
			}
			if(isAdmin) {
				showPage(new J_분석(), "분석");
				label_7.setIcon(getIcon("logo/분석.png",40,40,blue));
			}
			else {
				showPage(new G_배송정보(), "배송정보");
				label_7.setIcon(getIcon("logo/배송정보.png",40,40,blue));
			}
		}
	}
	private class Label_6MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(uno==0&&!isAdmin) {
				msgErr("로그인 후 사용 가능합니다.");
				return;
			}
			if(isAdmin) {
				showPage(new I_배송처리(), "배송처리");
				label_6.setIcon(getIcon("logo/배송처리.png",40,40,blue));
			}
			else {
				showPage(new F_구매목록(), "구매목록");
				label_6.setIcon(getIcon("logo/구매목록.png",40,40,blue));
			}
		}
	}
	private class Label_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new C_검색(0), "검색");
			label_4.setIcon(getIcon("logo/검색.png",40,40,blue));
		}
	}
	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new A_메인(), "메인");
			label_3.setIcon(getIcon("logo/메인.png",40,40,blue));
		}
	}
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(uno!=0||isAdmin) {
				msgInfo("로그아웃되었습니다.");
				isAdmin =false;
				uno = 0;
				showPage(new A_메인(), "A_메인");
				label_3.setIcon(getIcon("logo/메인.png",40,40,blue));
			}
			else {
				showPage(new B_로그인(MainFrame.this), "B_로그인");
			}
		}
	}
}
