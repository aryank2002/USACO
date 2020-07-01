import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class shuffle {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("shuffle.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new FileWriter("shuffle.out"));
		PrintWriter pw = new PrintWriter(bw);
		
		int N = Integer.parseInt(st.nextToken());
		int[] shuffle = new int[N + 1];
		int[] unshuffle1 = new int[N + 1];
		int[] unshuffle2 = new int[N + 1];
		int[] unshuffle3 = new int[N + 1];
		
		int[] ids = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++){
			shuffle[i + 1] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++){
			ids[i + 1] = Integer.parseInt(st.nextToken());
		}
		
		int temp = 0;
		for (int i = 0; i < N; i++){
			temp = shuffle[i + 1];
			unshuffle1[i + 1] = ids[temp];
		}
		
		for (int i = 0; i < N; i++){
			temp = shuffle[i + 1];
			unshuffle2[i + 1] = unshuffle1[temp];
		}
		
		for (int i = 0; i < N; i++){
			temp = shuffle[i + 1];
			unshuffle3[i + 1] = unshuffle2[temp];
		}
		
		for (int i = 0; i < N; i++){
			pw.println(unshuffle3[i+1]);
		}
		
		br.close();
		pw.close();

	}

}
