import java.util.*;
import java.io.*;

public class dishes {
	
	static int N;
	static int[] data;
	
	static boolean Checker(int k){
		int[] corr = new int[k + 1];
		for (int i = 1; i <= k; i++){
			corr[i] = data[i];
		}
		
		Arrays.sort(corr);
		int[] tester = new int[k + 1];
		
		// index of tester
		int index = 1;
		int numhead = 0;
		int headindex = 0;
		
		TreeMap<Integer, Integer> heads = new TreeMap<Integer, Integer>();
		ArrayList<LinkedList<Integer>> stacks = new ArrayList<LinkedList<Integer>>();
		
		for (int i = 1; i <= k; i++){
			if (heads.ceilingKey(data[i]) != null){
				int stacknum = heads.get(heads.ceilingKey(data[i]));
				heads.put(data[i], stacknum);
				stacks.get(stacknum).addFirst(data[i]);;
			}
			else {
				heads.put(data[i], numhead);
				numhead++;
				LinkedList<Integer> l = new LinkedList<Integer>();
				l.add(data[i]);
				stacks.add(l);
			}
			
			// maintain no sorted prefix
			while (headindex < numhead){
				while (stacks.get(headindex).size() > 0){
					if (stacks.get(headindex).peekFirst() == corr[index]){
						tester[index] = stacks.get(headindex).pollFirst();
						index++;
					}
					else {
						break;
					}
				}
				if (stacks.get(headindex).size() == 0){
					headindex++;
				}
				else {
					break;
				}
			}
			// end
		}
		
		// remember to add everything to array
		for (int i = headindex; i < numhead; i++){
			for (int val: stacks.get(i)){
				tester[index] = val;
				index++;
			}
		}
		
		for (int i = 1; i <= k; i++){
			if (tester[i] != corr[i]) return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("dishes.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("dishes.out")));
		
		N = Integer.parseInt(br.readLine());
		data = new int[N + 1];
		for (int i = 1; i <= N; i++){
			data[i] = Integer.parseInt(br.readLine());
		}
		
		int min = 0;
		int max = N;
		
		while (min != max){
			int mid = (min + max + 1) / 2;
			if (Checker(mid)){
				min = mid;
			}
			else {
				max = mid - 1;
			}
		}
		
		pw.println(min);
		pw.close();
		br.close();
	}
}
