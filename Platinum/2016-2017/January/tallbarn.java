import java.io.*;
import java.util.*;

public class tallbarn2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("tallbarn.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("tallbarn.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		long K = Long.parseLong(st.nextToken());
		
		long[] nums = new long[N];
		long max = 0;
		
		for (int i = 0; i < N; i++) max = Math.max(max, nums[i] = Long.parseLong(br.readLine()));
		
		long[] cows = new long[N];
		Arrays.fill(cows, 1);
		max = (long) Math.ceil((max + 1.0) / 2000.0 + 1000);
		
		long lo = 1;
		long hi = 18000000000L * max + 1;
		long totcows = 0;
		
		while (lo != hi){
			long mid = (lo + hi) / 2;
			double temp = (double) mid / 18000000000.0;
			
			long sum = 0;
			for (int i = 0; i < N; i++){
				sum += (long) Math.floor(0.5 + Math.sqrt(0.25 + (double) nums[i] / temp));
			}
			
			if (sum <= K){
				hi = mid;
			}
			else {
				lo = mid + 1;
			}
		}
		
		double temp = (double) lo / 18000000000.0;
		for (int i = 0; i < N; i++){
			totcows += cows[i] = (long) Math.floor(0.5 + Math.sqrt(0.25 + (double) nums[i] / temp));
		}
		
		PriorityQueue<State> pq = new PriorityQueue<State>();
		for (int i = 0; i < N; i++){
			pq.add(new State((double) nums[i] / (double) cows[i] - (double) nums[i] / (double) (cows[i] + 1), i));
		}
		
		while (totcows != K){
			State a = pq.poll();
			cows[a.node]++;
			totcows++;
			
			pq.add(new State((double) nums[a.node] / (double) cows[a.node] - (double) nums[a.node] / (double) (cows[a.node] + 1), a.node));
		}
		
		double t = 0;
		for (int i = 0; i < N; i++) t += (double) nums[i] / (double) cows[i];
		
		pw.println((int) (t + 0.5));
		pw.close();
		br.close();
	}
	
	static class State implements Comparable<State> {
		public double inc;
		public int node;
		public State(double a, int b){
			inc = a;
			node = b;
		}
		public int compareTo(State other){
			if (inc < other.inc) return 1;
			if (inc > other.inc) return -1;
			return 0;
		}
	}
}
