import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class 유저패널 extends JPanel {
	public JLabel label;
	public JPanel panel;
	public JPanel panel_1;
	public JButton button;
	public JButton button_1;

	/**
	 * Create the panel.
	 */
	public 유저패널() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));

		label = new JLabel(BF.getIcon("로고1.jpg", 135, 65));
		label.addMouseListener(new LabelMouseListener());
		label.setPreferredSize(new Dimension(140, 70));
		add(label, BorderLayout.WEST);

		panel = new JPanel();
		add(panel, BorderLayout.CENTER);

		panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(200, 70));
		add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new GridLayout(0, 2, 10, 0));

		button = new JButton("로그인");
		button.addActionListener(new ButtonActionListener());
		panel_1.add(button);

		button_1 = new JButton("영화검색");
		button_1.addActionListener(new Button_1ActionListener());
		panel_1.add(button_1);

	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (BF.uno == 0 && !BF.isAdmin) {
				var l = new B_로그인();
				l.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						if(BF.uno!=0) {
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

	private class LabelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			BF.uno = 0;
			BF.isAdmin = false;
			button.setText("로그인");
			BF.msgInfo("로그아웃 되었습니다.");
			((BF) SwingUtilities.getWindowAncestor(button)).updateForm();
		}
	}
	private class Button_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			((BF) SwingUtilities.getWindowAncestor(button)).showPage(new C_영화검색(), "C_영화검색");
		}
	}
}
