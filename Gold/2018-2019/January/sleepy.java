import java.util.*;
import java.io.*;

public class sleepy {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("sleepy.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sleepy.out")));
		
		int N = Integer.parseInt(st.nextToken());
		int[] data = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++){
			data[i] = Integer.parseInt(st.nextToken());
		}
		
		int back = N;
		while (back > 0 && data[back - 1] < data[back]){
			back--;
		}
		
		BIT b = new BIT(N);
		for (int i = back; i <= N; i++){
			b.update(data[i]);
		}
		
		pw.println(back - 1);
		for (int i = 1; i < back - 1; i++){
			int t = b.sum(data[i] - 1);
			t += back - i - 1;
			pw.print(t + " ");
			b.update(data[i]);
		}
		pw.print(b.sum(data[back - 1] - 1));
		
		pw.close();
		br.close();
	}
	
	static class BIT {
		public int[] tree;
		public BIT (int N){
			tree = new int[N + 1];
		}
		public void update(int index){
			while (index <= tree.length){
				tree[index]++;
				index += (index & -index);
			}
		}
		public int sum(int index){
			int s = 0;
			while (index >= 1){
				s += tree[index];
				index -= (index & -index);
			}
			return s;
		}
	}
}
