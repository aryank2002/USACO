import java.io.*;
import java.util.*;

public class barnpainting {
	
	static boolean[][] explored;
	static long[][] DP;
	static long[] color;
	static long mod;
	static LinkedList<Long>[] edges;
	
	static long DP (int vertex, int colors, int prev){
		if (color[vertex] > 0 && color[vertex] != colors) {
			explored[vertex][colors] = true;
			DP[vertex][colors] = 0;
			return 0;
		}
		
		else {
			long ans = 1;
			for (long val: edges[vertex]){
				if (val == prev) continue;
				long temp = 0;
				for (int i = 1; i <= 3; i++){
					if (i == colors){
						continue;
					}
					if (explored[vertex][colors] == true){
						temp = (temp + DP[(int) val][i]) % mod;
					}
					else {
						temp = (temp + DP((int) val, i, vertex)) % mod;
					}
				}
				ans = (ans * temp) % mod;
			}
			explored[vertex][colors] = true;
			DP[vertex][colors] = ans;
			return ans;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("barnpainting.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("barnpainting.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long N = Long.parseLong(st.nextToken());
		long K = Long.parseLong(st.nextToken());
		mod = (long) Math.pow(10, 9) + 7;
		
		edges = new LinkedList[(int) N + 1];
		color = new long[(int) N + 1];
		explored = new boolean[(int) N + 1][4];
		DP = new long[(int) N + 1][4];
		
		for (int i = 0; i <= N; i++){
			edges[i] = new LinkedList<Long>();
		}
		
		for (int i = 1; i < N; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edges[a].add((long) b);
			edges[b].add((long) a);
		}
		
		for (int i = 0; i < K; i++){
			st = new StringTokenizer(br.readLine());
			color[Integer.parseInt(st.nextToken())] = Long.parseLong(st.nextToken());
		}
		
		long answer = 0;
		if (color[1] > 0){
			answer = DP(1, (int) color[1], 0) % mod;
		}
		else {
			answer = (DP(1, 1, 0) + DP(1, 2, 0) + DP(1, 3, 0)) % mod;
		}
		
		pw.println(answer);
		br.close();
		pw.close();
	}
}
