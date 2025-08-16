import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelSlider extends JPanel {
	public JPanel panel;
	public JPanel panel_1;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JPanel panel_2;
	public JLabel label_5;
	public JLabel label_6;
	public JLabel label_7;

	/**
	 * Create the panel.
	 */
	public PanelSlider() {
		setOpaque(false);
		setLayout(new BorderLayout(0, 0));
		setSize(350, 550);
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setBackground(new Color(255, 255, 255));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
				g.setColor(new Color(100, 120, 255).darker());
				g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
			}
		};
		panel_1.setOpaque(false);
		panel_1.setBackground(new Color(255, 255, 255));
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		label_1 = new JLabel("\uBC14\uB85C\uAC00\uAE30");
		label_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		label_1.setForeground(new Color(255, 255, 255));
		label_1.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(29, 10, 201, 36);
		panel_1.add(label_1);
		
		label_2 = new JLabel(BF.getIcon("¾ÆÀÌÄÜ/Æ¼ÄÏ.png",120,48));
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setBounds(71, 81, 121, 48);
		panel_1.add(label_2);
		
		label_3 = new JLabel(BF.getIcon("¾ÆÀÌÄÜ/Æ¼ÄÏ.png",120,48));
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setBounds(71, 171, 121, 48);
		panel_1.add(label_3);
		
		label_4 = new JLabel(BF.getIcon("¾ÆÀÌÄÜ/Æ¼ÄÏ.png",120,48));
		label_4.addMouseListener(new Label_4MouseListener());
		label_4.setBounds(71, 251, 121, 48);
		panel_1.add(label_4);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(29, 328, 201, 2);
		panel_1.add(panel_2);
		
		label_5 = new JLabel("\uC608\uB9E4");
		label_5.setFont(new Font("±¼¸²", Font.BOLD, 13));
		label_5.setForeground(new Color(255, 255, 255));
		label_5.setBounds(107, 146, 57, 15);
		panel_1.add(label_5);
		
		label_6 = new JLabel("\uC804\uC2DC");
		label_6.setFont(new Font("±¼¸²", Font.BOLD, 13));
		label_6.setForeground(new Color(255, 255, 255));
		label_6.setBounds(107, 226, 57, 15);
		panel_1.add(label_6);
		
		label_7 = new JLabel("\uC608\uB9E4\uB0B4\uC5ED");
		label_7.setFont(new Font("±¼¸²", Font.BOLD, 13));
		label_7.setForeground(new Color(255, 255, 255));
		label_7.setBounds(107, 303, 57, 15);
		panel_1.add(label_7);
		
		label = new JLabel("<");
		label.setForeground(new Color(255, 0, 0));
		label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		label.setPreferredSize(new Dimension(20, 15));
		panel.add(label, BorderLayout.WEST);

	}

	private class Label_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			((MainFrame)SwingUtilities.getWindowAncestor(PanelSlider.this)).showPage(new PageReservationList());
		}
	}
	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			((MainFrame)SwingUtilities.getWindowAncestor(PanelSlider.this)).showPage(new PageDisplay());
		}
	}
	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			((MainFrame)SwingUtilities.getWindowAncestor(PanelSlider.this)).showPage(new PageReservation());
		}
	}
}
