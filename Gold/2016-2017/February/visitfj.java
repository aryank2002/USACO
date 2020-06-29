import java.util.*;
import java.io.*;

public class visitfj {
	
	static int N;
	static ArrayList<int[]>[][] edges;
	static int T;
	static int[][] data;
	
	static boolean InRange(int x, int y){
		if (1 <= x && x <= N && 1 <= y && y<= N) return true;
		return false;
	}
	
	static void EdgeAdder(int x, int y){
		if (InRange(x, y + 3)){
			int[] t = {x, y + 3, 3 * T + data[x][y + 3]};
			edges[x][y].add(t);
		}
		if (InRange(x, y - 3)){
			int[] t = {x, y - 3, 3 * T + data[x][y - 3]};
			edges[x][y].add(t);
		}
		for (int i = -1; i <= 1; i++){
			if (i % 2 == 0) continue;
			if (InRange(x + i, y + 2)){
				int[] t = {x + i, y + 2, 3 * T + data[x + i][y + 2]};
				edges[x][y].add(t);
			}
		}
		for (int i = -2; i <= 2; i++){
			if (i % 2 == 1 || i % 2 == -1) continue;
			if (InRange(x + i, y + 1)){
				int[] t = {x + i, y + 1, 3 * T + data[x + i][y + 1]};
				edges[x][y].add(t);
			}
		}
		for (int i = -3; i <= 3; i++){
			if (i % 2 == 0) continue;
			if (InRange(x + i, y)){
				int[] t = {x + i, y, 3 * T + data[x + i][y]};
				edges[x][y].add(t);
			}
		}
		for (int i = -2; i <= 2; i++){
			if (i % 2 == 1 || i % 2 == -1) continue;
			if (InRange(x + i, y - 1)){
				int[] t = {x + i, y - 1, 3 * T + data[x + i][y - 1]};
				edges[x][y].add(t);
			}
		}
		for (int i = -1; i <= 1; i++){
			if (i % 2 == 0) continue;
			if (InRange(x + i, y - 2)){
				int[] t = {x + i, y - 2, 3 * T + data[x + i][y - 2]};
				edges[x][y].add(t);
			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("visitfj.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("visitfj.out")));
		
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		data = new int[N + 1][N + 1];
		int[][] distances = new int[N + 1][N + 1];
		for (int i = 0; i <= N; i++){
			Arrays.fill(distances[i], Integer.MAX_VALUE / 3);
		}
		
		for (int i = N; i > 0; i--){
			st = new StringTokenizer(br.readLine());
			for (int k = 1; k <= N; k++){
				data[k][i] = Integer.parseInt(st.nextToken());
			}
		}
		
		edges = new ArrayList[N + 1][N + 1]; 
		boolean[][] processed = new boolean[N + 1][N + 1];
		
		for (int i = 1; i <= N; i++){
			for (int k = 1; k <= N; k++){
				edges[i][k] = new ArrayList<int[]>();
				EdgeAdder(i, k);
			}
		}
		
		distances[1][N] = 0;
		PriorityQueue<State> q = new PriorityQueue<State>();
		q.add(new State(0, 1, N));
		
		while (!q.isEmpty()){
			State s = q.poll();
			int x = s.node1;
			int y = s.node2;
			if (processed[x][y]) continue;
			
			processed[x][y] = true;
			for (int[] t: edges[x][y]){
				int xt = t[0];
				int yt = t[1];
				int w = t[2];
				
				if (distances[x][y] + w < distances[xt][yt]){
					distances[xt][yt] = distances[x][y] + w;
					q.add(new State(distances[xt][yt], xt, yt));
				}
			}
		}
		int min = distances[N][1];
		min = Math.min(distances[N - 2][1] + 2 * T, min);
		min = Math.min(distances[N - 1][2] + 2 * T, min);
		min = Math.min(distances[N][3] + 2 * T, min);
		
		min = Math.min(distances[N - 1][1] + T, min);
		min = Math.min(distances[N][2] + T, min);
		
		pw.println(min);
		br.close();
		pw.close();
	}
	
	static class State implements Comparable<State> {
		public int weight, node1, node2;
		public State (int a, int b, int c) {
			weight = a;
			node1 = b;
			node2 = c;
		}
		public int compareTo (State other) {
			return weight - other.weight;
		}
	}
}
