import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class A_랭킹패널 extends A_패널 {

	/**
	 * Create the panel.
	 */
	public A_랭킹패널(ImageIcon img, String name, int rank) {
		super(img, name);
		label.setLayout(new BorderLayout());
		JLabel jl = new JLabel(rank+"");
		jl.setVerticalAlignment(SwingConstants.TOP);
		jl.setFont(new Font("맑은 고딕",1, 40));
		jl.setForeground(Color.white);
		label.add(jl);
	}

}
