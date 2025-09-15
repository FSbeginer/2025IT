import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class A_순위패널 extends A_패널 {

	/**
	 * Create the panel.
	 * @param img 
	 * @param txt 
	 */
	public A_순위패널(ImageIcon img, String txt,int rank) {
		super(img,txt);
		
		JLabel jl = new JLabel(" "+rank);
		jl.setFont(new Font("맑은 고딕", 1, 35));
		jl.setVerticalAlignment(SwingConstants.TOP);
		jl.setForeground(Color.white);;
		label.setLayout(new BorderLayout());
		label.add(jl);
	}

}
