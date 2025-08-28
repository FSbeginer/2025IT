import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		comboBox.setBounds(12, 10, 127, 35);
		add(comboBox);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(12, 56, 852, 393);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel.add(new J_버블차트());
		repaint();
	}

	private class ComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			if(comboBox.getSelectedIndex()==0)
				panel.add(new J_버블차트());
			else {
				panel.add(new J_막대차트());
			}
			panel.revalidate();
			panel.repaint();
		}
	}
}
