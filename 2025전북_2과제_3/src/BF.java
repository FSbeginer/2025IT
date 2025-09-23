import java.awt.Color;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.UIManager;

public class BF extends JFrame {
	public static int uno;
	public static String uname ;
	public static boolean isAdmin = false;
	public static Color blue = new Color(120, 150, 255).brighter();
	public BF() {
		setIconImage(new ImageIcon("./datafiles/logo.png").getImage());
	}
	
	public static void msgInfo(String smg) {
		setUIUnset();
		JOptionPane.showMessageDialog(null, smg, "정보", 1);
		setUIset();
	}
	public static void msgErr(String smg) {
		setUIUnset();
		JOptionPane.showMessageDialog(null, smg, "경고", 0);
		setUIset();
	}
	public static ImageIcon getIcon(String path) {
		return new ImageIcon("./datafiles/"+path);
	}
	public static ImageIcon getIcon(String path,int w,int h) {
		return new ImageIcon(new ImageIcon("./datafiles/"+path).getImage().getScaledInstance(w, h, 1));
	}
	
	public void updateForm() {
		
	}
	public void showPage(JFrame jf, String name) {
		jf.setName(name);
		setVisible(false);
		jf.setDefaultCloseOperation(2);
		jf.setLocationRelativeTo(null);
		jf.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if(jf.getName().equals(name)) {
					updateForm();
					setVisible(true);
				}
			}
		});
		jf.setVisible(true);
	}
	public void showPage(String name) {
		var stack = new Stack<Window>();
		stack.addAll(Arrays.asList(Window.getWindows()));
		while(!stack.isEmpty()) {
			var window=  stack.pop();
			if(window.getName().equals(name)) {
				((BF)window).updateForm();
				window.setVisible(true);
				break;
			}
			else {
				window.setName("");
				window.dispose();
			}
		}
	}
	
	public static Connection con;
	public static Statement stmt;
	
	
	static {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/medinow?serverTimezone=Asia/Seoul", "root", "1234");
			stmt = con.createStatement();
			setUIset();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void execute(String sql) throws SQLException {
		stmt.execute(sql);
	}
	private static void setUIset() {
		UIManager.put("Panel.background", Color.white);
		UIManager.put("ComboBox.background", Color.white);
		UIManager.put("Button.background", blue);
		UIManager.put("Button.foreground", Color.white);
	}
	private static void setUIUnset() {
		UIManager.put("Panel.background", null);
		UIManager.put("ComboBox.background", null);
		UIManager.put("Button.background", null);
		UIManager.put("Button.foreground", null);
	}
	

	public static PreparedStatement pre(String sql) throws SQLException	{
		return con.prepareStatement(sql);
	}
	
	public static ResultSet res(String sql) throws SQLException {
		return pre(sql).executeQuery();
	}
	
	public static void preSet(PreparedStatement pre, Object...objects) throws SQLException {
		int i = 1;
		for (Object object : objects) {
			pre.setObject(i++, object);
		}
	}
	
}
