import java.util.*;
import java.io.*;

public class greedy {
	
	static int N;
	static int[] jumps;
	
	static boolean Checker(int K){
		int[] num = new int[N + 1];
		for (int i = 1; i < K; i++){
			num[jumps[i]]++;
		}
		int total = 0;
		for (int i = 1; i <= N; i++){
			total += num[i];
			if (total >= i) return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("greedy.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("greedy.out")));
		
		N = Integer.parseInt(st.nextToken());
		jumps = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++){
			jumps[i + 1] = N - Integer.parseInt(st.nextToken());
		}
		
		int lo = 2;
		int hi = N;
		while (lo != hi){
			int mid = (lo + hi) / 2;
			if (Checker(mid)){
				hi = mid;
			}
			else {
				lo = mid + 1;
			}
		}
		
		pw.println(N - lo + 1);
		br.close();
		pw.close();
	}
}
