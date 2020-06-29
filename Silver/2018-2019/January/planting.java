import java.util.*;
import java.io.*;

public class planting {
	
	static int counter = 1;
	static HashSet<Integer>[] nope;
	static ArrayList<Integer>[] connections;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("planting.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("planting.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		nope = new HashSet[N + 1];
		connections = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++){
			nope[i] = new HashSet<Integer>();
			connections[i] = new ArrayList<Integer>();
		}
		for (int i = 1; i < N; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			connections[a].add(b);
			connections[b].add(a);
		}
		for (int i = 1; i <= N; i++){
			counter = Math.max(counter, connections[i].size());
		}
		
		pw.println(counter + 1);
		br.close();
		pw.close();
	}
}
