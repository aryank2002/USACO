import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class mooyomooyo {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("mooyomooyo.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mooyomooyo.out")));
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] data = new int[N + 2][12];
		for (int i = 1; i < N + 1; i++){
			st = new StringTokenizer(br.readLine());
			String temp = new String(st.nextToken());
			for (int k = 1; k < 11; k++){
				data[i][k] = Integer.parseInt(temp.substring(k - 1, k));
			}
		}
		
		boolean repeat = true;
		while (repeat){
			repeat = false;
			for (int i = 1; i < N + 1; i++){
				for (int k = 1; k < 11; k++){
					int counter = 1;
					int val = data[i][k];
					if (val == 0){
						continue;
					}
					
					boolean explored[][] = new boolean[N + 1][11];
					int[] temp = new int[2];
					temp[0] = i;
					temp[1] = k;
					explored[i][k] = true;
					
					LinkedList<int[]> path = new LinkedList<int[]>();
					path.add(temp);
					while (!path.isEmpty()){
						int[] temp1 = path.removeFirst();
						if (data[temp1[0] - 1][temp1[1]] == val && explored[temp1[0] -1][temp1[1]] == false){
							int[] t2 = new int[2];
							t2[0] = temp1[0] - 1;
							t2[1] = temp1[1];
							path.add(t2);
							explored[temp1[0] - 1][temp1[1]] = true;
							counter++;
						}
						if (data[temp1[0] + 1][temp1[1]] == val && explored[temp1[0] + 1][temp1[1]] == false){
							int[] t2 = new int[2];
							t2[0] = temp1[0] + 1;
							t2[1] = temp1[1];
							path.add(t2);
							explored[temp1[0] + 1][temp1[1]] = true;
							counter++;
						}
						if (data[temp1[0]][temp1[1] - 1] == val && explored[temp1[0]][temp1[1] - 1] == false){
							int[] t2 = new int[2];
							t2[0] = temp1[0];
							t2[1] = temp1[1] - 1;
							path.add(t2);
							explored[temp1[0]][temp1[1] - 1] = true;
							counter++;
						}
						if (data[temp1[0]][temp1[1] + 1] == val && explored[temp1[0]][temp1[1] + 1] == false){
							int[] t2 = new int[2];
							t2[0] = temp1[0];
							t2[1] = temp1[1] + 1;
							path.add(t2);
							explored[temp1[0]][temp1[1] + 1] = true;
							counter++;
						}
	 				}
					if (counter >= K){
						for (int m = 1; m < N + 1; m++){
							for (int n = 1; n < 11; n++){
								if (explored[m][n] == true){
									data[m][n] = 0;
									repeat = true;
								}
							}
						}
					}
					
				}
				
			}
			if (repeat){
				for (int m = 1; m < 11; m++){
					for (int n = N - 1; n > 0; n--){
						if (data[n][m] != 0){
							for (int i = N; i > n; i--){
								if (data[i][m] == 0){
									data[i][m] = data[n][m];
									data[n][m] = 0;
									break;
								}
							}
						}
					}
					
				}
			}
			
		}
		
		for (int i = 1; i < N; i++){
			for (int m = 1; m < 11; m++){
				pw.print(data[i][m]);
			}
			pw.println();
		}
		
		for (int m = 1; m < 11; m++){
			pw.print(data[N][m]);
		}
		
		pw.close();
		br.close();
	}

}
