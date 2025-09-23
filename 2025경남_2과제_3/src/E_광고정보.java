import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class E_±§∞Ì¡§∫∏ extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					E_±§∞Ì¡§∫∏ frame = new E_±§∞Ì¡§∫∏(1);
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
	int ano;
	public JLabel label;
	public JPanel panel;
	public JLabel label_1;
	public E_±§∞Ì¡§∫∏(int ano) {
		setTitle("\uAD11\uACE0 \uC815\uBCF4");
		this.ano = ano;
		setBounds(100, 100, 450, 412);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(BF.getIcon("icon/icon.png").getImage());
		
		setModal(true);
		getContentPane().setLayout(null);
		
		label = new JLabel("New label");
		label.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 15));
		label.setBounds(12, 10, 410, 58);
		getContentPane().add(label);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(12, 95, 410, 172);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		label_1 = new JLabel("New label");
		label_1.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		label_1.setBounds(12, 289, 410, 58);
		getContentPane().add(label_1);
		
		try (var rs = BF.res("select * from advertise where ano ="+ano)) {
			rs.next();
			label.setText("<html>"+rs.getString("aname"));
			label_1.setText("<html>"+rs.getString(3));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < jls.length; i++) {
			jls[i] = new JLabel(BF.getIcon("advertise/"+ano+"-"+(i+1)+".jpg",panel.getWidth(),panel.getHeight()));
			jls[i].setSize(panel.getSize());
			jls[i].setLocation(panel.getWidth()*i, 0);
			panel.add(jls[i]);
		}
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					for (int i = 0; i < 5000; i++) {
						for (JLabel jl : jls) {
							jl.setLocation(jl.getX()-1, jl.getY());
							if(jl.getX() == -jl.getWidth()) {
								jl.setLocation(jl.getWidth(), 0);
							}
						}
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
	}
	JLabel[] jls = new JLabel[2];
}
