import java.io.*;
import java.util.*;

public class atlarge {
	
	static int N;
	static int K;
	static int[] degree;
	static LinkedList<Integer>[] edges;
	
	static int[] besdist;
	static int[] exitdist;
	static boolean[] explored;
	static int count;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("atlarge.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("atlarge.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		degree = new int[N + 1];
		edges = new LinkedList[N + 1];
		
		for (int i = 0; i <= N; i++){
			edges[i] = new LinkedList<Integer>();
		}
		
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			edges[a].add(b);
			edges[b].add(a);
			degree[a]++;
			degree[b]++;
		}
		
		besdist = new int[N + 1];
		exitdist = new int[N + 1];
		explored = new boolean[N + 1];
		
		BessieDFS(K, 0);
		Arrays.fill(explored, false);
		
		LinkedList<Integer> q = new LinkedList<Integer>();
		for (int i = 1; i <= N; i++){
			if (degree[i] == 1){
				explored[i] = true;
				exitdist[i] = 0;
				q.add(i);
			}
		}
		
		while (!q.isEmpty()){
			int val = q.poll();
			for (int edge: edges[val]){
				if (explored[edge] == false){
					explored[edge] = true;
					exitdist[edge] = exitdist[val] + 1;
					q.add(edge);
				}
			}
		}
		
		count = 0;
		DFS(K, 0);
		
		pw.println(count);
		br.close();
		pw.close();
	}
	
	static void BessieDFS (int vertex, int distance){
		explored[vertex] = true;
		for (int val: edges[vertex]){
			if (explored[val] == false){
				BessieDFS(val, distance + 1);
			}
		}
		besdist[vertex] = distance;
	}
	
	static void DFS (int vertex, int prev){
		if (degree[vertex] == 1){
			count++;
			return;
		}
		for (int val: edges[vertex]){
			if (val == prev) continue;
			if (besdist[val] >= exitdist[val]){
				count++;
			}
			else {
				DFS (val, vertex);
			}
		}
	}
}
