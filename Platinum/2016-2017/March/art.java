import java.io.*;
import java.util.*;

public class art {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("art.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("art.out")));
		
		int N = Integer.parseInt(br.readLine());
		int[][] info = new int[N][N];
		int temp = N * N;
		
		for (int k = 0; k < N; k++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) info[i][k] = Integer.parseInt(st.nextToken());
		}
		
		// minx, maxx, miny, maxy
		int[][] rect = new int[N * N + 1][4];
		for (int i = 0; i <= N * N; i++) {
			rect[i][0] = 1001;
			rect[i][1] = -1;
			rect[i][2] = 1001;
			rect[i][3] = -1;
		}
		
		for (int i = 0; i < N; i++){
			for (int k = 0; k < N; k++){
				rect[info[i][k]][0] = Math.min(rect[info[i][k]][0], i);
				rect[info[i][k]][1] = Math.max(rect[info[i][k]][1], i);
				rect[info[i][k]][2] = Math.min(rect[info[i][k]][2], k);
				rect[info[i][k]][3] = Math.max(rect[info[i][k]][3], k);
			}
		}
		
		boolean[] possible = new boolean[N * N + 1];
		Arrays.fill(possible, true);
		
		for (int i = 1; i <= N * N; i++){
			if (rect[i][0] == 1001) {
				temp--;
				continue;
			}
			
			for (int x = rect[i][0]; x <= rect[i][1]; x++){
				for (int y = rect[i][2]; y <= rect[i][3]; y++){
					if (info[x][y] == i) continue;
					possible[info[x][y]] = false;
				}
			}
		}
		
		int count = 0;
		for (int i = 1; i <= N * N; i++) if (possible[i]) count++;
		
		if (temp == 1){
			pw.println(N * N - 1);
			pw.close();
			System.exit(0);
		}
		
		pw.println(count);
		pw.close();
		br.close();
	}
}
