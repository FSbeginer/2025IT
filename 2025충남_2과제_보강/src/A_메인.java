import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class A_메인 extends BP {
	public JLabel label;
	public JPanel panel;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;

	/**
	 * Create the panel.
	 */
	public A_메인() {

		label = new JLabel(
				"<html>\uC6B0\uC8FC \uACFC\uD559\uAD00\uC5D0 \uC624\uC2E0 \uAC78<br>\uD658\uC601\uD569\uB2C8\uB2E4.");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		label.setBounds(12, 10, 273, 78);
		add(label);

		panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(765, 0, 242, 514);
		add(panel);
		panel.setLayout(null);

		label_1 = new JLabel("\uC608\uB9E4");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(33, 144, 156, 26);
		panel.add(label_1);

		label_2 = new JLabel("\uC804\uC2DC");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(33, 236, 156, 26);
		panel.add(label_2);

		label_3 = new JLabel("\uC608\uB9E4\uB0B4\uC5ED");
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(Color.WHITE);
		label_3.setBounds(33, 319, 156, 26);
		panel.add(label_3);

		panel.setVisible(BF.uno != 0);
		load();
	}

	JLabel[] jls = new JLabel[4];

	private void load() {
		for (int i = 0; i < jls.length; i++) {
			jls[i] = new JLabel(getIcon("메인/" + (i + 1) + ".png", getWidth(), getHeight()));
			jls[i].setSize(getSize());
			jls[i].setLocation(getWidth() * i, 0);
			add(jls[i]);
		}
		new Thread(new Runnable() {
			boolean stop = true;

			@Override
			public void run() {
				while (true) {
					try {
						if (stop) {
							Thread.sleep(1000);
							stop = false;
						} else {
							Thread.sleep(1);
						}
						for (JLabel jl : jls) {
							jl.setLocation(jl.getX()-1, 0);
							if(jl.getX()==-jl.getWidth()) {
								jl.setLocation(jl.getWidth()*3, 0);
								stop = true;
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			getmf(label).showpage(new F_예약내역(), "예약내역");
		}
	}
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			getmf(label).showpage(new D_예매(), "예매");
		}
	}
	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			getmf(label).showpage(new C_전시(), "전시");
		}
	}
}
