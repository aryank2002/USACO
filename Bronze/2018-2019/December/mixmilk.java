import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class mixmilk {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("mixmilk.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long[] capacity = new long[3];
		long[] milkcur = new long[3];
		
		capacity[0] = Long.parseLong(st.nextToken());
		milkcur[0] = Long.parseLong(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		capacity[1] = Long.parseLong(st.nextToken());
		milkcur[1] = Long.parseLong(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		capacity[2] = Long.parseLong(st.nextToken());
		milkcur[2] = Long.parseLong(st.nextToken());
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mixmilk.out")));
		
		for (int i = 0; i < 33; i++){
			if (capacity[1] >= (milkcur[0] + milkcur[1])){
				milkcur[1] = milkcur[0] + milkcur[1];
				milkcur[0] = 0;
			}
			else {
				milkcur[0] = milkcur[0] + milkcur[1] - capacity[1];
				milkcur[1] = capacity[1];
			}
			
			if (capacity[2] >= (milkcur[1] + milkcur[2])){
				milkcur[2] = milkcur[1] + milkcur[2];
				milkcur[1] = 0;
			}
			else {
				milkcur[1] = milkcur[1] + milkcur[2] - capacity[2];
				milkcur[2] = capacity[2];
			}
			
			if (capacity[0] >= (milkcur[2] + milkcur[0])){
				milkcur[0] = milkcur[2] + milkcur[0];
				milkcur[2] = 0;
			}
			else {
				milkcur[2] = milkcur[2] + milkcur[0] - capacity[0];
				milkcur[0] = capacity[0];
			}
		}
		
		if (capacity[1] >= (milkcur[0] + milkcur[1])){
			milkcur[1] = milkcur[0] + milkcur[1];
			milkcur[0] = 0;
		}
		else {
			milkcur[0] = milkcur[0] + milkcur[1] - capacity[1];
			milkcur[1] = capacity[1];
		}
		
		pw.println(milkcur[0]);
		pw.println(milkcur[1]);
		pw.println(milkcur[2]);
		
		pw.close();
		br.close();

	}

}
