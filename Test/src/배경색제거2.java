import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class 배경색제거2 extends JFrame {
	BufferedImage org;
	BufferedImage bi;
	
	public 배경색제거2() {
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
			bi = new BufferedImage(org.getWidth(), org.getHeight(), BufferedImage.TYPE_INT_ARGB);
		} catch (IOException e) {
		}
		
		for (int y = 0; y < org.getHeight(); y++) {
            for (int x = 0; x < org.getWidth(); x++) {
                int rgba = org.getRGB(x, y);
                Color col = new Color(rgba, true);
                if (col.getRed() > 180 && col.getGreen() > 180 && col.getBlue() > 180) {
                	//알파값을 0(투명)으로 처리
                    col = new Color(255, 255, 255, 0);
                }
                bi.setRGB(x, y, col.getRGB());
            }
        }
		
		Image img = bi.getScaledInstance(200, 100, Image.SCALE_SMOOTH);
		JLabel jl = new JLabel(new ImageIcon(img));
		
		jp.add(jl);
		jp.setBackground(Color.PINK);
	}
	
	public static void main(String[] args) {
		new 배경색제거2();
	}
}
