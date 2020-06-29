import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class bcount {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("bcount.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new FileWriter("bcount.out"));
		PrintWriter pw = new PrintWriter(bw);
		
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		int[][] breeds = new int[4][N+1];
		
		int holsteins = 0;
		int guernseys = 0;
		int jerseys = 0;
		
		int temp;
		
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			temp = Integer.parseInt(st.nextToken());
			
			if (temp == 1){
				holsteins++;
			}
			else if (temp == 2){
				guernseys++;
			}
			else {
				jerseys++;
			}
			
			breeds[1][i+1] = holsteins;
			breeds[2][i+1] = guernseys;
			breeds[3][i+1] = jerseys;
			
		}
		
		for (int i = 0; i < Q; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			pw.println((breeds[1][b] - breeds[1][a-1]) + " " + (breeds[2][b] - breeds[2][a-1]) + " " + (breeds[3][b] - breeds[3][a-1]));
		}
		
		pw.close();
		br.close();

	}

}
