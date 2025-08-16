import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Font;

public class BP extends JPanel {

	public static Stack<Object[]> prevPage = new Stack<Object[]>();
	
	public BP() {
		setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		setSize(892, 492);
		setBackground(Color.white);
	}
	
	public static void msgInfo(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Á¤º¸", 1);
	}

	public static void msgErr(String msg) {
		JOptionPane.showMessageDialog(null, msg, "°æ°í", 0);
	}

	public static ImageIcon getIcon(String path) {
		return new ImageIcon("./datafiles/" + path);
	}

	public static ImageIcon getIcon(byte[] path) {
		return new ImageIcon(path);
	}

	public static ImageIcon getIcon(String path, int w, int h) {
		return new ImageIcon(new ImageIcon("./datafiles/" + path).getImage().getScaledInstance(w, h, 1));
	}

	public static ImageIcon getIcon(byte[] path, int w, int h) {
		return new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(w, h, 1));
	}
	public static Statement stmt;
	public static Connection con;
	static {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/science?serverTimezone=Asia/Seoul", "root",
					"1234");
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static PreparedStatement pre(String sql) throws SQLException {
		return con.prepareStatement(sql);
	}

	public static ResultSet res(String sql) throws SQLException {
		return pre(sql).executeQuery();
	}

	public static void execute(String sql) throws SQLException {
		stmt.execute(sql);
	}

	public static void preSet(PreparedStatement pre, Object... objects) throws SQLException {
		int i = 1;
		for (Object object : objects) {
			pre.setObject(i++, object);
		}
	}
}
