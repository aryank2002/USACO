import java.io.*;
import java.util.*;

public class triangles {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("triangles.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));
		
		int N = Integer.parseInt(br.readLine());
		Point[] data = new Point[N];
		
		for (int i = 0; i < N; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			data[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		int[][] pre = new int[N][N];
		int[] under = new int[N];
		
		for (int i = 0; i < N; i++){
			for (int k = i + 1; k < N; k++){
				if (data[i].x == data[k].x) {
					if (data[i].y > data[k].y) under[i]++;
					else under[k]++;
					continue;
				}
				
				for (int m = 0; m < N; m++){
					if (m == i || m == k) continue;
					if (data[m].x < Math.min(data[i].x, data[k].x) || data[m].x > Math.max(data[i].x, data[k].x)) continue;
					
					double d = (1.0 * data[m].x - data[i].x) * (data[k].y - data[i].y) / (double) (data[k].x - data[i].x) + data[i].y;
					if (d > data[m].y) pre[i][k]++;
				}
			}
		}
		
		int[] v = new int[N - 2];
		
		for (int i = 0; i < N; i++){
			for (int k = i + 1; k < N; k++){
				for (int m = k + 1; m < N; m++){
					int t = above(data[i], data[k], data[m]) * pre[i][k] + above(data[m], data[k], data[i]) * pre[k][m]
							+ above(data[i], data[m], data[k]) * pre[i][m];
					
					int c = above(data[i], data[k], data[m]) + above(data[m], data[k], data[i]) + above(data[i], data[m], data[k]);
					
					if (c == 1){
						if (above(data[i], data[m], data[k]) == -1) t -= under[k];
						if (above(data[i], data[k], data[m]) == -1) t -= under[m];
						if (above(data[k], data[m], data[i]) == -1) t -= under[i];
					}
					else {
						if (above(data[i], data[m], data[k]) == 1) t += under[k] - 1;
						if (above(data[i], data[k], data[m]) == 1) t += under[m] - 1;
						if (above(data[k], data[m], data[i]) == 1) t += under[i] - 1;
					}
						
					if (t < N - 2) v[t]++;
				}
			}
		}
		
		for (int i = 0; i < N - 2; i++) pw.println(v[i]);
		pw.close();
		br.close();
	}
	
	static int above(Point a, Point b, Point c){
		double tx = (a.x + b.x) / 2.0;
		double ty = (a.y + b.y) / 2.0;
		
		if (tx >= Math.min(b.x, c.x) && tx <= Math.max(b.x, c.x)) if (ty > (1.0 * tx - b.x) * (b.y - c.y) / (double) (b.x - c.x) + b.y) return 1;
		if (tx >= Math.min(a.x, c.x) && tx <= Math.max(a.x, c.x)) if (ty > (1.0 * tx - a.x) * (a.y - c.y) / (double) (a.x - c.x) + a.y) return 1;
		
		return -1;
	}
	
	static class Point {
		public int x, y;
		public Point(int a, int b){
			x = a;
			y = b;
		}
	}
}

