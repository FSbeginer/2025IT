import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class MainFrame extends BF {

	private JPanel contentPane;
	public JPanel panel;
	public JPanel panel_1;
	public JPanel panel_2;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;
	public JPanel panel_3;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 953, 707);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 70));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(null);

		panel_3 = new JPanel();
		panel_3.setBounds(849, 10, 60, 60);
		panel.add(panel_3);
		panel_3.setLayout(null);

		label_6 = new JLabel();
		label_6.addMouseListener(new Label_6MouseListener());
		label_6.setBounds(0, 0, 57, 40);
		panel_3.add(label_6);

		label_7 = new JLabel("New label");
		label_7.setBounds(0, 45, 57, 15);
		panel_3.add(label_7);

		label_5 = new JLabel("Roupang");
		label_5.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(246, 0, 480, 70);
		panel.add(label_5);

		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);

		panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(10, 60));
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new GridLayout(0, 5, 0, 0));

		label = new JLabel("");
		label.addMouseListener(new LabelMouseListener());
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label);

		label_1 = new JLabel("");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_1);

		label_2 = new JLabel("");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_2);

		label_3 = new JLabel("");
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_3);

		label_4 = new JLabel("");
		label_4.addMouseListener(new Label_4MouseListener());
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_4);

		showPage(new A_메인(), "메인");
		label.setIcon(getIcon(getIcon("logo/메인.png", 40, 40).getImage(), blue));
	}

	@Override
	public void updateForm() {
		if (uno != 0) {
			label_6.setIcon(getIcon(getIcon("logo/유저.png", 40, 40).getImage(), blue));
			label_7.setText(uname);
		} else if (isAdmin) {
			label_6 .setIcon(getIcon(getIcon("logo/유저.png", 40, 40).getImage(), Color.red));
			label_7.setText("관리자");
		} else {
			label_6.setIcon(getIcon("logo/유저.png", 40, 40));
			label_7.setText("");
		}
	}

	public void showPage(JPanel jp, String name) {
		panel_1.removeAll();
		panel_1.add(jp);
		updateForm();
		setTitle(name);
		resetIcon();
		panel_1.revalidate();
		panel_1.repaint();
	}

	private void resetIcon() {
		label.setIcon(getIcon("logo/메인.png", 40, 40));
		label_1.setIcon(getIcon("logo/검색.png", 40, 40));
		if (isAdmin) {
			label_2.setIcon(getIcon("logo/등록.png", 40, 40));
			label_3.setIcon(getIcon("logo/배송처리.png", 40, 40));
			label_4.setIcon(getIcon("logo/분석.png", 40, 40));
		} else {
			label_2.setIcon(getIcon("logo/장바구니.png", 40, 40));
			label_3.setIcon(getIcon("logo/구매목록.png", 40, 40));
			label_4.setIcon(getIcon("logo/배송정보.png", 40, 40));
		}
	}

	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (uno == 0 && !isAdmin) {
				msgErr("로그인 후 사용 가능합니다.");
				return;
			}
			if (isAdmin) {
				showPage(new H_상품등록(-1), "H_상품등록");
			} else {
				showPage(new E_장바구니(), "장바구니");
			}
		}
	}

	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new C_검색(0), "검색");
			label_1.setIcon(getIcon(getIcon("logo/검색.png", 40, 40).getImage(), blue));
		}
	}

	private class LabelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new A_메인(), "메인");
			label.setIcon(getIcon(getIcon("logo/메인.png", 40, 40).getImage(), blue));
		}
	}

	private class Label_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (uno == 0 && !isAdmin) {
				msgErr("로그인 후 사용 가능합니다.");
				return;
			}
			if (isAdmin) {
				showPage(new J_분석(), "분석");
				label_4.setIcon(getIcon(getIcon("logo/분석.png", 40, 40).getImage(), blue));
			} else {
				showPage(new G_배송정보(), "배송정보");
				label_4.setIcon(getIcon(getIcon("logo/배송정보.png", 40, 40).getImage(), blue));
			}
		}
	}

	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (uno == 0 && !isAdmin) {
				msgErr("로그인 후 사용 가능합니다.");
				return;
			}
			if (isAdmin) {
				showPage(new I_배송처리(), "배송처리");
				label_3.setIcon(getIcon(getIcon("logo/배송처리.png", 40, 40).getImage(), blue));
			} else {
				showPage(new F_구매목록(), "구매목록");
				label_3.setIcon(getIcon(getIcon("logo/구매목록.png", 40, 40).getImage(), blue));
			}
		}
	}

	private class Label_6MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (uno == 0 && !isAdmin) {
				showPage(new B_로그인(MainFrame.this), "B_로그인");
			} else {
				uno = 0;
				isAdmin = false;
				showPage(new A_메인(), "메인");
				label.setIcon(getIcon(getIcon("logo/메인.png", 40, 40).getImage(), blue));
			}
		}
	}
}
