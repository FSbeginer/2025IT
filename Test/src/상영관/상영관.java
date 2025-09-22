package 상영관;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class 상영관 extends JFrame {

	JPanel pp[] = new JPanel[81];
	int sno[] = new int[81];
	int srm[] = { 2, 2, 0, 0, 0, 0, 2, 2, 3, 3, 2, 3, 3 };// 0~3사이 아무거나 예시
	Thread th = new Thread();

	Timer t;

	public 상영관() {
		fs("상영관 배치도");
		cp.setLayout(new GridLayout(9, 9));
		try {
			rs = db.rs("SELECT * FROM srm");
			while (rs.next()) {
				int i = rs.getInt(1) - 1;

				cp.add(pp[i] = new JPanel());
				sz(pp[i], 60, 60);
				line(pp[i], getForeground());

				int c = rs.getInt(2);
				Color col = c == 0 ? Color.white : c == 1 ? Color.gray : Color.blue;
				// 상태 (c) → 0: 흰색(빈칸), 1: 회색(불가), 2: 파랑(상영관)
				bk(pp[i], col);

				pp[i].add(jl = new JLabel(i + ""));

				sno[i] = rs.getString(3) == null ? 0 : rs.getInt(3);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		int s = 72, e = -1;// 출발 도착
		bk(pp[s], Color.green);

		for (int i = 0; i < pp.length; i++) {
			if (sno[i] == vq.srm)
				e = i;
		}

		th = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					int s = 72;
					int i = 0;
					for (;;) {
						if (ck == 0) {
							int a = srm[i] == 0 ? -9 : srm[i] == 1 ? -1 : srm[i] == 2 ? 1 : 9;
							// 0 → -9 → 위로 이동 1 → -1 → 왼쪽으로 이동 2 → 1 → 오른쪽으로 이동 3 → 9 → 아래로 이동

							s = s + a;
							System.out.println(srm[i]);
							if (pp[s].getBackground() == Color.blue)
								bk(pp[s], Color.green);
							else
								bk(pp[s], Color.red);

							i++;
							if (i == srm.length) {
								ck = 1;
								s = 72;
								i = 0;
							}
							th.sleep(100);
						} else {
							int a = srm[i] == 0 ? -9 : srm[i] == 1 ? -1 : srm[i] == 2 ? 1 : 9;
							s = s + a;
							if ((i + 1) != srm.length) {
								for (int j = 0; j < 3; j++) {
									bk(pp[s], Color.white);
									th.sleep(50);
									bk(pp[s], Color.red);
									th.sleep(50);
								}
							} else {
								th.interrupt();
								imsg(vq.srm + "관에 도착했습니다.");
								dispose();
							}
							i++;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		th.start();
		shp();
	}

	int ck = 0;


}
