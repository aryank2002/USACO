import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class paint {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("paint.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("paint.out"));
		PrintWriter pw = new PrintWriter(new PrintWriter(bw));
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		
		int c = Integer.parseInt(st2.nextToken());
		int d = Integer.parseInt(st2.nextToken());
		
		int min = 0;
		int max = 0;
		
		if (a > c){
			min = c;
		}
		else{
			min = a;
		}
		
		if (d > b){
			max = d;
		}
		else{
			max = b;
		}
		
		int length = 0;
		
		for (int i = min + 1; i < max + 1; i++){
			if ( b >= i & i>= a & b >= (i-1) & (i-1) >= a){
				length++;
			}
			else if ( d >= i & i >= c & d >= (i-1) & (i-1) >= c){
				length++;
			}
		}
		
		pw.println(length);
		br.close();
		pw.close();
	}

}
