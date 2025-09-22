import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class A_순위패널 extends A_패널 {

	/**
	 * Create the panel.
	 */
	public A_순위패널(ImageIcon img, String txt, int rank) {
		super(img, txt);
		
		JLabel jl = new JLabel();
		jl.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		jl.setForeground(Color.white);
		jl.setText(rank+"");
		jl.setVerticalAlignment(SwingConstants.TOP);
		label.setLayout(new BorderLayout());
		label.add(jl);
	}

}
