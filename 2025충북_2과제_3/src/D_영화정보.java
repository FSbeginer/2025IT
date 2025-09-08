import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JScrollPane;

public class D_영화정보 extends BF {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					D_영화정보 frame = new D_영화정보(1);
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
	int mno;
	public JPanel panel;
	public JPanel panel_1;
	public JScrollPane scrollPane;
	public D_영화정보(int mno) {
		setTitle("영화 정보");
		this.mno = mno;
		setBounds(100, 100, 921, 596);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 80));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 883, 457);
		panel_1.add(scrollPane);
		
		scrollPane.setViewportView(new D_패널(mno));
		up =new 유저패널();
		panel.add(up);
		
	}
	유저패널 up ;
	@Override
	public void showPage(JFrame jf, String name) {
		super.showPage(jf, name);
		jf.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if(uno==0) {
					up.button.setText("로그인");
				}
				else {
					up.button.setText("내 정보");
				}
			}
		});
	}

}
