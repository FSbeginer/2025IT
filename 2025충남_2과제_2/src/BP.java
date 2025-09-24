import java.awt.Color;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class BP extends JPanel {
	
	
	/**
	 * Create the panel.
	 */
	public static Color blue = new Color(120,150,250).brighter();
	public BP() {
		setSize(998, 551);
		setLayout(null);
	}
	public static ImageIcon getLogoIcon(int w,int h) {
		BufferedImage bi = new BufferedImage(w, h, 2);
		var g = bi.createGraphics();
		
		var logo = getIcon("아이콘/아이콘.png").getImage();
		
		g.drawImage(logo, 0, 0, w, h, 0, 0, logo.getWidth(null)*4/10, logo.getHeight(null), null);
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				Color c = new Color(bi.getRGB(i, j));
				if(c.getRed()>=180&&c.getBlue()>=180&&c.getGreen()>=180) {
					bi.setRGB(i, j, 0);
				}
			}
		}
		return new ImageIcon(bi);
	}
	
	
	public static void msgInfo(String msg) {
		setUIunset();
		JOptionPane.showMessageDialog(null, msg, "정보", 1);
		setUIset();
	}
	public static void msgErr(String msg) {
		setUIunset();
		JOptionPane.showMessageDialog(null, msg, "경고", 0);
		setUIset();
	}
	
	public static ImageIcon getIcon(String path) {
		return new ImageIcon("./datafiles/"+path);
	}
	public static ImageIcon getIcon(byte[] path) {
		return new ImageIcon(path);
	}
	public static ImageIcon getIcon(String path,int w, int h) {
		return new ImageIcon(new ImageIcon("./datafiles/"+path).getImage().getScaledInstance(w, h, 1));
	}
	public static ImageIcon getIcon(byte[] path,int w, int h) {
		return new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(w, h, 1));
	}


	public static Connection con;
	public static Statement stmt;
	
	static {
		try {
			con =  DriverManager.getConnection("jdbc:mysql://localhost/science?serverTimezone=Asia/Seoul", "root", "1234");
			stmt = con.createStatement();
			setUIset();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void execute(String sq) throws SQLException {
		stmt.execute(sq);
	}
	private static void setUIset() {
		UIManager.put("Panel.background", Color.white);
		UIManager.put("Button.background", blue);
		UIManager.put("Button.foreground", Color.white);
	}
	private static void setUIunset() {
		UIManager.put("Panel.background", null);
		UIManager.put("Button.background", null);
		UIManager.put("Button.foreground", null);
	}
	public static PreparedStatement pre(String sql) throws SQLException {
		return con.prepareStatement(sql);
	}
	public static void preSet(PreparedStatement pre, Object...objects) throws SQLException {
		int i = 1;
		for (Object object : objects) {
			pre.setObject(i++, object);
		}
	}
	public static ResultSet res(String sql) throws SQLException {
		return pre(sql).executeQuery();
	}
	
}
