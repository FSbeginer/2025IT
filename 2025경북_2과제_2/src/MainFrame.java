import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends BF {
	public JPanel panel;
	public JPanel panel_1;
	public JPanel panelPage;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;
	public JPanel panel_2;
	public JLabel lblProfile;
	public JLabel lblName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setName("MainFrame");
					frame.setLocationRelativeTo(null);
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
		setTitle("\uBA54\uC778");
		setBounds(100, 100, 892, 628);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 70));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		
		label_5 = new JLabel("Roupang");
		label_5.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(0, 0, 876, 70);
		panel.add(label_5);
		
		panel_2 = new JPanel();
		panel_2.addMouseListener(new Panel_2MouseListener());
		panel_2.setBounds(795, 10, 69, 60);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		lblProfile = new JLabel("");
		lblProfile.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfile.setBounds(0, 0, 60, 39);
		panel_2.add(lblProfile);
		
		lblName = new JLabel("");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(3, 45, 57, 15);
		panel_2.add(lblName);
		
		panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(10, 60));
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 5, 0, 0));
		
		label = new JLabel("");
		label.addMouseListener(new LabelMouseListener());
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label);
		
		label_1 = new JLabel("");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_1);
		
		label_2 = new JLabel("");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_2);
		
		label_3 = new JLabel("");
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_3);
		
		label_4 = new JLabel("");
		label_4.addMouseListener(new Label_4MouseListener());
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_4);
		
		panelPage = new JPanel();
		getContentPane().add(panelPage, BorderLayout.CENTER);
		panelPage.setLayout(new BorderLayout(0, 0));
		
		
		showPage(new A_메인());
		label.setIcon(getIcon("logo/메인.png",50,50,blue));
		updateForm();
	}
	
	@Override
	public void updateForm() {
		if(uno==0&&!isAdmin) {
			lblProfile.setIcon(getIcon("logo/유저.png",40,40));
			lblName.setText("");
		}
		else if(uno!=0) {
			lblProfile.setIcon(getIcon("logo/유저.png",40,40,blue));
			lblName.setText(uname);
		}
		else {
			lblProfile.setIcon(getIcon("logo/유저.png",40,40, Color.red));
			lblName.setText("관리자");
		}
	}
	
	public void showPage(JPanel page) {
		panelPage.removeAll();
		panelPage.add(page);
		panelPage.revalidate();
		panelPage.repaint();
		resetIcon();
	}

	private void resetIcon() {
		label.setIcon(getIcon("logo/메인.png",40,40));
		label_1.setIcon(getIcon("logo/검색.png",40,40));
		if(isAdmin) {
			label_2.setIcon(getIcon("logo/등록.png",40,40));
			label_3.setIcon(getIcon("logo/배송처리.png",40,40));
			label_4.setIcon(getIcon("logo/분석.png",40,40));
		}
		else {
			label_2.setIcon(getIcon("logo/장바구니.png",40,40));
			label_3.setIcon(getIcon("logo/구매목록.png",40,40));
			label_4.setIcon(getIcon("logo/배송정보.png",40,40));
		}
	}
	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(uno==0&&!isAdmin) {
				msgErr("로그인 후 사용가능합니다.");
			}
			else if(uno!=0) {
				showPage(new E_장바구니());
				label_2.setIcon(getIcon("logo/장바구니.png",40,40,blue));
			}
			else if(isAdmin) {
				showPage(new H_상품등록(),"H_상품등록");
			}
		}
	}
	private class Label_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(uno==0&&!isAdmin) {
				msgErr("로그인 후 사용가능합니다.");
			}
			else if(uno!=0) {
				showPage(new G_배송정보());
				label_4.setIcon(getIcon("logo/배송정보.png",40,40,blue));
			}
			else if(isAdmin) {
				showPage(new J_분석());
				label_4.setIcon(getIcon("logo/분석.png",40,40,blue));
			}
		}
	}
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new C_검색(0));
			label_1.setIcon(getIcon("logo/검색.png",40,40,blue));
		}
	}
	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(uno==0&&!isAdmin) {
				msgErr("로그인 후 사용가능합니다.");
			}
			else if(uno!=0) {
				showPage(new F_구매목록());
				label_3.setIcon(getIcon("logo/구매목록.png",40,40,blue));
			}
			else if(isAdmin) {
				showPage(new I_배송처리());
				label_3.setIcon(getIcon("logo/배송처리.png",40,40,blue));
			}
		}
	}
	private class LabelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new A_메인());
			label.setIcon(getIcon("logo/메인.png",40,40,blue));
		}
	}
	private class Panel_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(uno!=0||isAdmin) {
				uno = 0;
				isAdmin = false;
				showPage(new A_메인());
				label.setIcon(getIcon("logo/메인.png",40,40,blue));
				updateForm();
				msgInfo("로그아웃되었습니다.");
			}
			else {
				showPage(new B_로그인(MainFrame.this), "B_로그인");
			}
		}
	}
}
