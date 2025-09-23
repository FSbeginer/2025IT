import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class BF extends JFrame {
	public static int uno ;
	public static boolean isAdmin = false;
	public static Color orange = new Color(255, 128, 0);
	
	public BF() {
		setIconImage(getIcon("icon/icon.png").getImage());
	}
	
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
	public static ImageIcon getIcon(String path,int w, int h) {
		return new ImageIcon(new ImageIcon("./datafiles/"+path).getImage().getScaledInstance(w, h, 1));
	}
	public void updateForm() {
		
	}
	public void showPage(JFrame jf, String name) {
		jf.setName(name);
		jf.setDefaultCloseOperation(2);
		jf.setLocationRelativeTo(null);
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
		var stack = new Stack<Window>();
		stack.addAll(Arrays.asList(Window.getWindows()));
		while(!stack.isEmpty()) {
			var window = stack.pop();
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
			con = DriverManager.getConnection("jdbc:mysql://localhost/parttimecat?serverTimezone=Asia/Seoul", "root", "1234");
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
		UIManager.put("Button.background", orange);
		UIManager.put("Button.foreground", Color.white);
	}
	private static void setUIUnset() {
		UIManager.put("Panel.background", null);
		UIManager.put("ComboBox.background", null);
		UIManager.put("Button.background", null);
		UIManager.put("Button.foreground", null);
	}
	
	
	public static PreparedStatement pre(String sql) throws SQLException {
		return con.prepareStatement(sql);
	}
	public static ResultSet res(String sql) throws SQLException {
		return pre(sql).executeQuery();
	}
	public static void preSet(PreparedStatement pre,Object...objects) throws SQLException {
		int  i = 1;
		for (Object object : objects) {
			pre.setObject(i++, object);
		}
	}
	
	public static String getGrade(int grade) {
		return grade ==0 ?"무관": grade==1?"대학" : "고등";
	}
	
}
class MainLogo extends JLabel {
	public MainLogo(int w, int h) {
		setIcon(BF.getIcon("icon/cat.png",w,h));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((BF)SwingUtilities.getWindowAncestor(MainLogo.this)).showPage("B_메인");
			}
		});
	}
}
class PlaceHolder extends JTextField {
	JLabel jl;
	public PlaceHolder(String txt) {
		jl = new JLabel(txt);
		jl.setEnabled(false);
		jl.setFont(new Font("맑은 고딕", 1, 13));
		setLayout(new BorderLayout());
		add(jl);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		jl.setVisible(getText().isBlank());
	}
}
