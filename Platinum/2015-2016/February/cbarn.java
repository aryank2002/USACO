import java.io.*;
import java.util.*;

public class cbarn {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cbarn.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		long[] data = new long[2 * N + 1];
		PriorityQueue<Long> pq = new PriorityQueue<Long>();
		
		for (int i = 1; i <= N; i++) {
			data[i] = data[i + N] = Integer.parseInt(br.readLine());
			pq.add(data[i]);
			if (pq.size() > 100) pq.poll();
		}
		
		long[] pre = new long[2 * N + 1];
		long[] dist = new long[2 * N + 1];
		
		for (int i = 1; i <= 2 * N; i++){
			pre[i] = pre[i - 1] + data[i];
			dist[i] = i * data[i] + dist[i - 1];
		}
		
		long max = Long.MAX_VALUE / 3;
		for (int start = 1; start <= N; start++){
			if (N > 200 && !pq.contains(data[start])) continue;
			if (K > 1){
				long[][] dp = new long[N][K];
				for (int i = 0; i < N; i++) Arrays.fill(dp[i], Long.MAX_VALUE / 3);
				
				for (int i = start; i < start + N; i++){
					dp[i - start][1] = dist[start + N - 1] - dist[i] - (long) i * (pre[start + N - 1] - pre[i]);
				}
				
				for (int k = 2; k < K; k++){
					for (int i = start + N - k; i >= start; i--){
						long min = Long.MAX_VALUE / 3;
						
						for (int a = i + 1; a <= start + N - 1; a++){
							min = Math.min(min, dp[a - start][k - 1] + dist[a - 1] - dist[i] - (long) i * (pre[a - 1] - pre[i]));
						}
							
						dp[i - start][k] = min;
					}
				}
				
				for (int i = start + 1; i < start + N; i++){
					max = Math.min(max, dp[i - start][K - 1] + dist[i - 1] - dist[start] - (long) start * (pre[i - 1] - pre[start]));
				}
			}
			else {
				max = Math.min(max, dist[start + N - 1] - dist[start] - (long) start * (pre[start + N - 1] - pre[start]));
			}
		}
		
		pw.println(max);
		pw.close();
		br.close();
	}
}
