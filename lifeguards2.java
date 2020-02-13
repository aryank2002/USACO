import java.io.*;
import java.util.*;

public class lifeguards2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("lifeguards.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lifeguards.out")));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		State[] sort = new State[2 * N];
		int[] b = new int[N];
		int[] e = new int[N];
		
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			b[i] = Integer.parseInt(st.nextToken());
			e[i] = Integer.parseInt(st.nextToken());
			
			sort[2 * i] = new State(b[i], i);
			sort[2 * i + 1] = new State(e[i], i);
		}
		
		Arrays.sort(sort);
		TreeSet<Integer> ts = new TreeSet<Integer>();
		boolean[] cover = new boolean[N];
		int count = 0;
		
		for (int i = 0; i < 2 * N; i++){
			if (ts.contains(b[sort[i].beg])){
				ts.remove(b[sort[i].beg]);
				
				if (ts.size() > 0 && ts.first() < b[sort[i].beg]) {
					cover[sort[i].beg] = true;
					count++;
				}
			}
			else ts.add(sort[i].end);
		}
		
		int[][] dp = new int[N + 1][K + 1];
		N -= count;
		
		State[] end = new State[N + 1];
		end[0] = new State(-1, -1);
		int t = 0;
		
		for (int i = 0; i < N + count; i++) if (cover[i] == false) end[++t] = new State(e[i], b[i]);
		for (int i = 0; i <= N; i++) Arrays.fill(dp[i], Integer.MIN_VALUE / 2);
		
		int length = 0;
		Arrays.sort(end);
		end[0] = new State(0, 0);
		
		for (int i = 1; i <= N; i++){
			length += end[i].end - end[i].beg;
			if (i != 1){
				length -= Math.max(0, end[i - 1].end - end[i].beg);
			}
			dp[i][0] = length;
			
		}
		
		for (int i = 0; i <= K; i++) {
			dp[i][i] = 0;
			if (i + 1 <= N) dp[i + 1][i] = end[i + 1].end - end[i + 1].beg;
		}
		
		K -= count;
		
		if (K <= 0){
			pw.println(length);
			pw.close();
			System.exit(0);
		}
		
		for (int dif = 2; dif <= N; dif++){
			int max = -1100000000;
			Deque<State> intersect = new ArrayDeque<State>();
			intersect.add(new State(dp[dif - 1][0] - end[dif - 1].end, dif - 1));
			
			if (intersect.getFirst().end < dp[dif][1] - end[dif].end) intersect.removeFirst();
			intersect.add(new State(dp[dif][1] - end[dif].end, dif));
			int a = dif - 1;
			
			for (int k = 1; k <= Math.min(K, N - dif); k++){
				while (!intersect.isEmpty() && end[intersect.getFirst().beg].end <= end[dif + k].beg) intersect.removeFirst();
				
				while (a <= N && end[a].end <= end[dif + k].beg) {
					max = Math.max(max, dp[a][a - dif + 1]);
					a++;
				}
				
				if (intersect.isEmpty()) dp[dif + k][k] = max;
				else dp[dif + k][k] = Math.max(max, intersect.getFirst().end + end[dif + k].beg);
				dp[dif + k][k] += end[dif + k].end - end[dif + k].beg;
				
				if (k == K) continue;
				while (!intersect.isEmpty() && intersect.getLast().end <= dp[dif + k][k + 1] - end[dif + k].end) intersect.removeLast();
				intersect.addLast(new State(dp[dif + k][k + 1] - end[dif + k].end, dif + k));
			}
		}
		
		int max = 0;
		for (int i = 0; i <= K; i++){
			max = Math.max(max, dp[N - i][K - i]);
		}
		
		pw.println(max);
		pw.close();
		br.close();
	}
	
	static class State implements Comparable<State> {
		// end --> value, beg --> index
		public int end, beg;
		public State(int a, int b){
			end = a;
			beg = b;
		}
		public int compareTo(State other){
			return end - other.end;
		}
	}
}