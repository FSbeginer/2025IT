import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class G_배송정보 extends BP {
	public JLabel label;
	public JScrollPane scrollPane;
	public JTable table;
	public JLabel label_1;
	public JLabel label_2;
	public JLabel label_3;
	public JLabel label_4;
	public JPanel panel;
	public JComboBox comboBox;
	private DefaultTableModel model;

	/**
	 * Create the panel.
	 */
	public G_배송정보() {
		
		label = new JLabel("\uBC30\uC1A1\uC815\uBCF4");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setBounds(12, 10, 187, 44);
		add(label);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uC804\uCCB4", "\uACB0\uC81C \uC644\uB8CC", "\uBC30\uC1A1\uC900\uBE44", "\uBC30\uC1A1 \uC911", "\uBC30\uC1A1 \uC644\uB8CC"}));
		comboBox.setBounds(734, 26, 116, 30);
		add(comboBox);
		
		panel_1 = new JPanel();
		panel_1.setBounds(12, 63, 852, 364);
		add(panel_1);
		panel_1.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 10, 852, 159);
		panel_1.add(scrollPane);
		scrollPane.getViewport().setBackground(Color.white);
		
		table = new JTable();
		table.addMouseListener(new TableMouseListener());
		scrollPane.setViewportView(table);
		
		label_1 = new JLabel("");
		label_1.setBounds(0, 193, 187, 123);
		panel_1.add(label_1);
		label_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		label_2 = new JLabel("New label");
		label_2.setBounds(199, 193, 182, 30);
		panel_1.add(label_2);
		
		label_3 = new JLabel("New label");
		label_3.setBounds(199, 233, 182, 30);
		panel_1.add(label_3);
		
		label_4 = new JLabel("New label");
		label_4.setBounds(199, 273, 182, 30);
		panel_1.add(label_4);
		
		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				int[] idx = {0,1,3,5};
				for (int i = 1; i <= now; i++) {
					idx[i]++;
				}
				
				int w = panel.getWidth()/8;
				int h=  panel.getHeight()/2;
				g2.setStroke(new BasicStroke(4));
				var txt = "결제완료,배송준비,배송중,배송완료".split(",");
				for (int i = 3; i >= 0; i--) {
					var image = new ImageIcon(shipIcon.get(idx[i]).getImage().getScaledInstance(w, h, 1)).getImage();
					g2.setColor(now>=i?blue:Color.GRAY);
					g2.drawLine(w+(w*2)*i, h+30, w+(w*2)*(Math.max(0, i-1)), h+30);
					g2.fillOval(w+(w*2)*i-4, h+30-4, 8, 8);
					
					if(i==now&&!act)continue;
					
					g2.drawImage(image, w/2+(w*2)*i, 0,null);
					g2.drawString(txt[i], w+(w*2)*i-20, h+50);
				}
				
			}
		};
		panel.setBounds(414, 193, 438, 123);
		panel_1.add(panel);
		
		getIcons();
		getmodel();
		load();
		if(model.getRowCount()!=0) {
			table.changeSelection(0, 0, false, false);
			loadData();
		}
		new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				act = !act;
				panel.repaint();
			}
		}).start();
	}
	
	private void getIcons() {
		for (int i = 0; i < 4; i++) {
			for (int j = (i==0?2:1); j <= 2; j++) {
				shipIcon.add(getIcon("delivery/"+i+j+".jpg"));
			}
		}
	}

	int now;
	boolean act;
	List<ImageIcon> shipIcon = new ArrayList<ImageIcon>();
	private void loadData() {
		var d = data.get(table.getSelectedRow());
		label_1.setIcon(icons.get(table.getSelectedRow()));
		label_2.setText(d[0].toString());
		label_3.setText(String.format("%,d원 %d개", (int)d[1],(int)d[2]));
		label_4.setText(String.format("%,d원", (int)d[1]*(int)d[2]));
		now = ship.get(table.getSelectedRow());
	}

	private void getmodel() {
		model = new DefaultTableModel("상품,수량,단가,합계,주문일".split(","),0);
		table.setModel(model);
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(0);
		table.setDefaultRenderer(Object.class, render);
	}

	String where = "";
	List<Object[]> data = new ArrayList<Object[]>();
	List<ImageIcon> icons = new ArrayList<ImageIcon>();
	List<Integer> ship =new ArrayList<Integer>();
	public JPanel panel_1;
	private void load() {
		model.setRowCount(0);
		data.clear();
		icons.clear();
		ship.clear();
		try (var rs = res("select pno,ono,pname,o.quantity, price, o.date, img, delivery from `order` o join product p using(pno) where uno = "+BF.uno+" "+where)) {
			while(rs.next()) {
				model.addRow(new Object[] {rs.getString(3),rs.getInt(4)+"개",String.format("%,d",rs.getInt(5)),String.format("%,d", rs.getInt(4)*rs.getInt(5)),rs.getString(6)});
				data.add(new Object[] { rs.getString(3),rs.getInt(5),rs.getInt(4)});
				icons.add(getIcon(rs.getBytes("img"),label_1.getWidth(),label_1.getHeight()));
				ship.add(rs.getInt("delivery"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(model.getRowCount()==0&&comboBox.getSelectedIndex()==0) {
			panel_1.removeAll();
			panel_1.setLayout(new BorderLayout());
			JLabel jl = new JLabel("배송정보가 없습니다.", 0);
			jl.setFont(new Font("맑은 고딕", 1, 24));
			jl.setBorder(new LineBorder(Color.black));
			panel.add(jl);
		
		}
	}

	private class TableMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			loadData();
			repaint();
		}
	}
	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(comboBox.getSelectedIndex()==0)
				where = "";
			else
				where = "and delivery ="+(comboBox.getSelectedIndex()-1);
			load();
		}
	}
}
