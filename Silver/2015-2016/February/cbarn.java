import java.io.*;
import java.util.*;

public class cbarn {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cbarn.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));
		int N = Integer.parseInt(br.readLine());
		long[] original = new long[N + 1];
		long[] rot = new long[N + 1];
		
		int count = 0;
		for (int i = 1; i < N + 1; i++){
			rot[i] = (long) i - 1;
			int a = Integer.parseInt(br.readLine());
			for (int k = 0; k < a; k++){
				count++;
				original[count] = (long) i;
			}
		}
		
		long min = Long.MAX_VALUE;
		for (int i = 0; i < N; i++){
			long temp = 0;
			for (int k = 1; k < N + 1; k++){
				rot[k] = (rot[k] % N) + 1;
				if (rot[k] >= original[k]){
					temp = temp + (rot[k] - original[k]) * (rot[k] - original[k]);
				}
				else {
					temp = temp + (N + rot[k] - original[k]) * (N + rot[k] - original[k]);
				}
			}
			min = Math.min(min, temp);
		}
		
		pw.println(min);
		pw.close();
		br.close();
	}
}
