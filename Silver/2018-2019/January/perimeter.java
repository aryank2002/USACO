import java.util.*;
import java.io.*;

public class perimeter {
	
	static int N;
	static String[][] coords;
	
	static boolean InRange(int x, int y){
		if (0 < x && x <= N && y > 0 && y <= N){
			return true;
		}
		return false;
	}
	
	static int Adjacent(int x, int y){
		int t = 0;
		if (InRange(x + 1, y) && coords[x + 1][y].equals("#")){
			t++;
		}
		if (InRange(x - 1, y) && coords[x - 1][y].equals("#")){
			t++;
		}
		if (InRange(x, y + 1) && coords[x][y + 1].equals("#")){
			t++;
		}
		if (InRange(x, y - 1) && coords[x][y - 1].equals("#")){
			t++;
		}
		return t;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("perimeter.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));
		
		N = Integer.parseInt(br.readLine());
		coords = new String[N + 2][N + 2];
		for (int i = 1; i <= N; i++){
			String s = br.readLine();
			for (int k = 1; k <= N; k++){
				coords[k][i] = s.substring(k - 1, k);
			}
		}
		
		boolean[][] explored = new boolean[N + 2][N + 2];
		ArrayList<int[]> maxreg = new ArrayList<int[]>();
		int max = 0;
		for (int i = 1; i <= N; i++){
			for (int k = 1; k <= N; k++){
				if (explored[i][k] == true || coords[i][k].equals(".")){
					continue;
				}
				explored[i][k] = true;
				int size = 1;
				int[] t = {i, k};
				LinkedList<int[]> q = new LinkedList<int[]>();
				q.add(t);
				while (!q.isEmpty()){
					int[] temp = q.remove();
					int x = temp[0];
					int y = temp[1];
					if (InRange(x + 1, y) && coords[x + 1][y].equals("#") && explored[x + 1][y] == false){
						size++;
						explored[x + 1][y] = true;
						int[] t1 = {x + 1, y};
						q.add(t1);
					}
					if (InRange(x - 1, y) && coords[x - 1][y].equals("#") && explored[x - 1][y] == false){
						size++;
						explored[x - 1][y] = true;
						int[] t1 = {x - 1, y};
						q.add(t1);
					}
					if (InRange(x, y + 1) && coords[x][y + 1].equals("#") && explored[x][y + 1] == false){
						size++;
						explored[x][y + 1] = true;
						int[] t1 = {x, y + 1};
						q.add(t1);
					}
					if (InRange(x, y - 1) && coords[x][y - 1].equals("#") && explored[x][y - 1] == false){
						size++;
						explored[x][y - 1] = true;
						int[] t1 = {x, y - 1};
						q.add(t1);
					}
				}
				
				if (max == size) {
					max = size;
					int[] t2 = {i, k};
					maxreg.add(t2);
				}
				else if (max < size){
					max = size;
					maxreg.clear();
					int[] t2 = {i, k};
					maxreg.add(t2);
				}
				// end
			}
		}
		
		int perim = Integer.MAX_VALUE;
		explored = new boolean[N + 1][N + 1];
		for (int[] t: maxreg){
			int i = t[0];
			int k = t[1];
			explored[i][k] = true;
			LinkedList<int[]> q = new LinkedList<int[]>();
			q.add(t);
			int tp = 4 * max;
			
			while (!q.isEmpty()){
				int[] temp = q.remove();
				int x = temp[0];
				int y = temp[1];
				tp = tp - Adjacent(x, y);
				
				if (InRange(x + 1, y) && coords[x + 1][y].equals("#") && explored[x + 1][y] == false){
					explored[x + 1][y] = true;
					int[] t1 = {x + 1, y};
					q.add(t1);
				}
				if (InRange(x - 1, y) && coords[x - 1][y].equals("#") && explored[x - 1][y] == false){
					explored[x - 1][y] = true;
					int[] t1 = {x - 1, y};
					q.add(t1);
				}
				if (InRange(x, y + 1) && coords[x][y + 1].equals("#") && explored[x][y + 1] == false){
					explored[x][y + 1] = true;
					int[] t1 = {x, y + 1};
					q.add(t1);
				}
				if (InRange(x, y - 1) && coords[x][y - 1].equals("#") && explored[x][y - 1] == false){
					explored[x][y - 1] = true;
					int[] t1 = {x, y - 1};
					q.add(t1);
				}
			}
			perim = Math.min(perim, tp);
		}
		
		pw.println(max + " " + perim);
		pw.close();
		br.close();
	}
}
