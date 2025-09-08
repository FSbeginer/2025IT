import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class D_패널 extends JPanel {

	/**
	 * Create the panel.
	 */
	int mno;
	public JLabel label;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JButton button;
	public JScrollPane scrollPane;
	public JTextArea textArea;
	public JPanel panel;
	public JLabel label_5;
	public JLabel label_6;
	public JLabel label_7;
	public JLabel label_8;
	public JLabel label_9;
	public JLabel label_10;
	public JLabel label_11;
	public JLabel label_12;
	public JLabel label_13;
	public JLabel label_14;
	public JLabel label_15;
	public JLabel label_16;
	public JScrollPane scrollPane_1;
	public D_패널(int mno) {
		this.mno = mno;
		setSize(863, 900);
		setPreferredSize(getSize());
		setLayout(null);
		
		label = new JLabel("");
		label.setBounds(12, 10, 227, 279);
		add(label);
		
		label_1 = new JLabel("New label");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 21));
		label_1.setBounds(251, 10, 586, 43);
		add(label_1);
		
		label_2 = new JLabel("New label");
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		label_2.setBounds(250, 67, 587, 26);
		add(label_2);
		
		label_3 = new JLabel("New label");
		label_3.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		label_3.setBounds(251, 115, 586, 26);
		add(label_3);
		
		label_4 = new JLabel("New label");
		label_4.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		label_4.setBounds(250, 167, 587, 26);
		add(label_4);
		
		button = new JButton("예매하기");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(251, 232, 97, 23);
		add(button);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 309, 825, 316);
		add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollPane.setViewportView(textArea);
		
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				var jls = new JLabel[]{label_13,label_14,label_15,label_16};
				int ang = 90;
				for (int i = 0; i < jls.length; i++) {
					int deg = (int) Math.round((double)cnt[i]/Arrays.stream(cnt).sum() * 360);
					int per = (int) Math.round((double)cnt[i]/Arrays.stream(cnt).sum() * 100);
					jls[i].setText(String.format("%d%%", per));
					g.setColor(c[i]);
					g.fillArc(getWidth()/2-100, getHeight()/2-100, 200, 200, ang, deg);
					ang +=deg;
				}
			}
		};
		panel.setBounds(12, 635, 266, 255);
		add(panel);
		
		label_5 = new JLabel("");
		label_5.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 15));
		label_5.setBounds(290, 639, 37, 32);
		add(label_5);
		
		label_6 = new JLabel("");
		label_6.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 15));
		label_6.setBounds(290, 681, 37, 32);
		add(label_6);
		
		label_7 = new JLabel("");
		label_7.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 15));
		label_7.setBounds(290, 720, 37, 32);
		add(label_7);
		
		label_8 = new JLabel("");
		label_8.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 15));
		label_8.setBounds(290, 762, 37, 32);
		add(label_8);
		
		label_9 = new JLabel("성인");
		label_9.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_9.setBounds(339, 639, 57, 32);
		add(label_9);
		
		label_10 = new JLabel("청소년");
		label_10.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_10.setBounds(339, 681, 57, 32);
		add(label_10);
		
		label_11 = new JLabel("어린이");
		label_11.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_11.setBounds(339, 720, 57, 32);
		add(label_11);
		
		label_12 = new JLabel("유아");
		label_12.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_12.setBounds(340, 762, 57, 32);
		add(label_12);
		
		label_13 = new JLabel("New label");
		label_13.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_13.setBounds(408, 635, 68, 37);
		add(label_13);
		
		label_14 = new JLabel("New label");
		label_14.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_14.setBounds(408, 679, 68, 37);
		add(label_14);
		
		label_15 = new JLabel("New label");
		label_15.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_15.setBounds(408, 720, 68, 37);
		add(label_15);
		
		label_16 = new JLabel("New label");
		label_16.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_16.setBounds(409, 757, 68, 37);
		add(label_16);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(488, 635, 349, 255);
		add(scrollPane_1);
		
		panel_1 = new JPanel();
		scrollPane_1.setViewportView(panel_1);
		panel_1.setLayout(null);
		
		var jls = new JLabel[] {label_5,label_6,label_7,label_8};
		for (int i = 0; i < jls.length; i++) {
			jls[i].setIcon(geticon(c[i]));
		}
		load();
		getData();
	}
	int limit;
	private void load() {
		try (var rs = BF.res("select * from movie join genre using(g_no) join movie_limit using(l_no) where m_no = "+mno)) {
			rs.next();
			label.setIcon(BF.getIcon("movies./"+mno+".jpg",label.getWidth(),label.getHeight()));
			label_1.setText(rs.getString("m_name"));
			label_2.setText("감독: "+rs.getString("m_dir"));
			label_3.setText("장르: "+rs.getString("g_name"));
			label_4.setText("개봉일: "+rs.getString("m_startday"));
			textArea.setText(rs.getString("m_plot"));
			int lno  = rs.getInt("l_no");
			limit = lno ==0 ? 0 : lno ==1 ? 12 : lno ==2 ? 15 : 19;
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					scrollPane.getVerticalScrollBar().setValue(0);
				}
			});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int w = scrollPane_1.getWidth()-20;
		int h = 50, i =0;
		try (var rs = BF.res("select * from review join movie using(m_no) join user using(u_no) where m_no = "+mno)) {
			while(rs.next()) {
				D_리뷰 pp = new D_리뷰(BF.getIcon("user/"+rs.getInt("u_no")+".jpg",50,50), rs.getString("u_name"),rs.getString("re_com"));
				pp.setLocation(0, (h+5)*i);
				panel_1.add(pp);
				i++;
			}
			panel_1.setPreferredSize(new Dimension(0, (h+5)*i));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void getData() {
		try (var rs = BF.res("select * from movie left join reservation using(m_no) join user using(u_no) where m_no = "+mno)) {
			while(rs.next()) {
				int age=	BF.getAge(rs.getDate("u_birth").toLocalDate());
				int idx = age<5? 3 : age <13 ? 2 : age < 19 ? 1 : 0;
				cnt[idx]++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private Icon geticon(Color color) {
		BufferedImage bi =  new BufferedImage(30, 30, 2);
		var g = bi.createGraphics();
		g.setColor(color);
		g.fillRect(0, 0, 30, 30);
		return new ImageIcon(bi);
	}
	Color[] c ={Color.red, Color.blue, Color.yellow, Color.green};
	int[] cnt = new int[4];
	public JPanel panel_1;

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(BF.uno==0) {
				BF.msgErr("로그인을 해주세요.");
				((BF)SwingUtilities.getWindowAncestor(scrollPane)).showPage(new B_로그인(), "B_로그인");
				return;
			}
			try (var rs = BF.res("select * from user where u_no = "+BF.uno)) {
				rs.next();
				int age = BF.getAge(rs.getDate("u_birth").toLocalDate());
				if(!(age>limit)&&limit == 19) {
					BF.msgErr("미성년자는 시청금지입니다.");
					return;
				}
				else if(!(age>limit)) {
					BF.msgErr("나이가 되지 않습니다.");
					return;
				}
				((BF)SwingUtilities.getWindowAncestor(scrollPane)).showPage(new E_예매(mno), "E_예매");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
