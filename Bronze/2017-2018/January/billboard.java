import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class billboard {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("billboard.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("billboard.out")));
		
		int area = 0;
		int x1 = Integer.parseInt(st.nextToken());
		int y1 = Integer.parseInt(st.nextToken());
		int x2 = Integer.parseInt(st.nextToken());
		int y2 = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int x3 = Integer.parseInt(st.nextToken());
		int y3 = Integer.parseInt(st.nextToken());
		int x4 = Integer.parseInt(st.nextToken());
		int y4 = Integer.parseInt(st.nextToken());
		
		int xleft = 100000;
		int ybot = 100000;
		int ytop = -100000;
		int xright = -10000;
		
		int counter = 0;
		
		for (int i = x1 + 1; i < x2 + 1; i++){
			for (int k = y1 +1; k < y2 + 1;k++){
				if (x2 >= i && i >= (x1+1) && y2 >= k && k >= (y1+1)){
					
					if (x4 >= i && i >= (x3+1) && y4 >= k && k >= (y3+1)){
						
					}
					else{
						counter++;
					
						if (i > xright){
							xright = i;
						}
						if (i-1 < xleft){
							xleft = i - 1;
						}
						if (k > ytop){
							ytop = k;
						}
						if (k-1 < ybot){
							ybot = k-1;
						}
					}
				}
				
			}
		}
		
		if (counter != 0){
			area = (xright - xleft) * (ytop - ybot);
		}
		
		pw.println(area);
		br.close();
		pw.close();

	}

}
