import java.io.*;
import java.util.*;

public class split {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("split.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("split.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long N = Integer.parseInt(st.nextToken());
		State[] nums = new State[(int) N + 1];
		
		long minx = Long.MAX_VALUE;
		long miny = Long.MAX_VALUE;
		long maxx = Long.MIN_VALUE;
		long maxy = Long.MIN_VALUE;
		
		nums[0] = new State(0, 0);
		State[] inver = new State[(int) N + 1];
		inver[0] = new State(0, 0);
		
		for (int i = 1; i <= N; i++){
			st = new StringTokenizer(br.readLine());
			nums[i] = new State(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			inver[i] = new State(nums[i].y, nums[i].x);
			
			minx = Math.min(minx, nums[i].x);
			miny = Math.min(miny, nums[i].y);
			maxx = Math.max(maxx, nums[i].x);
			maxy = Math.max(maxy, nums[i].y);
		}
		
		long area = (long) (maxx - minx) * (maxy - miny);
		Arrays.sort(nums);
		Arrays.sort(inver);
		
		long[] mins = new long[(int) N + 1];
		mins[0] = Long.MAX_VALUE;
		long[] maxs = new long[(int) N + 1];
		for (int i = 1; i <= N; i++){
			mins[i] = Math.min(mins[i - 1], nums[i].y);
			maxs[i] = Math.max(maxs[i - 1], nums[i].y);
		}
		
		long[] rmins = new long[(int) N + 1];
		long[] rmaxs = new long[(int) N + 1];
		rmins[(int) N] = nums[(int) N].y;
		rmaxs[(int) N] = nums[(int) N].y;
		
		for (int i = (int) N - 1; i > 0; i--){
			rmins[i] = Math.min(rmins[i + 1], nums[i].y);
			rmaxs[i] = Math.max(rmaxs[i + 1], nums[i].y);
		}
		
		long minarea = Long.MAX_VALUE;
		for (int i = 1; i < N; i++){
			if (nums[i].x == nums[i + 1].x) continue;
			long temp = (long) (nums[i].x - nums[1].x) * (maxs[i] - mins[i]) + (nums[(int) N].x - nums[i + 1].x) * (rmaxs[i + 1] - rmins[i + 1]);
			minarea = Math.min(minarea, temp);
		}
		
		mins[0] = Long.MAX_VALUE;
		for (int i = 1; i <= N; i++){
			mins[i] = Math.min(mins[i - 1], inver[i].y);
			maxs[i] = Math.max(maxs[i - 1], inver[i].y);
		}
		
		rmins[(int) N] = inver[(int) N].y;
		rmaxs[(int) N] = inver[(int) N].y;
		
		for (int i = (int) N - 1; i > 0; i--){
			rmins[i] = Math.min(rmins[i + 1], inver[i].y);
			rmaxs[i] = Math.max(rmaxs[i + 1], inver[i].y);
		}
		
		for (int i = 1; i < N; i++){
			if (inver[i].x == inver[i + 1].x) continue;
			long temp = (long) (inver[i].x - inver[1].x) * (maxs[i] - mins[i]) + (inver[(int) N].x - inver[i + 1].x) * (rmaxs[i + 1] - rmins[i + 1]);
			minarea = Math.min(minarea, temp);
		}
		
		pw.println(area - minarea);
		br.close();
		pw.close();
	}
	static class State implements Comparable<State> {
		public long x, y;
		public State (long a, long b) {
			x = a;
			y = b;
		}
		public int compareTo (State other) {
			return (int) (x - other.x);
		}
	}
}
