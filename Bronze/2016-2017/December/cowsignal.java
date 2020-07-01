import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class cowsignal {

	public static void main(String[] args) throws Exception {
	    
		BufferedReader br = new BufferedReader(new FileReader("cowsignal.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());

		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowsignal.out")));

		byte M = Byte.parseByte(st.nextToken());
		byte N = Byte.parseByte(st.nextToken());
		byte K = Byte.parseByte(st.nextToken());
		
		for (byte i = 0; i < M; i++) {
			String string = br.readLine();
			for (byte n = 0; n < K; n++) {
				for (byte m = 0; m < N; m++) {
					for (byte k = 0; k < K; k++) {
						pw.print(string.charAt(m));
					}
				}
				
				pw.println();
			}
		}
		
		pw.close();
		br.close();
		
	}

}
