import java.util.*;
import java.io.*;

public class sort {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("sort.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));
		
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N + 1];
		for (int i = 1; i <= N; i++){
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		int[] sorter = nums.clone();
		Arrays.sort(sorter);
		HashMap<Integer, LinkedList<Integer>> a = new HashMap<Integer, LinkedList<Integer>>();
		
		for (int i = 1; i <= N; i++){
			if (a.containsKey(sorter[i])){
				a.get(sorter[i]).add(i);
			}
			else {
				LinkedList<Integer> q = new LinkedList<Integer>();
				q.add(i);
				a.put(sorter[i], q);
			}
		}
		
		for (int i = 1; i <= N; i++){
			nums[i] = a.get(nums[i]).getFirst();
		}
		
		int max = 1;
		BIT b = new BIT(N);
		
		for (int i = 1; i < N; i++){
			b.update(nums[i]);
			max = Math.max(max, i - b.sum(i));
		}
		
		pw.println(max);
		br.close();
		pw.close();
	}
	
	static class BIT {
		public int[] tree;
		public BIT (int N){
			tree = new int[N + 1];
		}
		public void update(int index){
			while (index <= tree.length - 1){
				tree[index]++;
				index += (index & -index);
			}
		}
		public int sum(int index){
			int s = 0;
			while (index >= 1){
				s += tree[index];
				index -= (index & -index);
			}
			return s;
		}
	}
}
