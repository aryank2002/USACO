import java.util.*;
import java.io.*;

public class hayfeast {
	
	static long[] flavor;
	static long[] spice;
	static long N;
	static long M;
	
	static boolean Possible(long spices){
		long count = 0;
		for (int i = 1; i <= N; i++){
			if (spice[i] <= spices){
				count = count + flavor[i];
				if (count >= M){
					return true;
				}
			}
			else {
				count = 0;
			}
		}
		
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("hayfeast.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hayfeast.out")));
		
		N = Long.parseLong(st.nextToken());
		M = Long.parseLong(st.nextToken());
		flavor = new long[(int) N + 1];
		spice = new long[(int) N + 1];
		
		for (int i = 1; i <= N; i++){
			st = new StringTokenizer(br.readLine());
			flavor[i] = Long.parseLong(st.nextToken());
			spice[i] = Long.parseLong(st.nextToken());
		}
		
		long min = 1;
		long max = 1000000000;
		while (min != max){
			long mid = (min + max) / 2;
			if (Possible(mid)){
				max = mid;
			}
			else {
				min = mid + 1;
			}
		}
		
		pw.println(min);
		pw.close();
		br.close();
	}
}
