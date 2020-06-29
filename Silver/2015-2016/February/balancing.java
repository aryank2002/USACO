import java.io.*;
import java.util.*;

public class balancing {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("balancing.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("balancing.out")));
		
		int N = Integer.parseInt(st.nextToken());
		int M = N;
		
		TreeMap<Integer, Integer> tx = new TreeMap<Integer, Integer>();
		TreeMap<Integer, Integer> ty = new TreeMap<Integer, Integer>();
		
		int[][] coord = new int[N][2];
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			coord[i][0] = Integer.parseInt(st.nextToken());
			coord[i][1] = Integer.parseInt(st.nextToken());
			
			tx.put(coord[i][0], 0);
			ty.put(coord[i][1], 0);
		}
		
		int count = 1;
		for (int val: tx.keySet()){
			tx.put(val, count);
			count += 2;
		}
		
		count = 1;
		for (int val: ty.keySet()){
			ty.put(val, count);
			count += 2;
		}
		
		ArrayList<Integer>[] xs = new ArrayList[2 * N];
		for (int i = 0; i < 2 * N; i++) xs[i] = new ArrayList<Integer>();
		BIT forw = new BIT(2 * N);
		BIT back = new BIT(2 * N);
		
		for (int i = 0; i < N; i++){
			int a = tx.get(coord[i][0]);
			int b = ty.get(coord[i][1]);
			xs[a].add(b);
			back.update(b, 1);
		}
		
		int left = 0;
		int right = N;
		
		for (int x = 2; x <= 2 * N; x += 2){
			int z = left;
			
			for (int val: xs[x - 1]){
				forw.update(val, 1);
				back.update(val, -1);
				left++;
				right--;
			}
			if (left == z) continue;
			
			int lo = N / 4;
			int hi = N;
			
			while (lo != hi){
				int mid = (lo + hi) / 2;
				int upleft, lowleft, upright, lowright; 
				//System.out.println(lo + " " + mid + " " + hi);
				
				if (left > (2 * mid) || right > (2 * mid)){
					lo = mid + 1;
					continue;
				}
				
				if (left <= mid){
					upleft = 2 * N;
					lowleft = 0;
				}
				else {
					int b = 0;
					int t = 2 * N;
					
					while (b != t){
						int m = (b + t + 1) / 2;
						if (forw.sum(m) <= mid) b = m;
						else t = m - 1;
					}
					upleft = t;
					b = 0;
					t = 2 * N;
					
					while (b != t){
						int m = (b + t) / 2;
						if (forw.sum(m) >= left - mid) t = m;
						else b = m + 1;
					}
					lowleft = t;
				}
				
				if (right <= mid){
					upright = 2 * N;
					lowright = 0;
				}
				else {
					int b = 0;
					int t = 2 * N;
					
					while (b != t){
						int m = (b + t + 1) / 2;
						if (back.sum(m) <= mid) b = m;
						else t = m - 1;
					}
					upright = t;
					b = 0;
					t = 2 * N;
					
					while (b != t){
						int m = (b + t) / 2;
						if (back.sum(m) >= right - mid) t = m;
						else b = m + 1;
					}
					lowright = t;
				}
				
				if (upright < lowleft || upleft < lowright) lo = mid + 1;
				else hi = mid;
				
				if (lo >= M) break;
			}
			
			M = Math.min(lo, M);
			// System.out.println(x + " " + M);
		}
		
		pw.println(M);
		pw.close();
		br.close();
	}
	
	static class BIT {
		public int[] tree;
		public BIT (int N){
			tree = new int[N + 1];
		}
		public void update (int index, int val){
			while (index < tree.length){
				tree[index] += val;
				index += (index & -index);
			}
		}
		public int sum (int index){
			int s = 0;
			while (index >= 1){
				s += tree[index];
				index -= (index & -index);
			}
			return s;
		}
	}
}
