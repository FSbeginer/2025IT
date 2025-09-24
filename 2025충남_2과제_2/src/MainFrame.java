import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends BF {
	public JPanel panel;
	public JPanel panel_1;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setName("메인");
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {
		setTitle("\uBA54\uC778");
		setBounds(100, 100, 1014, 650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel.setPreferredSize(new Dimension(10, 60));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 6, 0, 0));
		
		label = new JLabel(getIcon("아이콘/아이콘.png",140,50));
		label.addMouseListener(new LabelMouseListener());
		label.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label);
		
		label_1 = new JLabel("\uC804\uC2DC");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setFont(new Font("굴림", Font.BOLD, 14));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_1);
		
		label_2 = new JLabel("\uC608\uB9E4");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2);
		
		label_3 = new JLabel("\uD504\uB85C\uADF8\uB7A8");
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_3);
		
		label_4 = new JLabel("\uCEE4\uBBA4\uB2C8\uD2F0");
		label_4.addMouseListener(new Label_4MouseListener());
		label_4.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_4);
		
		label_5 = new JLabel("LOGIN");
		label_5.addMouseListener(new Label_5MouseListener());
		label_5.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_5);
		
		panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		A_메인 a_메인 = new A_메인();
		showPage(a_메인,"메인");
		
	}
	@Override
	public void updateForm() {
		if(isAdmin) {
			label_1.setText("과학관 관리");
			label_2.setText("프로그램 등록");
			label_3.setText("예매분석");
		}
		else {
			label_1.setText("전시");
			label_2.setText("예매");
			label_3.setText("프로그램");
		}
		
		if(isAdmin||uno!=0) {
			label_5.setText("LOGOUT");
		}
		else {
			label_5.setText("LOGIN");
		}
	}

	public void showPage(JPanel jp,String name) {
		panel_1.removeAll();
		panel_1.add(jp);
		panel_1.revalidate();
		panel_1.repaint();
		setTitle(name);
		resetIcon();
		updateForm();
		prevPage.add(jp);
		prevtitle.add(name);
	}

	private void resetIcon() {
		JLabel[] jls = {label_1,label_2,label_3,label_4,label_5};
		for (JLabel jl : jls) {
			jl.setForeground(Color.black);
		}
	}
	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(isAdmin) {
				showPage(new M예매분석(),"예매분석");
				label_3.setForeground(blue);
			}
			else {
				showPage(new E_프로그램(),"프로그램");
				label_3.setForeground(blue);
			}
		}
	}
	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(isAdmin) {
				showPage(new J_프로그램등록(),"프로그램등록");
				label_2.setForeground(blue);
			}
			else {
				showPage(new D_예매(),"예매");
				label_2.setForeground(blue);
			}
		}
	}
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(isAdmin) {
				showPage(new E_프로그램(),"과학관 관리");
				label_1.setForeground(blue);
			}
			else {
				showPage(new C_전시(),"전시");
				label_1.setForeground(blue);
			}
		}
	}
	private class Label_5MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(BF.uno==0&&!isAdmin) {
				showPage(new B_로그인(),"로그인");
				label_5.setForeground(blue);
			}
			else {
				uno = 0;
				isAdmin = false;
				showPage(new A_메인(), "메인");
			}
		}
	}
	private class Label_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new F_커뮤니티(),"커뮤니티");
			label_4.setForeground(blue);
		}
	}
	private class LabelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new A_메인(),"메인");
		}
	}
}
