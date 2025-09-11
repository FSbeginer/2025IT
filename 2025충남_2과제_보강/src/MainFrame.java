import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends BF {
	public JPanel panel;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;
	public JPanel panel_1;

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
		setBounds(100, 100, 1023, 614);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel.setBounds(0, 0, 1007, 61);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 6, 0, 0));

		label = new JLabel(getIcon("아이콘/아이콘.png", 140, 50));
		label.addMouseListener(new LabelMouseListener());
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label);

		label_1 = new JLabel("\uC804\uC2DC");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_1);

		label_2 = new JLabel("\uC608\uB9E4");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2);

		label_3 = new JLabel("\uD504\uB85C\uADF8\uB7A8");
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_3);

		label_4 = new JLabel("\uCEE4\uBBA4\uB2C8\uD2F0");
		label_4.addMouseListener(new Label_4MouseListener());
		label_4.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_4);

		label_5 = new JLabel("LOGIN");
		label_5.addMouseListener(new Label_5MouseListener());
		label_5.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_5);

		panel_1 = new JPanel();
		panel_1.setBounds(0, 61, 1007, 514);
		getContentPane().add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		showpage(new A_메인(), "메인");
//		showpage(new I_과학관관리(), "메인");
	}

	public void showpage(JPanel jp, String name) {
		panel_1.removeAll();
		panel_1.add(jp);
		setTitle(name);
		resetIcon();
		updateForm();
		panel_1.revalidate();
		panel_1.repaint();
	}

	private void resetIcon() {
		if (!isAdmin) {
			label_1.setText("전시");
			label_2.setText("예매");
			label_3.setText("프로그램");
			label_4.setText("커뮤니티");
		} else {
			label_1.setText("과학관 관리");
			label_2.setText("프로그램 등록");
			label_3.setText("예매분석");
			label_4.setText("커뮤니티");
		}
		var jls = new JLabel[] { label_1, label_2, label_3, label_4, label_5 };
		for (JLabel jl : jls) {
			jl.setForeground(Color.black);
		}
	}

	@Override
	public void updateForm() {
		if (uno == 0 && !isAdmin) {
			label_5.setText("LOGIN");
		} else {
			label_5.setText("LOGOUT");
		}
	}

	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (isAdmin) {
				showpage(new M_예매분석(), "예매분석");
				label_3.setForeground(blue);
			} else {
				showpage(new E_프로그램(), "프로그램");
				label_3.setForeground(blue);
			}
		}
	}

	private class Label_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showpage(new G_커뮤니티(), "커뮤니티");
			label_4.setForeground(blue);
		}
	}

	private class Label_5MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (uno == 0 && !isAdmin) {
				showpage(new B_로그인(), "로그인");
				label_5.setForeground(blue);
			} else {
				uno = 0;
				isAdmin = false;
				showpage(new A_메인(), "메인");
			}
		}
	}

	private class LabelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showpage(new A_메인(), "메인");
		}
	}

	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (isAdmin) {
				showpage(new E_프로그램(), "프로그램");
				label_1.setForeground(blue);
			} else {
				showpage(new C_전시(), "전시");
				label_1.setForeground(blue);
			}
		}
	}

	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (isAdmin) {
				showpage(new D_예매(), "예매");
				label_2.setForeground(blue);
			} else {
				showpage(new J_프로그램등록(), "프로그램등록");
				label_2.setForeground(blue);
			}
		}
	}
}
