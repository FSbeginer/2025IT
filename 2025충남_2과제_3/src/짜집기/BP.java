package 짜집기;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class BP extends JPanel {

	/**
	 * Create the panel.
	 */
	public BP() {
		setSize(962, 496);
		setLayout(null);
	}
	public static void msgInfo(String msg) {
		setUiunset();
		JOptionPane.showMessageDialog(null, msg, "정보", 1);
		setUiset();
	}
	public static void msgErr(String msg) {
		setUiunset();
		JOptionPane.showMessageDialog(null, msg, "경고", 0);
		setUiset();
	}
	
	public static ImageIcon getIcon(String path) {
		return new ImageIcon("./datafiles/"+path);
	}
	public static ImageIcon getIcon(String path, int w,int h) {
		return new ImageIcon(new ImageIcon("./datafiles/"+path).getImage().getScaledInstance(w, h, 1));
	}
	public static ImageIcon getIcon(byte[] path) {
		return new ImageIcon(path);
	}
	public static ImageIcon getIcon(byte[] path,int w,int h) {
		return new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(w, h, 1));
	}
	
	
	public static Connection con;
	public static Statement stmt;
	static {	
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/science?serverTimezone=Asia/Seoul", "root", "1234");
			stmt = con.createStatement();
			setUiset();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private static void setUiset() {
		UIManager.put("Panel.background", Color.white);
		UIManager.put("ComboBox.background", Color.white);
	}
	public static void setUiunset() {
		UIManager.put("Panel.background", null);
	}
	public static void execute(String sql) throws SQLException {
		stmt.execute(sql);
	}
	public static PreparedStatement	pre(String sql) throws SQLException {
		return con.prepareStatement(sql);
	}
	public static ResultSet res(String slq ) throws SQLException {
		return pre(slq).executeQuery();
	}
	public static void preSet(PreparedStatement pre,Object...objects) throws SQLException {
		int i = 1;
		for (Object object : objects) {
			pre.setObject(i++, object);
		}
	}

}
