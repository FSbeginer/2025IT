package 짜집기;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class K_일정선택 extends BF {
	public JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					K_일정선택 frame = new K_일정선택();
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
	LocalDate start,end;
	public K_일정선택() {
		setTitle("\uC77C\uC815\uC120\uD0DD");
		setBounds(100, 100, 501, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(12, 10, 463, 421);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		var d = new 달력(null);
		for (int i = 0; i < 42; i++) {
			var lbl = d.lbls[i];
			lbl.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(start==null) {
						start = lbl.date;
						lbl.chek= true;
					}
					else {
						end = lbl.date;
						if(start.isAfter(end)) {
							var temp  =start;
							start = end;
							end = temp;
						}
						for (var imsi : d.lbls) {
							if(imsi.date.isAfter(end)||imsi.date.isBefore(start)) imsi.chek = false;
							else imsi.chek = true;
						}
					}
					repaint();
				}
			});
		}
		panel.add(d);
	}

}
