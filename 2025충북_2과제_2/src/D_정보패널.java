import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.GridLayout;

public class D_정보패널 extends JPanel {
	public JLabel label;
	public JScrollPane scrollPane;
	public JPanel panel;
	public JScrollPane scrollPane_1;
	public JPanel panel_1;
	public JTextArea textArea;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JButton button;

	/**
	 * Create the panel.
	 */
	int mno;
	public D_정보패널(int mno) {
		this.mno=mno;
		setSize(794, 1000);
		setLayout(null);
		
		label = new JLabel("");
		label.setBounds(12, 10, 206, 289);
		add(label);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 325, 770, 350);
		add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("맑은 고딕",1, 13));
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				
			}
		};
		panel.setBounds(12, 721, 367, 269);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_2 = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				int ang = 90;
				int sum = Arrays.stream(cnt).sum();
				int w = getWidth()-50;
				int h = getWidth()-50;
				var txt = "성인,청소년,어린이,유아".split(",");
				JLabel[] jls = {label_5,label_6,label_7, label_8};
				for (int i = 0; i < 4; i++) {
					int deg = (int) Math.round((double)cnt[i]/sum * 360);
					g.setColor(c[i]);
					g.fillArc(25, 25, w, h, ang, deg);
					jls[i].setIcon(getIcon(c[i]));
					jls[i].setText(txt[i]+"   "+(int)((double)cnt[i]/sum*100)+"%");
					ang += deg;
				}
			}
		};
		panel.add(panel_2, BorderLayout.CENTER);
		
		panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(150, 10));
		panel.add(panel_3, BorderLayout.EAST);
		panel_3.setLayout(new GridLayout(8, 1, 0, 0));
		
		label_5 = new JLabel("New label");
		label_5.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(label_5);
		
		label_6 = new JLabel("New label");
		label_6.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(label_6);
		
		label_7 = new JLabel("New label");
		label_7.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		label_7.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(label_7);
		
		label_8 = new JLabel("New label");
		label_8.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		label_8.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(label_8);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(401, 721, 381, 269);
		add(scrollPane_1);
		
		panel_1 = new JPanel();
		scrollPane_1.setViewportView(panel_1);
		panel_1.setLayout(null);
		
		label_1 = new JLabel("ㅈ");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		label_1.setBounds(230, 10, 552, 61);
		add(label_1);
		
		label_2 = new JLabel("New label");
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		label_2.setBounds(230, 81, 552, 25);
		add(label_2);
		
		label_3 = new JLabel("New label");
		label_3.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		label_3.setBounds(230, 138, 552, 25);
		add(label_3);
		
		label_4 = new JLabel("New label");
		label_4.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		label_4.setBounds(230, 192, 552, 25);
		add(label_4);
		
		button = new JButton("예매하기");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(230, 259, 97, 23);
		add(button);
		
		load();
	}
	public static ImageIcon getIcon(Color c) {
		BufferedImage bi  =new BufferedImage(30, 30, 2);
		var g = bi.createGraphics();
		g.setColor(c);
		g.fillRect(0, 0, 30, 30);
		return new ImageIcon(bi);
	}
	
	int lno = 0;
	public JPanel panel_2;
	Color[] c = {Color.red, Color.blue, Color.yellow, Color.green};
	public JPanel panel_3;
	public JLabel label_5;
	public JLabel label_6;
	public JLabel label_7;
	public JLabel label_8;
	int[] cnt = {0,0,0,0};
	private void load() {
		try {
			var rs =BF.res("select * from movie join genre using(g_no) where  m_no = "+mno);
			while(rs.next()) {
				label.setIcon(BF.getIcon("movies/"+mno+".jpg",label.getWidth(),label.getHeight()));
				label_1.setText("제목: "+rs.getString("m_name"));
				label_2.setText("감독: "+rs.getString("m_dir"));
				label_3.setText("장르: "+rs.getString("g_name"));
				label_4.setText("개봉일: "+rs.getString("m_startday"));
				JLabel jl = new JLabel(BF.getIcon("limits/"+rs.getInt("l_no")+".png",30,30));
				jl.setVerticalAlignment(SwingConstants.TOP);
				jl.setHorizontalAlignment(JLabel.LEFT);
				label.setLayout(new BorderLayout());
				label.add(jl);
				textArea.setText(rs.getString("m_plot"));
				lno = rs.getInt("l_no");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = BF.res("select * from reservation join user using(u_no) where m_no = "+mno)) {
			
			while(rs.next()) {
				int age = BF.getAge(rs.getDate("u_birth").toLocalDate());
				cnt[age<5?3:age<13?2:age<19?1:0]++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = BF.res("select * from review join user using(u_no) where m_no = "+mno)) {
			int w = scrollPane_1.getWidth()-20;
			int h = 50, i =0;
			while(rs.next()) {
				D_댓글 pp =new D_댓글(BF.getIcon("user/"+rs.getInt("u_no")+".jpg",50,50), rs.getString("u_name"), rs.getString("re_com"));
				pp.setSize(w, h);
				pp.setLocation(0, (h+10)*i);
				panel_1.add(pp);
				i++;
			}
			panel_1.setPreferredSize(new Dimension(0, (h+10)*i));
		} catch (SQLException e) {
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				scrollPane.getVerticalScrollBar().setValue(0);
			}
		});
	}
	
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(BF.uno==0) {
				BF.msgErr("로그인을 해주세요.");
				((BF)SwingUtilities.getWindowAncestor(D_정보패널.this)).showPage(new B_로그인(), "B_로그인");
				return;
			}
			if(BF.getAge(BF.ubirth)<19&&lno == 4) {
				BF.msgErr("미성년자는 시청 금지입니다.");
				return;
			}
			((BF)SwingUtilities.getWindowAncestor(D_정보패널.this)).showPage(new E_예매(mno), "E_예매");
		}
	}
}
