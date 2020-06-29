import java.io.*;
import java.util.*;

public class fencedin {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("fencedin.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fencedin.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] row = new int[N + 2];
		int[] col = new int[M + 2];
		boolean[] useRow = new boolean[M + 1];
		boolean[] useCol = new boolean[N + 1];
		
		boolean r = false;
		boolean c = false;
		
		for (int i = 1; i <= N; i++) row[i] = Integer.parseInt(br.readLine());
		for (int i = 1; i <= M; i++) col[i] = Integer.parseInt(br.readLine());
		
		row[N + 1] = A;
		col[M + 1] = B;
		
		Arrays.sort(row);
		Arrays.sort(col);
		
		long tot = 0;
		int size = 0;
		PriorityQueue<State> pq = new PriorityQueue<State>();
		
		int numCol = 0;
		int numRow = 0;
		
		int a = 0;
		int b = 0;
		
		for (int i = 0; i <= N; i++) if (row[a + 1] - row[a] > row[i + 1] - row[i]) a = i;
		for (int i = 0; i <= M; i++) if (col[b + 1] - col[b] > col[i + 1] - col[i]) b = i;
		
		if (col[b] - col[b] <= row[a] - row[a]){
			size += N + 1;
			tot = tot + (long) N * (long) (col[b + 1] - col[b]);
			
			for (int i = 0; i <= N; i++){
				pq.add(new State(i, row[i + 1] - row[i], false));
				useCol[i] = true;
			}
			
			useRow[b] = true;
			c = true;
			numRow++;
		}
		else {
			size += M + 1;
			tot = tot + (long) M * (long) (row[a + 1] - row[a]);
			
			for (int i = 0; i <= M; i++) {
				pq.add(new State(i, col[i + 1] - col[i], true));
				useRow[i] = true;
			}
			
			r = true;
			useCol[a] = true;
			numCol++;
		}
		
		while (size != (N + 1) * (M + 1)){
			State s = pq.poll();
			
			if (s.row == true){
				size += N + 1 - numCol;
				tot = tot + (long) (N + 1 - numCol) * (long) s.weight;
				
				if (c == false){
					for (int i = 0; i <= N; i++){
						if (useCol[i] == true) continue;
						pq.add(new State(i, row[i + 1] - row[i], false));
						useCol[i] = true;
					}
					c = true;
				}
				
				useRow[s.num] = true;
				numRow++;
				c = true;
			}
			else {
				size += M + 1 - numRow;
				tot = tot + (long) (M + 1 - numRow) * (long) s.weight;
				
				if (r == false){
					for (int i = 0; i <= M; i++){
						if (useRow[i] == true) continue;
						pq.add(new State(i, col[i + 1] - col[i], true));
						useRow[i] = true;
					}
					r = true;
				}
				
				useCol[s.num] = true;
				numCol++;
				r = true;
			}
		}
		
		pw.println(tot);
		pw.close();
		br.close();
	}
	
	static class State implements Comparable<State> {
		public boolean row; 
		public int num, weight;
		public State(int a, int b, boolean c){
			num = a;
			weight = b;
			row = c;
		}
		public int compareTo(State other){
			return weight - other.weight;
		}
	}
}
