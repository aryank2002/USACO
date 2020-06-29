import java.util.*;
import java.io.*;

public class teamwork {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("teamwork.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("teamwork.out")));
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] vals = new int[N + 1];
		for (int i = 1; i <= N; i++){
			vals[i] = Integer.parseInt(br.readLine());
		}
		
		int[] sums = new int[N + 1];
		for (int i = 1; i <= N; i++){
			int maxsum = 0;
			int maxval = vals[i];
			for (int k = i; k >= Math.max(1, i - K + 1); k--){
				maxval = Math.max(maxval, vals[k]);
				maxsum = Math.max(sums[k - 1] + (i - k + 1) * maxval, maxsum);
			}
			sums[i] = maxsum;
		}
		
		pw.println(sums[N]);
		pw.close();
		br.close();
	}
}
