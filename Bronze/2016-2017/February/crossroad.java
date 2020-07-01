
import java.util.*;
import java.io.*;

public class crossroad {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("crossroad.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());

		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("crossroad.out")));

		int N = Integer.parseInt(st.nextToken());
		int[] cow = new int[10000];
		int[] position = new int[100];
		
		for(int i =0; i<11; i++){
			position[i] = 30;
		}

		int count = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());

			cow[a] = Integer.parseInt(st.nextToken());

			if ((cow[a] == 1) && (position[a] == 0)) {
				count++;
			}
			if ((cow[a] == 0) && (position[a] == 1)) {
				count++;
			}

			position[a] = cow[a];
		}

		pw.println(count);

		pw.close();
		br.close();
	}

}
