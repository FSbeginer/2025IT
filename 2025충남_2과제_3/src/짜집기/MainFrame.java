package 짜집기;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class MainFrame extends BF {
	public JPanel panel;
	public JPanel panel_1;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;

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
		setBounds(100, 100, 978, 615);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel.setPreferredSize(new Dimension(10, 80));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 6, 0, 0));

		label = new JLabel(getIcon("아이콘/아이콘.png", 140, 50));
		label.addMouseListener(new LabelMouseListener());
		label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		panel.add(label);

		label_1 = new JLabel("\uC804\uC2DC");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		panel.add(label_1);

		label_2 = new JLabel("");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		panel.add(label_2);

		label_3 = new JLabel("New label");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		panel.add(label_3);

		label_4 = new JLabel("New label");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.addMouseListener(new Label_4MouseListener());
		label_4.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		panel.add(label_4);

		label_5 = new JLabel("New label");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.addMouseListener(new Label_5MouseListener());
		label_5.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		panel.add(label_5);

		panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		updateForm();
		showPage(new A_메인(), "메인");
	}

	@Override
	public void updateForm() {
		if (uno == 0 && !isAdmin) {
			label_1.setText("전시");
			label_2.setText("예매");
			label_3.setText("프로그램");
			label_4.setText("커뮤니티");
			label_5.setText("LOGIN");
		} else if (isAdmin) {
			label_1.setText("과학관 관리");
			label_2.setText("프로그램 등록");
			label_3.setText("예매분석");
			label_4.setText("커뮤니티");
			label_5.setText("LOGOUT");
		} else {
			label_1.setText("전시");
			label_2.setText("예매");
			label_3.setText("프로그램");
			label_4.setText("커뮤니티");
			label_5.setText("LOGOUT");
		}
	}

	public void showPage(JPanel jp, String name) {
		setTitle(name);
		panel_1.removeAll();
		panel_1.add(jp);
		prevPage.add(jp);
		prevName.add(name);
		resetIcon();
		updateForm();
		panel_1.revalidate();
		panel_1.repaint();
	}

	private void resetIcon() {
		var jls = new JLabel[] { label_1, label_2, label_3, label_4, label_5 };
		for (JLabel jl : jls) {
			jl.setForeground(Color.black);
		}
	}

	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (!isAdmin) {
				showPage(new C_전시(), "전시");
				label_1.setForeground(blue);
				System.out.println(1);
			} else {
				showPage(new E_프로그램(), "과학관 관리");
				label_1.setForeground(blue);
			}
		}
	}

	private class Label_5MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(!isAdmin&&uno==0) {
				showPage(new B_로그인() , "로그인");
				label_5.setForeground(blue);
			}
			else{
				uno = 0;
				isAdmin = false;
				showPage(new A_메인(), "메인");
			}
		}
	}

	private class Label_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new F_커뮤니티(), "커뮤니티");
			label_4.setForeground(blue);
		}
	}

	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (uno != 0) {
				showPage(new D_예매(), "예매");
			} else if (isAdmin){
				label_2.setForeground(blue);
				showPage(new J_프로그램등록(), "프로그램 등록");
				label_2.setForeground(blue);
			}
			else {
				msgErr("로그인 후 이용 가능합니다.");
				showPage(new B_로그인(), "로그인");
				label_5.setForeground(blue);
			}
		}
	}

	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (!isAdmin) {
				showPage(new E_프로그램(), "프로그램");
				label_3.setForeground(blue);
			} else {
				showPage(new M_예매분석(), "예매분석");
				label_3.setForeground(blue);
			}
		}
	}
	private class LabelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new A_메인(), "메인");
		}
	}
}
