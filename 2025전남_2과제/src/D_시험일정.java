import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class D_시험일정 extends BF {
	public JComboBox comboBox;
	public JTextField textField;
	public JButton button;
	public JScrollPane scrollPane;
	public JPanel panel;
	private String where="";
	private String like="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					D_시험일정 frame = new D_시험일정();
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
	public D_시험일정() {
		setTitle("시험일정");
		setBounds(100, 100, 726, 440);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"전체"}));
		comboBox.setBounds(35, 21, 147, 32);
		getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(206, 21, 318, 32);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		button = new JButton("조회하기");
		button.addActionListener(new ButtonActionListener());
		button.setForeground(new Color(255, 255, 255));
		button.setBackground(new Color(0, 0, 255));
		button.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		button.setBounds(536, 19, 119, 32);
		getContentPane().add(button);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 63, 675, 320);
		getContentPane().add(scrollPane);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		addCategory();
		load();
	}

	private void addCategory() {
		try (var rs = res("select * from category")) {
			while(rs.next()) {
				comboBox.addItem(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void load() {
		panel.removeAll();
		try (var rs = res("select cno, cname, ratring, exam_date, schedule.start_date, cgname, address, contents, scno from schedule left join certi using(cno) left join category using(cgno) left join course_registration using(cno) left join user using(uno) left join lecture using(cno) where true "+where +" "+like+" group by scno, ratring order by scno;")) {
			int w= 0 , h=0,i=0;
			while(rs.next()) {
				D_SchedulePanel pp= new D_SchedulePanel(getIcon("certification/"+rs.getInt(1)+".png",160,160), rs.getString(2), rs.getInt(3),rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(), rs.getString(6), rs.getString(7), rs.getString(8));
				w = pp.getWidth();
				h = pp.getHeight();
				pp.setLocation(0, (h+3)*i);
				
				int scno = rs.getInt("scno");
				
				var sub = res("select * from course_registration where uno = "+uno+" and cno = "+rs.getInt("cno"));
				pp.button.setEnabled(sub.next());
				
				pp.button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						showPage(new E_시험스케줄(scno), "E_시험스케줄");
					}
				});
				panel.add(pp);
				i++;
			}
			panel.setPreferredSize(new Dimension(0, (h+3)*i));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		panel.revalidate();
		panel.repaint();
	}
	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(comboBox.getSelectedIndex()==0) {
				where = ""; 
			}
			else {
				where = "and cgno = "+comboBox.getSelectedIndex();
			}
			load();
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			like = "and cname like '%"+textField.getText()+"%'";
			try (var rs = res("select cno, cname, ratring, exam_date, schedule.start_date, cgname, address, contents from schedule left join certi using(cno) left join category using(cgno) left join course_registration using(cno) left join user using(uno) left join lecture using(cno) where true "+where +" "+like+" group by scno, ratring;")) {
				if(rs.next()) {
					load();
				}
				else {
					msgErr("해당 자격증이 존재하지 않습니다.");
				}
			}catch (Exception e2) {
			}
		}
	}
}
