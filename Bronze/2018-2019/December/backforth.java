import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class backforth {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("backforth.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("backforth.out")));
		
		HashSet<Integer> possibles = new HashSet<Integer>();
		HashSet<Integer> row1 = new HashSet<Integer>();
		HashSet<Integer> row2 = new HashSet<Integer>();
		
		possibles.add(0);
		
		int[] firstnums = new int[10];
		int[] lastnums = new int[10];
		
		for (int i = 0; i < 10; i++){
			firstnums[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 10; i++){
			lastnums[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < 10; i++){
			for (int k = 0; k < 10; k++){
				if (k != i){
					row1.add(firstnums[i] + firstnums[k]);
				}
			}
		}
		
		for (int i = 0; i < 10; i++){
			for (int k = 0; k < 10; k++){
				if (k != i){
					row2.add(lastnums[i] + lastnums[k]);
				}
			}
		}
		
		for (int i = 0; i < 10; i++){
			int temp = -firstnums[i];
			for (int k = 0; k < 10; k++){
				possibles.add(temp + lastnums[k]);
			}
		}
		
		for (int val: row1) {
			for (int val2 : row2){
				possibles.add(val2 - val);
			}
		}
		
		pw.println(possibles.size());
		pw.close();
		br.close();

	}

}
