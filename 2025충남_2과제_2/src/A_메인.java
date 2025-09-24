import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class A_∏ﬁ¿Œ extends BP {
	public JLabel label;
	/**
	 * Create the panel.
	 */
	public A_∏ﬁ¿Œ() {
		setLayout(null);
		
		label = new JLabel("<html>\uC6B0\uC8FC \uACFC\uD559\uAD00\uC5D0 \uC624\uC2E0 \uAC78<br> \uD658\uC601\uD569\uB2C8\uB2E4.");
		label.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 23));
		label.setBounds(12, 10, 279, 73);
		add(label);
		
		addMain();
	}

	JLabel[] jls = new JLabel[4];
	private void addMain() {
		for (int i = 0; i < jls.length; i++) {
			jls[i] = new JLabel(getIcon("∏ﬁ¿Œ/"+(i+1)+".png",getWidth(),getHeight()));
			jls[i].setSize(getSize());
			jls[i].setLocation(getWidth()*i, 0);
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
						else
							Thread.sleep(1);
					} catch (InterruptedException e) {
						break;
					}
					for (JLabel jl : jls) {
						jl.setLocation(jl.getX()-1, 0);
						if(jl.getX()==-jl.getWidth()) {
							stop = true;
							jl.setLocation(jl.getWidth()*4, 0);
						}
					}
				}
			}
		}).start();
	}
}
