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
import javax.swing.UIManager;

public class BF extends JFrame {
	public static int uno;
	public static boolean isAdmin;

	public BF() {
		setIconImage(getIcon("로고1.jpg").getImage());
	}

	public static ImageIcon getIcon(String path) {
		return new ImageIcon("./datafiles/" + path);
	}

	public static ImageIcon getIcon(String path, int w, int h) {
		return new ImageIcon(new ImageIcon("./datafiles/" + path).getImage().getScaledInstance(w, h, 1));
	}

	public static void msgInfo(String msg) {
		setUiUnSet();
		JOptionPane.showMessageDialog(null, msg, "정보", 1);
		setUiSet();
	}

	public static void msgErr(String msg) {
		setUiUnSet();
		JOptionPane.showMessageDialog(null, msg, "경고", 0);
		setUiSet();
	}

	public static int msgCon(String title, String msg) {
		setUiUnSet();
		int r = JOptionPane.showConfirmDialog(null, msg, title, JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		setUiSet();
		return r;
	}

	public void updateForm() {
		
	}

	public void showPage(JFrame jf, String name) {
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

	public static Connection con;
	public static Statement stmt;
	static {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/moviedb?serverTimezone=Asia/Seoul", "root",
					"1234");
			stmt = con.createStatement();
			setUiSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void setUiSet() {
		UIManager.put("Panel.background", Color.white);
		UIManager.put("ComboBox.background", Color.white);
		UIManager.put("Button.background", Color.blue.darker());
		UIManager.put("Button.foreground", Color.white);
	}

	public static void setUiUnSet() {
		UIManager.put("Panel.background", null);
		UIManager.put("ComboBox.background", null);
		UIManager.put("Button.background", null);
		UIManager.put("Button.foreground", null);
	}

	public static void execute(String sql) throws SQLException {
		stmt.execute(sql);
	}

	public static PreparedStatement pre(String sql) throws SQLException {
		return con.prepareStatement(sql);
	}

	public static ResultSet res(String sql) throws SQLException {
		return pre(sql).executeQuery();
	}

	public static void preSet(PreparedStatement pre, Object... objects) throws SQLException {
		int i = 1;
		for (Object object : objects) {
			pre.setObject(i++, object);
		}
	}

}
