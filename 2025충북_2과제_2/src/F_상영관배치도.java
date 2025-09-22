
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class F_상영관배치도 extends BF {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					F_상영관배치도 frame = new F_상영관배치도(3);
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
	int srmno;
	public JPanel panel;
	int[] xdir = {1,0,-1,0};
	int[] ydir = {0,1,0,-1};
	boolean[][] visited = new boolean[9][9];
	int[][] map = new int[9][9];
	int[][] prev = new int[9][9];
 	JPanel[][] pps = new JPanel[9][9];
	
	public F_상영관배치도(int srmno) {
		setTitle("상영관 배치도");
		this.srmno = srmno;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 559);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(9, 9, 0, 0));
		
		
		
		getData();
		load();
		findRoute();
	}
	int arrive;
	private void findRoute() {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(72);
		visited[8][0] = true;
		for (int i = 0; i < map.length; i++) {
			Arrays.fill(prev[i], -1);
		}
		while(queue.size()>0) {
			int current = queue.poll();
			int row = current / 9;
			int col = current % 9;
			
			if(current==arrive) {
				System.out.println(arrive+" "+current +" "+srmno);
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				int nextR = row + ydir[i];
				int nextC = col + xdir[i];
				if(nextR<0||nextR>8||nextC<0||nextC>8||map[nextR][nextC]==1||visited[nextR][nextC]) continue;
				
				queue.add(nextR*9+nextC);
				prev[nextR][nextC] = current;
				visited[nextR][nextC] = true;
			}
		}
		
		List<Integer> route = new ArrayList<Integer>();
		route.add(arrive);
		int p = prev[arrive/9][arrive%9];
		while (p!=-1) {
			route.add(p);
			int row = p/9, col = p%9;
			p = prev[row][col];
		}
		Collections.reverse(route);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i < route.size(); i++) {
					var r = route.get(i);
					int row = r/9, col = r%9;
					pps[row][col].setBackground(Color.red);
					if(i==route.size()-1) {
						pps[row][col].setBackground(Color.green);
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for (int i = 1; i < route.size()-1; i++) {
					var r = route.get(i);
					int row = r/9, col = r%9;
					for (int j = 0; j < 6; j++) {
						if(j%2==0)
							pps[row][col].setBackground(Color.white);
						else
							pps[row][col].setBackground(Color.red);
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				msgInfo(srmno +"관에 도착했습니다.");
				showPage(new G_좌석선택(), "G_좌석선택");
			}
		}).start();		
	}
	private void load() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				pps[i][j] = new JPanel();
				pps[i][j].setBorder(new LineBorder(Color.black));
				pps[i][j].setBackground(map[i][j]==0? Color.white : map[i][j]==1?Color.gray:Color.blue);
				panel.add(pps[i][j]);
			}
		}
		pps[8][0].setBackground(Color.green);
	}
	private void getData() {
		try (var rs = res("SELECT * FROM moviedb.srm;")) {
			while(rs.next()) {
				int row = (rs.getInt("srm_no")-1)/9;
				int col = (rs.getInt("srm_no")-1)%9;
				map[row][col] = rs.getInt(2);
				if(rs.getInt("srm_srmno")==srmno) arrive = (rs.getInt("srm_no")-1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		
	}

}
