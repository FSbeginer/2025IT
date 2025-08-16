import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

public class PageReservation extends BP {
	public JPanel panel;
	public JPanel panel_1;
	public JPanel panel_2;
	public JLabel label;
	public JPanel panel_3;
	
	int sno = 0, pno = 0,lno=0;
	public JLabel label_1;
	public JLabel label_2;
	public JScrollPane scrollPane;
	public JPanel panel_4;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel lblImg1;
	public JLabel lblname;
	public JLabel lblAdress;
	public JLabel label_6;
	public JLabel lblImg2;
	public JLabel lblProgram;
	public JLabel label_11;
	public JLabel label_12;
	public JComboBox comboBox;
	public JLabel label_13;
	public JComboBox comboBox_1;
	public JComboBox comboBox_2;
	public JLabel label_14;
	public JLabel label_15;
	public JLabel label_16;
	public JLabel label_17;
	public JLabel label_18;
	public JLabel label_19;
	public JLabel label_20;
	public JLabel label_21;
	public JTextField textField;
	public JTextField textField_1;
	public JTextField textField_2;
	public JLabel label_22;
	public JLabel label_23;
	public JLabel label_24;
	public JLabel label_25;
	public JLabel label_26;
	public JLabel label_27;
	public JLabel label_28;
	public JLabel label_29;
	public JLabel label_30;
	public JTextField textField_3;
	
	public PageReservation(int sno, int pno, int lno){
		this();
		this.lno = lno;
		this.sno = sno;
		this.pno = pno;
		loadReservation();
		((CardLayout)getLayout()).last(this);
	}
	
	public PageReservation() {
		setLayout(new CardLayout(0, 0));
		setName("예약");
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, "name_30016972470900");
		panel.setLayout(null);

		label = new JLabel("\uC608\uB9E4\uD560 \uACFC\uD559\uAD00\uC744 \uACE8\uB77C\uC8FC\uC138\uC694.");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setBounds(0, 0, 892, 59);
		panel.add(label);

		panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(32, 81, 823, 355);
		panel.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 3, 0, 0));

		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		add(panel_1, "name_30018563046100");
		panel_1.setLayout(null);
		
		label_1 = new JLabel(getIcon("아이콘/이전.png",118,52));
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setBounds(0, 0, 118, 52);
		panel_1.add(label_1);
		
		label_2 = new JLabel("New label");
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(0, 0, 892, 52);
		panel_1.add(label_2);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 62, 797, 372);
		panel_1.add(scrollPane);
		
		panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		scrollPane.setViewportView(panel_4);
		panel_4.setLayout(null);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		add(panel_2, "name_30023446702700");
		panel_2.setLayout(null);
		
		label_3 = new JLabel(getIcon("아이콘/이전.png",118,52));
		label_3.setBounds(0, 0, 118, 52);
		label_3.addMouseListener(new Label_1MouseListener());
		panel_2.add(label_3);
		
		label_4 = new JLabel("\uACFC\uD559\uAD00");
		label_4.setBounds(20, 62, 298, 15);
		panel_2.add(label_4);
		
		lblImg1 = new JLabel("");
		lblImg1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblImg1.setBounds(20, 87, 298, 158);
		panel_2.add(lblImg1);
		
		lblname = new JLabel("New label");
		lblname.setBounds(20, 255, 298, 15);
		panel_2.add(lblname);
		
		lblAdress = new JLabel("New label");
		lblAdress.setBounds(20, 280, 298, 15);
		panel_2.add(lblAdress);
		
		label_6 = new JLabel("\uD504\uB85C\uADF8\uB7A8");
		label_6.setBounds(356, 62, 298, 15);
		panel_2.add(label_6);
		
		lblImg2 = new JLabel("");
		lblImg2.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblImg2.setBounds(356, 87, 298, 158);
		panel_2.add(lblImg2);
		
		lblProgram = new JLabel("New label");
		lblProgram.setBounds(356, 255, 298, 15);
		panel_2.add(lblProgram);
		
		label_11 = new JLabel("\uB0A0\uC9DC");
		label_11.setBounds(20, 316, 36, 15);
		panel_2.add(label_11);
		
		label_12 = new JLabel("\uC778\uC6D0");
		label_12.setBounds(20, 348, 36, 15);
		panel_2.add(label_12);
		
		comboBox = new JComboBox();
		comboBox.setBounds(87, 308, 118, 30);
		panel_2.add(comboBox);
		
		label_13 = new JLabel("\uB144");
		label_13.setBounds(217, 316, 22, 15);
		panel_2.add(label_13);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(251, 308, 118, 30);
		panel_2.add(comboBox_1);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setBounds(408, 308, 118, 30);
		panel_2.add(comboBox_2);
		
		label_14 = new JLabel("\uC6D4");
		label_14.setBounds(381, 316, 22, 15);
		panel_2.add(label_14);
		
		label_15 = new JLabel("\uC77C");
		label_15.setBounds(538, 316, 22, 15);
		panel_2.add(label_15);
		
		label_16 = new JLabel("\uC131\uC778");
		label_16.setBounds(30, 373, 57, 20);
		panel_2.add(label_16);
		
		label_17 = new JLabel("\uCCAD\uC18C\uB144");
		label_17.setBounds(30, 413, 57, 20);
		panel_2.add(label_17);
		
		label_18 = new JLabel("\uC5B4\uB9B0\uC774");
		label_18.setBounds(30, 451, 57, 20);
		panel_2.add(label_18);
		
		label_19 = new JLabel("4000\uC6D0");
		label_19.setBounds(112, 373, 81, 20);
		panel_2.add(label_19);
		
		label_20 = new JLabel("3000\uC6D0");
		label_20.setBounds(112, 413, 81, 20);
		panel_2.add(label_20);
		
		label_21 = new JLabel("2000\uC6D0");
		label_21.setBounds(112, 451, 81, 20);
		panel_2.add(label_21);
		
		textField = new JTextField();
		textField.setText("0");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setBounds(251, 373, 81, 21);
		panel_2.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("0");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(251, 413, 81, 21);
		panel_2.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setText("0");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(251, 451, 81, 21);
		panel_2.add(textField_2);
		
		label_22 = new JLabel("+");
		label_22.addMouseListener(new Label_22MouseListener());
		label_22.setHorizontalAlignment(SwingConstants.CENTER);
		label_22.setBounds(217, 376, 27, 15);
		panel_2.add(label_22);
		
		label_23 = new JLabel("+");
		label_23.addMouseListener(new Label_23MouseListener());
		label_23.setHorizontalAlignment(SwingConstants.CENTER);
		label_23.setBounds(217, 416, 27, 15);
		panel_2.add(label_23);
		
		label_24 = new JLabel("+");
		label_24.addMouseListener(new Label_24MouseListener());
		label_24.setHorizontalAlignment(SwingConstants.CENTER);
		label_24.setBounds(217, 454, 27, 15);
		panel_2.add(label_24);
		
		label_25 = new JLabel("-");
		label_25.addMouseListener(new Label_25MouseListener());
		label_25.setHorizontalAlignment(SwingConstants.CENTER);
		label_25.setBounds(344, 376, 27, 15);
		panel_2.add(label_25);
		
		label_26 = new JLabel("-");
		label_26.addMouseListener(new Label_26MouseListener());
		label_26.setHorizontalAlignment(SwingConstants.CENTER);
		label_26.setBounds(344, 416, 27, 15);
		panel_2.add(label_26);
		
		label_27 = new JLabel("-");
		label_27.addMouseListener(new Label_27MouseListener());
		label_27.setHorizontalAlignment(SwingConstants.CENTER);
		label_27.setBounds(344, 454, 27, 15);
		panel_2.add(label_27);
		
		label_28 = new JLabel("\uACB0\uC81C");
		label_28.setOpaque(true);
		label_28.addMouseListener(new Label_28MouseListener());
		label_28.setForeground(Color.WHITE);
		label_28.setBackground(BF.blue);
		label_28.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_28.setHorizontalAlignment(SwingConstants.CENTER);
		label_28.setBounds(700, 0, 192, 492);
		panel_2.add(label_28);
		
		label_29 = new JLabel("\uC0AC\uC6A9\uD3EC\uC778\uD2B8");
		label_29.setBounds(446, 390, 81, 15);
		panel_2.add(label_29);
		
		label_30 = new JLabel("\uACB0\uC81C\uAE08\uC561: 0");
		label_30.setBounds(445, 416, 174, 15);
		panel_2.add(label_30);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.addKeyListener(new TextField_3KeyListener());
		textField_3.setBounds(538, 387, 81, 21);
		panel_2.add(textField_3);
		textField_3.setColumns(10);

		addScience();
	}
	private void loadReservation() {
		comboBox.removeAllItems();
		comboBox_1.removeAllItems();
		comboBox_2.removeAllItems();
		try (var rs = res("select lno, sno, pno, s.name sname, s.address, p.name pname, s_img, p_img, start_date, end_date from location l join program p using(pno) join science s using(sno) where pno = "+pno+" and sno = "+sno+" and lno = "+lno)) {
			rs.next();
			lblImg1.setIcon(getIcon(rs.getBytes("s_img"),lblImg1.getWidth(),lblImg1.getHeight()));
			lblImg2.setIcon(getIcon(rs.getBytes("p_img"),lblImg2.getWidth(),lblImg2.getHeight()));
			lblname.setText(rs.getString(4));
			lblAdress.setText(rs.getString(5));
			lblProgram.setText(rs.getString(6));
			
			// 이 방법 쓰지 마세요.
			var sd = rs.getDate("start_date").toLocalDate();
			var ed = rs.getDate("end_date").toLocalDate();
			int month1 = sd.getMonthValue()<ed.getMonthValue()?sd.getMonthValue():ed.getMonthValue();
			int month2 = sd.getMonthValue()>ed.getMonthValue()?sd.getMonthValue():ed.getMonthValue();
			int day1 = sd.getDayOfMonth()<ed.getDayOfMonth()?sd.getDayOfMonth():ed.getDayOfMonth();
			int day2 = sd.getDayOfMonth()>ed.getDayOfMonth()?sd.getDayOfMonth():ed.getDayOfMonth();
			
			for (int i = sd.getYear(); i <= ed.getYear(); i++) {
				comboBox.addItem(i);
			}
			for (int i = month1; i <= month2; i++) {
				comboBox_1.addItem(i);
			}
			for (int i = day1; i <= day2; i++) {
				comboBox_2.addItem(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void selectProgram() {
		panel_4.removeAll();
		try (var rs = res("select * from program p join location l using(pno) where sno = "+sno+" and (curdate() between start_date and end_date);")) {
			int w = scrollPane.getWidth()*2/3;
			int h = scrollPane.getHeight() * 2/5;
			int i = 0;
			while(rs.next()) {
				String name = rs.getString("name");
				String ex = rs.getString("explanation");
				PanelSelectProgram pp = new PanelSelectProgram(getIcon(rs.getBytes("p_img"),150,150), name, ex);
				pp.setSize(w, h);
				pp.setLocation(i%2==0? scrollPane.getWidth()/4+20:20, 30+(h+10)*i);
				
				JLabel jl = new DarkLabel();
				jl.setIcon(getIcon("아이콘/체크.png",50,50));
				jl.setSize(pp.getSize());
				jl.setLocation(pp.getLocation());
				jl.setVisible(false);
				
				int pno = rs.getInt("pno");
				int lno = rs.getInt("lno");
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						jl.setVisible(true);
					}
					@Override
					public void mouseExited(MouseEvent e) {
						jl.setVisible(false);
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						PageReservation.this.pno = pno;
						PageReservation.this.lno = lno;
						loadReservation();
						((CardLayout)getLayout()).next(PageReservation.this);
					}
				});
				panel_4.add(jl);
				panel_4.add(pp);
				i++;
			}
			panel_4.setPreferredSize(new Dimension(0, 30+(h+10)*i));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel_4.repaint();
		panel_4.revalidate();
	}

	private void addScience() {
		try (var rs = res("select * from science")) {
			int w = panel_3.getWidth()/3;
			int h = panel_3.getHeight()/2;
			while (rs.next()) {
				MyLabel jl = new MyLabel(getGrayIcon(getIcon(rs.getBytes("s_img"), w,h)), getIcon(rs.getBytes("s_img"), w,h), rs.getString("name"));
				int sno = rs.getInt("sno");
				jl.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						PageReservation.this.sno = sno;
						selectProgram();
						label_2.setText(jl.jl.getText()+" 예약");
						((CardLayout)getLayout()).next(PageReservation.this);
					}

				});
				panel_3.add(jl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	private ImageIcon getGrayIcon(ImageIcon icon) {
		Image img = icon.getImage();
		BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), 2);
		
		var g = bi.createGraphics();
		g.drawImage(img, 0, 0, null);
		
		for (int x = 0; x < img.getWidth(null); x++) {
			for (int y = 0; y < img.getHeight(null); y++) {
				Color c = new Color(bi.getRGB(x, y));
				
				int mid = (c.getRed()+c.getBlue()+c.getGreen())/3;
				Color newC = new Color(mid, mid,mid);
				bi.setRGB(x, y, newC.getRGB());
			}
		}
		
		return new ImageIcon(bi);
	}


	class MyLabel extends JLabel {
		JLabel jl = new DarkLabel();
		public MyLabel(ImageIcon grayImg, ImageIcon img, String txt) {
			super(grayImg);
			setLayout(new BorderLayout());
			jl.setVisible(false);
			jl.setBorder(new LineBorder(Color.red,2));
			jl.setText(txt);
			add(jl);
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					jl.setVisible(true);
					setIcon(img);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					jl.setVisible(false);
					setIcon(grayImg);
				}
			});
		}
	}
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(prevPage.size()==0)
				((CardLayout)getLayout()).previous(PageReservation.this);
			else {
				var item = prevPage.pop();
				((MainFrame)SwingUtilities.getWindowAncestor(PageReservation.this)).showPage((JPanel) item[0]);
				((JLabel)item[1]).setForeground(BF.blue);
			}
		}
	}
	int[] cnt = {0,0,0};
	private class Label_22MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			cnt[0]++;
			textField.setText(cnt[0]+"");
			calTot();
		}
	}
	private class Label_23MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			textField_1.setText(cnt[1]+"");
			cnt[1]++;
			calTot();
		}
	}
	private class Label_24MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			cnt[2]++;
			textField_2.setText(cnt[2]+"");
			calTot();
		}
	}
	private class Label_25MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			cnt[0] = Math.max(cnt[0]-1, 0);
			textField.setText(cnt[0]+"");
			calTot();
		}
	}
	private class Label_26MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			cnt[1] = Math.max(cnt[1]-1, 0);
			textField_1.setText(cnt[1]+"");
			calTot();
		}
	}
	private class Label_27MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			cnt[2] = Math.max(cnt[2]-1, 0);
			textField_2.setText(cnt[2]+"");
			calTot();
		}
	}
	private class Label_28MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			String day = comboBox.getSelectedItem()+"-"+comboBox_1.getSelectedItem()+"-"+comboBox_2.getSelectedItem();
			int sum = 0;
			for (int i : cnt) {
				sum +=i;
			}
			if(sum==0) {
				msgErr("티켓 구매 인원을 선택해주세요.");
				return;
			}
			try {
				var rs = res("select * from ticket where uno = "+BF.uno+" and date = "+day);
				if(rs.next()) {
					msgErr("해당 날짜에는 다른 프로그램 예약이 존재합니다.");
					return;
				}
				
			
				int point = getPrice(use)/10;
				execute("update user set point = point + "+point+" where uno = "+BF.uno);
				var pre = pre("insert into ticket values(0,?,?,?,?,?,?,?,?,?)");
				String code = getCode();
				preSet(pre, BF.uno, code, lno, cnt[0], cnt[1] ,cnt[2], getPrice(), use, day);
				pre.execute();
				
				var q = new QR(code);
				q.button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						q.dispose();
						((MainFrame)SwingUtilities.getWindowAncestor(PageReservation.this)).showPage(new PageReservationList());
					}
				});
				q.setVisible(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		private String getCode() {
			Random rand = new Random();
			var list = IntStream.range(65, 65+26).boxed().collect(Collectors.toList());
			var list2 = IntStream.range(0, 10).boxed().collect(Collectors.toList());
			String s = "";
			for (int i = 0; i < 6; i++) {
				if(rand.nextBoolean())
					s += (char)(int)list.get(rand.nextInt(26));
				else
					s += list2.get(rand.nextInt(10));
						
			}
			return s;
		}
	}
	int use;
	private class TextField_3KeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				try {
					int point = Integer.parseInt(textField_3.getText());
					var rs = res("select point from user where uno = "+BF.uno);
					rs.next();
					int having = rs.getInt(1);
					if(having<point) {
						msgErr("현재 보유 중인 포인트는 "+having+"입니다.");
						return;
					}
					
					if(getPrice()<point) {
						point = getPrice();
					}
					use = point;
					calTot();
				} catch (Exception e1) {
					e1.printStackTrace();
				} 
			}
		}


	}
	private void calTot() {
		label_30.setText(String.format("결제금액: %,d", getPrice(use)));
	}
	private int getPrice() {
		int sum = 4000 * cnt[0] + 3000 * cnt[1] + 2000 * cnt[2];
		return sum;
	}
	private int getPrice(int sale) {
		int sum = 4000 * cnt[0] + 3000 * cnt[1] + 2000 * cnt[2];
		return sum-sale;
	}
}
