import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class highcard {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("highcard.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("highcard.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		HashSet<Integer> Elsie = new HashSet<Integer>();
		
		int N = Integer.parseInt(st.nextToken());
		int[] elsie = new int[N];
		int[] bessie = new int[N];
		
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			elsie[i] = a;
			Elsie.add(a);
		}
		
		int counter = 0;
		int counter2 = 0;
		for (int i = 1; i <= 2 * N; i++){
			if (!Elsie.contains(i)){
				bessie[counter] = i;
				counter++;
			}
			else {
				elsie[counter2] = i;
				counter2++;
			}
		}
		
		int ecounter = 0;
		int beat = 0;
		for (int i = 0; i < N; i++){
			if (bessie[i] > elsie[ecounter]){
				ecounter++;
				beat++;
			}
		}
		
		pw.println(beat);
		pw.close();
		br.close();
	}

}
