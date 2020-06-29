import java.util.*;
import java.io.*;

public class talent {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("talent.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("talent.out")));
		
		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		int[] weights = new int[N];
		int[] talents = new int[N];
		
		int K = 2000000;
		
		int[] maxtal = new int[K + 1];
		Arrays.fill(maxtal, -1);
		maxtal[0] = 0;
		
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			weights[i] = Integer.parseInt(st.nextToken());
			talents[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++){
			for (int k = K; k >= 0; k--){
				if (maxtal[k] == -1) continue;
				if (k + weights[i] > K) continue;
				maxtal[k + weights[i]] = Math.max(maxtal[k + weights[i]], maxtal[k] + talents[i]);
			}
		}
		
		int max = 0;
		for (int i = W; i <= K; i++){
			max = Math.max(max, (int) 1000 * maxtal[i] / i);
		}
		
		pw.println(max);
		br.close();
		pw.close();
	}
}
