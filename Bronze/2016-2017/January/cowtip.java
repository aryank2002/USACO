
import java.util.*;
import java.io.*;

public class cowtip {

	public static void main(String[] args) throws IOException {
		
		File file = new File("cowtip.in");
		BufferedReader br = new BufferedReader(new FileReader(file));
		StringTokenizer st = new StringTokenizer(br.readLine());

		File outFile = new File("cowtip.out");
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(outFile)));
		
		int N = Integer.parseInt(st.nextToken());
		char[][] cowgrid = new char[N][N];

		for(int a = 0; a< N; a++){
			String s = br.readLine();
			for(int b = 0; b<N; b++){
				cowgrid[a][b] = s.charAt(b);
			}
		}
		int count = 0;
		
		for (int i = (N-1); i > -1; i += -1) {
			for (int k = (N - 1); k > -1; k += -1) {
				if (cowgrid[i][k] == '1') {
					for (int a = 0; a < i+1; a++) {
						for (int b = 0; b < k+1; b++) {
							if(cowgrid[a][b] == '0'){
								cowgrid[a][b] = '1';
							}
							else{
								cowgrid[a][b] = '0';
							}
						}
					}
					count++;
				}
			}
		}
		pw.println(count);
		pw.close();
		br.close();
		
	}
}
