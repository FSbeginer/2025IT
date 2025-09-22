import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class 유저패널 extends JPanel {
	public JPanel panel;
	public JPanel panel_1;
	public JPanel panel_2;
	public JButton button;
	public JButton button_1;
	public JLabel label;

	/**
	 * Create the panel.
	 */
	public 유저패널() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		add(panel, BorderLayout.EAST);
		panel.setLayout(new GridLayout(1, 0, 10, 0));

		button = new JButton("로그인");
		button.addActionListener(new ButtonActionListener());
		panel.add(button);

		button_1 = new JButton("영화 검색");
		button_1.addActionListener(new Button_1ActionListener());
		panel.add(button_1);

		panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);

		panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(140, 55));
		add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new BorderLayout(0, 0));

		label = new JLabel(BF.getIcon("로고1.jpg", 140, 55));
		label.addMouseListener(new LabelMouseListener());
		panel_2.add(label, BorderLayout.CENTER);
		if(BF.uno!=0) button.setText("내 정보");
	}

	private class LabelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			BF.uno = 0;
			BF.ubirth = null;
			BF.msgInfo("로그아웃 되었습니다.");
			button.setText("로그인");
		}
	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (BF.uno == 0) {
				var b = new B_로그인();
				b.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						if(BF.uno!=0) {
							button.setText("내 정보");
						}
					}
				});
				((BF) SwingUtilities.getWindowAncestor(유저패널.this)).showPage(b, "B_로그인");

			} else {
				((BF) SwingUtilities.getWindowAncestor(유저패널.this)).showPage(new H_내정보(), "H_내정보");
			}
		}
	}

	private class Button_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			((BF) SwingUtilities.getWindowAncestor(유저패널.this)).showPage(new C_영화검색(), "C_영화검색");
		}
	}
}
