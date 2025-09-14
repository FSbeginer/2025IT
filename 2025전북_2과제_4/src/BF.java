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
	public static String uname;
	public static boolean isAdmin;
	
	public BF() {
		setIconImage(getIcon("logo.png").getImage());
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
	public static ImageIcon getIcon(String path,int w,int h) {
		return new ImageIcon(new ImageIcon("./datafiles/"+path).getImage().getScaledInstance(w, h, 1));
	}
	
	public void updateForm() {
		
	}
	public void showPage(JFrame jf,String name) {
		jf.setName(name);
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
		setVisible(false);
		jf.setVisible(true);
	}
	public static void showPage(String name) {
		var st = new Stack<Window>();
		st.addAll(Arrays.asList(Window.getWindows()));
		while(!st.isEmpty()) {
			var window = st.pop();
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
	public static Color blue = new Color(120,150,255).brighter(); 
	static {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/medinow?serverTimezone=Asia/Seoul", "root", "1234");
			stmt =con.createStatement();
			setUIset();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void execute(String sql) throws SQLException {
		stmt.execute(sql);
	}
	public static void setUIset() {
		UIManager.put("Panel.background", Color.white);
		UIManager.put("Button.background", blue);
		UIManager.put("Button.foreground", Color.white);
		UIManager.put("ComboBox.background", Color.white);
	}
	public static void setUIunset() {
		UIManager.put("Panel.background", null);
		UIManager.put("Button.background",null);
		UIManager.put("Button.foreground", null);
		UIManager.put("ComboBox.background", null);
	}
	public static PreparedStatement pre(String sql) throws SQLException {
		return con.prepareStatement(sql);
	}
	public static ResultSet res(String slq) throws SQLException {
		return pre(slq).executeQuery();
	}
	public static void preSet(PreparedStatement pre,Object...objects) throws SQLException {
		int i = 1;
		for (Object object : objects) {
			pre.setObject(i++, object);
		}
	}
}
