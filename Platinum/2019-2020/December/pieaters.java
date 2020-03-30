import java.io.*;
import java.util.*;

public class pieaters {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("pieaters.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pieaters.out")));
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[N + 2][N + 2];
		int[][][] max = new int[N + 1][N + 2][N + 2];
		
		for (int i = 0; i < M; i++){
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			dp[l][r] = w;
		}
		
		for (int i = 1; i <= N; i++){
			for (int len = 1; len <= N; len++){
				for (int m = 1; m <= i; m++){
					if (len + m - 1 > N) continue;
					if (m + len - 1 < i) continue;
					
					max[i][m][m + len - 1] = dp[m][m + len - 1];
					max[i][m][m + len - 1] = Math.max(max[i][m][m + len - 1], max[i][m + 1][m + len - 1]);
					max[i][m][m + len - 1] = Math.max(max[i][m][m + len - 1], max[i][m][m + len - 2]);
				}
			}
		}
		
		for (int len = 2; len <= N; len++)
			for (int k = 1; k <= N - len + 1; k++)
				for (int i = k; i <= k + len - 1; i++) dp[k][k + len - 1] = 
						Math.max(dp[k][k + len - 1], dp[k][i - 1] + dp[i + 1][k + len - 1] + max[i][k][k + len - 1]);
		
		
		pw.println(dp[1][N]);
		pw.close();
		br.close();
	}
}
