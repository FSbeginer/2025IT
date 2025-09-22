import java.awt.EventQueue;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

	BufferedImage dropImage;
	
	/**
	 * Create the frame.
	 */
	public 후기작성폼() {
		setTitle("후기작성폼");
		setBounds(100, 100, 590, 372);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		label = new JLabel("문의");
		label.setBounds(12, 10, 57, 15);
		getContentPane().add(label);
		
		label_1 = new JLabel("이미지를 넣는 곳");
		label_1.addMouseListener(new Label_1MouseListener());
		label_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(22, 35, 262, 248);
		getContentPane().add(label_1);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setBounds(296, 35, 250, 248);
		getContentPane().add(textArea);
		
		button = new JButton("작성완료");
		button.setForeground(Color.WHITE);
		button.setBackground(Color.BLUE);
		button.setBounds(407, 293, 155, 30);
		getContentPane().add(button);

		new DropTarget(label_1, new DropTargetAdapter() {
			@Override
			public void drop(DropTargetDropEvent dtde) {
				dtde.acceptDrop(DnDConstants.ACTION_COPY);
				var tf = dtde.getTransferable();
				try {
					File f = ((List<File>)tf.getTransferData(DataFlavor.javaFileListFlavor)).get(0);
					if(f.getAbsolutePath().toLowerCase().contains(".png")) {
						dropImage = ImageIO.read(f);
						imageAnimation();
					}
				} catch (UnsupportedFlavorException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});
	}
	private void imageAnimation() {
		BufferedImage src = new BufferedImage(label_1.getWidth(), label_1.getHeight(), 2);
		var g = src.createGraphics();
		g.drawImage(dropImage, 0, 0, label_1.getWidth(), label_1.getHeight(), 0, 0, dropImage.getWidth(), dropImage.getHeight(), null);
		
		BufferedImage bi = new BufferedImage(label_1.getWidth(), label_1.getHeight(), 2);
		
		new	Thread(new Runnable() {
			
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
					}
				}
			}
		}).start();
	}
	private class Label_1MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			JFileChooser jfc = new JFileChooser();
			jfc.setMultiSelectionEnabled(false);
			jfc.setAcceptAllFileFilterUsed(false);
			jfc.setFileFilter(new FileNameExtensionFilter("PNG FIlES", "png"));
			if(jfc.showOpenDialog(null)==jfc.APPROVE_OPTION) {
				try {
					dropImage = ImageIO.read(jfc.getSelectedFile());
					imageAnimation();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
