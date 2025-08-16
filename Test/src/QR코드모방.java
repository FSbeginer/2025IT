import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class QR코드모방 extends JFrame {
	int size = 400, cnt=40, unit = size/cnt;
	BufferedImage bi = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
	Graphics2D g2d = bi.createGraphics();
	
	public QR코드모방() {
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
		
		//가짜 QR코드
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0,  0, bi.getWidth(), bi.getHeight());
		g2d.setColor(Color.BLACK);
		
		List<Integer> nums;
		nums = IntStream.range(0, 40).boxed().collect(Collectors.toList());
		for (int r = 0; r < cnt; r++) {
			Collections.shuffle(nums);
			for (int c = 0; c < 25; c++) {
				g2d.fillRect(nums.get(c)*unit, r*unit, unit, unit);
			}
		}
		
		JLabel jl = new JLabel(new ImageIcon(bi));
		jp.add(jl);
	}
	
	
	public static void main(String[] args) {
		new QR코드모방();
	}
}
