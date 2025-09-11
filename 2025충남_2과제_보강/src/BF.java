import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class BF extends JFrame {
	public static int uno=1;
	public static boolean isAdmin;
	public static Color blue = new Color(120, 150, 255).brighter();

	public BF() {
		setIconImage(getLogo(100, 100).getImage());
	}

	public void updateForm() {

	}

	public void showpage(JFrame jf, String name) {
		jf.setDefaultCloseOperation(2);
		jf.setLocationRelativeTo(null);
		jf.setName(name);
		setVisible(false);
		jf.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if (jf.getName().equals(name)) {
					updateForm();
					setVisible(true);
				}
			}
		});
		jf.setVisible(true);
	}

	public static ImageIcon getIcon(String path) {
		return new ImageIcon("./datafiles/" + path);
	}

	public static ImageIcon getIcon(String path, int w, int h) {
		return new ImageIcon(new ImageIcon("./datafiles/" + path).getImage().getScaledInstance(w, h, 1));
	}

	public static ImageIcon getIcon(byte[] path) {
		return new ImageIcon(path);
	}

	public static ImageIcon getIcon(byte[] path, int w, int h) {
		return new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(w, h, 1));
	}

	public static ImageIcon getLogo(int w, int h) {
		BufferedImage bi = new BufferedImage(w, h, 2);
		var g = bi.createGraphics();
		g.drawImage(getIcon("아이콘/아이콘.png", w, h).getImage(), 0, 0, w, h, 0, 0, w * 4 / 10, h, null);
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				Color c = new Color(bi.getRGB(i, j));
				if (c.getRed() >= 180 && c.getBlue() >= 180 && c.getGreen() >= 180) {
					bi.setRGB(i, j, 0);
				}
			}
		}
		return new ImageIcon(bi);
	}

	public static void msgInfo(String msg) {
		UIManager.put("Panel.background", null);
		JOptionPane.showMessageDialog(null, msg, "정보", 1);
		UIManager.put("Panel.background", Color.white);
	}

	public static void msgErr(String msg) {
		UIManager.put("Panel.background", null);
		JOptionPane.showMessageDialog(null, msg, "경고", 0);
		UIManager.put("Panel.background", Color.white);
	}

	public static ImageIcon getIcon() {
		BufferedImage bi = new BufferedImage(24, 24, 2);
		var g = bi.createGraphics();
		g.setColor(Color.green);
		g.fillOval(0, 0, 24, 24);
		g.setColor(Color.white);
		g.fillOval(3, 3, 18, 18);
		return new ImageIcon(bi);
	}

	public static Connection con;
	public static Statement stmt;
	static {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/science?serverTimezone=Asia/Seoul", "root",
					"1234");
			stmt = con.createStatement();
			UIManager.put("Panel.background", Color.white);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void execute(String slq) throws SQLException {
		stmt.execute(slq);
	}

	public static PreparedStatement pre(String slq) throws SQLException {
		return con.prepareStatement(slq);
	}

	public static ResultSet res(String slq) throws SQLException {
		return pre(slq).executeQuery();
	}

	public static void preSet(PreparedStatement pre, Object... objects) throws SQLException {
		int i = 1;
		for (Object object : objects) {
			pre.setObject(i++, object);
		}
	}
	
	public static ImageIcon getQr(int w, int h) {
		BufferedImage bi = new BufferedImage(400, 400, 2);
		var g = bi.createGraphics();
		g.setColor(Color.black);
		var list = IntStream.range(0, 40).boxed().collect(Collectors.toList());
		for (int i = 0; i < 40; i++) {
			Collections.shuffle(list);
			for (int j = 0; j < 25; j++) {
				g.fillRect(list.get(j)*10, i*10, 10, 10);
			}
		}
		return new ImageIcon(bi.getScaledInstance(w, h, 1));
	}
}

class PlaceHolder extends JTextField {
	JLabel jl;

	public PlaceHolder(String txt) {
		jl = new JLabel(txt, 0);
		jl.setEnabled(false);
		jl.setFont(new Font("맑은 고딕", 1, 12));
		setLayout(new BorderLayout());
		add(jl);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		jl.setVisible(getText().isBlank());
	}
}

class RoundButton extends JButton {
	public RoundButton(String txt) {
		super(txt);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusPainted(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(getBackground());
		g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
		super.paintComponent(g);
	}

}
