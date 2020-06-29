import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;

public class gates {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("gates.in"));
		int N = Integer.parseInt(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("gates.out")));
		String s = br.readLine();
		
		// N: 1; E: 2; S: 3; W: 4
		HashMap<Integer, HashMap<Integer, HashSet<Integer>>> visited = new HashMap<Integer, HashMap<Integer, HashSet<Integer>>>();
		HashSet<Integer> t1 = new HashSet<Integer>();
		HashMap<Integer, HashSet<Integer>> t2 = new HashMap<Integer, HashSet<Integer>>();
		t2.put(0, t1);
		visited.put(0, t2);
		
		int counter = 0;
		int x = 0;
		int y = 0;
		for (int i = 0; i < N; i++){
			String temp = s.substring(i, i + 1);
			int xorig = x;
			int yorig = y;
			
			int a = 0;
			int b = 0;
			if (temp.equals("N")){
				y++;
				a = 1;
				b = 3;
			}
			if (temp.equals("E")){
				x++;
				a = 2;
				b = 4;
			}
			if (temp.equals("S")){
				y--;
				a = 3;
				b = 1;
			}
			if (temp.equals("W")){
				x--;
				a = 4;
				b = 2;
			}
			
			if (visited.containsKey(x) && visited.get(x).containsKey(y)){
				if (!visited.get(x).get(y).contains(b)){
					visited.get(x).get(y).add(b);
					counter++;
				}
				visited.get(xorig).get(yorig).add(a);
			}
			else {
				if (visited.containsKey(x)){
					visited.get(xorig).get(yorig).add(a);
					t1 = new HashSet<Integer>();
					t1.add(b);
					visited.get(x).put(y, t1);
				}
				else {
					t1 = new HashSet<Integer>();
					t1.add(b);
					t2 = new HashMap<Integer, HashSet<Integer>>();
					t2.put(y, t1);
					visited.put(x, t2);
					
					visited.get(xorig).get(yorig).add(a);
				}
			}
		}
		
		pw.println(counter);
		br.close();
		pw.close();
	}
}
