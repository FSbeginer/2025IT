import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class C_검색 extends BP {
	public JComboBox comboBox;
	public JPanel panel;
	public JTextField textField;
	public JPanel panel_1;
	public JTextField textField_1;
	public JButton button;
	public JButton button_1;
	public JButton button_2;
	public JLabel label;
	public JTextField textField_2;
	public JPanel panel_2;
	public JScrollPane scrollPane;
	public JPanel panel_3;

	/**
	 * Create the panel.
	 */
	public C_검색(int cno) {
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uC0C1\uD488\uBA85", "\uAC00\uACA9"}));
		comboBox.setBounds(12, 10, 108, 39);
		add(comboBox);
		
		panel = new JPanel();
		panel.setBounds(132, 10, 551, 39);
		add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		textField = new JTextField();
		panel.add(textField, "name_8963814646900");
		textField.setColumns(10);
		
		panel_1 = new JPanel();
		panel.add(panel_1, "name_8966709177300");
		panel_1.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setBounds(0, 0, 244, 39);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		label = new JLabel("~");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(247, 0, 49, 39);
		panel_1.add(label);
		
		textField_2 = new JTextField();
		textField_2.setBounds(300, 0, 229, 39);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		button = new JButton("\uAC80\uC0C9");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(695, 10, 74, 39);
		add(button);
		
		button_1 = new JButton("\u2191");
		button_1.setFont(new Font("굴림", Font.PLAIN, 9));
		button_1.addActionListener(new Button_1ActionListener());
		button_1.setBounds(775, 11, 42, 39);
		add(button_1);
		
		button_2 = new JButton("\u2193");
		button_2.setFont(new Font("굴림", Font.PLAIN, 9));
		button_2.addActionListener(new Button_2ActionListener());
		button_2.setBounds(822, 10, 42, 39);
		add(button_2);
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(12, 58, 108, 391);
		add(panel_2);
		panel_2.setLayout(new GridLayout(11, 1, 0, 5));
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(132, 58, 732, 391);
		add(scrollPane);
		
		panel_3 = new JPanel();
		scrollPane.setViewportView(panel_3);
		panel_3.setLayout(null);

		addCate();
		selectCate(category.get(cno));
		load();
	}

	private void load() {
		panel_3.removeAll();
		try (var rs = res("select pno, p.img, pname, p.price, sum(o.quantity) cnt, round(avg(rating),1) star , avg(rating) star from product p join category c using(cno) left join `order` o using(pno) left join review using(ono) where true "+where+" "+like+" group by pno "+order)) {
			int i = 0;
			int w = (scrollPane.getWidth()-65)/4;
			int h = (scrollPane.getHeight()-10)/2;
			while(rs.next()) {
				A_Panel pp =new A_Panel(getIcon(rs.getBytes(2), 100,100), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getDouble(6));
				int pno =rs.getInt(1);
				pp.setSize(w, h);
				pp.setLocation((i%4)*(w+10), (h+10)*(i/4));
				pp.label_4.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(BF.uno!=0)
							((MainFrame)SwingUtilities.getWindowAncestor(pp)).showPage(new D_상세정보(pno), "D_상세정보");
					}
				});
				panel_3.add(pp);
				i++;
			}
			panel_3.setPreferredSize(new Dimension(0,(h+10)*((i+3)/4)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel_3.revalidate();
		panel_3.repaint();
	}

	private void addCate() {
		createCate("전체");
		try (var rs = res("select * from category;")) {
			while(rs.next()) {
				createCate(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	List<JLabel> category = new ArrayList<JLabel>();
	String where = "", like = "", order = "";
	private void createCate(String string) {
		JLabel jl = new JLabel(string, 0);
		jl.setOpaque(true);
		jl.setBackground(Color.lightGray);
		jl.setForeground(Color.black);
		jl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectCate(jl);
			}
		});
		panel_2.add(jl);
		category.add(jl);
	}
	
	private void selectCate(JLabel jl) {
		for (JLabel cate : category) {
			cate.setBackground(Color.lightGray);
			cate.setForeground(Color.black);
		}
		jl.setBackground(Color.white);
		jl.setForeground(blue);
		int idx = category.indexOf(jl);
		if(idx==0) {
			where = "";
		}
		else {
			where ="and cno = "+idx;
		}
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
			order = "order by "+(comboBox.getSelectedIndex()==0?"pname":"price");
			load();
		}
	}
	private class Button_2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			order = "order by "+(comboBox.getSelectedIndex()==0?"pname":"price")+" desc";
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
					Integer.parseInt(textField_1.getText());
					Integer.parseInt(textField_2.getText());
				} catch (NumberFormatException e1) {
					msgErr("가격을 숫자로 입력하세요.");
					return;
				}
				like = "and price between "+textField_1.getText()+" and "+textField_2.getText();
			}
			order = "";
			load();
			if(panel_3.getComponents().length==0) {
				msgErr("검색 결과가 없습니다.");
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				where ="";
				order ="";
				like = "";
				selectCate(category.get(0));
				comboBox.setSelectedIndex(0);
				load();
			}
		}
	}
}
