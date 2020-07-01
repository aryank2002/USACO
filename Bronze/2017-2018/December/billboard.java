import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class billboard {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("billboard.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new FileWriter("billboard.out"));
		PrintWriter pw = new PrintWriter(new PrintWriter(bw));
		
		int xleft1 = Integer.parseInt(st.nextToken());
		int yleft1 = Integer.parseInt(st.nextToken());
		int xright1 = Integer.parseInt(st.nextToken());
		int yright1 = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int xleft2 = Integer.parseInt(st.nextToken());
		int yleft2 = Integer.parseInt(st.nextToken());
		int xright2 = Integer.parseInt(st.nextToken());
		int yright2 = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int xleftb = Integer.parseInt(st.nextToken());
		int yleftb = Integer.parseInt(st.nextToken());
		int xrightb = Integer.parseInt(st.nextToken());
		int yrightb = Integer.parseInt(st.nextToken());
		
		int area = (xright1 - xleft1) * (yright1 - yleft1) + (xright2 - xleft2) * (yright2 - yleft2);
		
		for (int i = xleft1 + 1; i < xright1 + 1; i++){
			for (int k = yleft1 + 1; k < yright1 + 1; k++){
				if (i <= xrightb && xleftb <= i-1 && k <= yrightb && yleftb <= k - 1){
					area--;
				}
			}
		}
		
		for (int i = xleft2 + 1; i < xright2 + 1; i++){
			for (int k = yleft2 + 1; k < yright2 + 1; k++){
				if (i <= xrightb && xleftb <= i-1 && k <= yrightb && yleftb <= k - 1){
					area--;
				}
			}
		}
		
		pw.println(area);
		
		br.close();
		pw.close();

	}

}

