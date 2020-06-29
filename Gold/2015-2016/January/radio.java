import java.util.*;
import java.io.*;

public class radio {
	
	static boolean InBounds(int x, int y){
		if (x >= 0 && y >= 0) return true;
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("radio.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("radio.out")));
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] consumed = new int[N + 1][M + 1];
		
		st = new StringTokenizer(br.readLine());
		int fx = Integer.parseInt(st.nextToken());
		int fy = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int bx = Integer.parseInt(st.nextToken());
		int by = Integer.parseInt(st.nextToken());
		
		String fsteps = br.readLine();
		String bsteps = br.readLine();
		
		int[][] floc = new int[N + 1][2];
		floc[0][0] = fx;
		floc[0][1] = fy;
		
		for (int i = 0; i < N; i++){
			String s = fsteps.substring(i, i + 1);
			if (s.equals("N")){
				floc[i + 1][0] = floc[i][0];
				floc[i + 1][1] = floc[i][1] + 1;
			}
			if (s.equals("E")){
				floc[i + 1][0] = floc[i][0] + 1;
				floc[i + 1][1] = floc[i][1];
			}
			if (s.equals("S")){
				floc[i + 1][0] = floc[i][0];
				floc[i + 1][1] = floc[i][1] - 1;
			}
			if (s.equals("W")){
				floc[i + 1][0] = floc[i][0] - 1;
				floc[i + 1][1] = floc[i][1];
			}
		}
		
		int[][] bloc = new int[M + 1][2];
		bloc[0][0] = bx;
		bloc[0][1] = by;
		
		for (int i = 0; i < M; i++){
			String s = bsteps.substring(i, i + 1);
			if (s.equals("N")){
				bloc[i + 1][0] = bloc[i][0];
				bloc[i + 1][1] = bloc[i][1] + 1;
			}
			if (s.equals("E")){
				bloc[i + 1][0] = bloc[i][0] + 1;
				bloc[i + 1][1] = bloc[i][1];
			}
			if (s.equals("S")){
				bloc[i + 1][0] = bloc[i][0];
				bloc[i + 1][1] = bloc[i][1] - 1;
			}
			if (s.equals("W")){
				bloc[i + 1][0] = bloc[i][0] - 1;
				bloc[i + 1][1] = bloc[i][1];
			}
		}
		
		for (int i = 0; i <= N; i++){
			for (int k = 0; k <= M; k++){
				if (i == 0 && k == 0) continue;
				int min = Integer.MAX_VALUE;
				
				if (InBounds(i - 1, k)){
					min = Math.min(min, consumed[i - 1][k] + (floc[i][0] - bloc[k][0]) * (floc[i][0] - bloc[k][0]) +
							(floc[i][1] - bloc[k][1]) * (floc[i][1] - bloc[k][1])); 
				}
				if (InBounds(i, k - 1)){
					min = Math.min(min, consumed[i][k - 1] + (floc[i][0] - bloc[k][0]) * (floc[i][0] - bloc[k][0]) +
							(floc[i][1] - bloc[k][1]) * (floc[i][1] - bloc[k][1])); 
				}
				if (InBounds(i - 1, k - 1)){
					min = Math.min(min, consumed[i - 1][k - 1] + (floc[i][0] - bloc[k][0]) * (floc[i][0] - bloc[k][0]) +
							(floc[i][1] - bloc[k][1]) * (floc[i][1] - bloc[k][1])); 
				}
				
				consumed[i][k] = min;
			}
		}
		
		pw.println(consumed[N][M]);
		br.close();
		pw.close();
	}
}
