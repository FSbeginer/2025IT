package 슬라이딩;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ImageSlider extends JFrame {
    private JLabel label;
    private ImageIcon[] images;
    private int index = 0;
    private Timer timer;
    private int startX;

    public ImageSlider() {
        setTitle("Image Slider");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 슬라이드에 보여줄 이미지들
        images = new ImageIcon[]{
            new ImageIcon("./datafiles/내부/1.png"),
            new ImageIcon("./datafiles/내부/2.png"),
            new ImageIcon("./datafiles/내부/3.png")
        };

        label = new JLabel(images[index]);
        label.setHorizontalAlignment(JLabel.CENTER);
        add(label, BorderLayout.CENTER);

        // 마우스로 슬라이드(드래그) 감지
        label.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                startX = e.getX();
            }
            public void mouseReleased(MouseEvent e) {
                int endX = e.getX();
                if (startX - endX > 50) { // 왼쪽으로 드래그
                    showNext();
                } else if (endX - startX > 50) { // 오른쪽으로 드래그
                    showPrevious();
                }
            }
        });

        // 1초마다 자동으로 다음 이미지 보여주기
        timer = new Timer(1000, e -> showNext());
        timer.start();

        setVisible(true);
    }

    private void showNext() {
        index = (index + 1) % images.length;
        label.setIcon(images[index]);
    }

    private void showPrevious() {
        index = (index - 1 + images.length) % images.length;
        label.setIcon(images[index]);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ImageSlider::new);
    }
}
