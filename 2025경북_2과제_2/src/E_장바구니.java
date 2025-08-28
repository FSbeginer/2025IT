import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class E_장바구니 extends BP {
	public JLabel label;
	public JLabel label_1;
	public JScrollPane scrollPane;
	public JPanel panel;
	public JCheckBox checkBox;

	/**
	 * Create the panel.
	 */
	public E_장바구니() {
		
		label = new JLabel("\uC7A5\uBC14\uAD6C\uB2C8");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setBounds(12, 10, 171, 44);
		add(label);
		
		label_1 = new JLabel("\uCD1D\uAE08\uC561 : 0\uC6D0");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_1.setBounds(428, 10, 436, 44);
		add(label_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 64, 852, 350);
		add(scrollPane);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		checkBox = new JCheckBox("\uC804\uCCB4 \uC120\uD0DD");
		checkBox.addActionListener(new CheckBoxActionListener());
		checkBox.setBackground(Color.WHITE);
		checkBox.setBounds(8, 430, 115, 23);
		add(checkBox);
		
		button = new JButton("\uAD6C\uB9E4\uD558\uAE30");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(767, 430, 97, 23);
		add(button);
		
		button_1 = new JButton("\uC0AD\uC81C");
		button_1.addActionListener(new Button_1ActionListener());
		button_1.setBounds(699, 430, 66, 23);
		add(button_1);
		
		load();
	}

	List<E_Panel> pps = new ArrayList<E_Panel>();
	List<int[]> ctnos = new ArrayList<int[]>();
	int tot;
	public JButton button;
	public JButton button_1;
	private void load() {
		tot=0;
		panel.removeAll();
		pps.clear();
		try (var rs = res("select pno,ctno, img, pname, price,cart.quantity,price*cart.quantity tot, p.quantity `left` from cart join product p using(pno) where uno = "+BF.uno)) {
			int w = (scrollPane.getWidth()-25)/3;
			int h = scrollPane.getHeight()/3;
			int i = 0;
			while(rs.next()) {
				E_Panel pp =new E_Panel(getIcon(rs.getBytes(3),100,100), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7));
				pp.setSize(w, h);
				pp.setLocation(1+w*(i%3), 1+h*(i/3));
				
				int sum = rs.getInt("tot");
				int ctno = rs.getInt(2);
				int left = rs.getInt("left");
				int pno = rs.getInt(1);
				int quan = rs.getInt("quantity");
				int[] data = {pno, ctno, left, quan};
				pp.checkBox.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						boolean every = true;
						for (E_Panel e_Panel : pps) {
							if(!e_Panel.checkBox.isSelected()) {
								every = false;
								break;
							}
						}
						checkBox.setSelected(every);
						if(pp.checkBox.isSelected()) {
							tot += sum;
							ctnos.add(data);
						}
						else {
							tot-=sum;
							ctnos.remove(data);
						}
						label_1.setText(String.format("총금액: %,d원", tot));
					}
				});
				pps.add(pp);
				panel.add(pp);
				i++;
			}
			panel.setPreferredSize(new Dimension(0, 1+h*((i+2)/3)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		label_1.setText(String.format("총금액: %,d원", tot));
		panel.revalidate();
		panel.repaint();
		
		if(panel.getComponents().length==0) {
			panel.setLayout(new BorderLayout());
			JLabel jl = new JLabel("장바구니가 비어있습니다.", 0);
			jl.setFont(new Font("맑은 고딕", 1, 24));
			panel.add(jl);
			button.setVisible(false);
			button_1.setVisible(false);
			checkBox.setVisible(false);
		}
	}
	private class CheckBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(checkBox.isSelected()) {
				for (E_Panel e_Panel : pps) {
					e_Panel.checkBox.setSelected(true);
				}
			}
			else {
				for (E_Panel e_Panel : pps) {
					e_Panel.checkBox.setSelected(false);
				}
			}
		}
	}
	private class Button_1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(ctnos.size()==00) {
				msgErr("삭제할 상품을 선택하세요.");
				return;
			}
			for (var integer : ctnos) {
				try {
					execute("delete from cart where ctno = "+integer[1]);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			load();
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(ctnos.size()==0) {
				msgErr("구매할 상품을 선택하세요.");
				return;
			}
			for (int[] is : ctnos) {
				if(is[2]<is[3]) {
					msgErr("재고가 부족합니다.");
					break;
				}else {
					try {
						var pre = pre("insert into `order` values(0,?,?,?,?,0,0)");
						preSet(pre, BF.uno,is[0],is[3],LocalDate.now());
						pre.execute();
						execute("update product set quantity = quantity - "+is[3]+" where pno = "+is[0]);
						execute("delete from cart where ctno = "+is[1]);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
			msgInfo("구매가 완료되었습니다.");
			load();
		}

	}
}
