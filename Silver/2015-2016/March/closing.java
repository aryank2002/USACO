import java.io.*;
import java.util.*;

public class closing {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("closing.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("closing.out")));
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		HashSet<Integer>[] connect = new HashSet[N + 1];
		for (int i = 0; i <= N; i++){
			connect[i] = new HashSet<Integer>();
		}
		
		for (int i = 0; i < M; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			connect[a].add(b);
			connect[b].add(a);
		}
		boolean[] acceptable = new boolean[N + 1];
		Arrays.fill(acceptable, true);
		
		int found = 1;
		LinkedList<Integer> q = new LinkedList<Integer>();
		boolean[] explored = new boolean[N + 1];
		explored[1] = true;
		q.add(1);
		
		while (!q.isEmpty()){
			for (int val: connect[q.remove()]){
				if (explored[val] == false){
					found++;
					explored[val] = true;
					q.add(val);
				}
			}
		}
		if (found == N){
			pw.println("YES");
		}
		else {
			pw.println("NO");
		}
		
		int counter = N;
		for (int i = 1; i < N; i++){
			counter--;
			acceptable[Integer.parseInt(br.readLine())] = false;
			int k = 0;
			for (k = 1; k <= N; k++){
				if (acceptable[k] == true){
					break;
				}
			}
			found = 1;
			
			q = new LinkedList<Integer>();
			explored = new boolean[N + 1];
			explored[k] = true;
			q.add(k);
			
			while (!q.isEmpty()){
				for (int val: connect[q.remove()]){
					if (explored[val] == false && acceptable[val] == true){
						found++;
						explored[val] = true;
						q.add(val);
					}
				}
			}
			if (found == counter){
				pw.println("YES");
			}
			else {
				pw.println("NO");
			}
		}
		
		pw.close();
		br.close();
	}
}
