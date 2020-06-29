import java.io.*;
import java.util.*;

public class convention {
	
	static int[] times;
	static int M;
	static int C;
	static int N;

	public static boolean Fits(int space){
		PriorityQueue<Integer> nums = new PriorityQueue<Integer>();
		for (int val: times){
			nums.add(val);
		}
		int i = 0;
		while (i < M && !nums.isEmpty()){
			i++;
			int num1 = nums.poll();
			int size = 1;
			while (size < C && !nums.isEmpty() && nums.peek() - num1 <= space){
				size++;
				nums.poll();
			}
		}
			
		if (nums.isEmpty()){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("convention.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("convention.out")));
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		times = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++){
			times[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(times);
		
		int min = 1;
		int max = times[N - 1] - times[0];
		while (min != max){
			int mid = (min + max) / 2;
			if (Fits(mid)){
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
