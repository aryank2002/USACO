import java.io.*;
import java.util.*;

public class slingshot {
	
	static int N;
	static long[] segment;
	
	static void update (int node, int left, int right, int x, long y) {
	    if (left == right) segment[node] = y;
	    else {
	        int middle = (left + right) / 2;
	        if (x <= middle) update(2 * node + 1, left, middle, x, y);
	        else update(2 * node + 2, middle + 1, right, x, y);
	        segment[node] = Math.min(segment[2 * node + 1], segment[2 * node + 2]);
	    }
	}
	
	static long query (int node, int left, int right, int x, int y) {
	    if (x <= left && right <= y) return segment[node];
	    else {
	    	long answer = Long.MAX_VALUE / 2;
	        int middle = (left + right) / 2;
	        
	        if (x <= middle) answer = Math.min(answer, query(2 * node + 1, left, middle, x, y));
	        if (y >= middle + 1) answer = Math.min(answer, query(2 * node + 2, middle + 1, right, x, y));
	        return answer;
	    }
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("slingshot.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("slingshot.out")));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// x_i, y_i, t_i
		int[][] slingshot = new int[N + 1][3];
		long[] answers = new long[M + 1];
		segment = new long[4 * N];
		
		// index, y
		State[] ys = new State[N + 1];
		ys[0] = new State(0, -1);
		
		State[] xs = new State[N + 1];
		xs[0] = new State(0, -1);
		
		for (int i = 1; i <= N; i++){
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < 3; k++){
				slingshot[i][k] = Integer.parseInt(st.nextToken());
			}
			ys[i] = new State(i, slingshot[i][1]);
			xs[i] = new State(i, slingshot[i][0]);
			update (0, 1, N, i, Long.MAX_VALUE / 2);
		}
		
		Arrays.sort(ys);
		Arrays.sort(xs);
		
		// a_i, b_i
		int[][] queries = new int[M + 1][2];
		State[] as = new State[M + 1];
		as[0] = new State(0, -1);
		State[] bs = new State[M + 1];
		bs[0] = new State(0, -1);
		
		for (int i = 1; i <= M; i++){
			st = new StringTokenizer(br.readLine());
			queries[i][0] = Integer.parseInt(st.nextToken());
			queries[i][1] = Integer.parseInt(st.nextToken());
			as[i] = new State(i, queries[i][0]);
			bs[i] = new State(i, queries[i][1]);
		}
		
		Arrays.sort(as);
		Arrays.sort(bs);
		int[] index = new int[N + 1];
		int[] bsort = new int[M + 1];
		for (int i = 1; i <= M; i++) bsort[bs[i].x] = i;
		
		// says ith slingshot is in position __ when sorted by y_i
		for (int i = 1; i <= N; i++) index[ys[i].x] = i;
		for (int i = 1; i <= M; i++) answers[i] = Math.abs(queries[i][0] - queries[i][1]);
		
		int[] bless = new int[M + 1];
		int bindex = 1;
		int yindex = 1;
		
		for (int i = 0; i < M + N; i++){
			if (bindex == M + 1) {
				break;
			}
			else if (yindex == N + 1) {
				bless[bindex] = N;
				bindex++;
			}
			else {
				if (bs[bindex].y > ys[yindex].y) {
					yindex++;
				}
				else {
					bless[bindex] = yindex - 1;
					bindex++;
				}
			}
		}
		
		
		// add a_i + b_i to the sum
		int s = 0;
		int q = 0;
		for (int i = 0; i < N + M; i++){
			if (q == M) break;
			else if (s == N) {
				q++;
				if (bless[bsort[as[q].x]] == 0) continue;
				answers[as[q].x] = Math.min(answers[as[q].x], 
						(long) (queries[as[q].x][0] + queries[as[q].x][1] + query(0, 1, N, 1, bless[bsort[as[q].x]])));
			}
			else {
				if (as[q + 1].y > xs[s + 1].y) {
					s++;
					update (0, 1, N, index[xs[s].x], 
							(long) (slingshot[xs[s].x][2] - slingshot[xs[s].x][1] - slingshot[xs[s].x][0]));
				}
				else {
					q++;
					if (bless[bsort[as[q].x]] == 0) continue;
					answers[as[q].x] = Math.min(answers[as[q].x], 
							(long) (queries[as[q].x][0] + queries[as[q].x][1] + query(0, 1, N, 1, bless[bsort[as[q].x]])));
				}
			}
		}
		
		s = 0;
		q = 0;
		for (int i = 1; i <= N; i++) update (0, 1, N, index[i], (long) (slingshot[i][2] - slingshot[i][1] + slingshot[i][0]));
		for (int i = 0; i < N + M; i++){
			if (q == M) break;
			else if (s == N) {
				q++;
				break;
			}
			else {
				if (as[q + 1].y > xs[s + 1].y) {
					s++;
					update (0, 1, N, index[xs[s].x], Long.MAX_VALUE / 2);
				}
				else {
					q++;
					if (bless[bsort[as[q].x]] == 0) continue;
					answers[as[q].x] = Math.min(answers[as[q].x], 
							(long) (-queries[as[q].x][0] + queries[as[q].x][1] + query(0, 1, N, 1, bless[bsort[as[q].x]])));
				}
			}
		}
		
		s = 0;
		q = 0;
		for (int i = 1; i <= N; i++) update (0, 1, N, i, Long.MAX_VALUE / 2);
		for (int i = 0; i < N + M; i++){
			if (q == M) break;
			else if (s == N) {
				q++;
				if (bless[bsort[as[q].x]] == N) continue;
				answers[as[q].x] = Math.min(answers[as[q].x], 
						(long) (queries[as[q].x][0] - queries[as[q].x][1] + query(0, 1, N, bless[bsort[as[q].x]] + 1, N)));
			}
			else {
				if (as[q + 1].y > xs[s + 1].y) {
					s++;
					update (0, 1, N, index[xs[s].x], 
							(long) (slingshot[xs[s].x][2] + slingshot[xs[s].x][1] - slingshot[xs[s].x][0]));
				}
				else {
					q++;
					if (bless[bsort[as[q].x]] == N) continue;
					answers[as[q].x] = Math.min(answers[as[q].x], 
							(long) (queries[as[q].x][0] - queries[as[q].x][1] + query(0, 1, N, bless[bsort[as[q].x]] + 1, N)));
				}
			}
		}
		
		s = 0;
		q = 0;
		for (int i = 1; i <= N; i++) update (0, 1, N, index[i], (long) (slingshot[i][0] + slingshot[i][1] + slingshot[i][2]));
		for (int i = 0; i < N + M; i++){
			if (q == M) break;
			else if (s == N) {
				q++;
				if (bless[bsort[as[q].x]] == N) continue;
				answers[as[q].x] = Math.min(answers[as[q].x], 
						(long) (-queries[as[q].x][0] - queries[as[q].x][1] + query(0, 1, N, bless[bsort[as[q].x]] + 1, N)));
			}
			else {
				if (as[q + 1].y > xs[s + 1].y) {
					s++;
					update (0, 1, N, index[xs[s].x], Long.MAX_VALUE / 2);
				}
				else {
					q++;
					if (bless[bsort[as[q].x]] == N) continue;
					answers[as[q].x] = Math.min(answers[as[q].x], 
							(long) (-queries[as[q].x][0] - queries[as[q].x][1] + query(0, 1, N, bless[bsort[as[q].x]] + 1, N)));
				}
			}
		}
		
		for (int i = 1; i <= M; i++) pw.println(answers[i]);
		br.close();
		pw.close();
	}
	
	static class State implements Comparable<State> {
		public int x, y;
		public State (int a, int b) {
			x = a;
			y = b;
		}
		public int compareTo (State other) {
			return y - other.y;
		}
	}
}
