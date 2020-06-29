import java.util.*;
import java.io.*;

public class walk {
	
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("walk.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("walk.out")));
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		pw.println(2019201997 - 48 * N - 84 * (K - 1));
		br.close();
		pw.close();
	}
	
}
