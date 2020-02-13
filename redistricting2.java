import java.io.*;
import java.util.*;

public class redistricting2 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("redistricting.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("redistricting.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] prefix = new int[N + 1];
		String s = br.readLine();
		
		for (int i = 1; i <= N; i++){
			if (s.substring(i - 1, i).equals("H")) prefix[i] = 1;
			else prefix[i] = -1;
			prefix[i] += prefix[i - 1];
		}
		
		int[] dp = new int[N + 1];
		TreeMap<Integer, Integer> mindp = new TreeMap<Integer, Integer>();
		TreeMap<Integer, Integer>[] minprefix = new TreeMap[N + 1];
		
		for (int i = 0; i <= N; i++) minprefix[i] = new TreeMap<Integer, Integer>();
		mindp.put(0, 1);
		minprefix[0].put(0, 1);
		
		for (int i = 1; i <= N; i++){
			int min = mindp.firstKey();
			if (minprefix[min].firstKey() < prefix[i]) dp[i] = min;
			else dp[i] = min + 1;
			
			if (mindp.containsKey(dp[i])){
				mindp.put(dp[i], mindp.get(dp[i]) + 1);
			}
			else {
				mindp.put(dp[i], 1);
			}
			if (minprefix[dp[i]].containsKey(prefix[i])){
				minprefix[dp[i]].put(prefix[i], minprefix[dp[i]].get(prefix[i]) + 1);
			}
			else {
				minprefix[dp[i]].put(prefix[i], 1);
			}
			
			if (i >= K){
				if (mindp.get(dp[i - K]) == 1) mindp.remove(dp[i - K]);
				else mindp.put(dp[i - K], mindp.get(dp[i - K]) - 1);
				
				if (minprefix[dp[i - K]].get(prefix[i - K]) == 1) minprefix[dp[i - K]].remove(prefix[i - K]);
				else minprefix[dp[i - K]].put(prefix[i - K], minprefix[dp[i - K]].get(prefix[i - K]) - 1);
			}
		}
		
		pw.println(dp[N]);
		br.close();
		pw.close();
	}
}