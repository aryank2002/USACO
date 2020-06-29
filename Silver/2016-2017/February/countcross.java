import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class countcross {
	
	static boolean[][][] nocross;
	static int N;
	public static void Direction(int x1, int y1, int x2, int y2){
		if (x1 == x2){
			if (y1 > y2){
				nocross[x1][y1][2] = true;
				nocross[x2][y2][0] = true;
			}
			else {
				nocross[x1][y1][0] = true;
				nocross[x2][y2][2] = true;
			}
		}
		else {
			if (x1 > x2){
				nocross[x1][y1][3] = true;
				nocross[x2][y2][1] = true;
			}
			else {
				nocross[x1][y1][1] = true;
				nocross[x2][y2][3] = true;
			}
		}
	}
	
	public static boolean InBound(int x, int y){
		if (N >= x && x >= 1 && N >= y && y >= 1){
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("countcross.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("countcross.out")));
		
		int components = 0;
		HashMap<Integer, Integer> sizes = new HashMap<Integer, Integer>();
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
	
		boolean[][] explored = new boolean[N + 1][N + 1];
		int[][] partition = new int[N + 1][N + 1];
		
		// N --> 0, E, S, W
		nocross = new boolean[N + 1][N + 1][4];
		for (int i = 0; i < R; i++){
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			Direction(x1, y1, x2, y2);
		}
		
		int answer = 0;
		for (int i = 1; i <= N; i++){
			for (int k = 1; k <= N; k++){
				if (explored[i][k] == false){
					components++;
					explored[i][k] = true;
					partition[i][k] = components;
					int[] t = new int[2];
					t[0] = i;
					t[1] = k;
					LinkedList<int[]> q = new LinkedList<int[]>();
					q.add(t);
					
					while (!q.isEmpty()){
						int[] t5 = q.remove();
						int x = t5[0];
						int y = t5[1];
						
						if (InBound(x, y + 1) && nocross[x][y][0] == false && explored[x][y + 1] == false){
							partition[x][y + 1] = components;
							explored[x][y + 1] = true;
							int[] t2 = new int[2];
							t2[0] = x;
							t2[1] = y + 1;
							q.add(t2);
						}
						if (InBound(x + 1, y) && nocross[x][y][1] == false && explored[x + 1][y] == false){
							partition[x + 1][y] = components;
							explored[x + 1][y] = true;
							int[] t2 = new int[2];
							t2[0] = x + 1;
							t2[1] = y;
							q.add(t2);
						}
						if (InBound(x, y - 1) && nocross[x][y][2] == false && explored[x][y - 1] == false){
							partition[x][y - 1] = components;
							explored[x][y - 1] = true;
							int[] t2 = new int[2];
							t2[0] = x;
							t2[1] = y - 1;
							q.add(t2);
						}
						if (InBound(x - 1, y) && nocross[x][y][3] == false && explored[x - 1][y] == false){
							partition[x - 1][y] = components;
							explored[x - 1][y] = true;
							int[] t2 = new int[2];
							t2[0] = x - 1;
							t2[1] = y;
							q.add(t2);
						}
					}
				}
			}
		}
		
		for (int i = 0; i < K; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			int co = partition[a][b];
			if (sizes.containsKey(co)){
				int temp = sizes.get(co);
				temp++;
				sizes.put(co, temp);
			}
			else {
				sizes.put(co, 1);
			}
		}
		
		for (int i = 1; i <= components; i++){
			for (int k = i + 1; k <= components; k++){
				if (sizes.containsKey(i) && sizes.containsKey(k)){
					answer = answer + sizes.get(i) * sizes.get(k);
				}
			}
		}
		
		pw.println(answer);
		pw.close();
		br.close();
	}
}
