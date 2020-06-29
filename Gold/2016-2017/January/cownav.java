import java.io.*;
import java.util.*;

public class cownav {
	
	static int N;
	static String[][] data;
	static boolean InRange (int x, int y){
		if (0 <= x && x < N && y >= 0 && y < N) return true;
		return false;
	}
	static boolean Ignore (int x, int y){
		if (x == (N - 1) && y == (N - 1)) return true;
		return false;
	}
	
	static boolean Checker (int x, int y, int dir){
		if (Ignore(x, y) == true) return false;
		
		if (dir == 0) y++;
		if (dir == 1) x--;
		if (dir == 2) y--;
		if (dir == 3) x++;
		
		if (InRange(x, y) && data[x][y].equals("E")) return true;
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cownav.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cownav.out")));
		N = Integer.parseInt(br.readLine());
		
		data = new String[N][N];
		for (int i = N - 1; i >= 0; i--){
			String t = br.readLine();
			for (int k = 0; k < N; k++){
				data[i][k] = t.substring(k, k + 1);
			}
		}
		
		// x1, y1, x2, y2, orientation
		boolean[][][][][] explored = new boolean[N][N][N][N][4];
		int[][][][][] distances = new int[N][N][N][N][4];
		
		explored[0][0][0][0][0] = true;
		LinkedList<State> q = new LinkedList<State>();
		q.add(new State(0, 0, 0, 0, 0));
		int[][] move = { {0, 1, 1, 0}, {-1, 0, 0, 1}, {0, -1, -1, 0}, {1, 0, 0, -1} };
		
		while (!q.isEmpty()){
			State s = q.poll();
			int ori = (s.ori + 1) % 4;
			if (explored[s.x1][s.y1][s.x2][s.y2][ori] == false){
				explored[s.x1][s.y1][s.x2][s.y2][ori] = true;
				distances[s.x1][s.y1][s.x2][s.y2][ori] = distances[s.x1][s.y1][s.x2][s.y2][s.ori] + 1;
				q.add(new State(s.x1, s.y1, s.x2, s.y2, ori));
			}
			
			ori = (s.ori + 3) % 4;
			if (explored[s.x1][s.y1][s.x2][s.y2][ori] == false){
				explored[s.x1][s.y1][s.x2][s.y2][ori] = true;
				distances[s.x1][s.y1][s.x2][s.y2][ori] = distances[s.x1][s.y1][s.x2][s.y2][s.ori] + 1;
				q.add(new State(s.x1, s.y1, s.x2, s.y2, ori));
			}
			
			// watch out for hay bales, out of bounds, and already reached destination cases
			if (Checker(s.x1, s.y1, s.ori) == true && Checker (s.x2, s.y2, (s.ori + 3) % 4) == true){
				if (explored[s.x1 + move[s.ori][0]][s.y1 + move[s.ori][1]][s.x2 + move[s.ori][2]][s.y2 + move[s.ori][3]][s.ori] == false){
					explored[s.x1 + move[s.ori][0]][s.y1 + move[s.ori][1]][s.x2 + move[s.ori][2]][s.y2 + move[s.ori][3]][s.ori] = true;
					distances[s.x1 + move[s.ori][0]][s.y1 + move[s.ori][1]][s.x2 + move[s.ori][2]][s.y2 + move[s.ori][3]][s.ori]
							= distances[s.x1][s.y1][s.x2][s.y2][s.ori] + 1;
					q.add(new State(s.x1 + move[s.ori][0], s.y1 + move[s.ori][1], s.x2 + move[s.ori][2], s.y2 + move[s.ori][3], s.ori));
				}
			}
			
			if (Checker(s.x1, s.y1, s.ori) == false && Checker (s.x2, s.y2, (s.ori + 3) % 4) == true){
				if (explored[s.x1][s.y1][s.x2 + move[s.ori][2]][s.y2 + move[s.ori][3]][s.ori] == false){
					explored[s.x1][s.y1][s.x2 + move[s.ori][2]][s.y2 + move[s.ori][3]][s.ori] = true;
					distances[s.x1][s.y1][s.x2 + move[s.ori][2]][s.y2 + move[s.ori][3]][s.ori]
							= distances[s.x1][s.y1][s.x2][s.y2][s.ori] + 1;
					q.add(new State(s.x1, s.y1, s.x2 + move[s.ori][2], s.y2 + move[s.ori][3], s.ori));
				}
			}
			
			if (Checker(s.x1, s.y1, s.ori) == true && Checker (s.x2, s.y2, (s.ori + 3) % 4) == false){
				if (explored[s.x1 + move[s.ori][0]][s.y1 + move[s.ori][1]][s.x2][s.y2][s.ori] == false){
					explored[s.x1 + move[s.ori][0]][s.y1 + move[s.ori][1]][s.x2][s.y2][s.ori] = true;
					distances[s.x1 + move[s.ori][0]][s.y1 + move[s.ori][1]][s.x2][s.y2][s.ori]
							= distances[s.x1][s.y1][s.x2][s.y2][s.ori] + 1;
					q.add(new State(s.x1 + move[s.ori][0], s.y1 + move[s.ori][1], s.x2, s.y2, s.ori));
				}
			}
		}
		
		int min = 1000000000;
		for (int i = 0; i < 4;i++){
			if (distances[N - 1][N - 1][N - 1][N - 1][i] > 0) min = Math.min(min, distances[N - 1][N - 1][N - 1][N - 1][i]);
		}
		pw.println(min);
		br.close();
		pw.close();
	}
	static class State {
		public int x1, y1, x2, y2, ori;
		public State (int a, int b, int c, int d, int e){
			x1 = a;
			y1 = b;
			x2 = c;
			y2 = d;
			ori = e;
		}
	}
}
