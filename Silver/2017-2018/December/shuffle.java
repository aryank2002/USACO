import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class shuffle {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("shuffle.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));
		int N = Integer.parseInt(st.nextToken());
		
		HashSet<Integer> perman = new HashSet<Integer>();
		int[] current = new int[N + 1];
		int[] shuffle = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++){
			perman.add(i+1);
			shuffle[i + 1] = Integer.parseInt(st.nextToken());
			current[i + 1] = 1;
		}
		for (int i = 0; i < 700; i++){
			
			int[] after = new int[N + 1];
			for (int k = 1; k < N + 1; k++){
				after[shuffle[k]]+=current[k];
			}
			HashSet<Integer> temp = new HashSet<Integer>();
			for (int var: perman){
				if (after[var] == 0){
					temp.add(var);
				}
			}
			for (int var:temp){
				perman.remove(var);
			}
			current = after;
		}
		pw.println(perman.size());
		br.close();
		pw.close();

	}

}
