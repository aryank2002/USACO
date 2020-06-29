import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class cowcode {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cowcode.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowcode.out")));
		String word = " " + st.nextToken();
		int length = word.length() - 1;
		
		long N = Long.parseLong(st.nextToken());
		long search = N;
		long temp = (long) length;
		long power = -1;
		
		while (temp < N){
			power++;
			temp *= 2;
		}
		
		while (!(search <= length)){
			long rem = (long) (search -  (long) length * (long) Math.pow(2, power) - 1);
			if (rem == 0){
				search--;
			}
			else {
				search = rem;
			}
			temp = length;
			power = -1;
			while (temp < search){
				power++;
				temp *= 2;
			}
		}
		
		pw.println(word.substring( (int) search, (int) search + 1));
		br.close();
		pw.close();

	}

}
