import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

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

import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JPanel panel;
	public JScrollPane scrollPane;
	public JPanel panel_2;
	public JLabel label_3;
	public JLabel label_4;
	private Timer timer;
	public G_브랜드정보(int bno) {
		setTitle("\uBE0C\uB79C\uB4DC \uC815\uBCF4");
		this.bno = bno;
		setBounds(100, 100, 577, 581);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("");
		label.setBorder(new LineBorder(new Color(255, 128, 0)));
		label.setBounds(12, 10, 120, 112);
		getContentPane().add(label);
		
		label_1 = new JLabel("New label");
		label_1.setBounds(142, 10, 109, 22);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("New label");
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(144, 30, 397, 92);
		getContentPane().add(label_2);
		
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image img = getIcon("지도.png",300,275).getImage();
				g.drawImage(img, 0, 0, null);
				g.setColor(Color.red);
				g.fillOval(p.x-4, p.y-4, 8, 8);
			}
		};
		panel.setBounds(12, 150, 300, 275);
		getContentPane().add(panel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(324, 150, 217, 275);
		getContentPane().add(scrollPane);
		
		label_5 = new JLabel("\u3147\u3147\u3147\u3147\u3147");
		label_5.setForeground(new Color(255, 255, 255));
		label_5.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_5.setBackground(new Color(255, 128, 0));
		label_5.setOpaque(true);
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(label_5);
		
		panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(null);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 0));
		panel_2.setBounds(0, 464, 561, 78);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		label_3 = new JLabel("");
		label_3.setBounds(12, 10, 63, 58);
		panel_2.add(label_3);
		
		label_4 = new JLabel("New label");
		label_4.setFont(new Font("맑은 고딕", Font.BOLD | Font.ITALIC, 12));
		label_4.setForeground(new Color(255, 255, 255));
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setBounds(111, 10, 419, 58);
		panel_2.add(label_4);
		
		label_6 = new JLabel("New label");
		label_6.setForeground(new Color(255, 128, 0));
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setBounds(288, 125, 261, 15);
		getContentPane().add(label_6);
		
		Random rand = new Random();
		int ano = rand.nextInt(200)+1;
		try {
			var rs = res("select * from advertise where ano = "+ano);
			rs.next();
			label_4.setText(rs.getString("aname"));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		label_3.setName(ano+"");
		label_3.setIcon(getIcon("advertise/"+ano+"-1.jpg",label_3.getWidth(),label_3.getHeight()));
		load();
		settimer();
	}
	private void settimer() {
		Random rand = new Random();
		timer = new Timer(2000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int ano = rand.nextInt(200)+1;
				try {
					var rs = res("select * from advertise where ano = "+ano);
					rs.next();
					label_4.setText(rs.getString("aname"));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				label_3.setName(ano+"");
				label_3.setIcon(getIcon("advertise/"+ano+"-1.jpg",label_3.getWidth(),label_3.getHeight()));
			}
		});
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ano = Integer.parseInt(label_3.getName());
				var d = new E_광고정보(ano);
				timer.stop();
				d.setVisible(true);
				timer.start();
			}
		});
		timer.start();
	}
	Point p ;
	List<JLabel> list = new ArrayList<JLabel>();
	public JLabel label_5;
	public JPanel panel_1;
	public JLabel label_6;
	private void load() {
		try (var rs = res("select * from brand join category using(cno) where bno = "+bno)) {
			rs.next();
			label.setIcon(getIcon("brand/"+bno+".png",label.getWidth(),label.getHeight()));
			label_1.setText(rs.getString("cname"));
			label_2.setText(rs.getString("bname"));
			label_5.setText(rs.getString("bname")+" 알바 목록");
			p = new Point(rs.getInt("bxx"),rs.getInt("byy"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = res("select * from job where bno = "+bno)) {
			int w = scrollPane.getWidth()-20;
			int h = (scrollPane.getHeight()-30)/5;
			int i = 0;
			while(rs.next()) {
				JLabel jl = new JLabel();
				jl.setSize(w, h);
				jl.setOpaque(true);
				jl.setBackground(Color.white);
				jl.setBorder(new LineBorder(Color.black));
				int jno = rs.getInt("jno");
				jl.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(e.getClickCount()==2) {
							showPage(new C_알바정보(jno),"C_알바정보");
						}
						for (var jl: list) {
							jl.setBackground(Color.white);
						}
						jl.setBackground(Color.yellow);
					}
				});
				list.add(jl);
				jl.setText("<html>"+rs.getString("jname"));
				jl.setLocation(0, (h-1)*i);
				panel_1.add(jl);
				i++;
			}
			label_6.setText("총 "+i+"건의 알바가 있어요!");
			panel_1.setPreferredSize(new Dimension(0, (h-1)*i));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
