import java.io.*;
import java.util.*;

public class closing {
	
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("closing.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("closing.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		LinkedList<Integer>[] edges = new LinkedList[N + 1];
		parent = new int[N + 1];
		
		for (int i = 1;  i <= N; i++){
			edges[i] = new LinkedList<Integer>();
			parent[i] = i;
		}
		
		for (int i = 0; i < M; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			edges[a].add(b);
			edges[b].add(a);
		}
		
		int[] remove = new int[N + 1];
		for (int i = N; i > 0; i--){
			remove[i] = Integer.parseInt(br.readLine());
		}
		
		HashSet<Integer> vertices = new HashSet<Integer>();
		String[] prints = new String[N + 1];
		
		int count = 0;
		for (int i = 1; i <= N; i++){
			count++;
			int vertex = remove[i];
			vertices.add(vertex);
			
			for (int val: edges[vertex]){
				if (vertices.contains(val)){
					int a = Find(val);
					int b = Find(vertex);
					if (a != b){
						parent[a] = b;
						count--;
					}
				}
			}
			
			if (count == 1){
				prints[N - i + 1] = "YES";
			}
			else {
				prints[N - i + 1] = "NO";
			}
		}
		
		for (int i = 1; i <= N; i++){
			pw.println(prints[i]);
		}
		
		pw.close();
		br.close();
	}
	
	public static int Find(int curr) {
		return parent[curr] == curr ? curr : (parent[curr] = Find(parent[curr]));
	}
	
	public static void Union(int x, int y) {
		parent[Find(x)] = Find(y);
	}
}
