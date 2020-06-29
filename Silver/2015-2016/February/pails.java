import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class pails {
	
	static int min = 100000;
	static int X, Y, M;
	static boolean[][][] explored;
	
	public static void Recurse(int x, int y, int k){
		if (explored[x][y][k] == true){
			return;
		}
		explored[x][y][k] = true;
		
		if (k == 0){
			min = Math.min(min, Math.abs(x + y - M));
		}
		else {
			min = Math.min(min, Math.abs(x + y - M));
			
			// fill
			Recurse(X, y, k - 1);
			Recurse(x, Y, k - 1);
			
			//empty
			Recurse(0, y, k - 1);
			Recurse(x, 0, k - 1);
			
			//empty into other bucket
			if (x + y <= X){
				Recurse(x + y, 0, k - 1);
			}
			else {
				Recurse(X, x + y - X, k - 1);
			}
			
			if (x + y <= Y){
				Recurse(0, x + y, k - 1);
			}
			else {
				Recurse(x + y - Y, Y, k - 1);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("pails.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pails.out")));
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		explored = new boolean[X + 1][Y + 1][K + 1];
		Recurse(0, 0, K);
		
		pw.println(min);
		pw.close();
		br.close();
	}

}
