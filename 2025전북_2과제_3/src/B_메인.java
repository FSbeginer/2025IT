import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;

public class B_¸ÞÀÎ extends BF {
	public JLabel label;
	public JTextField textField;
	public JButton button;
	public JPanel panel;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JLabel label_5;
	public JLabel label_6;
	public JLabel label_7;
	public JPanel panel_1;
	public JScrollPane scrollPane;
	public JPanel panel_2;
	public JPanel panel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					B_¸ÞÀÎ frame = new B_¸ÞÀÎ();
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
	public B_¸ÞÀÎ() {
		setTitle("\uBA54\uC778");
		setBounds(100, 100, 450, 547);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		label = new JLabel("Medinow");
		label.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(12, 10, 410, 48);
		getContentPane().add(label);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBorder(new MatteBorder(0, 0, 1, 0, blue));
		textField.setBounds(71, 53, 260, 22);
		getContentPane().add(textField);

		button = new JButton("\uAC80\uC0C9");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(343, 52, 79, 23);
		getContentPane().add(button);

		panel = new JPanel();
		panel.setBounds(44, 85, 358, 52);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 4, 0, 0));

		label_1 = new MyLabel("\uB9C8\uC774\uD648");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_1);

		label_2 = new MyLabel("\uACE0\uAC1D\uC13C\uD130");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2);

		label_3 = new MyLabel("\uBD84\uC11D");
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_3);

		label_4 = new MyLabel("\uC9C0\uB3C4");
		label_4.addMouseListener(new Label_4MouseListener());
		label_4.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_4);

		label_5 = new JLabel("\uBCD1\uC6D0");
		label_5.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		label_5.setBounds(25, 144, 57, 15);
		getContentPane().add(label_5);

		label_6 = new JLabel("\uC9C4\uB8CC\uACFC\uBAA9");
		label_6.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		label_6.setBounds(25, 248, 57, 15);
		getContentPane().add(label_6);

		label_7 = new JLabel("\uC99D\uC0C1");
		label_7.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		label_7.setBounds(25, 420, 57, 15);
		getContentPane().add(label_7);

		panel_1 = new JPanel();
		panel_1.addMouseWheelListener(new Panel_1MouseWheelListener());
		panel_1.setBounds(24, 169, 378, 69);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(26, 273, 376, 141);
		getContentPane().add(scrollPane);

		panel_2 = new JPanel();
		scrollPane.setViewportView(panel_2);
		panel_2.setLayout(null);

		panel_3 = new JPanel();
		panel_3.addMouseWheelListener(new Panel_3MouseWheelListener());
		panel_3.setBounds(25, 445, 378, 53);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);

		load();
		load2();
	}

	private void load2() {
		try (var rs = res("select * from symptom;")) {
			int w = (panel_3.getWidth()-40)/5;
			int h = panel_3.getHeight(), i =0;
			while(rs.next()) {
				JLabel jl = new JLabel(rs.getString(2),0);
				jl.setSize(w, h);
				jl.setLocation(w*i, 0);
				jl.setFont(new Font("¸¼Àº °íµñ",0, 12));
				jl.setForeground(blue);
				int cno = rs.getInt(3);
				jl.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new D_º´¿ø(cno),"D_º´¿ø");
					}
					@Override
					public void mousePressed(MouseEvent e) {
						cx =e.getX();
					}
				});
				jl.addMouseMotionListener(new MouseAdapter() {
					@Override
					public void mouseDragged(MouseEvent e) {
						int dx = e.getX() - cx;
						if(jls.size()==0||jls.get(0).getX()+dx>0||jls.get(pps.size()-1).getX()+dx<panel_3.getWidth()-jls.get(0).getWidth()) return;
						for (var pp : jls) {
							pp.setLocation(pp.getX()+dx, pp.getY());
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
	HashSet<Integer> cnos = new HashSet<>();
	List<JPanel> pps = new ArrayList<JPanel>();
	List<JLabel> jls = new ArrayList<>();
	private int cx;
	
	
	private void load() {
		panel_1.removeAll();
		panel_2.removeAll();
		cnos.clear();
		pps.clear();
		try {
			var rs = res("select * from hospital where name like '%"+where+"%';");
			int w = panel_1.getWidth()/4, h = panel_1.getHeight();
			int i = 0;
			while(rs.next()) {
				B_ÆÐ³Î pp = new B_ÆÐ³Î(getIcon("hospital/"+rs.getInt(1)+".png",w,h-15), rs.getString(2));
				pp.setSize(w, h);
				pp.setLocation((w+10)*i, 0);
				int hno = rs.getInt("hno");
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						cx =e.getX();
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new E_º´¿øÁ¤º¸(hno),"E_º´¿øÁ¤º¸");
					}
				});
				pp.addMouseMotionListener(new MouseAdapter() {
					@Override
					public void mouseDragged(MouseEvent e) {
						int dx = e.getX() - cx;
						if(pps.size()==0||pps.get(0).getX()+dx>0||pps.get(pps.size()-1).getX()+dx<panel_1.getWidth()-pps.get(0).getWidth()) return;
						for (var pp : pps) {
							pp.setLocation(pp.getX()+dx, pp.getY());
						}
					}
				});
				cnos.addAll(Arrays.stream(rs.getString("cno").trim().split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList()));
				panel_1.add(pp);
				pps.add(pp);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = res("select * from category where cno in ("+cnos.toString().replaceAll("\\s|\\[|\\]", "")+")")) {
			int w = (scrollPane.getWidth()-20)/4 ,  h =scrollPane.getHeight()/2,i=0;
			while(rs.next()) {
				B_ÆÐ³Î pp = new B_ÆÐ³Î(getIcon("category/"+rs.getInt(1)+".png",70,h-15), rs.getString(2));
				int cno =rs.getInt(1);
				pp.setSize(w, h);
				pp.setLocation(w*(i%4), h*(i/4));
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new D_º´¿ø(cno), "D_º´¿ø");
					}
				});
				panel_2.add(pp);
				i++;
			}
			panel_2.setPreferredSize(new Dimension(0, h*((i+3)/4)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		panel_1.revalidate();
		panel_1.repaint();
		panel_2.revalidate();
		panel_2.repaint();
	}
	private class Label_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			try (var rs = res("select * from user where uno = "+uno)) {
				rs.next();
				var p = new Point(rs.getInt("x"),rs.getInt("y")) ;
				showPage(new F_Áöµµ(p,uname), "F_Áöµµ");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new C_¸¶ÀÌÈ¨(), "C_¸¶ÀÌÈ¨");
		}
	}
	private class Label_3MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new J_ºÐ¼®(), "J_ºÐ¼®");
		}
	}
	private class Label_2MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new I_°í°´¼¾ÅÍ(), "I_°í°´¼¾ÅÍ");
		}
	}
	private class Panel_3MouseWheelListener implements MouseWheelListener {
		public void mouseWheelMoved(MouseWheelEvent e) {
			int dx = e.getWheelRotation()>0?-10:10;
			if(jls.size()==0||jls.get(0).getX()+dx>0||jls.get(pps.size()-1).getX()+dx<panel_3.getWidth()-jls.get(0).getWidth()) return;
			for (var pp : jls) {
				pp.setLocation(pp.getX()+dx, pp.getY());
			}
		}
	}
	private class Panel_1MouseWheelListener implements MouseWheelListener {
		public void mouseWheelMoved(MouseWheelEvent e) {
			int dx = e.getWheelRotation()>0?-10:10;
			if(pps.size()==0||pps.get(0).getX()+dx>0||pps.get(pps.size()-1).getX()+dx<panel_1.getWidth()-pps.get(0).getWidth()) return;
			for (var pp : pps) {
				pp.setLocation(pp.getX()+dx, pp.getY());
			}
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			where = textField.getText();
			load();
		}
	}
}

class MyLabel extends JLabel {
	boolean flag = false;

	public MyLabel(String txt) {
		super(txt);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				flag = true;
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				flag = false;
				repaint();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (flag) {
			g.setColor(BF.blue);
			g.fillOval(getWidth()/2-25, 0, 50, 50);
		}
		super.paintComponent(g);
	}
}
