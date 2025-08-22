import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MoviePanelRank extends MoviePanel {

	/**
	 * Create the panel.
	 */
	public MoviePanelRank(ImageIcon img, String txt, int rank) {
		super(img, txt);
		JLabel jl = new JLabel(rank+"");
		jl.setVerticalAlignment(SwingConstants.TOP);
		jl.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		jl.setForeground(Color.white);
		label.setLayout(new BorderLayout());
		jl.setBorder(new EmptyBorder(0, 5, 5, 5));
		label.add(jl);
	}

}
