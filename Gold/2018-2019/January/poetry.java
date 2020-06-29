import java.util.*;
import java.io.*;

public class poetry {
	
	static long power(long base, long pow){
		long t = 1;
		for (int i = 0; i < pow; i++){
			t = (t * base) % 1000000007;
		}
		return t;
	}

	public static void main(String[] args) throws IOException {
    // USE FAST-EXPO for FULL SOLUTION
  
		BufferedReader br = new BufferedReader(new FileReader("poetry.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("poetry.out")));
		
		long N = Long.parseLong(st.nextToken());
		long M = Long.parseLong(st.nextToken());
		long K = Long.parseLong(st.nextToken());
		
		long[] syllable = new long[(int) N + 1];
		long[] rhyme = new long[(int) N + 1];
		for (int i = 1; i <= N; i++){
			st = new StringTokenizer(br.readLine());
			syllable[i] = Long.parseLong(st.nextToken());
			rhyme[i] = Long.parseLong(st.nextToken());
		}
		
		long[] combo = new long[(int) K + 1];
		combo[0] = 1;
		long mod = 1000000007;
		
		for (int i = 1; i <= K; i++){
			for (int k = 1; k <= N; k++){
				if (syllable[k] > i){
					continue;
				}
				combo[i] = (combo[i] + combo[ (int) (i - syllable[k])]) % mod;
			}
		}
		
		// rhyme --> combinations
		long[] type = new long[(int) N + 1];
		
		for (int i = 1; i <= N; i++){
			type[(int) rhyme[i]] = (type[(int) rhyme[i]] + combo[(int) (K - syllable[i])] ) % mod;
		}

		long[] rtype = new long[26];
		for (int i = 1; i <= M; i++){
			rtype[br.readLine().compareTo("A")]++;
		}
		
		Arrays.sort(rtype);
		long ans = 1;
		
		for (int i = 0; i < 26; i++){
			long pow = rtype[i];
			if (pow == 0){
				continue;
			}
			long temp = 0;
			for (int k = 1; k <= N; k++){
				if (type[k] == 0){
					continue;
				}
				temp = (temp + power(type[k], pow)) % mod;
			}
			ans = (ans * temp) % mod;
		}
		pw.println(ans);
		
		pw.close();
		br.close();
	}
}
