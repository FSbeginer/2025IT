import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.List;

public class PageMain extends BP {
	public JLabel label;

	List<JLabel> jls = new ArrayList<JLabel>();

	private PanelSlider pp;

	public PageMain() {
		setLayout(null);
		setName("∏ﬁ¿Œ");
		label = new JLabel(
				"<html>\uC6B0\uC8FC \uACFC\uD559\uAD00\uC5D0 \uC624\uC2E0 \uAC78<br> \uD658\uC601\uD569\uB2C8\uB2E4.");
		label.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 25));
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setBounds(23, 0, 293, 76);
		add(label);

		addLabel();
		if(BF.uno!=0) {
			addSlider();
		}
	}

	private void addSlider() {
		pp = new PanelSlider();
		pp.setLocation(getWidth()-pp.getWidth()+50, 0);
		add(pp);
		setComponentZOrder(pp, 0);
	}

	private void addLabel() {
		for (int i = 0; i < 4; i++) {
			JLabel jl = new JLabel(getIcon("∏ﬁ¿Œ/" + (i + 1) + ".png", getWidth(), getHeight()));
			jl.setSize(getSize());
			jl.setLocation(getWidth() * i, 0);
			add(jl);
			jls.add(jl);
		}
		Thread th = new Thread(new Runnable() {

			@Override
			public void run() {
				boolean stop = true;
				while (true) {
					try {
						if (stop) {
							Thread.sleep(1000);
							stop = false;
						}
						else Thread.sleep(1);
						for (JLabel jLabel : jls) {
							jLabel.setLocation(jLabel.getX()-1, 0);
							if(jLabel.getX()==0) stop =true;
							if(jLabel.getX()==-jLabel.getWidth()) jLabel.setLocation(jLabel.getWidth()*3,0);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		th.start();
	}

}
