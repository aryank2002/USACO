import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class cowdance {
	
	static int[] duration;
	public static int Time(int K, int N){
		int max = 0;
		PriorityQueue<Integer> times = new PriorityQueue<Integer>();
		for (int i = 0; i < K; i++){
			times.add(duration[i]);
			if (duration[i] > max){
				max = duration[i];
			}
		}
		for (int i = K; i < N; i++){
			int time = times.poll() + duration[i];
			times.add(time);
			if (time > max){
				max = time;
			}
		}
		return max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cowdance.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowdance.out")));
		int N = Integer.parseInt(st.nextToken());
		int maxtime = Integer.parseInt(st.nextToken());
		
		duration =  new int[N];
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			duration[i] = Integer.parseInt(st.nextToken());
		}
		
		int size = 0;
		for (int i = 1; i <= N; i++){
			if (Time(i, N) <= maxtime){
				size = i;
				break;
			}
		}
		
		pw.println(size);
		br.close();
		pw.close();
		
	}

}
