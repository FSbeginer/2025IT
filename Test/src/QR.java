import java.awt.Color;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;

public class QR extends JFrame {

	private JPanel contentPane;
	public JLabel label;

	public static void main(String[] args) {
		EventQueue.invokeLater(QR::new);
	}

	public QR() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		label = new JLabel(getQr(150, 150));
		label.setBorder(new LineBorder(Color.black));
		label.setBounds(57, 55, 150, 150);
		contentPane.add(label);
		setVisible(true);
	}

	private ImageIcon getQr(int w, int h) {
		BufferedImage bi = new BufferedImage(90, 90, 2);
		Random rand = new Random();
		var g = bi.createGraphics();
		for (int x = 0; x < 90; x += 2) {
			int cnt = 0, dy = 1;
			for (int y = 0; y < 90; y += dy) {
				dy = 1;
				if (rand.nextBoolean() && cnt < 30) {
					int rgb = Color.black.getRGB();
					int nextX = Math.min(x + 1, 89);
					int nextY = Math.min(y + 1, 89);
					bi.setRGB(x, y, rgb);
					bi.setRGB(nextX, y, rgb);
					bi.setRGB(nextX, nextY, rgb);
					bi.setRGB(x, nextY, rgb);
					dy = 2;
					cnt++;
				}
			}
		}
		
		BufferedImage dest = new BufferedImage(w, h, 2);
		dest.createGraphics().drawImage(bi, 0, 0, w, h, 0, 0, 90, 90, null);
		
		return new ImageIcon(dest);
	}

}
