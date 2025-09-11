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
	public K_일정선택() {
		setTitle("\uC77C\uC815\uC120\uD0DD");
		setBounds(100, 100, 418, 454);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(12, 10, 371, 398);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		var d = new 달력(null);
		for (var jl : d.jls) {
			jl.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(d.d1==null) {
						d.d1 = jl.date;
						d.lendering();
					}
					else {
						d.d2 = jl.date;
						if(d.d2.isBefore(d.d1)) {
							var temp = d.d1;
							d.d1 = d.d2;
							d.d2 = temp;
						}
						d.lendering();
					}
				}
			});
		}
		panel.add(d);
	}

}
