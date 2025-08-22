import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserPanel extends JPanel {
	public JLabel label;
	public JPanel panel;
	public JButton button;
	public JButton button_1;

	/**
	 * Create the panel.
	 */
	public UserPanel() {
		setLayout(new BorderLayout(0, 0));
		
		label = new JLabel(BF.getIcon("로고1.jpg",140,65));
		label.addMouseListener(new LabelMouseListener());
		label.setBorder(new EmptyBorder(0, 10, 0, 0));
		label.setPreferredSize(new Dimension(150, 65));
		add(label, BorderLayout.WEST);
		
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 5, 5, 5));
		add(panel, BorderLayout.EAST);
		panel.setLayout(new GridLayout(0, 2, 5, 0));
		
		button = new JButton("로그인");
		button.addActionListener(new ButtonActionListener());
		panel.add(button);
		
		button_1 = new JButton("영화 검색");
		button_1.addActionListener(new Button_1ActionListener());
		panel.add(button_1);

	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(BF.uno==0)
				((BF)SwingUtilities.getWindowAncestor(UserPanel.this)).showPage(new LoginForm(), "LoginForm");
			else
				((BF)SwingUtilities.getWindowAncestor(UserPanel.this)).showPage(new MyPage(), "MyPage");
		}
	}
	private class Button_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			((BF)SwingUtilities.getWindowAncestor(UserPanel.this)).showPage(new MovieSearch(), "MovieSearch");
		}
	}
	private class LabelMouseListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			BF.uno = 0;
			BF.msgInfo("로그아웃 되었습니다.");
		}
	}
}
