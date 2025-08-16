import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
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
import java.util.Collections;
import java.util.List;
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
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BF extends JFrame {
	public static boolean remember = false;
	public static boolean isAdmin = false;
	public static String id;
	public static int uno;
	public static Color blue = new Color(120, 150, 200);
	
	public BF() {
		setIconImage(getLogoIcon(100,100).getImage());
	}
	public static ImageIcon getLogoIcon(int w, int h) {
		BufferedImage bi = new BufferedImage(w, h, 2);
		Graphics2D g2 = bi.createGraphics();
		Image img = getIcon("아이콘/아이콘.png").getImage();
		g2.drawImage(img, 0, 0, w, h, 0,0, img.getWidth(null)*4/10, img.getHeight(null), null);
		for (int i = 0; i < w; i++) {
		    for (int j = 0; j < h; j++) {
		    	Color c = new Color(bi.getRGB(i, j));
		        if (c.getRed()>180&&c.getBlue()>180&&c.getGreen()>180) {
		            bi.setRGB(i, j, 0); 
		        }
		    }
		}
		return new ImageIcon(bi);
	}

	public static void msgInfo(String msg) {
		JOptionPane.showMessageDialog(null, msg, "정보", 1);
	}

	public static void msgErr(String msg) {
		JOptionPane.showMessageDialog(null, msg, "경고", 0);
	}

	public static ImageIcon getIcon(String path) {
		return new ImageIcon("./datafiles/" + path);
	}

	public static ImageIcon getIcon(byte[] path) {
		return new ImageIcon(path);
	}

	public static ImageIcon getIcon(String path, int w, int h) {
		return new ImageIcon(new ImageIcon("./datafiles/" + path).getImage().getScaledInstance(w, h, 1));
	}

	public static ImageIcon getIcon(byte[] path, int w, int h) {
		return new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(w, h, 1));
	}

	public void showPage(JFrame jf, String name) {
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
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
	}

	public void showPage(String name) {
		var stack = new Stack<Window>();
		stack.addAll(Arrays.asList(Window.getWindows()));
		while (!stack.isEmpty()) {
			var window = stack.pop();
			if (window.getName().equals(name))
				break;
			else {
				window.setName("닫아");
				window.dispose();
			}
		}
	}

	public void updateForm() {

	}

	public static Statement stmt;
	public static Connection con;
	static {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/science?serverTimezone=Asia/Seoul", "root",
					"1234");
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static PreparedStatement pre(String sql) throws SQLException {
		return con.prepareStatement(sql);
	}

	public static ResultSet res(String sql) throws SQLException {
		return pre(sql).executeQuery();
	}

	public static void execute(String sql) throws SQLException {
		stmt.execute(sql);
	}

	public static void preSet(PreparedStatement pre, Object... objects) throws SQLException {
		int i = 1;
		for (Object object : objects) {
			pre.setObject(i++, object);
		}
	}
	public static BufferedImage getQR() {
		BufferedImage bi = new BufferedImage(400, 400, 2);
		var g = bi.createGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, 400, 400);
		g.setColor(Color.black);
		var list = IntStream.range(0, 40).boxed().collect(Collectors.toList());
		for (int i = 0; i < 40; i++) {
			Collections.shuffle(list);
			for (int j = 0; j < 25; j++) {
				g.fillRect(i*10, list.get(j)*10, 10, 10);
			}
		}
		return bi;
	}
	public static JToggleButton createToggle(String txt) {
		JToggleButton jt = new JToggleButton(txt);
		jt.setContentAreaFilled(false);
		jt.setOpaque(true);
		jt.setBackground(Color.white);
		jt.setForeground(blue);
		jt.setBorder(new LineBorder(blue));
		jt.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(jt.isSelected()) {
					jt.setBackground(blue);
					jt.setForeground(Color.white);
				}
				else {
					jt.setBackground(Color.white);
					jt.setForeground(blue);
				}
			}
		});
		return jt;
	}
}
class RoundButton extends JButton {
	public RoundButton(String txt) {
		super(txt);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setBorderPainted(false);
		setForeground(Color.white);
	}
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(BF.blue);
		g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 30, 30);
		super.paintComponent(g);
	}
}
class PlaceHolder extends JTextField {
	JLabel jl;
	public PlaceHolder(String name) {
		setBorder(new LineBorder(Color.black));
		setLayout(new BorderLayout());
		jl = new JLabel(name,0);
		jl.setForeground(Color.GRAY);
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
class DarkLabel extends JLabel{
	public DarkLabel() {
		setForeground(Color.white);
		setFont(new Font("맑은 고딕",Font.BOLD, 20));
		setHorizontalAlignment(0);
	}
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0, 0, 0, 50));
		g2.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}
}