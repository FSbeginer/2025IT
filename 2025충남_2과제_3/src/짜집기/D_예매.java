package 짜집기;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
public class D_예매 extends BP {
	public JPanel panel;
	public JPanel panel_1;
	public JPanel panel_2;
	public JLabel label;
	public JPanel panel_3;

	boolean flag;
	public D_예매 (int sno, int lno, int pno) {
		this();
		this.sno = sno;
		this.lno = lno;
		this.pno = pno;
		card.last(panel);
		load3();
	}
	
	/**
	 * Create the panel.
	 */
	public D_예매() {
		setLayout(new CardLayout(0, 0));
		
		panel = new JPanel();
		add(panel, "name_11031877636900");
		panel.setLayout(null);
		
		label = new JLabel("\uC608\uB9E4\uD560 \uACFC\uD559\uAD00\uC744 \uACE8\uB77C\uC8FC\uC138\uC694.");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(12, 10, 938, 48);
		panel.add(label);
		
		panel_3 = new JPanel();
		panel_3.setBounds(22, 68, 913, 387);
		panel.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 3, 0, 0));
		
		panel_1 = new JPanel();
		add(panel_1, "name_11033168805700");
		panel_1.setLayout(null);
		
		label_1 = new JLabel(getIcon("아이콘/이전.png",100,40));
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setBounds(12, 10, 118, 45);
		panel_1.add(label_1);
		
		label_3 = new JLabel("New label");
		label_3.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(12, 10, 938, 59);
		panel_1.add(label_3);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(63, 79, 836, 385);
		panel_1.add(scrollPane);
		
		panel_4 = new JPanel();
		scrollPane.setViewportView(panel_4);
		panel_4.setLayout(null);
		
		panel_2 = new JPanel();
		add(panel_2, "name_11034214796800");
		panel_2.setLayout(null);
		
		label_2 = new JLabel(getIcon("아이콘/이전.png",100,40));
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setBounds(12, 10, 118, 45);
		panel_2.add(label_2);
		
		label_4 = new JLabel("\uACFC\uD559\uAD00");
		label_4.setBounds(12, 63, 57, 15);
		panel_2.add(label_4);
		
		lblImg = new JLabel("");
		lblImg.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblImg.setBounds(12, 88, 316, 154);
		panel_2.add(lblImg);
		
		lblImg2 = new JLabel("");
		lblImg2.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblImg2.setBounds(390, 88, 309, 154);
		panel_2.add(lblImg2);
		
		label_7 = new JLabel("\uD504\uB85C\uADF8\uB7A8");
		label_7.setBounds(390, 63, 57, 15);
		panel_2.add(label_7);
		
		lblSn = new JLabel("New label");
		lblSn.setBounds(12, 252, 253, 15);
		panel_2.add(lblSn);
		
		lblAddress = new JLabel("New label");
		lblAddress.setBounds(12, 277, 253, 15);
		panel_2.add(lblAddress);
		
		lblPn = new JLabel("New label");
		lblPn.setBounds(390, 252, 253, 15);
		panel_2.add(lblPn);
		
		label_5 = new JLabel("\uB0A0\uC9DC");
		label_5.setBounds(12, 302, 57, 15);
		panel_2.add(label_5);
		
		label_6 = new JLabel("\uC778\uC6D0");
		label_6.setBounds(12, 327, 57, 15);
		panel_2.add(label_6);
		
		label_8 = new JLabel("\uC131\uC778");
		label_8.setBounds(28, 371, 57, 15);
		panel_2.add(label_8);
		
		label_9 = new JLabel("\uCCAD\uC18C\uB144");
		label_9.setBounds(28, 406, 57, 15);
		panel_2.add(label_9);
		
		label_10 = new JLabel("\uC5B4\uB9B0\uC774");
		label_10.setBounds(28, 443, 57, 15);
		panel_2.add(label_10);
		
		label_11 = new JLabel("4000\uC6D0");
		label_11.setBounds(94, 371, 57, 15);
		panel_2.add(label_11);
		
		label_12 = new JLabel("3000\uC6D0");
		label_12.setBounds(94, 406, 57, 15);
		panel_2.add(label_12);
		
		label_13 = new JLabel("2000\uC6D0");
		label_13.setBounds(94, 443, 57, 15);
		panel_2.add(label_13);
		
		comboBox = new JComboBox();
		comboBox.setBounds(94, 298, 99, 19);
		panel_2.add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(229, 298, 99, 19);
		panel_2.add(comboBox_1);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setBounds(360, 298, 99, 19);
		panel_2.add(comboBox_2);
		
		label_14 = new JLabel("\uB144");
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setBounds(184, 302, 57, 15);
		panel_2.add(label_14);
		
		label_15 = new JLabel(" \uC6D4");
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		label_15.setBounds(311, 302, 57, 15);
		panel_2.add(label_15);
		
		label_16 = new JLabel("\uC77C");
		label_16.setHorizontalAlignment(SwingConstants.CENTER);
		label_16.setBounds(446, 302, 57, 15);
		panel_2.add(label_16);
		
		label_17 = new JLabel("\uACB0\uC81C");
		label_17.addMouseListener(new Label_17MouseListener());
		label_17.setOpaque(true);
		label_17.setBackground(BF.blue);
		label_17.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		label_17.setForeground(new Color(255, 255, 255));
		label_17.setHorizontalAlignment(SwingConstants.CENTER);
		label_17.setBounds(761, 0, 201, 496);
		panel_2.add(label_17);
		
		label_18 = new JLabel("+");
		label_18.addMouseListener(new Label_18MouseListener());
		label_18.setHorizontalAlignment(SwingConstants.CENTER);
		label_18.setFont(new Font("돋움체", Font.BOLD, 18));
		label_18.setBounds(184, 371, 38, 15);
		panel_2.add(label_18);
		
		label_19 = new JLabel("+");
		label_19.addMouseListener(new Label_19MouseListener());
		label_19.setHorizontalAlignment(SwingConstants.CENTER);
		label_19.setFont(new Font("돋움체", Font.BOLD, 18));
		label_19.setBounds(184, 406, 38, 15);
		panel_2.add(label_19);
		
		label_20 = new JLabel("+");
		label_20.addMouseListener(new Label_20MouseListener());
		label_20.setHorizontalAlignment(SwingConstants.CENTER);
		label_20.setFont(new Font("돋움체", Font.BOLD, 18));
		label_20.setBounds(184, 442, 38, 15);
		panel_2.add(label_20);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("0");
		textField.setEditable(false);
		textField.setBounds(229, 368, 57, 21);
		panel_2.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setText("0");
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(229, 403, 57, 21);
		panel_2.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setText("0");
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(229, 440, 57, 21);
		panel_2.add(textField_2);
		
		label_21 = new JLabel("-");
		label_21.addMouseListener(new Label_21MouseListener());
		label_21.setHorizontalAlignment(SwingConstants.CENTER);
		label_21.setFont(new Font("돋움체", Font.BOLD, 18));
		label_21.setBounds(298, 443, 38, 15);
		panel_2.add(label_21);
		
		label_22 = new JLabel("-");
		label_22.addMouseListener(new Label_22MouseListener());
		label_22.setHorizontalAlignment(SwingConstants.CENTER);
		label_22.setFont(new Font("돋움체", Font.BOLD, 18));
		label_22.setBounds(298, 407, 38, 15);
		panel_2.add(label_22);
		
		label_23 = new JLabel("-");
		label_23.addMouseListener(new Label_23MouseListener());
		label_23.setHorizontalAlignment(SwingConstants.CENTER);
		label_23.setFont(new Font("돋움체", Font.BOLD, 18));
		label_23.setBounds(298, 372, 38, 15);
		panel_2.add(label_23);
		
		textField_3 = new JTextField();
		textField_3.addKeyListener(new TextField_3KeyListener());
		textField_3.setBounds(543, 365, 116, 21);
		panel_2.add(textField_3);
		textField_3.setColumns(10);
		
		label_24 = new JLabel("\uC0AC\uC6A9\uD3EC\uC778\uD2B8");
		label_24.setBounds(456, 368, 67, 19);
		panel_2.add(label_24);
		
		label_25 = new JLabel("\uACB0\uC81C\uAE08\uC561: 0");
		label_25.setBounds(456, 406, 187, 15);
		panel_2.add(label_25);
		
		load();
		card = (CardLayout) getLayout();
	}
	CardLayout card;
	int sno, lno, pno;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JScrollPane scrollPane;
	public JPanel panel_4;
	public JLabel label_4;
	public JLabel lblImg;
	public JLabel lblImg2;
	public JLabel label_7;
	public JLabel lblSn;
	public JLabel lblAddress;
	public JLabel lblPn;
	public JLabel label_5;
	public JLabel label_6;
	public JLabel label_8;
	public JLabel label_9;
	public JLabel label_10;
	public JLabel label_11;
	public JLabel label_12;
	public JLabel label_13;
	public JComboBox comboBox;
	public JComboBox comboBox_1;
	public JComboBox comboBox_2;
	public JLabel label_14;
	public JLabel label_15;
	public JLabel label_16;
	public JLabel label_17;
	public JLabel label_18;
	public JLabel label_19;
	public JLabel label_20;
	public JTextField textField;
	public JTextField textField_1;
	public JTextField textField_2;
	public JLabel label_21;
	public JLabel label_22;
	public JLabel label_23;

	private void load() {
		try (var rs = res("select * from science")) {
			int  i = 0;
			int w = panel_3.getWidth()/3;
			int h = panel_3.getHeight()/2;
			while(rs.next()) {
				JLabel jl = new JLabel("",0);
				ImageIcon gray = BF.getGrayIcon(getIcon(rs.getBytes("s_img"),w,h).getImage());
				ImageIcon img = getIcon(rs.getBytes("s_img"),w,h);
				jl.setForeground(Color.white);
				jl.setFont(new Font("맑은 고딕", 1, 15));
				jl.setIcon(gray);
				jl.setHorizontalTextPosition(SwingUtilities.CENTER);
				String name = rs.getString(2);
				int sno = rs.getInt(1);
				jl.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						jl.setBorder(new LineBorder(Color.red,2));
						jl.setText(name);
						jl.setIcon(img);
					}
					@Override
					public void mouseExited(MouseEvent e) {
						jl.setBorder(null);
						jl.setText("");
						jl.setIcon(gray);
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						D_예매.this.sno = sno;
						card.next(D_예매.this);
						load2();
					}
				});
				panel_3.add(jl);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void load2() {
		panel_4.removeAll();
		try (var rs = res("select * from (select pno, lno, sno, p.name pn, p.explanation ,s.name sn,p_img ,row_number() over(partition by pno order by curdate() between start_date and end_date desc) r, curdate() between start_date and end_date flag from location l join program p using(pno) join science s using(sno) where sno = "+sno+") sub where flag = 1 group by pno;")) {
			int w= 569, h= 169, i = 0;
			while(rs.next()) {
				D_패널 pp = new D_패널(getIcon(rs.getBytes("p_img"),200, 169),rs.getString(4),rs.getString(5));
				pp.setSize(w,h);
				DarkLabel dl = new DarkLabel();
				dl.setHorizontalAlignment(0);
				dl.setIcon(getIcon("아이콘/체크.png",50,50));
				DarkPanel dp = new DarkPanel(pp, dl);
				dp.setLocation(i%2==0?220:20, 20+(h+10)*i);
				int lno = rs.getInt(2);
				int pno = rs.getInt(1);
				dp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						D_예매.this.lno = lno;
						D_예매.this.pno = pno;
						load3();
						card.last(D_예매.this);
					}
				});
				panel_4.add(dp);
				label_3.setText(rs.getString("sn"));
				i++;
			}
			panel_4.setPreferredSize(new Dimension(0, 20+(h+10)*i));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel_4.revalidate();
		panel_4.repaint();
	}
	private void load3() {
		try (var rs = res("select s.name sn, s.address ,p.name pn, start_date, end_date, s.s_img, p.p_img from location l join program p using(pno) join science s using(sno) where sno = "+sno +" and lno = "+lno +" and pno = "+pno)) {
			rs.next();
			System.out.println("select s.name sn, s.address ,p.name pn, start_date, end_date, s.s_img, p.p_img from location l join program p using(pno) join science s using(sno) where sno = "+sno +" and lno = "+lno +" and pno = "+pno);
			lblImg.setIcon(getIcon(rs.getBytes("s_img"),lblImg.getWidth(),lblImg.getHeight()));
			lblImg2.setIcon(getIcon(rs.getBytes("p_img"),lblImg2.getWidth(),lblImg2.getHeight()));
			lblPn.setText(rs.getString(3));
			lblSn.setText(rs.getString(1));
			lblAddress.setText(rs.getString(2));
			var d1 = LocalDate.now();
			var d2 =rs.getDate("end_date").toLocalDate();
			for (int i = d1.getYear(); i <= d2.getYear(); i++) {
				comboBox.addItem(i);
			}
			comboBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					comboBox_1.removeAllItems();
					var imsi = d1;
					while(!imsi.isAfter(d2)) {
						comboBox_1.addItem(imsi.getMonthValue());
						imsi = imsi.plusMonths(1);
					}
					comboBox_1.setSelectedIndex(0);
				}
			});
			comboBox_1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					comboBox_2.removeAllItems();
					var imsi = LocalDate.of((int)comboBox.getSelectedItem(), (int)comboBox_1.getSelectedItem(), 1);
					if(imsi.isBefore(d1)) imsi = LocalDate.of((int)comboBox.getSelectedItem(), (int)comboBox_1.getSelectedItem(), d1.getDayOfMonth());
					while(!imsi.isAfter(d2)) {
						comboBox_2.addItem(imsi.getDayOfMonth());
						imsi = imsi.plusDays(1);
					}
					System.out.println(1);
				}
			});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		comboBox.setSelectedItem(LocalDate.now().getYear());
		comboBox_1.setSelectedItem(LocalDate.now().getMonthValue());
		comboBox_2.setSelectedItem(LocalDate.now().getDayOfMonth());
	}
	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(flag) {
				((MainFrame)SwingUtilities.getWindowAncestor(label)).showPage(BF.prevPage.pop(), BF.prevName.pop());
			}
			else {
				card.previous(D_예매.this);
			}
		}
	}
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			card.previous(D_예매.this);
		}
	}
	
	int[] cnt = new int[3];
	public JTextField textField_3;
	public JLabel label_24;
	public JLabel label_25;
	private class Label_19MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			cnt[1]++;
			textField_1.setText(cnt[1]+"");
		}
	}
	private class Label_20MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			cnt[2]++;
			textField_2.setText(cnt[2]+"");
		}
	}
	private class Label_18MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			cnt[0]++;
			textField.setText(cnt[0]+"");
		}
	}
	private class Label_21MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			cnt[0] = Math.max(0, cnt[0]-1);
			textField.setText(cnt[0]+"");
		}
	}
	private class Label_23MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			cnt[2] = Math.max(0, cnt[2]-1);
			textField.setText(cnt[2]+"");
		}
	}
	private class Label_22MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			cnt[1] = Math.max(0, cnt[1]-1);
			textField.setText(cnt[1]+"");
		}
	}
	int use = 0;
	private class TextField_3KeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==e.VK_ENTER) {
				try {
					use = Integer.parseInt(textField_3.getText());
					try {
						var rs = res("select * from user where uno = "+BF.uno);
						rs.next();
						if(use> rs.getInt("point")) {
							msgErr("현재 보유 중인 포인트는 "+rs.getInt("point")+"입니다.");
							return;
						}
						else if(use >= getprice()) {
							use = getprice();
						}
						label_25.setText("결제금액: "+(getprice()-use));
					} catch (SQLException e1) {
						msgErr("숫자를 입력해주세요.");
					}
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				}
			}
		}

	}
	private int getprice() {
		int[] p = {4000, 3000, 2000};
		int sum =0;
		for (int i = 0; i < p.length; i++) {
			sum += cnt[i]*p[i];
		}
		return sum;
	}
	private class Label_17MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(getprice()==0) {
				msgErr("티켓 구매 인원을 선택해주세요.");
				return;
			}
			LocalDate selDate = LocalDate.of((int)comboBox.getSelectedItem(), (int)comboBox_1.getSelectedItem(), (int)comboBox_2.getSelectedItem());
			try {
				var rs = res("select * from ticket where uno = "+BF.uno+" and date = '"+selDate+"'");
				System.out.println("select * from ticket where uno = "+BF.uno+" and date = '"+selDate+"'");
				if(rs.next()) {
					msgErr("해당 날짜에는 다른 프로그램 예약이 존재합니다.");
					return;
				}
				int plus = (int) ((getprice()-use)*0.1);
				execute("update user set point = point-"+use+" where uno = "+BF.uno);
				execute("update user set point = point+"+plus+" where uno = "+BF.uno);
				var pre = pre("insert into ticket values(0,?,?,?,?,?,?,?,?,?)");
				var code = getcode();
				preSet(pre, BF.uno, code, lno, cnt[0], cnt[1],cnt[2], getprice(), use, selDate);
				pre.execute();
				var q = new QR코드(code);
				q.button.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						var d = new H_예약내역();
						d.localReser(selDate);
						((MainFrame)SwingUtilities.getWindowAncestor(label)).showPage(d, "예약내역");
						q.dispose();
					}
				});
				q.setVisible(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}

	}
	public static String getcode() {
		String result = "";
		Random rand = new Random();
		for (int i = 0; i < 6; i++) {
			if(rand.nextBoolean()) {
				var c = ((char)('A'+rand.nextInt(26)))+"";
				result+=c;
			}
			else {
				result += rand.nextInt(10)+"";
			}
		}
		
		return result;
	}
}
