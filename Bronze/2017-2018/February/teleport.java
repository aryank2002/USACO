import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class teleport {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("teleport.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("teleport.out")));
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		
		int min = Math.abs(b - a);
		int temp = Math.abs(x - a) + Math.abs(y - b);
		if (min > temp){
			min = temp;
		}
		temp = Math.abs(x - b) + Math.abs(y - a);
		if (min > temp){
			min = temp;
		}
		
		pw.println(min);
		pw.close();
		br.close();

	}

}
