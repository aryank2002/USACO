import java.io.*;
import java.util.*;

public class cardgame {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cardgame.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cardgame.out")));
		
		int N = Integer.parseInt(br.readLine());
		int[] hi = new int[N + 1];
		int[] lo = new int[N + 1];
		
		int[] elsie = new int[N + 1];
		boolean[] num = new boolean[2 * N + 1];
		
		for (int i = 1; i <= N; i++){
			num[elsie[i] = Integer.parseInt(br.readLine())] = true;
		}
		
		TreeSet<Integer> t1 = new TreeSet<Integer>();
		TreeSet<Integer> t2 = new TreeSet<Integer>();
		
		for (int i = 1; i <= 2 * N; i++){
			if (num[i] == false){
				t1.add(i);
				t2.add(i);
			}
		}
		
		for (int i = 1; i <= N; i++){
			if (t1.ceiling(elsie[i]) != null){
				hi[i]++;
				t1.remove(t1.ceiling(elsie[i]));
			}
			hi[i] += hi[i - 1];
		}
		
		for (int i = 1; i <= N; i++){
			if (t2.floor(elsie[N + 1 - i]) != null){
				lo[i]++;
				t2.remove(t2.floor(elsie[N + 1 - i]));
			}
			lo[i] += lo[i - 1];
		}
		
		int max = 0;
		for (int i = 0; i <= N; i++) max = Math.max(max, hi[i] + lo[N - i]);
		
		pw.println(max);
		pw.close();
		br.close();
	}
}
