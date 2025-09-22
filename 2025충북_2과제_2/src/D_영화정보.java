import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import java.awt.GridLayout;

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
	public JScrollPane scrollPane;
	public JPanel panel_1;
	public D_영화정보(int mno) {
		setTitle("영화 정보");
		this.mno = mno;
		setBounds(100, 100, 852, 557);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 836, 73);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(new 유저패널());
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 83, 814, 425);
		getContentPane().add(scrollPane);
		
		panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		panel_1.add(new D_정보패널(mno));
		panel_1.setPreferredSize(new Dimension(0, 1000));
	}

}
