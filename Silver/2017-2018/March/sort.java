import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class sort {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("sort.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));
		int N = Integer.parseInt(br.readLine());
		
		Index[] list = new Index[N];
		for (int i = 0; i < N; i++){
			list[i] = new Index(Integer.parseInt(br.readLine()), i);
		}
		Arrays.sort(list);
		int answer = 0;
		for (int i = 0; i < N; i++){
			answer = Math.max(answer, list[i].index - i);
		}
		answer++;
		pw.println(answer);
		pw.close();
		br.close();
	}
	
	static class Index implements Comparable<Index> {
		public int val, index;
		public Index(int a, int b) {
			val = a;
			index = b;
		}
		public int compareTo(Index s) {
			return val - s.val;
		}
	}

}
