package 풀이본;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Graphics;

public class 후기작성폼 extends BF {
	public JLabel label;
	public JLabel label_1;
	public JTextArea textArea;
	public JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					후기작성폼 frame = new 후기작성폼();
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
	int ino;
	public JLabel label_2;
	public JTextArea textArea_1;
	public RoundButton button_1;
	public 후기작성폼(int ino){
		this();
		this.ino = ino;
		load();
	}
	
	private void load() {
		setBounds(100, 100, 596, 617);
		button.setVisible(false);
		button_1.setVisible(true);
		label_2.setVisible(true);
		textArea_1.setVisible(true);
		textArea.setEditable(false);
		try {
			var rs=res("select * from inquiry where ino ="+ino);
			rs.next();
			textArea.setText(rs.getString("icontent"));
			label_1.setIcon(getIcon("Error/"+rs.getInt("iimg")+".png"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public 후기작성폼() {
		setTitle("후기작성폼");
		setBounds(100, 100, 596, 325);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("문의");
		label.setBounds(12, 10, 57, 15);
		getContentPane().add(label);
		
		label_1 = new JLabel("이미지를 넣는 곳");
		label_1.setFont(new Font("굴림", Font.BOLD, 12));
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(12, 35, 245, 216);
		getContentPane().add(label_1);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setBounds(269, 35, 245, 216);
		getContentPane().add(textArea);
		
		button = new RoundButton("작성완료");
		button.addActionListener(new ButtonActionListener());
		button.setBackground(Color.blue);
		button.setForeground(Color.white);
		button.setBounds(435, 252, 133, 32);
		getContentPane().add(button);
		
		new DropTarget(label_1, new DropTargetAdapter() {
			
			@Override
			public void drop(DropTargetDropEvent dtde) {
				dtde.acceptDrop(DnDConstants.ACTION_COPY);
				var tf = dtde.getTransferable();
				try {
					File f= ((List<File>)tf.getTransferData(DataFlavor.javaFileListFlavor)).get(0);
					if(f.getAbsolutePath().contains("datafiles/Error/")) {
						Image img = ImageIO.read(f).getScaledInstance(label_1.getWidth(), label_1.getHeight(), 1);
						imgLoad(img);
					}
					else {
						msgErr("Error 이미지를 넣어주세요.");
					}
				} catch (UnsupportedFlavorException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});
		
		label_2 = new JLabel("답변");
		label_2.setVisible(false);
		label_2.setBounds(12, 282, 57, 15);
		getContentPane().add(label_2);
		
		JLabel jl = new JLabel("현재 답변이 없습니다.");
		jl.setEnabled(false);
		jl.setVerticalAlignment(SwingConstants.TOP);
		textArea_1 = new JTextArea() {
			{
				setLayout(new BorderLayout());
				add(jl);
			}
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				jl.setVisible(getText().isBlank());
			}
		};
		textArea_1.setForeground(new Color(255, 0, 0));
		textArea_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea_1.setWrapStyleWord(true);
		textArea_1.setLineWrap(true);
		textArea_1.setBounds(12, 307, 556, 228);
		textArea_1.setVisible(false);
		getContentPane().add(textArea_1);
		
		button_1 = new RoundButton("작성완료");
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					var pre = pre("update inquiry set answer = ? where ino = ?");
					preSet(pre, textArea_1.getText(), ino);
					dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
						
			}
		});
		button_1.setText("확인");
		button_1.setVisible(false);;
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(Color.BLUE);
		button_1.setBounds(435, 545, 133, 23);
		getContentPane().add(button_1);

	}
	private void imgLoad(Image img) {
		BufferedImage src = new BufferedImage(img.getWidth(null), img.getHeight(null), 2);
		var g = src.createGraphics();
		g.drawImage(img, 0, 0, null);
		
		BufferedImage bi = new BufferedImage(label_1.getWidth(), label_1.getHeight(), 2);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int v = 0; v < label_1.getWidth()*2; v++) {
					for (int i = 0; i < label_1.getWidth(); i++) {
						for (int j = 0; j < label_1.getHeight(); j++) {
							if(i+j==v) {
								bi.setRGB(i, j, src.getRGB(i, j));
							}
						}
					}
					label_1.setIcon(new ImageIcon(bi));
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			var txt = textArea.getText();
			if(txt.isBlank()) {
				msgErr("빈칸이 있습니다.");
				return;
			}
			try {
				var pre = pre("insert into inquiry values(0, ?, ?, ?, ?, ?);");
				preSet(pre, txt, "", LocalDate.now(), 1, uno);
				pre.execute();
				dispose();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			JFileChooser jfc = new JFileChooser();
			jfc.setMultiSelectionEnabled(false);
			jfc.setAcceptAllFileFilterUsed(false);
			jfc.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));
			if(jfc.showOpenDialog(null)==jfc.APPROVE_OPTION) {
				try {
					imgLoad(ImageIO.read(jfc.getSelectedFile()).getScaledInstance(label_1.getWidth(), label_1.getHeight(), 1));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
		}
	}
}
