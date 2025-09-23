import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class A_메인 extends BP {
	public JLabel label;

	/**
	 * Create the panel.
	 */
	public A_메인() {
		
		label = new JLabel("<html>\uC6B0\uC8FC \uACFC\uD559\uAD00\uC5D0 \uC624\uC2E0 \uAC78<br>\uD658\uC601\uD569\uB2C8\uB2E4.");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 23));
		label.setBounds(12, 10, 279, 80);
		add(label);
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 255));
		panel.setBorder(new LineBorder(new Color(0, 0, 0).darker()));
		panel.setBounds(763, 0, 199, 496);
		add(panel);
		panel.setLayout(null);
		
		label_1 = new JLabel("\uC608\uB9E4");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setForeground(new Color(255, 255, 255));
		label_1.setBounds(28, 84, 122, 63);
		panel.add(label_1);
		
		label_2 = new JLabel("\uC804\uC2DC");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(28, 187, 122, 63);
		panel.add(label_2);
		
		label_3 = new JLabel("\uC608\uB9E4\uB0B4\uC5ED");
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setForeground(Color.WHITE);
		label_3.setBounds(28, 295, 122, 63);
		panel.add(label_3);

		load();
		if(BF.uno==0) {
			panel.setVisible(false);
		}
	}

	JLabel[] jls = new JLabel[4];
	public JPanel panel;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	private void load() {
		for (int i = 0; i < 4; i++) {
			jls[i] = new JLabel(getIcon("메인/"+(i+1)+".png", getWidth(), getHeight()));
			jls[i].setSize(getSize());
			jls[i].setLocation(jls[i].getWidth()*i, 0);
			add(jls[i]);
		}
		new Thread(new Runnable() {
			boolean stop = true;
			@Override
			public void run() {
				while(true) {
					try {
						if(stop) {
							Thread.sleep(1000);
							stop = false;
						}
						else {
							Thread.sleep(1);
						}
					} catch (InterruptedException e) {
					}
					for (JLabel jl : jls) {
						jl.setLocation(jl.getX()-1, jl.getY());
						if(jl.getX()==-jl.getWidth()) {
							stop = true;
							jl.setLocation(jl.getWidth()*4, 0);
						}
					}
				}
			}
		}).start();
	}
	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			((MainFrame)SwingUtilities.getWindowAncestor(label)).showPage(new H_예약내역(), "예약내역");
		}
	}
	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			((MainFrame)SwingUtilities.getWindowAncestor(label)).showPage(new C_전시(), "전시");
			((MainFrame)SwingUtilities.getWindowAncestor(label)).label_1.setForeground(BF.blue);
		}
	}
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			((MainFrame)SwingUtilities.getWindowAncestor(label)).showPage(new D_예매(), "예매");
			((MainFrame)SwingUtilities.getWindowAncestor(label)).label_2.setForeground(BF.blue);
		}
	}
}
