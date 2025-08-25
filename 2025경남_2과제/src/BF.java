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
	public static int uno = 1;
	public static int ugrade = 1;
	public static int ugender = 1;
	public static Color orange = new Color(255,128,0);
	public BF() {
		setIconImage(new ImageIcon("./datafiles/icon/icon.png").getImage());
	}
	public static void setUISet() {
		UIManager.put("Panel.background", Color.white);
		UIManager.put("ComboBox.background", Color.white);
		UIManager.put("Button.background", orange);
		UIManager.put("Button.foreground", Color.white);
	}
	public static void setUIUnset() {
		UIManager.put("Panel.background", null);
		UIManager.put("ComboBox.background",null);
		UIManager.put("Button.background", null);
		UIManager.put("Button.foreground", null);
	}
	public static void msgInfo(String msg) {
		setUIUnset();
		JOptionPane.showMessageDialog(null, msg, "¡§∫∏", 1);
		setUISet();
	}
	public static void msgErr(String msg) {
		setUIUnset();
		JOptionPane.showMessageDialog(null, msg, "∞Ê∞Ì", 0);
		setUISet();
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
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		jf.setName(name);
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
	
	
	public static Connection con;
	public static Statement stmt;
	static {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/parttimecat?serverTimezone=Asia/Seoul", "root", "1234");
			stmt = con.createStatement();
			setUISet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void execute(String sql)throws SQLException {
		stmt.execute(sql);
	}
	public static PreparedStatement pre(String slq) throws SQLException {
		return con.prepareStatement(slq);
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
class PlaceHolder extends JTextField {
	JLabel jl;
	public PlaceHolder(String txt) {
		jl = new JLabel(txt);
		jl.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 12));
		jl.setForeground(Color.LIGHT_GRAY);
		setLayout(new BorderLayout());
		add(jl);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(getText().isBlank())
			jl.setVisible(true);
		else
			jl.setVisible(false);
	}
}
class MainIcon extends JLabel {
	public MainIcon() {
		setIcon(BF.getIcon("icon/cat.png", 120, 55));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((BF)SwingUtilities.getWindowAncestor(MainIcon.this)).showPage("B_∏ﬁ¿Œ");
			}
		});
	}
}