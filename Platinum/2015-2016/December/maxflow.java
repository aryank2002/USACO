import java.util.*;
import java.io.*;

public class maxflow {
	
	static Segment[] segments;
	static int N;
	static ArrayList<Integer>[] edges;
	static int[] segment;
	static int[] vertex;
	static int[] sizes;
	static int[] depth;
	static int[][] jumps;
	static int[] topchain;
	static int id = 0;
	
	static void propagate (int node, int left, int right) {
	    segments[node].max += segments[node].lazy;
	    if (left < right) {
	        segments[2 * node + 1].lazy += segments[node].lazy;
	        segments[2 * node + 2].lazy += segments[node].lazy;
	    }
	    segments[node].lazy = 0;
	}

	static void update (int node, int left, int right, int x, int y, int value) {
	    propagate (node, left, right);
	    if (x <= left && right <= y) {
	        segments[node].lazy += value;
	    } 
	    else {
	        int middle = (left + right) / 2;
	        if (x <= middle) {
	            update (2 * node + 1, left, middle, x, y, value);
	        }
	        if (y >= middle + 1) {
	            update (2 * node + 2, middle + 1, right, x, y, value);
	        }
	        propagate (2 * node + 1, left, middle);
	        propagate (2 * node + 2, middle + 1, right);
	        segments[node].max = Math.max(segments[2 * node + 1].max, segments[2 * node + 2].max);
	    }
	}
	
	static int query (int node, int left, int right, int x, int y) {
	    propagate (node, left, right);
	    if (x <= left && right <= y) {
	        return segments[node].max;
	    } 
	    else {
	        int sum = 0;
	        int middle = (left + right) / 2;
	        if (x <= middle) {
	            sum = Math.max(sum, query (2 * node + 1, left, middle, x, y));
	        }
	        if (y >= middle + 1) {
	            sum = Math.max(sum, query (2 * node + 2, middle + 1, right, x, y));
	        }
	        return sum;
	    }
	}
	
	static void size (int cur, int par) {
		sizes[cur]++;
		for (int val: edges[cur]){
			if (val == par) continue;
			depth[val] = depth[cur] + 1;
			jumps[val][0] = cur;
			size (val, cur);
			sizes[cur] += sizes[val];
		}
	}
	
	static void HLD (int cur, int par, int top) {
		id++;
		vertex[cur] = id;
		topchain[cur] = top;
		int lchild = -1;
		int lsize = -1;
		
		for (int temp: edges[cur]) {
			if (temp == par) continue;
			if (lsize < sizes[temp]) {
				lsize = sizes[temp];
				lchild = temp;
			}
		}
		if (lsize < 0) return;
		HLD (lchild, cur, top);
		
		for (int temp: edges[cur]) {
			if (temp == par || temp == lchild) continue;
			HLD (temp, cur, temp);
		}
	}
	
	static int LCA (int a, int b) {
		if (depth[a] > depth[b]) {
			int c = a;
			a = b;
			b = c;
		}
		int d = depth[b] - depth[a];
		while (d != 0){
			int inc = d & -d;
			int log = (int) (Math.log10(inc) / Math.log10(2));
			b = jumps[b][log];
			d -= inc;
		}
		for (d = 16; d >= 0; d--) {
		    if (jumps[a][d] != jumps[b][d]) {
		    	a = jumps[a][d];
		    	b = jumps[b][d];
		    }
		}
		if (a != b) a = jumps[a][0];
		return a;
	}
	
	static void path (int child, int par){
		while (child != par) {
			if (depth[topchain[child]] > depth[par]) {
				update(0, 1, N, vertex[topchain[child]], vertex[child], 1);
			    child = jumps[topchain[child]][0];
			}
			else {
				update(0, 1, N, vertex[par] + 1, vertex[child], 1);
				break;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("maxflow.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("maxflow.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		sizes = new int[100001];
		
		depth = new int[100001];
		edges = new ArrayList[100001];
		
		segment = new int[400000 + 1];
		vertex = new int[100001];
		segments = new Segment[400001];
		
		for (int i = 0; i <= 400000; i++){
			segments[i] = new Segment(0, 0);
		}
		for (int i = 1; i <= N; i++){
			edges[i] = new ArrayList<Integer>();
		}
		
		int a, b;
		for (int i = 1; i < N; i++){
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			edges[a].add(b);
			edges[b].add(a);
		}
		
		depth[1] = 1;
		jumps = new int[100001][17];
		jumps[1][0] = 1;
		topchain = new int[100001];
		
		size (1, -1);
		HLD (1, -1, 1);
		
		for (int i = 1; i < 17; i++){
			for (int k = 1; k < 100001; k++){
				jumps[k][i] = jumps[jumps[k][i - 1]][i - 1];
			}
		}
		
		for (int i = 0; i < K; i++){
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			int l = LCA (a, b);
			
			path(a, l);
			path(b, l);
			update(0, 1, N, vertex[l], vertex[l], 1);
		}
		pw.println(query(0, 1, N, 1, N));
		br.close();
		pw.close();
	}
	
	static class Segment {
	    public int max, lazy;
	    public Segment (int a, int b){
	    	max = a;
	    	lazy = b;
	    }
	} 
}
