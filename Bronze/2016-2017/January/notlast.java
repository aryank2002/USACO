
import java.util.*;
import java.io.*;

public class notlast {

	public static void main(String[] args) throws IOException {

		File file = new File("notlast.in");
		BufferedReader br = new BufferedReader(new FileReader(file));
		StringTokenizer st = new StringTokenizer(br.readLine());

		File outFile = new File("notlast.out");
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(outFile)));

		int N = Integer.parseInt(st.nextToken());

		int[] cowmilk = new int[7];
		
		for (int i = 0; i < 7; i++) {
			cowmilk[i] = 0;
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int milk = Integer.parseInt(st.nextToken());
			if (name.equals("Bessie")) {
				cowmilk[0] = cowmilk[0] + milk;
			}
			if (name.equals("Elsie")) {
				cowmilk[1] = cowmilk[1] + milk;
			}
			if (name.equals("Daisy")) {
				cowmilk[2] = cowmilk[2] + milk;
			}
			if (name.equals("Gertie")) {
				cowmilk[3] = cowmilk[3] + milk;
			}
			if (name.equals("Annabelle")) {
				cowmilk[4] = cowmilk[4] + milk;
			}
			if (name.equals("Maggie")) {
				cowmilk[5] = cowmilk[5] + milk;
			}
			if (name.equals("Henrietta")) {
				cowmilk[6] = cowmilk[6] + milk;
			}
		}
		int m= 1000;
		for (int i = 0; i < 7; i++) {
			if (cowmilk[i] < m) {
				m = cowmilk[i];
			}
		}

		for (int i = 0; i < 7; i++) {
			if (cowmilk[i] == m) {
				cowmilk[i] = 10001;
			}
		}
		
		m = 1000;
		
		for (int i = 0; i < 7; i++) {
			if (cowmilk[i] < m) {
				m = cowmilk[i];
			}
		}

		int count = 0;

		for (int i = 0; i < 7; i++) {
			if (cowmilk[i] == m) {
				count++;
			}
		}
		if(count == 1) {
			for (int i = 0; i < 7; i++) {
				if (cowmilk[i] == m) {
					if (i == 0) {
						pw.println("Bessie");
					}
					if (i == 1) {
						pw.println("Elsie");
					}
					if (i == 2) {
						pw.println("Daisy");
					}
					if (i == 3) {
						pw.println("Gertie");
					}
					if (i == 4) {
						pw.println("Annabelle");
					}
					if (i == 5) {
						pw.println("Maggie");
					}
					if (i == 6) {
						pw.println("Henrietta");
					}
				}
			}
		}
		else{
			pw.println("Tie");
		}

		br.close();
		pw.close();

	}

}
