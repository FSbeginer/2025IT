import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class B_메인 extends BF {
	public JLabel label;
	public JTextField textField;
	public JButton button;
	public JPanel panel;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JPanel panel_1;
	public JScrollPane scrollPane;
	public JPanel panel_2;
	public JPanel panel_3;
	public JLabel label_5;
	public JLabel label_6;
	public JLabel label_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					B_메인 frame = new B_메인();
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
	public B_메인() {
		setTitle("\uBA54\uC778");
		setBounds(100, 100, 473, 603);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		label = new JLabel("Medinow");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(12, 10, 433, 51);
		getContentPane().add(label);

		textField = new JTextField();
		textField.setFocusable(true);
		textField.setColumns(10);
		textField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(155, 208, 232)));
		textField.setBounds(74, 71, 265, 27);
		getContentPane().add(textField);

		button = new JButton("\uAC80\uC0C9");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(351, 71, 71, 27);
		getContentPane().add(button);

		panel = new JPanel();
		panel.setBounds(12, 105, 433, 51);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 4, 0, 0));

		label_1 = new MyLabel("\uB9C8\uC774\uD648");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_1);

		label_2 = new MyLabel("\uACE0\uAC1D\uC13C\uD130");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2);

		label_3 = new MyLabel("\uBD84\uC11D");
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_3);

		label_4 = new MyLabel("\uC9C0\uB3C4");
		label_4.addMouseListener(new Label_4MouseListener());
		label_4.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_4);

		panel_1 = new JPanel();
		panel_1.addMouseWheelListener(new Panel_1MouseWheelListener());
		panel_1.setBounds(12, 180, 433, 70);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(12, 273, 433, 214);
		getContentPane().add(scrollPane);

		panel_3 = new JPanel();
		scrollPane.setViewportView(panel_3);
		panel_3.setLayout(null);

		panel_2 = new JPanel();
		panel_2.addMouseWheelListener(new Panel_2MouseWheelListener());
		panel_2.setBounds(12, 511, 433, 43);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		label_5 = new JLabel("\uBCD1\uC6D0");
		label_5.setBounds(12, 166, 57, 15);
		getContentPane().add(label_5);

		label_6 = new JLabel("\uC9C4\uB8CC \uACFC\uBAA9");
		label_6.setBounds(12, 260, 57, 15);
		getContentPane().add(label_6);

		label_7 = new JLabel("\uC99D\uC0C1");
		label_7.setBounds(12, 497, 57, 15);
		getContentPane().add(label_7);

		load();
		addCate();
	}

	private void addCate() {
		try {
			var rs = res("select * from symptom");
			int w = panel_2.getWidth() / 5;
			int h = panel_2.getHeight(), i = 0;
			while (rs.next()) {
				JLabel jl = new JLabel(rs.getString("name"), 0);
				jl.setFont(new Font("맑은 고딕", 0, 12));
				jl.setForeground(blue);
				jl.setSize(w, h);
				jl.setLocation((w + 10) * i, 0);
				int cno = rs.getInt("cno");
				jl.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new D_병원(cno), "D_병원");
					}

					@Override
					public void mousePressed(MouseEvent e) {
						cx = e.getX();
					}
				});
				jl.addMouseMotionListener(new MouseAdapter() {
					@Override
					public void mouseDragged(MouseEvent e) {
						int dx = e.getX() - cx;
						if (pps2.size() == 0 || pps2.get(0).getX() + dx > 0
								|| pps2.get(pps.size() - 1).getX() < panel_2.getWidth() - pps2.get(0).getWidth())
							return;
						for (var jp : pps2) {
							jp.setLocation(jp.getX() + dx, 0);
						}
					}
				});
				pps2.add(jl);
				panel_2.add(jl);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	String like = "";
	int cx = 0;
	List<JPanel> pps = new ArrayList<JPanel>();
	List<JLabel> pps2 = new ArrayList<>();
	HashSet<Integer> cnos = new HashSet<Integer>();

	private void load() {
		panel_1.removeAll();
		panel_3.removeAll();
		pps.clear();
		cnos.clear();
		try (var rs = res("select * from hospital where true " + like)) {
			int w = panel_1.getWidth() / 4;
			int h = panel_1.getHeight(), i = 0;
			while (rs.next()) {
				B_패널 pp = new B_패널(getIcon("hospital/" + rs.getInt("hno") + ".png", w, h - 15), rs.getString("name"));
				pp.setSize(w, h);
				pp.setLocation((w + 20) * i, 0);
				int hno = rs.getInt("hno");
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new E_병원정보(hno), "E_병원정보");
					}

					@Override
					public void mousePressed(MouseEvent e) {
						cx = e.getX();
					}
				});
				pp.addMouseMotionListener(new MouseAdapter() {
					@Override
					public void mouseDragged(MouseEvent e) {
						int dx = e.getX() - cx;
						if (pps.size() == 0 || pps.get(0).getX() + dx > 0
								|| pps.get(pps.size() - 1).getX() < panel_1.getWidth() - pps.get(0).getWidth())
							return;
						for (JPanel jp : pps) {
							jp.setLocation(jp.getX() + dx, 0);
						}
					}
				});
				pps.add(pp);
				panel_1.add(pp);
				for (var cno : Arrays.stream(rs.getString("cno").split(",")).mapToInt(Integer::parseInt).toArray()) {
					cnos.add(cno);
				}
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(cnos.toString().replaceAll("\\[|\\]|\\s", ""));
		try (var rs = res(
				"select * from category where cno in(" + cnos.toString().replaceAll("\\[|\\]|\\s", "") + ")")) {
			int w = (scrollPane.getWidth() - 20) / 4;
			int h = scrollPane.getHeight() / 2, i = 0;
			while (rs.next()) {
				B_패널 pp = new B_패널(getIcon("category/" + rs.getInt("cno") + ".png", 60, 60), rs.getString("name"));
				pp.setSize(w, h);
				pp.setLocation(w * (i % 4), h * (i / 4));
				int cno = rs.getInt("cno");
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new D_병원(cno), "D_병원");
					}
				});
				panel_3.add(pp);
				i++;
			}
			panel_3.setPreferredSize(new Dimension(0, (h) * ((i + 3) / 4)));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		panel_1.revalidate();
		panel_1.repaint();
		panel_3.revalidate();
		panel_3.repaint();
	}

	private class Panel_1MouseWheelListener implements MouseWheelListener {
		public void mouseWheelMoved(MouseWheelEvent e) {
			int dx = e.getWheelRotation() > 0 ? -10 : 10;
			if (pps.size() == 0 || pps.get(0).getX() + dx > 0
					|| pps.get(pps.size() - 1).getX() +dx< panel_1.getWidth() - pps.get(0).getWidth())
				return;
			for (JPanel jp : pps) {
				jp.setLocation(jp.getX() + dx, 0);
			}
		}
	}

	private class Panel_2MouseWheelListener implements MouseWheelListener {
		public void mouseWheelMoved(MouseWheelEvent e) {
			int dx = e.getWheelRotation() > 0 ? -10 : 10;
			if (pps2.size() == 0 || pps2.get(0).getX() + dx > 0
					|| pps2.get(pps.size() - 1).getX()+dx < panel_2.getWidth() - pps2.get(0).getWidth())
				return;
			for (var jp : pps2) {
				jp.setLocation(jp.getX() + dx, 0);
			}
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			like = "and name like '%"+textField.getText()+"%'";
			load();
			if(panel_1.getComponents().length==0) {
				msgErr("검색 결과가 없습니다.");
				like = "";
				textField.setText("");
				load();
				return;
			}
		}
	}
	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new J_분석(),"J_분석");
		}
	}
	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new I_고객센터(),"I_고객센터");
		}
	}
	private class Label_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			Point p=null;
			try (var rs = res("select * from user where uno = "+uno)) {
				rs.next();
				p = new Point(rs.getInt("x"),rs.getInt("y"));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			showPage(new F_지도(p,uname),"F_지도");
		}
	}
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new C_마이홈(),"C_마이홈");
		}
	}
}

class MyLabel extends JLabel {
	boolean enter = false;

	public MyLabel(String s) {
		super(s);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				enter = true;
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				enter = false;
				repaint();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(BF.blue);
		if (enter) {
			g.fillOval(getWidth() / 2 - 25, getHeight() / 2 - 25, 50, 50);
		}
		super.paintComponent(g);
	}
}
