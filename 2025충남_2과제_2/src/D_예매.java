import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class D_예매 extends BP {

	/**
	 * Create the panel.
	 */
	int sno,lno,pno;
	public JPanel panel;
	public JPanel panel_1;
	public JPanel panel_2;
	public JLabel label;
	public JPanel panel_3;
	public D_예매() {
		setLayout(new CardLayout(0, 0));
		card = (CardLayout) getLayout();
		panel = new JPanel();
		add(panel, "name_19342793765200");
		panel.setLayout(null);
		
		label = new JLabel("\uC608\uB9E4\uD560 \uACFC\uD559\uAD00\uC744 \uACE8\uB77C\uC8FC\uC138\uC694.");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(57, 10, 880, 43);
		panel.add(label);
		
		panel_3 = new JPanel();
		panel_3.setBounds(47, 69, 910, 438);
		panel.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 3, 0, 0));
		
		panel_1 = new JPanel();
		add(panel_1, "name_19345620926200");
		panel_1.setLayout(null);
		
		label_1 = new JLabel(getIcon("아이콘/이전.png",120,60));
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setBounds(0, 0, 119, 60);
		panel_1.add(label_1);
		
		label_2 = new JLabel("New label");
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(0, 0, 998, 60);
		panel_1.add(label_2);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 70, 902, 443);
		panel_1.add(scrollPane);
		
		panel_4 = new JPanel();
		scrollPane.setViewportView(panel_4);
		panel_4.setLayout(null);
		
		panel_2 = new JPanel();
		add(panel_2, "name_19347522867100");
		panel_2.setLayout(null);
		
		label_3 = new JLabel(getIcon("아이콘/이전.png",135,52));
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setBounds(0, 0, 135, 52);
		panel_2.add(label_3);
		
		label_4 = new JLabel("\uACFC\uD559\uAD00");
		label_4.setBounds(10, 60, 57, 15);
		panel_2.add(label_4);
		
		lblImg1 = new JLabel("");
		lblImg1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblImg1.setBounds(10, 83, 290, 163);
		panel_2.add(lblImg1);
		
		lblImg2 = new JLabel("");
		lblImg2.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblImg2.setBounds(327, 83, 290, 163);
		panel_2.add(lblImg2);
		
		label_7 = new JLabel("\uD504\uB85C\uADF8\uB7A8");
		label_7.setBounds(327, 60, 57, 15);
		panel_2.add(label_7);
		
		lblName = new JLabel("New label");
		lblName.setBounds(10, 255, 290, 15);
		panel_2.add(lblName);
		
		lblAddress = new JLabel("New label");
		lblAddress.setBounds(10, 280, 290, 21);
		panel_2.add(lblAddress);
		
		lblProgram = new JLabel("New label");
		lblProgram.setBounds(327, 256, 290, 15);
		panel_2.add(lblProgram);
		
		label_11 = new JLabel("\uB0A0\uC9DC");
		label_11.setBounds(10, 311, 57, 15);
		panel_2.add(label_11);
		
		comboBox = new JComboBox();
		comboBox.addItemListener(new ComboBoxItemListener());
		comboBox.setBounds(103, 307, 123, 29);
		panel_2.add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.addItemListener(new ComboBox_1ItemListener());
		comboBox_1.setBounds(261, 307, 123, 29);
		panel_2.add(comboBox_1);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setBounds(415, 307, 123, 29);
		panel_2.add(comboBox_2);
		
		label_12 = new JLabel("\uB144");
		label_12.setBounds(230, 311, 19, 15);
		panel_2.add(label_12);
		
		label_13 = new JLabel("\uC6D4");
		label_13.setBounds(396, 311, 19, 15);
		panel_2.add(label_13);
		
		label_14 = new JLabel("\uC77C");
		label_14.setBounds(550, 311, 19, 15);
		panel_2.add(label_14);
		
		label_15 = new JLabel("\uC778\uC6D0");
		label_15.setBounds(10, 359, 57, 15);
		panel_2.add(label_15);
		
		label_16 = new JLabel("\uC131\uC778");
		label_16.setBounds(33, 412, 57, 15);
		panel_2.add(label_16);
		
		label_17 = new JLabel("\uCCAD\uC18C\uB144");
		label_17.setBounds(33, 449, 57, 15);
		panel_2.add(label_17);
		
		label_18 = new JLabel("\uC5B4\uB9B0\uC774");
		label_18.setBounds(33, 491, 57, 15);
		panel_2.add(label_18);
		
		label_19 = new JLabel("4000\uC6D0");
		label_19.setBounds(123, 412, 57, 15);
		panel_2.add(label_19);
		
		label_20 = new JLabel("3000\uC6D0");
		label_20.setBounds(123, 449, 57, 15);
		panel_2.add(label_20);
		
		label_21 = new JLabel("2000\uC6D0");
		label_21.setBounds(123, 491, 57, 15);
		panel_2.add(label_21);
		
		label_22 = new JLabel("+");
		label_22.addMouseListener(new Label_22MouseListener());
		label_22.setHorizontalAlignment(SwingConstants.CENTER);
		label_22.setBounds(243, 412, 25, 15);
		panel_2.add(label_22);
		
		textField = new JTextField();
		textField.setText("0");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setBounds(280, 409, 57, 21);
		panel_2.add(textField);
		textField.setColumns(10);
		
		label_23 = new JLabel("-");
		label_23.addMouseListener(new Label_23MouseListener());
		label_23.setHorizontalAlignment(SwingConstants.CENTER);
		label_23.setBounds(349, 412, 25, 15);
		panel_2.add(label_23);
		
		label_24 = new JLabel("+");
		label_24.addMouseListener(new Label_24MouseListener());
		label_24.setHorizontalAlignment(SwingConstants.CENTER);
		label_24.setBounds(243, 452, 25, 15);
		panel_2.add(label_24);
		
		textField_1 = new JTextField();
		textField_1.setText("0");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(280, 449, 57, 21);
		panel_2.add(textField_1);
		
		label_25 = new JLabel("-");
		label_25.addMouseListener(new Label_25MouseListener());
		label_25.setHorizontalAlignment(SwingConstants.CENTER);
		label_25.setBounds(349, 452, 25, 15);
		panel_2.add(label_25);
		
		label_26 = new JLabel("+");
		label_26.addMouseListener(new Label_26MouseListener());
		label_26.setHorizontalAlignment(SwingConstants.CENTER);
		label_26.setBounds(243, 488, 25, 15);
		panel_2.add(label_26);
		
		textField_2 = new JTextField();
		textField_2.setText("0");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(280, 485, 57, 21);
		panel_2.add(textField_2);
		
		label_27 = new JLabel("-");
		label_27.addMouseListener(new Label_27MouseListener());
		label_27.setHorizontalAlignment(SwingConstants.CENTER);
		label_27.setBounds(349, 488, 25, 15);
		panel_2.add(label_27);
		
		label_28 = new JLabel("\uC0AC\uC6A9\uD3EC\uC778\uD2B8");
		label_28.setBounds(443, 412, 68, 15);
		panel_2.add(label_28);
		
		textField_3 = new JTextField();
		textField_3.addKeyListener(new TextField_3KeyListener());
		textField_3.setBounds(522, 409, 82, 21);
		panel_2.add(textField_3);
		textField_3.setColumns(10);
		
		label_29 = new JLabel("\uACB0\uC81C\uAE08\uC561: 0");
		label_29.setBounds(443, 435, 234, 15);
		panel_2.add(label_29);
		
		label_30 = new JLabel("\uACB0\uC81C");
		label_30.addMouseListener(new Label_30MouseListener());
		label_30.setOpaque(true);
		label_30.setForeground(Color.WHITE);
		label_30.setBackground(blue);
		label_30.setHorizontalAlignment(SwingConstants.CENTER);
		label_30.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		label_30.setBounds(776, 0, 222, 551);
		panel_2.add(label_30);
		
		loadpage1();
	}
	CardLayout card;
	public JLabel label_1;
	public JLabel label_2;
	public JScrollPane scrollPane;
	public JPanel panel_4;
	private LocalDate d1;
	private LocalDate d2;
	private void loadpage1() {
		try (var rs = res("select * from science")) {
			int w = panel_3.getWidth()/3;
			int h = panel_3.getHeight()/2;
			while(rs.next()) {
				JLabel jl = new JLabel();
				jl.setForeground(Color.white);
				jl.setHorizontalTextPosition(JLabel.CENTER);
				jl.setFont(new Font("맑은 고딕", 1, 20));
				jl.setSize(w,h);
				String txt= rs.getString("name");
				ImageIcon img = getIcon(rs.getBytes("s_img"), w, h);
				ImageIcon gray = getGrayIcon(rs.getBytes("s_img"),w,h);
				jl.setIcon(gray);
				int sno = rs.getInt("sno");
				jl.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						jl.setBorder(new LineBorder(Color.red,2));
						jl.setText(txt);
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
						loadpage2();
						card.next(D_예매.this);
					}
				});
				panel_3.add(jl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void pageload3() {
		try (var rs = res("select sno,lno,pno,s.name sname, p.name pname, s.address, start_date, end_date,s_img,p_img from location l join science s using(sno) join program p using(pno) where sno = "+sno+" and pno ="+pno+" and lno = "+lno)) {
			rs.next();
			lblImg1.setIcon(getIcon(rs.getBytes("s_img"),lblImg1.getWidth(), lblImg2.getHeight()));
			lblImg2.setIcon(getIcon(rs.getBytes("p_img"),lblImg2.getWidth(),lblImg2.getHeight()));
			lblName.setText(rs.getString("sname"));
			lblProgram.setText(rs.getString("pname"));
			lblAddress.setText(rs.getString("address"));
			d1 = rs.getDate("start_date").toLocalDate();
			d2 = rs.getDate("end_date").toLocalDate();
			for (int i = d1.getYear(); i <= d2.getYear(); i++) {
				comboBox.addItem(i);
			}
			comboBox.setSelectedIndex(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void loadpage2() {
		panel_4.removeAll();
		try (var rs = res("select * from (select *, curdate() between start_date and end_date r, row_number() over(partition by pno order by curdate() between start_date and end_date desc) and end_date from location join program using(pno) where sno = "+sno+") sub where r = 1 group by pno;")) {
			int i = 0;
			int h = 0;
			while(rs.next()) {
				D_예매패널 pp =new D_예매패널(getIcon(rs.getBytes("p_img"),180,160), rs.getString("name"), rs.getString("explanation"));
				DarkLabel dl =new DarkLabel();
				dl.setIcon(getIcon("아이콘/체크.png",50,50));
				DarkPanel dp = new DarkPanel(pp, dl);
				h = dp.getHeight();
				int pno = rs.getInt("pno");
				int lno = rs.getInt("lno");
				dp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						D_예매.this.pno = pno;
						D_예매.this.lno = lno;
						pageload3();
						card.next(D_예매.this);
					}
				});
				dp.setLocation(320*(i%2==0?1:0), 30+(dp.getHeight()+10)*i);
				panel_4.add(dp);
				i++;
			}
			panel_4.setPreferredSize(new Dimension(0,30+(h+10)*i));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			var rs =res("select * from science where sno = "+sno);
			rs.next();
			label_2.setText(rs.getString("name")+" 예약");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel_4.revalidate();
		panel_4.repaint();
	}
	
	private ImageIcon getGrayIcon(byte[] bytes, int w, int h) {
		Image img = getIcon(bytes, w, h).getImage();
		BufferedImage bi = new BufferedImage(w, h, 2);
		var g = bi.createGraphics();
		g.drawImage(img, 0, 0, null);
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				Color c = new Color(bi.getRGB(i, j));
				int mid = (c.getRed()+c.getBlue()+c.getGreen())/3;
				Color newc= new Color(mid,mid,mid);
				bi.setRGB(i, j, newc.getRGB());
			}
		}
		
		return new ImageIcon(bi);
	}
	
	boolean flag = false;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel lblImg1;
	public JLabel lblImg2;
	public JLabel label_7;
	public JLabel lblName;
	public JLabel lblAddress;
	public JLabel lblProgram;
	public JLabel label_11;
	public JComboBox comboBox;
	public JComboBox comboBox_1;
	public JComboBox comboBox_2;
	public JLabel label_12;
	public JLabel label_13;
	public JLabel label_14;
	public JLabel label_15;
	public JLabel label_16;
	public JLabel label_17;
	public JLabel label_18;
	public JLabel label_19;
	public JLabel label_20;
	public JLabel label_21;
	public JLabel label_22;
	public JTextField textField;
	public JLabel label_23;
	public JLabel label_24;
	public JTextField textField_1;
	public JLabel label_25;
	public JLabel label_26;
	public JTextField textField_2;
	public JLabel label_27;
	public JLabel label_28;
	public JTextField textField_3;
	public JLabel label_29;
	public JLabel label_30;
	public D_예매(int sno,int lno, int pno) {
		this();
		this.sno = sno;
		this.lno = lno;
		this.pno = pno;
		flag = true;
		card.last(this);
		pageload3();
	}

	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			card.previous(D_예매.this);
		}
	}
	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(flag) {
				var mf = (MainFrame)SwingUtilities.getWindowAncestor(scrollPane);
				mf.showPage(mf.prevPage.pop(), mf.prevtitle.pop());
			}
			else {
				card.previous(D_예매.this);
			}
		}
	}
	int[] cnt = {0,0,0};
	private class Label_22MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			cnt[0]++;
			textField.setText(4000*cnt[0]+"");
			cal();
		}

	}
	private void cal() {
		int price = getPrice();
		use = 0;
		label_29.setText("결제금액: "+price);
	}
	private int getPrice() {
		int result = 0;
		int[] p = {4000,3000,2000};
		for (int i = 0; i < cnt.length; i++) {
			result += p[i]*cnt[i];
		}
		return result;
	}

	private class Label_24MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			cnt[1]++;
			textField_1.setText(3000*cnt[0]+"");
			cal();
		}
	}
	private class Label_26MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			cnt[2]++;
			textField_2.setText(2000*cnt[0]+"");
			cal();
		}
	}
	private class Label_27MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			cnt[2]--;
			textField_2.setText(2000*cnt[0]+"");
			cal();
		}
	}
	private class Label_25MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			cnt[1]--;
			textField_1.setText(3000*cnt[0]+"");
			cal();
		}
	}
	private class Label_23MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			cnt[0]--;
			textField.setText(4000*cnt[0]+"");
			cal();
		}
	}
	private class ComboBoxItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			comboBox_1.removeAllItems();
			LocalDate imsi = d1;
			while(!imsi.isAfter(d2)) {
				comboBox_1.addItem(imsi.getMonthValue());
				imsi = imsi.plusMonths(1);
			}
			comboBox_1.setSelectedIndex(0);
		}
	}
	private class ComboBox_1ItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			comboBox_2.removeAllItems();
			LocalDate selMonth = d1.plusMonths(comboBox_1.getSelectedIndex());
			LocalDate selmontDate = LocalDate.of(selMonth.getYear(), selMonth.getMonthValue(), selMonth.equals(d1)?d1.getDayOfMonth():1);
			
			while(!selmontDate.isAfter(d2)) {
				comboBox_2.addItem(selmontDate.getDayOfMonth());
				selmontDate = selmontDate.plusDays(1);
				if(selmontDate.getMonthValue()!=selMonth.getMonthValue()) break;
			}
		}
	}
	private class TextField_3KeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if(getPrice()==0) {
				e.consume();
				return;
			}
			
			if(e.getKeyCode()==e.VK_ENTER) {
				try {
					var rs = res("select * from user where uno = "+BF.uno);
					rs.next();
					int point = rs.getInt("point");
					int  input =Integer.parseInt(textField_3.getText());
					if(input > point) {
						msgErr("현재 보유 중인 포인트는 "+point+"입니다.");
					}
					else {
						use = input>getPrice() ? getPrice() : input;
						label_29.setText("결제금액: "+(getPrice()- use));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		}
	}
	int use = 0;
	private class Label_30MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(Arrays.stream(cnt).sum()==0) {
				msgErr("티켓 구매 인원을 선택해주세요.");
				return;
			}
		LocalDate seldate = LocalDate.of((int)comboBox.getSelectedItem(), (int)comboBox_1.getSelectedItem(), (int)comboBox_2.getSelectedItem());
			try (var rs = res("select * from ticket where uno = "+BF.uno+" and date = '"+seldate+"'")) {
				if(rs.next()) {
					msgErr("해당 날짜에 다른 프로그램 예약이 존재합니다.");
				}
				else {
					int point = (int) ((getPrice()-use)/10.0);
					execute("update user set point = point - "+use+" + "+point+" where uno="+BF.uno);
					var pre = pre("insert into ticket values(0,?,?,?, ?,?,?, ?,?,?)");
					
					String code = getcode();
					var d = new QR코드(code);
					d.button.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							((MainFrame)SwingUtilities.getWindowAncestor(comboBox)).showPage(new H_예약내역(), "예약내역");
						}
					});
					d.setVisible(true);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}
	private String getcode() {
		String result = "";
		Random rand = new Random();
		for (int i = 0; i < 6; i++) {
			if(rand.nextBoolean())
				result += (char)('A'+rand.nextInt(26));
			else
				result += rand.nextInt(10);
		}
		
		return result.matches("^(?=.*[0-9].*)(?=.*[A-Z]).*$")? result : getcode();
	}
}
