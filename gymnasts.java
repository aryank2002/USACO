import java.util.*;
import java.io.*;

public class gymnasts {
	
	static long N;
	static long mod;
	static ArrayList<State> primefact;
	static long length;
	static long total;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("gymnasts.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("gymnasts.out")));
		
		N = Long.parseLong(br.readLine());
		
		primefact = new ArrayList<State>();
		primefact.add(new State(0, 0));
		
		long p = 2;
		long copy = N;
		while (copy != 1) {
			if (copy % p == 0){
				copy = copy / p;
				if (primefact.get(primefact.size() - 1).prime == p){
					primefact.get(primefact.size() - 1).power++;
				}
				else {
					primefact.add(new State(p, 1));
				}
			}
			else {
				p++;
				if (primefact.size() == 1 && p * p > N) break;
			}
		}
		
		mod = 1000000007;
		total = N % mod;
		length = primefact.size() - 1;
		
		long[] pows = new long[(int) length + 1];
		DFS (0L, pows);
		
		pw.println(total);
		br.close();
		pw.close();
	}
	
	static void DFS (long cur, long[] powers){
		if (cur == length){
			long prod = 1;
			long totient = 1;
			
			for (int i = 1; i <= length; i++){
				prod = prod * (long) Math.pow(primefact.get(i).prime, powers[i]);
				if (powers[i] == primefact.get(i).power) continue;
				totient = totient * (primefact.get(i).prime - 1)
						* (long) Math.pow(primefact.get(i).prime, primefact.get(i).power - powers[i] - 1);
			}
			
			if (prod == 1 || prod == N) return;
			total = (total + ((power(prod) - 2) * totient) % mod) % mod;
		}
		else {
			for (long i = 0; i <= primefact.get((int) cur + 1).power; i++){
				powers[(int) cur + 1] = i;
				DFS (cur + 1, powers);
			}
		}
	}
	
	static long power (long raise){
		if (raise == 0) return 1;
		if (raise == 1) return 2;
		
		long ans = power(raise / 2);
		ans = (ans * ans + mod) % mod;
		if (raise % 2 == 1) ans = (2 * ans) % mod;
		return ans;
	}
	
	static class State {
		public long prime, power;
		public State (long a, long b){
			prime = a;
			power = b;
		}
	}
}