import java.util.*;
import java.io.*;

public class where {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("where.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("where.out")));
		int N = Integer.parseInt(br.readLine());
		String[][] data = new String[N + 2][N + 2];
		HashSet<Integer>[][] PCL = new HashSet[N + 2][N + 2];
		for (int i = 1; i <= N; i++){
			String temp = br.readLine();
			for (int k = 1; k <= N; k++){
				PCL[k][i] = new HashSet<Integer>();
				data[k][i] = temp.substring(k - 1, k);
			}
		}
		
		int counter = 0;
		for (int ystep = N - 1; ystep >= 0; ystep--){
			for (int xstep = N - 1; xstep >= 0; xstep--){
				for (int y = 1; y <= N - ystep; y++){
					for (int x = 1; x <= N - xstep; x++){
						
						boolean[][] explored = new boolean[N + 2][N + 2];
						LinkedList<int[]> q = new LinkedList<int[]>();
						
						int[] nums = new int[26];
						for (int numx = x; numx <= x + xstep; numx++){
							for (int numy = y; numy <= y + ystep; numy++){
								if (explored[numx][numy] == true){
									continue;
								}
								explored[numx][numy] = true;
								int[] t1 = {numx, numy};
								q.add(t1);
								String s = data[numx][numy];
								nums[s.compareTo("A")]++;
								
								while (!q.isEmpty()){
									t1 = q.remove();
									if (t1[0] + 1 <= (x + xstep) && explored[t1[0] + 1][t1[1]] == false && data[t1[0] + 1][t1[1]].equals(s)){
										explored[t1[0] + 1][t1[1]] = true;
										int[] t2 = {t1[0] + 1, t1[1]};
										q.add(t2);
									}
									if (t1[1] + 1 <= (y + ystep) && explored[t1[0]][t1[1] + 1] == false && data[t1[0]][t1[1] + 1].equals(s)){
										explored[t1[0]][t1[1] + 1] = true;
										int[] t2 = {t1[0], t1[1] + 1};
										q.add(t2);
									}
									if (t1[0] - 1 >= x && explored[t1[0] - 1][t1[1]] == false && data[t1[0] - 1][t1[1]].equals(s)){
										explored[t1[0] - 1][t1[1]] = true;
										int[] t2 = {t1[0] - 1, t1[1]};
										q.add(t2);
									}
									if (t1[1] - 1 >= y && explored[t1[0]][t1[1] - 1] == false && data[t1[0]][t1[1] - 1].equals(s)){
										explored[t1[0]][t1[1] - 1] = true;
										int[] t2 = {t1[0], t1[1] - 1};
										q.add(t2);
									}
								}
								
							}
						}
						
						int one = 0;
						int two = 0;
						for (int val: nums){
							if (val >= 2){
								two++;
							}
							if (val == 1){
								one++;
							}
						}
						if (one != 1 || two != 1){
							continue;
						}
						
						// check if in PCL
						HashSet<Integer> t = new HashSet<Integer>();
						for (int val: PCL[x][y]){
							t.add(val);
						}
						
						boolean b = true;
						for (int m = y; m <= y + ystep; m++){
							if (b == false){
								break;
							}
							for (int n = x; n <= x + xstep; n++){
								HashSet<Integer> t2 = new HashSet<Integer>();
								for (int val: t){
									if (!PCL[n][m].contains(val)){
										t2.add(val);
									}
								}
								for (int val: t2){
									t.remove(val);
								}
								if (t.isEmpty()){
									b = false;
									break;
								}
							}
						}
						if (b == false){
							counter++;
							for (int numx = x; numx <= x + xstep; numx++){
								for (int numy = y; numy <= y + ystep; numy++){
									PCL[numx][numy].add(counter);
								}
							}
						}
						// end
					}
				}
			}
		}
		pw.println(counter);
		pw.close();
		br.close();
	}
}
