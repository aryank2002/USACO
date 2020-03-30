import java.util.*;
import java.io.*;

public class promote {
	
	static int[] associate;
	static int[] begin;
	static int count;
	static ArrayList<Integer>[] children;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("promote.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("promote.out")));
		
		int N = Integer.parseInt(br.readLine());
		int[] p = new int[N + 1];
		for (int i = 1; i <= N; i++){
			p[i] = Integer.parseInt(br.readLine());
		}
		
		children = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++){
			children[i] = new ArrayList<Integer>();
		}
		for (int i = 2; i <= N; i++){
			children[Integer.parseInt(br.readLine())].add(i);
		}
		associate = new int[N + 1];
		begin = new int[N + 1];
		
		count = 1;
		DFS(1);
		State[] sort = new State[N];
		
		for (int i = 0; i < N; i++){
			sort[i] = new State(associate[i + 1], i + 1, p[i + 1]);
		}
		Arrays.sort(sort);
		
		BIT bit = new BIT(N);
		int[] answer = new int[N + 1];
		
		for (int i = 0; i < N; i++){
			answer[sort[i].y] += bit.sum(sort[i].x) - bit.sum(begin[sort[i].x] - 1);
			bit.update(sort[i].x);
		}
		
		for (int i = 1; i <= N; i++){
			pw.println(answer[i]);
		}
		pw.close();
		br.close();
	}
	
	static class State implements Comparable<State> {
		public int x, y, p;
		public State (int a, int b, int c) {
			x = a;
			y = b;
			p = c;
		}
		public int compareTo (State other) {
			return other.p - p;
		}
	}
	
	static void DFS (int node){
		int min = 1000000000;
		for (int val: children[node]){
			DFS(val);
			min = Math.min(min, begin[associate[val]]);
		}
		min = Math.min(count, min);
		associate[node] = count;
		begin[associate[node]] = min;
		count++;
	}
	
	static class BIT {
		public int[] tree;
		public BIT (int N){
			tree = new int[N + 1];
		}
		public void update(int index){
			while (index < tree.length){
				tree[index]++;
				index += (index & -index);
			}
		}
		public int sum(int index){
			int s = 0;
			while (index >= 1){
				s += tree[index];
				index -= (index & -index);
			}
			return s;
		}
	}
}
