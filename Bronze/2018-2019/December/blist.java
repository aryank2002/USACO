import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class blist {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("blist.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("blist.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[][] data = new int[3][N];
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			data[0][i] = Integer.parseInt(st.nextToken());
			data[1][i] = Integer.parseInt(st.nextToken());
			data[2][i] = Integer.parseInt(st.nextToken());
		}
		int max = 0;
		for (int time = 1; time <= 1000; time++){
			int temp = 0;
			for (int i = 0; i < N; i++){
				if (data[0][i] <= time && time <= data[1][i]){
					temp+=data[2][i];
				}
			}
			if (temp > max) {
				max = temp;
			}
		}
		
		pw.println(max);
		pw.close();
		br.close();

	}

}
