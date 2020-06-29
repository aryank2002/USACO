import java.io.*;
import java.util.*;

public class milkingorder {
	
	static LinkedList<Integer>[] data;
	static int N, M;
	
	static boolean Possible (int k) {
		int[] degree = new int[N + 1];
		LinkedList<Integer>[] parent = new LinkedList[N + 1];
		for (int i = 0; i <= N; i++){
			parent[i] = new LinkedList<Integer>();
		}
		
		for (int i = 0; i < k; i++){
			int length = data[i].size();
			for (int a = 0; a < (length - 1); a++){
				int x = data[i].get(a);
				int y = data[i].get(a + 1);
				degree[x]++;
				parent[y].add(x);
			}
		}
		
		LinkedList<Integer> q = new LinkedList<Integer>();
		int count = N;
		for (int i = 1; i <= N; i++){
			if (degree[i] == 0){
				count--;
				q.add(i);
			}
		}
		
		while (!q.isEmpty() && count != 0){
			int node = q.poll();
			// maybe add set to see if val is in twice
			for (int val: parent[node]){
				degree[val]--;
				if (degree[val] == 0){
					q.add(val);
					count--;
				}
			}
		}
		
		if (count == 0) return true;
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("milkorder.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milkorder.out")));
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		data = new LinkedList[M];
		for (int i = 0; i < M; i++){
			data[i] = new LinkedList<Integer>();
		}
		
		for (int i = 0; i < M; i++){
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			for (int k = 0; k < m; k++){
				data[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		int min = 0;
		int max = M;
		
		while (min != max){
			int mid = (min + max + 1) / 2;
			if (Possible(mid)){
				min = mid;
			}
			else {
				max = mid - 1;
			}
		}
		
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		int[] parentnum = new int[N + 1];
		
		LinkedList<Integer>[] children = new LinkedList[N + 1];
		for (int i = 0; i <= N; i++){
			children[i] = new LinkedList<Integer>();
		}
		
		for (int i = 0; i < min; i++){
			int size = data[i].size();
			for (int k = 0; k < (size - 1); k++){
				int a = data[i].get(k);
				int b = data[i].get(k + 1);
				
				parentnum[b]++;
				children[a].add(b);
			}
		}
		
		for (int i = 1; i <= N; i++){
			if (parentnum[i] == 0) q.add(i);
		}
		
		int[] l = new int[N];
		int count = 0;
		while (!q.isEmpty()){
			int node = q.poll();
			l[count] = node;
			count++;
			// maybe need a set
			for (int val: children[node]){
				parentnum[val]--;
				if (parentnum[val] == 0){
					q.add(val);
				}
			}
		}
		
		pw.print(l[0]);
		for (int i = 1; i < N; i++){
			pw.print(" " + l[i]);
		}
		
		br.close();
		pw.close();
	}
}
