package 짜집기;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class BF extends JFrame{
	public static int uno = 0;
	public static boolean isAdmin;
	public static Stack<String> prevName = new Stack<>();
	public static Stack<JPanel> prevPage = new Stack<>();
	public static Color blue = new Color(120, 150, 255).brighter();
	
	public BF() {
		setIconImage(getLogoImage(100, 100).getImage());
	}
	
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
		return new ImageIcon("./datafiles/"+path);
	}
	public static ImageIcon getIcon(String path, int w,int h) {
		return new ImageIcon(new ImageIcon("./datafiles/"+path).getImage().getScaledInstance(w, h, 1));
	}
	public static ImageIcon getIcon(byte[] path) {
		return new ImageIcon(path);
	}
	public static ImageIcon getIcon(byte[] path,int w,int h) {
		return new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(w, h, 1));
	}
	
	public void updateForm() {
		
	}
	public void showPage(JFrame jf, String name) {
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
	
	public static Connection con;
	public static Statement stmt;
	static {	
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/science?serverTimezone=Asia/Seoul", "root", "1234");
			stmt = con.createStatement();
			setUiset();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private static void setUiset() {
		UIManager.put("Panel.background", Color.white);
		UIManager.put("ComboBox.background", Color.white);
	}
	public static void setUiunset() {
		UIManager.put("Panel.background", null);
	}
	public static void execute(String sql) throws SQLException {
		stmt.execute(sql);
	}
	public static PreparedStatement	pre(String sql) throws SQLException {
		return con.prepareStatement(sql);
	}
	public static ResultSet res(String slq ) throws SQLException {
		return pre(slq).executeQuery();
	}
	public static void preSet(PreparedStatement pre,Object...objects) throws SQLException {
		int i = 1;
		for (Object object : objects) {
			pre.setObject(i++, object);
		}
	}
	
	public static ImageIcon getLogoImage(int w, int h) {
		Image img = getIcon("아이콘/아이콘.png",w,h).getImage();
		BufferedImage bi = new BufferedImage(w, h, 2);
		var g = bi.createGraphics();
		g.drawImage(img, 0, 0, w, h, 0, 0, w*4/10, h, null);
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
	public static ImageIcon getGrayIcon(Image img) {
		BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), 2);
		var g = bi.createGraphics();
		g.drawImage(img, 0, 0, null);
		for (int i = 0; i < img.getWidth(null); i++) {
			for (int j = 0; j < img.getHeight(null); j++) {
				Color c = new Color(bi.getRGB(i, j));
				int mid = (c.getRed()+c.getBlue() + c.getGreen())/3;
				Color newC = new Color(mid, mid, mid);
				bi.setRGB(i, j, newC.getRGB());
			}
		}
		return new ImageIcon(bi);
	}
	public static ImageIcon getQRcode(int w, int h) {
		int cnt = 40;
		int unit = 10;
		int size = 400;
		BufferedImage bi = new BufferedImage(size, size, 2);
		var g =bi.createGraphics();
		g.setColor(Color.black);
		var list =IntStream.range(0, 40).boxed().collect(Collectors.toList());
		for (int i = 0; i < cnt; i++) {
			Collections.shuffle(list);
			for (int j = 0; j < 25; j++) {
				g.fillRect(i*unit, list.get(j)*unit, unit, unit);
			}
		}
		BufferedImage img = new BufferedImage(w, h, 2);
		img.createGraphics().drawImage(bi, 0, 0, w, h, 0, 0, size, size, null);
		return new ImageIcon(img);
	}
}
class RoundButton extends JButton {
	public RoundButton(String txt) {
		super(txt);
		setBorderPainted(false);
		setFocusPainted(false);
		setContentAreaFilled(false);
	}
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(getBackground());
		g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
		super.paintComponent(g);
	}
}
class PlaceHolder extends JTextField {
	JLabel jl;
	public PlaceHolder(String txt) {
		jl = new JLabel(txt,0);
		jl.setEnabled(false);
		jl.setFont(new Font("맑은 고딕", 1, 12));
		setLayout(new BorderLayout());
		add(jl);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		jl.setVisible(getText().isEmpty());
	}
}
 