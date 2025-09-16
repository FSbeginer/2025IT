import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
public class J_분석 extends BP {
	public JComboBox comboBox;
	public JPanel panel;

	/**
	 * Create the panel.
	 */
	public J_분석() {
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ComboBoxActionListener());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uC804\uCCB4", "\uCE74\uD14C\uACE0\uB9AC"}));
		comboBox.setBounds(12, 10, 114, 32);
		add(comboBox);
		
		panel = new JPanel();
		panel.setBounds(12, 52, 944, 395);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(new 버블차트());
	}

	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			if(comboBox.getSelectedIndex()==0) {
				panel.add(new 버블차트());
			}
			else {
				panel.add(new 막대차트());
			}
			panel.revalidate();
			panel.repaint();
		}
	}
}
