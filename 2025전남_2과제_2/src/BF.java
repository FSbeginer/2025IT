import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class BF extends JFrame {
	public static int uno;
	public static boolean isAdmin;
	public static Color blue = new Color(120,150,255).brighter();

	public static void msgInfo(String msg) {
		setUiunset();
		JOptionPane.showMessageDialog(null, msg, "정보", 1);
		setUiset();
	}

	public static void msgErr(String msg) {
		setUiunset();
		JOptionPane.showMessageDialog(null, msg, "경고", 0);
		setUiset();
	}

	public static ImageIcon getIcon(String path) {
		return new ImageIcon("./datafiles/" + path);
	}

	public static ImageIcon getIcon(String path, int w, int h) {
		return new ImageIcon(new ImageIcon("./datafiles/" + path).getImage().getScaledInstance(w, h, 1));
	}

	public void updateForm() {

	}

	public void showPage(JFrame jf, String name) {
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(2);
		jf.setName(name);
		jf.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if (jf.getName().equals(name)) {
					updateForm();
					setVisible(true);
				}
			}
		});
		setVisible(false);
		jf.setVisible(true);
	}

	public void showPage(String name) {
		Stack<Window> st = new Stack<>();
		st.addAll(Arrays.asList(Window.getWindows()));
		while (!st.isEmpty()) {
			var window = st.pop();
			if (window.getName().equals(name)) {
				((BF) window).updateForm();
				window.setVisible(true);
				break;
			}
			else {
				window.setName("s");
				window.dispose();
			}
		}
	}

	public static Connection con;
	public static Statement stmt;

	static {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/lecture?serverTimezone=Asia/Seoul", "root",
					"1234");
			stmt = con.createStatement();
			setUiset();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void execute(String slq) throws SQLException {
		stmt.execute(slq);
	}

	private static void setUiset() {
		UIManager.put("Panel.background", Color.white);
		UIManager.put("ComboBox.background", Color.white);
	}

	private static void setUiunset() {
		UIManager.put("Panel.background", null);
		UIManager.put("ComboBox.background", null);
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
	public static String getIDNumber(LocalDate birth, String gender) {
		String r = birth.toString().replaceAll("-", "")+"-";
		r += birth.isBefore(LocalDate.of(2000, 1, 1))? (gender=="M"? "1" : "2") : (gender=="M"?"3":"4");
		return r;
	}
}
class MainLogo extends JLabel {
	public MainLogo() {
		setIcon(BF.getIcon("icon/logo.png",50,50));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((BF)SwingUtilities.getWindowAncestor(MainLogo.this)).showPage("A_메인");
			}
		});
	}
}
class RoundBorder extends LineBorder{
	public RoundBorder(Color c) {
		super(c);
	}
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(lineColor);
		g.drawRoundRect(x, y, width-thickness, height-thickness, 20, 20);
	}
}
class RoundButton extends JButton {
	public RoundButton(String txt) {
		super(txt);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(false);
		setBorder(null);
	}
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(getBackground());
		g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
		super.paintComponent(g);
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
