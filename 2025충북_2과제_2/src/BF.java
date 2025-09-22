import java.awt.Color;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class BF extends JFrame {
	public static int uno ;
	public static LocalDate ubirth;
	public static boolean isAdmin = false;
	
	public static LocalDate selDate;
	public static LocalTime selTime;
	public static int selScno;
	
	public BF() {
		setIconImage(getIcon("로고1.jpg").getImage());
	}
	
	public static ImageIcon getIcon(String path) {
		return new ImageIcon("./datafiles/"+path);
	}
	public static ImageIcon getIcon(String path, int w, int h) {
		return new ImageIcon(new ImageIcon("./datafiles/"+path).getImage().getScaledInstance(w, h, 1));
	}
	public static void msgInfo(String msg) {
		setUIUnset();
		JOptionPane.showMessageDialog(null, msg, "정보", 1);
		setUISet();
	}
	public static void msgErr(String msg) {
		setUIUnset();
		JOptionPane.showMessageDialog(null, msg, "경고", 0);
		setUISet();
	}
	public void showPage(JFrame jf,String name) {
		jf.setName(name);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(2);
		setVisible(false);
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
		Stack<Window> stack =new Stack<>();
		stack.addAll(Arrays.asList(Window.getWindows()));
		while(!stack.isEmpty()) {
			var window = stack.pop();
			if(window.getName().equals(name)) {
				((BF)window).updateForm();
				window.setVisible(true);
			}
			else {
				window.setName("d");
				window.dispose();
			}
		}
	}
	public static void setUISet() {
		UIManager.put("Panel.background", Color.white);
		UIManager.put("Button.background", Color.blue.darker());
		UIManager.put("Button.foreground", Color.white);
		UIManager.put("ComboBox.background", Color.white);
	}
	public static void setUIUnset() {
		UIManager.put("Panel.background", null);
		UIManager.put("Button.background", null);
		UIManager.put("Button.foreground", null);
		UIManager.put("ComboBox.foreground", null);
	}
	public void updateForm() {
		
	}
	
	public static Connection con;
	public static Statement stmt;
	static {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/moviedb?serverTimezone=Asia/Seoul", "root",
					"1234");
			stmt = con.createStatement();
			setUISet();
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

	public static void execute(String slq) throws SQLException {
		stmt.execute(slq);
	}

	public static void preSet(PreparedStatement pre, Object... objects) throws SQLException {
		int i = 1;
		for (Object object : objects) {
			pre.setObject(i++, object);
		}
	}
	public static int getAge(LocalDate birth) {
		LocalDate now = LocalDate.now();
		int age = now.getYear()- birth.getYear();
		if(birth.plusYears(age).isAfter(now)) {
			age--;
		}
		return age;
	}

}
