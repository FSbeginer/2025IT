import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
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

import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JPanel;
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
	public JLabel label_1;
	public JLabel label_2;
	public JPanel panel;
	public JScrollPane scrollPane;
	public JPanel panel_1;
	public JLabel label_3;
	public JPanel panel_2;
	public JLabel label_4;
	public JLabel label_5;
	private Timer timer;
	public G_∫Í∑£µÂ¡§∫∏(int bno) {
		setTitle("\uBE0C\uB79C\uB4DC \uC815\uBCF4");
		this.bno = bno;
		setBounds(100, 100, 559, 561);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("");
		label.setBorder(new LineBorder(new Color(255, 128, 0)));
		label.setBounds(12, 10, 151, 132);
		getContentPane().add(label);
		
		label_1 = new JLabel("New label");
		label_1.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 13));
		label_1.setBounds(175, 10, 118, 27);
		getContentPane().add(label_1);
		
		label_2 = new JLabel("New label");
		label_2.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(175, 10, 353, 132);
		getContentPane().add(label_2);
		
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image img= getIcon("¡ˆµµ.png",300,275).getImage();
				g.drawImage(img, 0, 0, null);
				g.setColor(Color.red);
				g.fillOval(me.x-4, me.y-4, 8, 8);
			}
		};
		panel.setBounds(12, 152, 300, 275);
		getContentPane().add(panel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 128, 0));
		scrollPane.setBounds(324, 152, 207, 275);
		getContentPane().add(scrollPane);
		
		panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(null);
		
		label_6 = new JLabel("New label");
		label_6.setForeground(new Color(255, 255, 255));
		label_6.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 13));
		label_6.setOpaque(true);
		label_6.setBackground(new Color(255, 128, 0));
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(label_6);
		
		label_3 = new JLabel("New label");
		label_3.setForeground(new Color(255, 128, 0));
		label_3.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 13));
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(324, 118, 207, 27);
		getContentPane().add(label_3);
		
		panel_2 = new JPanel();
		panel_2.addMouseListener(new Panel_2MouseListener());
		panel_2.setBackground(new Color(0, 0, 0));
		panel_2.setBounds(0, 436, 543, 86);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		label_4 = new JLabel("");
		label_4.setBounds(12, 10, 97, 66);
		panel_2.add(label_4);
		
		label_5 = new JLabel("New label");
		label_5.setForeground(new Color(255, 255, 255));
		label_5.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD | Font.ITALIC, 13));
		label_5.setBounds(131, 10, 400, 66);
		panel_2.add(label_5);

		load();
		setAd();
	}
	Random rand = new Random();
	private void setAd() {
		timer = new Timer(2000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int ano = rand.nextInt(200) + 1;
				String path = "advertise/" + ano + "-1.jpg";
				label_4.setIcon(getIcon(path, label_4.getWidth(), label_4.getHeight()));
				label_4.setName(ano + "");
				try {
					var rs =res("select * from advertise where ano = "+ano);
					rs.next();
					label_5.setText(rs.getString("aname"));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		int ano = rand.nextInt(200) + 1;
		String path = "advertise/" + ano + "-1.jpg";
		label_4.setIcon(getIcon(path, label_4.getWidth(), label_4.getHeight()));
		label_4.setName(ano + "");
		try {
			var rs =res("select * from advertise where ano = "+ano);
			rs.next();
			label_5.setText(rs.getString("aname"));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		timer.start();
	}

	Point me;
	List<JLabel> jls = new ArrayList<JLabel>();
	public JLabel label_6;
	private void load() {
		try (var rs = res("select * from brand join category using(cno) where bno = "+bno )) {
			if(rs.next()) {
				System.out.println();
				label.setIcon(getIcon("brand/"+bno+".png",label.getWidth(),label.getHeight()));
				label_1.setText("["+rs.getString("cname")+"]");
				label_2.setText(rs.getString("bname"));
				label_6.setText(rs.getNString("bname")+" æÀπŸ ∏Ò∑œ");
				me = new Point(rs.getInt("bxx")/2, rs.getInt("byy")/2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = res(" select * from job where bno = "+bno)) {
			int i = 0;
			int w = scrollPane.getWidth()-20;
			int h = (scrollPane.getHeight()-23)/5;
			while(rs.next()) {
				JLabel jl = new JLabel("<html>"+rs.getString("jname"));
				jl.setSize(w, h);
				jl.setBorder(new LineBorder(Color.black));
				jl.setLocation(0, (h-1)*i);
				jl.setOpaque(true);
				jl.setFont(new Font("∏º¿∫ ∞ÌµÒ",0,11));
				jl.setBackground(Color.white);
				int jno = rs.getInt("jno");
				jl.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						for (JLabel jl : jls) {
							jl.setBackground(Color.white);
						}
						jl.setBackground(Color.yellow);
						if(e.getClickCount()==2) {
							showPage(new C_æÀπŸ¡§∫∏(jno),"C_æÀπŸ¡§∫∏");
						}
					}
				});
				jls.add(jl);
				panel_1.add(jl);
				i++;
			}
			panel_1.setPreferredSize(new Dimension(0, (h)*i));
			label_3.setText("√— "+i+"∞«¿« æÀπŸ∞° ¿÷æÓø‰!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private class Panel_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			int ano = Integer.parseInt(label_4.getName());
			var d = new E_±§∞Ì¡§∫∏(ano);
			timer.stop();
			d.setVisible(true);
			timer.start();
		}
	}
}
