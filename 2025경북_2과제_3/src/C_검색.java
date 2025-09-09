import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class C_검색 extends BP {

	/**
	 * Create the panel.
	 */
	int cno;
	public JComboBox comboBox;
	public JPanel panel;
	public JTextField textField;
	public JPanel panel_1;
	public JTextField textField_1;
	public JLabel label;
	public JTextField textField_2;
	public JButton button;
	public JButton button_1;
	public JButton button_2;
	public JPanel panel_2;
	public JScrollPane scrollPane;
	public JPanel panel_3;
	public C_검색(int cno) {
		this.cno = cno;
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uC0C1\uD488\uBA85", "\uAC00\uACA9"}));
		comboBox.setBounds(12, 10, 125, 33);
		add(comboBox);
		
		panel = new JPanel();
		panel.setBounds(149, 10, 475, 33);
		add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		textField = new JTextField();
		panel.add(textField, "name_29898140801300");
		textField.setColumns(10);
		
		panel_1 = new JPanel();
		panel.add(panel_1, "name_29902134840100");
		panel_1.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setBounds(0, 0, 213, 33);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		label = new JLabel("~");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(210, 0, 48, 33);
		panel_1.add(label);
		
		textField_2 = new JTextField();
		textField_2.setBounds(260, 0, 215, 33);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		button = new JButton("\uAC80\uC0C9");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(636, 15, 97, 23);
		add(button);
		
		button_1 = new JButton("\u2191");
		button_1.addActionListener(new Button_1ActionListener());
		button_1.setBounds(739, 15, 60, 23);
		add(button_1);
		
		button_2 = new JButton("\u2193");
		button_2.addActionListener(new Button_2ActionListener());
		button_2.setBounds(805, 15, 60, 23);
		add(button_2);
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(12, 53, 134, 475);
		add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(149, 53, 776, 475);
		add(scrollPane);
		
		panel_3 = new JPanel();
		scrollPane.setViewportView(panel_3);
		panel_3.setLayout(null);
		
		addCate();
		load();
		selCate(cno);
	}
	private void addCate() {
		createCate("전체",0);
		try (var rs = res("select * from category")) {
			while(rs.next()) {
				createCate(rs.getString(2), rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	List<JLabel> jls= new ArrayList<JLabel>();
	private void createCate(String name, int idx) {
		JLabel jl = new JLabel(name,0);
		jl.setOpaque(true);
		jl.setBackground(Color.LIGHT_GRAY);
		jl.setForeground(Color.black);
		jl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selCate(idx);
			}

		});
		jl.setFont(new Font("맑은 고딕",1, 17));
		jls.add(jl);
		panel_2.add(jl);
	}
	private void selCate(int idx) {
		for (JLabel jl : jls) {
			jl.setBackground(Color.LIGHT_GRAY);
			jl.setForeground(Color.black);
		}
		jls.get(idx).setBackground(Color.white);
		jls.get(idx).setForeground(BF.blue);
		if(idx!=0)
			where = "and cno ="+idx;
		else
			where = "";
		order = "";
	}
	
	String where = "", like = "", order = "";
	
	private void load() {
		panel_3.removeAll();
		try (var rs = res(
				"select *, sum(o.quantity) cnt, avg(review.rating) star from product left join `order` o using(pno) left join review using(ono) where true "+where+" "+like+" group by pno "+order)) {
			System.out.println("select *, sum(o.quantity) cnt, avg(review.rating) star from product left join `order` o using(pno) left join review using(ono) where true "+where+" "+like+" group by pno "+order);
			int w = (scrollPane.getWidth() - 20 - 60) / 4;
			int h = (scrollPane.getHeight() - 10) / 2, i = 0;
			while (rs.next()) {
				A_패널 pp = new A_패널(getIcon(rs.getBytes("img"), w - 10, h - 90), rs.getString("pname"),
						rs.getInt("price"), rs.getInt("cnt"), rs.getDouble("star"));
				pp.setSize(w, h);
				pp.setLocation((w + 20) * (i % 4), (h + 10) * (i / 4));
				int pno = rs.getInt("pno");
				pp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(BF.uno!=0)
							((MainFrame) SwingUtilities.getWindowAncestor(pp)).showPage(new D_상세정보(pno), "D_상세정보");
					}
				});
				panel_3.add(pp);
				i++;
			}
			panel_3.setPreferredSize(new Dimension(0, (h+10)*((i+3)/4)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel_3.revalidate();
		panel_3.repaint();
	}

	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(comboBox.getSelectedIndex()==0) {
				((CardLayout)panel.getLayout()).first(panel);
			}
			else {
				((CardLayout)panel.getLayout()).last(panel);
			}
		}
	}
	private class Button_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			order = "order by price";
			load();
		}
	}
	private class Button_2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			order = "order by price desc";
			load();
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(comboBox.getSelectedIndex()==0) {
				like = "and pname like '%"+textField.getText()+"%'";
			}
			else {
				try {
					var p1 = Integer.parseInt(textField_1.getText());
					var p2 = Integer.parseInt(textField_2.getText());
					like = "price between "+p1+" and "+p2;
				} catch (NumberFormatException e1) {
					msgErr("가격을 숫자로 입력하세요.");
					return;
				}
			}
			order = "";
			load();
			if(panel_3.getComponents().length==0) {
				msgErr("검색 결과가 없습니다.");
				selCate(0);
				where ="";
				like = "";
				order = "";
				((CardLayout)panel.getLayout()).first(panel);
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				load();
			}
			
		}
	}
}
