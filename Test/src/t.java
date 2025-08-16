import java.awt.Color;
import java.awt.EventQueue;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToggleButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class t extends JFrame {

	private JPanel contentPane;
	public JToggleButton toggleButton;
	public JToggleButton toggleButton_1;
	public JPopupMenu popupMenu;
	public JMenuItem menuItem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					t frame = new t();
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
	public t() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		toggleButton = new JToggleButton("New toggle button");
		toggleButton.addChangeListener(new ToggleButtonChangeListener());
		
		popupMenu = new JPopupMenu();
		addPopup(contentPane, popupMenu);
		
		menuItem = new JMenuItem("dkdkd");
		popupMenu.add(menuItem);
		toggleButton.setBounds(45, 45, 144, 23);
		contentPane.add(toggleButton);
		
		toggleButton_1 = new JToggleButton("New toggle button");
		toggleButton_1.addChangeListener(new ToggleButton_1ChangeListener());
		toggleButton_1.setBounds(210, 45, 135, 23);
		contentPane.add(toggleButton_1);
		System.out.println(getCode());
		
		
		JToggleButton toggle = new JToggleButton("토글 버튼");


		// ① 기본 UI 효과 끄기
		toggle.setContentAreaFilled(false);
		toggle.setBorderPainted(false);
		toggle.setFocusPainted(false);

		// ② 배경이 보이도록 설정
		toggle.setOpaque(true);
		toggle.setBackground(Color.LIGHT_GRAY);  // 초기 배경색

		// ③ 선택 상태에 따라 배경색 변경
		toggle.addChangeListener(e -> {
		    if (toggle.isSelected()) {
		        toggle.setBackground(Color.ORANGE);   // 눌린(선택) 상태의 색
		    } else {
		        toggle.setBackground(Color.LIGHT_GRAY); // 평상시 색
		    }
		});
		
		getContentPane().add(toggle);
		toggleButton.setContentAreaFilled(false);
//		toggleButton.setBorderPainted(false);
//		toggleButton.setFocusPainted(false);
		toggleButton.setOpaque(true);
		toggleButton_1.setContentAreaFilled(false);
//		toggleButton_1.setBorderPainted(false);
//		toggleButton_1.setFocusPainted(false);
		toggleButton_1.setOpaque(true);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(toggleButton);
		bg.add(toggleButton_1);
	}
	private String getCode() {
		Random rand = new Random();
		var list = IntStream.range(65, 65+26).boxed().collect(Collectors.toList());
		var list2 = IntStream.range(0, 10).boxed().collect(Collectors.toList());
		String s = "";
		for (int i = 0; i < 6; i++) {
			if(rand.nextBoolean())
				s += (char)(int)list.get(rand.nextInt(26));
			else
				s += list2.get(rand.nextInt(10));
		}
		return s;
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	private class ToggleButton_1ChangeListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			if(toggleButton_1.isSelected())
				toggleButton_1.setBackground(Color.BLUE);
			else
				toggleButton_1.setBackground(Color.red);
		}
	}
	private class ToggleButtonChangeListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			if(toggleButton.isSelected())
				toggleButton.setBackground(Color.BLUE);
			else
				toggleButton.setBackground(Color.red);
		}
	}
}
