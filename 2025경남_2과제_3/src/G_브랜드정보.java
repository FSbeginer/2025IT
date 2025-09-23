import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class G_브랜드정보 extends BF {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					G_브랜드정보 frame = new G_브랜드정보(1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	int bno;
	public JPanel panel;
	public JPanel panel_1;
	public JLabel lbladd;
	public JLabel lbladd_1;
	public JScrollPane scrollPane;
	public JPanel panel_2;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	private Timer timer;
	public G_브랜드정보(int bno) {
		setTitle("\uBE0C\uB79C\uB4DC \uC815\uBCF4");
		this.bno = bno;
		setBounds(100, 100, 559, 562);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image img = getIcon("지도.png",300,275).getImage();
				g.drawImage(img, 0, 0, null);
				g.setColor(Color.red);
				g.fillOval(me.x-4, me.y-4, 8, 8);
			}
		};
		panel.setBounds(12, 152, 300, 275);
		getContentPane().add(panel);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(0, 440, 543, 83);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		lbladd = new JLabel("");
		lbladd.setBounds(12, 10, 76, 63);
		panel_1.add(lbladd);
		
		lbladd_1 = new JLabel("sssss");
		lbladd_1.setForeground(Color.WHITE);
		lbladd_1.setFont(new Font("맑은 고딕", Font.BOLD | Font.ITALIC, 13));
		lbladd_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbladd_1.setBounds(123, 10, 369, 63);
		panel_1.add(lbladd_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(324, 153, 207, 274);
		getContentPane().add(scrollPane);
		
		panel_2 = new JPanel();
		scrollPane.setViewportView(panel_2);
		panel_2.setLayout(null);
		
		label = new JLabel("New label");
		label.setForeground(new Color(255, 128, 0));
		label.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(288, 119, 243, 28);
		getContentPane().add(label);
		
		label_1 = new JLabel("");
		label_1.setBorder(new LineBorder(new Color(255, 128, 0)));
		label_1.setBounds(12, 10, 119, 106);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("New label");
		label_2.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_2.setBounds(143, 10, 113, 28);
		getContentPane().add(label_2);
		
		label_3 = new JLabel("New label");
		label_3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(163, 48, 344, 63);
		getContentPane().add(label_3);
		
		int ano = rand.nextInt(200)+1;
		String path = "advertise/"+ano+"-1.jpg";
		lbladd.setIcon(getIcon(path,lbladd.getWidth(),lbladd.getHeight()));
		try {
			var rs =res("select * from advertise where ano = "+ano);
			rs.next();
			lbladd_1.setText(rs.getString(2));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		lbladd.setName(ano+"");
		
		settimer();
		load();
	}
	private void settimer() {
		timer = new Timer(2000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int ano = rand.nextInt(200)+1;
				String path = "advertise/"+ano+"-1.jpg";
				try {
					var rs =res("select * from advertise where ano = "+ano);
					rs.next();
					lbladd_1.setText(rs.getString(2));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				lbladd.setIcon(getIcon(path,lbladd.getWidth(),lbladd.getHeight()));
				lbladd.setName(ano+"");
			}
		});
		timer.start();
		
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ano = Integer.parseInt(lbladd.getName());
				timer.stop();
				var d = new E_광고정보(ano);
				d.setVisible(true);
				timer.start();
			}
		});
	}
	
	Point me;
	List<JLabel> jls = new ArrayList<JLabel>();
	Random rand = new Random();
	private void load() {
		
		try {
			var rs =res("select * from brand join category using(cno) where bno = "+bno);
			rs.next();
			label_1.setIcon(getIcon("brand/"+bno+".png",label_1.getWidth(),label_1.getHeight()));
			label_2.setText("["+rs.getString("cname")+"]");
			label_3.setText(rs.getString("bname"));
			me = new Point(rs.getInt("bxx")/2, rs.getInt("byy")/2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try (var rs = res("select * from job join brand using(bno) join category using(cno) where bno = "+bno+" order by jno")) {
			int w = scrollPane.getWidth()-20;
			int h = scrollPane.getHeight()/5;
			int i = 0;
			while(rs.next()) {
				JLabel jl = new JLabel("<html>"+rs.getString("jname"));
				jl.setVerticalAlignment(SwingConstants.TOP);
				jl.setOpaque(true);
				int jno = rs.getInt("jno");
				jl.setSize(w, h);
				jl.setLocation(0,( h-1)*i);
				jl.setBorder(new LineBorder(Color.black));
				jl.setBackground(Color.white);
				jl.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(e.getClickCount()==2) {
							showPage(new C_알바정보(jno),"C_알바정보");
						}
						for (JLabel jl : jls) {
							jl.setBackground(Color.white);
						}
						jl.setBackground(Color.yellow);
					}
				});
				jls.add(jl);
				panel_2.add(jl);
				i++;
			}
			label.setText("총 "+i+"건의 알바가 있어요!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
