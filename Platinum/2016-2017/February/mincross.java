import java.io.*;
import java.util.*;

public class mincross {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("mincross.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mincross.out")));
		
		long min = 10000000000000L;
		int N = Integer.parseInt(br.readLine());
		
		int[] top = new int[N + 1];
		int[] bot = new int[N + 1];
		
		for (int i = 1; i <= N; i++) top[i] = Integer.parseInt(br.readLine());
		for (int i = 1; i <= N; i++) bot[i] = Integer.parseInt(br.readLine());
		
		long cur = 0;
		int[] index = new int[N + 1];
		for (int i = 1; i <= N; i++) index[top[i]] = i;
		
		BIT bit = new BIT(N);
		for (int i = 1; i <= N; i++){
			bit.update(index[bot[i]], 1L);
			cur = cur + bit.sum(N) - bit.sum(index[bot[i]]);
		}
		
		min = Math.min(min, cur);
		for (int i = 1; i < N; i++){
			cur = cur - bit.sum(index[bot[i]] - 1);
			cur = cur + bit.sum(N) - bit.sum(index[bot[i]]);
			min = Math.min(min, cur);
		}
		
		cur = 0;
		for (int i = 1; i <= N; i++) index[bot[i]] = i;
		
		bit = new BIT(N);
		for (int i = 1; i <= N; i++){
			bit.update(index[top[i]], 1L);
			cur = cur + bit.sum(N) - bit.sum(index[top[i]]);
		}
		
		min = Math.min(min, cur);
		for (int i = 1; i < N; i++){
			cur = cur - bit.sum(index[top[i]] - 1);
			cur = cur + bit.sum(N) - bit.sum(index[top[i]]);
			min = Math.min(min, cur);
		}
		
		pw.println(min);
		pw.close();
		br.close();
	}
	
	static class BIT {
		public long[] tree;
		public BIT (int N){
			tree = new long[N + 1];
		}
		public void update (int index, long val){
			while (index < tree.length){
				tree[index] += val;
				index += (index & -index);
			}
		}
		public long sum (int index){
			long s = 0;
			while (index >= 1){
				s += tree[index];
				index -= (index & -index);
			}
			return s;
		}
	}
}
