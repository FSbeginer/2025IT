import java.awt.Color;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class BP extends JPanel {

	/**
	 * Create the panel.
	 */
	public BP() {
		setSize(1007, 514);
		setLayout(null);
	}

	public static ImageIcon getIcon(String path) {
		return new ImageIcon("./datafiles/"+path);
	}
	public static ImageIcon getIcon(String path,int w,int h) {
		return new ImageIcon(new ImageIcon("./datafiles/"+path).getImage().getScaledInstance(w, h, 1));
	}
	public static ImageIcon getIcon(byte[] path) {
		return new ImageIcon(path);
	}
	public static ImageIcon getIcon(byte[] path,int w,int h) {
		return new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(w, h, 1));
	}
	
	public static ImageIcon getLogo(int w, int h) {
		BufferedImage bi = new BufferedImage(w,h,2);
		var g = bi.createGraphics();
		g.drawImage(getIcon("아이콘/아이콘.png",w,h).getImage(),0, 0, w, h, 0, 0, w*4/10, h, null);
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
		UIManager.put("Panel.background", null);
		JOptionPane.showMessageDialog(null, msg, "정보", 1);
		UIManager.put("Panel.background", Color.white);
	}
	public static void msgErr(String msg) {
		UIManager.put("Panel.background", null);
		JOptionPane.showMessageDialog(null, msg, "경고", 0);
		UIManager.put("Panel.background", Color.white);
	}
	
	public static Connection con;
	public static Statement stmt;
	static {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/science?serverTimezone=Asia/Seoul", "root", "1234");
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
	public static void preSet(PreparedStatement pre, Object...objects) throws SQLException {
		int i = 1;
		for (Object object : objects) {
			pre.setObject(i++, object);
		}
	}
	public static MainFrame getmf(Component comp) {
		return (MainFrame) SwingUtilities.getWindowAncestor(comp);
	}

}
