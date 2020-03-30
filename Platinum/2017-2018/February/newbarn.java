import java.io.*;
import java.util.*;

public class newbarn {
	
	static int[] depth;
	static int[][] jumps;
	
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
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("newbarn.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("newbarn.out")));
		
		int N = Integer.parseInt(st.nextToken());
		jumps = new int[N + 1][17];
		depth = new int[N + 1];
		
		int[] top = new int[N + 1];
		int[][] diams = new int[N + 1][2];
		int count = 0;
		
		
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			if (st.nextToken().equals("B")){
				count++;
				int connect = Integer.parseInt(st.nextToken());
				
				if (connect == -1){
					depth[count] = 0;
					top[count] = count;
					diams[count][0] = count;
					diams[count][1] = count;
					jumps[count][0] = count;
				}
				else {
					depth[count] = depth[connect] + 1;
					top[count] = top[connect];
					jumps[count][0] = connect;
				}
				for (int k = 1; k < 17; k++) jumps[count][k] = jumps[jumps[count][k - 1]][k - 1];
				int d = depth[diams[top[count]][0]] + depth[diams[top[count]][1]] - 
						2 * depth[LCA(diams[top[count]][0], diams[top[count]][1])];
				
				int d1 = depth[count] + depth[diams[top[count]][0]] - 2 * depth[LCA(count, diams[top[count]][0])];
				int d2 = depth[count] + depth[diams[top[count]][1]] - 2 * depth[LCA(count, diams[top[count]][1])];
				if (d1 > d) diams[top[count]][1] = count;
				else if (d2 > d) diams[top[count]][0] = count;
			}
			else {
				int q = Integer.parseInt(st.nextToken());
				int d1 = depth[q] + depth[diams[top[q]][0]] - 2 * depth[LCA(q, diams[top[q]][0])];
				int d2 = depth[q] + depth[diams[top[q]][1]] - 2 * depth[LCA(q, diams[top[q]][1])];
				pw.println(Math.max(d1, d2));
			}
		}
		
		int ans = 0;
		for (int i = 1; i <= 200; i++){
			for (int k = 1; k <= 200; k++){
				ans += (201 - i) * (201 - k);
			}
		}
		
		br.close();
		pw.close();
	}
}
