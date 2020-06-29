import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class p248 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("248.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("248.out")));
		
		int N = Integer.parseInt(br.readLine());
		int max = 0;
		
		int[][] dp = new int[N + 1][60];
		int[] vals = new int[N + 1];
		
		for (int i = 1; i <= N; i++){
			vals[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, vals[i]);
			dp[i][vals[i]] = i;
		}
		
		for (int i = 1; i < 60; i++){
			for (int k = 1; k <= N; k++){
				if (dp[k][i - 1] != 0 && dp[k][i - 1] != N && dp[dp[k][i - 1] + 1][i - 1] != 0){
					dp[k][i] = dp[dp[k][i - 1] + 1][i - 1];
					max = Math.max(max, i);
				}
			}
		}
		
		pw.println(max);
		br.close();
		pw.close();
	}
}
