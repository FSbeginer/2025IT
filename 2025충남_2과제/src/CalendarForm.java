import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class CalendarForm extends BF {


	LocalDate start,end;
	public CalendarForm(LocalDate start, LocalDate end) {
		this.start = start;
		this.end = end;
		setTitle("\uC77C\uC815\uC120\uD0DD");
		setBounds(100, 100, 450, 463);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		var c = new Calendar(null);
		add(c);
		JLabel jl = new JLabel(getIcon("아이콘/체크.png",30,30));
		jl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CalendarForm.this.start = c.start;
				CalendarForm.this.end = c.end;
				dispose();
			}
		});
		c.label_3.setLayout(new BorderLayout());
		c.label_3.add(jl, "East");
	}

}
