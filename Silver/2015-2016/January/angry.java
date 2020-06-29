import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class angry {
	
	static int[] cows;
	static int K;
	public static boolean Destroyed(int power){
		PriorityQueue<Integer> nums = new PriorityQueue<Integer>();
		for (int val: cows){
			nums.add(val);
		}
		int t = K;
		while (t != 0 && !nums.isEmpty()){
			t--;
			int a = nums.poll();
			int range = a + 2 * power;
			while (!nums.isEmpty() && nums.peek() <= range){
				nums.poll();
			}
		}
		if (nums.isEmpty()){
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("angry.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		cows = new int[N];
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			cows[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(cows);
		int min = 1; 
		int max = N;
		while (min != max){
			int mid = (min + max) / 2;
			if (Destroyed(mid)){
				max = mid;
			}
			else {
				min = mid + 1;
			}
		}
		
		pw.println(min);
		br.close();
		pw.close();
	}

}
