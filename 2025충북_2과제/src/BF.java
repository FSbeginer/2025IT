import java.awt.BorderLayout;
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
import java.util.Arrays;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class BF extends JFrame {
	
	public static int uno;
	public static boolean isAdmin;
	
	public BF() {
		setIconImage(getIcon("로고1.jpg").getImage());
		getContentPane().setLayout(null);
	}
	
	public static ImageIcon getIcon(String path) {
		return new ImageIcon("./datafiles/"+path);
	}
	public static ImageIcon getIcon(byte[] path) {
		return new ImageIcon(path);
	}
	public static ImageIcon getIcon(String path, int w, int h) {
		return new ImageIcon(new ImageIcon("./datafiles/"+path).getImage().getScaledInstance(w, h, 1));
	}
	public static ImageIcon getIcon(byte[] data, int w, int h) {
		return new ImageIcon(new ImageIcon(data).getImage().getScaledInstance(w, h, 1));
	}
	
	
	public static void msgInfo(String msg) {
		setUINull();
		JOptionPane.showMessageDialog(null, msg, "정보", 1);
		setUISet();
	}
	private static void setUISet() {
		UIManager.put("Panel.background", Color.white);
		UIManager.put("Button.background", Color.blue.darker());
		UIManager.put("Button.foreground", Color.white);
		UIManager.put("ComboBox.background", Color.white);
	}

	private static void setUINull() {
		UIManager.put("Panel.background", null);
		UIManager.put("Button.background", null);
		UIManager.put("Button.foreground", null);
		UIManager.put("ComboBox.background", null);
	}

	public static void msgErr(String msg) {
		setUINull();
		JOptionPane.showMessageDialog(null, msg, "경고", 0);
		setUISet();
	}
	public void showPage(JFrame next, String name) {
		next.setName(name);
		next.setLocationRelativeTo(null);
		next.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if(next.getName().equals(name)) {
					updateForm();
					setVisible(true);
				}
			}
		});
		setVisible(false);
		next.setVisible(true);
	}
	public void showPage(String name) {
		Stack<Window> windows = new Stack<>();
		windows.addAll(Arrays.asList(Window.getWindows()));
		while (!windows.isEmpty()) {
			var window = windows.pop();
			if(window.getName().equals(name)) {
				window.setVisible(true);
				break;
			}
			else {
				window.setName("close");
				window.dispose();
			}
		}
	}
	
	public void updateForm() {
		
	}
	
	public static Connection con;
	public static Statement stmt;
	
	static {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/moviedb?serverTimezone=Asia/Seoul", "root", "1234");
			stmt = con.createStatement();
			setUISet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void execute(String sql) throws SQLException {
		stmt.execute(sql);
	}
	public static PreparedStatement pre(String slq) throws SQLException {
		return con.prepareStatement(slq);
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
	public static int getAge(Date date) {
		LocalDate birth = date.toLocalDate();
		int age = LocalDate.now().getYear() - birth.getYear();
		if(birth.plusYears(age).isAfter(LocalDate.now())) {
			age--;
		}
		return age;
	}
	
}
class BackgroundImageLabel extends JLabel{
	JLabel jl = new JLabel();
	public BackgroundImageLabel(ImageIcon img, String txt, int horizon) {
		setLayout(new BorderLayout());
		jl.setText(txt);
		jl.setHorizontalAlignment(horizon);
		add(jl);
		setIcon(img);
	}
}
