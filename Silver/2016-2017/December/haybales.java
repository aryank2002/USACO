import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class haybales {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		int[] locations = new int[N + 1];
		locations[0] = -1;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++){
			locations[i + 1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(locations);
		TreeMap<Integer, Integer> sorthay = new TreeMap<Integer, Integer>();
		int counter = 0;
		for (int i = 0; i < N + 1; i++){
			sorthay.put(locations[i], counter);
			counter++;
		}
		for (int i = 0; i < Q; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			pw.println(sorthay.get(sorthay.floorKey(b)) - sorthay.get(sorthay.floorKey(a - 1)));
		}
		pw.close();
		br.close();
	}

}
