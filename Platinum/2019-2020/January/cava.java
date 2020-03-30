import java.io.*;
import java.util.*;

public class cave {
	
	static int mod = 1000000007;
	static int[] parent;
	static int[] height;
	static long[] use;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cave.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cave.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		// height of thing, then column
		String[][] data = new String[r][c];
		
		for (int h = r - 1; h >= 0; h--){
			String s = br.readLine();
			for (int k = 0; k < c; k++){
				data[h][k] = s.substring(k, k + 1);
			}
		}
		
		parent = new int[r * c];
		use = new long[r * c];
		
		for (int i = 0; i < r * c; i++) parent[i] = i;
		for (int i = 0; i < r * c; i++) use[i] = 1;
		
		long tot = 1;
		int[][] move = {{0, -1}, {0, 1}, {-1, 0}};
		
		for (int h = 1; h < r - 1; h++){
			for (int k = 1; k < c - 1; k++){
				if (data[h][k].equals("#")) continue;
				
				for (int t = 0; t < 3; t++){
					int th = h + move[t][0];
					int tc = k + move[t][1];
					
					if (data[th][tc].equals("#")) continue;
					Union(h * c + k, th * c + tc);
				}
			}
			
			TreeSet<Integer> ts = new TreeSet<Integer>();
			
			for (int k = 1; k < c - 1; k++){
				if (data[h][k].equals("#")) continue;
				int f = Find(h * c + k);
				if (ts.contains(f)) continue;
				
				ts.add(f);
				use[f] = (use[f] + 1) % mod;
 			}
		}
		
		TreeSet<Integer> ts = new TreeSet<Integer>();
		
		for (int h = 1; h < r - 1; h++){
			for (int k = 1; k < c - 1; k++){
				if (data[h][k].equals("#")) continue;
				int f = Find(h * c + k);
				if (ts.contains(f)) continue;
				
				ts.add(f);
				tot = (tot * use[f]) % mod;
			}
		}
		
		pw.println(tot);
		pw.close();
		br.close();
	}
	
	public static int Find(int curr) {
		return parent[curr] == curr ? curr : (parent[curr] = Find(parent[curr]));
	}
	
	public static void Union(int a, int b){
		int ma = Find(a);
		int mb = Find(b);
		if (ma == mb) return;
		
		use[ma] = (use[mb] * use[ma]) % mod;
		parent[mb] = ma;
	}
}
