import java.util.*;
import java.io.*;

public class piepie {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("piepie.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("piepie.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		int[][] bpies = new int[N + 1][2];
		int[][] epies = new int[N + 1][2];
		
		LinkedList<State> q = new LinkedList<State>();
		int[] answers = new int[N + 1];
		Arrays.fill(answers, -1);
		
		Sort[] b1 = new Sort[N];
		Sort[] e1 = new Sort[N];
		
		// 0 --> Bessie
		boolean[][] explored = new boolean[N + 1][2];
		TreeSet<Integer> bvals = new TreeSet<Integer>();
		TreeSet<Integer> evals = new TreeSet<Integer>();
		
		for (int i = 1; i <= N; i++){
			st = new StringTokenizer(br.readLine());
			bpies[i][0] = Integer.parseInt(st.nextToken());
			bpies[i][1] = Integer.parseInt(st.nextToken());
			bvals.add(bpies[i][1]);
			
			b1[i - 1] = new Sort(bpies[i][1], i);
			
			if (bpies[i][1] == 0){
				explored[i][0] = true;
				q.add(new State(i, 0, 1));
				answers[i] = 1;
			}
		}
		
		for (int i = 1; i <= N; i++){
			st = new StringTokenizer(br.readLine());
			epies[i][1] = Integer.parseInt(st.nextToken());
			epies[i][0] = Integer.parseInt(st.nextToken());
			evals.add(epies[i][1]);
			
			e1[i - 1] = new Sort(epies[i][1], i);
			
			if (epies[i][1] == 0){
				explored[i][1] = true;
				q.add(new State(i, 1, 1));
			}
		}
		Arrays.sort(b1);
		Arrays.sort(e1);
		
		int[] b = new int[N];
		int[] e = new int[N];
		
		for (int i = 0; i < N; i++){
			b[i] = b1[i].othertaste;
			e[i] = e1[i].othertaste;
		}
		
		while (!q.isEmpty()){
			State s = q.poll();
			
			if (s.bessie == 0){
				// need to find the numbers between bpies[s.vertex] - D to bpies[s.vertex]
				if (evals.ceiling(bpies[s.vertex][0] - D) == null || evals.floor(bpies[s.vertex][0]) == null) continue;
				int x = Arrays.binarySearch(e, evals.ceiling(bpies[s.vertex][0] - D));
				int y = Arrays.binarySearch(e, evals.floor(bpies[s.vertex][0]));
				
				while (x > 1 && e[x - 1] == e[x]){
					x--;
				}
				while (y < N - 1 && e[y + 1] == e[y]){
					y++;
				}
				
				for (int index = x; index <= y; index++){
					int i = e1[index].vertex;
					if (epies[i][1] <= bpies[s.vertex][0] && epies[i][1] >= (bpies[s.vertex][0] - D) && explored[i][1] == false){
						explored[i][1] = true;
						q.add(new State(i, 1, s.moves + 1));
					}
				}
			}
			else {
				if (bvals.ceiling(epies[s.vertex][0] - D) == null || bvals.floor(epies[s.vertex][0]) == null) continue;
				int x = Arrays.binarySearch(b, bvals.ceiling(epies[s.vertex][0] - D));
				int y = Arrays.binarySearch(b, bvals.floor(epies[s.vertex][0]));
				
				while (x > 1 && b[x - 1] == b[x]){
					x--;
				}
				while (y < N - 1 && b[y + 1] == b[y]){
					y++;
				}
				
				for (int in = x; in <= y; in++){
					int i = b1[in].vertex;
					if (bpies[i][1] <= epies[s.vertex][0] && bpies[i][1] >= (epies[s.vertex][0] - D) && explored[i][0] == false){
						explored[i][0] = true;
						q.add(new State(i, 0, s.moves + 1));
						answers[i] = s.moves + 1;
					}
				}
			}
			
		}
		
		for (int i = 1; i <= N; i++){
			pw.println(answers[i]);
		}
		
		pw.close();
		br.close();
	}
	
	static class State {
		public int vertex, bessie, moves;
		public State (int a, int b, int c){
			vertex = a;
			bessie = b;
			moves = c;
		}
	}
	
	static class Sort implements Comparable<Sort>{
		public int othertaste, vertex;
		public Sort (int a, int b) {
			othertaste = a;
			vertex = b;
		}
		public int compareTo (Sort other) {
			return othertaste - other.othertaste;
		}
		
		public int equalTo () {
			return othertaste;
		}
	}
}
