import java.io.*;
import java.util.*;

public class subrev {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("subrev.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("subrev.out")));
		
		int N = Integer.parseInt(br.readLine());
		int[] a = new int[N + 1];
		for (int i = 1; i <= N; i++) a[i] = Integer.parseInt(br.readLine());
		
		int[][][][] dp = new int[N + 1][N + 1][51][51];
		
		for (int i = 1; i <= N; i++) for (int t = a[i]; t <= 50; t++) for (int b = 1; b <= a[i]; b++) dp[i][i][b][t] = 1;
		
		for (int t = 1; t <= 50; t++){
			for (int b = t; b >= 1; b--){
				for (int len = 2; len <= N; len++){
					for (int l = 1; l <= N + 1 - len; l++){
						int r = l + len - 1;
						int max = Math.max(0, dp[l + 1][r - 1][b][t]);
						
						if (a[r] >= b && a[r] <= t) max = Math.max(max, 1 + dp[l + 1][r - 1][a[r]][t]);
						if (a[l] >= b && a[l] <= t) max = Math.max(max, 1 + dp[l + 1][r - 1][b][a[l]]);
						if (a[r] >= b && a[r] <= t && a[l] >= b && a[l] <= t && a[r] <= a[l]) max = Math.max(max, 2 + dp[l + 1][r - 1][a[r]][a[l]]);
						
						max = Math.max(max, Math.max(dp[l + 1][r][b][t], dp[l][r - 1][b][t]));
						if (a[l] >= b && a[l] <= t) max = Math.max(max, 1 + dp[l + 1][r][a[l]][t]);
						if (a[r] >= b && a[r] <= t) max = Math.max(max, 1 + dp[l][r - 1][b][a[r]]);
						
						dp[l][r][b][t] = max;
					}
				}
			}
		}
		
		pw.println(dp[1][N][1][50]);
		pw.close();
		br.close();
	}
}
