import java.io.*;
import java.util.*;

public class mootube {
	
	static int[] parent;
	static int[] size;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("mootube.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		parent = new int[N + 1];
		size = new int[N + 1];
		
		for (int i = 1; i <= N; i++){
			parent[i] = i;
			size[i] = 1;
		}
		
		Edges[] edge = new Edges[N - 1];
		Queries[] qs = new Queries[Q];
		
		for (int i = 1; i < N; i++){
			st = new StringTokenizer(br.readLine());
			edge[i - 1] = new Edges(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(edge);
		
		for (int i = 0; i < Q; i++){
			st = new StringTokenizer(br.readLine());
			qs[i] = new Queries(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i + 1);
		}
		Arrays.sort(qs);
		
		int count = -1;
		int[] answers = new int[Q + 1];
		for (int i = 0; i < Q; i++){
			int rel = qs[i].rel;
			int vert = qs[i].vert;
			int index = qs[i].index;
			
			while (count < (N - 2) && edge[count + 1].length >= rel){
				count++;
				Union (edge[count].x, edge[count].y);
			}
			
			answers[index] = size[Find(vert)] - 1;
		}
		
		for (int i = 1; i <= Q; i++){
			pw.println(answers[i]);
		}
		
		pw.close();
		br.close();
	}
	
	public static int Find(int curr) {
		return parent[curr] == curr ? curr : (parent[curr] = Find(parent[curr]));
	}
	
	public static void Union(int x, int y) {
		int a = Find(y);
		int b = Find(x);
		if (a != b) size[a] = size[a] + size[b]; 
		parent[b] = a;
	}
	
	static class Edges implements Comparable<Edges> {
		public int x, y, length;
		public Edges (int a, int b, int c) {
			x = a;
			y = b;
			length = c;
		}
		public int compareTo (Edges other) {
			return other.length - length;
		}
	}
	
	static class Queries implements Comparable<Queries> {
		public int rel, vert, index;
		public Queries (int a, int b, int c) {
			rel = a;
			vert = b;
			index = c;
		}
		public int compareTo (Queries other) {
			return other.rel - rel;
		}
	}
}
