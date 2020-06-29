import java.util.*;
import java.io.*;

public class lasers {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("lasers.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lasers.out")));
		
		int N = Integer.parseInt(st.nextToken());
		HashMap<Integer, HashSet<Integer>> samex = new HashMap<Integer, HashSet<Integer>>();
		HashMap<Integer, HashSet<Integer>> samey = new HashMap<Integer, HashSet<Integer>>();
		
		int[] xs = new int[N + 2];
		int[] ys = new int[N + 2];
		xs[0] = Integer.parseInt(st.nextToken());
		ys[0] = Integer.parseInt(st.nextToken());
		
		HashSet<Integer> t5 = new HashSet<Integer>();
		t5.add(0);
		HashSet<Integer> t6 = new HashSet<Integer>();
		t6.add(0);
		samex.put(xs[0], t5);
		samey.put(ys[0], t6);
		
		xs[N + 1] = Integer.parseInt(st.nextToken());
		ys[N + 1] = Integer.parseInt(st.nextToken());
		
		HashSet<Integer> t = new HashSet<Integer>();
		t.add(N + 1);
		HashSet<Integer> ti = new HashSet<Integer>();
		ti.add(N + 1);
		samex.put(xs[N + 1], t);
		samey.put(ys[N + 1], ti);
		
		for (int i = 1; i <= N; i++){
			st = new StringTokenizer(br.readLine());
			xs[i] = Integer.parseInt(st.nextToken());
			ys[i] = Integer.parseInt(st.nextToken());
			
			if (samex.containsKey(xs[i])){
				samex.get(xs[i]).add(i);
			}
			else {
				HashSet<Integer> t1 = new HashSet<Integer>();
				t1.add(i);
				samex.put(xs[i], t1);
			}
			
			if (samey.containsKey(ys[i])){
				samey.get(ys[i]).add(i);
			}
			else {
				HashSet<Integer> t3 = new HashSet<Integer>();
				t3.add(i);
				samey.put(ys[i], t3);
			}
		}
		
		boolean[] explored = new boolean[N + 2];
		int[] distances = new int[N + 2];
		Arrays.fill(distances, -1);
		explored[0] = true;
		
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		q.add(0);
		while (!q.isEmpty()){
			if (distances[N + 1] != -1){
				break;
			}
			int node = q.poll();
			if (samex.containsKey(xs[node])){
				ArrayList<Integer> temp = new ArrayList<Integer>();
				for (int val: samex.get(xs[node])){
					if (val != node && explored[val] == false){
						explored[val] = true;
						q.add(val);
						distances[val] = distances[node] + 1;
					}
					if (explored[val] == true){
						temp.add(val);
					}
				}
				for (int val: temp){
					samex.get(xs[node]).remove(val);
				}
			}
			
			if (samey.containsKey(ys[node])){
				ArrayList<Integer> temp = new ArrayList<Integer>();
				for (int val: samey.get(ys[node])){
					if (val != node && explored[val] == false){
						explored[val] = true;
						q.add(val);
						distances[val] = distances[node] + 1;
					}
					if (explored[val] == true){
						temp.add(val);
					}
				}
				for (int val: temp){
					samey.get(ys[node]).remove(val);
				}
			}
		}
		
		pw.println(distances[N + 1]);
		pw.close();
		br.close();
	}
}
