import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Point;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class L_위치선택 extends JDialog {

	private JPanel contentPane;
	public JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					L_위치선택 frame = new L_위치선택(1, 1);
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
	int sno, pno;

	public L_위치선택(int sno, int pno) {
		addKeyListener(new ThisKeyListener());
		this.sno = sno;
		this.pno = pno;
		setTitle("\uC704\uCE58\uC120\uD0DD");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 701, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		label = new JLabel(BF.getIcon("내부/" + sno + ".png", 680, 280));
		label.addMouseListener(new LabelMouseListener());
		label.setBounds(0, 0, 680, 280);
		contentPane.add(label);
		setIconImage(BF.getLogo(100, 100).getImage());
		setModal(true);

		load();
	}

	JLabel me = new JLabel();
	private void load() {
		try (var rs = BF.res("select * from location join program using(pno) where sno = " + sno + " group by pno;")) {
			while (rs.next()) {
				var p = new Point(rs.getInt("x"), rs.getInt("y"));
				if (rs.getInt("pno") != pno) {
					JLabel jl = new JLabel(BF.getIcon());
					jl.setSize(100, 25);
					jl.setText(rs.getString("name"));
					jl.setForeground(Color.white);
					jl.setLocation(p.x, p.y - 10);
					label.add(jl);
				} else {
					me = new JLabel(BF.getIcon("아이콘/위치.png",50,50));
					me.setLocation(p.x-25, p.y-50);
					me.setSize(50, 50);
					label.add(me);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		me.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("선택 : "+me.getLocation());
			}
		});
	}

	private class ThisKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case 0x25: {
				label.setLocation(label.getX() - 1, label.getY());
				break;
			}
			case 0x26: {
				label.setLocation(label.getX(), label.getY() - 1);
				break;
			}
			case 0x27: {
				label.setLocation(label.getX() + 1, label.getY());
				break;
			}
			case 0x28: {
				label.setLocation(label.getX(), label.getY() + 1);
				break;
			}
			}
		}
	}
	private class LabelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			me.setLocation(e.getX()-25, e.getY()-50);
		}
	}
}
// 6 2 32 9 18