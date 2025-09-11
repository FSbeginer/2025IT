import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseWheelListener;

public class B_메인 extends BF {

	private JPanel contentPane;
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
	public JLabel label_6;
	public JScrollPane scrollPane;
	public JPanel panel_2;
	public JLabel label_7;
	public JPanel panel_3;

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

	public B_메인() {
		setTitle("\uBA54\uC778");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 516, 631);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label = new JLabel("Medinow");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 500, 70);
		contentPane.add(label);
		
		textField = new LineTextField();
		textField.setBounds(58, 57, 337, 43);
		contentPane.add(textField);
		textField.setColumns(10);
		
		button = new JButton("\uAC80\uC0C9");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(403, 57, 65, 43);
		contentPane.add(button);
		
		panel = new JPanel();
		panel.setBounds(41, 100, 428, 51);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 4, 0, 0));
		
		label_1 = new MyLabel("\uB9C8\uC774\uD648");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_1);
		
		label_2 = new MyLabel("\uACE0\uAC1D\uC13C\uD130");
		label_2.addMouseListener(new Label_2MouseListener());
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2);
		
		label_3 = new MyLabel("\uBD84\uC11D");
		label_3.addMouseListener(new Label_3MouseListener());
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_3);
		
		label_4 = new MyLabel("\uC9C0\uB3C4");
		label_4.addMouseListener(new Label_4MouseListener());
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_4);
		
		panel_1 = new JPanel();
		panel_1.addMouseWheelListener(new Panel_1MouseWheelListener());
		panel_1.setBounds(12, 172, 476, 70);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		label_5 = new JLabel("\uBCD1\uC6D0");
		label_5.setFont(new Font("굴림", Font.PLAIN, 13));
		label_5.setBounds(12, 147, 55, 15);
		contentPane.add(label_5);
		
		label_6 = new JLabel("\uC9C4\uB8CC \uACFC\uBAA9");
		label_6.setFont(new Font("굴림", Font.PLAIN, 13));
		label_6.setBounds(12, 260, 72, 15);
		contentPane.add(label_6);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(12, 285, 476, 219);
		contentPane.add(scrollPane);
		
		panel_2 = new JPanel();
		scrollPane.setViewportView(panel_2);
		panel_2.setLayout(null);
		
		label_7 = new JLabel("\uC99D\uC0C1");
		label_7.setFont(new Font("굴림", Font.PLAIN, 13));
		label_7.setBounds(12, 510, 57, 25);
		contentPane.add(label_7);
		
		panel_3 = new JPanel();
		panel_3.addMouseWheelListener(new Panel_3MouseWheelListener());
		panel_3.setBounds(12, 540, 476, 42);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		addHospital("");
		addCategory("");
		addSymptom();
	}

	List<JLabel> jls = new ArrayList<JLabel>();
	private void addSymptom() {
		try (var rs = res("select * from symptom;")) {
			int w = panel_3.getWidth()/5;
			int i = 0;
			while (rs.next()) {
				JLabel jl = new JLabel(rs.getString(2),0);
				jl.setForeground(blue);
				jl.setFont(new Font("굴림", Font.BOLD, 14));
				jl.setSize(w, panel_3.getHeight());
				jl.setLocation((w+5)*i, 0);
				int cno= rs.getInt(3);
				jl.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new D_병원(cno),"D_병원");
					}
					@Override
					public void mousePressed(MouseEvent e) {
						cx = e.getX();
					}
				});
				jl.addMouseMotionListener(new MouseAdapter() {
					@Override
					public void mouseDragged(MouseEvent e) {
						int dx = e.getX()-cx;
						if(jls.size()==0|| jls.get(0).getX()+dx>0||jls.get(jls.size()-1).getX()+dx<panel_3.getWidth()-w) return;
						for (var jl : jls) {
							jl.setLocation(jl.getX()+dx, 0);
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

	private void addCategory(String where) {
		panel_2.removeAll();
		try (var rs = res("select * from category where true "+where)) {
			int i = 0;
			int w = (scrollPane.getWidth()-25)/4, h= scrollPane.getHeight()/2;
			while(rs.next()) {
				ImagePanel pp = new ImagePanel(getIcon("category/"+rs.getInt(1)+".png",80,80), rs.getString(2));
				pp.setSize(w,h);
				pp.setLocation(w*(i%4), h*(i/4));
				int cno = rs.getInt(1);
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						showPage(new D_병원(cno),"D_병원");
					}
				});
				panel_2.add(pp);
				i++;
			}
			panel_2.setPreferredSize(new Dimension(0, h*((i+3)/4)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel_2.revalidate();
		panel_2.repaint();
	}

	List<ImagePanel> hospitals = new ArrayList<ImagePanel>();
	HashSet<Integer> cnos = new HashSet<>();
	int cx = 0;
	
	private void addHospital(String where) {
		hospitals.clear();
		panel_1.removeAll();
		cnos.clear();
		try (var rs = res("select * from hospital where true "+where)) {
			int w = panel_1.getWidth()/4, h =panel_1.getHeight();
			while(rs.next()) {
				ImagePanel pp = new ImagePanel(getIcon("hospital/"+rs.getInt(1)+".png",w,h-20), rs.getString(2));
				pp.setLocation((w+15)*hospitals.size(), 0);
				pp.setSize(w, h);
				int hno = rs.getInt(1);
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
						int dx = e.getX() -cx;
						if(hospitals.size()==0||hospitals.get(0).getX()+dx>0||hospitals.get(hospitals.size()-1).getX()+dx<panel_1.getWidth()-hospitals.get(0).getWidth()) return;
						for (ImagePanel pp : hospitals) {
							pp.setLocation(pp.getX()+dx, 0);
						}
					}
				});
				var list = Arrays.asList(rs.getString("cno").trim().split(",")).stream().mapToInt(Integer::parseInt).toArray();
				for (int i : list) {
					cnos.add(i);
				}
				hospitals.add(pp);
				panel_1.add(pp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel_1.revalidate();
		panel_1.repaint();
	}
	
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			addHospital("and name like '%"+textField.getText()+"%'");
			if(cnos.isEmpty()) {
				msgErr("검색 결과가 없습니다.");
				return;
			}
			addCategory("and cno in("+cnos.toString().replaceAll("\\s|\\[|\\]", "")+")");
		}
	}
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new C_마이홈(), "C_마이홈");
		}
	}
	private class Label_4MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			showPage(new F_지도(),"F_지도");
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
	private class Panel_3MouseWheelListener implements MouseWheelListener {
		public void mouseWheelMoved(MouseWheelEvent e) {
			int dx = e.getWheelRotation()>0? -10 : 10;
			if(jls.size()==0|| jls.get(0).getX()+dx>0||jls.get(jls.size()-1).getX()+dx<panel_3.getWidth()-jls.get(0).getWidth()) return;
			for (var jl : jls) {
				jl.setLocation(jl.getX()+dx, 0);
			}
		}
	}
	private class Panel_1MouseWheelListener implements MouseWheelListener {
		public void mouseWheelMoved(MouseWheelEvent e) {
			int dx = e.getWheelRotation() > 0? -10 : 10;
			if(hospitals.size()==0||hospitals.get(0).getX()+dx>0||hospitals.get(hospitals.size()-1).getX()+dx<panel_1.getWidth()-hospitals.get(0).getWidth()) return;
			for (ImagePanel pp : hospitals) {
				pp.setLocation(pp.getX()+dx, 0);
			}
		}
	}
}
class MyLabel extends JLabel{
	boolean isEnter = false;
	public MyLabel(String txt) {
		super(txt);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				isEnter = true;
				repaint();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				isEnter = false;
				repaint();
			}
		});
	}
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(BF.blue);
		int x = getWidth()/2-25;
		if(isEnter)
			g.fillOval(x, 0, 50, 50);
		super.paintComponent(g);
	}
}
