import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class maxcross {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("maxcross.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new FileWriter("maxcross.out"));
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		int[] data = new int[B];
		for (int i = 0; i < B; i++){
			st = new StringTokenizer(br.readLine());
			data[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(data);
		int min = 1000000;
		for (int i = 1; i <= N - K + 1; i++){
			int a = Arrays.binarySearch(data, i);
			int b = Arrays.binarySearch(data, i + K - 1);
			if (a < 0){
				a = - a - 1;
			}
			if (b < 0){
				b = - b - 2;
			}
			if (b - a + 1 < min){
				min = b - a + 1;
			}
		}
		
		pw.println(min);
		pw.close();
		br.close();

	}

}
