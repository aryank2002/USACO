import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class cownomics {
	
	public static int Converter(String a){
		if (a.equals("A")){
			return 0;
		}
		if (a.equals("G")){
			return 1;
		}
		if (a.equals("C")){
			return 2;
		}
		return 3;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cownomics.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		String[][] spotty = new String[N + 1][M + 1];
		String[][] plain = new String[N + 1][M + 1];
		
		for (int i = 1; i <= N; i++){
			st = new StringTokenizer(br.readLine());
			String temp = st.nextToken();
			for (int k = 1; k <= M; k++){
				spotty[i][k] = temp.substring(k - 1, k);
			}
		}
		
		for (int i = 1; i <= N; i++){
			st = new StringTokenizer(br.readLine());
			String temp = st.nextToken();
			for (int k = 1; k <= M; k++){
				plain[i][k] = temp.substring(k - 1, k);
			}
		}
		
		int counter = 0;
		for (int i = 1; i <= M - 2; i++){
			for (int k = i + 1; k <= M - 1; k++){
				for (int m = k + 1; m <= M; m++){
					// set for all positions
					boolean[][][] t = new boolean[4][4][4];
					boolean bool = true;
					for (int x = 1; x <= N; x++){
						t[Converter(spotty[x][i])][Converter(spotty[x][k])][Converter(spotty[x][m])] = true;
					}
					for (int x = 1; x <= N; x++){
						if (t[Converter(plain[x][i])][Converter(plain[x][k])][Converter(plain[x][m])] == true){
							bool = false;
							break;
						}
					}
					
					if (bool == true){
						counter++;
					}
				}
			}
		}
		
		pw.println(counter);
		pw.close();
		br.close();
	}

}
