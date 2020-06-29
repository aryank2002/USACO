import java.util.*;
import java.io.*;

public class convention2{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("convention2.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("convention2.out")));
		int N = Integer.parseInt(st.nextToken());
		
		int maxwait = Integer.MIN_VALUE;
		int[] start = new int[N + 1];
		int[] end = new int[N + 1];
		
		int[] vistime = new int[N + 1];
		int[] usetime = new int[N + 1];
		
		// time, id
		TreeMap<Integer, LinkedList<Integer>> sorter = new TreeMap<Integer, LinkedList<Integer>>();
		PriorityQueue<Integer> waiting = new PriorityQueue<Integer>();
		LinkedList<Integer> noarrived = new LinkedList<Integer>();
		
		for (int i = 1; i <= N; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			vistime[i] = a;
			usetime[i] = b;
			
			if (sorter.containsKey(a)){
				sorter.get(a).add(i);
			}
			else {
				LinkedList<Integer> t = new LinkedList<Integer>();
				t.add(i);
				sorter.put(a, t);
			}
		}
		
		while (!sorter.isEmpty()){
			int key = sorter.firstKey();
			for (int val: sorter.get(key)){
				noarrived.add(val);
			}
			sorter.remove(key);
		}
				
		while (!noarrived.isEmpty()){
			int index = noarrived.pollFirst();
			start[index] = vistime[index];
			int time = vistime[index];
			waiting.add(index);
			
			while (!waiting.isEmpty()){
				index = waiting.poll();
				end[index] = time;
				time = time + usetime[index];
				
				while (!noarrived.isEmpty() && vistime[noarrived.getFirst()] <= time){
					start[noarrived.getFirst()] = vistime[noarrived.getFirst()];
					waiting.add(noarrived.pollFirst());
				}
			}
		}
		
		for (int i = 1; i <= N; i++){
			maxwait = Math.max(maxwait, end[i] - start[i]);
		}
		
		pw.println(maxwait);
		pw.close();
		br.close();
	}
}
