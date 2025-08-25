package 주문제작;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class QuestionControl extends JPanel {

	/**
	 * Create the panel.
	 */
	public QuestionControl() {
		setBackground(new Color(255, 255, 255));
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 255));
		panel.setPreferredSize(new Dimension(10, 0));
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("\uB0B4\uC6A9");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(255, 255, 128));
		panel.add(lblNewLabel, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(0, 0, 0, 40)));
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_1 = new JLabel("\uC9C8\uBB38");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(new Color(128, 255, 255));
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("\u25B6");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int h = panel.getHeight();
				test.SetPreferredSize(panel, h == 50 ? 0 : 50);
				test.SetPreferredSize(QuestionControl.this, h == 50 ? 60 : 110);
			}
		});
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setBackground(new Color(255, 128, 128));
		panel_1.add(lblNewLabel_2, BorderLayout.EAST);

		panel_1.setPreferredSize(new Dimension(0, 60));
		this.invalidate();
	}

}
