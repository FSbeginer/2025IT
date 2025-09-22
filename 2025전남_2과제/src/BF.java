import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import javax.swing.border.LineBorder;

public class BF extends JFrame {
	public static int uno;
	public static boolean teacher;
	public static String uname;
	public static Color blue = new Color(120, 150, 255).brighter();
	
	public static void msgInfo(String msg) {
		setUIUnset();
		JOptionPane.showMessageDialog(null, msg, "정보", 1);
		setUIset();
	}
	public static void msgErr(String msg) {
		setUIUnset();
		JOptionPane.showMessageDialog(null, msg, "경고", 0);
		setUIset();
	}
	public static ImageIcon getIcon(String path) {
		return new ImageIcon("./datafiles/"+path);
	}
	public static ImageIcon getIcon(String path, int w, int h) {
		return new ImageIcon(new ImageIcon("./datafiles/"+path).getImage().getScaledInstance(w, h, 1));
	}
	
	public void updateForm() {
		
	}
	
	public void showPage(JFrame jf, String name) {
		setVisible(false);
		jf.setName(name);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		while(!stack.isEmpty()) {
			var window = stack.pop();
			if(window.getName().equals(name)) {
				window.setVisible(true);
				break;
			}
			else {
				window.setName("d");
				window.dispose();
			}
		}
	}
	
	public static void setUIset() {
		UIManager.put("Panel.background", Color.white);
	}
	public static void setUIUnset() {
		UIManager.put("Panel.background", null);
	}
	
	public static Connection con;
	public static Statement stmt;
	static {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/lecture?serverTimezone=Asia/Seoul", "root", "1234");
			stmt = con.createStatement();
			setUIset();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static PreparedStatement pre(String sql) throws SQLException {
		return con.prepareStatement(sql);
	}
	public static void preSet(PreparedStatement pre, Object...objects)throws SQLException {
		int i = 1;
		for (Object object : objects) {
			pre.setObject(i++, object);
		}
	}
	public static void execute(String sql) throws SQLException {
		stmt.execute(sql);
	}
	public static ResultSet res(String sql)  throws SQLException{
		return pre(sql).executeQuery();
	}
}
class RoundBorder extends LineBorder {

	public RoundBorder(Color color) {
		super(color);
	}
	public RoundBorder(Color color, int thinkness) {
		super(color,thinkness);
	}
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(thickness));
		g2.setColor(lineColor);
		g2.drawRoundRect(x+thickness, y+thickness, width-1-thickness, height-1-thickness, 20, 20);
	}
	
}
