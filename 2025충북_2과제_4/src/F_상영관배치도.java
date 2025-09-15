import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
	public F_상영관배치도(int srmno) {
		setTitle("상영관 배치도");
		this.srmno = srmno;
		setBounds(100, 100, 646, 610);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(12, 10, 606, 551);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(9, 9, 0, 0));

		addpanel();
		load();
	}
	int[] xdir = {0,1,0,-1}; 
	int[] ydir = {1,0,-1,0}; 
	private void load() {
		for (int i = 0; i < 9; i++) {
			Arrays.fill(prev[i],-1);
		}
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(72);
		visited[8][0] = true;
		while(!queue.isEmpty()) {
			int current = queue.poll();
			int row = current/9;
			int col = current%9;
			if(current==arrived) {
				break;
			}
			for (int i = 0; i < 4; i++) {
				int nc = col + xdir[i];
				int nr = row + ydir[i];
				if(nc<0||nc>8||nr<0||nr>8||visited[nr][nc]||map[nr][nc]==1)continue;
				
				queue.add(nr*9+nc);
				prev[nr][nc]=current;
				visited[nr][nc]= true;
			}
		}
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(prev[i][j]);
			}
			System.out.println();
		}
		
		pps[8][0].setBackground(Color.green);
		List<Integer> route = new ArrayList<Integer>();
		int p = arrived;
		while(p!=-1) {
			route.add(0,p);
			int row = p/9;
			int col = p%9;
			p = prev[row][col];
		}
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i < route.size(); i++) {
					int current = route.get(i);
					int row = current/9;
					int col = current%9;
					pps[row][col].setBackground(i==route.size()-1? Color.green : Color.red);
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for (int i = 1; i < route.size()-1; i++) {
					int current = route.get(i);
					int row = current/9;
					int col = current%9;
					for (int j = 0; j < 5; j++) {
						if(j%2==0)
							pps[row][col].setBackground(Color.red);
						else
							pps[row][col].setBackground(Color.white);
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				msgInfo(srmno+"관에 도착했습니다.");
				showPage(new G_좌석선택(), "G_좌석선택");
			}
		}).start();
	}

	JPanel[][] pps = new JPanel[9][9];
	int[][] map= new int[9][9];
	int[][] prev= new int[9][9];
	boolean[][] visited= new boolean[9][9];
	int arrived;
	
	private void addpanel() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				pps[i][j] = new JPanel();
				pps[i][j].setBorder(new LineBorder(Color.black));
				panel.add(pps[i][j]);
			}
		}
		
		try (var rs = res("select * from srm;")) {
			while(rs.next()) {
				int row = (rs.getInt(1)-1)/9;
				int col = (rs.getInt(1)-1)%9;
				map[row][col] = rs.getInt(2);
				if(rs.getInt(3)==srmno) arrived = row*9+col;
				pps[row][col].setBackground(rs.getInt(2) == 0? Color.white : rs.getInt(2)==1?Color.gray : Color.blue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
