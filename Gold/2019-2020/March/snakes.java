import java.io.*;
import java.util.*;

public class snakes {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("snakes.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("snakes.out")));
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] dp = new int[N + 1][K + 1];
		
		int[] data = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++){
			data[i] = Integer.parseInt(st.nextToken());
		}
		
		int max = 0;
		int sum = 0;
		for (int i = 1; i <= N; i++){
			max = Math.max(data[i], max);
			sum += data[i];
			dp[i][0] = max * i - sum;
		}
		
		for (int i = 1; i <= N; i++){
			for (int k = 1; k <= K; k++){
				int tmax = 0;
				int tsum = 0;
				dp[i][k] = 1000000000;
				for (int a = i; a > 0; a--){
					tmax = Math.max(tmax, data[a]);
					tsum += data[a];
					dp[i][k] = Math.min(dp[i][k], dp[a - 1][k - 1] - tsum + tmax * (i - a + 1));
				}
			}
		}
		
		int min = 1000000000;
		for (int i = 0; i <= K; i++){
			min = Math.min(min, dp[N][i]);
		}
		
		pw.println(min);
		br.close();
		pw.close();
	}
}
