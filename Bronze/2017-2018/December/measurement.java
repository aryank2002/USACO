import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class measurement {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("measurement.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("measurement.out"));
		PrintWriter pw = new PrintWriter(bw);
		
		int N = Integer.parseInt(st.nextToken());
		int[] Mildred = new int[101];
		int[] Bessie = new int[101];
		int[] Elsie = new int[101];
		
		String[] display = new String[101];
		
		int[] Mildredch = new int[101];
		int[] Bessiech = new int[101];
		int[] Elsiech = new int[101];
		
		Mildred[0] = 7;
		Bessie[0] = 7;
		Elsie[0] = 7;
		
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			int day = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			int change = Integer.parseInt(st.nextToken());
			
			if (name.equals("Bessie")){
				Bessiech[day] = change; 
			}
			
			else if (name.equals("Mildred")){
				Mildredch[day] = change; 
			}
			
			else if (name.equals("Elsie")){
				Elsiech[day] = change; 
			}
		}
		
		for (int i = 1; i < 101; i++){
			Mildred[i] = Mildred[i - 1] + Mildredch[i];
		}
		
		for (int i = 1; i < 101; i++){
			Bessie[i] = Bessie[i - 1] + Bessiech[i];
		}
		
		for (int i = 1; i < 101; i++){
			Elsie[i] = Elsie[i - 1] + Elsiech[i];
		}
		
		for (int i = 0; i < 101; i++){
			int max = 0;
			String cows = "";
			
			if (Mildred[i] > max){
				max = Mildred[i];
			}
			if (Bessie[i] > max){
				max = Bessie[i];
			}
			if (Elsie[i] > max){
				max = Elsie[i];
			}
			
			if (Mildred[i] == max){
				cows = cows + "1";
			}
			if (Bessie[i] == max){
				cows = cows + "2";
			}
			if (Elsie[i] == max){
				cows = cows + "3";
			}
			display[i] = cows;
		}
		
		int changes = 0;
		for (int i = 1; i < 101; i++){
			if (display[i].equals(display[i - 1])){
			}
			else{
				changes++;
			}
		}
		
		pw.println(changes);
		pw.close();
		br.close();

	}

}
