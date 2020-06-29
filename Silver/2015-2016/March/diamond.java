import java.io.*;
import java.util.*;

public class diamond {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("diamond.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("diamond.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] nums = new int[N + 1];
		int[] setup = new int[N + 1];
		int[] setdown = new int[N + 1];
		
		nums[0] = -2000000000;
		for (int i = 1; i < N + 1; i++){
			nums[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(nums);
		
		for (int i = 1; i < N + 1; i++){
			int index = Arrays.binarySearch(nums, K + nums[i]);
			if (index > 0){
				while (index + 1 <= N && nums[index + 1] == (K + nums[i])){
					index++;
				}
				setup[i] = index - i + 1;
			}
			else {
				setup[i] = - i - index - 1;
			}
			
			index = Arrays.binarySearch(nums, nums[i] - K);
			if (index > 0){
				while (index - 1 > 0 && nums[index - 1] ==  - K + nums[i]){
					index--;
				}
				setdown[i] = i - index + 1;
			}
			else {
				setdown[i] = i + index + 2;
			}
		}
		
		// 1 to i inclusive
		int[] forward = new int[N + 1];
		forward[1] = 1;
		for (int i = 2; i <= N; i++){
			forward[i] = Math.max(setdown[i], forward[i - 1]);
		}
		
		// N to i inclusive
		int[] backward = new int[N + 1];
		backward[N] = 1;
		for (int i = N - 1; i > 0; i--){
			backward[i] = Math.max(setup[i], backward[i + 1]);
		}
		
		int size = 0;
		for (int i = 1; i < N; i++){
			size = Math.max(size, forward[i] + backward[i + 1]);
		}
		pw.println(size);
		
		br.close();
		pw.close();
	}
}
