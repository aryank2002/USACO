import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class homework {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("homework.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new FileWriter("homework.out"));
		PrintWriter pw = new PrintWriter(bw);
		
		int N = Integer.parseInt(st.nextToken());
		long[] scores = new long[N+1];
		long[] mins = new long[N+1];
		long[] sum = new long[N+1];
		float[] avg = new float[N+1];
		
		long min = 1000000;
		long total = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++){
			scores[i] = Long.parseLong(st.nextToken());
			total += scores[i];
		}
		for (int i = N; i > 0; i--){
			if (scores[i] < min){
				min =  scores[i];
			}
			mins[i] = (long) min;
		}
		for (int i = 1; i < N + 1; i++){
			total = total - scores[i-1];
			sum[i] = total;
		}
		for (int i = 1; i < N; i++){
			avg[i] = (float) ( sum[i] - mins[i] ) / (N - i);
		}
		float max = -1;
		for (int i = 2; i < N; i++){
			if (avg[i] > max){
				max = avg[i];
			}
		}
		for (int i = 2; i < N; i++){
			if (avg[i] == max){
				pw.println(i - 1);
			}
		}
		br.close();
		pw.close();
	}

}
