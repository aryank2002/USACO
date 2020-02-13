import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class disrupt {
	
	static ArrayList<Integer>[] edges, active, deactivate;
	static TreeMap<Integer, Integer>[] tm;
	static int[] above, answers, depth, size;
	static int[][] track, jumps;
	static boolean[] explored = new boolean[60000];
	
	static int LCA (int a, int b) {
		if (depth[a] > depth[b]) {
			int c = a;
			a = b;
			b = c;
		}
		int d = depth[b] - depth[a];
		for (int h = 15; h >= 0; h--){
			if ((1 << h) <= d) {
				b = jumps[b][h];
				d -= 1 << h;
			}
		}
		for (d = 15; d >= 0; d--) {
		    if (jumps[a][d] != jumps[b][d]) {
		    	a = jumps[a][d];
		    	b = jumps[b][d];
		    }
		}
		if (a != b) a = jumps[a][0];
		return a;
	}
	
	static void DFS(int node, int parent){
		int lnode = -1;
		int largest = 0;
		
		for (int val: edges[node]){
			if (val == parent) continue;
			DFS(val, node);
			
			if (largest < size[val]){
				lnode = val;
				largest = size[val];
			}
		}
		
		// if (node == 1) return;
		for (int val: edges[node]){
			if (val == parent || val == lnode) continue;
			
			ArrayList<Integer> l1 = new ArrayList<Integer>();
			ArrayList<Integer> l2 = new ArrayList<Integer>();
			
			for (Entry<Integer, Integer> a: tm[val].entrySet()){
				l1.add(a.getKey());
				l2.add(a.getValue());
			}
			
			if (lnode == -1) continue;
			
			int s = l1.size();
			for (int i = 0; i < s; i++){
				size[lnode] += l2.get(i);
				
				if (tm[lnode].containsKey(l1.get(i))){
					tm[lnode].put(l1.get(i), tm[lnode].get(l1.get(i)) + l2.get(i));
				}
				else {
					tm[lnode].put(l1.get(i), l2.get(i));
				}
			}
		}
		
		if (lnode != -1) {
			tm[node] = tm[lnode];
			size[node] = size[lnode];
		}
		
		for (int val: active[node]){
			if (tm[node].containsKey(val)){
				tm[node].put(val, tm[node].get(val) + 1);
			}
			else {
				tm[node].put(val, 1);
			}
			size[node]++;
		}
		
		for (int val: deactivate[node]){
			int s = tm[node].get(val);
			size[node] -= 2;
			
			if (s == 2) tm[node].remove(val);
			else tm[node].put(val, s - 2);
		}
		
		if (size[node] == 0) answers[above[node]] = -1;
		else answers[above[node]] = tm[node].firstKey();
		
		if (node == 32634) System.out.println(above[node]);
	}
	
	static void DFS2(int node, int parent){
		for (int val: edges[node]){
			if (val == parent) continue;
			depth[val] = depth[node] + 1;	
			DFS2(val, node);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("disrupt.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("disrupt.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		tm = new TreeMap[N + 1];
		
		edges = new ArrayList[N + 1];
		active = new ArrayList[N + 1];
		deactivate = new ArrayList[N + 1];
		
		above = new int[N + 1];
		depth = new int[N + 1];
		size = new int[N + 1];
		
		track = new int[N][2];
		answers = new int[N];
		jumps = new int[N + 1][16];
		
		for (int i = 0; i <= N; i++){
			edges[i] = new ArrayList<Integer>();
			active[i] = new ArrayList<Integer>();
			deactivate[i] = new ArrayList<Integer>();
			tm[i] = new TreeMap<Integer, Integer>();
		}
		
		for (int i = 1; i < N; i++){
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			edges[x].add(y);
			edges[y].add(x);
			
			track[i][0] = x;
			track[i][1] = y;
		}
		
		depth[1] = 1;
		DFS2(1, 0);
		
		for (int i = 1; i < N; i++){
			if (depth[track[i][0]] < depth[track[i][1]]){
				above[track[i][1]] = i;
			}
			else {
				above[track[i][0]] = i;
			}
		}
		
		Arrays.fill(jumps[1], 1);
		for (int i = 1; i < N; i++){
			if (depth[track[i][0]] < depth[track[i][1]]){
				jumps[track[i][1]][0] = track[i][0];
			}
			else {
				jumps[track[i][0]][0] = track[i][1];
			}
		}
		
		for (int i = 1; i < 16; i++){
			for (int k = 2; k <= N; k++){
				jumps[k][i] = jumps[jumps[k][i - 1]][i - 1];
			}
		}
		
		for (int i = 0; i < M; i++){
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			deactivate[LCA(x, y)].add(e);
			active[x].add(e);
			active[y].add(e);
		}
		
		DFS(1, 0);
		for (int i = 1; i < N; i++) pw.println(answers[i]);
		
		pw.close();
		br.close();
	}
}