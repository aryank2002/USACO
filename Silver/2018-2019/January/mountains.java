import java.util.*;
import java.io.*;

public class mountains {

	static boolean Under(int x1, int y1, int x2, int y2){
		if (x1 == x2 && y1 < y2){
			return true;
		}
		else if (x1 == x2 && y1 > y2){
			return false;
		}
		
		if (x1 < x2){
			if (y2 + x1 - x2 >= y1){
				return true;
			}
			return false;
		}
		else {
			if (y2 + x2 - x1 >= y1){
				return true;
			}
			return false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("mountains.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mountains.out")));
		
		int N = Integer.parseInt(st.nextToken());
		int[] xs = new int[N + 1];
		int[] ys = new int[N + 1];
		
		for (int i = 1; i <= N; i++){
			st = new StringTokenizer(br.readLine());
			xs[i] = Integer.parseInt(st.nextToken());
			ys[i] = Integer.parseInt(st.nextToken());
 		}
		
		int counter = 0;
		for (int i = 1; i <= N; i++){
			boolean t = false;
			for (int k = 1; k <= N; k++){
				if (k == i){
					continue;
				}
				t = Under(xs[i], ys[i], xs[k], ys[k]);
				if (t == true){
					break;
				}
			}
			if (t == false){
				counter++;
			}
		}
		
		pw.println(counter);
		pw.close();
		br.close();
	}
}
