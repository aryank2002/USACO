import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class moocast {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("moocast.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
		int N = Integer.parseInt(st.nextToken());
		int[][] data = new int[3][N];
		ArrayList<ArrayList<Integer>> connected = new ArrayList<ArrayList<Integer>>();
		
		int max = 0;
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			data[0][i] = Integer.parseInt(st.nextToken());
			data[1][i] = Integer.parseInt(st.nextToken());
			data[2][i] = Integer.parseInt(st.nextToken());
			connected.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < N; i++){
			int x = data[0][i];
			int y = data[1][i];
			int p = data[2][i];
			for (int k = 0; k < i; k++){
				if (p * p >= (x - data[0][k]) * (x - data[0][k]) + (y - data[1][k]) * (y - data[1][k])){
					connected.get(i).add(k);
				}
			}
			for (int k = i + 1; k < N; k++){
				if (p * p >= (x - data[0][k]) * (x - data[0][k]) + (y - data[1][k]) * (y - data[1][k])){
					connected.get(i).add(k);
				}
			}
		}
		for (int i = 0; i < N; i++){
			int counter = 1;
			boolean[] explored = new boolean[N];
			explored[i] = true;
			
			LinkedList<Integer> path = new LinkedList<Integer>();
			for (int val: connected.get(i)){
				explored[val] = true;
				path.add(val);
				counter++;
			}
			while (!path.isEmpty() && counter != N){
				int value = path.removeFirst();
				for (int val: connected.get(value)){
					if (explored[val] == false){
						explored[val] = true;
						path.addLast(val);
						counter++;
					}
				}
			}
			if (counter > max){
				max = counter;
			}
			
		}
		pw.println(max);
		pw.close();
		br.close();
	}

}
