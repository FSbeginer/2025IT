import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class I_마이페이지 extends BF {
	public JToggleButton button;
	public JToggleButton button_1;
	public JToggleButton button_2;
	public JScrollPane scrollPane;
	public JTable table;
	private DefaultTableModel model1;
	private DefaultTableModel model2;
	private DefaultTableModel model3;
	private DefaultTableCellRenderer render;
	private DefaultTableCellRenderer render2;
	List<List<Integer>> bnos = new ArrayList<>();
	List<List<Integer>> jnos = new ArrayList<>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					I_마이페이지 frame = new I_마이페이지();
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
	public I_마이페이지() {
		setTitle("\uB9C8\uC774\uD398\uC774\uC9C0");
		setBounds(100, 100, 742, 551);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		button = new MyButton("\uB0B4\uAC00 \uC88B\uC544\uD55C \uC54C\uBC14",true);
		button.addActionListener(new ButtonActionListener());
		button.setBounds(12, 10, 225, 36);
		getContentPane().add(button);
		
		button_1 = new MyButton("\uB300\uAE30\uC911\uC778 \uC54C\uBC14",false);
		button_1.addActionListener(new Button_1ActionListener());
		button_1.setBounds(249, 10, 225, 36);
		getContentPane().add(button_1);
		
		button_2 = new MyButton("\uD569\uACA9\uD55C \uC54C\uBC14",false);
		button_2.addActionListener(new Button_2ActionListener());
		button_2.setBounds(487, 10, 225, 36);
		getContentPane().add(button_2);
		
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(button);
		bg.add(button_1);
		bg.add(button_2);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 69, 700, 412);
		getContentPane().add(scrollPane);
		
		table = new JTable() {
			@Override
			public Class<?> getColumnClass(int column) {
				if(column==2)
					return Icon.class;
				else
					return super.getColumnClass(column);
			}
		};
		table.addMouseListener(new TableMouseListener());
		scrollPane.setViewportView(table);
		
		getModels();
		getdata();
	}

	private void getdata() {
		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();
		List<Integer> list3 = new ArrayList<Integer>();
		List<Integer> list4 = new ArrayList<Integer>();
		List<Integer> list5 = new ArrayList<Integer>();
		List<Integer> list6 = new ArrayList<Integer>();
		try (var rs = res("select * from job join likes using(jno) join brand using(bno) join category using(cno) where uno = "+uno)) {
			while(rs.next()) {
				model1.addRow(new Object[] {model1.getRowCount()+1, "<html>"+rs.getString("cname"), getIcon("brand/"+rs.getInt("bno")+".png",100,100), rs.getString("bname"), "<html>"+rs.getString("jname"),rs.getString("ldate")});
				list1.add(rs.getInt("bno"));
				list4.add(rs.getInt("jno"));
			}
			bnos.add(list1);
			jnos.add(list4);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = res("select * from apply join job using(jno) where apok = 0 and uno = "+uno)) {
			while(rs.next()) {
				model2.addRow(new Object[] {model2.getRowCount()+1,rs.getInt("apno"), getIcon("brand/"+rs.getInt("bno")+".png",100,100), "<html>"+rs.getString("jname"), rs.getString("apdate"), rs.getInt("jgrade")==0?"무관":rs.getInt("jgrade")==1?"대학":"고등"});
				list2.add(rs.getInt("bno"));
				list5.add(rs.getInt("jno"));
			}
			bnos.add(list2);
			jnos.add(list5);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (var rs = res("select * from apply join job using(jno) where apok = 1 and uno = "+uno)) {
			while(rs.next()) {
				model3.addRow(new Object[] {model3.getRowCount()+1,rs.getInt("apno"), getIcon("brand/"+rs.getInt("bno")+".png",100,100), "<html>"+rs.getString("jname"), rs.getString("apdate"), String.format("%,d",rs.getInt("jmoney"))});
				list3.add(rs.getInt("bno"));
				list6.add(rs.getInt("jno"));
			}
			bnos.add(list3);
			jnos.add(list6);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void getModels() {
		model1 = new DefaultTableModel("번호,카테고리,브랜드,브랜드명,알바,좋아요 한 날짜".split(","), 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model2 = new DefaultTableModel("번호,지원 번호,브랜드,알바,지원 날짜,지원자격".split(","), 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model3 = new DefaultTableModel("번호,지원 번호,브랜드,알바,지원 날짜,급여".split(","), 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model1);
		table.getTableHeader().setBackground(orange);
		table.getTableHeader().setForeground(Color.white);
		table.setRowHeight(100);
		render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		render2 = new DefaultTableCellRenderer();
		render2.setVerticalAlignment(SwingConstants.TOP);
		rendering();
	}

	private class Button_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			table.setModel(model2);
			rendering();
		}
	}
	private class Button_2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			table.setModel(model3);
			rendering();
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			table.setModel(model1);
			rendering();
		}

	}
	private void rendering() {
		if(button.isSelected()) {
			for (int i = 0; i < table.getColumnCount(); i++) {
				if(i==1||i==4)
					table.getColumnModel().getColumn(i).setCellRenderer(render2);
				else if(i!=2)
					table.getColumnModel().getColumn(i).setCellRenderer(render);
			}
			table.getColumnModel().getColumn(0).setMaxWidth(50);
			table.getColumnModel().getColumn(1).setMaxWidth(60);
			table.getColumnModel().getColumn(2).setMinWidth(100);
			table.getColumnModel().getColumn(4).setMinWidth(150);
		}
		else {
			for (int i = 0; i < table.getColumnCount(); i++) {
				if(i==3)
					table.getColumnModel().getColumn(i).setCellRenderer(render2);
				else if(i!=2)
					table.getColumnModel().getColumn(i).setCellRenderer(render);
			}
			table.getColumnModel().getColumn(0).setMaxWidth(50);
			table.getColumnModel().getColumn(1).setMaxWidth(60);
			table.getColumnModel().getColumn(2).setMinWidth(100);
			table.getColumnModel().getColumn(3).setMinWidth(150);
		}
	}
	private class TableMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			int listIdx = button.isSelected()?0 : button_1.isSelected()? 1 : 2;
			int col = button.isSelected()? 4 : 3;
			
			if(table.columnAtPoint(e.getPoint())==2&&e.getClickCount()==2) {
				showPage(new G_브랜드정보(bnos.get(listIdx).get(table.getSelectedRow())),"G_브랜드정보");
			}
			else if(table.columnAtPoint(e.getPoint())==col&&e.getClickCount()==2) {
				showPage(new C_알바정보(jnos.get(listIdx).get(table.getSelectedRow())),"C_알바정보");
			}
			 
		}
	}
}
class MyButton extends JToggleButton{
	public MyButton(String txt,boolean selected) {
		super(txt, selected);
		setBorder(new LineBorder(BF.orange));
		setContentAreaFilled(false);
		setOpaque(true);
		changeColor(selected);
		addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				changeColor(isSelected());
			}
		});
	}

	private void changeColor(boolean selected) {
		if(selected) {
			setBackground(BF.orange);
			setForeground(Color.white);
		}
		else {
			setBackground(Color.white);
			setForeground(BF.orange);
		}
	}
}
