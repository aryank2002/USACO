import java.io.*;
import java.util.*;

public class fencedin2 {

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
		
		for (int i = 1; i <= N; i++) row[i] = Integer.parseInt(br.readLine());
		for (int i = 1; i <= M; i++) col[i] = Integer.parseInt(br.readLine());
		
		row[N + 1] = A;
		col[M + 1] = B;
		
		Arrays.sort(row);
		Arrays.sort(col);
		
		State[] s1 = new State[N + 1];
		State[] s2 = new State[M + 1];
		
		for (int i = 0; i < N + 1; i++) s1[i] = new State(i, row[i + 1] - row[i], false);
		for (int k = 0; k < M + 1; k++) s2[k] = new State(k, col[k + 1] - col[k], true);
		
		Arrays.sort(s1);
		Arrays.sort(s2);
		
		int v = 1; int h = 1;
		int size = M + N + 1;
		
		// s1 --> col, s2 --> row
		long tot = (long) M * (long) s1[0].weight + (long) N * (long) s2[0].weight;
				
		while (size != (M + 1) * (N + 1)){
			if (s1[v].weight <= s2[h].weight){
				tot = tot + (long) s1[v].weight * (long) (M + 1 - h);
				size += M + 1 - h;
				v++;
			}
			else {
				tot = tot + (long) s2[h].weight * (long) (N + 1 - v);
				size += N + 1 - v;
				h++;
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
