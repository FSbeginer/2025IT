import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class 배경색제거1 extends JFrame {
	Image org;
	BufferedImage bi;
	Graphics2D g2d;
	
	public 배경색제거1() {
		ui1();
		ui2();
		setVisible(true);
	}
	
	void ui1() {
		setSize(600, 600);
		setDefaultCloseOperation(2);
		setLocationRelativeTo(null);
	}
	
	void ui2() {
		JPanel jp = new JPanel();
		add(jp);
		
		//배경 제거
		try {
			org = ImageIO.read(new File("./datafiles/아이콘/아이콘.png"));
			bi = new BufferedImage(org.getWidth(null), org.getHeight(null), BufferedImage.TYPE_INT_ARGB);
			g2d = bi.createGraphics();
			g2d.drawImage(org, 0, 0, null);
			
			for (int y = 0; y < bi.getHeight(); y++) {
				for (int x = 0; x < bi.getWidth(); x++) {
					int rgba = bi.getRGB(x, y);
					Color col = new Color(rgba, true);
					if (col.getRed() > 180 && col.getGreen() > 180 && col.getBlue() > 180) {
						//알파값을 0(투명)으로 처리
						col = new Color(255, 255, 255, 0);
					}
					bi.setRGB(x, y, col.getRGB());
				}
			}
		} catch (IOException e) {
		}
		
		JLabel jl = new JLabel(new ImageIcon(bi.getScaledInstance(200, 100, Image.SCALE_SMOOTH)));
		
		jp.add(jl);
		jp.setBackground(Color.PINK);
	}
	
	public static void main(String[] args) {
		new 배경색제거1();
	}
}
