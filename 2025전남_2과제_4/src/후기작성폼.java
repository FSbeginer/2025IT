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
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class 후기작성폼 extends BF {
	public JLabel label;
	public JTextArea textArea;
	public JButton button;
	public JLabel label_1;

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
	public 후기작성폼() {
		setTitle("후기작성폼");
		setBounds(100, 100, 600, 368);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("이미지를 넣는 곳");
		label.addMouseListener(new LabelMouseListener());
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(12, 36, 265, 237);
		getContentPane().add(label);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		textArea.setBounds(289, 36, 265, 237);
		getContentPane().add(textArea);
		
		button = new JButton("작성완료");
		button.addActionListener(new ButtonActionListener());
		button.setForeground(Color.WHITE);
		button.setBackground(Color.BLUE);
		button.setBounds(403, 288, 169, 31);
		getContentPane().add(button);
		
		label_1 = new JLabel("문의");
		label_1.setBounds(12, 10, 57, 15);
		getContentPane().add(label_1);
		
		new DropTarget(label, new DropTargetAdapter() {
			
			@Override
			public void drop(DropTargetDropEvent dtde) {
				dtde.acceptDrop(DnDConstants.ACTION_COPY);
				var tf = dtde.getTransferable();
				try {
					File f = ((List<File>)tf.getTransferData(DataFlavor.javaFileListFlavor)).get(0);
					eno = Integer.parseInt(f.getName().replaceAll(".png", ""));
					try {
						Image img = ImageIO.read(f);
						imgLOad(img);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} catch (UnsupportedFlavorException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	int eno ;
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(label.getIcon()==null || textArea.getText().isBlank()) {
				msgErr("빈칸이 있습니다.");
				return;
			}
			try (var pre = pre("insert into values(0,?,'',curdate(),?,?)")) {
				preSet(pre, textArea.getText(), eno, uno);
				pre.execute();
				msgInfo("질문이 등록되었습니다.");
				dispose();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
		}
	}
	private class LabelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			JFileChooser jfc= new JFileChooser();
			jfc.setMultiSelectionEnabled(false);
			jfc.setAcceptAllFileFilterUsed(true);
			jfc.setCurrentDirectory(new File("./datafiles/Error"));
			jfc.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));
			if(jfc.showOpenDialog(null)==0) {
				try {
					Image img = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(label.getWidth(), label.getHeight(), 1);
					imgLOad(img);
					eno = Integer.parseInt(jfc.getSelectedFile().getName().replaceAll(".png", ""));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

	}
	private void imgLOad(Image img) {
		BufferedImage scr = new BufferedImage(label.getWidth(), label.getHeight(), 2);
		var g = scr.createGraphics();
		g.drawImage(img, 0, 0,null);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				BufferedImage bi = new BufferedImage(label.getWidth(), label.getHeight(), 2);
				for (int i = 0; i < label.getWidth()*2; i++) {
					for (int j = 0; j < label.getWidth(); j++) {
						for (int j2 = 0; j2 < label.getHeight(); j2++) {
							if(j+j2==i) {
								bi.setRGB(j, j2, scr.getRGB(j, j2));
							}
						}
					}
					label.setIcon(new ImageIcon(bi));
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		
	}
}
