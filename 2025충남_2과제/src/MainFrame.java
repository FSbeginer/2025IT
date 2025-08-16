import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.border.MatteBorder;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class MainFrame extends BF {

	private JPanel contentPane;
	public JPanel panel;
	public JLabel label;
	public JPanel panel_1;
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
					frame.setLocationRelativeTo(null);
					frame.setName("MainFrame");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {
		setTitle("\uBA54\uC778");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 908, 581);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setPreferredSize(new Dimension(10, 50));
		panel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		
		label = new MainLogo();
		label.setBounds(0, 0, 132, 50);
		panel.add(label);
		
		label_1 = new JLabel("\uC804\uC2DC");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(175, 0, 119, 50);
		panel.add(label_1);
		
		label_2 = new JLabel("\uC608\uB9E4");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(306, 0, 119, 50);
		panel.add(label_2);
		
		label_3 = new JLabel("\uD504\uB85C\uADF8\uB7A8");
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(448, 0, 119, 50);
		panel.add(label_3);
		
		label_4 = new JLabel("\uCEE4\uBBA4\uB2C8\uD2F0");
		label_4.addMouseListener(new Label_4MouseListener());
		label_4.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(593, 0, 119, 50);
		panel.add(label_4);
		
		label_5 = new JLabel("LOGIN");
		label_5.addMouseListener(new Label_5MouseListener());
		label_5.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(752, 0, 128, 50);
		panel.add(label_5);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		showPage(new PageMain());
	}
	
	public void resetIcon() {
		JLabel[] jl = {label_1, label_2, label_3, label_4, label_5};
		for (JLabel jls : jl) {
			jls.setForeground(Color.black);
		}
	}
	
	public void showPage(JPanel panel) {
		resetIcon();
		panel_1.removeAll();
		panel_1.add(panel);
		setTitle(panel.getName());
		panel_1.revalidate();
		panel_1.repaint();
		if(BF.uno != 0) {
			label_5.setText("LOGOUT");
		}
		else if(isAdmin){
			label_1.setText("과학관 관리");
			label_2.setText("프로그램 등록");
			label_3.setText("예매분석");
			label_5.setText("LOGOUT");
		}
		else {
			label_5.setText("LOGIN");
		}
	}

	class MainLogo extends JLabel{
		public MainLogo() {
			setIcon(BF.getIcon("아이콘/아이콘.png",100,40));
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					showPage(new PageMain());
				}
			});
		}
	}
	private class Label_5MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(BF.uno==0&&!isAdmin) {
				showPage(new PageLogin());
				label_5.setForeground(blue);
			}
			else {
				uno = 0;
				isAdmin = false;
				showPage(new PageMain());
			}
		}
	}
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(isAdmin) {
//				showPage(new PageMange()); 조건 불확실. 9-1과학관 관리 이동시 어느 프로그램을 수정시킬 것인가가 필요
				showPage(new PageProgram()); // 정황상 이게 맞음..
			}
			else {
				showPage(new PageDisplay());
			}
			label_1.setForeground(blue);
		}
	}
	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(isAdmin) {
				showPage(new PageAnalyze());
			}
			else {
				showPage(new PageProgram());
			}
			label_3.setForeground(blue);
		}
	}
	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(isAdmin) {
				showPage(new PageRes());
				label_2.setForeground(blue);
			}
			else {
				showPage(new PageReservation());
				label_2.setForeground(blue);
				if(uno==0) {
					msgErr("로그인 후 이용 가능합니다.");
					showPage(new PageLogin());
					label_5.setForeground(blue);
				}
			}
		}
	}
	private class Label_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new PageCommu());
			label_4.setForeground(blue);
		}
	}
}
