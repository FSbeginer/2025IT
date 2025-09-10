import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
	public static int uno = 0;
	public static boolean isAdmin = false;
	
	public static void msgInfo(String name) {
		setUIunset();
		JOptionPane.showMessageDialog(null, name, "정보", 1);
		setUIset();
	}
	public static void msgErr(String name) {
		setUIunset();
		JOptionPane.showMessageDialog(null, name, "경고", 0);
		setUIset();
	}
	
	public static ImageIcon getIcon(String path) {
		return new ImageIcon("./datafiles/"+path);
	}
	public static ImageIcon getIcon(String path, int w,int h) {
		return new ImageIcon(new ImageIcon("./datafiles/"+path).getImage().getScaledInstance(w, h, 1));
	}
	
	public void updateForm() {
		
	}
	public void showPage(JFrame jf, String name) {
		jf.setName(name);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(2);
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
	public void showPage(String name) {
		var st = new Stack<Window>();
		st.addAll(Arrays.asList(Window.getWindows()));
		while(!st.isEmpty()) {
			var w = st.pop();
			if(w.getName().equals(name)) {
				((BF)w).updateForm();
				w.setVisible(true);
				break;
			}
			else {
				w.setName("");
				w.dispose();
			}
		}
	}
	
	public static Connection con;
	public static Statement stmt;
	static {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/lecture?serverTimezone=Asia/Seoul","root","1234");
			stmt = con.createStatement();
			setUIset();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static PreparedStatement pre(String sql) throws SQLException {
		return con.prepareStatement(sql);
	}
	public static void setUIset() {
		UIManager.put("Panel.background", Color.white);
	}
	public static void setUIunset() {
		UIManager.put("Panel.background", null);
	}
	public static void execute(String slq) throws SQLException {
		stmt.execute(slq);
	}
	public static ResultSet res(String sql) throws SQLException {
		return pre(sql).executeQuery();
	}
	
	public static void preSet(PreparedStatement pre,Object...objects) throws SQLException {
		int i = 1;
		for (Object object : objects) {
			pre.setObject(i++, object);
		}
	}
	
}
class Logo extends JLabel{
	public Logo(int w,int h,boolean move) {
		setIcon(BF.getIcon("icon/logo.png",w,h));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(move)
					((BF)SwingUtilities.getWindowAncestor(Logo.this)).showPage("A_메인");
			}
		});
	}
}
class PlaceHolder extends JTextField{
	JLabel jl;
	public PlaceHolder(String txt) {
		jl = new JLabel(txt);
		jl.setEnabled(false);
		setLayout(new BorderLayout());
		add(jl);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		jl.setVisible(getText().isBlank());
	}
}
