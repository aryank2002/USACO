import java.util.*;
import java.io.*;

public class lightsout {

	static String s;
	static int N;
	static int len;
	
	static boolean Unique(String t){
		int length = t.length();
		int count = 0;
		for (int i = 1; i <= len; i++){
			if (t.equals(s.substring(i, i + length))){
				count++;
			}
		}
		if (count == 1) return true;
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("lightsout.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lightsout.out")));
		
		N = Integer.parseInt(st.nextToken());
		int[][] data = new int[N + 2][2];
		for (int i = 1; i <= N; i++){
			st = new StringTokenizer(br.readLine());
			data[i][0] = Integer.parseInt(st.nextToken());
			data[i][1] = Integer.parseInt(st.nextToken());
		}
		data[0][0] = data[N][0];
		data[0][1] = data[N][1];
		
		data[N + 1][0] = data[1][0];
		data[N + 1][1] = data[1][1];
		
		int[] ori = new int[N + 1];
		ori[1] = -1;
		
		for (int i = 2; i < N + 1; i++){
			if (data[i - 1][0] == data[i][0]){
				if (data[i - 1][1] < data[i][1]){
					if (data[i + 1][0] > data[i - 1][0]){
						ori[i] = -2;
					}
					else {
						ori[i] = -3;
					}
				}
				else {
					if (data[i + 1][0] > data[i - 1][0]){
						ori[i] = -3;
					}
					else {
						ori[i] = -2;
					}
				}
			}
			else {
				if (data[i - 1][0] < data[i][0]){
					if (data[i + 1][1] > data[i - 1][1]){
						ori[i] = -3;
					}
					else {
						ori[i] = -2;
					}
				}
				else {
					if (data[i + 1][1] > data[i - 1][1]){
						ori[i] = -2;
					}
					else {
						ori[i] = -3;
					}
				}
			}
		}
		
		int max = 0;
		int[] distances = new int[N + 1];
		int total = Math.abs(data[1][0] - data[N][0]) + Math.abs(data[1][1] - data[N][1]);
		
		for (int i = 1; i < N; i++){
			total += Math.abs(data[i][0] - data[i + 1][0]) + Math.abs(data[i][1] - data[i + 1][1]);
		}
		
		int a = 0;
		for (int i = 2; i <= N; i++){
			a += Math.abs(data[i][0] - data[i - 1][0]) + Math.abs(data[i][1] - data[i - 1][1]);
			distances[i] = Math.min(a, total - a);
		}
		
		s = "-1" + Integer.toString(Math.abs(data[1][0] - data[2][0]) + Math.abs(data[1][1] - data[2][1]));
		for (int i = 2; i <= N; i++){
			s = s + Integer.toString(ori[i]);
			s = s + Integer.toString(Math.abs(data[i][0] - data[i + 1][0]) + Math.abs(data[i][1] - data[i + 1][1]));
		}
		len = s.length();
		s = " " + s + s;
		// System.out.println(s);
		
		int[] dist = new int[N + 1];
		for (int i = 2; i <= N; i++){
			boolean unique = false;
			String t = Integer.toString(ori[i]);
			int tdist = 0;
			int tvert = i;
			
			// contains twice
			if (Unique(t)){
				unique = true;
			}
			while (unique == false){
				int tnew = (tvert + 1) % N;
				if (tnew == 0) tnew = N;
				tdist += Math.abs(data[tvert][0] - data[tnew][0]) + Math.abs(data[tvert][1] - data[tnew][1]);
				
				t = t + Integer.toString(Math.abs(data[tvert][0] - data[tnew][0]) + Math.abs(data[tvert][1] - data[tnew][1]))
					+ Integer.toString(ori[tnew]);
				tvert = tnew;
				// System.out.println(t);
				if (Unique(t)){
					unique = true;
				}
			}
			dist[i] = tdist + distances[tvert] - distances[i];
		}
		
		for (int i = 1; i <= N; i++){
			max = Math.max(max, dist[i]);
		}
		
		pw.println(max);
		br.close();
		pw.close();
	}
}
