import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class InfoPanel extends JPanel {
	public JPanel panel;
	public JPanel panel_1;
	public JPanel panelLegend;
	public JPanel panelChart;
	public JScrollPane scrollPane;
	public JPanel panelComment;
	
	Color[] c = { Color.RED, Color.BLUE, Color.YELLOW,Color.GREEN};
	int[] ages = new int[4];
	public JLabel label;
	int mno;
	
	public InfoPanel(int mno) {
		this.mno = mno;
		setLayout(new GridLayout(0, 2, 0, 0));
		
		panel = new JPanel();
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		panelLegend = new JPanel();
		panelLegend.setPreferredSize(new Dimension(120, 10));
		panel.add(panelLegend, BorderLayout.EAST);
		panelLegend.setLayout(new GridLayout(8, 1, 0, 0));
		
		panelChart = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				int ang = 90;
				for (int i = 0; i < ages.length; i++) {
					int spin = Math.round((float)ages[i]/sum*360);
					g.setColor(c[i]);
					g.fillArc(0, 25, 250, 250, ang, spin);
					ang += spin;
				}
			}
		};
		panel.add(panelChart, BorderLayout.CENTER);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		panelComment = new JPanel();
		scrollPane.setViewportView(panelComment);
		panelComment.setLayout(null);
		
		label = new JLabel("New label");
		label.setVisible(false);
		label.setBounds(12, 10, 329, 65);
		panelComment.add(label);
		
		getData();
		label();
	}
	
	int sum;
	private void label() {
		var text = "성인 청소년 어린이 유아".split(" ");
		sum = Arrays.stream(ages).sum();
		for (int i = 0; i < 4; i++) {
			JLabel jl = new JLabel(getIcon(c[i]));
			jl.setHorizontalAlignment(SwingConstants.LEFT);
			jl.setText(String.format("%s %.0f%%", text[i], (double)ages[i]/sum*100));
			panelLegend.add(jl);
		}
	}

	private ImageIcon getIcon(Color color) {
		BufferedImage bi = new BufferedImage(40, 40, 2);
		Graphics2D g2 = (Graphics2D) bi.getGraphics();
		
		g2.setColor(color);
		g2.fillRect(5, 5, 30, 30);
		
		return new ImageIcon(bi);
	}

	private void getData() {
		try (var rs = BF.res("select * from review join movie using(m_no) join user using(u_no) where m_no = "+mno)) {
			int i = 0;
			while(rs.next()) {
				int age = BF.getAge(rs.getDate("u_birth"));
				int idx = age < 5 ? 3 : age < 13 ? 2 : age < 19 ? 1 : 0;
				ages[idx]++;
				CommentPanel cp = new CommentPanel(BF.getIcon("user/"+rs.getInt("u_no")+".jpg",40,40), rs.getString("u_name"), rs.getString("re_com"));
				cp.setSize(label.getSize());
				cp.setLocation(label.getX(), label.getY()+(cp.getHeight()+10)*i);
				panelComment.add(cp);
				i++;
			}
			panelComment.setPreferredSize(new Dimension(0, label.getY()+(label.getHeight()+10)*i));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
