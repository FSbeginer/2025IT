import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JScrollPane;

public class G_∫Í∑£µÂ¡§∫∏ extends BF {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					G_∫Í∑£µÂ¡§∫∏ frame = new G_∫Í∑£µÂ¡§∫∏(1);
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
	public JPanel panel;
	public JLabel label_1;
	public JLabel label_2;
	public JScrollPane scrollPane;
	public JPanel panel_1;
	public JLabel label_3;
	public JPanel panel_2;
	public JLabel label_4;
	public JLabel label_5;
	private Timer timer;
	public G_∫Í∑£µÂ¡§∫∏(int bno) {
		setTitle("\uBE0C\uB79C\uB4DC \uC815\uBCF4");
		this.bno=bno;
		setBounds(100, 100, 547, 556);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("");
		label.setBorder(new LineBorder(new Color(255, 128, 0)));
		label.setBounds(12, 10, 148, 137);
		getContentPane().add(label);
		
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image img = getIcon("¡ˆµµ.png", 300, 275).getImage();
				g.drawImage(img, 0, 0, null);
				
				g.setColor(Color.red);
				g.fillOval(mypoint.x-4, mypoint.y-4, 8, 8);
			}
		};
		panel.setBounds(12, 160, 300, 275);
		getContentPane().add(panel);
		
		label_1 = new JLabel("New label");
		label_1.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 12));
		label_1.setBounds(172, 10, 348, 38);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("New label");
		label_2.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 22));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(172, 45, 348, 98);
		getContentPane().add(label_2);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(325, 162, 195, 273);
		getContentPane().add(scrollPane);
		
		panel_2 = new JPanel();
		panel_2.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 10));
		scrollPane.setViewportView(panel_2);
		panel_2.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(0, 437, 531, 80);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		label_4 = new JLabel("");
		label_4.setBounds(12, 10, 81, 60);
		panel_1.add(label_4);
		
		label_5 = new JLabel("New label");
		label_5.setForeground(new Color(255, 255, 255));
		label_5.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD | Font.ITALIC, 12));
		label_5.setBounds(105, 10, 414, 60);
		panel_1.add(label_5);
		
		label_3 = new JLabel("New label");
		label_3.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 14));
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setForeground(new Color(255, 128, 0));
		label_3.setBounds(233, 132, 286, 20);
		getContentPane().add(label_3);

		Random rand = new Random();
		int ano =rand.nextInt(200)+1;
		try (var rs = res("select * from advertise where ano ="+ano)) {
			rs.next();
			label_4.setIcon(getIcon("advertise/"+ano+"-1.jpg",label_4.getWidth(),label_4.getHeight()));
			label_5.setText(rs.getString("aname"));
			panel_1.setName(ano+"");
		} catch (SQLException e1) {
				e1.printStackTrace();
		}
		
		load();
		AreaB();
	}
	private void AreaB() {
		timer = new Timer(2000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Random rand = new Random();
				int ano =rand.nextInt(200)+1;
				try (var rs = res("select * from advertise where ano ="+ano)) {
					rs.next();
					label_4.setIcon(getIcon("advertise/"+ano+"-1.jpg",label_4.getWidth(),label_4.getHeight()));
					label_5.setText(rs.getString("aname"));
					panel_1.setName(ano+"");
				} catch (SQLException e1) {
						e1.printStackTrace();
				}
			}
		});
		timer.start();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				timer.stop();
				int ano = Integer.parseInt(panel_1.getName());
				var E = new E_±§∞Ì¡§∫∏(ano);
				E.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						timer.start();
					}
				});
				E.setVisible(true);
			}
		});
	}
	
	Point mypoint;
	List<JLabel> jls = new ArrayList<JLabel>();
	private void load() {
		label.setIcon(getIcon("brand/"+bno+".png", label.getWidth(), label.getHeight()));
		try {
			var rs = res("select * from brand join category using(cno) where bno = "+bno);
			rs.next();
			label_1.setText("["+rs.getString("cname")+"]");
			label_2.setText(rs.getString("bname"));
			mypoint = new Point(rs.getInt("bxx")/2, rs.getInt("byy")/2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try (var rs = res("select * from job join brand using(bno) where bno = "+bno)) {
			int i = 0;
			int w = scrollPane.getWidth()-25;
			int h = scrollPane.getHeight()/5;
			while(rs.next()) {
				JLabel jl = new JLabel();
				jl.setOpaque(true);
				jl.setBackground(Color.white);
				jl.setBorder(new LineBorder(Color.black));
				jl.setText("<html>"+rs.getString("jname"));
				jl.setVerticalAlignment(SwingConstants.TOP);
				jls.add(jl);
				jl.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 11));
				jl.setSize(w,h);
				jl.setLocation(0, (h-1)*i);
				int jno = rs.getInt("jno");
				jl.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						for (JLabel imsi : jls) {
							imsi.setBackground(Color.white);
						}
						jl.setBackground(Color.yellow);
						if(e.getClickCount()==2) {
							showPage(new C_æÀπŸ¡§∫∏(jno), "C_æÀπŸ¡§∫∏");
						}
					}
				});
				panel_2.add(jl);
				i++;
			}
			panel_2.setPreferredSize(new Dimension(0, (h-1)*i));
			label_3.setText("√— "+i+"∞«¿« æÀπŸ∞° ¿÷æÓø‰!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
