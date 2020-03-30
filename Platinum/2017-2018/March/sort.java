import java.io.*;
import java.util.*;

public class sort {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("sort.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));
		int N = Integer.parseInt(br.readLine());
		
		State[] nums = new State[N + 1];
		nums[0] = new State(-1, -1);
		for (int i = 1; i <= N; i++) nums[i] = new State(i, Integer.parseInt(br.readLine()));
		
		Arrays.sort(nums);
		long count = 0;
		int[] max = new int[N + 1];
		
		for (int i = 1; i <= N; i++) max[i] = Math.max(max[i - 1], nums[i].index);
		int[] part = new int[N + 1];
		for (int i = 1; i <= N; i++) part[i] = max[i] - i;
		
		int[] cont = new int[N + 1];
		for (int i = 1; i <= N; i++) cont[i] = Math.max(1, Math.max(part[i], part[i - 1]));
		for (int i = 1; i <= N; i++) count = count + (long) cont[i];
		
		pw.println(count);
		br.close();
		pw.close();
	}
	
	static class State implements Comparable<State> {
		public int index, val;
		public State(int a, int b){
			index = a;
			val = b;
		}
		public int compareTo(State other){
			if (val != other.val) return val - other.val;
			return index - other.index;
		}
	}
}
