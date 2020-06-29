import java.io.*;
import java.util.*;

public class shortcut {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("shortcut.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shortcut.out")));
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int[] cownums = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++){
			cownums[i + 1] = Integer.parseInt(st.nextToken());
		}
		
		ArrayList<Integer>[] path = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++){
			path[i] = new ArrayList<Integer>();
		}
		
		ArrayList<State>[] edges = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++){
			edges[i] = new ArrayList<State>();
		}
		
		for (int i = 0; i < M; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			edges[a].add(new State(c, b));
			edges[b].add(new State(c, a));
		}
		
		int[] passing = new int[N + 1];
		PriorityQueue<State> q = new PriorityQueue<State>();
		int[] distances = new int[N + 1];
		
		Arrays.fill(distances, Integer.MAX_VALUE / 2);
		distances[1] = 0;
		q.add(new State(0, 1));
		boolean[] processed = new boolean[N + 1];
		
		while (!q.isEmpty()){
			int node = q.poll().node;
			if (processed[node]) continue;
			processed[node] = true;
			
			for (State s: edges[node]){
				int len = s.length;
				int vert = s.node;
				if (distances[node] + len < distances[vert]){
					distances[vert] = distances[node] + len;
					q.add(new State(distances[vert], vert));
					path[vert] = (ArrayList<Integer>) path[node].clone();
					path[vert].add(vert);
				}
				// if the path is equal, make sure it is lexicographically smaller
				else if (distances[node] + len == distances[vert]){
					if (node < path[vert].get(path[vert].size() - 2)){
						path[vert] = (ArrayList<Integer>) path[node].clone();
						path[vert].add(vert);
					}
				}
			}
		}
		
		for (int i = 2; i <= N; i++){
			for (int val: path[i]){
				passing[val] += cownums[i];
			}
		}
		
		long max = 0;
		for (int i = 2; i <= N; i++){
			max = Math.max(max, (long) passing[i] * (distances[i] - T));
		}
		
		pw.println(max);
		br.close();
		pw.close();
	}
	static class State implements Comparable<State> {
		public int length, node;
		public State (int a, int b) {
			length = a;
			node = b;
		}
		public int compareTo (State other) {
			return length - other.length;
		}
	}
}
