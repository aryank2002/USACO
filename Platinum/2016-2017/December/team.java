import java.util.*;
import java.io.*;

public class team {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("team.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("team.out")));
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		long[] fj = new long[N + 1];
		long[] fp = new long[M + 1];
		long mod = 1000000009;
		
		fj[0] = Long.MIN_VALUE / 2;
		fj[0] = Long.MIN_VALUE / 2;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++){
			fj[i] = Long.parseLong(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++){
			fp[i] = Long.parseLong(st.nextToken());
		}
		
		Arrays.sort(fj);
		Arrays.sort(fp);
		
		int j = 0;
		int p = 0;
		long[] less = new long[N + 1];
		
		for (int i = 0; i < N + M; i++){
			if (j == N) break;
			else if (p == M) {
				j++;
				less[j] = M;
			}
			else {
				if (fj[j + 1] > fp[p + 1]){
					p++;
				}
				else {
					j++;
					less[j] = p;
				}
			}
		}
		
		long[][][] dp = new long[K + 1][N + 1][M + 1];
		for (int i = 1; i <= N; i++){
			for (int k = 1; k <= M; k++){
				if (fj[i] > fp[k]) dp[1][i][k] = 1L;
			}
		}
		
		for (int k = 2; k <= K; k++){
			long[][] prefix = new long[N + 1][M + 1];
			prefix[1][1] = dp[k - 1][1][1];
			for (int x = 2; x <= N; x++){
				prefix[x][1] = prefix[x - 1][1] + dp[k - 1][x][1];
			}
			for (int x = 2; x <= M; x++){
				prefix[1][x] = prefix[1][x - 1] + dp[k - 1][1][x];
			}
			
			for (int x = 2; x <= N; x++){
				for (int y = 2; y <= M; y++){
					prefix[x][y] = (prefix[x - 1][y] + prefix[x][y - 1] - prefix[x - 1][y - 1] + dp[k - 1][x][y]) % mod;
					if (prefix[x][y] < 0) prefix[x][y] += (long) mod;
				}
			}
			
			for (int n = k; n <= N; n++){
				for (int m = k; m <= less[n]; m++){
					dp[k][n][m] = prefix[n - 1][m - 1];
				}
			}
		}
		
		long answer = 0;
		for (int i = 1; i <= N; i++){
			for (int k = 1; k <= less[i]; k++){
				answer = (answer + dp[K][i][k]) % mod;
			}
		}
		
		pw.println(answer);
		pw.close();
		br.close();
	}
}
