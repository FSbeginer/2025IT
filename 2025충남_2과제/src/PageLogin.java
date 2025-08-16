import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

public class PageLogin extends BP {
	public JLabel label;

	public PageLogin() {
		setName("로그인");
		setLayout(new BorderLayout(0, 0));

		label = new JLabel(getIcon("메인/5.png", getWidth(), getHeight()));
		add(label, BorderLayout.CENTER);
		LoginPanel lp = new LoginPanel();
		lp.setLocation(getWidth()/2-lp.getWidth()/2-30, getHeight()/2-lp.getHeight()/2);
		label.add(lp);
	}

}
