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
import java.awt.event.MouseWheelEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JScrollPane;
import java.awt.event.MouseWheelListener;
import javax.swing.border.EmptyBorder;
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
	public JLabel label_5;
	public JScrollPane scrollPane;
	public JPanel panel_2;
	public JLabel label_6;
	public JLabel label_7;
	public JPanel panel_3;

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
		setBounds(100, 100, 450, 621);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		label = new JLabel("Medinow");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 23));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 434, 54);
		getContentPane().add(label);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(143, 197, 214)));
		textField.setBounds(83, 50, 252, 30);
		getContentPane().add(textField);

		button = new JButton("\uAC80\uC0C9");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(347, 50, 62, 30);
		getContentPane().add(button);

		panel = new JPanel();
		panel.setBounds(12, 85, 410, 48);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 4, 0, 0));

		label_1 = new myLabel("\uB9C8\uC774\uD648");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_1);

		label_2 = new myLabel("\uACE0\uAC1D\uC13C\uD130");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2);

		label_3 = new myLabel("\uBD84\uC11D");
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_3);

		label_4 = new myLabel("\uC9C0\uB3C4");
		label_4.addMouseListener(new Label_4MouseListener());
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_4);

		panel_1 = new JPanel();
		panel_1.addMouseWheelListener(new Panel_1MouseWheelListener());
		panel_1.setBounds(12, 166, 410, 77);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		label_5 = new JLabel("\uBCD1\uC6D0");
		label_5.setBounds(22, 143, 57, 15);
		getContentPane().add(label_5);

		scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(12, 275, 410, 183);
		getContentPane().add(scrollPane);

		panel_2 = new JPanel();
		scrollPane.setViewportView(panel_2);
		panel_2.setLayout(null);

		label_6 = new JLabel("\uC9C4\uB8CC \uACFC\uBAA9");
		label_6.setBounds(22, 253, 57, 15);
		getContentPane().add(label_6);

		label_7 = new JLabel("  \uC99D\uC0C1");
		label_7.setBounds(12, 494, 57, 15);
		getContentPane().add(label_7);

		panel_3 = new JPanel();
		panel_3.addMouseWheelListener(new Panel_3MouseWheelListener());
		panel_3.setBounds(12, 522, 410, 50);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);

		load();
		load2();
	}

	List<JLabel> jls = new ArrayList<JLabel>();

	private void load2() {
		try (var rs = res("select * from symptom")) {
			int w = (panel_3.getWidth() - 25) / 5;
			int h = panel_3.getHeight();
			int i = 0;
			while (rs.next()) {
				JLabel jl = new JLabel(rs.getString(2), 0);
				jl.setForeground(blue);
				jl.setSize(w, h);
				jl.setLocation((w + 5) * i, 0);
				int cno = rs.getInt("cno");
				jl.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						cx = e.getX();
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new D_병원(cno),"D_병원");
					}
				});
				jl.addMouseMotionListener(new MouseAdapter() {
					@Override
					public void mouseDragged(MouseEvent e) {
						int dx = e.getX() - cx;
						if (jls.size() == 0 || jls.get(0).getX() + dx > 0
								|| jls.get(jls.size() - 1).getX() + dx < panel_3.getWidth() - w - 10)
							return;
						for (var b_패널 : jls) {
							b_패널.setLocation(b_패널.getX() + dx, 0);
						}
					}
				});

				panel_3.add(jl);
				jls.add(jl);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	String where = "";
	private int cx;

	List<B_패널> pps = new ArrayList<B_패널>();
	HashSet<Integer> cnos = new HashSet<>();

	private void load() {
		pps.clear();
		panel_1.removeAll();
		panel_2.removeAll();
		cnos.clear();
		try (var rs = res("select * from hospital where true " + where)) {
			int w = (panel_1.getWidth() - 40) / 4;
			int h = panel_1.getHeight();
			int i = 0;
			while (rs.next()) {
				B_패널 pp = new B_패널(getIcon("hospital/" + rs.getInt("hno") + ".png", w, h - 15), rs.getString("name"));
				pp.setSize(w, h);
				pp.setLocation((w + 10) * i, 0);
				int hno = rs.getInt("hno");
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						cx = e.getX();
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new E_병원정보(hno), "E_병원정보");
					}
				});
				pp.addMouseMotionListener(new MouseAdapter() {
					@Override
					public void mouseDragged(MouseEvent e) {
						int dx = e.getX() - cx;
						if (pps.size() == 0 || pps.get(0).getX() + dx > 0
								|| pps.get(pps.size() - 1).getX() + dx < panel_1.getWidth() - w - 10)
							return;
						for (B_패널 b_패널 : pps) {
							b_패널.setLocation(b_패널.getX() + dx, 0);
						}
					}
				});

				pps.add(pp);
				panel_1.add(pp);
				for (Integer integer : Arrays.stream(rs.getString("cno").trim().split(",")).mapToInt(Integer::parseInt)
						.toArray()) {
					cnos.add(integer);
				}
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			var rs = res(
					"select * from category where cno in(" + (cnos.toString().replaceAll("\\[|\\s|\\]", "")) + ");");
			int w = scrollPane.getWidth() / 4;
			int h = scrollPane.getHeight() / 2;
			int i = 0;
			while (rs.next()) {
				B_패널 pp = new B_패널(getIcon("category/" + rs.getInt("cno") + ".png"), rs.getString(2));
				pp.setSize(w, h);
				pp.setLocation(w * (i % 4), (i / 4) * h);
				panel_2.add(pp);
				int cno = rs.getInt("cno");
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new D_병원(cno),"D_병원");
					}
				});
				i++;
			}
			panel_2.setPreferredSize(new Dimension(0, h * ((i + 3) / 4)));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		panel_1.revalidate();
		panel_1.repaint();
		panel_2.revalidate();
		panel_2.repaint();
	}

	private class Panel_1MouseWheelListener implements MouseWheelListener {
		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			int dx = e.getWheelRotation() > 0 ? -10 : 10;
			if (pps.size() == 0 || pps.get(0).getX() + dx > 0
					|| pps.get(pps.size() - 1).getX() + dx < panel_1.getWidth() - pps.get(0).getWidth() - 10)
				return;
			for (B_패널 b_패널 : pps) {
				b_패널.setLocation(b_패널.getX() + dx, 0);
			}
		}
	}

	private class Panel_3MouseWheelListener implements MouseWheelListener {
		public void mouseWheelMoved(MouseWheelEvent e) {
			int dx = e.getWheelRotation() > 0 ? -10 : 10;
			if (jls.size() == 0 || jls.get(0).getX() + dx > 0
					|| jls.get(jls.size() - 1).getX() + dx < panel_3.getWidth() - jls.get(0).getWidth() - 10)
				return;
			for (var b_패널 : jls) {
				b_패널.setLocation(b_패널.getX() + dx, 0);
			}
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			where = "and name like '%"+textField.getText()+"%'";
			load();
		}
	}
	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new J_분석(),"J_분석");
		}
	}
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new C_마이홈(),"C_마이홈");
		}
	}
	private class Label_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			try {
				var rs = res("select * from user where uno = "+uno);
				rs.next();
				var  p = new Point(rs.getInt("x"), rs.getInt("y"));
				showPage(new F_지도(p, uname),"F_지도");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new I_고객센터(),"I_고객센터");
		}
	}
}

class myLabel extends JLabel {
	boolean ck;

	public myLabel(String txt) {
		super(txt);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ck = true;
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				ck = false;
				repaint();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (ck) {
			g.setColor(BF.blue);
			g.fillOval(getWidth() / 2 - 25, getHeight() / 2 - 25, 50, 50);
		}
		super.paintComponent(g);
	}

}
