import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class hps {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("hps.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		String[] data = new String[N + 1];
		for (int i = 1; i <= N; i++){
			st = new StringTokenizer(br.readLine());
			data[i] = st.nextToken();
		}
		
		int[] ps = new int[N + 1];
		int[] hs = new int[N + 1];
		int[] ss = new int[N + 1];
		int[] pback = new int[N + 1];
		int[] hback = new int[N + 1];
		int[] sback = new int[N + 1];
		
		int P = 0;
		int H = 0;
		int S = 0;
		
		for (int i = 1; i <= N; i++){
			if (data[i].equals("H")){
				hs[i] = hs[i - 1] + 1;
				ss[i] = ss[i - 1];
				ps[i] = ps[i - 1];
				H++;
			}
			else if (data[i].equals("S")){
				ss[i] = ss[i - 1] + 1;
				hs[i] = hs[i - 1];
				ps[i] = ps[i - 1];
				S++;
			}
			else if (data[i].equals("P")){
				ps[i] = ps[i - 1] + 1;
				ss[i] = ss[i - 1];
				hs[i] = hs[i - 1];
				P++;
			}
		}
		
		int[] maxf = new int[N + 1];
		int[] maxb = new int[N + 1];
		for (int i = 1; i <= N; i++){
			maxf[i] = Math.max(Math.max(ss[i], ps[i]), hs[i]);
			pback[i] = P - ps[i - 1];
			hback[i] = H - hs[i - 1];
			sback[i] = S - ss[i - 1];
		}
		
		for (int i = 1; i <= N; i++){
			maxb[i] = Math.max(Math.max(pback[i], sback[i]), hback[i]);
		}
		
		int max = 0;
		for (int i = 1; i <= N; i++){
			if (maxf[i - 1] + maxb[i] > max){
				max = maxf[i - 1] + maxb[i];
			}
		}
		
		pw.println(max);
		br.close();
		pw.close();
	}

}
