import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;

public class 유저패널 extends JPanel {
	public JLabel label;
	public JPanel panel;
	public JButton button;
	public JButton button_1;

	/**
	 * Create the panel.
	 */
	public 유저패널() {
		setBorder(new EmptyBorder(0, 10, 0, 0));
		setLayout(new BorderLayout(0, 0));

		label = new JLabel(BF.getIcon("로고1.jpg", 140, 60));
		label.addMouseListener(new LabelMouseListener());
		add(label, BorderLayout.WEST);
		label.setPreferredSize(new Dimension(140, 60));

		panel = new JPanel();
		panel.setPreferredSize(new Dimension(180, 10));
		add(panel, BorderLayout.EAST);
		panel.setLayout(new GridLayout(0, 2, 5, 0));

		button = new JButton("로그인");
		button.addActionListener(new ButtonActionListener());
		panel.add(button);

		button_1 = new JButton("영화 검색");
		button_1.addActionListener(new Button_1ActionListener());
		panel.add(button_1);
		button.setText(BF.uno==0?"로그인":"내 정보");
	}

	private class LabelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			BF.uno = 0;
			BF.msgInfo("로그아웃 되었습니다.");
			button.setText("로그인");
		}
	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (BF.uno == 0) {
				var l = new B_로그인();
				l.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						if (BF.uno != 0) {
							button.setText("내 정보");
						}
					}
				});
				((BF) SwingUtilities.getWindowAncestor(button)).showPage(l, "B_로그인");
			} else {
				((BF) SwingUtilities.getWindowAncestor(button)).showPage(new H_내정보(), "H_내정보");
			}
		}
	}
	private class Button_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			((BF) SwingUtilities.getWindowAncestor(button)).showPage(new C_영화검색(), "C_영화검색");
		}
	}
}
