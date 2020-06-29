import java.io.*;
import java.util.*;

public class balance {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("balance.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("balance.out")));
		
		int N = Integer.parseInt(st.nextToken());
		long[] data = new long[2 * N + 1];
		
		st = new StringTokenizer(br.readLine());
		long min = 200000000000L;
		
		long firstinv = 0;
		long secinv = 0;
		// TAKE CARE OF CASES IN WHICH IT IS NOT POSSIBLE
		
		for (int i = 1; i <= 2 * N; i++){
			data[i] = Long.parseLong(st.nextToken());
		}
		
		long total = 0;
		for (int i = 1; i <= N; i++){
			if (data[i] == 0) total++;
		}
		
		// number of 1s in first half
		long first1 = (long) N - total;
		long first0 = (long) total;
		for (int i = 1; i <= N; i++){
			if (data[i] == 0) total--;
			else {
				firstinv += total;
			}
		}
		
		for (int i = N + 1; i <= 2 * N; i++){
			if (data[i] == 0) total++;
		}
		
		// number of 1s in second half
		long sec1 = (long) N - total;
		long sec0 = (long) total;
		for (int i = N + 1; i <= 2 * N; i++){
			if (data[i] == 0) total--;
			else {
				secinv += total;
			}
		}
		
		min = Math.min(min, Math.abs(secinv - firstinv));
		
		// time for zeros to go right
		ArrayList<Integer> zeros = new ArrayList<Integer>();
		long[] firstzero = new long[N + 1];
		
		long[] zswitch = new long[N + 1];
		int t = 1;
		int on = 0;
		for (int i = N; i > 0; i--){
			if (data[i] == 1) on++;
			else {
				zswitch[t] = on;
				zswitch[t] += (t - 1);
				t++;
			}
		}
		
		long[] oswitch = new long[N + 1];
		t = 1;
		on = 0;
		for (int i = N + 1; i <= 2 * N; i++){
			if (data[i] == 0) on++;
			else {
				oswitch[t] = on;
				oswitch[t] += (t - 1);
				t++;
			}
		}
		
		for (int i = 1; i <= N; i++){
			if (data[i] == 0) zeros.add(i);
		}
		int count = 0;
		while (!zeros.isEmpty()){
			count++;
			firstzero[count] = firstzero[count - 1] + Math.abs(N - zeros.remove(zeros.size() - 1));
		}
		
		LinkedList<Integer> ones = new LinkedList<Integer>();
		long[] lastone = new long[N + 1];
		
		for (int i = N + 1; i <= 2 * N; i++){
			if (data[i] == 1) ones.add(i);
		}
		
		count = 0;
		while (!ones.isEmpty()){
			count++;
			lastone[count] = lastone[count - 1] + Math.abs(N + 1 - ones.poll());
		}
		
		long inv1 = firstinv;
		long inv2 = secinv;
		
		int a = (int) Math.min(N - first1, sec1);
		for (int i = 1; i <= a; i++){
			inv1 -= first1;
			inv2 -= (N - sec1);
			
			inv1 += zswitch[i];
			inv2 += oswitch[i];
			first1++;
			sec1--;
			min = Math.min(min, i + firstzero[i] + lastone[i] + Math.abs(inv1 - inv2));
		}
		
		
		// SECOND TIME
		ArrayList<Integer> one = new ArrayList<Integer>();
		long[] firstone = new long[N + 1];
		
		for (int i = 1; i <= N; i++){
			if (data[i] == 1) one.add(i);
		}
		
		count = 0;
		while (!one.isEmpty()){
			count++;
			firstone[count] = firstone[count - 1] + Math.abs(N - one.remove(one.size() - 1));
		}
		
		LinkedList<Integer> zero = new LinkedList<Integer>();
		long[] lastzero = new long[N + 1];
		
		for (int i = N + 1; i <= 2 * N; i++){
			if (data[i] == 0) zero.add(i);
		}
		
		count = 0;
		while (!zero.isEmpty()){
			count++;
			lastzero[count] = lastzero[count - 1] + Math.abs(N + 1 - zero.poll());
		}
		
		inv1 = firstinv;
		inv2 = secinv;
		
		a = (int) Math.min(N - first0, sec0);
		for (int i = 1; i <= a; i++){
			inv1 += (N - first0 - 1);
			inv2 += (sec0 - 1);
			first0++;
			sec0--;
			min = Math.min(min, i + firstone[i] + lastzero[i] + Math.abs(inv1 - inv2));
		}
		
		// result
		pw.println(min);
		br.close();
		pw.close();
	}
}
