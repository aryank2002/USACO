import java.util.*;
import java.io.*;

public class pairup {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("pairup.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pairup.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		TreeMap<Integer, Integer> data = new TreeMap<Integer, Integer>();
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int output = Integer.parseInt(st.nextToken());
			
			if (data.containsKey(output)){
				data.put(output, data.get(output) + num);
			}
			else {
				data.put(output, num);
			}
		}
		
		int max = 0;
		while (!data.isEmpty()){
			int o1 = data.firstKey();
			int o2 = data.lastKey();
			
			int n1 = data.get(o1);
			int n2 = data.get(o2);
			max = Math.max(max, o1 + o2);
			
			if (n1 > n2){
				data.put(o1, n1 - n2);
				data.remove(o2);
			}
			else if (n1 < n2){
				data.put(o2, n2 - n1);
				data.remove(o1);
			}
			else {
				data.remove(o1);
				data.remove(o2);
			}
		}
		
		pw.println(max);
		br.close();
		pw.close();
	}
}
