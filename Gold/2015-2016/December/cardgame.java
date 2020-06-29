import java.util.*;
import java.io.*;

public class cardgame {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cardgame.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cardgame.out")));
		
		int N = Integer.parseInt(br.readLine());
		boolean[] bessie = new boolean[2 * N + 1];
		Arrays.fill(bessie, true);
		
		int[] ecards = new int[N + 1];
		int[] bcards = new int[N + 1];
		
		for (int i = 0; i < N; i++){
			ecards[i + 1] = Integer.parseInt(br.readLine());
			bessie[ecards[i + 1]] = false;
		}
		
		int counter = 1;
		for (int i = 1; i <= 2 * N; i++){
			if (bessie[i] == true){
				bcards[counter] = i;
				counter++;
			}
		}
		
		Arrays.sort(bcards);
		int points = 0;
		
		int[] eup = new int[N / 2];
		int[] bup = new int[N / 2];
		for (int i = 1; i <= N / 2; i++){
			eup[i - 1] = ecards[i];
			bup[i - 1] = bcards[N / 2 + i];
		}
		Arrays.sort(eup);
		Arrays.sort(bup);
		
		int t = 0;
		int i = 0;
		while (t < N / 2){
			if (eup[i] < bup[t]){
				i++;
				t++;
				points++;
			}
			else {
				t++;
			}
		}
		
		int[] edown = new int[N / 2];
		int[] bdown = new int[N / 2];
		for (int k = 0; k < N / 2; k++){
			edown[k] = ecards[1 + k + N / 2];
			bdown[k] = bcards[1 + k];
		}
		Arrays.sort(edown);
		Arrays.sort(bdown);
		
		t = N / 2 - 1;
		i = N / 2 - 1;
		while (t >= 0){
			if (edown[i] > bdown[t]){
				i--;
				t--;
				points++;
			}
			else {
				t--;
			}
		}
		// for (int val : bdown) System.out.println(val);
		// for (int val : edown) System.out.println(val);
		
		pw.println(points);
		br.close();
		pw.close();
	}
}
