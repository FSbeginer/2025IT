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

import java.awt.Color;
import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class 후기작성폼 extends BF {
	public JLabel label;
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
	public 후기작성폼() {
		setTitle("\uD6C4\uAE30\uC791\uC131\uD3FC");
		setBounds(100, 100, 622, 331);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		label = new JLabel("\uC774\uBBF8\uC9C0\uB97C \uB123\uB294 \uACF3");
		label.addMouseListener(new LabelMouseListener());
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(12, 10, 264, 221);
		getContentPane().add(label);

		textArea = new JTextArea();
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBounds(311, 10, 257, 221);
		getContentPane().add(textArea);

		button = new JButton("\uC791\uC131\uC644\uB8CC");
		button.addActionListener(new ButtonActionListener());
		button.setForeground(Color.WHITE);
		button.setBackground(Color.BLUE);
		button.setBounds(398, 241, 178, 32);
		getContentPane().add(button);

		new DropTarget(label, new DropTargetAdapter() {
			
			@Override
			public void drop(DropTargetDropEvent dtde) {
				dtde.acceptDrop(DnDConstants.ACTION_COPY);
				var tf = dtde.getTransferable();
				try {
					File f = ((List<File>)tf.getTransferData(DataFlavor.javaFileListFlavor)).get(0);
					var img = ImageIO.read(f).getScaledInstance(label.getWidth(), label.getHeight(), 1);
					eno = Integer.parseInt(f.getName().replaceAll(".png", ""));
					ani(img);
				} catch (UnsupportedFlavorException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	int eno;

	private class LabelMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			JFileChooser jfc = new JFileChooser();
			jfc.setAcceptAllFileFilterUsed(false);
			jfc.setMultiSelectionEnabled(false);
			jfc.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));
			jfc.setCurrentDirectory(new File("./datafiles/Error"));
			if (jfc.showOpenDialog(null) == 0) {
				try {
					var img = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(label.getWidth(), label.getHeight(),
							1);
					int eno = Integer.parseInt(jfc.getSelectedFile().getName().replaceAll(".png", ""));
					ani(img);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

	}

	private void ani(Image img) {
		BufferedImage src = new BufferedImage(label.getWidth(), label.getHeight(), 2);
		var g = src.createGraphics();
		g.drawImage(img, 0, 0, null);

		BufferedImage bi = new BufferedImage(label.getWidth(), label.getHeight(), 2);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int v = 0; v < label.getWidth() * 2; v++) {
					for (int i = 0; i < label.getWidth(); i++) {
						for (int j = 0; j < label.getHeight(); j++) {
							if(i+j==v) {
								bi.setRGB(i, j, src.getRGB(i, j));
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
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(eno == 0 || textArea.getText().isBlank()) {
				msgErr("빈칸이 있습니다.");
				return;
			}
			try {
				var pre = pre("insert into inquiry values(0,?,'',curdate(),?,?)");
				preSet(pre, textArea.getText(),eno,uno);
				pre.execute();
				msgInfo("등록되었습니다.");
				dispose();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
