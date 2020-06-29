import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class reststops {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("reststops.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("reststops.out")));
		int position = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int L = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int rateF = Integer.parseInt(st.nextToken());
		int rateB = Integer.parseInt(st.nextToken());
		long points = 0;
		int[] maxs = new int[N];
		
		int[][] data = new int[2][N];
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			data[0][i] = Integer.parseInt(st.nextToken());
			data[1][i] = Integer.parseInt(st.nextToken());
		}
		maxs[N - 1] = N - 1;
		for (int i = N - 2; i >= 0; i--){
			if (data[1][ maxs[i + 1] ] < data[1][i]) {
				maxs[i] = i;
			}
			else {
				maxs[i] = maxs[i + 1];
			}
		}
		int index = 0;
		while (position != data[0][N - 1]){
			int reach = data[0][maxs[index]];
			points += (long) (reach - position) * (rateF - rateB) * (data[1][maxs[index]]);
			position = reach;
			index = maxs[index] + 1;
		}
		pw.println(points);
		pw.close();
		br.close();
	}
}
