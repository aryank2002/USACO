import java.io.*;
import java.util.*;

public class mooriokart {
	
	static ArrayList<Edge>[] adj;
	static boolean[] explore;
	static int[] region;
	static int count, tot;
	static long mod = 1000000007;
	static Edge[][] dist;
	static Edge[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("mooriokart.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mooriokart.out")));
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[N + 1];
		explore = new boolean[N + 1];
		region = new int[N + 1];
		for (int i = 0; i <= N; i++) adj[i] = new ArrayList<Edge>();
		
		for (int i = 0; i < M; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			adj[a].add(new Edge(b, d));
			adj[b].add(new Edge(a, d));
		}
		
		for (int i = 1; i <= N; i++){
			if (explore[i] == true) continue;
			count++;
			DFS(i, i);
		}
		
		tot = Math.max(0, Y - X * count) + 1;
		dist = new Edge[count + 1][tot];
		dp = new Edge[count + 1][tot];
		
		for (int i = 0; i <= count; i++) for (int k = 0; k < tot; k++) dp[i][k] = new Edge(0, 0);
		for (int i = 0; i <= count; i++) for (int k = 0; k < tot; k++) dist[i][k] = new Edge(0, 0);
		
		for (int i = 1; i <= N; i++) DFS2(i, i, i, 0);
		for (int i = 0; i < tot; i++) {
			dp[1][i].node = dist[1][i].node;
			dp[1][i].len = dist[1][i].len;
		}
		
		// node ==> ways to have tracks with length k (dp[i][k])
		// len ==> total length of tracks with length k
		// System.out.println(dist[1][7].node + " " + dist[1][7].len);
		for (int i = 2; i <= count; i++){
			for (int k = 0; k < tot; k++){
				if (dist[i][k].node == 0) continue;
				
				for (int m = 0; m < tot; m++){
					int t = Math.min(tot - 1, k + m);
					// System.out.println(dp[i][t].len + " " + dp[i][t].node);
					dp[i][t].len = (dp[i][t].len + dist[i][k].node * dp[i - 1][m].len + 
								dp[i - 1][m].node * dist[i][k].len) % mod;
					
					dp[i][t].node = (dp[i][t].node + dp[i - 1][m].node
								* dist[i][k].node) % mod;
					// System.out.println(i + " " + k + " " + m + " " + t + " " + dp[i][t].len + " " + dp[i][t].node);
				}
			}
		}
		
		long mult = 1;
		for (int i = 1; i < count; i++) mult = (2L * (long) mult * (long) i) % mod;
		long ans =  (mult * ((dp[count][tot - 1].len + dp[count][tot - 1].node * (long) X * (long) count) % mod)) % mod;
		
		pw.println(ans);
		pw.close();
		br.close();
	}
	
	static void DFS2(int node, int par, int ref, int d){
		if (node > ref) {
			dist[region[node]][Math.min(tot - 1, d)].node++;
			dist[region[node]][Math.min(tot - 1, d)].len = (dist[region[node]][Math.min(tot - 1, d)].len + d) % mod;
		}
		
		for (Edge e: adj[node]){
			if (e.node == par) continue;
			DFS2((int) e.node, node, ref, d + (int) e.len);
		}
	}
	
	static void DFS(int node, int par){
		explore[node] = true;
		region[node] = count;
		
		for (Edge e: adj[node]){
			if (e.node == par) continue;
			DFS((int) e.node, node);
		}
	}
	
	static class Edge {
		public long node, len;
		public Edge(long a, long b){
			node = a;
			len = b;
		}
	}
}
