import java.io.*;

public class cowdate {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cowdate.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowdate.out")));
		
		int N = Integer.parseInt(br.readLine());
		double[] prob = new double[N];
		for (int i = 0; i < N; i++){
			prob[i] = (double) Integer.parseInt(br.readLine()) / 1000000;
		}
		
		double max = 0;
		double sum = 0;
		double prod = 1;
		int l = 0;
		int r = 0;
		
		while (l < N){
			while (r < N && sum < 1){
				sum += prob[r] / (1 - prob[r]);
				prod *= (1 - prob[r]);
				r++;
			}
			max = Math.max(max, sum * prod);
			sum -= prob[l] / (1 - prob[l]);
			prod /= (1 - prob[l]);
			l++;
		}
		
		pw.println((int) (1000000 * max));
		pw.close();
		br.close();
	}
}