import java.io.*;
import java.util.*;

public class fortmoo {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("fortmoo.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fortmoo.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		String[][] data = new String[M][N];
		for (int i = N - 1; i >= 0; i--){
			String s = br.readLine();
			for (int k = 0; k < M; k++){
				data[k][i] = s.substring(k, k + 1);
			}
		}
		
		int[][] top = new int[M][N];
		int[][] right = new int[M][N];
		
		for (int x = 0; x < M; x++){
			if (data[x][N - 1].equals(".")) top[x][N - 1] = 1;
			for (int y = N - 2; y > -1; y--){
				if (data[x][y].equals(".")) top[x][y] = top[x][y + 1] + 1;
			}
		}
		
		for (int y = 0; y < N; y++){
			if (data[M - 1][y].equals(".")) right[M - 1][y] = 1;
			for (int x = M - 2; x > -1; x--){
				if (data[x][y].equals(".")) right[x][y] = right[x + 1][y] + 1;
			}
		}
		
		int ans = 0;
		for (int len = 1; len <= M; len++){
			for (int hi = 1; hi <= N; hi++){
				boolean temp = false;
				for (int x = 0; x <= M - len; x++){
					for (int y = 0; y <= N - hi; y++){
						if (top[x][y] >= hi && right[x][y] >= len && right[x][y + hi - 1] >= len && top[x + len - 1][y] >= hi){
							temp = true;
							break;
						}
					}
					if (temp) break;
				}
				if (temp) ans = Math.max(ans, len * hi);
			}
		}
		
		pw.println(ans);
		br.close();
		pw.close();
	}
}
