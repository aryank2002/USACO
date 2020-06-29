import java.util.*;
import java.io.*;

public class cownomics {
	
	static String[] data;
	static int M;
	static int N;
	
	static boolean Checker(int k){
		for (int i = 0; i <= M - k; i++){
			HashSet<String> hash = new HashSet<String>();
			for (int p = 1; p <= N; p++) {
				hash.add(data[p].substring(i, i + k));
			}
			boolean bool = true;
			for (int p = N + 1; p <= 2 * N; p++){
				if (hash.contains(data[p].substring(i, i + k))){
					bool = false;
				}
			}
			if (bool){
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cownomics.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		data = new String[2 * N + 1];
		
		for (int i = 1; i <= 2 * N; i++){
			data[i] = br.readLine();
		}
		
		int min = 1;
		int max = M;
		
		while (min != max){
			int mid = (min + max) / 2;
			if (Checker(mid)){
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
