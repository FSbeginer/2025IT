import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
	JPanel[][] pps = new JPanel[9][9];
	int[][] map = new int[9][9];
	int[][] prev = new int[9][9];
	int[] xdir = {0,1,0,-1};
	int[] ydir = {1,0,-1,0};
	boolean[][] visited= new boolean[9][9];
	
	public JPanel panel;
	public F_상영관배치도(int srmno) {
		setTitle("상영관 배치도");
		this.srmno = srmno;
		setBounds(100, 100, 600+16, 600+8+22);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(9, 9, 0, 0));

		
		getDAta();
		addpanel();
		bfs();
	}
	private void bfs() {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(72);
		visited[8][0] = true;
		
		for (int i = 0; i < 9; i++) {
			Arrays.fill(prev[i], -1);
		}
		
		while(!queue.isEmpty()) {
			var current = queue.poll();
			if(current==arrivedIdx) {
				break;
			}
			int row = current/9;
			int col = current%9;
			for (int i = 0; i < 4; i++) {
				int nextR = row +ydir[i];
				int nextC = col+xdir[i];
				if(nextC<0||nextC>8||nextR<0||nextR>8||visited[nextR][nextC]||map[nextR][nextC]==1) continue;
				queue.add(nextR*9+nextC);
				prev[nextR][nextC] = current;
				visited[nextR][nextC] = true;
			}
		}
		
		List<Integer> route = new ArrayList<Integer>();
		route.add(arrivedIdx);
		int p = prev[arrivedIdx/9][arrivedIdx%9];
		while(p!=-1) {
			route.add(0,p);
			p = prev[p/9][p%9];
		}
		
		new Thread(new Runnable() {
			public void run() {
				for (int i = 1; i < route.size(); i++) {
					int row = route.get(i)/9;
					int col = route.get(i)%9;
					pps[row][col].setBackground(Color.red);
					if(i==route.size()-1)
						pps[row][col].setBackground(Color.green);
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for (int i = 1; i <route.size()-1; i++) {
					int row = route.get(i)/9;
					int col = route.get(i)%9;
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
				msgInfo(srmno+"관에 도착했습니다.");
				showPage(new G_좌석선택(),"G_좌석선택");
			}
		}).start();
	}

	int arrivedIdx = 0;
	private void getDAta() {
		try (var rs = res("select * from srm;")) {
			int i = 0;
			while(rs.next()) {
				int row = i/9;
				int col = i%9;
				map[row][col] = rs.getInt("srm_type");
				if(rs.getInt("srm_srmno")==srmno) arrivedIdx = i; 
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void addpanel() {
		for (int i = 0; i < 81; i++) {
			int row = i/9;
			int col = i%9;
			pps[row][col] = new JPanel();
			if(map[row][col]==1) { 
				pps[row][col].setBackground(Color.gray);
			}
			else if(map[row][col]==0) pps[row][col].setBackground( Color.white);
			else
				pps[row][col].setBackground(Color.blue);
			pps[row][col].setBorder(new LineBorder(Color.black));
			panel.add(pps[row][col]);
		}
		pps[8][0].setBackground(Color.green);
	}

}
