import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class div7 {
	
	static int[] nums;
	static int N;
	static int[] sums;
	
	public static boolean Sums(int length){
		if (length == 0){
			return true;
		}
		if (sums[length - 1] % 7 == 0){
			return true;
		}
		for (int i = length; i < N; i++){
			if ( (sums[i] - sums[i - length]) % 7 == 0){
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("div7.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("div7.out")));
		N = Integer.parseInt(st.nextToken());
		nums = new int[N];
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			nums[i] = Integer.parseInt(st.nextToken()) % 7;
		}
		sums = new int[N];
		sums[0] = nums[0];
		for (int i = 1; i < N; i++){
			sums[i] = (sums[i - 1] + nums[i]) % 7;
		}
		
		boolean unfound = true;
		int l = N;
		while (unfound){
			if (Sums(l)){
				break;
			}
			else {
				l--;
			}
		}
		
		pw.println(l);
		pw.close();
		br.close();
	}
}
