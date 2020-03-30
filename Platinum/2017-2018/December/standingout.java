import java.io.*;
import java.util.*;

public class standingout {
	
	static int[][] P;
	static int stp;
	static int N;
	static StringBuilder sb;
	
	static int LCP(int x, int y){
		int ret = 0; 
		if (x == y) return sb.length() - x;
		
		for (int k = stp; k >= 0 && x < sb.length() && y < sb.length(); k--){
			if (P[x][k] == P[y][k]){
				x += (1 << k);
				y += (1 << k);
				ret += (1 << k);
			}
		}
		
		return ret;
	} 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("standingout.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("standingout.out")));
		
		N = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		
		for (int i = 0; i < N - 1; i++){
			sb.append(br.readLine());
			sb.append("?");
		}
		
		sb.append(br.readLine());
		sb.append("$");
		
		P = new int[sb.length()][20];
		String s = sb.toString();
		
		int[] loc = new int[sb.length()];
		int[] end = new int[sb.length()];
		
		int l = N - 1;
		int e = sb.length() - 1;
		end[e] = e;
		
		for (int i = 2; i <= sb.length(); i++){
			if (s.charAt(sb.length() - i) == '?'){
				e = sb.length() - i;
				l--;
			}
			
			loc[sb.length() - i] = l;
			end[sb.length() - i] = e;
		}
		
		for (int i = 0; i < sb.length(); i++){
			P[i][0] = (int) (s.charAt(i) - 'a');
		}
		
		State[] L = new State[sb.length()];
		for (int i = 0; i < sb.length(); i++) L[i] = new State(10000000, 10000000, 10000000);
		stp = 1;
		
		for (int count = 1; (count >> 1) < sb.length(); count <<= 1){
			for (int i = 0; i < sb.length(); i++){
				L[i].x = P[i][stp - 1];
				
				if (i + count < sb.length()) L[i].y = P[i + count][stp - 1];
				else L[i].y = -1;
				L[i].p = i;
			}
			
			Arrays.sort(L);
			for (int i = 0; i < sb.length(); i++){
				if (i > 0 && L[i].x == L[i - 1].x && L[i].y == L[i - 1].y) P[L[i].p][stp] = P[L[i - 1].p][stp];
				else P[L[i].p][stp] = i;
			}
			stp++;
		}
		
		stp--;
		long[] answers = new long[N];
		int[] before = new int[sb.length()];
		int[] after = new int[sb.length()];
		
		int[] copy = new int[sb.length()];
		for (int i = 0; i < sb.length(); i++) copy[P[i][stp]] = i;
		
		before[0] = -1;
		after[sb.length() - 1] = -1;
		
		for (int i = 1; i < s.length(); i++){
			int start = copy[i];
			if (s.charAt(start) == '$' || s.charAt(start) == '?') {
				before[i] = -1;
				continue;
			}
			
			if (loc[start] == loc[copy[i - 1]] || s.charAt(copy[i - 1]) == '$' || s.charAt(copy[i - 1]) == '?'){
				before[i] = before[i - 1];
			}
			else {
				before[i] = i - 1;
			}
		}
		
		for (int i = s.length() - 2; i >= 0; i--){
			int start = copy[i];
			if (s.charAt(start) == '$' || s.charAt(start) == '?') {
				after[i] = -1;
				continue;
			}
			
			if (loc[start] == loc[copy[i + 1]]){
				after[i] = after[i + 1];
			}
			else {
				after[i] = i + 1;
			}
		}
		
		for (int i = 0; i < s.length(); i++){
			int start = copy[i];
			if (s.charAt(start) == '$' || s.charAt(start) == '?') continue;
			
			int length = end[start] - start;
			int sub = 0;
			
			if (before[i] != -1){
				sub = Math.max(sub, LCP(start, copy[before[i]]));
			}
			if (after[i] != -1){
				sub = Math.max(sub, LCP(start, copy[after[i]]));
			}
			if (i != 0 && loc[start] == loc[copy[i - 1]]){
				sub = Math.max(sub, LCP(start, copy[i - 1]));
			}
			
			answers[loc[start]] += (long) Math.max(0L, length - sub);
			// System.out.println(i + " " + start + " " + before[i]);
		}
		
		for (long val: answers) pw.println(val);
		
		pw.close();
		br.close();
	}
	
	static class State implements Comparable<State>{
		public int x, y, p;
		public State(int a, int b, int c){
			x = a;
			y = b;
			p = c;
		}
		public int compareTo(State other){
			if (x != other.x) return x - other.x;
			return y - other.y;
		}
	}
}

