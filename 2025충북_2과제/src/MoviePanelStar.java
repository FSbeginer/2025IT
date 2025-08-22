import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class MoviePanelStar extends MoviePanel {
	public JPanel panel;
	public JLabel lblStar;
	public JLabel lbl별점;

	/**
	 * Create the panel.
	 */
	public MoviePanelStar(ImageIcon img, String txt, double star) {
		super(img, txt);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		lblStar = new JLabel("\u2605");
		lblStar.setForeground(Color.YELLOW);
		lblStar.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lblStar.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblStar, BorderLayout.CENTER);
		
		lbl별점 = new JLabel(String.format("%.1f", star));
		lbl별점.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		panel.add(lbl별점, BorderLayout.EAST);
		
	}

}
