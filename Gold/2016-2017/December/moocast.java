import java.util.*;
import java.io.*;

public class moocast {

	static int maxsize = 1;
	static int[] parent;
	static int[] size;
	static HashMap<Integer, ArrayList<Integer>> clusters = new HashMap<Integer, ArrayList<Integer>>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("moocast.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
		
		int N = Integer.parseInt(st.nextToken());
		size = new int[N + 1];
		Arrays.fill(size, 1);
		
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++){
			parent[i] = i;
		}
		
		for (int i = 1; i <= N; i++){
			ArrayList<Integer> t = new ArrayList<Integer>();
			t.add(i);
			clusters.put(i, t);
		}
		
		int[] x = new int[N + 1];
		int[] y = new int[N + 1];
		for (int i = 1; i <= N; i++){
			st = new StringTokenizer(br.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}
		
		int count = 0;
		int nums = N * (N - 1);
		nums = nums / 2;
		Edge[] edges = new Edge[nums];
		for (int i = 1; i <= N; i++){
			for (int k = 1; k <= N; k++){
				if (k <= i){
					continue;
				}
				edges[count] = new Edge((x[i] - x[k]) * (x[i] - x[k]) + (y[i] - y[k]) * (y[i] - y[k]), i, k);
				count++;
			}
		}
		
		Arrays.sort(edges);
		for (Edge a: edges){
			Union(a.node1, a.node2);
			if (maxsize == N){
				pw.println(a.dist);
				break;
			}
		}

		br.close();
		pw.close();
	}
	
	public static void Union(int x, int y){
		int p1 = parent[x];
		int p2 = parent[y];
		if (p1 == p2){
			return;
		}
		
		int s1 = size[p1];
		int s2 = size[p2];
		if (s1 > s2){
			for (int val: clusters.get(p2)){
				parent[val] = p1;
				clusters.get(p1).add(val);
				size[p1] = s1 + s2;
				maxsize = Math.max(maxsize, size[p1]);
			}
			clusters.remove(p2);
		}
		else {
			for (int val: clusters.get(p1)){
				parent[val] = p2;
				clusters.get(p2).add(val);
				size[p2] = s1 + s2;
				maxsize = Math.max(maxsize, size[p2]);
			}
			clusters.remove(p1);
		}
	}
	
	static class Edge implements Comparable<Edge> {
		public int dist, node1, node2;
		public Edge (int a, int b, int c) {
			dist = a;
			node1 = b;
			node2 = c;
		}
		public int compareTo (Edge other) {
			return dist - other.dist;
		}
	}
}
