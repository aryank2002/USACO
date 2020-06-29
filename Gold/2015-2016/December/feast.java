import java.util.*;
import java.io.*;

public class feast {
	
	static boolean[] possible;
	static int A;
	static int B;
	static int T;
	
	static void Combos (int full){
		possible[full] = true;
		if (full + A <= T && possible[full + A] == false){
			Combos(full + A);
		}
		
		if (full + B <= T && possible[full + B] == false){
			Combos(full + B);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("feast.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("feast.out")));
		
		T = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		possible = new boolean[T + 1];
		
		Combos(0);
		Combos(A / 2);
		Combos(B / 2);
		if ((A + B) <= T){
			Combos((A + B) / 2);
		}
		 
	 	int i = T;
	 	while (possible[i] == false){
	 		i--;
	 	}
		
	 	pw.println(i);
		pw.close();
		br.close();
	}
}
