import java.io.*;
import java.util.*;

public class snowcow {
	
	static ArrayList<Integer>[] edge;
	static int[] pre;
	static int[] size;
	static int count = 0;
	static TreeMap<Integer, Integer>[] delist = new TreeMap[100001];
	static ArrayList<State>[] add;
	static ArrayList<Integer>[] rem;
	static boolean[] never;
	
	static Segment[] segments = new Segment[400000 + 1];
	
	static void propagate (int node, int left, int right) {
	    segments[node].max += (long) (right - left + 1) * segments[node].lazy;
	    if (left < right) {
	        segments[2 * node + 1].lazy += segments[node].lazy;
	        segments[2 * node + 2].lazy += segments[node].lazy;
	    }
	    segments[node].lazy = 0;
	}

	static void update (int node, int left, int right, int x, int y, long value) {
	    propagate (node, left, right);
	    if (x <= left && right <= y) segments[node].lazy += value;
	    else {
	        int middle = (left + right) / 2;
	        if (x <= middle) update (2 * node + 1, left, middle, x, y, value);
	        if (y >= middle + 1) update (2 * node + 2, middle + 1, right, x, y, value);
	        
	        propagate (2 * node + 1, left, middle);
	        propagate (2 * node + 2, middle + 1, right);
	        segments[node].max = segments[2 * node + 1].max + segments[2 * node + 2].max;
	    }
	}
	
	static long query (int node, int left, int right, int x, int y) {
	    propagate (node, left, right);
	    if (x <= left && right <= y) return segments[node].max;
	    else {
	        long sum = 0;
	        int middle = (left + right) / 2;
	        if (x <= middle) sum = sum + (long) query (2 * node + 1, left, middle, x, y);
	        if (y >= middle + 1) sum = sum + (long) query (2 * node + 2, middle + 1, right, x, y);
	        return sum;
	    }
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("snowcow.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("snowcow.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i <= 400000; i++){
			segments[i] = new Segment(0, 0);
		}
		
		pre = new int[N + 1];
		size = new int[N + 1];
		rem = new ArrayList[Q];
		
		edge = new ArrayList[N + 1];
		never = new boolean[Q];
		add = new ArrayList[N + 1];
		
		for (int i = 0; i <= N; i++) {
			edge[i] = new ArrayList<Integer>();
			add[i] = new ArrayList<State>();
		}
		
		for (int i = 0; i <= 100000; i++) delist[i] = new TreeMap<Integer, Integer>();
		for (int i = 0; i < Q; i++) rem[i] = new ArrayList<Integer>();
		
		for (int i = 1; i < N; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			edge[a].add(b);
			edge[b].add(a);
		}
		
		DFS(1, 1);
		int[][] quer = new int[Q][3];
		
		for (int i = 0; i < Q; i++){
			st = new StringTokenizer(br.readLine());
			quer[i][0] = Integer.parseInt(st.nextToken());
			if (quer[i][0] == 1){
				quer[i][1] = Integer.parseInt(st.nextToken());
				quer[i][2] = Integer.parseInt(st.nextToken());
				
				add[quer[i][1]].add(new State(i, quer[i][2]));
			}
			else {
				quer[i][1] = Integer.parseInt(st.nextToken());
			}
		}
		
		DFS2(1, 1);
		
		for (int i = 0; i < Q; i++){
			for (int val: rem[i]) update(0, 1, N, pre[quer[val][1]] - size[quer[val][1]] + 1, pre[quer[val][1]], -1);
			
			if (quer[i][0] == 1){
				if (never[i]) continue;
				update(0, 1, N, pre[quer[i][1]] - size[quer[i][1]] + 1, pre[quer[i][1]], 1);
			}
			else pw.println(query(0, 1, N, pre[quer[i][1]] - size[quer[i][1]] + 1, pre[quer[i][1]]));
		}
		
		pw.close();
		br.close();
	}
	
	static void DFS2(int node, int par){
		for (State s: add[node]){
			delist[s.color].put(s.time, 1);
		}
		
		for (State s: add[node]){
			if (delist[s.color].firstKey() < s.time){
				never[s.time] = true;
				continue;
			}
			if (delist[s.color].ceilingKey(s.time + 1) != null){
				rem[delist[s.color].ceilingKey(s.time + 1)].add(s.time);
			}
		}
		
		for (int val: edge[node]){
			if (val == par) continue;
			DFS2(val, node);
		}
		
		for (State s: add[node]){
			delist[s.color].remove(s.time);
		}
	}
	
	static int DFS(int node, int par){
		size[node] = 1;
		
		for (int val: edge[node]){
			if (val == par) continue;
			size[node] += DFS(val, node);
		}
		
		count++;
		pre[node] = count;
		return size[node];
	}
	
	static class Segment {
	    public long max, lazy;
	    public Segment (long a, long b){
	    	max = a;
	    	lazy = b;
	    }
	} 
	
	static class State {
		public int time, color;
		public State(int a, int b){
			time = a;
			color = b;
		}
	}
}
