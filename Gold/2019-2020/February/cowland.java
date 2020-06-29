import java.util.*;
import java.io.*;

public class cowland {
	
	static int maxd, max;
	static ArrayList<Integer>[] edges;
	static int[] segment;
	static int[] vertex;
	static int[] sizes;
	static int[] depth;
	static int[][] jumps;
	static int[] topchain;
	static int id = 0;
	static int[] val;
	
	static void update (int node, int left, int right, int x, int y) {
	    if (left == right) segment[node] = y;
	    else {
	        int middle = (left + right) / 2;
	        if (x <= middle) update(2 * node + 1, left, middle, x, y);
	        else update(2 * node + 2, middle + 1, right, x, y);
	        segment[node] = segment[2 * node + 1] ^ segment[2 * node + 2];
	    }
	}
	
	static int query (int node, int left, int right, int x, int y) {
	    if (x <= left && right <= y) return segment[node];
	    else {
	    	int answer = 0;
	        int middle = (left + right) / 2;
	        
	        if (x <= middle) answer ^= query(2 * node + 1, left, middle, x, y);
	        if (y >= middle + 1) answer ^= query(2 * node + 2, middle + 1, right, x, y);
	        return answer;
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
		update (0, 1, max, vertex[cur], val[cur]);
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
		
		if(a != b) a = jumps[a][0];
		return a;
	}
	
	static int path (int child, int par) {
		int ans = 0;
		while (child != par) {
			if (depth[topchain[child]] > depth[par]) {
				ans ^= query(0, 1, max, vertex[topchain[child]], vertex[child]);
			    child = jumps[topchain[child]][0];
			}
			else {
				ans ^= query(0, 1, max, vertex[par] + 1, vertex[child]);
				break;
			}
		}
		
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cowland.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowland.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		max = 100001;
		maxd = 17;
		sizes = new int[max];
		
		val = new int[max];
		depth = new int[max];
		st = new StringTokenizer(br.readLine());
		
		edges = new ArrayList[max];
		segment = new int[400000 + 1];
		vertex = new int[max];
		
		for (int i = 1; i <= N; i++){
			val[i] = Integer.parseInt(st.nextToken());
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
		jumps = new int[max][maxd];
		jumps[1][0] = 1;
		topchain = new int[max];
		
		size (1, -1);
		HLD (1, -1, 1);
		
		for (int i = 1; i < maxd; i++){
			for (int k = 1; k < max; k++){
				jumps[k][i] = jumps[jumps[k][i - 1]][i - 1];
			}
		}
		
		for (int i = 0; i < Q; i++){
			st = new StringTokenizer(br.readLine());
			
			if (Integer.parseInt(st.nextToken()) == 1){
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				val[a] = b;
				update (0, 1, max, vertex[a], b);
			}
			
			else {
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				int l = LCA (a, b);
				pw.println(path(a, l) ^ path(b, l) ^ val[l]);
			}
		}
		
		br.close();
		pw.close();
	}
}
