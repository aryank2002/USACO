import java.util.*;
import java.io.*;

public class nocross {
	
	static Segment[] segments = new Segment[400000];
	
	static void propagate (int node, int left, int right) {
	    segments[node].max += segments[node].lazy;
	    if (left < right) {
	        segments[2 * node + 1].lazy += segments[node].lazy;
	        segments[2 * node + 2].lazy += segments[node].lazy;
	    }
	    segments[node].lazy = 0;
	}

	static void update (int node, int left, int right, int x, int y, int value) {
	    propagate (node, left, right);
	    if (x <= left && right <= y) {
	        segments[node].lazy += value;
	    } 
	    else {
	        int middle = (left + right) / 2;
	        if (x <= middle) {
	            update (2 * node + 1, left, middle, x, y, value);
	        }
	        if (y >= middle + 1) {
	            update (2 * node + 2, middle + 1, right, x, y, value);
	        }
	        propagate (2 * node + 1, left, middle);
	        propagate (2 * node + 2, middle + 1, right);
	        segments[node].max = Math.max(segments[2 * node + 1].max, segments[2 * node + 2].max);
	    }
	}
	
	static int query (int node, int left, int right, int x, int y) {
	    propagate (node, left, right);
	    if (x <= left && right <= y) {
	        return segments[node].max;
	    } 
	    else {
	        int sum = 0;
	        int middle = (left + right) / 2;
	        if (x <= middle) {
	            sum = Math.max(sum, query (2 * node + 1, left, middle, x, y));
	        }
	        if (y >= middle + 1) {
	            sum = Math.max(sum, query (2 * node + 2, middle + 1, right, x, y));
	        }
	        return sum;
	    }
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("nocross.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("nocross.out")));
		
		int N = Integer.parseInt(br.readLine());
		int[] left = new int[N + 1];
		int[] right = new int[N + 1];
		
		for (int i = 1; i <= N; i++){
			left[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = 1; i <= N; i++){
			right[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = 0; i < 400000; i++){
			segments[i] = new Segment(0, 0);
		}
		
		int[] rindex = new int[N + 1];
		int[] dp = new int[N + 1];
		for (int i = 1; i <= N; i++) rindex[right[i]] = i;
		
		for (int i = 1; i <= N; i++){
			ArrayList<State> sort = new ArrayList<State>();
			
			for (int k = 4; k > -5; k--){
				if (left[i] + k > N || left[i] + k <= 0) continue;
				sort.add(new State(rindex[left[i] + k]));
			}
			Collections.sort(sort);
			
			for (State s: sort){
				int store = dp[s.val];
				if (s.val == 1) dp[s.val] = Math.max(dp[s.val], 1);
				else dp[s.val] = Math.max(dp[s.val], 1 + query(0, 1, N, 0, s.val - 1));
				update(0, 1, N, s.val, s.val, dp[s.val] - store);
				//if (i == 1) System.out.println(s.val);
			}
			
			//for (int k = 1; k <= N; k++) System.out.println(i + " " + k + " " + dp[k]);
			//System.out.println();
		}
		
		int max = 0;
		for (int i = 0; i <= N; i++) max = Math.max(max, dp[i]);
		
		pw.println(max);
		pw.close();
		br.close();
	}
	
	static class State implements Comparable<State> {
		public int val;
		public State(int a){
			val = a;
		}
		public int compareTo(State other){
			return other.val - val;
		}
	}
	
	static class Segment {
	    public int max, lazy;
	    public Segment (int a, int b){
	    	max = a;
	    	lazy = b;
	    }
	} 
}
