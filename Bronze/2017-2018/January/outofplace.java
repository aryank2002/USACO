import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class outofplace {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("outofplace.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("outofplace.out")));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		int[] nums = new int[N];
		int[] sortednums = new int[N];
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			nums[i] = Integer.parseInt(st.nextToken());
			pq.add(nums[i]);
		}
		for (int i = 0; i < N; i++){
			sortednums[i] = pq.remove();
		}
		
		int counter = 0;
		for (int i = 0; i < N; i++){
			if (sortednums[i] != nums[i]){
				counter++;
			}
		}
		
		pw.println(counter - 1);
		pw.close();
		br.close();

	}

}
