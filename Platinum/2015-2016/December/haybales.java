import java.util.*;
import java.io.*;

public class haybales {
	
	static Segment[] segment;
	static Segment[] segmentmin;

	static void propagatesum (int node, int left, int right) {
	    segment[node].sum += (right - left + 1) * segment[node].lazy;
	    if (left < right) {
	        segment[2 * node + 1].lazy += segment[node].lazy;
	        segment[2 * node + 2].lazy += segment[node].lazy;
	    }
	    segment[node].lazy = 0;
	}

	static void updatesum (int node, int left, int right, int x, int y, long value) {
	    propagatesum (node, left, right);
	    if (x <= left && right <= y) {
	        segment[node].lazy += value;
	    } 
	    else {
	        int middle = (left + right) / 2;
	        if (x <= middle) {
	            updatesum (2 * node + 1, left, middle, x, y, value);
	        }
	        if (y >= middle + 1) {
	            updatesum (2 * node + 2, middle + 1, right, x, y, value);
	        }
	        propagatesum (2 * node + 1, left, middle);
	        propagatesum (2 * node + 2, middle + 1, right);
	        segment[node].sum = segment[2 * node + 1].sum + segment[2 * node + 2].sum;
	    }
	}

	static long querysum (int node, int left, int right, int x, int y) {
	    propagatesum (node, left, right);
	    if (x <= left && right <= y) {
	        return segment[node].sum;
	    } 
	    else {
	        long sum = 0;
	        int middle = (left + right) / 2;
	        if (x <= middle) {
	            sum += querysum (2 * node + 1, left, middle, x, y);
	        }
	        if (y >= middle + 1) {
	            sum += querysum (2 * node + 2, middle + 1, right, x, y);
	        }
	        return sum;
	    }
	}
	
	static void propagatemin (int node, int left, int right) {
	    segmentmin[node].sum += segmentmin[node].lazy;
	    if (left < right) {
	        segmentmin[2 * node + 1].lazy += segmentmin[node].lazy;
	        segmentmin[2 * node + 2].lazy += segmentmin[node].lazy;
	    }
	    segmentmin[node].lazy = 0;
	}

	static void updatemin (int node, int left, int right, int x, int y, long value) {
	    propagatemin (node, left, right);
	    if (x <= left && right <= y) {
	        segmentmin[node].lazy += value;
	    } 
	    else {
	        int middle = (left + right) / 2;
	        if (x <= middle) {
	            updatemin (2 * node + 1, left, middle, x, y, value);
	        }
	        if (y >= middle + 1) {
	            updatemin (2 * node + 2, middle + 1, right, x, y, value);
	        }
	        propagatemin (2 * node + 1, left, middle);
	        propagatemin (2 * node + 2, middle + 1, right);
	        segmentmin[node].sum = Math.min(segmentmin[2 * node + 1].sum, segmentmin[2 * node + 2].sum);
	    }
	}

	static long querymin (int node, int left, int right, int x, int y) {
	    propagatemin (node, left, right);
	    if (x <= left && right <= y) {
	        return segmentmin[node].sum;
	    } 
	    else {
	        long sum = Long.MAX_VALUE / 2;
	        int middle = (left + right) / 2;
	        if (x <= middle) {
	            sum = Math.min(sum, querymin (2 * node + 1, left, middle, x, y));
	        }
	        if (y >= middle + 1) {
	            sum = Math.min(sum, querymin (2 * node + 2, middle + 1, right, x, y));
	        }
	        return sum;
	    }
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));
		
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		long[] vals = new long[N + 1];
		
		segment = new Segment[4 * N];
		segmentmin = new Segment[4 * N];
		for (int i = 0; i < 4 * N; i++){
			segment[i] = new Segment (0, 0);
			segmentmin[i] = new Segment(100000000000L, 0);
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++){
			vals[i] = Long.parseLong(st.nextToken());
			updatesum (0, 1, N, i, i, vals[i]);
			updatemin (0, 1, N, i, i, vals[i]);
			updatemin (0, 1, N, i, i, -100000000000L);
		}
		
		for (int i = 0; i < Q; i++){
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			
			if (s.equals("M")) pw.println(querymin (0, 1, N, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			if (s.equals("S")) pw.println(querysum (0, 1, N, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			
			if (s.equals("P")){
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				long C = Long.parseLong(st.nextToken());
				updatesum (0, 1, N, A, B, C);
				updatemin (0, 1, N, A, B, C);
			}
		}
		
		br.close();
		pw.close();
	}
	
	static class Segment {
		// sum will denote minimum in segmentmin
	    public long sum, lazy;
	    public Segment (long a, long b){
	    	sum = a;
	    	lazy = b;
	    }
	} 
}
