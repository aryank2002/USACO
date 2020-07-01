import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class lifeguards {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("lifeguards.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lifeguards.out")));
		
		int N = Integer.parseInt(st.nextToken());
		int max = 0;
		int[] covered = new int[1000001];
		int[][] ranges = new int[2][N];
		int low = 0;
		int big = 0;
		
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			low = Integer.parseInt(st.nextToken());
			big = Integer.parseInt(st.nextToken());
			ranges[0][i] = low;
			ranges[1][i] = big;
			
			for (int k = low + 1; k < big + 1; k++){
				covered[k]++;
			}
		}
		int count = 0;
		int tcount = 0;
		for (int i = 1; i < 1000001; i++){
			if (covered[i] > 0){
				count++;
			}
		}
		
		int lowb = 0;
		int maxb = 0;
		for (int i = 0; i < N; i++){
			tcount = count;
			lowb = ranges[0][i];
			maxb = ranges[1][i];
			for (int k = lowb + 1; k < maxb + 1; k++){
				if (covered[k] - 1 == 0){
					tcount--;
				}
			}
			if (max < tcount){
				max = tcount;
			}
		}
		
		pw.println(max);
		pw.close();
		br.close();
	}

}
