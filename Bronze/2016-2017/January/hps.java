
import java.util.*;
import java.io.*;

public class hps {

	public static void main(String[] args) throws IOException {

		File file = new File("hps.in");
		BufferedReader br = new BufferedReader(new FileReader(file));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		File fileout = new File("hps.out");
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileout));
		PrintWriter pw = new PrintWriter(bw);
		
		int count = 0;
		int finalcount = 0;
		int N = Integer.parseInt(st.nextToken());

		int[] a = new int[N];
		int[] b = new int[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			a[i] = Integer.parseInt(st.nextToken());
			b[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			if ((a[i] == 1) && (b[i] == 3)) {
				count++;
			}
			if ((a[i] == 2) && (b[i] == 1)) {
				count++;
			}
			if ((a[i] == 3) && (b[i] == 2)) {
				count++;
			}
		}
		if (count > finalcount) {
			finalcount = count;
		}
		count = 0;
		
		for (int i = 0; i < N; i++) {
			if ((a[i] == 1) && (b[i] == 2)) {
				count++;
			}
			if ((a[i] == 2) && (b[i] == 3)) {
				count++;
			}
			if ((a[i] == 3) && (b[i] == 1)) {
				count++;
			}
		}
		if (count > finalcount) {
			finalcount = count;
		}
		
		pw.println(finalcount);
		pw.close();

		br.close();
	}

}
