import java.io.*;
import java.util.*;

public class gathering {

	static ArrayList<Integer>[] connection;
	static boolean[] impossible;
	static int[] ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("gathering.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("gathering.out")));
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		connection = new ArrayList[N + 1];
		// cows that have to leave after cow i
		ArrayList<Integer>[] after = new ArrayList[N + 1];
		
		for (int i = 0; i <= N; i++){
			connection[i] = new ArrayList<Integer>();
			after[i] = new ArrayList<Integer>();
		}
		
		int[] edges = new int[N + 1];
		// cows that must leave before cow i
		int[] before = new int[N + 1];
		
		impossible = new boolean[N + 1];
		
		int a, b;
		for (int i = 1; i < N; i++){
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			connection[a].add(b);
			connection[b].add(a);
			edges[a]++;
			edges[b]++;
		}
		
		for (int i = 0; i < M; i++){
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			impossible[a] = true;
			before[b]++;
			after[a].add(b);
		}
		
		LinkedList<Integer> q = new LinkedList<Integer>();
		for (int i = 1; i <= N; i++){
			if (edges[i] == 1 && before[i] == 0) q.add(i);
		}
		
		boolean[] used = new boolean[N + 1];
		
		for (int i = 1; i < N; i++){
			if (q.isEmpty()) {
				for (int k = 0; k < N; k++) pw.println(0);
				pw.close();
				return;
			}
			
			int cow = q.poll();
			used[cow] = true;
			
			for (int val: connection[cow]) {
				edges[val]--;
				if (edges[val] == 1 && before[val] == 0) q.add(val);
			}
			for (int val: after[cow]) {
				before[val]--;
				if (before[val] == 0 && edges[val] == 1) q.add(val);
			}
		}
		
		int rem = 0;
		ans = new int[N + 1];
		
		for (int i = 1; i <= N; i++){
			if (used[i] == false) rem = i;
		}
		
		ans[rem] = 1;
		DFS (rem, -1);
		for (int i = 1; i <= N; i++) pw.println(ans[i]);
		
		pw.close();
		br.close();
	}
	
	static void DFS (int cow, int prev){
		for (int val: connection[cow]){
			if (val != prev && impossible[val] == false){
				ans[val] = 1;
				DFS (val, cow);
			}
		}
	}
}
