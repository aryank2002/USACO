import java.io.*;
import java.util.*;

public class sprinklers {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("sprinklers.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sprinklers.out")));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		long[] bad = new long[N];
		long[] top = new long[N];
		long mod = 1000000007;
		
		long[] ys = new long[N];
		long[] left = new long[N];
		long[] pre = new long[N];
		
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			ys[Integer.parseInt(st.nextToken())] = Long.parseLong(st.nextToken());
		}
		
		int height = N;
		long ans = 0;
		
		for (int i = 0; i < N; i++) while (height > ys[i]) left[--height] = i;
		top[N - 1] = ys[N - 1];
		pre[N - 1] = bad[N - 1] = left[N - 1];
		
		for (int i = N - 2; i >= 0; i--){
			pre[i] = (pre[i + 1] + left[i]) % mod;
			bad[i] = (bad[i + 1] + (long) (N - i) * left[i]) % mod;
			top[i] = Math.max(top[i + 1], ys[i]);
		}
		
		long bot = N - 1;
		for (int i = 1; i < N; i++){
			while (bot > 0 && left[(int) bot - 1] <= i) bot--;
			long total = (((top[i] - bot) * (top[i] + 1 - bot)) / 2) % mod;
			
			total = (total * i) % mod;
			ans = (ans + total) % mod;
			
			long no = (bad[(int) bot] - bad[(int) top[i]] - (long) (N - top[i]) * (pre[(int) bot] - pre[(int) top[i]])) % mod;
			ans = (ans + mod - no) % mod;
		}
		
		pw.println(ans);
		pw.close();
		br.close();
	}
}