import javax.swing.*;

public class PackExample {
    private static JFrame frame;

	public static void main(String[] args) {
        	frame = new JFrame("pack() 예시");
            JPanel panel = new JPanel();
            panel.add(new JButton("버튼 A"));
            panel.add(new JButton("버튼 B"));
            
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);
            
            frame.pack();               // 컴포넌트에 딱 맞춰 창 크기 조정
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
    }
}
