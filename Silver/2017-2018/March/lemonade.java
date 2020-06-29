import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class lemonade {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("lemonade.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lemonade.out")));
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] nums = new int[N];
		for (int i = 0; i < N; i++){
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);
		int counter = 0;
		for (int i = N - 1; i >= 0; i--){
			if (counter <= nums[i]){
				counter++;
			}
		}
		
		pw.println(counter);
		br.close();
		pw.close();
	}
}
