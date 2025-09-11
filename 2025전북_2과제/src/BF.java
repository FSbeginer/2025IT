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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

public class BF extends JFrame {

	public static int uno = 1;
	public static Color blue = new Color(120, 150, 250).brighter();
	
	public void updateForm() {
		setIconImage(getIcon("logo.png").getImage());
	}
	public void showPage(JFrame jf, String name) {
		jf.setName(name);
		setVisible(false);
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
		Stack<Window> stack = new Stack<>();
		stack.addAll(Arrays.asList(Window.getWindows()));
		while (!stack.isEmpty()) {
			var jf = stack.pop();
			if(jf.getName().equals(name))
				break;
			else {
				jf.setName("close");
				jf.dispose();
			}
		}
	}
	
	public static void msgErr(String msg) {
		unSetUi();
		JOptionPane.showMessageDialog(null, msg, "경고", 0);
		setUI();
	}

	public static void msgInfo(String msg) {
		unSetUi();
		JOptionPane.showMessageDialog(null, msg, "정보", 1);
		setUI();
	}

	public static ImageIcon getIcon(String path) {
		return new ImageIcon("./datafiles/" + path);
	}

	public static ImageIcon getIcon(byte[] data) {
		return new ImageIcon(data);
	}

	public static ImageIcon getIcon(String path, int w, int h) {
		return new ImageIcon(new ImageIcon("./datafiles/" + path).getImage().getScaledInstance(w, h, 1));
	}

	public static ImageIcon getIcon(byte[] data, int w, int h) {
		return new ImageIcon(new ImageIcon(data).getImage().getScaledInstance(w, h, 1));
	}

	public static void setUI() {
		UIManager.put("Panel.background", Color.white);
		UIManager.put("ComboBox.background", Color.white);
		UIManager.put("Button.background", blue);
		UIManager.put("Button.foreground", Color.white);
	}

	public static void unSetUi() {
		UIManager.put("Panel.background", null);
		UIManager.put("ComboBox.background", null);
		UIManager.put("Button.background", null);
		UIManager.put("Button.foreground", null);
	}

	public static Connection con;
	public static Statement stmt;
	static {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/medinow?serverTimezone=Asia/Seoul", "root", "1234");
			stmt = con.createStatement();
			setUI();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void execute(String sql) throws SQLException {
		stmt.execute(sql);
	}

	public static PreparedStatement pre(String sql) throws SQLException {
		return con.prepareStatement(sql);
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
}
class LineTextField extends JTextField{
	public LineTextField() {
		setBorder(new MatteBorder(0, 0, 1, 0, BF.blue));
	}
}
