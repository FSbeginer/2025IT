import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;


public class BF extends JFrame {
	
	public static Stack<JPanel> prevPage = new Stack<>();
	public static Stack<String> prevtitle = new Stack<>();
	public static int uno = 0;
	public static boolean isAdmin = false;
	public static Color blue = new Color(120,150,250).brighter();
	
	public BF() {
		setIconImage(getLogoIcon(100, 100).getImage());
	}
	
	public static ImageIcon getLogoIcon(int w,int h) {
		BufferedImage bi = new BufferedImage(w, h, 2);
		var g = bi.createGraphics();
		
		var logo = getIcon("아이콘/아이콘.png").getImage();
		
		g.drawImage(logo, 0, 0, w, h, 0, 0, logo.getWidth(null)*4/10, logo.getHeight(null), null);
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				Color c = new Color(bi.getRGB(i, j));
				if(c.getRed()>=180&&c.getBlue()>=180&&c.getGreen()>=180) {
					bi.setRGB(i, j, 0);
				}
			}
		}
		return new ImageIcon(bi);
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
	public static ImageIcon getIcon(byte[] path) {
		return new ImageIcon(path);
	}
	public static ImageIcon getIcon(String path,int w, int h) {
		return new ImageIcon(new ImageIcon("./datafiles/"+path).getImage().getScaledInstance(w, h, 1));
	}
	public static ImageIcon getIcon(byte[] path,int w, int h) {
		return new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(w, h, 1));
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
		Stack<Window> stack = new Stack<>();
		stack.addAll(Arrays.asList(Window.getWindows()));
		while(stack.size()>0) {
			var window= stack.pop();
			if(window.getName().equals(name)) {
				((BF)window).updateForm();
				setVisible(true);
				break;
			}else {
				window.setName("d");
				window.dispose();
			}
		}
	}
	
	public static Connection con;
	public static Statement stmt;
	static {
		try {
			con =  DriverManager.getConnection("jdbc:mysql://localhost/science?serverTimezone=Asia/Seoul", "root", "1234");
			stmt = con.createStatement();
			setUIset();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void execute(String sq) throws SQLException {
		stmt.execute(sq);
	}
	private static void setUIset() {
		UIManager.put("Panel.background", Color.white);
		UIManager.put("Button.background", blue);
		UIManager.put("Button.foreground", Color.white);
	}
	private static void setUIunset() {
		UIManager.put("Panel.background", null);
		UIManager.put("Button.background", null);
		UIManager.put("Button.foreground", null);
	}
	public static PreparedStatement pre(String sql) throws SQLException {
		return con.prepareStatement(sql);
	}
	public static void preSet(PreparedStatement pre, Object...objects) throws SQLException {
		int i = 1;
		for (Object object : objects) {
			pre.setObject(i++, object);
		}
	}
	public static ResultSet res(String sql) throws SQLException {
		return pre(sql).executeQuery();
	}
	public static ImageIcon getQrCode(int width,int h) {
		int w = 400, cnt = 40, unit = 10;
		BufferedImage bi = new BufferedImage(400,400,2);
		
		var g = bi.createGraphics();
		g.setColor(Color.black);
		var list = IntStream.range(0, 40).boxed().collect(Collectors.toList());
		for (int i = 0; i < 40; i++) {
			Collections.shuffle(list);
			for (int j = 0; j < 25; j++) {
				g.fillRect(unit*i, list.get(j)*unit, 10, 10);
			}
		}
		
		BufferedImage dest = new BufferedImage(width, h, 2);
		dest.createGraphics().drawImage(bi, 0, 0, width, h, 0, 0, 400, 400, null);
		return new ImageIcon(dest);
	}
}
class PlaceHolder extends JTextField {
	JLabel jl;
	public PlaceHolder(String txt) {
		jl = new JLabel(txt, 0);
		jl.setEnabled(false);
		jl.setFont(new Font("맑은 고딕",1,13));
		setBorder(new LineBorder(Color.black));
		setLayout(new BorderLayout());
		add(jl);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		jl.setVisible(getText().isBlank());
	}
}
class RoundButton extends JButton{
	public RoundButton(String txt) {
		super(txt);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setBorderPainted(false);
	}
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(getBackground());
		g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
		super.paintComponent(g);
	}
}
class MyButton extends JToggleButton{
	public MyButton(String txt) {
		super(txt);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(true);
	}
}
