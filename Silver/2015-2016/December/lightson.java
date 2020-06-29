import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class lightson {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("lightson.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lightson.out")));
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		boolean[][] explored = new boolean[N + 2][N + 2];
		boolean[][] lightson = new boolean[N + 2][N + 2];
		
		explored[1][1] = true;
		lightson[1][1] = true;
		
		ArrayList<ArrayList<ArrayList<int[]>>> toggle = new ArrayList<ArrayList<ArrayList<int[]>>>();
		for (int i = 0; i < N + 2; i++){
			toggle.add(new ArrayList<ArrayList<int[]>>());
			for (int k = 0; k < N + 2; k++){
				toggle.get(i).add(new ArrayList<int[]>());
			}
		}
		
		for (int i = 0; i < M; i++){
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int[] temp = new int[2];
			temp[0] = Integer.parseInt(st.nextToken());
			temp[1] = Integer.parseInt(st.nextToken());
			toggle.get(x).get(y).add(temp);
			
			if (x == 1 && y == 1){
				lightson[temp[0]][temp[1]] = true;
			}
		}
		
		ArrayList<int[]> visited = new ArrayList<int[]>();
		int[] temp = new int[2];
		temp[0] = 1;
		temp[1] = 1;
		visited.add(temp);
		
		boolean possible = true;
		while (possible){
			int size = visited.size();
			boolean grew = false;
			
			for (int i = size - 1; i >= 0; i--){
				int[] rand = visited.get(i);
				int x = rand[0];
				int y = rand[1];
				
				if (explored[x - 1][y] == false && lightson[x - 1][y] == true){
					explored[x - 1][y] = true;
					for (int[] t: toggle.get(x - 1).get(y)){
						lightson[t[0]][t[1]] = true;
					}
					int[] rand2 = new int[2];
					rand2[0] = x - 1;
					rand2[1] = y;
					visited.add(rand2);
					grew = true;
					break;
				}
				
				if (explored[x + 1][y] == false && lightson[x + 1][y] == true){
					explored[x + 1][y] = true;
					for (int[] t: toggle.get(x + 1).get(y)){
						lightson[t[0]][t[1]] = true;
					}
					int[] rand2 = new int[2];
					rand2[0] = x + 1;
					rand2[1] = y;
					visited.add(rand2);
					grew = true;
					break;
				}
				
				if (explored[x][y - 1] == false && lightson[x][y - 1] == true){
					explored[x][y - 1] = true;
					for (int[] t: toggle.get(x).get(y - 1)){
						lightson[t[0]][t[1]] = true;
					}
					int[] rand2 = new int[2];
					rand2[0] = x;
					rand2[1] = y - 1;
					visited.add(rand2);
					
					grew = true;
					break;
				}
				
				if (explored[x][y + 1] == false && lightson[x][y + 1] == true){
					explored[x][y + 1] = true;
					for (int[] t: toggle.get(x).get(y + 1)){
						lightson[t[0]][t[1]] = true;
					}
					int[] rand2 = new int[2];
					rand2[0] = x;
					rand2[1] = y + 1;
					visited.add(rand2);
					
					grew = true;
					break;
				}	
			}
			if (grew == false){
				possible = false;
			}
		}
		
		int counter = 0;
		for (int i = 1; i < N + 1; i++){
			for (int k = 1; k < N + 1; k++){
				if (lightson[i][k] == true){
					counter++;
				}
			}
		}
		
		pw.println(counter);
		pw.close();
		br.close();
	}
}
